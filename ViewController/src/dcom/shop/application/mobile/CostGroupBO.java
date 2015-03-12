package dcom.shop.application.mobile;

import java.util.HashMap;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.util.GenericVirtualType;

public class CostGroupBO implements Comparable {
    private String Whse;
    private String CostGroup;
    private String Description;
    private GenericVirtualType Attributes;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public CostGroupBO() {
        super();
    }

    public void setBOClassRow(HashMap hashMap) {
        this.setWhse((String) hashMap.get("whse"));
        this.setCostGroup((String) hashMap.get("costgroup"));
        this.setDescription((String) hashMap.get("description"));
    }

    public HashMap getBOClassRow(CostGroupBO costGroup) {
        HashMap map = new HashMap();
        map.put("whse", costGroup.getWhse());
        map.put("costgroup", costGroup.getCostGroup());
        map.put("description", costGroup.getDescription());
        return map;
    }

    public int compareTo(Object o) {
        // TODO Implement this method
        return 0;
    }

    public void setWhse(String Whse) {
        String oldWhse = this.Whse;
        this.Whse = Whse;
        propertyChangeSupport.firePropertyChange("Whse", oldWhse, Whse);
    }

    public String getWhse() {
        return Whse;
    }

    public void setCostGroup(String CostGroup) {
        String oldCostGroup = this.CostGroup;
        this.CostGroup = CostGroup;
        propertyChangeSupport.firePropertyChange("CostGroup", oldCostGroup, CostGroup);
    }

    public String getCostGroup() {
        return CostGroup;
    }


    public void setDescription(String Description) {
        String oldDescription = this.Description;
        this.Description = Description;
        propertyChangeSupport.firePropertyChange("Description", oldDescription, Description);
    }

    public String getDescription() {
        return Description;
    }

    public void setPropertyChangeSupport(PropertyChangeSupport propertyChangeSupport) {
        PropertyChangeSupport oldPropertyChangeSupport = this.propertyChangeSupport;
        this.propertyChangeSupport = propertyChangeSupport;
        propertyChangeSupport.firePropertyChange("propertyChangeSupport", oldPropertyChangeSupport,
                                                 propertyChangeSupport);
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }

    public void setAttributes(GenericVirtualType Attributes) {
        GenericVirtualType oldAttributes = this.Attributes;
        this.Attributes = Attributes;
        propertyChangeSupport.firePropertyChange("Attributes", oldAttributes, Attributes);
    }

    public GenericVirtualType getAttributes() {
        return Attributes;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
