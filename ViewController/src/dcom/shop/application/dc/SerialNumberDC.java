package dcom.shop.application.dc;

import dcom.shop.application.mobile.SerialNumberBO;
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

public class SerialNumberDC {
    public SerialNumberDC() {
        super();
    }
    
    List s_list = new ArrayList();
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public SerialNumberBO[] getSerialNumbers() {
        ValueExpression ve = null;
        s_list.clear();

        System.out.println("Inside Serial Number DC");
        String keyword = null;
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.serialNo}", String.class);
        keyword = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        String restURI = RestURI.PostDockDoorDetailsURI();
        RestCallerUtil rcu = new RestCallerUtil();
        String type="SHIP_SERIAL";
        String payload =
            "{\"x\": {\"RESTHeader\": {\"@xmlns\": \"http://xmlns.oracle.com/apps/inv/rest/DCOMLOV/header\",\"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\"RespApplication\": \"ONT\",\"SecurityGroup\": \"STANDARD\",\n" +
            "\"NLSLanguage\": \"AMERICAN\",\"Org_Id\": \"82\"},\"InputParameters\": {\"PSERIALTYPE\": \""+type+"\",\"PWAREHOUSE\": \"%\",\"PITEM\": \"%\",\"PSERIAL\": \"%"+keyword+"%\"}\n" + "}\n" + "}";
        System.out.println("Calling create method");
        String jsonArrayAsString = (rcu.invokeUPDATE(restURI, payload)).toString();
        System.out.println("Received response");
        SerialNumberBO[] arr = null;
        
        try {
            JSONParser parser = new JSONParser();
            Object object = parser.parse(jsonArrayAsString);
            JSONObject jsonObject = (JSONObject) object;
            JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
            JSONObject jsObject1 = (JSONObject) jsObject.get("XSERIAL");
            try {
                JSONArray array = (JSONArray) jsObject1.get("XXDCOM_SERIALS_TAB");

                if (array != null) {
                    int size = array.size();
                    //  ProductSearchEntity[] prodItems= new ProductSearchEntity[size];
                    for (int i = 0; i < size; i++) {


                        SerialNumberBO items = new SerialNumberBO();
                        //  JSONObject jsObjectArrayData = (JSONObject) jsObject1.get("XLPNRES_ITEM");
                        JSONObject jsObjectArrayData = (JSONObject) array.get(i);

                        items.setSERIALNUMBER((jsObjectArrayData.get("SERIALNUMBER").toString()));
                        items.setSERIALSTATUS((jsObjectArrayData.get("SERIALSTATUS").toString()));
                        
                        s_list.add(items);
                    }


                }
            } catch (ClassCastException e2) {
                JSONObject jsObject2 = (JSONObject) jsObject1.get("XXDCOM_SERIALS_TAB");
                if (jsObject2 != null) {


                    SerialNumberBO items = new SerialNumberBO();
                    // JSONObject jsObject2 = (JSONObject)

                    items.setSERIALNUMBER((jsObject2.get("SERIALNUMBER").toString()));
                    items.setSERIALSTATUS((jsObject2.get("SERIALSTATUS").toString()));
                    
                    s_list.add(items);
                }
            }

            arr = (SerialNumberBO[]) s_list.toArray(new SerialNumberBO[s_list.size()]);
            if(s_list.size()!=0){
                AdfmfJavaUtilities.setELValue("{pageFlowScope.SerialResults}", "");
            }else{
                AdfmfJavaUtilities.setELValue("{pageFlowScope.SerialResults}", "No Search Results");
            }
        
        } catch (Exception e) {
            Trace.log("REST_JSON", Level.SEVERE, this.getClass(), "SerialNumberBO", e.getLocalizedMessage());
        }
        return arr;
    }

    public void refresh() {
        providerChangeSupport.fireProviderRefresh("serialNumbers");
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
