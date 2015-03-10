package dcom.shop.application.mobile.txn;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class ConcProgramParamValueBO {
    private String seq;
    private String ref;
    private String value;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void setSeq(String seq) {
        String oldSeq = this.seq;
        this.seq = seq;
        propertyChangeSupport.firePropertyChange("seq", oldSeq, seq);
    }

    public String getSeq() {
        return seq;
    }

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

    public ConcProgramParamValueBO() {
        super();
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
