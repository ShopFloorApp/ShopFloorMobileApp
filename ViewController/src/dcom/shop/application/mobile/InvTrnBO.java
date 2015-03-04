package dcom.shop.application.mobile;

import java.util.Date;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.util.GenericVirtualType;

public class InvTrnBO {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public InvTrnBO() {
        super();
    }
    private String XSTATUS;
    private String XMSG;

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

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
