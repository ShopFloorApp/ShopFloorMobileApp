package dcom.shop.application.dc.dispatch;

import dcom.shop.application.dc.dispatch.TransactDC.TrxResult;
import dcom.shop.application.mobile.dispatch.ReportTimeBO;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReportTimeDC {
    ReportTimeBO reportTimeBO;

    public ReportTimeDC() {
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
                return "Transaction Successful!";
            } else {
                return "Transaction Unsuccessful! " + getMessage();
            }
        }
    }


    public ReportTimeBO[] getReportTime() {
        reportTimeBO = new ReportTimeBO();
        String orgCode =
            AdfmfJavaUtilities.evaluateELExpression("#{preferenceScope.feature.dcom.shop.MyWarehouse.OrgCodePG.OrgCode}").toString();
        String jobOp = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.jobOps}").toString();
        reportTimeBO.setOrgCode(orgCode);
        reportTimeBO.setJobOp(jobOp);

        ReportTimeBO[] reportTimeArray = new ReportTimeBO[1];
        reportTimeArray[0] = reportTimeBO;
        return reportTimeArray;

    }

    public void rollback() {
        if (reportTimeBO != null) {
            reportTimeBO = null;
        }
        if (trxResult != null) {
            trxResult = null;
        }
    }

    public String saveReportTime(ReportTimeBO obj) {
        StringBuffer strPayload = new StringBuffer();
        RestCallerUtil restCallerUtil = new RestCallerUtil();

        strPayload.append("{\n" + "  \"x\": {\n" + "    \"RESTHeader\": {\n" +
                          "      \"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" +
                          "      \"RespApplication\": \"ONT\",\n" + "      \"SecurityGroup\": \"STANDARD\",\n" +
                          "      \"NLSLanguage\": \"AMERICAN\",\n" + "      \"Org_Id\": \"82\"\n" + "    },\n" +
                          "    \"InputParameters\": {\n" + "      \"PORGCODE\": \"" + obj.getOrgCode() + "\",\n" +
                          "      \"PTIME\": {\n" + "        \"JOBOP\":\"" + obj.getJobOp() + "\",\n" +
                          "        \"RESOURCECODE\":\"" + obj.getResourceCode() + "\",\n" +
                          "        \"INSTANCENUM\":\"" + obj.getInstanceNum() + "\",\n" + "        \"EMPLOYEE\":\"" +
                          obj.getEmployee() + "\",\n" + "        \"STARTTIME\":\"" + obj.getStartTime() + "\",\n" +
                          "        \"ENDTIME\":\"" + obj.getEndTime() + "\",\n" + "        \"QTY\":" + obj.getQty() +
                          ",\n" + "        \"UOM\":\"" + obj.getUom() + "\"\n" + "      }\n" + "    }\n" + "  }\n" +
                          "}");
        String jsonArrayAsString = restCallerUtil.invokeUPDATE(RestURI.PostReportTime(), strPayload.toString());
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
