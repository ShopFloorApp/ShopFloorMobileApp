package dcom.shop.Transaction.bean;

import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.el.ValueExpression;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.util.Utility;
import oracle.adfmf.util.logging.Trace;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TaskCountDC {
    public TaskCountDC() {
        super();
    }
    List s_TaskList = new ArrayList();
    

    
    public TaskCountEntityBean[] getAllTasks() {

        s_TaskList.clear();
        ValueExpression ve = null;
        System.out.println("Inside cycle count subinv service");
        Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
        String userName = (String)AdfmfJavaUtilities.evaluateELExpression("#{securityContext.userName}");
        String orgCode = null;
        
        ve = AdfmfJavaUtilities.getValueExpression("#{preferenceScope.feature.dcom.shop.MyWarehouse.OrgCodePG.OrgCode}", String.class);
        orgCode = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        
                
        String restURI = RestURI.PostCycleCountTasksURI();
        RestCallerUtil rcu = new RestCallerUtil();
        String payload = null;
        
        payload = "{\n" + 
        "    \"x\": {\n" + 
        "        \n" + 
        "        \"RESTHeader\": {\n" + 
        "            \"Responsibility\": \"DCOM_MOBILE_USER\",\n" + 
        "            \"RespApplication\": \"INV\",\n" + 
        "            \"SecurityGroup\": \"STANDARD\",\n" + 
        "            \"NLSLanguage\": \"AMERICAN\",\n" + 
        "            \"Org_Id\": \"82\"\n" + 
        "        },\n" + 
        "        \"InputParameters\": \n" + 
        "         { \"P_ORGANIZATION_CODE\": \""+orgCode+"\",\"P_USER\":\""+userName+"\"}\n" + 
        "    }\n" + 
        "}";  
        
        System.out.println("Calling create method");
        String jsonArrayAsString = (rcu.invokeUPDATE(restURI, payload)).toString();
        System.out.println("Received response");
        
        TaskCountEntityBean[] taskArray = null;
        
        try {

            JSONParser parser = new JSONParser();
            Object object = parser.parse(jsonArrayAsString);
            JSONObject jsonObject = (JSONObject) object;
            JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
            JSONObject jsObject1 = (JSONObject) jsObject.get("X_TASK_TBL");
            try {
                JSONArray array = (JSONArray) jsObject1.get("X_TASK_TBL_ITEM");
                if (array != null) {
                    int size = array.size();
                    //  ProductSearchEntity[] prodItems= new ProductSearchEntity[size];
                    for (int i = 0; i < size; i++) {


                        TaskCountEntityBean taskItems = new TaskCountEntityBean();
                        JSONObject jsObjectArrayData = (JSONObject) array.get(i);

                        taskItems.setSUBINVENTORY((jsObjectArrayData.get("SUBINVENTORY").toString()));   
                        taskItems.setITEM((jsObjectArrayData.get("ITEM").toString()));
                        taskItems.setLOCATOR((jsObjectArrayData.get("LOCATOR").toString()));
                        taskItems.setLPN((jsObjectArrayData.get("LPN").toString()));
                        taskItems.setCCNAME((jsObjectArrayData.get("CCNAME").toString()));
                        taskItems.setUOM((jsObjectArrayData.get("UOM").toString()));
                        taskItems.setOHQTY(Float.valueOf((jsObjectArrayData.get("OHQTY").toString())));
                        taskItems.setITEM_DESC((jsObjectArrayData.get("ITEM_DESC").toString()));
                        taskItems.setCC_ENTRY_ID(Integer.parseInt((jsObjectArrayData.get("CC_ENTRY_ID").toString())));
                        taskItems.setLOTCONTROL((jsObjectArrayData.get("LOTCONTROL").toString()));
                        taskItems.setSERIALCONTROL((jsObjectArrayData.get("SERIALCONTROL").toString()));
                        
                        
                        s_TaskList.add(taskItems);
                    }
                }
            } catch (ClassCastException e2) {
                JSONObject jsObject2 = (JSONObject) jsObject1.get("X_TASK_TBL_ITEM");
                if (jsObject2 != null) {
                    TaskCountEntityBean taskItems = new TaskCountEntityBean();
                    taskItems.setSUBINVENTORY((jsObject2.get("SUBINVENTORY").toString()));   
                    taskItems.setITEM((jsObject2.get("ITEM").toString()));
                    taskItems.setLOCATOR((jsObject2.get("LOCATOR").toString()));
                    taskItems.setLPN((jsObject2.get("LPN").toString()));
                    taskItems.setCCNAME((jsObject2.get("CCNAME").toString()));
                    taskItems.setUOM((jsObject2.get("UOM").toString()));
                    taskItems.setOHQTY(Float.valueOf((jsObject2.get("OHQTY").toString())));
                    taskItems.setITEM_DESC((jsObject2.get("ITEM_DESC").toString()));
                    taskItems.setCC_ENTRY_ID(Integer.parseInt((jsObject2.get("CC_ENTRY_ID").toString())));
                    taskItems.setLOTCONTROL((jsObject2.get("LOTCONTROL").toString()));
                    taskItems.setSERIALCONTROL((jsObject2.get("SERIALCONTROL").toString()));
                    s_TaskList.add(taskItems);
                    
                }
            }


            taskArray = (TaskCountEntityBean[]) s_TaskList.toArray(new TaskCountEntityBean[s_TaskList.size()]);
            

        } catch (Exception e) {
            Trace.log("REST_JSON", Level.SEVERE, this.getClass(), "CycleCountTaskDC", e.getLocalizedMessage());
        }
        
      return taskArray;
    }
}
