package dcom.shop.application.dc.dispatch;

import dcom.shop.application.mobile.dispatch.TransactBO;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TransactDC {
    TransactBO transactBO;

    TrxResult trxResult = null;

    class TrxResult {
        String status;
        String message;

        public TrxResult(String statusStr, String messageStr) {
            this.status = statusStr;
            this.message = messageStr;
        }

        public String getStatus() {
            if (this.status.contains("@xsi")) {
                return "";
            }
            return status;
        }

        public String getMessage() {
            if (this.message.contains("@xsi")) {
                return "";
            }
            return message;
        }

        String getResult() {
            if (getStatus().equals("S")) {
                return "Transaction Successful!";
            } else {
                return "Transaction Unsuccessful! " + getMessage();
            }
        }
    }

    public TransactDC() {
        super();
    }

    public TransactBO[] getTransact() {
        String preserveTrx = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.preserveTrx}").toString();
        if (preserveTrx.equals("PRESERVE")) {
            TransactBO[] transactArray = new TransactBO[1];
            transactArray[0] = transactBO;
            return transactArray;
        }
        transactBO = new TransactBO();
        //Defaulting values in the new object
        String nextOpSeq = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.nextOpSeq}").toString();
        String compSubInv = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.compSubInv}").toString();
        String compLocator = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.compLocator}").toString();
        String opSeq = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.opSeq}").toString();

        String assemblyUom = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.uom}").toString();
        String lastDept = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.lastDept}").toString();
        String lastOpSeq = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.lastOpSeq}").toString();
        String nextDept = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.nextDept}").toString();
        String orgCode =
            AdfmfJavaUtilities.evaluateELExpression("#{preferenceScope.feature.dcom.shop.MyWarehouse.OrgCodePG.OrgCode}").toString();
        String qtyOp = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.qtyOp}").toString();
        String qtyCompleted = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.qtyCompleted}").toString();
        String qtyQueue = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.qtyQueue}").toString();
        String qtyScrapped = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.qtyScrapped}").toString();
        String qtyRun = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.qtyRun}").toString();
        String qty2Move = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.qty2Move}").toString();
        String qtyCScrapQty = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.qtyCScrapQty}").toString();
        String qtyRejected = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.qtyRejected}").toString();

        transactBO.setOrgCode(orgCode);
        transactBO.setIsNewEntity(true);
        transactBO.setToDept(nextDept);
        transactBO.setToOpSeq(nextOpSeq);
        if (opSeq.equals(lastOpSeq)) {
            transactBO.setToStep("To Move");
        } else {
            transactBO.setToStep("Queue");
        }
        transactBO.setTxnUom(assemblyUom);
        transactBO.setFromDept(lastDept);
        transactBO.setFromOpSeq(opSeq);
        transactBO.setTrxQty(qtyOp);
        transactBO.setScrapQty(qtyScrapped);
        TransactBO[] transactArray = new TransactBO[1];
        transactArray[0] = transactBO;
        return transactArray;
    }

    public String saveTransaction(TransactBO transactBo) {
        String subInv = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.FromSubinventory}").toString();
        String locator = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.FromLocator}").toString();
        transactBO.setSubinv(subInv);
        transactBO.setLocator(locator);

        StringBuffer strPayload = new StringBuffer();
        RestCallerUtil restCallerUtil = new RestCallerUtil();
        String trxType = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.trxType}").toString();
        strPayload.append("{");
        strPayload.append(RestURI.getPayloadHeader());
        strPayload.append(",");
        strPayload.append(transactBo.getPayload());
        strPayload.append("}");
        String jsonArrayAsString = restCallerUtil.invokeUPDATE(RestURI.PostWipTrx(), strPayload.toString());
        if (jsonArrayAsString != null) {
            try {
                JSONParser parser = new JSONParser();
                Object object;
                object = parser.parse(jsonArrayAsString);
                JSONObject jsonObject = (JSONObject) object;
                JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                String status = jsObject.get("XSTATUS").toString();
                String msg = jsObject.get("XMSG").toString();
                trxResult = new TrxResult(status, msg);
                return trxResult.getResult();
            } catch (Exception e) {
                e.getMessage();
            }
        }

        return null;
    }

    public void rollback() {
        if (transactBO != null) {
            transactBO = null;
        }
        if (trxResult != null) {
            trxResult = null;
        }
    }
}
