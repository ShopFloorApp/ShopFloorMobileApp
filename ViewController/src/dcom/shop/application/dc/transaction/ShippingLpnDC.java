package dcom.shop.application.dc.transaction;

import dcom.shop.application.mobile.LPNBO;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;

import javax.el.ValueExpression;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.util.logging.Trace;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ShippingLpnDC {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public ShippingLpnDC() {
        super();
    }
    List s_Lpnlist = new ArrayList();

    public LPNBO[] getShipLPN(){
        s_Lpnlist.clear();
        String restURI =  RestURI.PostLpn();
        String orgCode = (String)AdfmfJavaUtilities.evaluateELExpression("#{preferenceScope.feature.dcom.shop.MyWarehouse.OrgCodePG.OrgCode}");

        String dockDoor = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.dockDoor}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.dockDoor}"));
        String lpn = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.searchLpnKeyword}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.searchLpnKeyword}"));
    LPNBO[] lpnArray =null;
    ValueExpression ve =null;

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
        "\"InputParameters\" : { \"PLPNTYPE\" : \"MISSING_LPN\" , \"PORGCODE\" : \""+orgCode+"\" \n , \"PDOCKDOOR\" : \""+dockDoor+"\" \n, \"PLPNFROM\" : \"\" \n, \"PLPNTO\" : \"\" \n, \"PSUBINV\" : \"\" \n, \"PLOCATOR\" :\"\" \n } } }";
        System.out.println("Calling missing lpn method");
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
               JSONArray jsarray = (JSONArray) jsObject.get("XLPN");
                
                if(jsarray!=null){
                    try{
                        int arraySize = jsarray.size();
                        for(int i=0; i<arraySize; i++){
                            
                            JSONObject jsi = (JSONObject) jsarray.get(i);
                            if(jsi!=null){
                                try{
                                    
                                    LPNBO lpni = new LPNBO();
                                    lpni.setLpn(jsi.get("LPN").toString());
                                    lpni.setGrossWeight(Integer.parseInt(jsi.get("GROSSWEIGHT").toString()));
                                    lpni.setLoadSeq(jsi.get("LOADSEQ").toString());
                                    lpni.setLocator(jsi.get("LOCATOR").toString());
                                    lpni.setLpnContext(jsi.get("LPNCONTEXT").toString());
                                    lpni.setOutermostLpn(jsi.get("OUTERMOSTLPN").toString());
                                    lpni.setParentLpn(jsi.get("PARENTLPN").toString());
                                    lpni.setSubinv(jsi.get("SUBINV").toString());
                                    lpni.setVolume(Integer.parseInt(jsi.get("VOLUME").toString()));
                                    lpni.setVolumeUom(jsi.get("VOLUMEUOM").toString());
                                    lpni.setWeightUom(jsi.get("WEIGHTUOM").toString());
                                    //add other stuff also
                                    
                                    s_Lpnlist.add(lpni);
                                        
                                    
                                }catch(Exception e2){
                                    e2.getMessage();
                                }
                                
                            }                      
                            
                        }
                    }catch(Exception e1){
                        e1.getMessage();
                    }
                }
                
               
                ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.ShipLpnResults}", String.class);

                if (s_Lpnlist.size() > 0) {
                    ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "Search results");
                } else {
                    ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "No Search results");

                }
                lpnArray = (LPNBO[]) s_Lpnlist.toArray(new LPNBO[s_Lpnlist.size()]);

                } catch (Exception e) {
                Trace.log("REST_JSON", Level.SEVERE, this.getClass(), "LPNBO", e.getLocalizedMessage());
                }
            }
    return lpnArray;    
}

    public synchronized void setLpnlist(List s_Lpnlist) {
        List oldLpnlist = this.s_Lpnlist;
        this.s_Lpnlist = s_Lpnlist;
        propertyChangeSupport.firePropertyChange("lpnlist", oldLpnlist, s_Lpnlist);
    }

    public synchronized List getLpnlist() {
        return s_Lpnlist;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}