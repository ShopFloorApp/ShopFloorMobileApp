package dcom.shop.application.mobile.dispatch;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;


public class TransactBO {
    
    private String action;
    private String trxRef;
    private String sourceType;
    private String trxType;
    private String trxDate;
    private String orgCode;
    private String item;
    private String subinv;
    private String locator;
    private String lpn;
    private String sourceCode;
    private String trxQty;
    private String txnUom;
    private String glAccount;
    private String accountAlias;
    private String reason;
    private String trxnAction;
    private String trxSource;
    private String wipEntityName;
    private String dept;
    private String fromOpSeq;
    private String toOpSeq;
    private String createdBy;
    private String salesOrder;
    private String notes;
    private String reference;
    private String kanban;
    private String attrib;
    private Serials lots;
    private Lots serials;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public TransactBO() {
        super();
    }


    public void setAction(String action) {
        String oldAction = this.action;
        this.action = action;
        propertyChangeSupport.firePropertyChange("action", oldAction, action);
    }

    public String getAction() {
        return action;
    }

    public void setTrxRef(String trxRef) {
        String oldTrxRef = this.trxRef;
        this.trxRef = trxRef;
        propertyChangeSupport.firePropertyChange("trxRef", oldTrxRef, trxRef);
    }

    public String getTrxRef() {
        return trxRef;
    }

