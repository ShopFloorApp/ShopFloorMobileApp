package dcom.shop.application.mobile;

import dcom.shop.application.base.AEntity;

import java.util.HashMap;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.util.GenericVirtualType;

public class SubinventoryBO extends AEntity implements Comparable {
    private String Whse;
    private String Subinv;
    private String Description;
    private String LocatorControl;
    private String LPNControl;
    private String DefLocator;
    private String DefCostGrp;
    private String XDIM;
    private String YDIM;
    private String ZDIM;
    private GenericVirtualType Attributes;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void setWhse(String Whse) {
        String oldWhse = this.Whse;
        this.Whse = Whse;
        propertyChangeSupport.firePropertyChange("Whse", oldWhse, Whse);
    }

    public void setXDIM(String XDIM) {
        String oldXDIM = this.XDIM;
        this.XDIM = XDIM;
        propertyChangeSupport.firePropertyChange("XDIM", oldXDIM, XDIM);
    }

    public String getXDIM() {
        return XDIM;
    }

    public void setYDIM(String YDIM) {
        String oldYDIM = this.YDIM;
        this.YDIM = YDIM;
        propertyChangeSupport.firePropertyChange("YDIM", oldYDIM, YDIM);
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

    public void setDescription(String Description) {
        String oldDescription = this.Description;
        this.Description = Description;
        propertyChangeSupport.firePropertyChange("Description", oldDescription, Description);
    }

    public String getDescription() {
        return Description;
    }

    public void setLocatorControl(String LocatorControl) {
        String oldLocatorControl = this.LocatorControl;
        this.LocatorControl = LocatorControl;
        propertyChangeSupport.firePropertyChange("LocatorControl", oldLocatorControl, LocatorControl);
    }

    public String getLocatorControl() {
        return LocatorControl;
    }

    public void setLPNControl(String LPNControl) {
        String oldLPNControl = this.LPNControl;
        this.LPNControl = LPNControl;
        propertyChangeSupport.firePropertyChange("LPNControl", oldLPNControl, LPNControl);
    }

    public String getLPNControl() {
        return LPNControl;
    }

    public void setDefLocator(String DefLocator) {
        String oldDefLocator = this.DefLocator;
        this.DefLocator = DefLocator;
        propertyChangeSupport.firePropertyChange("DefLocator", oldDefLocator, DefLocator);
    }

    public String getDefLocator() {
        return DefLocator;
    }

    public void setDefCostGrp(String DefCostGrp) {
        String oldDefCostGrp = this.DefCostGrp;
        this.DefCostGrp = DefCostGrp;
        propertyChangeSupport.firePropertyChange("DefCostGrp", oldDefCostGrp, DefCostGrp);
    }

    public String getDefCostGrp() {
        return DefCostGrp;
    }

    public void setAttributes(GenericVirtualType Attributes) {
        GenericVirtualType oldAttributes = this.Attributes;
        this.Attributes = Attributes;
        propertyChangeSupport.firePropertyChange("Attributes", oldAttributes, Attributes);
    }

    public GenericVirtualType getAttributes() {
        return Attributes;
    }

    public SubinventoryBO() {
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
        this.setDescription((String) hashMap.get("description"));
        this.setLocatorControl((String) hashMap.get("locatorcontrol"));
        this.setLPNControl((String) hashMap.get("lpncontrol"));
        this.setDefLocator((String) hashMap.get("deflocator"));
        this.setDefCostGrp((String) hashMap.get("defcostgrp"));
        this.setXDIM((String) hashMap.get("xdim"));
        this.setYDIM((String) hashMap.get("ydim"));
        this.setZDIM((String) hashMap.get("zdim"));
    }

    public HashMap getBOClassRow(SubinventoryBO subInventories) {
        HashMap map = new HashMap();
        map.put("whse", subInventories.getWhse());
        map.put("subinv", subInventories.getSubinv());
        map.put("description", subInventories.getDescription());
        map.put("locatorcontrol", subInventories.getLocatorControl());
        map.put("lpncontrol", subInventories.getLPNControl());
        map.put("deflocator", subInventories.getDefLocator());
        map.put("defcostgrp", subInventories.getDefCostGrp());
        map.put("xdim", subInventories.getXDIM());
        map.put("ydim", subInventories.getYDIM());
        map.put("zdim", subInventories.getZDIM());
        return map;
    }
}
