package dcom.shop.application.dc;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class DeliveryLovEntity {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public DeliveryLovEntity() {
        super();
    }
    private String DLVNAME;

    public void setDLVNAME(String DLVNAME) {
        String oldDLVNAME = this.DLVNAME;
        this.DLVNAME = DLVNAME;
        propertyChangeSupport.firePropertyChange("DLVNAME", oldDLVNAME, DLVNAME);
    }

    public String getDLVNAME() {
        return DLVNAME;
    }

    public void setDLVID(int DLVID) {
        int oldDLVID = this.DLVID;
        this.DLVID = DLVID;
        propertyChangeSupport.firePropertyChange("DLVID", oldDLVID, DLVID);
    }

    public int getDLVID() {
        return DLVID;
    }

    public void setGROSSWEIGHT(int GROSSWEIGHT) {
        int oldGROSSWEIGHT = this.GROSSWEIGHT;
        this.GROSSWEIGHT = GROSSWEIGHT;
        propertyChangeSupport.firePropertyChange("GROSSWEIGHT", oldGROSSWEIGHT, GROSSWEIGHT);
    }

    public int getGROSSWEIGHT() {
        return GROSSWEIGHT;
    }

    public void setWEIGHTUOM(String WEIGHTUOM) {
        String oldWEIGHTUOM = this.WEIGHTUOM;
        this.WEIGHTUOM = WEIGHTUOM;
        propertyChangeSupport.firePropertyChange("WEIGHTUOM", oldWEIGHTUOM, WEIGHTUOM);
    }

    public String getWEIGHTUOM() {
        return WEIGHTUOM;
    }

    public void setWAYBILL(String WAYBILL) {
        String oldWAYBILL = this.WAYBILL;
        this.WAYBILL = WAYBILL;
        propertyChangeSupport.firePropertyChange("WAYBILL", oldWAYBILL, WAYBILL);
    }

    public String getWAYBILL() {
        return WAYBILL;
    }

    public void setSHIPMETHODCODE(String SHIPMETHODCODE) {
        String oldSHIPMETHODCODE = this.SHIPMETHODCODE;
        this.SHIPMETHODCODE = SHIPMETHODCODE;
        propertyChangeSupport.firePropertyChange("SHIPMETHODCODE", oldSHIPMETHODCODE, SHIPMETHODCODE);
    }

    public String getSHIPMETHODCODE() {
        return SHIPMETHODCODE;
    }

    private int DLVID;
    private int GROSSWEIGHT;
    private String WEIGHTUOM;
    private String WAYBILL;
    private String SHIPMETHODCODE;

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
