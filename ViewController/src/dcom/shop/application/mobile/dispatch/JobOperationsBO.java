package dcom.shop.application.mobile.dispatch;

import java.math.BigDecimal;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class JobOperationsBO {
    private String opStartDate;
    private BigDecimal qtyQueue;
    private String jobOps;
    private String jobOn;;
    private BigDecimal qty2move;
    
    private BigDecimal qtyCScrapQty;
    private BigDecimal qtyScrapped;
    private String opDesc;
    private String attrib;
    private BigDecimal opSeq;
    private BigDecimal qtyCompleted;
    private String opComplDate;
    private BigDecimal qtyRejected;
    private String dept;
    private String opCode;
    private BigDecimal qtyRun;
    private String jobNumber;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public JobOperationsBO() {
        super();
    }

    public void setOpStartDate(String opStartDate) {
        String oldOpStartDate = this.opStartDate;
        this.opStartDate = opStartDate;
        propertyChangeSupport.firePropertyChange("opStartDate", oldOpStartDate, opStartDate);
    }

    public String getOpStartDate() {
        return opStartDate;
    }

    public void setQtyQueue(BigDecimal qtyQueue) {
        BigDecimal oldQtyQueue = this.qtyQueue;
        this.qtyQueue = qtyQueue;
        propertyChangeSupport.firePropertyChange("qtyQueue", oldQtyQueue, qtyQueue);
    }

    public BigDecimal getQtyQueue() {
        return qtyQueue;
    }

    public void setJobOps(String jobOps) {
        String oldJobOps = this.jobOps;
        this.jobOps = jobOps;
        propertyChangeSupport.firePropertyChange("jobOps", oldJobOps, jobOps);
    }

    public String getJobOps() {
        return jobOps;
    }

    public void setJobOn(String jobOn) {
        String oldJobOn = this.jobOn;
        this.jobOn = jobOn;
        propertyChangeSupport.firePropertyChange("jobOn", oldJobOn, jobOn);
    }

    public String getJobOn() {
        return jobOn;
    }

    public void setQty2move(BigDecimal qty2move) {
        BigDecimal oldQty2move = this.qty2move;
        this.qty2move = qty2move;
        propertyChangeSupport.firePropertyChange("qty2move", oldQty2move, qty2move);
    }

    public BigDecimal getQty2move() {
        return qty2move;
    }

    public void setQtyCScrapQty(BigDecimal qtyCScrapQty) {
        BigDecimal oldQtyCScrapQty = this.qtyCScrapQty;
        this.qtyCScrapQty = qtyCScrapQty;
        propertyChangeSupport.firePropertyChange("qtyCScrapQty", oldQtyCScrapQty, qtyCScrapQty);
    }

    public BigDecimal getQtyCScrapQty() {
        return qtyCScrapQty;
    }

    public void setQtyScrapped(BigDecimal qtyScrapped) {
        BigDecimal oldQtyScrapped = this.qtyScrapped;
        this.qtyScrapped = qtyScrapped;
        propertyChangeSupport.firePropertyChange("qtyScrapped", oldQtyScrapped, qtyScrapped);
    }

    public BigDecimal getQtyScrapped() {
        return qtyScrapped;
    }

    public void setOpDesc(String opDesc) {
        String oldOpDesc = this.opDesc;
        this.opDesc = opDesc;
        propertyChangeSupport.firePropertyChange("opDesc", oldOpDesc, opDesc);
    }

    public String getOpDesc() {
        return opDesc;
    }

    public void setAttrib(String attrib) {
        String oldAttrib = this.attrib;
        this.attrib = attrib;
        propertyChangeSupport.firePropertyChange("attrib", oldAttrib, attrib);
    }

    public String getAttrib() {
        return attrib;
    }

    public void setOpSeq(BigDecimal opSeq) {
        BigDecimal oldOpSeq = this.opSeq;
        this.opSeq = opSeq;
        propertyChangeSupport.firePropertyChange("opSeq", oldOpSeq, opSeq);
    }

    public BigDecimal getOpSeq() {
        return opSeq;
    }

    public void setQtyCompleted(BigDecimal qtyCompleted) {
        BigDecimal oldQtyCompleted = this.qtyCompleted;
        this.qtyCompleted = qtyCompleted;
        propertyChangeSupport.firePropertyChange("qtyCompleted", oldQtyCompleted, qtyCompleted);
    }

    public BigDecimal getQtyCompleted() {
        return qtyCompleted;
    }

    public void setOpComplDate(String opComplDate) {
        String oldOpComplDate = this.opComplDate;
        this.opComplDate = opComplDate;
        propertyChangeSupport.firePropertyChange("opComplDate", oldOpComplDate, opComplDate);
    }

    public String getOpComplDate() {
        return opComplDate;
    }

    public void setQtyRejected(BigDecimal qtyRejected) {
        BigDecimal oldQtyRejected = this.qtyRejected;
        this.qtyRejected = qtyRejected;
        propertyChangeSupport.firePropertyChange("qtyRejected", oldQtyRejected, qtyRejected);
    }

    public BigDecimal getQtyRejected() {
        return qtyRejected;
    }

    public void setDept(String dept) {
        String oldDept = this.dept;
        this.dept = dept;
        propertyChangeSupport.firePropertyChange("dept", oldDept, dept);
    }

    public String getDept() {
        return dept;
    }

    public void setOpCode(String opCode) {
        String oldOpCode = this.opCode;
        this.opCode = opCode;
        propertyChangeSupport.firePropertyChange("opCode", oldOpCode, opCode);
    }

    public String getOpCode() {
        return opCode;
    }

    public void setQtyRun(BigDecimal qtyRun) {
        BigDecimal oldQtyRun = this.qtyRun;
        this.qtyRun = qtyRun;
        propertyChangeSupport.firePropertyChange("qtyRun", oldQtyRun, qtyRun);
    }

    public BigDecimal getQtyRun() {
        return qtyRun;
    }

    public void setJobNumber(String jobNumber) {
        String oldJobNumber = this.jobNumber;
        this.jobNumber = jobNumber;
        propertyChangeSupport.firePropertyChange("jobNumber", oldJobNumber, jobNumber);
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
