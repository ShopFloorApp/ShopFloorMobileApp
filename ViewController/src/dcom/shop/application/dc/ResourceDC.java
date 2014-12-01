package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.CarrierBO;

import dcom.shop.application.mobile.CategorySetBO;
import dcom.shop.application.mobile.ResourceBO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.GenericVirtualType;

public class ResourceDC extends SyncUtils {
    private List filtered_Resources =new ArrayList();    
    private String resourceCodeFilter = "";
    private String descFilter = "";
    protected static List s_resources = new ArrayList();
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);  
    
    public ResourceDC() {
        GenericVirtualType payload = new GenericVirtualType(null, "payload");
        HashMap paramsMap=new HashMap();
        paramsMap.put("resAttriName","ResourceDetails");
        paramsMap.put("lovDCName", "ResourceLOV_WS");
        paramsMap.put("opeartionName", "process");
        paramsMap.put("payload",payload);
        s_resources = super.getCollection(ResourceBO.class, paramsMap);
        filterResources();
    }
    
    public ResourceBO[] getResources() {

        try {
            ResourceBO[] resources = null;

            resources = (ResourceBO[]) filtered_Resources.toArray(new ResourceBO[filtered_Resources.size()]);
            return resources;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setResourceCodeFilter(String resourceCodeFilter) {
        String oldResourceCodeFilter = this.resourceCodeFilter;
        this.resourceCodeFilter = resourceCodeFilter;
        propertyChangeSupport.firePropertyChange("resourceCodeFilter", oldResourceCodeFilter, resourceCodeFilter);
    }

    public String getResourceCodeFilter() {
        return resourceCodeFilter;
    }

    public void setDescFilter(String descFilter) {
        String oldDescFilter = this.descFilter;
        this.descFilter = descFilter;
        propertyChangeSupport.firePropertyChange("descFilter", oldDescFilter, descFilter);
    }

    public String getDescFilter() {
        return descFilter;
    }

    public void filterResources() {
        try{
                filtered_Resources.clear();
                            HashMap filterFileds = new HashMap();
                            filterFileds.put("resourcecode", getResourceCodeFilter());
                            filterFileds.put("desc", getDescFilter());
                            
                            HashMap paramMap = new HashMap();
                            paramMap.put("collection", s_resources);
                            paramMap.put("filterFieldsValues", filterFileds);
                            System.out.println("called super filtered class");
                            
                            filtered_Resources = (List)super.getFileteredCollection(ResourceBO.class, paramMap);
                            System.out.println("collection size is " + filtered_Resources.size());
                            providerChangeSupport.fireProviderRefresh("resources");                        
            
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
