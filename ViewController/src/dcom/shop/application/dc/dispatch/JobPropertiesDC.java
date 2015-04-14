package dcom.shop.application.dc.dispatch;

import dcom.shop.application.base.AViewObject;
import dcom.shop.application.mobile.dispatch.JobPropertiesBO;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class JobPropertiesDC extends AViewObject {
    protected static List<JobPropertiesBO> jobPropsList = new ArrayList<JobPropertiesBO>();

    public JobPropertiesDC() {
        super();
    }

    public JobPropertiesBO[] getJobPropertiesBO() {
        RestCallerUtil restCallerUtil = new RestCallerUtil();
        JobPropertiesBO[] jobPropsArray = null;
        String orgCode = AdfmfJavaUtilities.evaluateELExpression("#{preferenceScope.feature.dcom.shop.MyWarehouse.OrgCodePG.OrgCode}").toString();
        String deptCode = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.deptName}").toString();
        String jobNumber = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.jobNumber}").toString();
        StringBuffer strPayload = new StringBuffer();
        strPayload.append("{\n" + "  \"x\": {\n" + "    \"RESTHeader\": {\n" +
                          "      \"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" +
                          "      \"RespApplication\": \"ONT\",\n" + "      \"SecurityGroup\": \"STANDARD\",\n" +
                          "      \"NLSLanguage\": \"AMERICAN\",\n" + "      \"Org_Id\": \"82\"\n" + "    },\n" +
                          "    \"InputParameters\": {\n" + "      \"PORGCODE\": \"" + orgCode + "\",\n" +
                          "      \"PDEPTCODE\": \"" + deptCode + "\",\n" + "      \"PJOB\": \"" + jobNumber + "\"\n" +
                          "    }\n" + "  }\n" + "}");


        String jsonArrayAsString = restCallerUtil.invokeUPDATE(RestURI.PostGetJobProp(), strPayload.toString());
        if (jsonArrayAsString != null) {
            try {
                JSONParser parser = new JSONParser();
                Object object;
                object = parser.parse(jsonArrayAsString);
                JSONObject jsonObject = (JSONObject) object;
                JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                JSONObject jsObject1 = (JSONObject) jsObject.get("XJOBS");
                if (jsObject1 != null) {
                    JobPropertiesBO jobProps = new JobPropertiesBO();
                    //Populating the base object
                    jobProps.setJobOps(jsObject1.get("JOBOPS").toString());
                    jobProps.setProject(jsObject1.get("PROJECT").toString());
                    jobProps.setReadyStatus(jsObject1.get("READYSTATUS").toString());
                    jobProps.setDueDate(jsObject1.get("DUEDATE").toString());
                    jobProps.setJobStatus(jsObject1.get("JOBSTATUS").toString());
                    jobProps.setSchStartDate(jsObject1.get("SCHSTARTDATE").toString());

                    jobProps.setUnitNumber(jsObject1.get("UNITNUMBER").toString());
                    jobProps.setStartQty(getAttributeValue(jsObject1.get("STARTQTY")));
                    jobProps.setAssembly(jsObject1.get("ASSEMBLY").toString());
                    jobProps.setJobNumber(jsObject1.get("JOBNUMBER").toString());

                    jobProps.setSchComplDate(jsObject1.get("SCHCOMPLDATE").toString());
                    jobProps.setJobStartDate(jsObject1.get("JOBSTARTDATE").toString());

                    jobProps.setCompSubInv(jsObject1.get("COMPSUBINV").toString());
                    jobProps.setJobDesc(jsObject1.get("JOBDESC").toString());
                    jobProps.setAssemblyDesc(jsObject1.get("ASSEMBLYDESC").toString());
                    jobProps.setBuildSeq(getAttributeValue(jsObject1.get("BUILDSEQ").toString()));

                    jobProps.setScheduleGrp(jsObject1.get("SCHEDULEGRP").toString());

                    jobProps.setPriorQty(getAttributeValue(jsObject1.get("PRIORQTY").toString()));
                    jobProps.setLine(getAttributeValue(jsObject1.get("LINE").toString()));

                    jobProps.setUom((jsObject1.get("UOM").toString()));

                    jobProps.setNextDept((jsObject1.get("NEXTDEPT").toString()));
                    jobProps.setTasks((jsObject1.get("TASKS").toString()));
                    jobProps.setJobQty((getAttributeValue(jsObject1.get("JOBQTY").toString())));
                    jobProps.setCompLocator((jsObject1.get("COMPLOCATOR").toString()));

                    jobPropsList.add(jobProps);
                }
                jobPropsArray = jobPropsList.toArray(new JobPropertiesBO[jobPropsList.size()]);
                return jobPropsArray;

            } catch (Exception e) {
                e.getMessage();
            }
        }
        return jobPropsArray;
    }
}
