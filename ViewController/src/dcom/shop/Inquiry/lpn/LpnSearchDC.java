package dcom.shop.Inquiry.lpn;

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

public class LpnSearchDC {
    public LpnSearchDC() {
        super();
    }
    List s_LpnList = new ArrayList();
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public LpnSearchEntity[] getAllLpns() {
        String backFrom = null;
        try {
            backFrom = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.LpnDetailsBack}").toString();
        } catch (Exception ae) {
            backFrom = "";
        }
        LpnSearchEntity[] lpnArray = null;
        if (!backFrom.equals("FromBack")) {
            ValueExpression ve = null;
            s_LpnList.clear();
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.keywrdLpnLenErr}", "");
            Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
            String keyword = null;
            ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchLpnKeyword}", String.class);
            keyword = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());
            String orgCode =
                (String) AdfmfJavaUtilities.evaluateELExpression("#{preferenceScope.feature.dcom.shop.MyWarehouse.OrgCodePG.OrgCode}");

            String restURI = RestURI.PostLpnDetailsURI();
            RestCallerUtil rcu = new RestCallerUtil();
            String payload =
                "{\"x\": {\"RESTHeader\": {\"@xmlns\": \"http://xmlns.oracle.com/apps/inv/rest/DCOMInquiry/header\",\"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\"RespApplication\": \"ONT\",\"SecurityGroup\": \"STANDARD\",\n" +
                "\"NLSLanguage\": \"AMERICAN\",\"Org_Id\": \"82\"},\"InputParameters\": {\"PLPNREQ\": {\"ORGCODE\": \"" +
                orgCode + "\",\"LPN\": \"%" + keyword + "%\"}}\n" + "}\n" + "}";
            String jsonArrayAsString = (rcu.invokeUPDATE(restURI, payload)).toString();

            try {
                JSONParser parser = new JSONParser();
                Object object = parser.parse(jsonArrayAsString);
                JSONObject jsonObject = (JSONObject) object;
                JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                JSONObject jsObject1 = (JSONObject) jsObject.get("XLPNRES");
                try {
                    JSONArray array = (JSONArray) jsObject1.get("XLPNRES_ITEM");

                    if (array != null) {
                        int size = array.size();
                        for (int i = 0; i < size; i++) {
                            LpnSearchEntity lpnItems = new LpnSearchEntity();
                            JSONObject jsObjectArrayData = (JSONObject) array.get(i);
                            lpnItems.setORGCODE((jsObjectArrayData.get("ORGCODE").toString()));
                            lpnItems.setLPN((jsObjectArrayData.get("LPN").toString()));
                            lpnItems.setLPNSTATUS((jsObjectArrayData.get("LPNSTATUS").toString()));
                            lpnItems.setSUBINV((jsObjectArrayData.get("SUBINV").toString()));
                            lpnItems.setLOCATOR((jsObjectArrayData.get("LOCATOR").toString()));
                            s_LpnList.add(lpnItems);
                        }
                    }
                } catch (ClassCastException e2) {
                    JSONObject jsObject2 = (JSONObject) jsObject1.get("XLPNRES_ITEM");
                    if (jsObject2 != null) {
                        LpnSearchEntity lpnItems = new LpnSearchEntity();
                        lpnItems.setORGCODE((jsObject2.get("ORGCODE").toString()));
                        lpnItems.setLPN((jsObject2.get("LPN").toString()));
                        lpnItems.setLPNSTATUS((jsObject2.get("LPNSTATUS").toString()));
                        lpnItems.setSUBINV((jsObject2.get("SUBINV").toString()));
                        lpnItems.setLOCATOR((jsObject2.get("LOCATOR").toString()));
                        s_LpnList.add(lpnItems);
                    }
                }
                lpnArray = (LpnSearchEntity[]) s_LpnList.toArray(new LpnSearchEntity[s_LpnList.size()]);
            } catch (Exception e) {
                Trace.log("REST_JSON", Level.SEVERE, this.getClass(), "LpnSearchEntity", e.getLocalizedMessage());
            }
            if (s_LpnList.size() != 0) {
                AdfmfJavaUtilities.setELValue("#{pageFlowScope.LpnResults}", "");
            } else {
                AdfmfJavaUtilities.setELValue("#{pageFlowScope.LpnResults}", "No Search Results");
            }
        } else {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.LpnDetailsBack}", "");
            lpnArray = (LpnSearchEntity[]) s_LpnList.toArray(new LpnSearchEntity[s_LpnList.size()]);
        }
        return lpnArray;
    }

    public void refresh() {
        providerChangeSupport.fireProviderRefresh("allLpns");
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
