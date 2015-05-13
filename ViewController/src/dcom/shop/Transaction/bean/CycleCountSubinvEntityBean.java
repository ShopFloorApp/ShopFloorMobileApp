package dcom.shop.Transaction.bean;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class CycleCountSubinvEntityBean {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public CycleCountSubinvEntityBean() {
        super();
    }
    private String X_STATUS;

    public void setX_STATUS(String X_STATUS) {
        String oldX_STATUS = this.X_STATUS;
        this.X_STATUS = X_STATUS;
        propertyChangeSupport.firePropertyChange("X_STATUS", oldX_STATUS, X_STATUS);
    }

    public String getX_STATUS() {
        return X_STATUS;
    }

    public void setX_MSG(String X_MSG) {
        String oldX_MSG = this.X_MSG;
        this.X_MSG = X_MSG;
        propertyChangeSupport.firePropertyChange("X_MSG", oldX_MSG, X_MSG);
    }

    public String getX_MSG() {
        return X_MSG;
    }

    public void setX_CC_TYPE(String X_CC_TYPE) {
        String oldX_CC_TYPE = this.X_CC_TYPE;
        this.X_CC_TYPE = X_CC_TYPE;
        propertyChangeSupport.firePropertyChange("X_CC_TYPE", oldX_CC_TYPE, X_CC_TYPE);
    }

    public String getX_CC_TYPE() {
        return X_CC_TYPE;
    }

    public void setSUBINVENTORY(String SUBINVENTORY) {
        String oldSUBINVENTORY = this.SUBINVENTORY;
        this.SUBINVENTORY = SUBINVENTORY;
        propertyChangeSupport.firePropertyChange("SUBINVENTORY", oldSUBINVENTORY, SUBINVENTORY);
    }

    public String getSUBINVENTORY() {
        return SUBINVENTORY;
    }
    private String X_MSG;
    private String X_CC_TYPE;
    private String SUBINVENTORY;

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
