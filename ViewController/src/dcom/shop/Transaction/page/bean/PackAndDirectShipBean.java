package dcom.shop.Transaction.page.bean;

import dcom.shop.application.mobile.DeliveryBO;
import dcom.shop.application.mobile.TaskBO;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.util.Map;
import java.util.logging.Level;

import javax.el.ValueExpression;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.util.Utility;
import oracle.adfmf.util.logging.Trace;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class PackAndDirectShipBean {
    public PackAndDirectShipBean() {
    }
    
    public void callDirectShipService(ActionEvent actionEvent){       
        ValueExpression ve = null;
        String response = null;
        System.out.println("Inside callPackService");
        String DELIVERYID=null;
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.DELIVERYID}", String.class);
        DELIVERYID = (ve.getValue(AdfmfJavaUtilities.getAdfELContext())+"").trim();
        if("{\"@xsi:nil\":\"true\"}".equals(DELIVERYID)){
            DELIVERYID = "";
        }   
                
        String SHIPMETHOD=null;
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.SHIPMETHOD}", String.class);
        SHIPMETHOD = (ve.getValue(AdfmfJavaUtilities.getAdfELContext())+"").trim();
        if("{\"@xsi:nil\":\"true\"}".equals(SHIPMETHOD)){
            SHIPMETHOD = "";
        }   
        
        String NETWT=null;
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.NETWT}", String.class);
        NETWT = (ve.getValue(AdfmfJavaUtilities.getAdfELContext())+"").trim();

        if("{\"@xsi:nil\":\"true\"}".equals(NETWT)){
            NETWT = "";
        }       
        
        String GROSSWT=null;
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.GROSSWT}", String.class);
        GROSSWT = ( ve.getValue(AdfmfJavaUtilities.getAdfELContext())+"").trim();
        if("{\"@xsi:nil\":\"true\"}".equals(GROSSWT)){
            GROSSWT = "";
        }       
        
        String WAYBILL=null;
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.WAYBILL}", String.class);
        WAYBILL = ( ve.getValue(AdfmfJavaUtilities.getAdfELContext())+"").trim();
        if("{\"@xsi:nil\":\"true\"}".equals(WAYBILL)){
            WAYBILL = "";
        } 
        
        
        String restURI = RestURI.PostDirectShipURI();
        RestCallerUtil rcu = new RestCallerUtil();
        String payload = null;
        
        payload = "{\"x\":\n" + 
        "{\n" + 
        "   \"RESTHeader\": {\"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" + 
        "                  \"RespApplication\": \"ONT\",\n" + 
        "                  \"SecurityGroup\": \"STANDARD\",\n" + 
        "                  \"NLSLanguage\": \"AMERICAN\",\n" + 
        "                  \"Org_Id\": \"82\"\n" + 
        "                 },\n" + 
        "   \"InputParameters\": \n" + 
        "      {\"PPACK\": {\"DOCKDOOR\": \""+DELIVERYID
                  +"\",\"LPN\": \""+SHIPMETHOD
                  +"\",\"SUBINV\": \""+NETWT
                  +"\",\"LOCATOR\": \""+GROSSWT
                  +"\",\"ITEM\": \""+WAYBILL
                  +"\"}}\n" + 
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
                
                String status =jsObject.get("XSTATUS").toString();
                String msg = jsObject.get("XMSG").toString();
                Map pageFlow = (Map) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope}");
                pageFlow.put("txnMsg", msg);
              
            }
        
        }catch(Exception e){
                Trace.log("REST_JSON",Level.SEVERE, this.getClass(),"ProductDetailsEntity", e.getLocalizedMessage());
            }
        
        
    }
    
    public void callPackService(ActionEvent actionEvent) {
        ValueExpression ve = null;
        String response = null;
        System.out.println("Inside callPackService");
        String dockDoor=null;
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.dockDoor}", String.class);
        dockDoor = (ve.getValue(AdfmfJavaUtilities.getAdfELContext())+"").trim();
        if("{\"@xsi:nil\":\"true\"}".equals(dockDoor)){
            dockDoor = "";
        }   
        
        String lpn=null;
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchLpnKeyword}", String.class);
        lpn = (ve.getValue(AdfmfJavaUtilities.getAdfELContext())+"").trim();
        if("{\"@xsi:nil\":\"true\"}".equals(lpn)){
            lpn = "";
        }   
        
        String subInventory=null;
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.subInv}", String.class);
        subInventory = (ve.getValue(AdfmfJavaUtilities.getAdfELContext())+"").trim();

        if("{\"@xsi:nil\":\"true\"}".equals(subInventory)){
            subInventory = "";
        }       
        
        String locator=null;
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.locator}", String.class);
        locator = (ve.getValue(AdfmfJavaUtilities.getAdfELContext())+"").trim();
        if("{\"@xsi:nil\":\"true\"}".equals(locator)){
            locator = "";
        }       
        
        String item=null;
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchKeyword}", String.class);
        item = (ve.getValue(AdfmfJavaUtilities.getAdfELContext())+"").trim();
        if("{\"@xsi:nil\":\"true\"}".equals(item)){
            item = "";
        } 
        
        String quantity=null;
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quantity}", String.class);
        quantity = (ve.getValue(AdfmfJavaUtilities.getAdfELContext())+"").trim();
        if("{\"@xsi:nil\":\"true\"}".equals(quantity)){
            quantity = "";
        } 
        
        String lotNumber=null;
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.lotNo}", String.class);
        lotNumber = (ve.getValue(AdfmfJavaUtilities.getAdfELContext())+"").trim();
        if("{\"@xsi:nil\":\"true\"}".equals(lotNumber)){
            lotNumber = "";
        } 
        
        String serialNumber=null;
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.serialNo}", String.class);
        serialNumber = (ve.getValue(AdfmfJavaUtilities.getAdfELContext())+"").trim();
        if("{\"@xsi:nil\":\"true\"}".equals(serialNumber)){
            serialNumber = "";
        } 
        
        String orderNumber=null;
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.documnetNumber}", String.class);
        orderNumber = (ve.getValue(AdfmfJavaUtilities.getAdfELContext())+"").trim();
        if("{\"@xsi:nil\":\"true\"}".equals(orderNumber)){
            orderNumber = "";
        } 
        
        String orderLineNumber=null;
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.orderLineNumber}", String.class);
        orderLineNumber = (ve.getValue(AdfmfJavaUtilities.getAdfELContext())+"").trim();
        if("{\"@xsi:nil\":\"true\"}".equals(orderLineNumber)){
            orderLineNumber = "";
        } 
        
        String restURI = RestURI.PostPackURI();
        RestCallerUtil rcu = new RestCallerUtil();
        String payload = null;
        
        payload = "{\"x\":\n" + 
        "{\n" + 
        "   \"RESTHeader\": {\"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" + 
        "                  \"RespApplication\": \"ONT\",\n" + 
        "                  \"SecurityGroup\": \"STANDARD\",\n" + 
        "                  \"NLSLanguage\": \"AMERICAN\",\n" + 
        "                  \"Org_Id\": \"82\"\n" + 
        "                 },\n" + 
        "   \"InputParameters\": \n" + 
        "      {\"PPACK\": {\"DOCKDOOR\": \""+dockDoor
                  +"\",\"LPN\": \""+lpn
                  +"\",\"SUBINV\": \""+subInventory
                  +"\",\"LOCATOR\": \""+locator
                  +"\",\"ITEM\": \""+item
                  +"\",\"QTY\": \""+quantity
                  +"\",\"ORDERNUM\": \""+orderNumber
                  +"\",\"LINENUM\": \""+orderLineNumber
                  +"\",\"LOTS\": \""+null
                  +"\",\"SERIALS\": \""+null
                  +"\"}}\n" + 
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
                
                String status =jsObject.get("XSTATUS").toString();
                String msg = jsObject.get("XMSG").toString();
                JSONObject jsObject1 = (JSONObject) jsObject.get("XOUT");
                
                DeliveryBO deliveryBO = new DeliveryBO();
                deliveryBO.setDELIVERYID(jsObject1.get("DELIVERYID")==null?"":jsObject1.get("DELIVERYID").toString());
                deliveryBO.setSHIPMETHOD(jsObject1.get("SHIPMETHOD")==null?"":jsObject1.get("SHIPMETHOD").toString());
                deliveryBO.setNETWT(jsObject1.get("NETWT")==null?"":jsObject1.get("NETWT").toString());
                deliveryBO.setGROSSWT(jsObject1.get("GROSSWT")==null?"":jsObject1.get("GROSSWT").toString());
                deliveryBO.setWAYBILL(jsObject1.get("WAYBILL")==null?"":jsObject1.get("WAYBILL").toString());
                
                AdfmfJavaUtilities.setELValue("#{pageFlowScope.DELIVERYID}", deliveryBO.getDELIVERYID());
                AdfmfJavaUtilities.setELValue("#{pageFlowScope.SHIPMETHOD}", deliveryBO.getSHIPMETHOD());
                AdfmfJavaUtilities.setELValue("#{pageFlowScope.NETWT}", deliveryBO.getNETWT());
                AdfmfJavaUtilities.setELValue("#{pageFlowScope.GROSSWT}", deliveryBO.getGROSSWT());
                AdfmfJavaUtilities.setELValue("#{pageFlowScope.DELIVERYID}", deliveryBO.getWAYBILL());
            }
        
        }catch(Exception e){
                Trace.log("REST_JSON",Level.SEVERE, this.getClass(),"ProductDetailsEntity", e.getLocalizedMessage());
            }
        
    }
}
