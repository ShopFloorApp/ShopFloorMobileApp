package dcom.shop.Inquiry.lpn;

import dcom.shop.application.base.AEntity;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;


public class LpnSearchEntity  extends AEntity  {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public LpnSearchEntity() {
        super();
    }
    private String ORGCODE;

    public void setORGCODE(String ORGCODE) {
        String oldORGCODE = this.ORGCODE;
        this.ORGCODE = ORGCODE;
        propertyChangeSupport.firePropertyChange("ORGCODE", oldORGCODE, ORGCODE);
    }

    public String getORGCODE() {
        return ORGCODE;
    }

    public void setPARENTLPN(String PARENTLPN) {
        PARENTLPN=getAttributeValue(PARENTLPN);
        String oldPARENTLPN = this.PARENTLPN;
        this.PARENTLPN = PARENTLPN;
        propertyChangeSupport.firePropertyChange("PARENTLPN", oldPARENTLPN, PARENTLPN);
    }

    public String getPARENTLPN() {
        return PARENTLPN;
    }

    public void setLPN(String LPN) {
        String oldLPN = this.LPN;
        this.LPN = LPN;
        propertyChangeSupport.firePropertyChange("LPN", oldLPN, LPN);
    }

    public String getLPN() {
        return LPN;
    }

    public void setLPNSTATUS(String LPNSTATUS) {
        String oldLPNSTATUS = this.LPNSTATUS;
        this.LPNSTATUS = LPNSTATUS;
        propertyChangeSupport.firePropertyChange("LPNSTATUS", oldLPNSTATUS, LPNSTATUS);
    }

    public String getLPNSTATUS() {
        return LPNSTATUS;
    }

    public void setSUBINV(String SUBINV) {
        SUBINV=getAttributeValue(SUBINV);
        String oldSUBINV = this.SUBINV;
        this.SUBINV = SUBINV;
        propertyChangeSupport.firePropertyChange("SUBINV", oldSUBINV, SUBINV);
    }

    public String getSUBINV() {
        return SUBINV;
    }

    public void setLOCATOR(String LOCATOR) {
        LOCATOR=getAttributeValue(LOCATOR);
        String oldLOCATOR = this.LOCATOR;
        this.LOCATOR = LOCATOR;
        propertyChangeSupport.firePropertyChange("LOCATOR", oldLOCATOR, LOCATOR);
    }

    public String getLOCATOR() {
        return LOCATOR;
    }

    public void setITEM(String ITEM) {
        String oldITEM = this.ITEM;
        this.ITEM = ITEM;
        propertyChangeSupport.firePropertyChange("ITEM", oldITEM, ITEM);
    }

    public String getITEM() {
        return ITEM;
    }

    public void setITEMDESC(String ITEMDESC) {
        String oldITEMDESC = this.ITEMDESC;
        this.ITEMDESC = ITEMDESC;
        propertyChangeSupport.firePropertyChange("ITEMDESC", oldITEMDESC, ITEMDESC);
    }

    public String getITEMDESC() {
        return ITEMDESC;
    }

    public void setUOM(String UOM) {
        String oldUOM = this.UOM;
        this.UOM = UOM;
        propertyChangeSupport.firePropertyChange("UOM", oldUOM, UOM);
    }

    public String getUOM() {
        return UOM;
    }

    public void setONHANDQTY(String ONHANDQTY) {
        String oldONHANDQTY = this.ONHANDQTY;
        this.ONHANDQTY = ONHANDQTY;
        propertyChangeSupport.firePropertyChange("ONHANDQTY", oldONHANDQTY, ONHANDQTY);
    }

    public String getONHANDQTY() {
        return ONHANDQTY;
    }

    public void setAVAILABLEQTY(String AVAILABLEQTY) {
        String oldAVAILABLEQTY = this.AVAILABLEQTY;
        this.AVAILABLEQTY = AVAILABLEQTY;
        propertyChangeSupport.firePropertyChange("AVAILABLEQTY", oldAVAILABLEQTY, AVAILABLEQTY);
    }

    public String getAVAILABLEQTY() {
        return AVAILABLEQTY;
    }
    private String PARENTLPN;
    private String LPN;
    private String LPNSTATUS;
    private String SUBINV;
    private String LOCATOR;
    private String ITEM;
    private String ITEMDESC;
    private String UOM;
    private String ONHANDQTY;
    private String AVAILABLEQTY;


    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
