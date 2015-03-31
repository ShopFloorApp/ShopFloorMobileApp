package dcom.shop.application.dc;

import dcom.shop.application.mobile.DockDoorBO;
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

public class DockDoorDC {
    public DockDoorDC() {
        super();
    }
    
    List s_list = new ArrayList();
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public DockDoorBO[] getDockDoors() {
        ValueExpression ve = null;
        s_list.clear();

        System.out.println("Inside Dock Door");
        String keyword = null;
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.dockDoor}", String.class);
        keyword = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        String restURI = RestURI.PostDockDoorDetailsURI();
        RestCallerUtil rcu = new RestCallerUtil();
        String dockType="SHIP_DOCK";
        String payload =
            "{\"x\": {\"RESTHeader\": {\"@xmlns\": \"http://xmlns.oracle.com/apps/inv/rest/DCOMShip/header\",\"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\"RespApplication\": \"ONT\",\"SecurityGroup\": \"STANDARD\",\n" +
            "\"NLSLanguage\": \"AMERICAN\",\"Org_Id\": \"82\"},\"InputParameters\": {\"PDOCKTYPE\": \""+dockType+"\",\"PORGCODE\": \"100\",\"PDOCKNAME\": \"%"+keyword+"%\"}\n" + "}\n" + "}";
        System.out.println("Calling create method");
        String jsonArrayAsString = (rcu.invokeUPDATE(restURI, payload)).toString();
        System.out.println("Received response");
        DockDoorBO[] arr = null;
        //ashish
        try {
            JSONParser parser = new JSONParser();
            Object object = parser.parse(jsonArrayAsString);
            JSONObject jsonObject = (JSONObject) object;
            JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
            JSONObject jsObject1 = (JSONObject) jsObject.get("XDOCK");
            try {
                JSONArray array = (JSONArray) jsObject1.get("XXDCOM_DOCK_DOOR_TAB");

                if (array != null) {
                    int size = array.size();
                    //  ProductSearchEntity[] prodItems= new ProductSearchEntity[size];
                    for (int i = 0; i < size; i++) {


                        DockDoorBO items = new DockDoorBO();
                        //  JSONObject jsObjectArrayData = (JSONObject) jsObject1.get("XLPNRES_ITEM");
                        JSONObject jsObjectArrayData = (JSONObject) array.get(i);

                        items.setDOCKDOOR((jsObjectArrayData.get("DOCKDOOR").toString()));
                        items.setDOCKDOORID((jsObjectArrayData.get("DOCKDOORID").toString()));
                        items.setSUBINV((jsObjectArrayData.get("SUBINV").toString()));
                        items.setSTGLANE((jsObjectArrayData.get("STGLANE").toString()));
                        
                        s_list.add(items);
                    }


                }
            } catch (ClassCastException e2) {
                JSONObject jsObject2 = (JSONObject) jsObject1.get("XLPNRES_ITEM");
                if (jsObject2 != null) {


                    DockDoorBO items = new DockDoorBO();
                    // JSONObject jsObject2 = (JSONObject)

                    items.setDOCKDOOR((jsObject2.get("DOCKDOOR").toString()));
                    items.setDOCKDOORID((jsObject2.get("DOCKDOORID").toString()));
                    items.setSUBINV((jsObject2.get("SUBINV").toString()));
                    items.setSTGLANE((jsObject2.get("STGLANE").toString()));


                    s_list.add(items);


                }
            }

            arr = (DockDoorBO[]) s_list.toArray(new DockDoorBO[s_list.size()]);
            if(s_list.size()!=0){
                AdfmfJavaUtilities.setELValue("{pageFlowScope.LpnResults}", "");
            }else{
                AdfmfJavaUtilities.setELValue("{pageFlowScope.LpnResults}", "No Search Results");
            }
        
        } catch (Exception e) {
            Trace.log("REST_JSON", Level.SEVERE, this.getClass(), "DockDoorBO", e.getLocalizedMessage());
        }
        return arr;
    }

    public void refresh() {
        providerChangeSupport.fireProviderRefresh("dockDoors");
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
