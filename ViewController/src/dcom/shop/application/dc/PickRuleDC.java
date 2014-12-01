package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.CarrierBO;

import dcom.shop.application.mobile.PickRuleBO;

import dcom.shop.application.mobile.TransactionReasonBO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.GenericVirtualType;

public class PickRuleDC extends SyncUtils {
    private List filtered_pickrule=new ArrayList();
    private String pickruleNameFilter = "";
    protected static List s_pickrule = new ArrayList();
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    
    public PickRuleDC() {
       
        GenericVirtualType payload = new GenericVirtualType(null, "payload");
        HashMap paramsMap=new HashMap();
        paramsMap.put("resAttriName","PickRuleDetails");
        paramsMap.put("lovDCName", "PickRuleLOV_WS");
        paramsMap.put("opeartionName", "process");
        paramsMap.put("payload",payload);
        s_pickrule = super.getCollection(PickRuleBO.class, paramsMap);
        filterpickrule();
        
    }
    
    public PickRuleBO[] getPickRules() {

        try {
            PickRuleBO[] pickRules = null;
             pickRules = (PickRuleBO[]) filtered_pickrule.toArray(new PickRuleBO[filtered_pickrule.size()]);
            return pickRules;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void filterpickrule() {
        try {
            System.out.println("inside filter code");
            filtered_pickrule.clear();

            HashMap filterFileds = new HashMap();
            filterFileds.put("rulename", getPickruleNameFilter());
          

            HashMap paramMap = new HashMap();
            paramMap.put("collection", s_pickrule);
            paramMap.put("filterFieldsValues", filterFileds);
            System.out.println("called super filtered class");
            
            filtered_pickrule = (List)super.getFileteredCollection(PickRuleBO.class, paramMap);
            System.out.println("collection size is " + filtered_pickrule.size());
            providerChangeSupport.fireProviderRefresh("pickRules");
        } catch (Exception e) {
            throw new RuntimeException("My Code Error " + e);
        }
    }


    public void setPickruleNameFilter(String pickruleNameFilter) {
        String oldPickruleNameFilter = this.pickruleNameFilter;
        this.pickruleNameFilter = pickruleNameFilter;
        propertyChangeSupport.firePropertyChange("pickruleNameFilter", oldPickruleNameFilter, pickruleNameFilter);
    }

    public String getPickruleNameFilter() {
        return pickruleNameFilter;
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
