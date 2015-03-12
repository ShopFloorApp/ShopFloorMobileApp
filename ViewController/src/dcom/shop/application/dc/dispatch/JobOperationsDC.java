package dcom.shop.application.dc.dispatch;

import dcom.shop.application.mobile.dispatch.JobOperationsBO;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JobOperationsDC {
    protected static List<JobOperationsBO> jobOpsList = new ArrayList<JobOperationsBO>();

    public JobOperationsDC() {
        super();
    }

    public JobOperationsBO[] getJobOperations() {
        RestCallerUtil restCallerUtil = new RestCallerUtil();
        JobOperationsBO[] jobOpsArray = null;
        String strPayload =
            "{\"x\":{\"RESTHeader\":{\"Responsibility\":\"ORDER_MGMT_SUPER_USER\",\"RespApplication\":\"ONT\",\"SecurityGroup\":\"STANDARD\",\"NLSLanguage\":\"AMERICAN\",\"Org_Id\":\"82\"},\"InputParameters\":{\"PORGCODE\":\"100\",\"PDEPTCODE\":\"1001\"}}}";

        String jsonArrayAsString = restCallerUtil.invokeUPDATE(RestURI.PostGetDeptURI(), strPayload);
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
                    jobOps.setJobNumber((jsObject1.get("JOBNUMBER").toString()));
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
