package dcom.shop.Transaction.page.ShippingTxn;

import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.util.Map;

import java.util.logging.Level;

import javax.el.ValueExpression;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.exception.AdfException;
import oracle.adfmf.util.Utility;

import oracle.adfmf.util.logging.Trace;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class QuickShipUtilBean {
    public QuickShipUtilBean() {
    }


    public String callBackOrder() {
     //   AdfmfJavaUtilities.setELValue("{pageFlowScope.txnStatus}", "");
        Map pageFlow = (Map) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope}");
        pageFlow.put("event", "BACKORDER");
        return callRestService();
    }
    
    public String callRestService(){       
        ValueExpression ve = null;
        String response = null;
        System.out.println("Inside productItem");
        Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
        /* OrgItemEntity orgObj = new OrgItemEntity();
          orgObj.setOrgItem("STR");
          s_OrgList.add(orgObj);
          orgObj.setOrgItem("REN");
          s_OrgList.add(orgObj);*/
        String dlvNum = null;
        String shipMthd = null;
        String wayBill = null;
        String vehicleNum = null;
        String event = null;
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.dlvNumber}", String.class);
        dlvNum = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.shipMthCode}", String.class);
        shipMthd = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        if(shipMthd.equals("{\"@xsi:nil\":\"true\"}")){
            shipMthd = "";
        }       
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.waybill}", String.class);
        wayBill = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        
        if(wayBill.equals("{\"@xsi:nil\":\"true\"}")){
            wayBill = "";
        }       
        
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.vehicleNumber}", String.class);
        vehicleNum = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.event}", String.class);
        event = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        
        String restURI = RestURI.PostQuickShipURI();
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
        "      {\"PSHIPREC\": {\"DELIVERY\": \""+dlvNum+"\",\"SHIPMETHOD\": \""+shipMthd+"\",\"WAYBILL\": \""+wayBill+"\",\"VEHICLENUM\": \""+vehicleNum+"\",\"EVENT\": \""+event+"\"}}\n" + 
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
                if ("S".contains(status)) {
                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                              "showAlert", new Object[] {
                                                                              "Success",
                                                                              "Transaction has been submitted successfully.",
                                                                              "ok"
                    });
                    response =  "quickShipBack";
                } else {
                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                              "showAlert", new Object[] {
                                                                              "Error",
                                                                              "Transaction submission failed. \n" +
                                                                              jsObject.get("XMSG").toString(), "ok"
                    });
                    response =  "";
                }
              /*  if(status.equals("F")){
                    Map pageFlow = (Map) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope}");
                    pageFlow.put("txnStatus", "");

                    throw new AdfException(msg,AdfException.ERROR);
                }
                else {
                    Map pageFlow = (Map) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope}");
                    pageFlow.put("txnStatus", "backToTxn");

                    throw new AdfException(event+" sucessfully done.!!",AdfException.INFO);
                }*/
            }
        /*    else {
                Map pageFlow = (Map) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope}");
                pageFlow.put("txnStatus", "backToTxn");
                throw new AdfException(event+" sucessfully done.!!",AdfException.INFO);
            }*/
        }catch(Exception e){
                Trace.log("REST_JSON",Level.SEVERE, this.getClass(),"ProductDetailsEntity", e.getLocalizedMessage());
            }
        
        return response;
        
    }

    public String callShip() {
       // AdfmfJavaUtilities.setELValue("{pageFlowScope.txnStatus}", "");
        Map pageFlow = (Map) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope}");
        pageFlow.put("event", "SHIP");
       return callRestService();
    }

}
