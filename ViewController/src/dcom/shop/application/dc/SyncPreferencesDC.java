package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;

import dcom.shop.application.mobile.SyncPreferencesBO;
import dcom.shop.application.mobile.WarehouseBO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.GenericVirtualType;

public class SyncPreferencesDC extends SyncUtils {
    private List filtered_syncLovs=new ArrayList();
    private String lovnameFilter = "";
    protected static List s_syncLovs = new ArrayList();
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    
    public SyncPreferencesDC() {
        try {          
            s_syncLovs = super.getOfflineCollection(SyncPreferencesBO.class);
            filterSyncLovs();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setLovnameFilter(String lovnameFilter) {
        String oldLovnameFilter = this.lovnameFilter;
        this.lovnameFilter = lovnameFilter;
        propertyChangeSupport.firePropertyChange("lovnameFilter", oldLovnameFilter, lovnameFilter);
    }

    public String getLovnameFilter() {
        return lovnameFilter;
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
    
    public SyncPreferencesBO[] getSyncLovs() {

        try {
            System.out.println("called collection warehouse");
            SyncPreferencesBO[] synclovs = null;
            synclovs = (SyncPreferencesBO[]) filtered_syncLovs.toArray(new SyncPreferencesBO[filtered_syncLovs.size()]);
            return synclovs;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void filterSyncLovs() {
        try {
            System.out.println("inside filter code");
            filtered_syncLovs.clear();
            
            HashMap filterFileds = new HashMap();
            filterFileds.put("lovname", getLovnameFilter());
            

            HashMap paramMap = new HashMap();
            paramMap.put("collection", s_syncLovs);
            paramMap.put("filterFieldsValues", filterFileds);
            System.out.println("called super filtered class");
            
            filtered_syncLovs = (List)super.getFileteredCollection(SyncPreferencesBO.class, paramMap);
            System.out.println("collection size is " + filtered_syncLovs.size());
            providerChangeSupport.fireProviderRefresh("syncLovs");
        } catch (Exception e) {
            throw new RuntimeException("My Code Error " + e);
        }
    }
}
