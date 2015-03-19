package dcom.shop.application.mobile.dispatch;

import java.math.BigDecimal;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class OpSeqBO {
    private String orgCode;
    private BigDecimal opSeq;
    private String oPcode;
    private String deptCode;
    private BigDecimal queueQty;
    private BigDecimal runQty;
    private BigDecimal toMoveQty;
    private BigDecimal rejectQty;
    private BigDecimal scrapQty;
    private BigDecimal compQty;
    private BigDecimal csScrapQty;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public OpSeqBO() {
        super();
    }


    public void setOrgCode(String orgCode) {
        String oldOrgCode = this.orgCode;
        this.orgCode = orgCode;
        propertyChangeSupport.firePropertyChange("orgCode", oldOrgCode, orgCode);
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOpSeq(BigDecimal opSeq) {
        BigDecimal oldOpSeq = this.opSeq;
        this.opSeq = opSeq;
        propertyChangeSupport.firePropertyChange("opSeq", oldOpSeq, opSeq);
    }

    public BigDecimal getOpSeq() {
        return opSeq;
    }


    public void setOPcode(String oPcode) {
        String oldOPcode = this.oPcode;
        this.oPcode = oPcode;
        propertyChangeSupport.firePropertyChange("oPcode", oldOPcode, oPcode);
    }

    public String getOPcode() {
        return oPcode;
    }

    public void setDeptCode(String deptCode) {
        String oldDeptCode = this.deptCode;
        this.deptCode = deptCode;
        propertyChangeSupport.firePropertyChange("deptCode", oldDeptCode, deptCode);
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setQueueQty(BigDecimal queueQty) {
        BigDecimal oldQueueQty = this.queueQty;
        this.queueQty = queueQty;
        propertyChangeSupport.firePropertyChange("queueQty", oldQueueQty, queueQty);
    }

    public BigDecimal getQueueQty() {
        return queueQty;
    }

    public void setRunQty(BigDecimal runQty) {
        BigDecimal oldRunQty = this.runQty;
        this.runQty = runQty;
        propertyChangeSupport.firePropertyChange("runQty", oldRunQty, runQty);
    }

    public BigDecimal getRunQty() {
        return runQty;
    }

    public void setToMoveQty(BigDecimal toMoveQty) {
        BigDecimal oldToMoveQty = this.toMoveQty;
        this.toMoveQty = toMoveQty;
        propertyChangeSupport.firePropertyChange("toMoveQty", oldToMoveQty, toMoveQty);
    }

    public BigDecimal getToMoveQty() {
        return toMoveQty;
    }

    public void setRejectQty(BigDecimal rejectQty) {
        BigDecimal oldRejectQty = this.rejectQty;
        this.rejectQty = rejectQty;
        propertyChangeSupport.firePropertyChange("rejectQty", oldRejectQty, rejectQty);
    }

    public BigDecimal getRejectQty() {
        return rejectQty;
    }

    public void setScrapQty(BigDecimal scrapQty) {
        BigDecimal oldScrapQty = this.scrapQty;
        this.scrapQty = scrapQty;
        propertyChangeSupport.firePropertyChange("scrapQty", oldScrapQty, scrapQty);
    }

    public BigDecimal getScrapQty() {
        return scrapQty;
    }

    public void setCompQty(BigDecimal compQty) {
        BigDecimal oldCompQty = this.compQty;
        this.compQty = compQty;
        propertyChangeSupport.firePropertyChange("compQty", oldCompQty, compQty);
    }

    public BigDecimal getCompQty() {
        return compQty;
    }

    public void setCsScrapQty(BigDecimal csScrapQty) {
        BigDecimal oldCsScrapQty = this.csScrapQty;
        this.csScrapQty = csScrapQty;
        propertyChangeSupport.firePropertyChange("csScrapQty", oldCsScrapQty, csScrapQty);
    }

    public BigDecimal getCsScrapQty() {
        return csScrapQty;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
