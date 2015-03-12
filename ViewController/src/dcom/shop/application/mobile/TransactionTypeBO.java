package dcom.shop.application.mobile;

import java.util.HashMap;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.util.GenericVirtualType;

public class TransactionTypeBO implements Comparable {
    private String TransactionType;
    private String Description;
    private GenericVirtualType Attributes;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public TransactionTypeBO() {
        super();
    }

    public void setBOClassRow(HashMap hashMap) {
        this.setTransactionType((String) hashMap.get("transactiontype"));
        this.setDescription((String) hashMap.get("description"));
    }

    public HashMap getBOClassRow(TransactionTypeBO transactionType) {
        HashMap map = new HashMap();
        map.put("transactiontype", transactionType.getTransactionType());
        map.put("description", transactionType.getDescription());
        return map;
    }

    public int compareTo(Object o) {
        // TODO Implement this method
        return 0;
    }

    public void setTransactionType(String TransactionType) {
        String oldTransactionType = this.TransactionType;
        this.TransactionType = TransactionType;
        propertyChangeSupport.firePropertyChange("TransactionType", oldTransactionType, TransactionType);
    }

    public String getTransactionType() {
        return TransactionType;
    }


    public void setDescription(String Description) {
        String oldDescription = this.Description;
        this.Description = Description;
        propertyChangeSupport.firePropertyChange("Description", oldDescription, Description);
    }

    public String getDescription() {
        return Description;
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
