package dcom.shop.Transaction.bean;

import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.util.Map;
import java.util.logging.Level;

import javax.el.ValueExpression;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.util.Utility;
import oracle.adfmf.util.logging.Trace;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CountUtilBean {
    public CountUtilBean() {
    }
    
    
    public void clearCountPage(ActionEvent actionEvent) {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.name}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.FromSubinventory}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.FromLocator}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchLpnKeyword}", null);            
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.callingPg}", null); 
       
        clearDetailPage(actionEvent);
    }

    public void clearDetailPage(ActionEvent actionEvent) {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchKeyword}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.description}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.uom}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.availQty}", null);  
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.qty}", null);    
    }


    
    public void callCountRestService(ActionEvent actionEvent) {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.countTxnMsg}", null); 
        ValueExpression ve = null;
        System.out.println("Insidecycle count service");
        Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
        /* OrgItemEntity orgObj = new OrgItemEntity();
          orgObj.setOrgItem("STR");
          s_OrgList.add(orgObj);
          orgObj.setOrgItem("REN");
          s_OrgList.add(orgObj);*/
        String lpn = null;
        String subinv = null;
        String loc = null;
        String orgCode= null;
        String ccName = null;
        String itemNum = null;
        String uom = null;
        String uomQty = null;
        String action = null;
        String detailTxnType = null;
        String username = (String)AdfmfJavaUtilities.evaluateELExpression("#{securityContext.userName}");

        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.name}", String.class);
        ccName = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        
        ve = AdfmfJavaUtilities.getValueExpression("#{preferenceScope.feature.dcom.shop.MyWarehouse.OrgCodePG.OrgCode}", String.class);
        orgCode = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();

        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchLpnKeyword}", String.class);
        lpn = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.FromSubinventory}", String.class);
        subinv = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.FromLocator}", String.class);
        loc = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchKeyword}", String.class);
        itemNum = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.uom}", String.class);
        uom = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.availQty}", String.class);
        uomQty = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.callingPg}", String.class);
        action =((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.detTxnType}", String.class);
        detailTxnType =((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        
        String restURI = RestURI.PostCycleCountURI();
        RestCallerUtil rcu = new RestCallerUtil();
        String payload = null;
        
        payload = "{\n" + 
        "\"x\":\n" + 
        "{\"RESTHeader\": {\"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" + 
        "                  \"RespApplication\": \"ONT\",\n" + 
        "                  \"SecurityGroup\": \"STANDARD\",\n" + 
        "                  \"NLSLanguage\": \"AMERICAN\",\n" + 
        "                  \"Org_Id\": \"82\"\n" + 
        "                 },\n" + 
        "   \"InputParameters\": \n" + 
        "      {\"P_WMS_TRX\": {\"ORG_CODE\": \""+orgCode+"\",\"SUBINV\": \""+subinv+"\",\"PRIMARY_UOM_QTY\": \""+uomQty+"\",\"LOCATOR\": \""+loc+"\",\"CC_NAME\": \""+ccName+"\",\"PARENT_LPN\": \""+lpn+"\",\"ITEM_NUMBER\": \""+itemNum+"\",\"COUNT_UOM\": \""+uom+"\",\"ACTION\": \""+action+"\",\"USER_NAME\": \""+username+"\"}}" + 
        "}\n" + 
        "}";
        System.out.println("Calling create method");
        String jsonArrayAsString = (rcu.invokeUPDATE(restURI, payload)).toString();
        System.out.println("Received response");
        
        try{
            JSONParser parser = new JSONParser();
            Object object = parser.parse(jsonArrayAsString);
            JSONObject jsonObject = (JSONObject)object;
            JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
            if(jsObject != null){
             // QuickShipEntityBean quickShipStatus = new QuickShipEntityBean();
             
                String status =jsObject.get("X_STATUS").toString();
                String msg = jsObject.get("X_MESSAGE").toString();
                Map pageFlow = (Map) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope}");
                pageFlow.put("countTxnMsg", msg);
                if (action.equals("DETAIL")){
                if(detailTxnType.equals("SAVE")){
                    clearDetailPage(actionEvent);
                }
                else {
                    clearCountPage(actionEvent);
                }
                }
                else if(action.equals("SUMMARY")) {
                    clearCountPage(actionEvent);
                }
            }

        }catch(Exception e){
                Trace.log("REST_JSON",Level.SEVERE, this.getClass(),"ProductDetailsEntity", e.getLocalizedMessage());
            }
        
    }
}
