package dcom.shop.application.mobile;

import dcom.shop.application.base.AEntity;

import java.util.HashMap;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.util.GenericVirtualType;

public class LocatorBO extends AEntity implements Comparable {
        private String Whse;
        private String Subinv;
        private String Locator;
        private String Alias;
        private String Description;
        private String LocatorType;
        private String XDIM;
        private String YDIM;
        private String ZDIM;
        private GenericVirtualType Attributes;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void setXDIM(String XDIM) {
        String oldXDIM = this.XDIM;
        this.XDIM = getAttributeValue(XDIM);
        propertyChangeSupport.firePropertyChange("XDIM", oldXDIM, getAttributeValue(XDIM));
    }

    public String getXDIM() {
        return XDIM;
    }

    public void setYDIM(String YDIM) {
        String oldYDIM = this.YDIM;
        this.YDIM = getAttributeValue(YDIM);
        propertyChangeSupport.firePropertyChange("YDIM", oldYDIM, getAttributeValue(YDIM));
    }

    public String getYDIM() {
        return YDIM;
    }

    public void setZDIM(String ZDIM) {
        String oldZDIM = this.ZDIM;
        this.ZDIM = ZDIM;
        propertyChangeSupport.firePropertyChange("ZDIM", oldZDIM, ZDIM);
    }

    public String getZDIM() {
        return ZDIM;
    }

    public void setWhse(String Whse) {
        String oldWhse = this.Whse;
        this.Whse = Whse;
        propertyChangeSupport.firePropertyChange("Whse", oldWhse, Whse);
    }

    public String getWhse() {
        return Whse;
    }

    public void setSubinv(String Subinv) {
        String oldSubinv = this.Subinv;
        this.Subinv = Subinv;
        propertyChangeSupport.firePropertyChange("Subinv", oldSubinv, Subinv);
    }

    public String getSubinv() {
        return Subinv;
    }

    public void setLocator(String Locator) {
        String oldLocator = this.Locator;
        this.Locator = Locator;
        propertyChangeSupport.firePropertyChange("Locator", oldLocator, Locator);
    }

    public String getLocator() {
        return Locator;
    }

    public void setAlias(String Alias) {
        String oldAlias = this.Alias;
        this.Alias = Alias;
        propertyChangeSupport.firePropertyChange("Alias", oldAlias, Alias);
    }

    public String getAlias() {
        return Alias;
    }

    public void setDescription(String Description) {
        String oldDescription = this.Description;
        this.Description = Description;
        propertyChangeSupport.firePropertyChange("Description", oldDescription, Description);
    }

    public String getDescription() {
        return Description;
    }

    public void setLocatorType(String LocatorType) {
        String oldLocatorType = this.LocatorType;
        this.LocatorType = LocatorType;
        propertyChangeSupport.firePropertyChange("LocatorType", oldLocatorType, LocatorType);
    }

    public String getLocatorType() {
        return LocatorType;
    }

    public void setAttributes(GenericVirtualType Attributes) {
        GenericVirtualType oldAttributes = this.Attributes;
        this.Attributes = Attributes;
        propertyChangeSupport.firePropertyChange("Attributes", oldAttributes, Attributes);
    }

    public GenericVirtualType getAttributes() {
        return Attributes;
    }

    public LocatorBO() {
        super();
    }

    public int compareTo(Object o) {
        // TODO Implement this method
        return 0;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
    
    public void setBOClassRow(HashMap hashMap) {
        this.setWhse((String) hashMap.get("whse"));
        this.setSubinv((String) hashMap.get("subinv"));
        this.setLocator((String) hashMap.get("locator"));
        this.setAlias((String) hashMap.get("alias"));
        this.setDescription((String) hashMap.get("description"));
        this.setLocatorType((String) hashMap.get("locatortype"));
        this.setXDIM((String) hashMap.get("xdim"));
        this.setYDIM((String) hashMap.get("ydim"));
        this.setZDIM((String) hashMap.get("zdim"));
    }

    public HashMap getBOClassRow(LocatorBO locators) {
        HashMap map = new HashMap();
        map.put("whse", locators.getWhse());
        map.put("subinv", locators.getSubinv());
        map.put("locator", locators.getLocator());
        map.put("alias", locators.getAlias());
        map.put("description", locators.getDescription());
        map.put("locatortype", locators.getLocatorType());
        map.put("xdim", locators.getXDIM());
        map.put("ydim", locators.getYDIM());
        map.put("zdim", locators.getZDIM());
        return map;
    }
}
