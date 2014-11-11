package dcom.shop.application.mobile;

import java.util.HashMap;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.util.GenericVirtualType;

public class TransactionReasonBO implements Comparable {
    private String ReasonName;
    private String Desc;
    private GenericVirtualType Attributes;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public TransactionReasonBO() {
        super();
    }
    public void setBOClassRow(HashMap hashMap) {
        this.setReasonName((String) hashMap.get("reasonname"));
        this.setDesc((String) hashMap.get("desc"));
    }

    public HashMap getBOClassRow(TransactionReasonBO transactionReason) {
        HashMap map = new HashMap();
        map.put("reasonname", transactionReason.getReasonName());
        map.put("desc", transactionReason.getDesc());
        return map;
    }

    public void setReasonName(String ReasonName) {
        String oldReasonName = this.ReasonName;
        this.ReasonName = ReasonName;
        propertyChangeSupport.firePropertyChange("ReasonName", oldReasonName, ReasonName);
    }

    public String getReasonName() {
        return ReasonName;
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
