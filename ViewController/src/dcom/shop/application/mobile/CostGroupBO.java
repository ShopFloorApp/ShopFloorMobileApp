package dcom.shop.application.mobile;

import java.util.HashMap;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.util.GenericVirtualType;

public class CostGroupBO implements Comparable {
    private String Whse;
    private String CostGroup;
    private String Desc;
    private GenericVirtualType Attributes;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public CostGroupBO() {
        super();
    }    

    public void setBOClassRow(HashMap hashMap) {
        this.setWhse((String) hashMap.get("whse"));
        this.setCostGroup((String) hashMap.get("costgroup"));
        this.setDesc((String) hashMap.get("desc"));
    }

    public HashMap getBOClassRow(CostGroupBO costGroup) {
        HashMap map = new HashMap();
        map.put("whse", costGroup.getWhse());
        map.put("costgroup", costGroup.getCostGroup());
        map.put("desc", costGroup.getDesc());
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

    public void setDesc(String Desc) {
        String oldDesc = this.Desc;
        this.Desc = Desc;
        propertyChangeSupport.firePropertyChange("Desc", oldDesc, Desc);
    }

    public String getDesc() {
        return Desc;
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
