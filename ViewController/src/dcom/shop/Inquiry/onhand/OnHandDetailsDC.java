package dcom.shop.Inquiry.onhand;

import dcom.shop.restURIDetails.RestURI;

import dcom.shop.restURIDetails.RestCallerUtil;

import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;

import javax.el.ValueExpression;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.JSONBeanSerializationHelper;

import oracle.adfmf.util.Utility;
import oracle.adfmf.util.logging.Trace;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class OnHandDetailsDC {
    public OnHandDetailsDC() {
        super();
    }
    List s_onHandDetails = new ArrayList();
    public OnHandDetailsEntity[] getAllOnhandDetails() {
        ValueExpression ve = null;


        System.out.println("Inside onhand details");
        Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
        String item = null;
        String subInv = null;
        String locator = null;
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.itemNumber}", String.class);
        item = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.subinv}", String.class);
        subInv = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.loc}", String.class);
        locator = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        
        
        String restURI = RestURI.PostOnHandInquiryURI();
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
        "     {\"PONHANDREQ\": {\"ORGCODE\": \"100\",\"ITEM\": \""+item+"\",\"SUBINV\": \""+subInv+"\",\"LOCATOR\": \""+locator+"\",\"COSTGROUP\": \"\"}}\n" + 
        "}\n" + 
        "}";
           
        System.out.println("Calling create method");
        String jsonArrayAsString = (rcu.invokeUPDATE(restURI, payload)).toString();
        System.out.println("Received response");
        OnHandDetailsEntity[] onHandArray = null;
        //ashish
        try {
            JSONParser parser = new JSONParser();
            Object object = parser.parse(jsonArrayAsString);
            JSONObject jsonObject = (JSONObject) object;
            JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
            JSONObject jsObject1 = (JSONObject) jsObject.get("XONHANDRES");
            
                JSONObject jsObject2 = (JSONObject) jsObject1.get("XONHANDRES_ITEM");
                if (jsObject2 != null) {


                    OnHandDetailsEntity onhandItems = new OnHandDetailsEntity();
                    // JSONObject jsObject2 = (JSONObject)
                    
                    onhandItems.setITEM((jsObject2.get("ITEM").toString()));
                    onhandItems.setITEMDESC((jsObject2.get("ITEMDESC").toString()));
                    onhandItems.setSUBINV((jsObject2.get("SUBINV").toString()));
                    onhandItems.setLOCATOR((jsObject2.get("LOCATOR").toString()));
                    onhandItems.setUOM((jsObject2.get("UOM").toString()));
                    onhandItems.setPACKEDQTY(Integer.parseInt((jsObject2.get("PACKEDQTY").toString()))); 
                    onhandItems.setLOOSEQTY(Integer.parseInt((jsObject2.get("LOOSEQTY").toString()))); 
                    onhandItems.setRECEIVINGQTY(Integer.parseInt((jsObject2.get("RECEIVINGQTY").toString()))); 
                    onhandItems.setINBOUNDQTY(Integer.parseInt((jsObject2.get("INBOUNDQTY").toString()))); 
                    onhandItems.setONHANDQTY(Integer.parseInt((jsObject2.get("ONHANDQTY").toString()))); 
                    onhandItems.setAVAILABLEQTY(Integer.parseInt((jsObject2.get("AVAILABLEQTY").toString())));                    

                    s_onHandDetails.add(onhandItems);


                            }

            onHandArray = (OnHandDetailsEntity[]) s_onHandDetails.toArray(new OnHandDetailsEntity[s_onHandDetails.size()]);

        } catch (Exception e) {
            Trace.log("REST_JSON", Level.SEVERE, this.getClass(), "OnHandSearchEntity", e.getLocalizedMessage());
        }
        return onHandArray;
    }
}
