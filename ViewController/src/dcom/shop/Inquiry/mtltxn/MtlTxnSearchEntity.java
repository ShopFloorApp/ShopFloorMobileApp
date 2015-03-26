package dcom.shop.Inquiry.mtltxn;

import dcom.shop.application.base.AEntity;

import java.util.Date;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class MtlTxnSearchEntity extends AEntity  {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public MtlTxnSearchEntity() {
        super();
    }
    private int TRXREF;

    public void setTRXREF(int TRXREF) {
        int oldTRXREF = this.TRXREF;
        this.TRXREF = TRXREF;
        propertyChangeSupport.firePropertyChange("TRXREF", oldTRXREF, TRXREF);
    }

    public int getTRXREF() {
        return TRXREF;
    }

    public void setSOURCETYPE(String SOURCETYPE) {
        SOURCETYPE=getAttributeValue(SOURCETYPE);
        String oldSOURCETYPE = this.SOURCETYPE;
        this.SOURCETYPE = SOURCETYPE;
        propertyChangeSupport.firePropertyChange("SOURCETYPE", oldSOURCETYPE, SOURCETYPE);
    }

    public String getSOURCETYPE() {
        return SOURCETYPE;
    }

    public void setTRXTYPE(String TRXTYPE) {
        TRXTYPE=getAttributeValue(TRXTYPE);
        String oldTRXTYPE = this.TRXTYPE;
        this.TRXTYPE = TRXTYPE;
        propertyChangeSupport.firePropertyChange("TRXTYPE", oldTRXTYPE, TRXTYPE);
    }

    public String getTRXTYPE() {
        return TRXTYPE;
    }

    public void setTRXDATE(Date TRXDATE) {
        Date oldTRXDATE = this.TRXDATE;
        this.TRXDATE = TRXDATE;
        propertyChangeSupport.firePropertyChange("TRXDATE", oldTRXDATE, TRXDATE);
    }

    public Date getTRXDATE() {
        return TRXDATE;
    }

    public void setSOURCEORGCODE(String SOURCEORGCODE) {
        SOURCEORGCODE=getAttributeValue(SOURCEORGCODE);
        String oldSOURCEORGCODE = this.SOURCEORGCODE;
        this.SOURCEORGCODE = SOURCEORGCODE;
        propertyChangeSupport.firePropertyChange("SOURCEORGCODE", oldSOURCEORGCODE, SOURCEORGCODE);
    }

    public String getSOURCEORGCODE() {
        return SOURCEORGCODE;
    }

    public void setITEM(String ITEM) {
        String oldITEM = this.ITEM;
        this.ITEM = ITEM;
        propertyChangeSupport.firePropertyChange("ITEM", oldITEM, ITEM);
    }

    public String getITEM() {
        return ITEM;
    }

    public void setSOURCSUBINV(String SOURCSUBINV) {
        String oldSOURCSUBINV = this.SOURCSUBINV;
        this.SOURCSUBINV = SOURCSUBINV;
        propertyChangeSupport.firePropertyChange("SOURCSUBINV", oldSOURCSUBINV, SOURCSUBINV);
    }

    public String getSOURCSUBINV() {
        return SOURCSUBINV;
    }

    public void setSOURCELOCATOR(String SOURCELOCATOR) {
        SOURCELOCATOR=getAttributeValue(SOURCELOCATOR);
        String oldSOURCELOCATOR = this.SOURCELOCATOR;
        this.SOURCELOCATOR = SOURCELOCATOR;
        propertyChangeSupport.firePropertyChange("SOURCELOCATOR", oldSOURCELOCATOR, SOURCELOCATOR);
    }

    public String getSOURCELOCATOR() {
        return SOURCELOCATOR;
    }

    public void setLPN(String LPN) {
        LPN=getAttributeValue(LPN);
        String oldLPN = this.LPN;
        this.LPN = LPN;
        propertyChangeSupport.firePropertyChange("LPN", oldLPN, LPN);
    }

    public String getLPN() {
        return LPN;
    }

    public void setDESTORG(String DESTORG) {
        DESTORG=getAttributeValue(DESTORG);
        String oldDESTORG = this.DESTORG;
        this.DESTORG = DESTORG;
        propertyChangeSupport.firePropertyChange("DESTORG", oldDESTORG, DESTORG);
    }

    public String getDESTORG() {
        return DESTORG;
    }

    public void setDESTSUBINV(String DESTSUBINV) {
        DESTSUBINV=getAttributeValue(DESTSUBINV);
        String oldDESTSUBINV = this.DESTSUBINV;
        this.DESTSUBINV = DESTSUBINV;
        propertyChangeSupport.firePropertyChange("DESTSUBINV", oldDESTSUBINV, DESTSUBINV);
    }

    public String getDESTSUBINV() {
        return DESTSUBINV;
    }

    public void setDESTLOCATOR(String DESTLOCATOR) {
        DESTLOCATOR=getAttributeValue(DESTLOCATOR);
        String oldDESTLOCATOR = this.DESTLOCATOR;
        this.DESTLOCATOR = DESTLOCATOR;
        propertyChangeSupport.firePropertyChange("DESTLOCATOR", oldDESTLOCATOR, DESTLOCATOR);
    }

    public String getDESTLOCATOR() {
        return DESTLOCATOR;
    }

    public void setSOURCECODE(String SOURCECODE) {
        SOURCECODE=getAttributeValue(SOURCECODE);
        String oldSOURCECODE = this.SOURCECODE;
        this.SOURCECODE = SOURCECODE;
        propertyChangeSupport.firePropertyChange("SOURCECODE", oldSOURCECODE, SOURCECODE);
    }

    public String getSOURCECODE() {
        return SOURCECODE;
    }

    public void setCOSTED(String COSTED) {
        COSTED=getAttributeValue(COSTED);
        String oldCOSTED = this.COSTED;
        this.COSTED = COSTED;
        propertyChangeSupport.firePropertyChange("COSTED", oldCOSTED, COSTED);
    }

    public String getCOSTED() {
        return COSTED;
    }

    public void setTRXQTY(int TRXQTY) {
        int oldTRXQTY = this.TRXQTY;
        this.TRXQTY = TRXQTY;
        propertyChangeSupport.firePropertyChange("TRXQTY", oldTRXQTY, TRXQTY);
    }

    public int getTRXQTY() {
        return TRXQTY;
    }

    public void setTXNUOM(String TXNUOM) {
        String oldTXNUOM = this.TXNUOM;
        this.TXNUOM = TXNUOM;
        propertyChangeSupport.firePropertyChange("TXNUOM", oldTXNUOM, TXNUOM);
    }

    public String getTXNUOM() {
        return TXNUOM;
    }

    public void setGLACCOUNT(String GLACCOUNT) {
        GLACCOUNT=getAttributeValue(GLACCOUNT);
        String oldGLACCOUNT = this.GLACCOUNT;
        this.GLACCOUNT = GLACCOUNT;
        propertyChangeSupport.firePropertyChange("GLACCOUNT", oldGLACCOUNT, GLACCOUNT);
    }

    public String getGLACCOUNT() {
        return GLACCOUNT;
    }

    public void setACCOUNTALIAS(String ACCOUNTALIAS) {
        ACCOUNTALIAS=getAttributeValue(ACCOUNTALIAS);
        String oldACCOUNTALIAS = this.ACCOUNTALIAS;
        this.ACCOUNTALIAS = ACCOUNTALIAS;
        propertyChangeSupport.firePropertyChange("ACCOUNTALIAS", oldACCOUNTALIAS, ACCOUNTALIAS);
    }

    public String getACCOUNTALIAS() {
        return ACCOUNTALIAS;
    }

    public void setREASON(String REASON) {
        REASON=getAttributeValue(REASON);
        String oldREASON = this.REASON;
        this.REASON = REASON;
        propertyChangeSupport.firePropertyChange("REASON", oldREASON, REASON);
    }

    public String getREASON() {
        return REASON;
    }

    public void setTRXNACTION(String TRXNACTION) {
        TRXNACTION=getAttributeValue(TRXNACTION);
        String oldTRXNACTION = this.TRXNACTION;
        this.TRXNACTION = TRXNACTION;
        propertyChangeSupport.firePropertyChange("TRXNACTION", oldTRXNACTION, TRXNACTION);
    }

    public String getTRXNACTION() {
        return TRXNACTION;
    }

    public void setTRXSOURCE(String TRXSOURCE) {
        TRXSOURCE=getAttributeValue(TRXSOURCE);
        String oldTRXSOURCE = this.TRXSOURCE;
        this.TRXSOURCE = TRXSOURCE;
        propertyChangeSupport.firePropertyChange("TRXSOURCE", oldTRXSOURCE, TRXSOURCE);
    }

    public String getTRXSOURCE() {
        return TRXSOURCE;
    }

    public void setDEPT(String DEPT) {
        DEPT=getAttributeValue(DEPT);
        String oldDEPT = this.DEPT;
        this.DEPT = DEPT;
        propertyChangeSupport.firePropertyChange("DEPT", oldDEPT, DEPT);
    }

    public String getDEPT() {
        return DEPT;
    }

    public void setCREATEDBY(String CREATEDBY) {
        CREATEDBY=getAttributeValue(CREATEDBY);
        String oldCREATEDBY = this.CREATEDBY;
        this.CREATEDBY = CREATEDBY;
        propertyChangeSupport.firePropertyChange("CREATEDBY", oldCREATEDBY, CREATEDBY);
    }

    public String getCREATEDBY() {
        return CREATEDBY;
    }
    private String SOURCETYPE;
    private String TRXTYPE;
    private Date TRXDATE;
    private String SOURCEORGCODE;
    private String ITEM;
    private String SOURCSUBINV;
    private String SOURCELOCATOR;
    private String LPN;
    private String DESTORG;
    private String DESTSUBINV;
    private String DESTLOCATOR;
    private String SOURCECODE;
    private String COSTED;
    private int TRXQTY;
    private String TXNUOM;
    private String GLACCOUNT;
    private String ACCOUNTALIAS;
    private String REASON;
    private String TRXNACTION;
    private String TRXSOURCE;
    private String DEPT;
    private String CREATEDBY;

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
