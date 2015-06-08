package dcom.shop.application.dc;

import dcom.shop.application.base.AViewObject;
import dcom.shop.application.database.ConnectionFactory;
import dcom.shop.application.dc.dispatch.JobOperationDC;
import dcom.shop.application.mobile.TaskBO;
import dcom.shop.application.mobile.dispatch.JobOperationBO;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;

import oracle.adfmf.javax.faces.model.SelectItem;

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
    private static List slist = new ArrayList<SelectItem>();

    public void refresh() {
        providerChangeSupport.fireProviderRefresh("tasks");
    }

    
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
        if (!isSortOperation) {
            s_list.clear();
            if (isOffline()) {
                s_list = getCollectionFromDB(TaskDC.class);
            } else {
                getFromWS();
            }

        }else{
            isSortOperation = false;
        }
        sTask = (TaskBO[]) s_list.toArray(new TaskBO[s_list.size()]);

        return sTask;
    }


    

    public void getTT(){
        String tt;
        String cc;
        Connection conn;
        try {
            conn = ConnectionFactory.getConnection();
        
        Statement stmt = conn.createStatement();
        slist.clear();
        StringBuffer query = new StringBuffer();
        query.append("SELECT COUNT(1) COUNT, TASKTYPE FROM TASK GROUP BY TASKTYPE;");
   
            try{
            ResultSet result = stmt.executeQuery(query.toString());
            while (result.next()) {
                tt = result.getObject("TASKTYPE")==null?"":result.getObject("TASKTYPE").toString();
                cc = result.getObject("COUNT")==null?"":result.getObject("COUNT").toString();
                
                SelectItem s1 = new SelectItem();
                s1.setLabel(tt+" ("+cc+")");
                s1.setValue(tt);
                
                slist.add(s1);
                
                }
                }catch(Exception e1){
                       e1.printStackTrace(); 
                    }
   
        } catch (Exception e) {
            e.printStackTrace();
        }
      

    }
    
    public SelectItem[] getFilterData(){
        SelectItem[] si = null;
        getTT();
        si= (SelectItem[]) slist.toArray(new SelectItem[slist.size()]);
        return si;
    }

    public void sortTasks(String criteria) {
            s_list.clear();
            isSortOperation = true;
            String orderByClause = "";
        if (criteria != null) {
            try {
                if(criteria.equalsIgnoreCase("PRIORITY_ASC")){
                    orderByClause = "ORDER BY PRIORITY ASC";
                }else if(criteria.equalsIgnoreCase("PRIORITY_DESC")){
                    orderByClause = "ORDER BY PRIORITY DESC";                    
                }else if(criteria.equalsIgnoreCase("TASK_TYPE_ASC")){
                    orderByClause = "ORDER BY TASKTYPE ASC";                    
                }else if(criteria.equalsIgnoreCase("TASK_TYPE_DESC")){
                    orderByClause = "ORDER BY TASKTYPE DESC";                    
                }else if(criteria.equalsIgnoreCase("TASK_ASC")){
                    orderByClause = "ORDER BY TASKNUM ASC";                    
                }else if(criteria.equalsIgnoreCase("TASK_DESC")){
                    orderByClause = "ORDER BY TASKNUM DESC";                    
                }                
             s_list =   super.getFilteredCollectionFromDB(TaskBO.class, orderByClause);
                //getTasks();
                refresh();
            } catch (Exception e) {
                String msg = e.getMessage();
                System.out.println("Error msg:" + msg);
            }
        }

    }
    
    public void filterTasks(String Criteria){
        s_list.clear();
        isSortOperation = true;
        String whereClause = "";
        
        if(Criteria != null){
            whereClause = "WHERE TASKTYPE IN ("+Criteria+");";
        }
        s_list = super.getFilteredCollectionFromDB(TaskBO.class, whereClause);
        //getTasks();
        refresh();
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
                        taskBO.setORGCODE(jsObject2.get("ORGCODE")==null?"":jsObject2.get("ORGCODE").toString());
                        taskBO.setTASKNUM(jsObject2.get("TASKNUM")==null?"":jsObject2.get("TASKNUM").toString());
                        taskBO.setTASKLINE(jsObject2.get("TASKLINE")==null?"":jsObject2.get("TASKLINE").toString());
                        taskBO.setITEM(jsObject2.get("ITEM")==null?"":jsObject2.get("ITEM").toString());
                        taskBO.setITEMDESC(jsObject2.get("ITEMDESC")==null?"":jsObject2.get("ITEMDESC").toString());
                        taskBO.setQTY(jsObject2.get("QTY")==null?"":jsObject2.get("QTY").toString());
                        taskBO.setPRIORITY(jsObject2.get("PRIORITY")==null?"":jsObject2.get("PRIORITY").toString());
                        taskBO.setTASKTYPE(jsObject2.get("TASKTYPE")==null?"":jsObject2.get("TASKTYPE").toString());
                        taskBO.setSUBINV(jsObject2.get("SUBINV")==null?"":jsObject2.get("SUBINV").toString());
                        taskBO.setLOCATOR(jsObject2.get("LOCATOR")==null?"":jsObject2.get("LOCATOR").toString());
                        taskBO.setSTATUS(jsObject2.get("STATUS")==null?"":jsObject2.get("STATUS").toString());
                        //taskBO.setCREATIONDATE((Date)jsObject2.get("CREATIONDATE"));
                        taskBO.setDESTSUBINV(jsObject2.get("DESTSUBINV")==null?"":jsObject2.get("DESTSUBINV").toString());
                        taskBO.setDESTLOCATOR(jsObject2.get("DESTLOCATOR")==null?"":jsObject2.get("DESTLOCATOR").toString());
                        taskBO.setEMPLOYEE(jsObject2.get("EMPLOYEE")==null?"":jsObject2.get("EMPLOYEE").toString());
                        //taskBO.setSTARTDATE((Date)jsObject2.get("STARTDATE"));
                        //taskBO.setENDDATE((Date)jsObject2.get("ENDDATE"));
                        taskBO.setUSERNAME(jsObject2.get("USERNAME")==null?"":jsObject2.get("USERNAME").toString());
                        s_list.add(taskBO);
                    }
                    super.updateSqlLiteTable(TaskBO.class, s_list);
                }
            } catch (Exception e) {
                e.getMessage();
            }
        }

    }
    
    
}
