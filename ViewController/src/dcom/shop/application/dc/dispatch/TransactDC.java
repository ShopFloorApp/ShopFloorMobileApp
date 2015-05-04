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
            return status;
        }

        public String getMessage() {
            return message;
        }
    }

    public TransactDC() {
        super();
    }

    public TransactBO[] getTransact() {
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
        transactBO.setFromOpSeq("0");
        TransactBO[] transactArray = new TransactBO[1];
        transactArray[0] = transactBO;
        return transactArray;
    }

    public TrxResult saveTransaction(TransactBO transactBo) {
        String subInv = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.FromSubinventory}").toString();
        String locator = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.FromLocator}").toString();
        transactBO.setSubinv(subInv);
        transactBO.setLocator(locator);

        StringBuffer strPayload = new StringBuffer();
        RestCallerUtil restCallerUtil = new RestCallerUtil();
        String trxType = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.trxType}").toString();
        strPayload.append("{\n" + "  \"x\": {\n" + "    \"RESTHeader\": {\n" +
                          "      \"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" +
                          "      \"RespApplication\": \"ONT\",\n" + "      \"SecurityGroup\": \"STANDARD\",\n" +
                          "      \"NLSLanguage\": \"AMERICAN\",\n" + "      \"Org_Id\": \"82\"\n" + "    },\n" +
                          "    \"InputParameters\": {\n" + "      \"PWIPTXN\": {\n" +
                          "        \"APPS.XXDCOM_WIP_TXN_IN\": {\n" + "          \"ACTION\": \"" +
                          transactBo.getAction() + "\",\n" + "          \"TRXREF\": \"" + transactBo.getTrxRef() +
                          "\",\n" + "          \"SOURCETYPE\": \"" + transactBo.getSourceType() + "\",\n" +
                          "          \"TRXTYPE\": \"" + trxType + "\",\n" + "          \"TRXDATE\": \"" +
                          transactBo.getTrxDate() + "\",\n" + "          \"ORGCODE\": \"" + transactBo.getOrgCode() +
                          "\",\n" + "          \"ITEM\": \"" + transactBo.getItem() + "\",\n" +
                          "          \"SUBINV\": \"" + transactBo.getSubinv() + "\",\n" + "          \"LOCATOR\": \"" +
                          transactBo.getLocator() + "\",\n" + "          \"LPN\": \"" + transactBo.getLpn() + "\",\n" +
                          "          \"SOURCECODE\": \"" + transactBo.getSourceCode() + "\",\n" +
                          "          \"TRXQTY\": \"" + transactBo.getTrxQty() + "\",\n" + "          \"TXNUOM\": \"" +
                          transactBo.getTxnUom() + "\",\n" + "          \"GLACCOUNT\": \"" + transactBo.getGlAccount() +
                          "\",\n" + "          \"ACCOUNTALIAS\": \"" + transactBo.getAccountAlias() + "\",\n" +
                          "          \"REASON\": \"" + transactBo.getReason() + "\",\n" +
                          "          \"TRXNACTION\": \"" + transactBo.getTrxnAction() + "\",\n" +
                          "          \"TRXSOURCE\": \"" + transactBo.getTrxSource() + "\",\n" +
                          "          \"WIPENTITYNAME\": \"" + transactBo.getWipEntityName() + "\",\n" +
                          "          \"DEPT\": \"" + transactBo.getDept() + "\",\n" + "          \"FROMOPSEQ\": \"" +
                          transactBo.getFromOpSeq() + "\",\n" + "          \"TOOPSEQ\": \"" + transactBo.getToOpSeq() +
                          "\",\n" + "          \"CREATEDBY\": \"" + transactBo.getCreatedBy() + "\",\n" +
                          "          \"SALESORDER\": \"" + transactBo.getSalesOrder() + "\",\n" +
                          "          \"NOTES\": \"" + transactBo.getNotes() + "\",\n" + "          \"REFERENCE\": \"" +
                          transactBo.getReference() + "\",\n" + "          \"KANBAN\": \"" + transactBo.getKanban() +
                          "\",\n" + "          \"SCRAPQTY\": \"" + transactBo.getScrapQty() + "\",\n" +
                          "          \"FROMSTEP\": \"" + transactBo.getFromStep() + "\",\n" +
                          "          \"TOSTEP\": \"" + transactBo.getToStep() + "\",\n" +
                          "          \"OVERCOMPLFLAG\": \"" + transactBo.getOverComplFlag() + "\",\n" +
                          "          \"ATTRIB\": \"\",\n" + "          \"LOTS\": {\n" +
                          "            \"APPS.XXDCOM_LOT_TAB\": {\n" + "              \"LOTS_ITEM\": {\n" +
                          "                \"APPS.XXDCOM_LOT_TYPE\": {\n" + "                  \"LOT\": \"\",\n" +
                          "                  \"LOTQTY\": \"\"\n" + "                }\n" + "              }\n" +
                          "            }\n" + "          },\n" + "          \"SERIALS\": {\n" +
                          "            \"SERIALS_ITEM\": {\n" + "              \"APPS.XXDCOM_SERIAL_TYPE\": {\n" +
                          "                \"FROMSERIAL\": \"\",\n" + "                \"TOSERIAL\": \"\",\n" +
                          "                \"SERIALQTY\": \"\"\n" + "              }\n" + "            }\n" +
                          "          }\n" + "        }\n" + "      }\n" + "    }\n" + "  }\n" + "}");
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
                return trxResult;
            } catch (Exception e) {
                e.getMessage();
            }
        }
        return null;
    }

    public void rollback() {
        if (transactBO != null) {
            transactBO = null;
            trxResult = null;
        }
    }
}
