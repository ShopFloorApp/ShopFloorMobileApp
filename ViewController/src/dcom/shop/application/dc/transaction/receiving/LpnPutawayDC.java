package dcom.shop.application.dc.transaction.receiving;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.LocatorBO;
import dcom.shop.application.mobile.SubinventoryBO;

import dcom.shop.application.mobile.transaction.receiving.ShipmentBO;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LpnPutawayDC extends SyncUtils {
    protected static List s_subInvLPN = new ArrayList();
    protected static List s_locatorLPN = new ArrayList();
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    public LpnPutawayDC() {
        super();
    }
    public void addProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.addProviderChangeListener(l);
    }

    public void removeProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.removeProviderChangeListener(l);
    }
    public SubinventoryBO[] getSubInventories(){
        s_subInvLPN.clear();
        String orgCode = (String)AdfmfJavaUtilities.evaluateELExpression("#{preferenceScope.feature.dcom.shop.MyWarehouse.OrgCodePG.OrgCode}");
        s_subInvLPN=super.getFilteredCollectionFromDB(SubinventoryBO.class,"WHERE WHSE="+orgCode);
        SubinventoryBO[] subInventoriesArray =
            (SubinventoryBO[]) s_subInvLPN.toArray(new SubinventoryBO[s_subInvLPN.size()]);
        return subInventoriesArray;
    }
    
    public LocatorBO[] getLocators(){
        s_locatorLPN.clear();
        String subInv = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.subinvLPN}");
        String whereClause="WHERE SUBINV=\""+subInv+"\"";
        s_locatorLPN=super.getFilteredCollectionFromDB(LocatorBO.class,whereClause);
        LocatorBO[] locatorsArray =
            (LocatorBO[]) s_locatorLPN.toArray(new LocatorBO[s_locatorLPN.size()]);
        return locatorsArray;
    }
    public void refreshLocators() {
        providerChangeSupport.fireProviderRefresh("locators");
    }
    
    public void submitLpnPutaway(){
        String restURI = RestURI.PostLpnPutaway();
        RestCallerUtil rcu = new RestCallerUtil();
        
        String orgCode = (String)AdfmfJavaUtilities.evaluateELExpression("#{preferenceScope.feature.dcom.shop.MyWarehouse.OrgCodePG.OrgCode}");
        String lpnMain = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.lpnMain}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.lpnMain}"));
        String itemLPN = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.itemLPN}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.itemLPN}"));
        String uomLPN = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.uomLPN}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.uomLPN}"));
        String qtyReceivedLPN = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.qtyReceivedLPN}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.qtyReceivedLPN}"));
        String lpnSubLPN = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.lpnSubLPN}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.lpnSubLPN}"));
        String subinvLPN = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.subinvLPN}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.subinvLPN}"));
        String locatorLPN = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.locatorLPN}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.locatorLPN}"));

        String payload =
            "{\n" + "\"GET_SO_PER_ORG_Input\":\n" + "{\n" +
            "\"@xmlns\": \"http://xmlns.oracle.com/apps/fnd/rest/GetSoPerOrgSvc/get_so_per_org/\",\n" +
            "   \"RESTHeader\": {\"@xmlns\": \"http://xmlns.oracle.com/apps/fnd/rest/GetSoPerOrgSvc/header\",\n" +
            "                  \"Responsibility\": \"DCOM_MOBILE_USER\",\n" +
            "                  \"RespApplication\": \"INV\",\n" +
            "                  \"SecurityGroup\": \"STANDARD\",\n" +
            "                  \"NLSLanguage\": \"AMERICAN\",\n" + "                  \"Org_Id\": \""+orgCode+"\"\n" +
            "                 },\n" + "   \"InputParameters\": \n" +
            "                   {\"PLPNPUT\": {"+
            "                   \"ORGCODE\": \""+orgCode+"\",\n" +
            "                   \"ITEM\": \""+itemLPN+"\",\n" +
            "                   \"QTY\": \""+qtyReceivedLPN+"\",\n" +
            "                   \"UOM\": \""+uomLPN+"\",\n" +
            "                   \"LPN\": \""+lpnMain+"\",\n" +
            "                   \"XREFLPN\": \""+lpnSubLPN+"\",\n" +
            "                   \"SUBINV\": \""+subinvLPN+"\",\n" +
            "                    \"LOCATOR\": "+locatorLPN+"\n }\n" + "}}}" ;
        System.out.println("Calling create method");
        String jsonArrayAsString = rcu.invokeUPDATE(restURI, payload);
        System.out.println("Received response");        
        
            try {
        JSONParser parser = new JSONParser();
        Object object;

        
            object = parser.parse(jsonArrayAsString);
        

        JSONObject jsonObject = (JSONObject) object;
        JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
            /*Updating the Transaction Status*/            
            String status = (String) jsObject.get("XSTATUS");
                    String msg = (String) jsObject.get("XMSG");
                        
                if(status.equals("S")){
                    HashMap whereClause = new HashMap();

                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                              "showAlert", new Object[] {"LPN Putaway","Status : Success \n"+msg,"Ok" });

                }else{
                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                              "showAlert", new Object[] {"LPN Putaway","Status : Failed","Ok" });
                }


        } catch (ParseException e) {
            e.getMessage();
        }
    }
}