    public void setSourceType(String sourceType) {
        String oldSourceType = this.sourceType;
        this.sourceType = sourceType;
        propertyChangeSupport.firePropertyChange("sourceType", oldSourceType, sourceType);
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setTrxType(String trxType) {
        String oldTrxType = this.trxType;
        this.trxType = trxType;
        propertyChangeSupport.firePropertyChange("trxType", oldTrxType, trxType);
    }

    public String getTrxType() {
        return trxType;
    }

    public void setTrxDate(String trxDate) {
        String oldTrxDate = this.trxDate;
        this.trxDate = trxDate;
        propertyChangeSupport.firePropertyChange("trxDate", oldTrxDate, trxDate);
    }

    public String getTrxDate() {
        return trxDate;
    }

    public void setOrgCode(String orgCode) {
        String oldOrgCode = this.orgCode;
        this.orgCode = orgCode;
        propertyChangeSupport.firePropertyChange("orgCode", oldOrgCode, orgCode);
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setItem(String item) {
        String oldItem = this.item;
        this.item = item;
        propertyChangeSupport.firePropertyChange("item", oldItem, item);
    }

    public String getItem() {
        return item;
    }

    public void setSubinv(String subinv) {
        String oldSubinv = this.subinv;
        this.subinv = subinv;
        propertyChangeSupport.firePropertyChange("subinv", oldSubinv, subinv);
    }

    public String getSubinv() {
        return subinv;
    }

    public void setLocator(String locator) {
        String oldLocator = this.locator;
        this.locator = locator;
        propertyChangeSupport.firePropertyChange("locator", oldLocator, locator);
    }

    public String getLocator() {
        return locator;
    }

    public void setLpn(String lpn) {
        String oldLpn = this.lpn;
        this.lpn = lpn;
        propertyChangeSupport.firePropertyChange("lpn", oldLpn, lpn);
    }

    public String getLpn() {
        return lpn;
    }

    public void setSourceCode(String sourceCode) {
        String oldSourceCode = this.sourceCode;
        this.sourceCode = sourceCode;
        propertyChangeSupport.firePropertyChange("sourceCode", oldSourceCode, sourceCode);
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setTrxQty(String trxQty) {
        String oldTrxQty = this.trxQty;
        this.trxQty = trxQty;
        propertyChangeSupport.firePropertyChange("trxQty", oldTrxQty, trxQty);
    }

    public String getTrxQty() {
        return trxQty;
    }

    public void setTxnUom(String txnUom) {
        String oldTxnUom = this.txnUom;
        this.txnUom = txnUom;
        propertyChangeSupport.firePropertyChange("txnUom", oldTxnUom, txnUom);
    }

    public String getTxnUom() {
        return txnUom;
    }

    public void setGlAccount(String glAccount) {
        String oldGlAccount = this.glAccount;
        this.glAccount = glAccount;
        propertyChangeSupport.firePropertyChange("glAccount", oldGlAccount, glAccount);
    }

    public String getGlAccount() {
        return glAccount;
    }

    public void setAccountAlias(String accountAlias) {
        String oldAccountAlias = this.accountAlias;
        this.accountAlias = accountAlias;
        propertyChangeSupport.firePropertyChange("accountAlias", oldAccountAlias, accountAlias);
    }

    public String getAccountAlias() {
        return accountAlias;
    }

    public void setReason(String reason) {
        String oldReason = this.reason;
        this.reason = reason;
        propertyChangeSupport.firePropertyChange("reason", oldReason, reason);
    }

    public String getReason() {
        return reason;
    }

    public void setTrxnAction(String trxnAction) {
        String oldTrxnAction = this.trxnAction;
        this.trxnAction = trxnAction;
        propertyChangeSupport.firePropertyChange("trxnAction", oldTrxnAction, trxnAction);
    }

    public String getTrxnAction() {
        return trxnAction;
    }

    public void setTrxSource(String trxSource) {
        String oldTrxSource = this.trxSource;
        this.trxSource = trxSource;
        propertyChangeSupport.firePropertyChange("trxSource", oldTrxSource, trxSource);
    }

    public String getTrxSource() {
        return trxSource;
    }

    public void setWipEntityName(String wipEntityName) {
        String oldWipEntityName = this.wipEntityName;
        this.wipEntityName = wipEntityName;
        propertyChangeSupport.firePropertyChange("wipEntityName", oldWipEntityName, wipEntityName);
    }

    public String getWipEntityName() {
        return wipEntityName;
    }

    public void setDept(String dept) {
        String oldDept = this.dept;
        this.dept = dept;
        propertyChangeSupport.firePropertyChange("dept", oldDept, dept);
    }

    public String getDept() {
        return dept;
    }

    public void setFromOpSeq(String fromOpSeq) {
        String oldFromOpSeq = this.fromOpSeq;
        this.fromOpSeq = fromOpSeq;
        propertyChangeSupport.firePropertyChange("fromOpSeq", oldFromOpSeq, fromOpSeq);
    }

    public String getFromOpSeq() {
        return fromOpSeq;
    }

    public void setToOpSeq(String toOpSeq) {
        String oldToOpSeq = this.toOpSeq;
        this.toOpSeq = toOpSeq;
        propertyChangeSupport.firePropertyChange("toOpSeq", oldToOpSeq, toOpSeq);
    }

    public String getToOpSeq() {
        return toOpSeq;
    }

    public void setCreatedBy(String createdBy) {
        String oldCreatedBy = this.createdBy;
        this.createdBy = createdBy;
        propertyChangeSupport.firePropertyChange("createdBy", oldCreatedBy, createdBy);
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setSalesOrder(String salesOrder) {
        String oldSalesOrder = this.salesOrder;
        this.salesOrder = salesOrder;
        propertyChangeSupport.firePropertyChange("salesOrder", oldSalesOrder, salesOrder);
    }

    public String getSalesOrder() {
        return salesOrder;
    }

    public void setNotes(String notes) {
        String oldNotes = this.notes;
        this.notes = notes;
        propertyChangeSupport.firePropertyChange("notes", oldNotes, notes);
    }

    public String getNotes() {
        return notes;
    }

    public void setReference(String reference) {
        String oldReference = this.reference;
        this.reference = reference;
        propertyChangeSupport.firePropertyChange("reference", oldReference, reference);
    }

    public String getReference() {
        return reference;
    }

    public void setKanban(String kanban) {
        String oldKanban = this.kanban;
        this.kanban = kanban;
        propertyChangeSupport.firePropertyChange("kanban", oldKanban, kanban);
    }

    public String getKanban() {
        return kanban;
    }

    public void setAttrib(String attrib) {
        String oldAttrib = this.attrib;
        this.attrib = attrib;
        propertyChangeSupport.firePropertyChange("attrib", oldAttrib, attrib);
    }

    public String getAttrib() {
        return attrib;
    }

    public void setLots(Serials lots) {
        Serials oldLots = this.lots;
        this.lots = lots;
        propertyChangeSupport.firePropertyChange("lots", oldLots, lots);
    }

    public Serials getLots() {
        return lots;
    }

    public void setSerials(Lots serials) {
        Lots oldSerials = this.serials;
        this.serials = serials;
        propertyChangeSupport.firePropertyChange("serials", oldSerials, serials);
    }

    public Lots getSerials() {
        return serials;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
