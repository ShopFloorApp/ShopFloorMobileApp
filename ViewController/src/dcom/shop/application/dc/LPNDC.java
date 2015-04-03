package dcom.shop.application.dc;

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
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.Utility;
import oracle.adfmf.util.logging.Trace;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class LPNDC {
    List s_LpnList = new ArrayList();
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public LPNBO[] getFromLpns() {
        ValueExpression ve = null;


        System.out.println("Inside lpn search");
        Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
        String keyword = null;
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchLpnKeyword}", String.class);
        keyword = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.LpnPage}", String.class);
        String lpnPage = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        String field = (String)AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.barCodeField}");
        if("TOLPN".equals(field)){
            keyword = (String)AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.searchToLpnKeyword}");
        }
        String restURI = RestURI.PostLpnInquiryURI();
        RestCallerUtil rcu = new RestCallerUtil();
        String payload =
            "{\n" + "  \"Input_Parameters\": {\n" + "    \"RESTHeader\": {\n" +
            "      \"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" + "      \"RespApplication\": \"ONT\",\n" +
            "      \"SecurityGroup\": \"STANDARD\",\n" + "      \"NLSLanguage\": \"AMERICAN\",\n" +
            "      \"Org_Id\": \"82\"\n" + "    },\n" + "    \"InputParameters\": {\n" +
            "        \"PORGCODE\": \"100\",\n" + "        \"PLPNTYPE\": \"" + lpnPage + "\",\n" +
            "        \"PLPNFROM\": \"" + keyword + "\"\n" + "}\n" + "    }\n" + "  }\n" + "}";
        System.out.println("Calling create method");
        String jsonArrayAsString = (rcu.invokeUPDATE(restURI, payload)).toString();
        System.out.println("Received response");
        LPNBO[] lpnArray = null;
        //ashish
        try {
            JSONParser parser = new JSONParser();
            Object object = parser.parse(jsonArrayAsString);
            JSONObject jsonObject = (JSONObject) object;
            JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
            JSONObject jsObject1 = (JSONObject) jsObject.get("XLPN");
            try {
                JSONArray array = (JSONArray) jsObject1.get("XLPN_ITEM");

                if (array != null) {
                    int size = array.size();
                    //  ProductSearchEntity[] prodItems= new ProductSearchEntity[size];
                    for (int i = 0; i < size; i++) {


                        LPNBO lpnItems = new LPNBO();
                        //  JSONObject jsObjectArrayData = (JSONObject) jsObject1.get("XLPNRES_ITEM");
                        JSONObject jsObjectArrayData = (JSONObject) array.get(i);

                        lpnItems.setWeightUom((jsObjectArrayData.get("WEIGHTUOM").toString()));
                        lpnItems.setLpn((jsObjectArrayData.get("LPN").toString()));
                        lpnItems.setLpnContext((jsObjectArrayData.get("LPNCONTEXT").toString()));
                        lpnItems.setSubinv((jsObjectArrayData.get("SUBINV").toString()));
                        lpnItems.setLocator((jsObjectArrayData.get("LOCATOR").toString()));
                        lpnItems.setGrossWeight(((Integer) Integer.parseInt(("{\"@xsi:nil\":\"true\"}".equals(jsObjectArrayData.get("GROSSWEIGHT").toString()) ?
                                                                             "0" :
                                                                             jsObjectArrayData.get("GROSSWEIGHT")).toString())));
                        lpnItems.setVolumeUom((jsObjectArrayData.get("VOLUMEUOM").toString()));
                        lpnItems.setVolume(((Integer) Integer.parseInt(("{\"@xsi:nil\":\"true\"}".equals(jsObjectArrayData.get("VOLUME").toString()) ?
                                                                        "0" :
                                                                        jsObjectArrayData.get("VOLUME")).toString())));
                        lpnItems.setParentLpn((jsObjectArrayData.get("PARENTLPN").toString()));
                        lpnItems.setOutermostLpn((jsObjectArrayData.get("OUTERMOSTLPN").toString()));
                        lpnItems.setLoadSeq((jsObjectArrayData.get("LOADSEQ").toString()));
                        lpnItems.setDeliveryId(((Integer) Integer.parseInt(("{\"@xsi:nil\":\"true\"}".equals(jsObjectArrayData.get("DELIVERYID").toString()) ?
                                                                            "0" :
                                                                            jsObjectArrayData.get("DELIVERYID")).toString())));
                        s_LpnList.add(lpnItems);
                    }


                }
            } catch (ClassCastException e2) {
                JSONObject jsObject2 = (JSONObject) jsObject1.get("XLPNRES_ITEM");
                if (jsObject2 != null) {


                    LPNBO lpnItems = new LPNBO();
                    // JSONObject jsObject2 = (JSONObject)

                    lpnItems.setWeightUom((jsObject2.get("WEIGHTUOM").toString()));
                    lpnItems.setLpn((jsObject2.get("LPN").toString()));
                    lpnItems.setLpnContext((jsObject2.get("LPNCONTEXT").toString()));
                    lpnItems.setSubinv((jsObject2.get("SUBINV").toString()));
                    lpnItems.setLocator((jsObject2.get("LOCATOR").toString()));
                    lpnItems.setGrossWeight(((Integer.parseInt(("{\"@xsi:nil\":\"true\"}".equals(jsObject2.get("GROSSWEIGHT").toString()) ?
                                                                "0" : jsObject2.get("GROSSWEIGHT")).toString()))));
                    lpnItems.setVolumeUom((jsObject2.get("VOLUMEUOM").toString()));
                    lpnItems.setVolume(((Integer.parseInt(("{\"@xsi:nil\":\"true\"}".equals(jsObject2.get("VOLUME").toString()) ?
                                                           "0" : jsObject2.get("VOLUME")).toString()))));
                    lpnItems.setParentLpn((jsObject2.get("PARENTLPN").toString()));
                    lpnItems.setOutermostLpn((jsObject2.get("OUTERMOSTLPN").toString()));
                    lpnItems.setLoadSeq((jsObject2.get("LOADSEQ").toString()));
                    lpnItems.setDeliveryId(((Integer.parseInt(("{\"@xsi:nil\":\"true\"}".equals(jsObject2.get("DELIVERYID").toString()) ?
                                                               "0" : jsObject2.get("DELIVERYID")).toString()))));


                    s_LpnList.add(lpnItems);


                }
            }
            ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.LpnResults}", String.class);

            if (s_LpnList.size() > 0) {
                ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "Search results");
            }else
            {
                ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "No Search results");

            }
            lpnArray = (LPNBO[]) s_LpnList.toArray(new LPNBO[s_LpnList.size()]);

        } catch (Exception e) {
            Trace.log("REST_JSON", Level.SEVERE, this.getClass(), "LPNBO", e.getLocalizedMessage());
        }
        return lpnArray;
    }

    public void refresh() {
        providerChangeSupport.fireProviderRefresh("fromLpns");
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
