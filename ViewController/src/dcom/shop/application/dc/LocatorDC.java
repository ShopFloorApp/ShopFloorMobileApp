package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
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
    private String fromLoc;
    private String toLoc;

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
    private static final String NOT_REACHABLE = "NotReachable"; // Indiates no network connectivity

    public void setFiltered_Locators(List filtered_Locators) {
        this.filtered_Locators = filtered_Locators;
    }

    public List getFiltered_Locators() {
        return filtered_Locators;
    }
    protected String aliasFilter = "";
    protected List s_locator = new ArrayList();
    protected static List s_to_locator = new ArrayList();
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);


    public LocatorDC() {
        super();
        // ProcessWS("");
        /*  try{
        GenericVirtualType payload = new GenericVirtualType(null, "payload");
        payload.defineAttribute(null, "Whse", String.class, "100");
        String subInv = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.fromSubInv}");
        if(subInv == null)
        payload.defineAttribute(null, "Subinv", String.class, "RAW");
        HashMap paramsMap=new HashMap();
        paramsMap.put("resAttriName","LocatorDetails");
        paramsMap.put("lovDCName", "LocatorLOV_WS");
        paramsMap.put("opeartionName", "process");
        paramsMap.put("payload",payload);
        s_locator = super.getCollection(LocatorBO.class, paramsMap);
            filterLocators();
        }catch(Exception e){
            throw new RuntimeException(e);
        }*/
    }

    //  public void ProcessWS(String subInv) {
    public void syncLocalDB() {
        String subInv = "";
        String networkStatus =
            (String) AdfmfJavaUtilities.evaluateELExpression("#{deviceScope.hardware.networkStatus}");
       List collections;
        if (networkStatus.equals(NOT_REACHABLE)) {
            s_locator = super.getCollectionFromDB(LocatorBO.class);
        } else {
            System.out.println("Inside orgItem");
            Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
            String restURI = "/webservices/rest/DCOMLOV/getLocator/";
            RestCallerUtil rcu = new RestCallerUtil();
            String payload =
 "{\n" +
                "  \"Input_Parameters\": {\n" +
                "    \"RESTHeader\": {\n" +
                "      \"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" +
                "      \"RespApplication\": \"ONT\",\n" +
                "      \"SecurityGroup\": \"STANDARD\",\n" +
                "      \"NLSLanguage\": \"AMERICAN\",\n" +
                "      \"Org_Id\": \"82\"\n" +
                "    },\n" +
                "    \"InputParameters\": {\n" +
                "      \"PWAREHOUSE\": \"\",\n" +
                "      \"PSUBINV\": \""+subInv+"\"\n" +
                "    }\n" +
                "  }\n" +
                "}";

             System.out.println("Calling create method");String jsonArrayAsString = rcu.invokeUPDATE(restURI, payload);
            System.out.println("Received response");
            if (jsonArrayAsString != null) {
                try {
                    JSONParser parser = new JSONParser();
                    Object object;

                    object = parser.parse(jsonArrayAsString);

                    JSONObject jsonObject = (JSONObject) object;
                    JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                    JSONObject jsObject1 = (JSONObject) jsObject.get("XLOCATOR");
                    JSONArray array = (JSONArray) jsObject1.get("XLOCATOR_ITEM");
                    if (array != null) {
                        int size = array.size();
                        //  ProductSearchEntity[] prodItems= new ProductSearchEntity[size];
                        for (int i = 0; i < size; i++) {


                            LocatorBO locatorItems = new LocatorBO();
                            JSONObject jsObject2 = (JSONObject) array.get(i);
                            locatorItems.setLocator((jsObject2.get("LOCATOR").toString()));
                            locatorItems.setWhse((jsObject2.get("WHSE").toString()));
                            locatorItems.setSubinv((jsObject2.get("SUBINV").toString()));
                            locatorItems.setDescription((jsObject2.get("DESCRIPTION").toString()));
                            locatorItems.setLocatorType((jsObject2.get("LOCATORTYPE").toString()));
                            s_locator.add(locatorItems);


                        }

                        super.updateSqlLiteTable(LocatorBO.class, s_locator);
                    }
                } catch (ParseException e) {
                    e.getMessage();
                }
            }
        }

        /* GenericVirtualType payload = new GenericVirtualType(null, "payload");
        payload.defineAttribute(null, "Whse", String.class, "100");
         subInv = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.FromSubinventory}");
        if(subInv == null)
        payload.defineAttribute(null, "Subinv", String.class, "RAW");
        HashMap paramsMap=new HashMap();
        paramsMap.put("resAttriName","LocatorDetails");
        paramsMap.put("lovDCName", "LocatorLOV_WS");
        paramsMap.put("opeartionName", "process");
        paramsMap.put("payload",payload);
        s_locator = super.getCollection(LocatorBO.class, paramsMap);
        providerChangeSupport.fireProviderRefresh("locators");*/
    }

    public void setLocator(List s_locator) {
        List oldLocator = s_locator;
        s_locator = s_locator;
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
            /*GenericVirtualType payload = new GenericVirtualType(null, "payload");
            payload.defineAttribute(null, "Whse", String.class, "100");
            String subInv = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.FromSubinventory}");
            payload.defineAttribute(null, "Subinv", String.class, subInv == null ? "RAW" : subInv);
            HashMap paramsMap = new HashMap();
            paramsMap.put("resAttriName", "LocatorDetails");
            paramsMap.put("lovDCName", "LocatorLOV_WS");
            paramsMap.put("opeartionName", "process");
            paramsMap.put("payload", payload);*/
            //String refresh = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.refreshFromLocator}");
           // if ("Y".equals(refresh)) {
            s_locator = super.getOfflineCollection(LocatorBO.class);
            filterLocators();
            //}
            locators = (LocatorBO[]) filtered_Locators.toArray(new LocatorBO[filtered_Locators.size()]);
            return locators;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public LocatorBO[] getToLocators() {

        try {
            LocatorBO[] locators = null;
            /* GenericVirtualType payload = new GenericVirtualType(null, "payload");
            payload.defineAttribute(null, "Whse", String.class, "100");
            String subInv = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.ToSubinventory}");
            payload.defineAttribute(null, "Subinv", String.class, subInv == null ? "RAW" : subInv);
            HashMap paramsMap = new HashMap();
            paramsMap.put("resAttriName", "LocatorDetails");
            paramsMap.put("lovDCName", "LocatorLOV_WS");
            paramsMap.put("opeartionName", "process");
            paramsMap.put("payload", payload);*/
            //String refresh = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.refreshToLocator}");
           // if ("Y".equals(refresh)) {
                s_to_locator = super.getOfflineCollection(LocatorBO.class);
                filterToLocators();
            //}
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
            System.out.println("inside filter code");
            filtered_Locators.clear();

            HashMap filterFileds = new HashMap();
            String subInv = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.FromSubinventory}");
            if (subInv == null)
                subInv = "DEFAULT";
            filterFileds.put("subinv", subInv);
            //   filterFileds.put("alias", getAliasFilter());


            HashMap paramMap = new HashMap();
            paramMap.put("collection", s_locator);
            paramMap.put("filterFieldsValues", filterFileds);
            System.out.println("called super filtered class");

            filtered_Locators = (List) super.getFileteredCollection(LocatorBO.class, paramMap);
            System.out.println("collection size is " + filtered_Locators.size());
            providerChangeSupport.fireProviderRefresh("locators");
        } catch (Exception e) {
            throw new RuntimeException("My Code Error " + e);
        }
    }

    public void filterToLocators() {
        try {
            System.out.println("inside filter code");
            filtered_To_Locators.clear();

            HashMap filterFileds = new HashMap();
            String subInv = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.ToSubinventory}");
            if (subInv == null)
                subInv = "DEFAULT";
            filterFileds.put("subinv", subInv);
            //   filterFileds.put("alias", getAliasFilter());


           HashMap paramMap = new HashMap();
            paramMap.put("collection", s_to_locator);
            paramMap.put("filterFieldsValues", filterFileds);
            System.out.println("called super filtered class");

            filtered_To_Locators = (List) super.getFileteredCollection(LocatorBO.class, paramMap);
            System.out.println("collection size is " + filtered_To_Locators.size());
           // providerChangeSupport.fireProviderRefresh("toLocators");
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
