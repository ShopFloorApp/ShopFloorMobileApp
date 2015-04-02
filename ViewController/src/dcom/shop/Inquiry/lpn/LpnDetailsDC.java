package dcom.shop.Inquiry.lpn;

import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;

import javax.el.ValueExpression;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.Utility;
import oracle.adfmf.util.logging.Trace;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class LpnDetailsDC {
    public LpnDetailsDC() {
        super();
    }
    List s_LpnDetailList = new ArrayList();
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    
    public LpnDetailsEntity[] getAllLpnDetails(){
        ValueExpression ve = null;
        s_LpnDetailList.clear();
        System.out.println("Inside lpn details");
        Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
        String keyword = null;
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.lpn}", String.class);
        keyword = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        String restURI = RestURI.PostLpnDetailsURI();
        RestCallerUtil rcu = new RestCallerUtil();
        String payload =
            "{\"x\": {\"RESTHeader\": {\"@xmlns\": \"http://xmlns.oracle.com/apps/inv/rest/DCOMInquiry/header\",\"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\"RespApplication\": \"ONT\",\"SecurityGroup\": \"STANDARD\",\n" +
            "\"NLSLanguage\": \"AMERICAN\",\"Org_Id\": \"82\"},\"InputParameters\": {\"PLPNREQ\": {\"ORGCODE\": \"100\",\"LPN\": \"%" +
            keyword + "%\"}}\n" + "}\n" + "}";
        System.out.println("Calling create method");
        String jsonArrayAsString = (rcu.invokeUPDATE(restURI, payload)).toString();
        System.out.println("Received response");
        LpnDetailsEntity[] lpnDetailsArray = null;
        //ashish
        try {
            JSONParser parser = new JSONParser();
            Object object = parser.parse(jsonArrayAsString);
            JSONObject jsonObject = (JSONObject) object;
            JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
            JSONObject jsObject1 = (JSONObject) jsObject.get("XLPNRES");
            JSONObject jsObject2 = (JSONObject) jsObject1.get("XLPNRES_ITEM");
            JSONObject jsObject3 = (JSONObject) jsObject2.get("LPNCONTENTS");
            try {
                JSONArray array = (JSONArray) jsObject3.get("LPNCONTENTS_ITEM");

                if (array != null) {
                    int size = array.size();
                    //  ProductSearchEntity[] prodItems= new ProductSearchEntity[size];
                    for (int i = 0; i < size; i++) {


                        LpnDetailsEntity lpnDetails = new LpnDetailsEntity();
                      //  JSONObject jsObjectArrayData = (JSONObject) jsObject1.get("XLPNRES_ITEM");
                      JSONObject jsObjectArrayData = (JSONObject) array.get(i); 
                        
                        lpnDetails.setITEM((jsObjectArrayData.get("ITEM").toString()));
                        lpnDetails.setITEMDESC((jsObjectArrayData.get("ITEMDESC").toString()));
                        lpnDetails.setUOM((jsObjectArrayData.get("UOM").toString()));                    
                        lpnDetails.setONHANDQTY(Integer.parseInt((jsObjectArrayData.get("ONHANDQTY").toString()))); 
                        lpnDetails.setAVAILABLEQTY(Integer.parseInt((jsObjectArrayData.get("AVAILABLEQTY").toString())));

                         
                        s_LpnDetailList.add(lpnDetails);
                    }


                }
            } catch (ClassCastException e2) {
                JSONObject jsObject4 = (JSONObject) jsObject3.get("LPNCONTENTS_ITEM");
                if (jsObject4 != null) {


                    LpnDetailsEntity lpnDetails = new LpnDetailsEntity();
                    // JSONObject jsObject2 = (JSONObject)

                    lpnDetails.setITEM((jsObject4.get("ITEM").toString()));
                    lpnDetails.setITEMDESC((jsObject4.get("ITEMDESC").toString()));
                    lpnDetails.setUOM((jsObject4.get("UOM").toString()));
                    lpnDetails.setONHANDQTY(Integer.parseInt((jsObject4.get("ONHANDQTY").toString())));
                    lpnDetails.setAVAILABLEQTY(Integer.parseInt((jsObject4.get("AVAILABLEQTY").toString())));
                    
                    s_LpnDetailList.add(lpnDetails);

                }
            }

            lpnDetailsArray = (LpnDetailsEntity[]) s_LpnDetailList.toArray(new LpnDetailsEntity[s_LpnDetailList.size()]);

        } catch (Exception e) {
            Trace.log("REST_JSON", Level.SEVERE, this.getClass(), "LpnDetailsEntity", e.getLocalizedMessage());
        }
        return lpnDetailsArray;
    }
    
    public void refreshLpnItems() {
        providerChangeSupport.fireProviderRefresh("allLpnDetails");
    }
    
    public void addProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.addProviderChangeListener(l);
    }

    public void removeProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.removeProviderChangeListener(l);
    }
}
