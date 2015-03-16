package dcom.shop.application.mobile.dispatch;

import java.math.BigDecimal;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class Serials {
    private String fromSerial;
    private String toSerial;
    private BigDecimal serialQty;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public Serials() {
        super();
    }


    public void setFromSerial(String fromSerial) {
        String oldFromSerial = this.fromSerial;
        this.fromSerial = fromSerial;
        propertyChangeSupport.firePropertyChange("fromSerial", oldFromSerial, fromSerial);
    }

    public String getFromSerial() {
        return fromSerial;
    }

    public void setToSerial(String toSerial) {
        String oldToSerial = this.toSerial;
        this.toSerial = toSerial;
        propertyChangeSupport.firePropertyChange("toSerial", oldToSerial, toSerial);
    }

    public String getToSerial() {
        return toSerial;
    }

    public void setSerialQty(BigDecimal serialQty) {
        BigDecimal oldSerialQty = this.serialQty;
        this.serialQty = serialQty;
        propertyChangeSupport.firePropertyChange("serialQty", oldSerialQty, serialQty);
    }

    public BigDecimal getSerialQty() {
        return serialQty;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
