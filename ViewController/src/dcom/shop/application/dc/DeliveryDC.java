package dcom.shop.application.dc;

import com.sun.mail.imap.Utility;


import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;

import javax.el.ValueExpression;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.logging.Trace;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class DeliveryDC {
    public DeliveryDC() {
        super();
    }
    List s_DeliveryDetails = new ArrayList();
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);

     private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    
    public DeliveryLovEntity[] getDeliveryDetails(){
        
        ValueExpression ve = null;
        System.out.println("Inside delivery DC");
        oracle.adfmf.util.Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
        String dlvName = null;
        String orgCode = null;
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.delvName}", String.class);
        dlvName = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        
  
        String restURI = RestURI.PostDeliveryInquiryURI();
        RestCallerUtil rcu = new RestCallerUtil();
        String payload = "{\"x\":\n" + 
        "{\n" + 
        "   \"RESTHeader\": {\"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" + 
        "                  \"RespApplication\": \"ONT\",\n" + 
        "                  \"SecurityGroup\": \"STANDARD\",\n" + 
        "                  \"NLSLanguage\": \"AMERICAN\",\n" + 
        "                  \"Org_Id\": \"82\"\n" + 
        "                 },\n" + 
        "   \"InputParameters\": \n" + 
        "     {\"PDLVTYPE\": \"QUICK_SHIP\",\"PORGCODE\": \"100\",\"PDELIVERYNAME\": \"%"+dlvName+"\"}\n" + 
        "}\n" + 
        "}";
        System.out.println("Calling create method");
        String jsonArrayAsString = (rcu.invokeUPDATE(restURI, payload)).toString();
        System.out.println("Received response");
        DeliveryLovEntity[] delvArray = null;
        //ashish
        try {
            JSONParser parser = new JSONParser();
            Object object = parser.parse(jsonArrayAsString);
            JSONObject jsonObject = (JSONObject) object;
            JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
            JSONObject jsObject1 = (JSONObject) jsObject.get("XDELIVERY");
            try {
                JSONArray array = (JSONArray) jsObject1.get("XDELIVERY_ITEM");

                if (array != null) {
                    int size = array.size();
                    //  ProductSearchEntity[] prodItems= new ProductSearchEntity[size];
                    for (int i = 0; i < size; i++) {


                        DeliveryLovEntity delvItems = new DeliveryLovEntity();
                      //  JSONObject jsObjectArrayData = (JSONObject) jsObject1.get("XLPNRES_ITEM");
                      JSONObject jsObjectArrayData = (JSONObject) array.get(i); 

                        delvItems.setDLVNAME((jsObjectArrayData.get("DLVNAME").toString()));
                        delvItems.setDLVID(Integer.parseInt((jsObjectArrayData.get("DLVID").toString())));
                        try{
                        delvItems.setGROSSWEIGHT(Integer.parseInt((jsObjectArrayData.get("GROSSWEIGHT").toString())));
                        }catch(Exception e1){
                            delvItems.setGROSSWEIGHT(0);
                        }
                        if(((jsObjectArrayData.get("SHIPMETHODCODE").toString())).equals("{@xsi:nil: \"true\"}")) {
                            delvItems.setSHIPMETHODCODE("");
                        }
                        else {
                            delvItems.setSHIPMETHODCODE((jsObjectArrayData.get("SHIPMETHODCODE").toString()));
                        }
                        if(((jsObjectArrayData.get("WAYBILL").toString())).equals("{@xsi:nil: \"true\"}")) {
                            delvItems.setWAYBILL("");
                        }
                        else {
                            delvItems.setWAYBILL((jsObjectArrayData.get("WAYBILL").toString()));
                        }
                        if(((jsObjectArrayData.get("WEIGHTUOM").toString())).equals("{@xsi:nil: \"true\"}")) {
                            delvItems.setWEIGHTUOM("");
                        }
                        else {
                            delvItems.setWEIGHTUOM((jsObjectArrayData.get("WEIGHTUOM").toString()));
                        }
                        
                       
                        s_DeliveryDetails.add(delvItems);
                    }


                }
            } catch (ClassCastException e2) {
                JSONObject jsObject2 = (JSONObject) jsObject1.get("XDELIVERY_ITEM");
                if (jsObject2 != null) {


                    DeliveryLovEntity delvItems = new DeliveryLovEntity();
                    
                    delvItems.setDLVNAME((jsObject2.get("DLVNAME").toString()));
                    delvItems.setDLVID(Integer.parseInt((jsObject2.get("DLVID").toString())));
                    try{
                    delvItems.setGROSSWEIGHT(Integer.parseInt((jsObject2.get("GROSSWEIGHT").toString())));
                    }catch(Exception e1){
                        delvItems.setGROSSWEIGHT(0);
                    }
                    if(((jsObject2.get("SHIPMETHODCODE").toString())).equals("{@xsi:nil: \"true\"}")) {
                        delvItems.setSHIPMETHODCODE("");
                    }
                    else {
                        delvItems.setSHIPMETHODCODE((jsObject2.get("SHIPMETHODCODE").toString()));
                    }
                    if(((jsObject2.get("WAYBILL").toString())).equals("{@xsi:nil: \"true\"}")) {
                        delvItems.setWAYBILL("");
                    }
                    else {
                        delvItems.setWAYBILL((jsObject2.get("WAYBILL").toString()));
                    }
                    if(((jsObject2.get("WEIGHTUOM").toString())).equals("{@xsi:nil: \"true\"}")) {
                        delvItems.setWEIGHTUOM("");
                    }
                    else {
                        delvItems.setWEIGHTUOM((jsObject2.get("WEIGHTUOM").toString()));
                    }
                    
                    s_DeliveryDetails.add(delvItems);


                }
            }

            delvArray = (DeliveryLovEntity[]) s_DeliveryDetails.toArray(new DeliveryLovEntity[s_DeliveryDetails.size()]);
            if(s_DeliveryDetails.size()!=0){
                AdfmfJavaUtilities.setELValue("{pageFlowScope.DeliveryServiceResults}", "");
            }else{
                AdfmfJavaUtilities.setELValue("{pageFlowScope.DeliveryServiceResults}", "No Search Results");
            }

        } catch (Exception e) { 
            Trace.log("REST_JSON", Level.SEVERE, this.getClass(), "DeliveryLovEntity", e.getLocalizedMessage());
        }
        return delvArray;
        
    
    }   
    public void refresh() {
        s_DeliveryDetails.clear();
     //   AdfmfJavaUtilities.setELValue("{pageFlowScope.DeliveryServiceResults}", "N");
        providerChangeSupport.fireProviderRefresh("deliveryDetails");
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
}
