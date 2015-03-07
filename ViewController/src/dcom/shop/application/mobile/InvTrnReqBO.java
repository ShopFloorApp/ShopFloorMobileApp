package dcom.shop.application.mobile;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class InvTrnReqBO {
   
    public InvTrnReqBO() {
        super();
    }
    private String LPN;
    private String ITEM;
    private String SOURCSUBINV;

    public void setLPN(String LPN) {
        this.LPN = LPN;
    }

    public String getLPN() {
        return LPN;
    }

    public void setITEM(String ITEM) {
        this.ITEM = ITEM;
    }

    public String getITEM() {
        return ITEM;
    }

    public void setSOURCSUBINV(String SOURCSUBINV) {
        this.SOURCSUBINV = SOURCSUBINV;
    }

    public String getSOURCSUBINV() {
        return SOURCSUBINV;
    }

    public void setSOURCELOCATOR(String SOURCELOCATOR) {
        this.SOURCELOCATOR = SOURCELOCATOR;
    }

    public String getSOURCELOCATOR() {
        return SOURCELOCATOR;
    }

    public void setDESTSUBINV(String DESTSUBINV) {
        this.DESTSUBINV = DESTSUBINV;
    }

    public String getDESTSUBINV() {
        return DESTSUBINV;
    }

    public void setDESTLOCATOR(String DESTLOCATOR) {
        this.DESTLOCATOR = DESTLOCATOR;
    }

    public String getDESTLOCATOR() {
        return DESTLOCATOR;
    }

    public void setTRXQTY(String TRXQTY) {
        this.TRXQTY = TRXQTY;
    }

    public String getTRXQTY() {
        return TRXQTY;
    }

    public void setTXNUOM(String TXNUOM) {
        this.TXNUOM = TXNUOM;
    }

    public String getTXNUOM() {
        return TXNUOM;
    }
    private String SOURCELOCATOR;
    private String DESTSUBINV;
    private String DESTLOCATOR;
    private String TRXQTY;
    private String TXNUOM;

   }
