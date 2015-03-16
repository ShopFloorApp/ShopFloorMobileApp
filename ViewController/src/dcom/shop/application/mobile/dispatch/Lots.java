package dcom.shop.application.mobile.dispatch;

import java.math.BigDecimal;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class Lots {
    private String lot;
    private BigDecimal lotQty;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public Lots() {
        super();
    }

    public void setLot(String lot) {
        String oldLot = this.lot;
        this.lot = lot;
        propertyChangeSupport.firePropertyChange("lot", oldLot, lot);
    }

    public String getLot() {
        return lot;
    }

    public void setLotQty(BigDecimal lotQty) {
        BigDecimal oldLotQty = this.lotQty;
        this.lotQty = lotQty;
        propertyChangeSupport.firePropertyChange("lotQty", oldLotQty, lotQty);
    }

    public BigDecimal getLotQty() {
        return lotQty;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
