package dcom.shop.application.dc.dispatch;

import dcom.shop.application.base.AViewObject;
import dcom.shop.application.dc.dispatch.ClockInOutDC.TrxResult;
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

public class JobOperationDC extends AViewObject {

    protected static List<JobOperationBO> jobOpList = new ArrayList<JobOperationBO>();
    private ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private static boolean isSortOperation = false;
    private static boolean isJobSearch = false;
    private JobOperationBO[] sJobOperation = null;

    public JobOperationDC() {
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

    public JobOperationBO[] getJobOperationBO() {
        return sJobOperation;
    }

    public void fetchData() {
        if (!isSortOperation) {
            jobOpList.clear();
            if (isOffline()) {
                jobOpList = getCollectionFromDB(JobOperationDC.class);
            } else {
                AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchJobKeyword}", "");
                getFromWS();
            }
        } else {
            isSortOperation = false;
        }
        sJobOperation = jobOpList.toArray(new JobOperationBO[jobOpList.size()]);
        providerChangeSupport.fireProviderRefresh("jobOperationBO");
    }

    public void getFromWS() {
        RestCallerUtil restCallerUtil = new RestCallerUtil();
        JobOperationBO[] jobOprArray = null;
        String orgCode =
            AdfmfJavaUtilities.evaluateELExpression("#{preferenceScope.feature.dcom.shop.MyWarehouse.OrgCodePG.OrgCode}").toString();
        String deptCode = null;
        try {
            deptCode = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.deptName}").toString();
        } catch (NullPointerException ae) {
            deptCode = "";
        }
        String jobOp = null;
        try {
            jobOp = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.searchJobKeyword}").toString();
        } catch (NullPointerException ae) {
            jobOp = "";
        }

        StringBuffer inputPayload = new StringBuffer();
        inputPayload.append("{\n" + "  \"x\": {\n" + "    \"RESTHeader\": {\n" +
                            "      \"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" +
                            "      \"RespApplication\": \"ONT\",\n" + "      \"SecurityGroup\": \"STANDARD\",\n" +
                            "      \"NLSLanguage\": \"AMERICAN\",\n" + "      \"Org_Id\": \"82\"\n" + "    },\n" +
                            "    \"InputParameters\": {\n" + "      \"PORGCODE\": \"" + orgCode + "\",\n" +
                            "      \"PDEPTCODE\": \"" + deptCode + "\",\n" + "      \"PJOB\":\"" + jobOp + "\"\n" +
                            "    }\n" + "  }\n" + "}");

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
                        //Added for quantity defaulting in Dispatch Transaction Pages
                        jobOperationBO.setQtyOp(jsObject2.get("QTYOP").toString());
                        jobOperationBO.setQtyCompleted(jsObject2.get("QTYCOMPLETED").toString());
                        jobOperationBO.setQtyQueue(jsObject2.get("QTYQUEUE").toString());
                        jobOperationBO.setQtyScrapped(jsObject2.get("QTYSCRAPPED").toString());
                        jobOperationBO.setQtyRun(jsObject2.get("QTYRUN").toString());
                        jobOperationBO.setQty2Move(jsObject2.get("QTY2MOVE").toString());
                        jobOperationBO.setQtyCScrapQty(jsObject2.get("QTYCSCRAPQTY").toString());
                        jobOperationBO.setQtyRejected(jsObject2.get("QTYREJECTED").toString());
                        jobOperationBO.setCurrDept(jsObject2.get("CURRENTDEPT").toString());
                        jobOperationBO.setCurrOpSeq(jsObject2.get("CURRENTOPSEQ").toString());
                        //End Changes
                        jobOpList.add(jobOperationBO);
                    }
                    super.updateSqlLiteTable(JobOperationBO.class, jobOpList);
                }
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

    public void sortJobOperation(String sortByCol) {
        jobOpList.clear();
        isSortOperation = true;
        String orderByClause;
        if (sortByCol.equals("SCHSTARTDATE")) {
            orderByClause = "ORDER BY datetime(" + sortByCol + ") ASC";
        } else {
            orderByClause = "ORDER BY " + sortByCol + " ASC";
        }
        jobOpList = super.getFilteredCollectionFromDB(JobOperationBO.class, orderByClause);
        fetchData();
    }

    public void addProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.addProviderChangeListener(l);
    }

    public void removeProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.removeProviderChangeListener(l);
    }

    public void saveJobAction(JobOperationBO jobOperation) {
        String status = null;
        String message = null;
        RestCallerUtil restCallerUtil = new RestCallerUtil();
        JobOperationBO[] jobOprArray = null;
        String orgCode =
            AdfmfJavaUtilities.evaluateELExpression("#{preferenceScope.feature.dcom.shop.MyWarehouse.OrgCodePG.OrgCode}").toString();
        String deptCode = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.deptName}").toString();
        String pJobOp = jobOperation.getJobOps();
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.jobActionResult}", "");
        try {
            String pAction = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.JobAction}").toString();

            if (!pAction.equals("0")) {
                StringBuffer inputPayload = new StringBuffer();
                inputPayload.append("{\n" + "  \"x\": {\n" + "    \"RESTHeader\": {\n" +
                                    "      \"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" +
                                    "      \"RespApplication\": \"ONT\",\n" +
                                    "      \"SecurityGroup\": \"STANDARD\",\n" +
                                    "      \"NLSLanguage\": \"AMERICAN\",\n" + "      \"Org_Id\": \"82\"\n" +
                                    "    },\n" + "    \"InputParameters\": {\n" + "      \"PORGCODE\": \"" + orgCode +
                                    "\",\n" + "      \"PDEPTCODE\": \"" + deptCode + "\",\n" + "      \"PJOBOP\": \"" +
                                    pJobOp + "\",\n" + "      \"PACTION\": \"" + pAction + "\"\n" + "    }\n" +
                                    "  }\n" + "}");

                String jsonArrayAsString =
                    restCallerUtil.invokeUPDATE(RestURI.PostJobAction(), inputPayload.toString());
                if (jsonArrayAsString != null) {
                    try {
                        JSONParser parser = new JSONParser();
                        //Object object;
                        JSONObject jsonObject = (JSONObject) parser.parse(jsonArrayAsString);
                        //JSONObject jsonObject = (JSONObject) object;
                        JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                        status = jsObject.get("XSTATUS").toString();
                        message = jsObject.get("XMSG").toString();
                        trxResult = new TrxResult(status, message);
                        String result= trxResult.getResult();
                        AdfmfJavaUtilities.setELValue("#{pageFlowScope.jobActionResult}", result);
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                status = "F";
                message = "Please select a Job action!";
                
            }
        } catch (Exception ae) {
            status = "F";
            message = "Please select a Job action!";
        }
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.jobActionResult}", message);
    }

    public void searchJobNumber() {
        String searchKeyword = null;
        String whereClause = null;
        isJobSearch = true;
        jobOpList.clear();
        sJobOperation = null;
        try {
            searchKeyword = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.searchJobKeyword}").toString();
            whereClause = "WHERE JOBNUMBER LIKE '" + searchKeyword + "%'";
        } catch (Exception ae) {
            whereClause = "WHERE 1=1";
        }
        if (isOffline()) {
            jobOpList = super.getFilteredCollectionFromDB(JobOperationBO.class, whereClause);
        } else {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.deptName}", "");
            getFromWS();
        }
        sJobOperation = jobOpList.toArray(new JobOperationBO[jobOpList.size()]);
        providerChangeSupport.fireProviderRefresh("jobOperationBO");
    }
}
