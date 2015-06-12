package dcom.shop.application.mobile.transaction.receiving;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class SalesOrderLineBO {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public SalesOrderLineBO() {
        super();
    }
    
    String LINENUM;
    String ITEM;
    String LOTCONTROL;
    String SERIALCONTROL;
    String LINEQTY;
    String UOM;

    public void setORDERNUM(String ORDERNUM) {
        String oldORDERNUM = this.ORDERNUM;
        this.ORDERNUM = ORDERNUM;
        propertyChangeSupport.firePropertyChange("ORDERNUM", oldORDERNUM, ORDERNUM);
    }

    public String getORDERNUM() {
        return ORDERNUM;
    }
    String ORDERNUM;


    public void setLINENUM(String LINENUM) {
        this.LINENUM = LINENUM;
    }

    public String getLINENUM() {
        return LINENUM;
    }

    public void setITEM(String ITEM) {
        this.ITEM = ITEM;
    }

    public String getITEM() {
        return ITEM;
    }

    public void setLOTCONTROL(String LOTCONTROL) {
        this.LOTCONTROL = LOTCONTROL;
    }

    public String getLOTCONTROL() {
        return LOTCONTROL;
    }

    public void setSERIALCONTROL(String SERIALCONTROL) {
        this.SERIALCONTROL = SERIALCONTROL;
    }

    public String getSERIALCONTROL() {
        return SERIALCONTROL;
    }

    public void setLINEQTY(String LINEQTY) {
        this.LINEQTY = LINEQTY;
    }

    public String getLINEQTY() {
        return LINEQTY;
    }

    public void setUOM(String UOM) {
        this.UOM = UOM;
    }

    public String getUOM() {
        return UOM;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
