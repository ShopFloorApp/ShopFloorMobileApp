package dcom.shop.application.dc.dispatch;

import dcom.shop.application.dc.dispatch.ReportTimeDC.TrxResult;
import dcom.shop.application.mobile.dispatch.ClockInOutBO;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ClockInOutDC {
    ClockInOutBO clockInOutBO;

    public ClockInOutDC() {
        super();
    }
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
                return "Transaction Successful !";
            } else {
                return "Transaction was Successful! " + getMessage();
            }
        }
    }

    public ClockInOutBO[] getClockInOutBO() {
        clockInOutBO = new ClockInOutBO();
        clockInOutBO.setOpSeq(AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.opSeq}").toString());
        clockInOutBO.setJobNumber(AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.jobNumber}").toString());
        clockInOutBO.setPDeptCode(AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.lastDept}").toString());
        clockInOutBO.setPorgCode(AdfmfJavaUtilities.evaluateELExpression("#{preferenceScope.feature.dcom.shop.MyWarehouse.OrgCodePG.OrgCode}").toString());
        clockInOutBO.setPJobOp(AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.jobOps}").toString());
        ClockInOutBO[] objArray = new ClockInOutBO[1];
        objArray[0] = this.clockInOutBO;
        return objArray;
    }

    public void rollback() {
        if (clockInOutBO != null) {
            clockInOutBO = null;
        }
        if (trxResult != null) {
            trxResult = null;
        }
    }

    public String saveTransaction(ClockInOutBO obj) {
        StringBuffer strPayload = new StringBuffer();
        RestCallerUtil restCallerUtil = new RestCallerUtil();

        strPayload.append("{\n" + "  \"x\": {\n" + "    \"RESTHeader\": {\n" +
                          "      \"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" +
                          "      \"RespApplication\": \"ONT\",\n" + "      \"SecurityGroup\": \"STANDARD\",\n" +
                          "      \"NLSLanguage\": \"AMERICAN\",\n" + "      \"Org_Id\": \"82\"\n" + "    },\n" +
                          "    \"InputParameters\": {\n" + "      \"PORGCODE\": \"" + obj.getPorgCode() + "\",\n" +
                          "      \"PDEPTCODE\": \"" + obj.getPDeptCode() + "\",\n" + "      \"PJOBOP\": \"" +
                          obj.getPJobOp() + "\",\n" + "      \"PINSTANCE\": \"" + obj.getPInstance() + "\",\n" +
                          "      \"PEMP\": \"" + obj.getPEmp() + "\",\n" + "      \"PRESOURCE\": \"" +
                          obj.getPResource() + "\"\n" + "    }\n" + "  }\n" + "}");

        String jsonArrayAsString = null;
        if (obj.getAction().equals("CLOCK OUT")) {
            jsonArrayAsString = restCallerUtil.invokeUPDATE(RestURI.PostClockOut(), strPayload.toString());
        } else if (obj.getAction().equals("CLOCK IN") || obj.getAction().equals("UNDO CLOCK IN")) {
            jsonArrayAsString = restCallerUtil.invokeUPDATE(RestURI.PostClockIn(), strPayload.toString());
        }

        if (jsonArrayAsString != null) {
            try {
                JSONParser parser = new JSONParser();
                Object object;
                object = parser.parse(jsonArrayAsString);
                JSONObject jsonObject = (JSONObject) object;
                JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                String status = jsObject.get("XSTATUS").toString();
                String message = jsObject.get("XMSG").toString();
                trxResult = new TrxResult(status, message);
                return trxResult.getResult();
            } catch (Exception e) {
                e.getMessage();
            }
        }
        return null;
    }
}
