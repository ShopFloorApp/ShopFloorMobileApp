package dcom.shop.application.dc.dispatch;

import dcom.shop.application.base.AViewObject;
import dcom.shop.application.mobile.dispatch.JobOperationsBO;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JobOperationsDC extends AViewObject {
    protected static List<JobOperationsBO> jobOpsList = new ArrayList<JobOperationsBO>();

    public JobOperationsDC() {
        super();
    }

    public JobOperationsBO[] getJobOperations() {
        RestCallerUtil restCallerUtil = new RestCallerUtil();
        JobOperationsBO[] jobOpsArray = null;
        String orgCode = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.orgCode}").toString();
        String deptCode = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.deptName}").toString();
        String jobOpsVal = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.jobOps}").toString();
        StringBuffer strPayload = new StringBuffer();
        strPayload.append("{\n" + "  \"x\": {\n" + "    \"RESTHeader\": {\n" +
                          "      \"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" +
                          "      \"RespApplication\": \"ONT\",\n" + "      \"SecurityGroup\": \"STANDARD\",\n" +
                          "      \"NLSLanguage\": \"AMERICAN\",\n" + "      \"Org_Id\": \"82\"\n" + "    },\n" +
                          "    \"InputParameters\": {\n" + "      \"PORGCODE\": \"" + orgCode + "\",\n" +
                          "      \"PDEPTCODE\": \"" + deptCode + "\",\n" + "      \"PJOB\": \"" + jobOpsVal + "\"\n" +
                          "    }\n" + "  }\n" + "}");

        String jsonArrayAsString = restCallerUtil.invokeUPDATE(RestURI.PostGetJobOps(), strPayload.toString());
        if (jsonArrayAsString != null) {
            try {
                JSONParser parser = new JSONParser();
                Object object;
                object = parser.parse(jsonArrayAsString);
                JSONObject jsonObject = (JSONObject) object;
                JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                JSONObject jsObject1 = (JSONObject) jsObject.get("XJOBOPS");
                if (jsObject1 != null) {
                    JobOperationsBO jobOps = new JobOperationsBO();
                    //Populating the base object
                    jobOps.setJobOps(jsObject1.get("JOBOPS").toString());
                    jobOps.setOpDesc(jsObject1.get("OPDESC").toString());
                    jobOps.setLastOpSeq(jsObject1.get("LASTOPSEQ").toString());
                    jobOps.setQtyCompleted(getAttributeValue(jsObject1.get("QTYCOMPLETED")));
                    jobOps.setQtyQueue(getAttributeValue(jsObject1.get("QTYQUEUE")));
                    jobOps.setOpSeq(getAttributeValue(jsObject1.get("OPSEQ")));
                    jobOps.setLastDept(jsObject1.get("LASTDEPT").toString());
                    jobOps.setQtyScrapped(getAttributeValue(jsObject1.get("QTYSCRAPPED").toString()));
                    jobOps.setDept(jsObject1.get("DEPT").toString());
                    jobOps.setJobOn(jsObject1.get("JOBON").toString());
                    jobOps.setJobNumber(jsObject1.get("JOBNUMBER").toString());
                    jobOps.setQtyRun(getAttributeValue(jsObject1.get("QTYRUN").toString()));

                    jobOps.setOpComplDate(jsObject1.get("OPCOMPLDATE").toString());

                    jobOps.setQty2move(getAttributeValue(jsObject1.get("QTY2MOVE").toString()));
                    jobOps.setNextOpSeq(getAttributeValue(jsObject1.get("NEXTOPSEQ").toString()));
                    jobOps.setQtyCScrapQty(getAttributeValue(jsObject1.get("QTYCSCRAPQTY").toString()));
                    jobOps.setOpCode(jsObject1.get("OPCODE").toString());

                    jobOps.setOpStartDate(jsObject1.get("OPSTARTDATE").toString());

                    jobOps.setNextDept(jsObject1.get("NEXTDEPT").toString());
                    jobOps.setQtyRejected(getAttributeValue(jsObject1.get("QTYREJECTED").toString()));
                    jobOpsList.add(jobOps);
                }
                jobOpsArray = jobOpsList.toArray(new JobOperationsBO[jobOpsList.size()]);
                return jobOpsArray;

            } catch (Exception e) {
                e.getMessage();
            }
        }
        return jobOpsArray;
    }
}
