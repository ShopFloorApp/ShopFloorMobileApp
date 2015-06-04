package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.AccountAliasBO;
import dcom.shop.application.mobile.LocatorBO;
import dcom.shop.restURIDetails.RestCallerUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.Utility;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LocatorDC extends SyncUtils {
    protected List filtered_Locators = new ArrayList();
    protected List filtered_To_Locators = new ArrayList();
    protected String locatorFilter = "";
    //private String fromLoc = "100.RAW.00.00";
    private String fromLoc = null;
    //private String toLoc = "100.RAW.00.00";
    private String toLoc = null;
    private static final String NOT_REACHABLE = "NotReachable"; // Indiates no network connectivity
    protected String aliasFilter = "";
    protected List s_locator = new ArrayList();
    protected static List s_to_locator = new ArrayList();
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);

    public void setFromLoc(String fromLoc) {
        String oldFromLoc = this.fromLoc;
        this.fromLoc = fromLoc;
        propertyChangeSupport.firePropertyChange("fromLoc", oldFromLoc, fromLoc);
    }

    public String getFromLoc() {
        return fromLoc;
    }

    public void setToLoc(String toLoc) {
        String oldToLoc = this.toLoc;
        this.toLoc = toLoc;
        propertyChangeSupport.firePropertyChange("toLoc", oldToLoc, toLoc);
    }

    public String getToLoc() {
        return toLoc;
    }

    public void setFiltered_Locators(List filtered_Locators) {
        this.filtered_Locators = filtered_Locators;
    }

    public List getFiltered_Locators() {
        return filtered_Locators;
    }

    public LocatorDC() {
        super();
    }

    public void syncLocalDB() {
        String subInv = "";
        String networkStatus =
            (String) AdfmfJavaUtilities.evaluateELExpression("#{deviceScope.hardware.networkStatus}");
        if (networkStatus.equals(NOT_REACHABLE)) {
            s_locator = super.getCollectionFromDB(LocatorBO.class);
        } else {

            Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
            String restURI = "/webservices/rest/DCOMLOV/getLocator/";
            RestCallerUtil rcu = new RestCallerUtil();
            String payload =
                "{\n" + "  \"Input_Parameters\": {\n" + "    \"RESTHeader\": {\n" +
                "      \"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" + "      \"RespApplication\": \"ONT\",\n" +
                "      \"SecurityGroup\": \"STANDARD\",\n" + "      \"NLSLanguage\": \"AMERICAN\",\n" +
                "      \"Org_Id\": \"82\"\n" + "    },\n" + "    \"InputParameters\": {\n" +
                "      \"PWAREHOUSE\": \"\",\n" + "      \"PSUBINV\": \"" + subInv + "\"\n" + "    }\n" + "  }\n" + "}";


            String jsonArrayAsString = rcu.invokeUPDATE(restURI, payload);

            if (jsonArrayAsString != null) {
                JSONObject jsObject1 = null;
                try {
                    JSONParser parser = new JSONParser();
                    Object object;

                    object = parser.parse(jsonArrayAsString);

                    JSONObject jsonObject = (JSONObject) object;
                    JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                    jsObject1 = (JSONObject) jsObject.get("XLOCATOR");
                    JSONArray array = (JSONArray) jsObject1.get("XLOCATOR_ITEM");
                    if (array != null) {
                        int size = array.size();
                        for (int i = 0; i < size; i++) {
                            LocatorBO locatorItems = new LocatorBO();
                            JSONObject jsObject2 = (JSONObject) array.get(i);
                            locatorItems.setLocator((jsObject2.get("LOCATOR").toString()));
                            locatorItems.setWhse((jsObject2.get("WHSE").toString()));
                            locatorItems.setSubinv((jsObject2.get("SUBINV").toString()));
                            locatorItems.setDescription((jsObject2.get("DESCRIPTION").toString()));
                            locatorItems.setLocatorType((jsObject2.get("LOCATORTYPE").toString()));
                            locatorItems.setXDIM((jsObject2.get("XDIM").toString()));
                            locatorItems.setYDIM((jsObject2.get("YDIM").toString()));
                            s_locator.add(locatorItems);
                        }
                        super.updateSqlLiteTable(LocatorBO.class, s_locator);
                    }
                } catch (ClassCastException e2) {
                    JSONObject jsObject2 = (JSONObject) jsObject1.get("XLOCATOR_ITEM");
                    if (jsObject2 != null) {
                        LocatorBO locatorItems = new LocatorBO();
                        locatorItems.setLocator((jsObject2.get("LOCATOR").toString()));
                        locatorItems.setWhse((jsObject2.get("WHSE").toString()));
                        locatorItems.setSubinv((jsObject2.get("SUBINV").toString()));
                        locatorItems.setDescription((jsObject2.get("DESCRIPTION").toString()));
                        locatorItems.setLocatorType((jsObject2.get("LOCATORTYPE").toString()));
                        locatorItems.setXDIM((jsObject2.get("XDIM").toString()));
                        locatorItems.setYDIM((jsObject2.get("YDIM").toString()));
                        s_locator.add(locatorItems);
                        super.updateSqlLiteTable(LocatorBO.class, s_locator);
                    }
                } catch (ParseException e) {
                    e.getMessage();
                }
            }
        }
    }

    public void setLocator(List s_locator) {
        List oldLocator = s_locator;
        this.s_locator = s_locator;
        propertyChangeSupport.firePropertyChange("locator", oldLocator, s_locator);
    }

    public List getLocator() {
        LocatorBO[] locators = null;
        locators = getLocators();
        return s_locator;
    }

    public void setLocatorFilter(String locatorFilter) {
        String oldLocatorFilter = this.locatorFilter;
        this.locatorFilter = locatorFilter;
        propertyChangeSupport.firePropertyChange("locatorFilter", oldLocatorFilter, locatorFilter);
    }

    public String getLocatorFilter() {
        return locatorFilter;
    }

    public void setAliasFilter(String aliasFilter) {
        String oldAliasFilter = this.aliasFilter;
        this.aliasFilter = aliasFilter;
        propertyChangeSupport.firePropertyChange("aliasFilter", oldAliasFilter, aliasFilter);
    }

    public String getAliasFilter() {
        return aliasFilter;
    }

    public LocatorBO[] getLocators() {
        try {
            LocatorBO[] locators = null;
            s_locator = super.getOfflineCollection(LocatorBO.class);
            filterLocators();
            locators = (LocatorBO[]) filtered_Locators.toArray(new LocatorBO[filtered_Locators.size()]);
            return locators;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public LocatorBO[] getToLocators() {

        try {
            LocatorBO[] locators = null;
            s_to_locator = super.getOfflineCollection(LocatorBO.class);
            filterToLocators();
            locators = (LocatorBO[]) filtered_To_Locators.toArray(new LocatorBO[filtered_To_Locators.size()]);
            return locators;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void refresh() {
        providerChangeSupport.fireProviderRefresh("locators");
    }

    public void refreshToLocators() {
        providerChangeSupport.fireProviderRefresh("toLocators");
    }

    public void filterLocators() {
        try {
            filtered_Locators.clear();

            HashMap filterFileds = new HashMap();
            String whereClause = null;
            String subInv = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.FromSubinventory}");
            //if (subInv == null)
            // subInv = "RAW";
            if (subInv != null)
                filterFileds.put("subinv", subInv);
            filterFileds.put("locatortype", "3");
            HashMap paramMap = new HashMap();
            paramMap.put("collection", s_locator);
            paramMap.put("filterFieldsValues", filterFileds);
            
            String orgCode = (String)AdfmfJavaUtilities.evaluateELExpression("#{preferenceScope.feature.dcom.shop.MyWarehouse.OrgCodePG.OrgCode}");

            //AJ 14May
            //Changed the way to call filtered as FG and FGCUST both subinv were getting returned
            //filtered_Locators = (List) super.getFileteredCollection(LocatorBO.class, paramMap);
            if(subInv != null)
            whereClause="WHERE SUBINV=\""+subInv+"\" AND WHSE=\""+orgCode+"\" AND LOCATORTYPE=\"3\"";
            else
                 whereClause="WHERE LOCATORTYPE=\"3\" AND WHSE=\""+orgCode+"\"";
            filtered_Locators=super.getFilteredCollectionFromDB(LocatorBO.class,whereClause);

        } catch (Exception e) {
            throw new RuntimeException("My Code Error " + e);
        }
    }

    public void filterToLocators() {
        try {
            filtered_To_Locators.clear();
            HashMap filterFileds = new HashMap();
            String whereClause = null;
            String subInv = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.ToSubinventory}");
            //if (subInv == null)
            //    subInv = "DEFAULT";
            if (subInv != null)
                filterFileds.put("subinv", subInv);
            HashMap paramMap = new HashMap();
            paramMap.put("collection", s_to_locator);
            paramMap.put("filterFieldsValues", filterFileds);
            //AJ 14May
            //Changed the way to call filtered as FG and FGCUST both subinv were getting returned
            //filtered_To_Locators = (List) super.getFileteredCollection(LocatorBO.class, paramMap);
            String orgCode = (String)AdfmfJavaUtilities.evaluateELExpression("#{preferenceScope.feature.dcom.shop.MyWarehouse.OrgCodePG.OrgCode}");

            if(subInv != null)
            whereClause="WHERE SUBINV=\""+subInv+"\" AND WHSE=\""+orgCode+"\" AND LOCATORTYPE=\"3\"";
            else
                 whereClause="WHERE LOCATORTYPE=\"3\" AND WHSE=\""+orgCode+"\"";
           // String whereClause="WHERE SUBINV=\""+subInv+"\" AND LOCATORTYPE=\"3\"";
            filtered_To_Locators=super.getFilteredCollectionFromDB(LocatorBO.class,whereClause);

        } catch (Exception e) {
            throw new RuntimeException("My Code Error " + e);
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }

    public void addProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.addProviderChangeListener(l);
    }

    public void removeProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.removeProviderChangeListener(l);
    }
}
