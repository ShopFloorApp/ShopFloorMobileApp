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

public class OnHandSearchDC {
    public OnHandSearchDC() {
        super();
    }
    List s_onHandList = new ArrayList();
    public OnHandSearchEntity[] getAllOnhands() {
        s_onHandList.clear();
        ValueExpression ve = null;


        System.out.println("Inside onhand search");
        Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
        String item = null;
        String subinv = null;
        String locator = null;
        String costGroup = null;
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchKeyword}", String.class);
        item = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.FromSubinventory}", String.class);
        subinv = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.FromLocator}", String.class);
        locator = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.costGrp}", String.class);
        costGroup = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        
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
        "     {\"PONHANDREQ\": {\"ORGCODE\": \"100\",\"ITEM\": \""+item+"\",\"SUBINV\": \""+subinv+"\",\"LOCATOR\": \""+locator+"\",\"COSTGROUP\": \"\"}}\n" + 
        "}\n" + 
        "}";
           
        System.out.println("Calling create method");
        String jsonArrayAsString = (rcu.invokeUPDATE(restURI, payload)).toString();
        System.out.println("Received response");
        OnHandSearchEntity[] onHandArray = null;
        //ashish
        try {
            JSONParser parser = new JSONParser();
            Object object = parser.parse(jsonArrayAsString);
            JSONObject jsonObject = (JSONObject) object;
            JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
            JSONObject jsObject1 = (JSONObject) jsObject.get("XONHANDRES");
            try {
                JSONArray array = (JSONArray) jsObject1.get("XONHANDRES_ITEM");

                if (array != null) {
                    int size = array.size();
                    //  ProductSearchEntity[] prodItems= new ProductSearchEntity[size];
                    for (int i = 0; i < size; i++) {


                        OnHandSearchEntity onhandItems = new OnHandSearchEntity();
                      //  JSONObject jsObjectArrayData = (JSONObject) jsObject1.get("XLPNRES_ITEM");
                      JSONObject jsObjectArrayData = (JSONObject) array.get(i); 

                        onhandItems.setITEM((jsObjectArrayData.get("ITEM").toString()));
                        onhandItems.setITEMDESC((jsObjectArrayData.get("ITEMDESC").toString()));
                        onhandItems.setSUBINV((jsObjectArrayData.get("SUBINV").toString()));
                        onhandItems.setLOCATOR((jsObjectArrayData.get("LOCATOR").toString()));
                        onhandItems.setONHANDQTY(Integer.parseInt((jsObjectArrayData.get("ONHANDQTY").toString()))); 
                        onhandItems.setAVAILABLEQTY(Integer.parseInt((jsObjectArrayData.get("AVAILABLEQTY").toString()))); 
                     //   JSONObject onHandOptObj = (JSONObject) jsObject1.get("XONHANDRES_ITEM");
                    //    JSONObject costGrpObj = (JSONObject) onHandOptObj.get("XONHANDRES_ITEM");
                     //   onhandItems.setCOSTGROUP((costGrpObj.get("COSTGROUP").toString()));

                      
                        s_onHandList.add(onhandItems);
                    }


                }
            } catch (ClassCastException e2) {
                JSONObject jsObject2 = (JSONObject) jsObject1.get("XONHANDRES_ITEM");
                if (jsObject2 != null) {


                    OnHandSearchEntity onhandItems = new OnHandSearchEntity();
                    // JSONObject jsObject2 = (JSONObject)
                    
                    onhandItems.setITEM((jsObject2.get("ITEM").toString()));
                    onhandItems.setITEMDESC((jsObject2.get("ITEMDESC").toString()));
                    onhandItems.setSUBINV((jsObject2.get("SUBINV").toString()));
                    onhandItems.setLOCATOR((jsObject2.get("LOCATOR").toString()));
                    onhandItems.setONHANDQTY(Integer.parseInt((jsObject2.get("ONHANDQTY").toString()))); 
                    onhandItems.setAVAILABLEQTY(Integer.parseInt((jsObject2.get("AVAILABLEQTY").toString())));                    

                    s_onHandList.add(onhandItems);


                }
            }

            onHandArray = (OnHandSearchEntity[]) s_onHandList.toArray(new OnHandSearchEntity[s_onHandList.size()]);

        } catch (Exception e) {
            Trace.log("REST_JSON", Level.SEVERE, this.getClass(), "OnHandSearchEntity", e.getLocalizedMessage());
        }
        return onHandArray;
    }
}
