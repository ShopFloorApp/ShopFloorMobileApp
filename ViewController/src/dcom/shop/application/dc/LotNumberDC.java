package dcom.shop.application.dc;

import dcom.shop.application.mobile.LotNumberBO;
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

public class LotNumberDC {
    public LotNumberDC() {
        super();
    }
    
    List s_list = new ArrayList();
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public LotNumberBO[] getLotNumbers() {
        ValueExpression ve = null;
        s_list.clear();

        System.out.println("Inside Lot Number DC");
        String keyword = null;
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.lotNo}", String.class);
        keyword = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        String restURI = RestURI.PostDockDoorDetailsURI();
        RestCallerUtil rcu = new RestCallerUtil();
        String type="SHIP_LOT";
        String payload =
            "{\"x\": {\"RESTHeader\": {\"@xmlns\": \"http://xmlns.oracle.com/apps/inv/rest/DCOMLOV/header\",\"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\"RespApplication\": \"ONT\",\"SecurityGroup\": \"STANDARD\",\n" +
            "\"NLSLanguage\": \"AMERICAN\",\"Org_Id\": \"82\"},\"InputParameters\": {\"PLOTTYPE\": \""+type+"\",\"PWAREHOUSE\": \"%\",\"PITEM\": \"%\",\"PLOT\": \"%"+keyword+"%\"}\n" + "}\n" + "}";
        System.out.println("Calling create method");
        String jsonArrayAsString = (rcu.invokeUPDATE(restURI, payload)).toString();
        System.out.println("Received response");
        LotNumberBO[] arr = null;
        //ashish
        try {
            JSONParser parser = new JSONParser();
            Object object = parser.parse(jsonArrayAsString);
            JSONObject jsonObject = (JSONObject) object;
            JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
            JSONObject jsObject1 = (JSONObject) jsObject.get("XLOT");
            try {
                JSONArray array = (JSONArray) jsObject1.get("XXDCOM_LOTS_TAB");

                if (array != null) {
                    int size = array.size();
                    //  ProductSearchEntity[] prodItems= new ProductSearchEntity[size];
                    for (int i = 0; i < size; i++) {


                        LotNumberBO items = new LotNumberBO();
                        //  JSONObject jsObjectArrayData = (JSONObject) jsObject1.get("XLPNRES_ITEM");
                        JSONObject jsObjectArrayData = (JSONObject) array.get(i);

                        items.setWHSE((jsObjectArrayData.get("WHSE").toString()));
                        items.setITEM((jsObjectArrayData.get("ITEM").toString()));
                        items.setLOTNUM((jsObjectArrayData.get("LOTNUM").toString()));
                        items.setLOTSTATUS((jsObjectArrayData.get("LOTSTATUS").toString()));
                        items.setSUBINV((jsObjectArrayData.get("SUBINV").toString()));
                        items.setLOCATOR((jsObjectArrayData.get("LOCATOR").toString()));
                        items.setATTRIBUTES((jsObjectArrayData.get("ATTRIBUTES").toString()));
                        s_list.add(items);
                    }


                }
            } catch (ClassCastException e2) {
                JSONObject jsObject2 = (JSONObject) jsObject1.get("XXDCOM_LOTS_TAB");
                if (jsObject2 != null) {


                    LotNumberBO items = new LotNumberBO();
                    // JSONObject jsObject2 = (JSONObject)

                    items.setWHSE((jsObject2.get("WHSE").toString()));
                    items.setITEM((jsObject2.get("ITEM").toString()));
                    items.setLOTNUM((jsObject2.get("LOTNUM").toString()));
                    items.setLOTSTATUS((jsObject2.get("LOTSTATUS").toString()));
                    items.setSUBINV((jsObject2.get("SUBINV").toString()));
                    items.setLOCATOR((jsObject2.get("LOCATOR").toString()));
                    items.setATTRIBUTES((jsObject2.get("ATTRIBUTES").toString()));

                    s_list.add(items);
                }
            }

            arr = (LotNumberBO[]) s_list.toArray(new LotNumberBO[s_list.size()]);
            if(s_list.size()!=0){
                AdfmfJavaUtilities.setELValue("{pageFlowScope.LotResults}", "");
            }else{
                AdfmfJavaUtilities.setELValue("{pageFlowScope.LotResults}", "No Search Results");
            }
        
        } catch (Exception e) {
            Trace.log("REST_JSON", Level.SEVERE, this.getClass(), "LotNumberBO", e.getLocalizedMessage());
        }
        return arr;
    }

    public void refresh() {
        providerChangeSupport.fireProviderRefresh("lotNumbers");
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
