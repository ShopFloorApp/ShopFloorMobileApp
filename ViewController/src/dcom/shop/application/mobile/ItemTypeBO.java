package dcom.shop.application.mobile;

import java.util.HashMap;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class ItemTypeBO {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public ItemTypeBO() {
        super();
    }

    public void setBOClassRow(HashMap hashMap) {
        this.setTypeCode((String) hashMap.get("typecode"));
        this.setMeaning((String) hashMap.get("meaning"));
    }

    public HashMap getBOClassRow(ItemTypeBO itemType) {
        HashMap map = new HashMap();
        map.put("typecode", itemType.getTypeCode());
        map.put("meaning", itemType.getMeaning());
        return map;
    }
    private String TypeCode;
    private String Meaning;

    public void setTypeCode(String TypeCode) {
        String oldTypeCode = this.TypeCode;
        this.TypeCode = TypeCode;
        propertyChangeSupport.firePropertyChange("TypeCode", oldTypeCode, TypeCode);
    }

    public String getTypeCode() {
        return TypeCode;
    }

    public void setMeaning(String Meaning) {
        String oldMeaning = this.Meaning;
        this.Meaning = Meaning;
        propertyChangeSupport.firePropertyChange("Meaning", oldMeaning, Meaning);
    }

    public String getMeaning() {
        return Meaning;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
