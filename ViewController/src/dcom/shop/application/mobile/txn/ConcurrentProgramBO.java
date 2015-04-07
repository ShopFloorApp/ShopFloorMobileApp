package dcom.shop.application.mobile.txn;

import dcom.shop.application.mobile.WarehouseBO;

import java.util.HashMap;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class ConcurrentProgramBO  {
    private String shortName;
    private String name;
    private String applCode;
    private String applName;
    private String hasParam;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);


    public void setBOClassRow(HashMap hashMap) {
        this.setShortName((String) hashMap.get("shortname"));
        this.setName((String) hashMap.get("name"));
        this.setApplCode((String) hashMap.get("applcode"));
        this.setApplName((String) hashMap.get("applname"));
        this.setHasParam((String) hashMap.get("hasparam"));
    }

    public HashMap getBOClassRow(ConcurrentProgramBO concurrentProgram) {
        HashMap map = new HashMap();
        map.put("shortname", concurrentProgram.getShortName());
        map.put("name", concurrentProgram.getName());
        map.put("applcode", concurrentProgram.getApplCode());
        map.put("applname", concurrentProgram.getApplName());
        map.put("hasparam", concurrentProgram.getHasParam());
        return map;
    }

    public void setShortName(String shortName) {
        String oldShortName = this.shortName;
        this.shortName = shortName;
        propertyChangeSupport.firePropertyChange("shortName", oldShortName, shortName);
    }

    public String getShortName() {
        return shortName;
    }

    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        propertyChangeSupport.firePropertyChange("name", oldName, name);
    }

    public String getName() {
        return name;
    }

    public void setApplCode(String applCode) {
        String oldApplCode = this.applCode;
        this.applCode = applCode;
        propertyChangeSupport.firePropertyChange("applCode", oldApplCode, applCode);
    }

    public String getApplCode() {
        return applCode;
    }

    public void setApplName(String applName) {
        String oldApplName = this.applName;
        this.applName = applName;
        propertyChangeSupport.firePropertyChange("applName", oldApplName, applName);
    }

    public String getApplName() {
        return applName;
    }

    public void setHasParam(String hasParam) {
        String oldHasParam = this.hasParam;
        this.hasParam = hasParam;
        propertyChangeSupport.firePropertyChange("hasParam", oldHasParam, hasParam);
    }

    public String getHasParam() {
        return hasParam;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
