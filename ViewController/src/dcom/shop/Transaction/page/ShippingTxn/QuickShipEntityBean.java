package dcom.shop.Transaction.page.ShippingTxn;

import dcom.shop.application.base.AEntity;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class QuickShipEntityBean extends AEntity {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public QuickShipEntityBean() {
        super();
    }
    private String XSTATUS;

    public void setXSTATUS(String XSTATUS) {
        XSTATUS=getAttributeValue(XSTATUS);
        String oldXSTATUS = this.XSTATUS;
        this.XSTATUS = XSTATUS;
        propertyChangeSupport.firePropertyChange("XSTATUS", oldXSTATUS, XSTATUS);
    }

    public String getXSTATUS() {
        return XSTATUS;
    }

    public void setXMSG(String XMSG) {
        XMSG=getAttributeValue(XMSG);
        String oldXMSG = this.XMSG;
        this.XMSG = XMSG;
        propertyChangeSupport.firePropertyChange("XMSG", oldXMSG, XMSG);
    }

    public String getXMSG() {
        return XMSG;
    }
    private String XMSG;

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
