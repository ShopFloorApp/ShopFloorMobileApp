package dcom.shop.Transaction.page.ShippingTxn;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class UnloadTruckEntityBean {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public UnloadTruckEntityBean() {
        super();
    }
    private String XSTATUS;

    public void setXSTATUS(String XSTATUS) {
        String oldXSTATUS = this.XSTATUS;
        this.XSTATUS = XSTATUS;
        propertyChangeSupport.firePropertyChange("XSTATUS", oldXSTATUS, XSTATUS);
    }

    public String getXSTATUS() {
        return XSTATUS;
    }

    public void setXMSG(String XMSG) {
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
