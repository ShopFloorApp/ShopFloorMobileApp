package dcom.shop.application.mobile.txn;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class ConcProgramParamLovBO {
    private String ref;
    private String value;
    private String name;
    private String meaning;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void setRef(String ref) {
        String oldRef = this.ref;
        this.ref = ref;
        propertyChangeSupport.firePropertyChange("ref", oldRef, ref);
    }

    public String getRef() {
        return ref;
    }

    public void setValue(String value) {
        String oldValue = this.value;
        this.value = value;
        propertyChangeSupport.firePropertyChange("value", oldValue, value);
    }

    public String getValue() {
        return value;
    }

    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        propertyChangeSupport.firePropertyChange("name", oldName, name);
    }

    public String getName() {
        return name;
    }

    public void setMeaning(String meaning) {
        String oldMeaning = this.meaning;
        this.meaning = meaning;
        propertyChangeSupport.firePropertyChange("meaning", oldMeaning, meaning);
    }

    public String getMeaning() {
        return meaning;
    }

    public ConcProgramParamLovBO() {
        super();
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
