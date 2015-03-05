package dcom.shop.Inquiry;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class ProductSearchAttribEntity {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public ProductSearchAttribEntity() {
        super();
    }
    private String NAME;

    public void setNAME(String NAME) {
        String oldNAME = this.NAME;
        this.NAME = NAME;
        propertyChangeSupport.firePropertyChange("NAME", oldNAME, NAME);
    }

    public String getNAME() {
        return NAME;
    }

    public void setVALUE(String VALUE) {
        String oldVALUE = this.VALUE;
        this.VALUE = VALUE;
        propertyChangeSupport.firePropertyChange("VALUE", oldVALUE, VALUE);
    }

    public String getVALUE() {
        return VALUE;
    }
    private String VALUE;

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
