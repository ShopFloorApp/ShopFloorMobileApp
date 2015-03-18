package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.SubinventoryBO;
import dcom.shop.restURIDetails.RestCallerUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.el.ValueExpression;

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

public class SubinventoryDC extends SyncUtils {
    public SubinventoryDC() {
        super();
        //        s_subInventories = getSubInventories();
    }
    private List filtered_Subinventories = new ArrayList();
    private String subinvFilter = "";

    public void setSubInv(String subInv) {
        String oldSubInv = this.subInv;
        this.subInv = subInv;
        propertyChangeSupport.firePropertyChange("subInv", oldSubInv, subInv);
    }

    public void setSubToInv(String subToInv) {
        String oldSubToInv = this.subToInv;
        this.subToInv = subToInv;
        propertyChangeSupport.firePropertyChange("subToInv", oldSubToInv, subToInv);
    }

    public String getSubToInv() {
        return subToInv;
    }

    public String getSubInv() {
        return subInv;
    }
    private String subInv;
    private String subToInv;

    public void setSubInventories(List s_subInventories) {
        List oldSubInventories = s_subInventories;
        s_subInventories = s_subInventories;
        propertyChangeSupport.firePropertyChange("subInventories", oldSubInventories, s_subInventories);
    }

    // public  List getSubInventories() {
    public void syncLocalDB() {
        String networkStatus =
            (String) AdfmfJavaUtilities.evaluateELExpression("#{deviceScope.hardware.networkStatus}");
        List collections;
        if (networkStatus.equals(NOT_REACHABLE)) {
            s_subInventories = super.getCollectionFromDB(SubinventoryBO.class);
        } else {
            System.out.println("Inside orgItem");
            Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
            String restURI = "/webservices/rest/DCOMLOV/getSubinv/";
            RestCallerUtil rcu = new RestCallerUtil();
            String payload =
                "{\n" + "\"Input_Parameters\":\n" + "{\n" +
                "   \"RESTHeader\": {\"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" +
                "                  \"RespApplication\": \"ONT\",\n" +
                "                  \"SecurityGroup\": \"STANDARD\",\n" +
                "                  \"NLSLanguage\": \"AMERICAN\",\n" + "                  \"Org_Id\": \"82\"\n" +
                "                 },\n" + "   \"InputParameters\": \n" +
                "      {\"PSUBINV\": \"\", \"PWAREHOUSE\": \"\"}\n" + "}\n" + "}";

            System.out.println("Calling create method");
            String jsonArrayAsString = rcu.invokeUPDATE(restURI, payload);
            System.out.println("Received response");
            if (jsonArrayAsString != null) {
                try {
                    JSONParser parser = new JSONParser();
                    Object object;

                    object = parser.parse(jsonArrayAsString);

                    JSONObject jsonObject = (JSONObject) object;
                    JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                    JSONObject jsObject1 = (JSONObject) jsObject.get("XSUBINV");
                    JSONArray array = (JSONArray) jsObject1.get("XSUBINV_ITEM");
                    if (array != null) {
                        int size = array.size();
                        //  ProductSearchEntity[] prodItems= new ProductSearchEntity[size];
                        for (int i = 0; i < size; i++) {


                            SubinventoryBO SubinventoryItems = new SubinventoryBO();
                            JSONObject jsObject2 = (JSONObject) array.get(i);

                            SubinventoryItems.setWhse((jsObject2.get("WHSE").toString()));
                            SubinventoryItems.setSubinv((jsObject2.get("SUBINV").toString()));
                            SubinventoryItems.setDescription((jsObject2.get("DESCRIPTION").toString()));
                            SubinventoryItems.setLocatorControl((jsObject2.get("LOCATORCONTROL").toString()));
                            SubinventoryItems.setLPNControl((jsObject2.get("LPNCONTROL").toString()));
                            SubinventoryItems.setDefLocator((jsObject2.get("DEFLOCATOR").toString()));
                            SubinventoryItems.setDefCostGrp((jsObject2.get("DEFCOSTGRP").toString()));
                            s_subInventories.add(SubinventoryItems);


                        }

                        super.updateSqlLiteTable(SubinventoryBO.class, s_subInventories);
                    }
                } catch (ParseException e) {
                    e.getMessage();
                }
            }
        }
        //  return s_subInventories;
    }
    private String descFilter = "";
    protected static List s_subInventories = new ArrayList();
    protected static List s_to_subInventories = new ArrayList();
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private static final String NOT_REACHABLE = "NotReachable"; // Indiates no network connectivity
    //SyncUtils syncUtils = new SyncUtils();
    public SubinventoryBO[] getSubinventories() {

        try {
            SubinventoryBO[] subInventories = null;
            /**Commented as data will be coming from local DB
                 * GenericVirtualType payload = new GenericVirtualType(null, "payload");
                payload.defineAttribute(null, "Whse", String.class, "100");
                HashMap paramsMap = new HashMap();
                paramsMap.put("resAttriName", "SubinvDetails");
                paramsMap.put("lovDCName", "SubinventoryLOV_WS");
                paramsMap.put("opeartionName", "process");
                paramsMap.put("payload", payload);
                s_subInventories = super.getCollection(SubinventoryBO.class, paramsMap);*/
          //  String refresh =
           //     (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.refreshFromSubinventory}");
            //if ("Y".equals(refresh))
                filtered_Subinventories = super.getOfflineCollection(SubinventoryBO.class);
            subInventories =
                (SubinventoryBO[]) filtered_Subinventories.toArray(new SubinventoryBO[filtered_Subinventories.size()]);

            ValueExpression ve =
                AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.FromSubinventory}", String.class);
            ve.setValue(AdfmfJavaUtilities.getAdfELContext(), subInventories[0].getSubinv());
            return subInventories;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SubinventoryBO[] getToSubinventories() {

        try {
            SubinventoryBO[] subInventories = null;
            /* GenericVirtualType payload = new GenericVirtualType(null, "payload");
            payload.defineAttribute(null, "Whse", String.class, "100");
            HashMap paramsMap = new HashMap();
            paramsMap.put("resAttriName", "SubinvDetails");
            paramsMap.put("lovDCName", "SubinventoryLOV_WS");
            paramsMap.put("opeartionName", "process");
            paramsMap.put("payload", payload);*/
          //  String refresh = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.refreshToSubinventory}");
           // if ("Y".equals(refresh))
                s_to_subInventories = super.getOfflineCollection(SubinventoryBO.class);
            subInventories =
                (SubinventoryBO[]) s_to_subInventories.toArray(new SubinventoryBO[s_to_subInventories.size()]);
            ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.ToSubinventory}", int.class);
            ve.setValue(AdfmfJavaUtilities.getAdfELContext(), subInventories[0].getSubinv());

            return subInventories;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SubinventoryBO[] getSubinventory() {
        String networkStatus =
            (String) AdfmfJavaUtilities.evaluateELExpression("#{deviceScope.hardware.networkStatus}");
        List collections;
        if (networkStatus.equals(NOT_REACHABLE)) {
            s_subInventories = super.getCollectionFromDB(SubinventoryBO.class);
        } else {
            System.out.println("Inside orgItem");
            Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
            String restURI = "/webservices/rest/DCOMLOV/getSubinventory/";
            RestCallerUtil rcu = new RestCallerUtil();
            String payload =
                "{\n" + "\"Input_Parameters\":\n" + "{\n" +
                "   \"RESTHeader\": {\"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" +
                "                  \"RespApplication\": \"ONT\",\n" +
                "                  \"SecurityGroup\": \"STANDARD\",\n" +
                "                  \"NLSLanguage\": \"AMERICAN\",\n" + "                  \"Org_Id\": \"82\"\n" +
                "                 },\n" + "   \"InputParameters\": \n" +
                "      {\"PSUBINV\": \"\", \"PWAREHOUSE\": \"\"}\n" + "}\n" + "}";

            System.out.println("Calling create method");
            String jsonArrayAsString = rcu.invokeUPDATE(restURI, payload);
            System.out.println("Received response");
            if (jsonArrayAsString != null) {
                try {
                    JSONParser parser = new JSONParser();
                    Object object;

                    object = parser.parse(jsonArrayAsString);

                    JSONObject jsonObject = (JSONObject) object;
                    JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                    JSONObject jsObject1 = (JSONObject) jsObject.get("XSUBINV");
                    JSONArray array = (JSONArray) jsObject1.get("XSUBINV_ITEM");
                    if (array != null) {
                        int size = array.size();
                        //  ProductSearchEntity[] prodItems= new ProductSearchEntity[size];
                        for (int i = 0; i < size; i++) {


                            SubinventoryBO SubinventoryItems = new SubinventoryBO();
                            JSONObject jsObject2 = (JSONObject) array.get(i);

                            SubinventoryItems.setWhse((jsObject2.get("WHSE").toString()));
                            SubinventoryItems.setSubinv((jsObject2.get("SUBINV").toString()));
                            SubinventoryItems.setDescription((jsObject2.get("DESCRIPTION").toString()));
                            SubinventoryItems.setLocatorControl((jsObject2.get("LOCATORCONTROL").toString()));
                            SubinventoryItems.setLPNControl((jsObject2.get("LPNCONTROL").toString()));
                            SubinventoryItems.setDefLocator((jsObject2.get("DEFLOCATOR").toString()));
                            SubinventoryItems.setDefCostGrp((jsObject2.get("DEFCOSTGRP").toString()));
                            s_subInventories.add(SubinventoryItems);


                        }

                        super.updateSqlLiteTable(SubinventoryBO.class, s_subInventories);
                    }
                } catch (ParseException e) {
                    e.getMessage();
                }
            }
        }
        SubinventoryBO[] SubinventoryArray =
            (SubinventoryBO[]) s_subInventories.toArray(new SubinventoryBO[s_subInventories.size()]);
        return SubinventoryArray;
    }

    public void setSubinvFilter(String subinvFilter) {
        String oldSubinvFilter = this.subinvFilter;
        this.subinvFilter = subinvFilter;
        propertyChangeSupport.firePropertyChange("subinvFilter", oldSubinvFilter, subinvFilter);
    }

    public String getSubinvFilter() {
        return subinvFilter;
    }

    public void setDescFilter(String descFilter) {
        String oldDescFilter = this.descFilter;
        this.descFilter = descFilter;
        propertyChangeSupport.firePropertyChange("descFilter", oldDescFilter, descFilter);
    }

    public String getDescFilter() {
        return descFilter;
    }

    public void filterSubinventories() {
        try {
            System.out.println("inside filter code");
            filtered_Subinventories.clear();

            HashMap filterFileds = new HashMap();
            filterFileds.put("subinv", getSubinvFilter());
            filterFileds.put("desc", getDescFilter());
            filterFileds.put("whse", "100");


            HashMap paramMap = new HashMap();
            paramMap.put("collection", s_subInventories);
            paramMap.put("filterFieldsValues", filterFileds);
            System.out.println("called super filtered class");

            filtered_Subinventories = (List) super.getFileteredCollection(SubinventoryBO.class, paramMap);
            System.out.println("collection size is " + filtered_Subinventories.size());
            providerChangeSupport.fireProviderRefresh("subinventories");
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
