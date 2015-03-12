package dcom.shop.application.mobile;

import java.util.HashMap;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.util.GenericVirtualType;

public class AccountAliasBO implements Comparable {
    private String Whse;
    private String AccountAlias;
    private String Description;
    private String AccountSegment;
    private GenericVirtualType Attributes;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public AccountAliasBO() {
        super();
    }
    
    public void setBOClassRow(HashMap hashMap) {
        this.setWhse((String) hashMap.get("whse"));
        this.setAccountAlias((String) hashMap.get("accountalias"));
        this.setDescription((String) hashMap.get("desc"));
        this.setAccountSegment((String) hashMap.get("accountsegment"));
    }

    public HashMap getBOClassRow(AccountAliasBO accountAlias) {
        HashMap map = new HashMap();
        map.put("whse", accountAlias.getWhse());
        map.put("accountalias", accountAlias.getAccountAlias());
        map.put("desc", accountAlias.getDescription());
        map.put("accountsegment", accountAlias.getAccountSegment());
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

    public void setAccountAlias(String AccountAlias) {
        String oldAccountAlias = this.AccountAlias;
        this.AccountAlias = AccountAlias;
        propertyChangeSupport.firePropertyChange("AccountAlias", oldAccountAlias, AccountAlias);
    }

    public String getAccountAlias() {
        return AccountAlias;
    }


    public void setDescription(String Description) {
        String oldDescription = this.Description;
        this.Description = Description;
        propertyChangeSupport.firePropertyChange("Description", oldDescription, Description);
    }

    public String getDescription() {
        return Description;
    }

    public void setAccountSegment(String AccountSegment) {
        String oldAccountSegment = this.AccountSegment;
        this.AccountSegment = AccountSegment;
        propertyChangeSupport.firePropertyChange("AccountSegment", oldAccountSegment, AccountSegment);
    }

    public String getAccountSegment() {
        return AccountSegment;
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
