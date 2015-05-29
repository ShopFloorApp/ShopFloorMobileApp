package dcom.shop.Transaction.bean;

import dcom.shop.application.dc.transaction.ShippingLpnDC;
import dcom.shop.application.mobile.LPNBO;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;


import java.util.ArrayList;
import java.util.List;

import javax.el.ValueExpression;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.util.Utility;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class transactionUtil {
    public transactionUtil() {
    }

    public void executePickRelease(ActionEvent actionEvent) {
            System.out.println("Inside execute Pick Release");
            Utility.ApplicationLogger.info("Inside execute Pick Release");
            String restURI =  "/webservices/rest/DCOMShip/pickrelease/";
            String pickRule = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.pickRule}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.pickRule}"));
            String orderNum = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.documnetNumber}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.documnetNumber}"));
            
            String event =  "true".equalsIgnoreCase(AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.Event}").toString())?"CONCURRENT":"ONLINE";
            RestCallerUtil rcu = new RestCallerUtil();
            System.out.println("values to method are 1 "+pickRule+" 2 "+orderNum+" 3 "+event);
            String payload =
                "{\n" + 
                "\"PickRelease_Input\":\n" + 
                "{\n" + 
                "\"@xmlns\": \"http://xmlns.oracle.com/apps/per/rest/dcomship/pickrelease/\",\n" + 
                "   \"RESTHeader\": {\"@xmlns\": \"http://xmlns.oracle.com/apps/per/rest/dcomship/header\",\n" + 
                "                  \"Responsibility\": \"DCOM_MOBILE_USER\",\n" + 
                "                  \"RespApplication\": \"INV\",\n" + 
                "                  \"SecurityGroup\": \"STANDARD\",\n" + 
                "                  \"NLSLanguage\": \"AMERICAN\",\n" + 
                "                  \"Org_Id\": \"82\"\n" + 
                "                 },\n" + 
                "   \"InputParameters\": \n" + "{\"PPICKREC\":    "+
                "        {\"PICKRULE\": \""+pickRule+"\",\n" + 
                "         \"ORDERNUM\": \""+orderNum+"\",\n" +
                "       \"EVENT\": \""+event+"\" \n" + 
                "       }}\n" + 
                "}\n" + 
                "}";
            System.out.println("Calling pickrelease execute method");
            String jsonArrayAsString = rcu.invokeUPDATE(restURI, payload);
            System.out.println("Received response");
            if (jsonArrayAsString != null) {
                try {
                    JSONParser parser = new JSONParser();
                    Object object;

                    object = parser.parse(jsonArrayAsString);

                    JSONObject jsonObject = (JSONObject) object;
                    JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                    String status=jsObject.get("XSTATUS").toString();
                    String message=jsObject.get("XMSG").toString();

                    
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.pickRelStatus}", status);
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.pickRelMsg}", message);
                   
                    if(status.equalsIgnoreCase("S")){
                        AdfmfJavaUtilities.setELValue("#{pageFlowScope.pickRule}", "10");
                        AdfmfJavaUtilities.setELValue("#{pageFlowScope.documnetNumber}", null);
                        AdfmfJavaUtilities.setELValue("#{pageFlowScope.Event}", Boolean.TRUE);
                    }

                    
                } catch (ParseException e) {
                    e.getMessage();
                }
                }
    }

    public void clearPickRel(ActionEvent actionEvent) {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.pickRule}", "10");
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.documnetNumber}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.Event}", Boolean.TRUE);
    }


    public void clearLPNShip(ActionEvent actionEvent) {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchLpnKeyword}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.dockDoor}", null);
    }

    public String LPNShipAction() {
        System.out.println("in LPN Ship Action Event");
        Utility.ApplicationLogger.info("in LPN Ship Action Event");
        
        //calling WS for Missing LPNs check
        
        String restURI1 =  RestURI.PostLpn();
        String orgCode = (String)AdfmfJavaUtilities.evaluateELExpression("#{preferenceScope.feature.dcom.shop.MyWarehouse.OrgCodePG.OrgCode}");

        String dockDoor = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.dockDoor}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.dockDoor}"));
        String lpn = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.searchLpnKeyword}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.searchLpnKeyword}"));

        RestCallerUtil rcu = new RestCallerUtil();
        String payload =
            "{\n" + 
            "\"PickRelease_Input\":\n" + 
            "{\n" + 
            "\"@xmlns\": \"http://xmlns.oracle.com/apps/per/rest/dcomship/pickrelease/\",\n" + 
            "   \"RESTHeader\": {\"@xmlns\": \"http://xmlns.oracle.com/apps/per/rest/dcomship/header\",\n" + 
            "                  \"Responsibility\": \"DCOM_MOBILE_USER\",\n" + 
            "                  \"RespApplication\": \"INV\",\n" + 
            "                  \"SecurityGroup\": \"STANDARD\",\n" + 
            "                  \"NLSLanguage\": \"AMERICAN\",\n" + 
            "                  \"Org_Id\": \"82\"\n" + 
            "                 },\n" + 
        "\"InputParameters\" : { \"PLPNTYPE\" : \"MISSING_LPN\" , \"PORGCODE\" : \""+orgCode+"\" \n , \"PDOCKDOOR\" : \""+dockDoor+"\" \n, \"PLPNFROM\" : \"\" \n, \"PLPNTO\" : \"\" \n, \"PSUBINV\" : \"\" \n, \"PLOCATOR\" :\"\" \n } } }";
        System.out.println("Calling missing lpn method");
        String jsonArrayAsString = rcu.invokeUPDATE(restURI1, payload);
        System.out.println("Received response");
        if (jsonArrayAsString != null) {
            try {
                JSONParser parser = new JSONParser();
                Object object;

                object = parser.parse(jsonArrayAsString);

                JSONObject jsonObject = (JSONObject) object;
                JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                
               JSONArray jsarray = (JSONArray) jsObject.get("XLPN");
                
                if(jsarray!=null){
                    try{
                        int arraySize = jsarray.size();
                        for(int i=0; i<arraySize; i++){
                            
                            JSONObject jsi = (JSONObject) jsarray.get(i);
                            if(jsi!=null){
                            
                            return "missing";
                            // navigate to Missing LPNs page
                            }
                        }
                    }catch(Exception e1){
                        e1.getMessage();
                    }
                    }} catch (ParseException e) {
                e.getMessage();
            }
        }
        
        
        
        //
        
        String restURI =  RestURI.PostLpnShip();
        //String orgCode = (String)AdfmfJavaUtilities.evaluateELExpression("#{preferenceScope.feature.dcom.shop.MyWarehouse.OrgCodePG.OrgCode}");

        //String dockDoor = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.dockDoor}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.dockDoor}"));
        //String lpn = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.searchLpnKeyword}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.searchLpnKeyword}"));
        

       // RestCallerUtil rcu = new RestCallerUtil();
        //System.out.println("values to method are 1 "+orgCode);
        String payload1 =
            "{\n" + 
            "\"PickRelease_Input\":\n" + 
            "{\n" + 
            "\"@xmlns\": \"http://xmlns.oracle.com/apps/per/rest/dcomship/pickrelease/\",\n" + 
            "   \"RESTHeader\": {\"@xmlns\": \"http://xmlns.oracle.com/apps/per/rest/dcomship/header\",\n" + 
            "                  \"Responsibility\": \"DCOM_MOBILE_USER\",\n" + 
            "                  \"RespApplication\": \"INV\",\n" + 
            "                  \"SecurityGroup\": \"STANDARD\",\n" + 
            "                  \"NLSLanguage\": \"AMERICAN\",\n" + 
            "                  \"Org_Id\": \"82\"\n" + 
            "                 },\n" + 
        "\"InputParameters\" : { \"PORGCODE\" : \""+orgCode+"\", \n"+"\"PDOCKLPN\" : { \n \"PDOCKLPN_ITEM\" : { \n \"DOCKDOOR\" : \""+dockDoor+"\", \n"+"\"LPN\": \""+lpn+"\" } } } } }";
        System.out.println("Calling lpn ship method");
        String jsonArrayAsString1 = rcu.invokeUPDATE(restURI, payload1);
        System.out.println("Received response");
        if (jsonArrayAsString1 != null) {
            try {
                JSONParser parser1 = new JSONParser();
                Object object1;

                object1 = parser1.parse(jsonArrayAsString1);

                JSONObject jsonObject1 = (JSONObject) object1;
                JSONObject jsObject1 = (JSONObject) jsonObject1.get("OutputParameters");
                String status1=jsObject1.get("XSTATUS").toString();
                String message1=jsObject1.get("XMSG").toString();
                
                
                AdfmfJavaUtilities.setELValue("#{pageFlowScope.lpnShipStatus}", status1);
                AdfmfJavaUtilities.setELValue("#{pageFlowScope.lpnShipMsg}", message1);
               
                if(status1.equalsIgnoreCase("S")){
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchLpnKeyword}", null);
                    
                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(), "showAlert", new Object[] {
                                                                              "Ship Successful", message1, "ok"
                    });
                }else{
                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(), "showAlert", new Object[] {
                                                                              "Error", message1, "ok"
                    });
                }

                
            } catch (ParseException e) {
                e.getMessage();
            }
            }
        return "";
    }

    public void LPNLoadAction(ActionEvent actionEvent) {
        System.out.println("in LPN Load Action Event");
        Utility.ApplicationLogger.info("in LPN Load Action Event");
        String restURI =  RestURI.PostLpnLoad();
        String orgCode = (String)AdfmfJavaUtilities.evaluateELExpression("#{preferenceScope.feature.dcom.shop.MyWarehouse.OrgCodePG.OrgCode}");

        String dockDoor = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.dockDoor}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.dockDoor}"));
        String lpn = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.searchLpnKeyword}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.searchLpnKeyword}"));
        

        RestCallerUtil rcu = new RestCallerUtil();
        System.out.println("values to method are 1 "+orgCode);
        String payload =
            "{\n" + 
            "\"PickRelease_Input\":\n" + 
            "{\n" + 
            "\"@xmlns\": \"http://xmlns.oracle.com/apps/per/rest/dcomship/pickrelease/\",\n" + 
            "   \"RESTHeader\": {\"@xmlns\": \"http://xmlns.oracle.com/apps/per/rest/dcomship/header\",\n" + 
            "                  \"Responsibility\": \"DCOM_MOBILE_USER\",\n" + 
            "                  \"RespApplication\": \"INV\",\n" + 
            "                  \"SecurityGroup\": \"STANDARD\",\n" + 
            "                  \"NLSLanguage\": \"AMERICAN\",\n" + 
            "                  \"Org_Id\": \"82\"\n" + 
            "                 },\n" + 
        "\"InputParameters\" : { \"PORGCODE\" : \""+orgCode+"\", \n"+"\"PDOCKLPN\" : { \n \"PDOCKLPN_ITEM\" : { \n \"DOCKDOOR\" : \""+dockDoor+"\", \n"+"\"LPN\": \""+lpn+"\" } } } } }";
        System.out.println("Calling lpn load method");
        String jsonArrayAsString = rcu.invokeUPDATE(restURI, payload);
        System.out.println("Received response");
        if (jsonArrayAsString != null) {
            try {
                JSONParser parser = new JSONParser();
                Object object;

                object = parser.parse(jsonArrayAsString);

                JSONObject jsonObject = (JSONObject) object;
                JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                String status=jsObject.get("XSTATUS").toString();
                String message=jsObject.get("XMSG").toString();
                
                
                AdfmfJavaUtilities.setELValue("#{pageFlowScope.lpnLoadStatus}", status);
                AdfmfJavaUtilities.setELValue("#{pageFlowScope.lpnLoadMsg}", message);
               
                if(status.equalsIgnoreCase("S")){
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchLpnKeyword}", null);
                                   
                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(), "showAlert", new Object[] {
                                                                              "Load Successful", message, "ok"
                    });
                }else{
                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(), "showAlert", new Object[] {
                                                                              "Error", message, "ok"
                    });
                }

                
            } catch (ParseException e) {
                e.getMessage();
            }
            }
    }

    public void UnloadLPNAction(ActionEvent actionEvent) {
        System.out.println("in LPN Unload Action Event");
        Utility.ApplicationLogger.info("in LPN Unload Action Event");
        String restURI =  RestURI.PostLpnUnLoad();
        String orgCode = (String)AdfmfJavaUtilities.evaluateELExpression("#{preferenceScope.feature.dcom.shop.MyWarehouse.OrgCodePG.OrgCode}");

        String dockDoor = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.dockDoor}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.dockDoor}"));
        String lpn = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.searchLpnKeyword}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.searchLpnKeyword}"));
        

        RestCallerUtil rcu = new RestCallerUtil();
        System.out.println("values to method are 1 "+orgCode);
        String payload =
            "{\n" + 
            "\"PickRelease_Input\":\n" + 
            "{\n" + 
            "\"@xmlns\": \"http://xmlns.oracle.com/apps/per/rest/dcomship/pickrelease/\",\n" + 
            "   \"RESTHeader\": {\"@xmlns\": \"http://xmlns.oracle.com/apps/per/rest/dcomship/header\",\n" + 
            "                  \"Responsibility\": \"DCOM_MOBILE_USER\",\n" + 
            "                  \"RespApplication\": \"INV\",\n" + 
            "                  \"SecurityGroup\": \"STANDARD\",\n" + 
            "                  \"NLSLanguage\": \"AMERICAN\",\n" + 
            "                  \"Org_Id\": \"82\"\n" + 
            "                 },\n" + 
        "\"InputParameters\" : { \"PUNTRUCKREC\" : { \"ORGCODE\" : \""+orgCode+"\", \n \"DOCKDOOR\" : \""+dockDoor+"\", \n \"LPN\" : \""+lpn+"\", \n \"SUBINV\" : \"\", \"LOCATOR\" : \"\", \"ATTRIB\" : \"\", } } } }";
        System.out.println("Calling lpn unload method");
        String jsonArrayAsString = rcu.invokeUPDATE(restURI, payload);
        System.out.println("Received response");
        if (jsonArrayAsString != null) {
            try {
                JSONParser parser = new JSONParser();
                Object object;

                object = parser.parse(jsonArrayAsString);

                JSONObject jsonObject = (JSONObject) object;
                JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                String status=jsObject.get("XSTATUS").toString();
                String message=jsObject.get("XMSG").toString();
                
                
                AdfmfJavaUtilities.setELValue("#{pageFlowScope.lpnUnloadStatus}", status);
                AdfmfJavaUtilities.setELValue("#{pageFlowScope.lpnUnloadMsg}", message);
               
                if(status.equalsIgnoreCase("S")){
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchLpnKeyword}", null);
                                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(), "showAlert", new Object[] {
                                                                              "Ship Successful", message, "ok"
                    });
                }else{
                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(), "showAlert", new Object[] {
                                                                              "Error", message, "ok"
                    });
                }

                
            } catch (ParseException e) {
                e.getMessage();
            }
            }
    }
    
    
}
               