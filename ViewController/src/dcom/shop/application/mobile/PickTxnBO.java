package dcom.shop.application.mobile;

import dcom.shop.application.base.AEntity;

import java.math.BigDecimal;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class PickTxnBO extends AEntity {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public PickTxnBO() {
        super();
    }
    private String ITEM;
    private String ITEMDESC;
    private String TXNUOM;
    private BigDecimal TXNQTY;
    private String PRIMARYUOM;
    private String SUBINV;
    private BigDecimal PICKID;
    private String TOSUBINV;
    private String TOLOCATOR;
    private String SUBREST;
    private String LOCREST;
    private String LOCATORCODE;

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

    public void setTXNUOM(String TXNUOM) {
        String oldTXNUOM = this.TXNUOM;
        this.TXNUOM = TXNUOM;
        propertyChangeSupport.firePropertyChange("TXNUOM", oldTXNUOM, TXNUOM);
    }

    public String getTXNUOM() {
        return TXNUOM;
    }

    public void setTXNQTY(BigDecimal TXNQTY) {
        BigDecimal oldTXNQTY = this.TXNQTY;
        this.TXNQTY = TXNQTY;
        propertyChangeSupport.firePropertyChange("TXNQTY", oldTXNQTY, TXNQTY);
    }

    public BigDecimal getTXNQTY() {
        return TXNQTY;
    }

    public void setPRIMARYUOM(String PRIMARYUOM) {
        String oldPRIMARYUOM = this.PRIMARYUOM;
        this.PRIMARYUOM = PRIMARYUOM;
        propertyChangeSupport.firePropertyChange("PRIMARYUOM", oldPRIMARYUOM, PRIMARYUOM);
    }

    public String getPRIMARYUOM() {
        return PRIMARYUOM;
    }

    public void setSUBINV(String SUBINV) {
        String oldSUBINV = this.SUBINV;
        this.SUBINV = SUBINV;
        propertyChangeSupport.firePropertyChange("SUBINV", oldSUBINV, SUBINV);
    }

    public String getSUBINV() {
        return SUBINV;
    }

    public void setPICKID(BigDecimal PICKID) {
        BigDecimal oldPICKID = this.PICKID;
        this.PICKID = PICKID;
        propertyChangeSupport.firePropertyChange("PICKID", oldPICKID, PICKID);
    }

    public BigDecimal getPICKID() {
        return PICKID;
    }

    public void setTOSUBINV(String TOSUBINV) {
        String oldTOSUBINV = this.TOSUBINV;
        this.TOSUBINV = TOSUBINV;
        propertyChangeSupport.firePropertyChange("TOSUBINV", oldTOSUBINV, TOSUBINV);
    }

    public String getTOSUBINV() {
        return TOSUBINV;
    }

    public void setTOLOCATOR(String TOLOCATOR) {
        String oldTOLOCATOR = this.TOLOCATOR;
        this.TOLOCATOR = TOLOCATOR;
        propertyChangeSupport.firePropertyChange("TOLOCATOR", oldTOLOCATOR, TOLOCATOR);
    }

    public String getTOLOCATOR() {
        return TOLOCATOR;
    }

    public void setSUBREST(String SUBREST) {
        String oldSUBREST = this.SUBREST;
        this.SUBREST = SUBREST;
        propertyChangeSupport.firePropertyChange("SUBREST", oldSUBREST, SUBREST);
    }

    public String getSUBREST() {
        return SUBREST;
    }

    public void setLOCREST(String LOCREST) {
        String oldLOCREST = this.LOCREST;
        this.LOCREST = LOCREST;
        propertyChangeSupport.firePropertyChange("LOCREST", oldLOCREST, LOCREST);
    }

    public String getLOCREST() {
        return LOCREST;
    }

    public void setLOCATORCODE(String LOCATORCODE) {
        String oldLOCATORCODE = this.LOCATORCODE;
        this.LOCATORCODE = LOCATORCODE;
        propertyChangeSupport.firePropertyChange("LOCATORCODE", oldLOCATORCODE, LOCATORCODE);
    }

    public String getLOCATORCODE() {
        return LOCATORCODE;
    }

    public void setTASKID(BigDecimal TASKID) {
        BigDecimal oldTASKID = this.TASKID;
        this.TASKID = TASKID;
        propertyChangeSupport.firePropertyChange("TASKID", oldTASKID, TASKID);
    }

    public BigDecimal getTASKID() {
        return TASKID;
    }

    public void setTRXHDRID(BigDecimal TRXHDRID) {
        BigDecimal oldTRXHDRID = this.TRXHDRID;
        this.TRXHDRID = TRXHDRID;
        propertyChangeSupport.firePropertyChange("TRXHDRID", oldTRXHDRID, TRXHDRID);
    }

    public BigDecimal getTRXHDRID() {
        return TRXHDRID;
    }

    public void setPACKSLIPNUM(String PACKSLIPNUM) {
        String oldPACKSLIPNUM = this.PACKSLIPNUM;
        this.PACKSLIPNUM = PACKSLIPNUM;
        propertyChangeSupport.firePropertyChange("PACKSLIPNUM", oldPACKSLIPNUM, PACKSLIPNUM);
    }

    public String getPACKSLIPNUM() {
        return PACKSLIPNUM;
    }

    public void setLOCATOR(String LOCATOR) {
        String oldLOCATOR = this.LOCATOR;
        this.LOCATOR = LOCATOR;
        propertyChangeSupport.firePropertyChange("LOCATOR", oldLOCATOR, LOCATOR);
    }

    public String getLOCATOR() {
        return LOCATOR;
    }

    public void setLOTCONTROL(String LOTCONTROL) {
        String oldLOTCONTROL = this.LOTCONTROL;
        this.LOTCONTROL = LOTCONTROL;
        propertyChangeSupport.firePropertyChange("LOTCONTROL", oldLOTCONTROL, LOTCONTROL);
    }

    public String getLOTCONTROL() {
        return LOTCONTROL;
    }

    public void setLOTALLOC(String LOTALLOC) {
        String oldLOTALLOC = this.LOTALLOC;
        this.LOTALLOC = LOTALLOC;
        propertyChangeSupport.firePropertyChange("LOTALLOC", oldLOTALLOC, LOTALLOC);
    }

    public String getLOTALLOC() {
        return LOTALLOC;
    }

    public void setSERIALCONTROL(String SERIALCONTROL) {
        String oldSERIALCONTROL = this.SERIALCONTROL;
        this.SERIALCONTROL = SERIALCONTROL;
        propertyChangeSupport.firePropertyChange("SERIALCONTROL", oldSERIALCONTROL, SERIALCONTROL);
    }

    public String getSERIALCONTROL() {
        return SERIALCONTROL;
    }

    public void setSERIALALLOC(String SERIALALLOC) {
        String oldSERIALALLOC = this.SERIALALLOC;
        this.SERIALALLOC = SERIALALLOC;
        propertyChangeSupport.firePropertyChange("SERIALALLOC", oldSERIALALLOC, SERIALALLOC);
    }

    public String getSERIALALLOC() {
        return SERIALALLOC;
    }
    private BigDecimal TASKID;
    private BigDecimal TRXHDRID;
    private String PACKSLIPNUM;
    private String LOCATOR;
    private String LOTCONTROL;
    private String LOTALLOC;
    private String SERIALCONTROL;

    public void setLPN(String LPN) {
        String oldLPN = this.LPN;
        this.LPN = LPN;
        propertyChangeSupport.firePropertyChange("LPN", oldLPN, LPN);
    }

    public String getLPN() {
        return LPN;
    }
    private String SERIALALLOC;
    private String LPN;

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
