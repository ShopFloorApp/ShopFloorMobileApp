package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.CategorySetBO;

import dcom.shop.application.mobile.LocatorBO;
import dcom.shop.application.mobile.SubinventoryBO;

import dcom.shop.application.mobile.WarehouseBO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.GenericVirtualType;

public class SubinventoryDC extends SyncUtils {
    private List filtered_Subinventories = new ArrayList();
    private String subinvFilter = "";
    private String descFilter = "";
    protected static List s_subInventories = new ArrayList();
    protected static List s_to_subInventories = new ArrayList();
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);

    public SubinventoryDC() {
        /*try {
            GenericVirtualType payload = new GenericVirtualType(null, "payload");
            payload.defineAttribute(null, "Whse", String.class, "100");
            HashMap paramsMap = new HashMap();
            paramsMap.put("resAttriName", "SubinvDetails");
            paramsMap.put("lovDCName", "SubinventoryLOV_WS");
            paramsMap.put("opeartionName", "process");
            paramsMap.put("payload", payload);
            s_subInventories = super.getCollection(SubinventoryBO.class, paramsMap);
            filterSubinventories();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/
    }
    
    public void ProcessWS() {
        GenericVirtualType payload = new GenericVirtualType(null, "payload");
        payload.defineAttribute(null, "Whse", String.class, "100");
        HashMap paramsMap = new HashMap();
        paramsMap.put("resAttriName", "SubinvDetails");
        paramsMap.put("lovDCName", "SubinventoryLOV_WS");
        paramsMap.put("opeartionName", "process");
        paramsMap.put("payload", payload);
        s_subInventories = super.getCollection(SubinventoryBO.class, paramsMap);
        providerChangeSupport.fireProviderRefresh("subinventories");
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

    public SubinventoryBO[] getSubinventories() {

        try {
            SubinventoryBO[] subInventories = null;
            GenericVirtualType payload = new GenericVirtualType(null, "payload");
            payload.defineAttribute(null, "Whse", String.class, "100");
            HashMap paramsMap = new HashMap();
            paramsMap.put("resAttriName", "SubinvDetails");
            paramsMap.put("lovDCName", "SubinventoryLOV_WS");
            paramsMap.put("opeartionName", "process");
            paramsMap.put("payload", payload);
            s_subInventories = super.getCollection(SubinventoryBO.class, paramsMap);
            subInventories =
                (SubinventoryBO[]) s_subInventories.toArray(new SubinventoryBO[s_subInventories.size()]);
            return subInventories;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public SubinventoryBO[] getToSubinventories() {

        try {
            SubinventoryBO[] subInventories = null;
            GenericVirtualType payload = new GenericVirtualType(null, "payload");
            payload.defineAttribute(null, "Whse", String.class, "100");
            HashMap paramsMap = new HashMap();
            paramsMap.put("resAttriName", "SubinvDetails");
            paramsMap.put("lovDCName", "SubinventoryLOV_WS");
            paramsMap.put("opeartionName", "process");
            paramsMap.put("payload", payload);
            s_to_subInventories = super.getCollection(SubinventoryBO.class, paramsMap);
            subInventories =
                (SubinventoryBO[]) s_to_subInventories.toArray(new SubinventoryBO[s_to_subInventories.size()]);
            return subInventories;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void filterSubinventories() {
        try {
            System.out.println("inside filter code");
            filtered_Subinventories.clear();

            HashMap filterFileds = new HashMap();
            filterFileds.put("subinv", getSubinvFilter());
            filterFileds.put("desc", getDescFilter());


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
