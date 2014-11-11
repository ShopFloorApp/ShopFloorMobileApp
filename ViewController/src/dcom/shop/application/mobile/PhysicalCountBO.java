package dcom.shop.application.mobile;

import java.util.HashMap;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.util.GenericVirtualType;

public class PhysicalCountBO implements Comparable {
    
    private String CountName;
    private String TagNumber;
    private GenericVirtualType Attributes;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public PhysicalCountBO() {
        super();
    }
    public void setBOClassRow(HashMap hashMap) {
        this.setCountName((String) hashMap.get("countname"));
        this.setTagNumber((String) hashMap.get("tagnumber"));
    }

    public HashMap getBOClassRow(PhysicalCountBO physicalCount) {
        HashMap map = new HashMap();
        map.put("countname", physicalCount.getCountName());
        map.put("tagnumber", physicalCount.getTagNumber());
        return map;
    }

    public void setCountName(String CountName) {
        String oldCountName = this.CountName;
        this.CountName = CountName;
        propertyChangeSupport.firePropertyChange("CountName", oldCountName, CountName);
    }

    public String getCountName() {
        return CountName;
    }

    public void setTagNumber(String TagNumber) {
        String oldTagNumber = this.TagNumber;
        this.TagNumber = TagNumber;
        propertyChangeSupport.firePropertyChange("TagNumber", oldTagNumber, TagNumber);
    }

    public String getTagNumber() {
        return TagNumber;
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
