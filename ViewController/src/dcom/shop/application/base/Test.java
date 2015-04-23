package dcom.shop.application.base;

import dcom.shop.application.mobile.TaskBO;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import java.util.List;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;

public class Test {
    public Test() {
        super();
    }
    protected static List s_list = new ArrayList();

    public static void main(String[] args) {
        Test test = new Test();
        String keyword = "%";
        String type="DIRECT_SHIP";
        String payload =
            "{\"x\": {\"RESTHeader\": {\"@xmlns\": \"http://xmlns.oracle.com/apps/inv/rest/DCOMShip/header\",\"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\"RespApplication\": \"ONT\",\"SecurityGroup\": \"STANDARD\",\n" +
            "\"NLSLanguage\": \"AMERICAN\",\"Org_Id\": \"82\"},\"InputParameters\": {\"PDOCKTYPE\": \""+type+"\",\"PORGCODE\": \"100\",\"PDOCKNAME\": \""+keyword+"\"}\n" + "}\n" + "}";
        
        payload =
                    "{\"x\": {\"RESTHeader\": {\"@xmlns\": \"http://xmlns.oracle.com/apps/inv/rest/DCOMInquiry/header\",\"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\"RespApplication\": \"ONT\",\"SecurityGroup\": \"STANDARD\",\n" +
                    "\"NLSLanguage\": \"AMERICAN\",\"Org_Id\": \"82\"},\"InputParameters\": {\"PLPNREQ\": {\"ORGCODE\": \"100\",\"LPN\": \"%" +
                    keyword + "%\"}}\n" + "}\n" + "}";
        /*    
        payload =
                    "{\"x\": {\"RESTHeader\": {\"@xmlns\": \"http://xmlns.oracle.com/apps/inv/rest/DCOMLOV/header\",\"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\"RespApplication\": \"ONT\",\"SecurityGroup\": \"STANDARD\",\n" +
                    "\"NLSLanguage\": \"AMERICAN\",\"Org_Id\": \"82\"},\"InputParameters\": {\"PWAREHOUSE\": \""+type+"\",\"PLOTTYPE\": \"%\",\"PITEM\": \"%\",\"PLOT\": \"%"+keyword+"%\"}\n" + "}\n" + "}";
        
        payload =
                    "{\"x\": {\"RESTHeader\": {\"@xmlns\": \"http://xmlns.oracle.com/apps/inv/rest/DCOMLOV/header\",\"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\"RespApplication\": \"ONT\",\"SecurityGroup\": \"STANDARD\",\n" +
                    "\"NLSLanguage\": \"AMERICAN\",\"Org_Id\": \"82\"},\"InputParameters\": {\"PSERIALTYPE\": \""+type+"\",\"PWAREHOUSE\": \"%\",\"PITEM\": \"%\",\"PSERIAL\": \"%"+keyword+"%\"}\n" + "}\n" + "}";
        */
        
        payload =
                    "{\n" + "\"GET_SO_PER_ORG_Input\":\n" + "{\n" +
                    "\"@xmlns\": \"http://xmlns.oracle.com/apps/fnd/rest/GetSoPerOrgSvc/get_so_per_org/\",\n" +
                    "   \"RESTHeader\": {\"@xmlns\": \"http://xmlns.oracle.com/apps/fnd/rest/GetSoPerOrgSvc/header\",\n" +
                    "                  \"Responsibility\": \"DCOM_MOBILE_USER\",\n" +
                    "                  \"RespApplication\": \"INV\",\n" +
                    "                  \"SecurityGroup\": \"STANDARD\",\n" +
                    "                  \"NLSLanguage\": \"AMERICAN\",\n" + "                  \"Org_Id\": \"82\"\n" +
                    "                 },\n" + "   \"InputParameters\": \n" + 
                    "                   {\"POU\": \"\",\n" +
                    "                   \"PTYPE\": \""+type+"\",\n" +
                       "                   \"PORDER\": \"\",\n" +
                    "                    \"PWAREHOUSE\": \"\"\n }\n" + "}\n" + "}\n";
        
        payload =   "{\"x\": {\"RESTHeader\": {\"@xmlns\": \"http://xmlns.oracle.com/apps/inv/rest/DCOMTask/header\",\"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\"RespApplication\": \"ONT\",\"SecurityGroup\": \"STANDARD\",\n" +
                    "\"NLSLanguage\": \"AMERICAN\",\"Org_Id\": \"82\"},\"InputParameters\": {\"PTASK\": {\"ORGCODE\": \"100\",\"UNRELEASED\": \"\"}}\n" + "}\n" + "}";
        
        //System.out.println("payload: "+payload);
        
        TaskBO[] sTask = getTasks();
        
        //s_list.clear();
        //getDummyTasks();
        sortTaskType("-1");
        sTask = (TaskBO[])s_list.toArray(new TaskBO[s_list.size()]); 
        for(int i=0;i<s_list.size();i++){
                System.out.println("SList-"+i+":"+sTask[i].getTASKNUM()+"-"+sTask[i].getTASKTYPE());
            }
        
        
    }
    
