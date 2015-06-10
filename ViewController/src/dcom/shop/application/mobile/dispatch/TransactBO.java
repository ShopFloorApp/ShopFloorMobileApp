package dcom.shop.application.mobile.dispatch;

import dcom.shop.application.dc.dispatch.OpSeqDC;

import java.math.BigDecimal;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
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
    private String scrapQty;
    private String txnUom;
    private String glAccount;
    private String accountAlias;
    private String reason;
    private String trxnAction;
    private String trxSource;
    private String wipEntityName;
    private String dept;
    private String fromStep;
    private String toStep;
    private String overComplFlag;
    private String fromOpSeq;
    private String toOpSeq;
    private String createdBy;
    private String salesOrder;
    private String notes;
    private String reference;
    private String kanban;
    private String attrib;
    private Serials serials;
    private Lots lots;
    private Boolean isNewEntity;
    private String fromOpCode;
    private String toOpCode;
    private String fromDept;
    private String toDept;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public TransactBO() {
        super();
    }

    public void setFromDept(String fromDept) {
        String oldFromDept = this.fromDept;
        this.fromDept = fromDept;
        propertyChangeSupport.firePropertyChange("fromDept", oldFromDept, fromDept);
    }

    public String getFromDept() {
        return fromDept;
    }

    public void setToDept(String toDept) {
        String oldToDept = this.toDept;
        this.toDept = toDept;
        propertyChangeSupport.firePropertyChange("toDept", oldToDept, toDept);
    }

    public String getToDept() {
        return toDept;
    }

    public void setFromOpCode(String fromOpCode) {
        String oldFromOpCode = this.fromOpCode;
        this.fromOpCode = fromOpCode;
        propertyChangeSupport.firePropertyChange("fromOpCode", oldFromOpCode, fromOpCode);
    }

    public String getFromOpCode() {
        return fromOpCode;
    }

    public void setToOpCode(String toOpCode) {
        String oldToOpCode = this.toOpCode;
        this.toOpCode = toOpCode;
        propertyChangeSupport.firePropertyChange("toOpCode", oldToOpCode, toOpCode);
    }

    public String getToOpCode() {
        return toOpCode;
    }

    public void setIsNewEntity(Boolean isNewEntity) {
        Boolean oldIsNewEntity = this.isNewEntity;
        this.isNewEntity = isNewEntity;
        propertyChangeSupport.firePropertyChange("isNewEntity", oldIsNewEntity, isNewEntity);
    }

    public Boolean getIsNewEntity() {
        return isNewEntity;
    }

    public void setScrapQty(String scrapQty) {
        String oldScrapQty = this.scrapQty;
        this.scrapQty = scrapQty;
        propertyChangeSupport.firePropertyChange("scrapQty", oldScrapQty, scrapQty);
    }

    public String getScrapQty() {
        return scrapQty;
    }

    public void setFromStep(String fromStep) {
        String oldFromStep = this.fromStep;
        this.fromStep = fromStep;
        propertyChangeSupport.firePropertyChange("fromStep", oldFromStep, fromStep);
    }

    public String getFromStep() {
        return fromStep;
    }

    public void setToStep(String toStep) {
        String oldToStep = this.toStep;
        this.toStep = toStep;
        propertyChangeSupport.firePropertyChange("toStep", oldToStep, toStep);
    }

    public String getToStep() {
        return toStep;
    }

    public void setOverComplFlag(String overComplFlag) {
        String oldOverComplFlag = this.overComplFlag;
        this.overComplFlag = overComplFlag;
        propertyChangeSupport.firePropertyChange("overComplFlag", oldOverComplFlag, overComplFlag);
    }

    public String getOverComplFlag() {
        return overComplFlag;
    }

    public void setPropertyChangeSupport(PropertyChangeSupport propertyChangeSupport) {
        PropertyChangeSupport oldPropertyChangeSupport = this.propertyChangeSupport;
        this.propertyChangeSupport = propertyChangeSupport;
        propertyChangeSupport.firePropertyChange("propertyChangeSupport", oldPropertyChangeSupport,
                                                 propertyChangeSupport);
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }

    public void setTrxRef(String trxRef) {
        String oldTrxRef = this.trxRef;
        this.trxRef = trxRef;
        propertyChangeSupport.firePropertyChange("trxRef", oldTrxRef, trxRef);
    }

    public String getTrxRef() {
        return trxRef;
    }

    public void setTrxQty(String trxQty) {
        String oldTrxQty = this.trxQty;
        this.trxQty = trxQty;
        propertyChangeSupport.firePropertyChange("trxQty", oldTrxQty, trxQty);
    }

    public String getTrxQty() {
        return trxQty;
    }

    public void setAction(String action) {
        String oldAction = this.action;
        this.action = action;
        propertyChangeSupport.firePropertyChange("action", oldAction, action);
    }

    public String getAction() {
        return action;
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
        //Need to update FromOpCode and fromDept
        OpSeqDC opSeqDC = new OpSeqDC();
        this.setFromDept(opSeqDC.getDept(fromOpSeq.toString()));
        this.setFromOpCode(opSeqDC.getOpCode(fromOpSeq.toString()));
        String oldFromOpSeq = this.fromOpSeq;
        this.fromOpSeq = fromOpSeq;
        propertyChangeSupport.firePropertyChange("fromOpSeq", oldFromOpSeq, fromOpSeq);
    }

    public String getFromOpSeq() {
        return fromOpSeq;
    }


    public void setToOpSeq(String toOpSeq) {
        //Need to update ToOpCode and ToDept
        OpSeqDC opSeqDC = new OpSeqDC();
        this.setToDept(opSeqDC.getDept(toOpSeq.toString()));
        this.setToOpCode(opSeqDC.getOpCode(toOpSeq.toString()));
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

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }

    public String getPayload() {
        StringBuffer payload = new StringBuffer();
        payload.append("\"InputParameters\":{\"PWIPTXN\":{");
        //Adding not null attributes in the payload
        if (getAction() != null) {
            payload.append("\"ACTION\": \"" + getAction() + "\",");
        }
        if (getTrxRef() != null) {
            payload.append("\"TRXREF\": \"" + getTrxRef() + "\",");
        }
        if (getSourceType() != null) {
            payload.append("\"SOURCETYPE\": \"" + getSourceType() + "\",");
        }
        String trxType = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.trxType}").toString();
        if (trxType != null) {
            payload.append("\"TRXTYPE\": \"" + trxType + "\",");
        }
        if (getTrxDate() != null) {
            payload.append("\"TRXDATE\": \"" + getTrxDate() + "\",");
        }
        if (getOrgCode() != null) {
            payload.append("\"ORGCODE\": \"" + getOrgCode() + "\",");
        }
        String item = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.searchKeyword}").toString();
        if (item != null) {
            payload.append("\"ITEM\": \"" + item + "\",");
        }
        if (getSubinv() != null) {
            payload.append("\"SUBINV\": \"" + getSubinv() + "\",");
        }
        if (getLocator() != null) {
            payload.append("\"LOCATOR\": \"" + getLocator() + "\",");
        }
        if (getLpn() != null) {
            payload.append("\"LPN\": \"" + getLpn() + "\",");
        }
        if (getSourceCode() != null) {
            payload.append("\"SOURCECODE\": \"" + getSourceCode() + "\",");
        }
        if (getTrxQty() != null) {
            payload.append("\"TRXQTY\": \"" + getTrxQty() + "\",");
        }
        if (getTxnUom() != null) {
            payload.append("\"TXNUOM\": \"" + getTxnUom() + "\",");
        }
        if (getGlAccount() != null) {
            payload.append("\"GLACCOUNT\": \"" + getGlAccount() + "\",");
        }
        if (getAccountAlias() != null) {
            payload.append("\"ACCOUNTALIAS\": \"" + getAccountAlias() + "\",");
        }
        if (getReason() != null) {
            payload.append("\"REASON\": \"" + getReason() + "\",");
        }
        if (getTrxnAction() != null) {
            payload.append("\"TRXNACTION\": \"" + getTrxnAction() + "\",");
        }
        if (getTrxSource() != null) {
            payload.append("\"TRXSOURCE\": \"" + getTrxSource() + "\",");
        }
        if (getWipEntityName() != null) {
            payload.append("\"WIPENTITYNAME\": \"" + getWipEntityName() + "\",");
        }
        if (getDept() != null) {
            payload.append("\"DEPT\": \"" + getDept() + "\",");
        }
        if (getFromStep() != null) {
            payload.append("\"FROMSTEP\": \"" + getFromStep() + "\",");
        }
        if (getToStep() != null) {
            payload.append("\"TOSTEP\": \"" + getToStep() + "\",");
        }

        if (getFromOpSeq() != null) {
            payload.append("\"FROMOPSEQ\": \"" + getFromOpSeq() + "\",");
        }
        if (getToOpSeq() != null) {
            payload.append("\"TOOPSEQ\": \"" + getToOpSeq() + "\",");
        }

        if (getOverComplFlag() != null) {
            payload.append("\"OVERCOMPLFLAG\": \"" + getOverComplFlag() + "\",");
        }

        String createdBy = AdfmfJavaUtilities.evaluateELExpression("#{securityContext.userName}").toString();
        if (createdBy != null) {
            payload.append("\"CREATEDBY\": \"" + createdBy + "\",");
        }
        if (getSalesOrder() != null) {
            payload.append("\"SALESORDER\": \"" + getSalesOrder() + "\",");
        }
        if (getNotes() != null) {
            payload.append("\"NOTES\": \"" + getNotes() + "\",");
        }
        if (getReference() != null) {
            payload.append("\"REFERENCE\": \"" + getReference() + "\",");
        }
        if (getKanban() != null) {
            payload.append("\"KANBAN\": \"" + getKanban() + "\",");
        }
        if (getScrapQty() != null) {
            payload.append("\"SCRAPQTY\": \"" + getScrapQty() + "\",");
        }
        if (getSerials() != null) {
            payload.append("\"LOTS\": \"" + getSerialPayload() + "\",");
        }
        payload.deleteCharAt(payload.length() - 1);
        payload.append("}}");
        return payload.toString();
    }

    public void setSerials(Serials serials) {
        Serials oldSerials = this.serials;
        this.serials = serials;
        propertyChangeSupport.firePropertyChange("serials", oldSerials, serials);
    }

    public Serials getSerials() {
        return serials;
    }

    public void setLots(Lots lots) {
        Lots oldLots = this.lots;
        this.lots = lots;
        propertyChangeSupport.firePropertyChange("lots", oldLots, lots);
    }

    public Lots getLots() {
        return lots;
    }

    public String getSerialPayload() {
        return null;
    }

    public String getLotPayload() {
        return null;
    }
}
