package dcom.shop.application.dc;

import dcom.shop.application.base.AViewObject;
import dcom.shop.application.mobile.TaskBO;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class TaskDC extends AViewObject {
    public TaskDC() {
        super();
    }
    protected static List s_list = new ArrayList();
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    protected transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private static boolean isSortOperation = false;
    private static TaskBO[] sTask = null;

    public void addProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.addProviderChangeListener(l);
    }

    public void removeProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.removeProviderChangeListener(l);
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }

    public TaskBO[] getTasks() {
        String str = AdfmfJavaUtilities.getELValue("#{pageFlowScope.taskIdUp}") + "";
        if (!(("-1".equals(str) || "0".equals(str) || "1".equals(str)))) {
            //providerChangeSupport.fireProviderRefresh("tasks");
            s_list.clear();
            //getFromWS();
            getDummyTasks();
            sortTasks("TASK_ASC");
            sTask = (TaskBO[]) s_list.toArray(new TaskBO[s_list.size()]);
        }

        return sTask;
    }

    public void sortTasks(String criteria) {
        if (criteria != null) {
            try {
                if ("TASK_ASC".equals(criteria) || "TASK_DESC".equals(criteria)) {
                    Collections.sort(s_list, new Comparator() {
                        public int compare(Object o1, Object o2) {
                            TaskBO p1 = (TaskBO) o1;
                            TaskBO p2 = (TaskBO) o2;
                            int a = Integer.parseInt(p1.getTASKNUM());
                            int b = Integer.parseInt(p2.getTASKNUM());
                            if ("TASK_ASC".equals(criteria)) {
                                return a - b;
                            } else {
                                return b - a;
                            }
                        }
                    });
                } else if ("TASK_TYPE_ASC".equals(criteria) || "TASK_TYPE_DESC".equals(criteria)) {
                    Collections.sort(s_list, new Comparator() {
                        public int compare(Object o1, Object o2) {
                            TaskBO p1 = (TaskBO) o1;
                            TaskBO p2 = (TaskBO) o2;

                            if ("TASK_TYPE_ASC".equals(criteria)) {
                                AdfmfJavaUtilities.setELValue("#{pageFlowScope.taskIdUp}", -1);
                                AdfmfJavaUtilities.setELValue("#{pageFlowScope.taskTypeUp}", 1);
                                AdfmfJavaUtilities.setELValue("#{pageFlowScope.taskPriorityUp}", -1);
                                return p1.getTASKTYPE().compareTo(p2.getTASKTYPE());
                            } else {
                                AdfmfJavaUtilities.setELValue("#{pageFlowScope.taskIdUp}", -1);
                                AdfmfJavaUtilities.setELValue("#{pageFlowScope.taskTypeUp}", 0);
                                AdfmfJavaUtilities.setELValue("#{pageFlowScope.taskPriorityUp}", -1);
                                return p2.getTASKTYPE().compareTo(p1.getTASKTYPE());
                            }
                        }
                    });
                } else if ("PRIORITY_ASC".equals(criteria) || "PRIORITY_DESC".equals(criteria)) {
                    Collections.sort(s_list, new Comparator() {
                        public int compare(Object o1, Object o2) {
                            TaskBO p1 = (TaskBO) o1;
                            TaskBO p2 = (TaskBO) o2;
                            String a = p1.getPRIORITY();
                            String b = p2.getPRIORITY();

                            if ("PRIORITY_ASC".equals(criteria)) {
                                AdfmfJavaUtilities.setELValue("#{pageFlowScope.taskIdUp}", -1);
                                AdfmfJavaUtilities.setELValue("#{pageFlowScope.taskTypeUp}", -1);
                                AdfmfJavaUtilities.setELValue("#{pageFlowScope.taskPriorityUp}", 1);
                                return a.compareTo(b);
                            } else {
                                AdfmfJavaUtilities.setELValue("#{pageFlowScope.taskIdUp}", -1);
                                AdfmfJavaUtilities.setELValue("#{pageFlowScope.taskTypeUp}", -1);
                                AdfmfJavaUtilities.setELValue("#{pageFlowScope.taskPriorityUp}", 0);
                                return a.compareTo(b);
                            }

                        }
                    });
                }
            } catch (Exception e) {
                String msg = e.getMessage();
                System.out.println("Error msg:" + msg);
            }
        }

    }
    /*
    public void sortTaskId(ActionEvent ae){
            String str = AdfmfJavaUtilities.getELValue("#{pageFlowScope.taskIdUp}")+"";
            if("-1".equals(str)||"0".equals(str)){
                sortTasks("taskIdUp");
                }
            else if("1".equals(str)){
                sortTasks("taskIdDown");
                }
            sTask = (TaskBO[])s_list.toArray(new TaskBO[s_list.size()]);
            providerChangeSupport.fireProviderRefresh("tasks");
            //getTasks();
        }

    public void sortTaskType(ActionEvent ae){
            String str = AdfmfJavaUtilities.getELValue("#{pageFlowScope.taskTypeUp}")+"";
            if("-1".equals(str)||"0".equals(str)){
                sortTasks("taskTypeUp");
                }
            else if("1".equals(str)){
                sortTasks("taskTypeDown");
                }
            sTask = (TaskBO[])s_list.toArray(new TaskBO[s_list.size()]);
            providerChangeSupport.fireProviderRefresh("tasks");
            //getTasks();
        }

    public void sortTaskPriority(ActionEvent ae){
            String str = AdfmfJavaUtilities.getELValue("#{pageFlowScope.taskPriorityUp}")+"";
            if("-1".equals(str)||"0".equals(str)){
                sortTasks("taskPriorityUp");
                }
            else if("1".equals(str)){
                sortTasks("taskPriorityDown");
                }
            sTask = (TaskBO[])s_list.toArray(new TaskBO[s_list.size()]);
            providerChangeSupport.fireProviderRefresh("tasks");
            //getTasks();
        }

    */
    public void getDummyTasks() {
        TaskBO task = new TaskBO();
        task.setTASKNUM("51");
        task.setTASKTYPE("Cycle Count");
        task.setPRIORITY("High");
        s_list.add(task);

        task = new TaskBO();
        task.setTASKNUM("12");
        task.setTASKTYPE("Cycle Group");
        task.setPRIORITY("Low");
        s_list.add(task);

        task = new TaskBO();
        task.setTASKNUM("3");
        task.setTASKTYPE("Pack");
        task.setPRIORITY("Low");
        s_list.add(task);

        task = new TaskBO();
        task.setTASKNUM("45");
        task.setTASKTYPE("Pack");
        task.setPRIORITY("Low");
        s_list.add(task);

        task = new TaskBO();
        task.setTASKNUM("29");
        task.setTASKTYPE("Assemble");
        task.setPRIORITY("Medium");
        s_list.add(task);

        task = new TaskBO();
        task.setTASKNUM("34");
        task.setTASKTYPE("Inspection");
        task.setPRIORITY("High");
        s_list.add(task);

        task = new TaskBO();
        task.setTASKNUM("92");
        task.setTASKTYPE("Assemble");
        task.setPRIORITY("Medium");
        s_list.add(task);

    }

    public void getFromWS() {
        RestCallerUtil restCallerUtil = new RestCallerUtil();
        TaskBO[] taskArray = null;
        String orgCode = "";
        String deptCode = "";

        String keyword = "";

        String payload =
            "{\"x\": {\"RESTHeader\": {\"@xmlns\": \"http://xmlns.oracle.com/apps/inv/rest/DCOMTask/header\",\"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\"RespApplication\": \"ONT\",\"SecurityGroup\": \"STANDARD\",\n" +
            "\"NLSLanguage\": \"AMERICAN\",\"Org_Id\": \"82\"},\"InputParameters\": {\"PTASK\": {\"ORGCODE\": \"100\",\"UNRELEASED\": \"\"}}\n" +
            "}\n" + "}";

        String jsonArrayAsString = restCallerUtil.invokeUPDATE(RestURI.PostTaskDetailsURI(), payload.toString());
        if (jsonArrayAsString != null) {
            try {
                JSONParser parser = new JSONParser();
                Object object;
                object = parser.parse(jsonArrayAsString);
                JSONObject jsonObject = (JSONObject) object;
                JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                JSONObject jsObject1 = (JSONObject) jsObject.get("XMOVEORDLIST");
                JSONArray jsonJobArray = (JSONArray) jsObject1.get("XMOVEORDLIST_ITEM");
                if (jsonJobArray != null) {
                    int size = jsonJobArray.size();
                    taskArray = new TaskBO[size];
                    for (int i = 0; i < size; i++) {
                        JSONObject jsObject2 = (JSONObject) jsonJobArray.get(i);

                        TaskBO taskBO = new TaskBO();
                        taskBO.setORGCODE(jsObject2.get("ORGCODE").toString());
                        taskBO.setTASKNUM(jsObject2.get("TASKNUM").toString());
                        taskBO.setTASKLINE(jsObject2.get("TASKLINE").toString());
                        taskBO.setITEM(jsObject2.get("ITEM").toString());
                        taskBO.setITEMDESC(jsObject2.get("ITEMDESC").toString());
                        taskBO.setQTY(jsObject2.get("QTY").toString());
                        taskBO.setUOM(jsObject2.get("PRIORITY").toString());
                        taskBO.setTASKTYPE(jsObject2.get("TASKTYPE").toString());
                        taskBO.setSUBINV(jsObject2.get("SUBINV").toString());
                        taskBO.setLOCATOR(jsObject2.get("LOCATOR").toString());
                        taskBO.setSTATUS(jsObject2.get("STATUS").toString());
                        //taskBO.setCREATIONDATE((Date)jsObject2.get("CREATIONDATE"));
                        taskBO.setDESTSUBINV(jsObject2.get("DESTSUBINV").toString());
                        taskBO.setDESTLOCATOR(jsObject2.get("DESTLOCATOR").toString());
                        taskBO.setEMPLOYEE(jsObject2.get("EMPLOYEE").toString());
                        //taskBO.setSTARTDATE((Date)jsObject2.get("STARTDATE"));
                        //taskBO.setENDDATE((Date)jsObject2.get("ENDDATE"));
                        taskBO.setUSERNAME(jsObject2.get("USERNAME").toString());
                        s_list.add(taskBO);
                    }
                    //super.updateSqlLiteTable(JobOperationBO.class, s_list);
                }
            } catch (Exception e) {
                e.getMessage();
            }
        }

    }
}
