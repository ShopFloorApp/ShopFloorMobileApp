package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.LocatorBO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.GenericVirtualType;

public class LocatorDC extends SyncUtils {
    private List filtered_Locators=new ArrayList();
    private String locatorFilter = "";
    private String aliasFilter = "";
    protected static List s_locator = new ArrayList();
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);

    
    public LocatorDC() {
        try{
        GenericVirtualType payload = new GenericVirtualType(null, "payload");
        payload.defineAttribute(null, "Whse", String.class, "100");
        payload.defineAttribute(null, "Subinv", String.class, "RAW");
        HashMap paramsMap=new HashMap();
        paramsMap.put("resAttriName","LocatorDetails");
        paramsMap.put("lovDCName", "LocatorLOV_WS");
        paramsMap.put("opeartionName", "process");
        paramsMap.put("payload",payload);
        filtered_Locators = super.getCollection(LocatorBO.class, paramsMap);
            filterLocators();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
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
            locators = (LocatorBO[]) filtered_Locators.toArray(new LocatorBO[filtered_Locators.size()]);
            return locators;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public void filterLocators() {
        try {
            System.out.println("inside filter code");
            filtered_Locators.clear();

            HashMap filterFileds = new HashMap();
            filterFileds.put("locator", getLocatorFilter());
            filterFileds.put("alias", getAliasFilter());
            

            HashMap paramMap = new HashMap();
            paramMap.put("collection", s_locator);
            paramMap.put("filterFieldsValues", filterFileds);
            System.out.println("called super filtered class");
            
            filtered_Locators = (List)super.getFileteredCollection(LocatorBO.class, paramMap);
            System.out.println("collection size is " + filtered_Locators.size());
            providerChangeSupport.fireProviderRefresh("locators");
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
