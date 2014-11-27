package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.CategorySetBO;
import dcom.shop.application.mobile.CostGroupBO;
import dcom.shop.application.mobile.UOMBO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.adfmf.util.GenericVirtualType;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.GenericVirtualType;
import oracle.adfmf.util.GenericVirtualType;

public class CostGroupDC extends SyncUtils {
    private List filtered_CostGroups=new ArrayList();
    private String costGroupsFilter = "";
    protected static List s_costGroups = new ArrayList();
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);      
    public CostGroupDC() {
        GenericVirtualType payload = new GenericVirtualType(null, "payload");
        HashMap paramsMap=new HashMap();
        paramsMap.put("resAttriName","CostGroupDetails");
        paramsMap.put("lovDCName", "CostGroupLOV_WS");
        paramsMap.put("opeartionName", "process");
        paramsMap.put("payload",payload);
        s_costGroups = super.getCollection(CostGroupBO.class, paramsMap);
        filterCostGroups();
    }

    public void setCostGroupsFilter(String costGroupsFilter) {
        String oldCostGroupsFilter = this.costGroupsFilter;
        this.costGroupsFilter = costGroupsFilter;
        propertyChangeSupport.firePropertyChange("costGroupsFilter", oldCostGroupsFilter, costGroupsFilter);
    }

    public String getCostGroupsFilter() {
        return costGroupsFilter;
    }

    public CostGroupBO[] getCostGroups() {

        try {
            CostGroupBO[] costGroups = null;                        
            costGroups = (CostGroupBO[]) filtered_CostGroups.toArray(new CostGroupBO[filtered_CostGroups.size()]);
            return costGroups;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public void filterCostGroups() {
    try{
        filtered_CostGroups.clear();
        
        HashMap filterFileds = new HashMap();
        filterFileds.put("costgroup",getCostGroupsFilter());
        HashMap paramMap = new HashMap();
       
        paramMap.put("collection", s_costGroups);
        paramMap.put("filterFieldsValues", filterFileds);
        System.out.println("called super filtered class");
        
        filtered_CostGroups = (List)super.getFileteredCollection(CostGroupBO.class, paramMap);
        System.out.println("collection size is " + filtered_CostGroups.size());
        providerChangeSupport.fireProviderRefresh("costGroups");        
    }catch (Exception e) {
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