    public static TaskBO[] getTasks() {
            s_list.clear();        
            TaskBO[] sTask = null;
            getDummyTasks();
            sTask = (TaskBO[])s_list.toArray(new TaskBO[s_list.size()]); 
            for(int i=0;i<s_list.size();i++){
                    System.out.println("SList-"+i+":"+sTask[i].getTASKNUM()+"-"+sTask[i].getTASKTYPE());
                }
            
            Collections.sort(s_list, new Comparator(){
             
                        public int compare(Object o1, Object o2) {
                            TaskBO p1 = (TaskBO) o1;
                            TaskBO p2 = (TaskBO) o2;
                            int a=Integer.parseInt(p1.getTASKNUM());
                            int b=Integer.parseInt(p2.getTASKNUM());
                            System.out.println("ID result:"+(a-b));
                            return a-b;
                            
                           //return p1.getTASKNUM().compareTo(p2.getTASKNUM());
                        }
             
                    });
            
            sTask = (TaskBO[])s_list.toArray(new TaskBO[s_list.size()]); 
            
            return sTask;
        }
    
    public static void sortTasks(String criteria){
        if("taskIdUp".equals(criteria)||"taskIdDown".equals(criteria)){
                Collections.sort(s_list, new Comparator(){
                            public int compare(Object o1, Object o2) {
                                TaskBO p1 = (TaskBO) o1;
                                TaskBO p2 = (TaskBO) o2;
                                int a=Integer.parseInt(p1.getTASKNUM());
                                int b=Integer.parseInt(p2.getTASKNUM());
                                if("taskIdUp".equals(criteria)){
                                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.taskIdUp}", 1);
                                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.taskTypeUp}", -1);
                                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.taskPriorityUp}", -1);
                                    System.out.println("ID result:"+(a-b));
                                    return a-b;
                                }
                                else{
                                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.taskIdUp}", 0);
                                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.taskTypeUp}", -1);
                                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.taskPriorityUp}", -1);
                                    return b-a;
                                }
                            }
                        });
            }
        else if("taskTypeUp".equals(criteria)||"taskTypeDown".equals(criteria)){
                System.out.println("Sort collection");
                Collections.sort(s_list, new Comparator(){
                            public int compare(Object o1, Object o2) {
                                TaskBO p1 = (TaskBO) o1;
                                TaskBO p2 = (TaskBO) o2;
                                
                                if("taskTypeUp".equals(criteria)){
                                    System.out.println("Sort collection criteria:"+p1.getTASKTYPE()+","+p2.getTASKTYPE());
                                    //AdfmfJavaUtilities.setELValue("#{pageFlowScope.taskIdUp}", -1);
                                    //AdfmfJavaUtilities.setELValue("#{pageFlowScope.taskTypeUp}", 1);
                                    //AdfmfJavaUtilities.setELValue("#{pageFlowScope.taskPriorityUp}", -1);
                                    System.out.println("Result:"+p1.getTASKTYPE().compareToIgnoreCase(p2.getTASKTYPE()));
                                    return p1.getTASKTYPE().compareToIgnoreCase(p2.getTASKTYPE());
                                }
                                else{
                                    //AdfmfJavaUtilities.setELValue("#{pageFlowScope.taskIdUp}", -1);
                                    //AdfmfJavaUtilities.setELValue("#{pageFlowScope.taskTypeUp}", 0);
                                    //AdfmfJavaUtilities.setELValue("#{pageFlowScope.taskPriorityUp}", -1);
                                    return p2.getTASKTYPE().compareTo(p1.getTASKTYPE());
                                }
                            }
                        });
            }
        else if("taskPriorityUp".equals(criteria)||"taskPriorityDown".equals(criteria)){
                Collections.sort(s_list, new Comparator(){
                            public int compare(Object o1, Object o2) {
                                TaskBO p1 = (TaskBO) o1;
                                TaskBO p2 = (TaskBO) o2;
                                
                                if("taskPriorityUp".equals(criteria)){
                                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.taskIdUp}", -1);
                                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.taskTypeUp}", -1);
                                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.taskPriorityUp}", 1);
                                    return p1.getPRIORITY().compareTo(p2.getPRIORITY());
                                }
                                else{
                                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.taskIdUp}", -1);
                                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.taskTypeUp}", -1);
                                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.taskPriorityUp}", 0);
                                    return p2.getPRIORITY().compareTo(p1.getPRIORITY());
                                }
                                    
                            }
                        });
            }
        
        }
    
    public void sortTaskId(String arg){
            String str = arg;
            if("-1".equals(str)||"0".equals(str)){
                sortTasks("taskIdUp");
                }
            else if("1".equals(str)){
                sortTasks("taskIdDown");
                }
            //providerChangeSupport.fireProviderRefresh("tasks");
        }
    
    public static void sortTaskType(String arg){
            System.out.println("sortTaskType");
            String str = arg;
            if("-1".equals(str)||"0".equals(str)){
                    System.out.println("taskTypeUp");
                sortTasks("taskTypeUp");
                }
            else if("1".equals(str)){
                sortTasks("taskTypeDown");
                }
            //providerChangeSupport.fireProviderRefresh("tasks");
        }
    
    public void sortTaskPriority(String arg){
            String str = arg;
            if("-1".equals(str)||"0".equals(str)){
                sortTasks("taskPriorityUp");
                }
            else if("1".equals(str)){
                sortTasks("taskPriorityDown");
                }
            //providerChangeSupport.fireProviderRefresh("tasks");
        }
    
    
    public static void getDummyTasks(){
            TaskBO task=new TaskBO();
            task.setTASKNUM("51");
            task.setTASKTYPE("Cycle Count");
            task.setPRIORITY("High");
            s_list.add(task);
            
            task=new TaskBO();
            task.setTASKNUM("12");
            task.setTASKTYPE("Cycle Group");
            task.setPRIORITY("Low");
            s_list.add(task);
            
            task=new TaskBO();
            task.setTASKNUM("3");
            task.setTASKTYPE("Pack");
            task.setPRIORITY("Low");
            s_list.add(task);
            
            task=new TaskBO();
            task.setTASKNUM("45");
            task.setTASKTYPE("Pack");
            task.setPRIORITY("Low");
            s_list.add(task);
            
            task=new TaskBO();
            task.setTASKNUM("29");
            task.setTASKTYPE("Assemble");
            task.setPRIORITY("Medium");
            s_list.add(task);
            
            task=new TaskBO();
            task.setTASKNUM("34");
            task.setTASKTYPE("Inspection");
            task.setPRIORITY("High");
            s_list.add(task);
            
            task=new TaskBO();
            task.setTASKNUM("92");
            task.setTASKTYPE("Assemble");
            task.setPRIORITY("Medium");
            s_list.add(task);
            
        }
}
