package dcom.shop.application.dc.dispatch;

import dcom.shop.application.base.AViewObject;
import dcom.shop.application.mobile.dispatch.JobOperationBO;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
/*
 * Fetches the Job Operations based on the department selected on dispatch list page
 */
public class JobOperationDC extends AViewObject{
    
    protected static List<JobOperationBO> jobOpList = new ArrayList<JobOperationBO>();
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private static boolean isSortOperation = false;

    public JobOperationDC() {
        super();
    }

    public JobOperationBO[] getJobOperationBO() {
        JobOperationBO[] sJobOperation = null;
        if (!isSortOperation) {
            jobOpList.clear();
            if (isOffline()) {
                jobOpList = getCollectionFromDB(JobOperationDC.class);
                sJobOperation = jobOpList.toArray(new JobOperationBO[jobOpList.size()]);
            } else {
                getFromWS();
                sJobOperation = jobOpList.toArray(new JobOperationBO[jobOpList.size()]);
            }
        } else {
            isSortOperation = false;
            return sJobOperation = jobOpList.toArray(new JobOperationBO[jobOpList.size()]);
        }
        return sJobOperation;
    }

    public void getFromWS() {
        RestCallerUtil restCallerUtil = new RestCallerUtil();
        JobOperationBO[] jobOprArray = null;
        String orgCode = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.orgCode}").toString();
        String deptCode = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.deptName}").toString();

        StringBuffer inputPayload = new StringBuffer();
        inputPayload.append("{\n" + "  \"x\": {\n" + "    \"RESTHeader\": {\n" +
                            "      \"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" +
                            "      \"RespApplication\": \"ONT\",\n" + "      \"SecurityGroup\": \"STANDARD\",\n" +
                            "      \"NLSLanguage\": \"AMERICAN\",\n" + "      \"Org_Id\": \"82\"\n" + "    },\n" +
                            "    \"InputParameters\": {\n" + "      \"PORGCODE\": \"" + orgCode + "\",\n" +
                            "      \"PDEPTCODE\": \"" + deptCode + "\"\n" + "    }\n" + "  }\n" + "}");

        String jsonArrayAsString = restCallerUtil.invokeUPDATE(RestURI.PostGetJobOp(), inputPayload.toString());
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
                        jobOperationBO.setNextOpSeq(jsObject2.get("NEXTOPSEQ").toString());
                        jobOperationBO.setNextDept(jsObject2.get("NEXTDEPT").toString());
                        jobOperationBO.setLastOpSeq(jsObject2.get("LASTOPSEQ").toString());
                        jobOperationBO.setLastDept(jsObject2.get("LASTDEPT").toString());
                        jobOperationBO.setCompSubInv(jsObject2.get("COMPSUBINV").toString());
                        jobOperationBO.setCompLocator(jsObject2.get("COMPLOCATOR").toString());
                        jobOperationBO.setAssemblyUom(jsObject2.get("ASSEMBLYUOM").toString());
                        jobOpList.add(jobOperationBO);
                    }
                    super.updateSqlLiteTable(JobOperationBO.class, jobOpList);
                }
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }
    
    public void sortJobOperation(String sortByCol){
        jobOpList.clear();
        isSortOperation = true;
        String orderByClause = "ORDER BY "+sortByCol+ " ASC";
        jobOpList = super.getFilteredCollectionFromDB(JobOperationBO.class, orderByClause);
        providerChangeSupport.fireProviderRefresh("jobOperationBO");
        
    }
    public void addProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.addProviderChangeListener(l);
    }

    public void removeProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.removeProviderChangeListener(l);
    }
}
