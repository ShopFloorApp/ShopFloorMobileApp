package dcom.shop.application.mobile;

import java.util.HashMap;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.util.GenericVirtualType;

public class CountTypeBO implements Comparable {
    private String Whse;
    private String CountType;
    private String CountName;
    private String Desc;
    private GenericVirtualType Attributes;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public CountTypeBO() {
        super();
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

    public void setCountType(String CountType) {
        String oldCountType = this.CountType;
        this.CountType = CountType;
        propertyChangeSupport.firePropertyChange("CountType", oldCountType, CountType);
    }

    public String getCountType() {
        return CountType;
    }

    public void setCountName(String CountName) {
        String oldCountName = this.CountName;
        this.CountName = CountName;
        propertyChangeSupport.firePropertyChange("CountName", oldCountName, CountName);
    }

    public String getCountName() {
        return CountName;
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
    
    public void setBOClassRow(HashMap hashMap) {
        this.setWhse((String) hashMap.get("whse"));
        this.setCountType((String) hashMap.get("counttype"));
        this.setCountName((String) hashMap.get("countname"));
        this.setDesc((String) hashMap.get("desc"));
    }

    public HashMap getBOClassRow(CountTypeBO countType) {
        HashMap map = new HashMap();
        map.put("whse", countType.getWhse());
        map.put("counttype", countType.getCountType());
        map.put("countname", countType.getCountName());
        map.put("desc", countType.getDesc());
        return map;
    }
}
