package dcom.shop.application.mobile;

import java.util.HashMap;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.util.GenericVirtualType;

public class UOMBO implements Comparable {
        private String UOMCode;
        private String UOM;
        private GenericVirtualType Attributes;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public UOMBO() {
        super();
    }

    public void setBOClassRow(HashMap hashMap) {
        this.setUOMCode((String) hashMap.get("uomcode"));
        this.setUOM((String) hashMap.get("uom"));
    }

    public HashMap getBOClassRow(UOMBO uom) {
        HashMap map = new HashMap();
        map.put("uomcode", uom.getUOMCode());
        map.put("uom", uom.getUOM());
        return map;
    }

    public void setUOMCode(String UOMCode) {
        String oldUOMCode = this.UOMCode;
        this.UOMCode = UOMCode;
        propertyChangeSupport.firePropertyChange("UOMCode", oldUOMCode, UOMCode);
    }

    public String getUOMCode() {
        return UOMCode;
    }

    public void setUOM(String UOM) {
        String oldUOM = this.UOM;
        this.UOM = UOM;
        propertyChangeSupport.firePropertyChange("UOM", oldUOM, UOM);
    }

    public String getUOM() {
        return UOM;
    }

    public void setAttributes(GenericVirtualType Attributes) {
        GenericVirtualType oldAttributes = this.Attributes;
        this.Attributes = Attributes;
        propertyChangeSupport.firePropertyChange("Attributes", oldAttributes, Attributes);
    }

    public GenericVirtualType getAttributes() {
        return Attributes;
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
}
