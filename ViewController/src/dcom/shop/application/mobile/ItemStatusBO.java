package dcom.shop.application.mobile;

import java.util.HashMap;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class ItemStatusBO {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void setBOClassRow(HashMap hashMap) {
        this.setStatus((String) hashMap.get("status"));
    }

    public HashMap getBOClassRow(ItemStatusBO itemStatus) {
        HashMap map = new HashMap();
        map.put("status", itemStatus.getStatus());
        return map;
    }

    public void setStatus(String Status) {
        String oldStatus = this.Status;
        this.Status = Status;
        propertyChangeSupport.firePropertyChange("Status", oldStatus, Status);
    }

    public String getStatus() {
        return Status;
    }

    public ItemStatusBO() {
        super();
    }
    private String Status;

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
