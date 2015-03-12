package dcom.shop.application.dc.dispatch;

import dcom.shop.application.mobile.dispatch.JobOperationBO;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JobOperationDC {
    protected static List<JobOperationBO> jobOpList = new ArrayList<JobOperationBO>();

    public JobOperationDC() {
        super();
    }

    public JobOperationBO[] getJobOperationBO() {
        RestCallerUtil restCallerUtil = new RestCallerUtil();
        JobOperationBO[] jobOprArray = null;
        String strPayload =
            "{\n" + 
            "  \"x\": {\n" + 
            "    \"RESTHeader\": {\n" + 
            "      \"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" + 
            "      \"RespApplication\": \"ONT\",\n" + 
            "      \"SecurityGroup\": \"STANDARD\",\n" + 
            "      \"NLSLanguage\": \"AMERICAN\",\n" + 
            "      \"Org_Id\": \"82\"\n" + 
            "    },\n" + 
            "    \"InputParameters\": {\n" + 
            "      \"PORGCODE\": \"100\",\n" + 
            "      \"PDEPTCODE\": \"SHAKEDOWN\"\n" + 
            "    }\n" + 
            "  }\n" + 
            "}";

        String jsonArrayAsString = restCallerUtil.invokeUPDATE(RestURI.PostGetJobOp(), strPayload);
        if (jsonArrayAsString != null) {
            try {
                JSONParser parser = new JSONParser();
                Object object;
                object = parser.parse(jsonArrayAsString);
                JSONObject jsonObject = (JSONObject) object;
                JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                JSONObject jsObject1 = (JSONObject) jsObject.get("XJOBS");
                JSONArray jsonJobArray = (JSONArray) jsObject1.get("XJOBS_ITEM");
                if (jsonJobArray != null) {
                    int size = jsonJobArray.size();
                    jobOprArray = new JobOperationBO[size];
                    for (int i = 0; i < size; i++) {
                        JSONObject jsObject2 = (JSONObject) jsonJobArray.get(i);

                        JobOperationBO jobOperationBO = new JobOperationBO();
                        jobOperationBO.setJobNumber(jsObject2.get("JOBNUMBER").toString());
                        jobOperationBO.setAssembly(jsObject2.get("ASSEMBLY").toString());
                        jobOperationBO.setAssemblyDesc(jsObject2.get("ASSEMBLYDESC").toString());
                        jobOperationBO.setJobDesc(jsObject2.get("JOBDESC").toString());
                        jobOperationBO.setJobOps(jsObject2.get("JOBOPS").toString());
                        jobOperationBO.setJobStatus(jsObject2.get("JOBSTATUS").toString());
                        jobOperationBO.setReadyStatus(jsObject2.get("READYSTATUS").toString());
                        jobOperationBO.setSchStartDate(jsObject2.get("SCHSTARTDATE").toString());
                        jobOpList.add(jobOperationBO);
                    }
                    jobOprArray = jobOpList.toArray(new JobOperationBO[jobOpList.size()]);
                    return jobOprArray;
                }
            } catch (Exception e) {
                e.getMessage();
            }
        }
        return jobOprArray;

    }
}
