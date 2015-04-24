package dcom.shop.application.mobile.dispatch;

import dcom.shop.application.base.AEntity;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class ClockInOutBO extends AEntity{
    private String porgCode;
    private String pDeptCode;
    private String pJobOp;
    private String pInstance;
    private String pEmp;
    private String pResource;
    private String jobNumber;
    private String opSeq;
    private String opStartDate;
    private String opCompletionDate;
    private String badgeNumber;
    private String action;
    
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public ClockInOutBO() {
        super();
    }


    public void setPorgCode(String porgCode) {
        String oldPorgCode = this.porgCode;
        this.porgCode = porgCode;
        propertyChangeSupport.firePropertyChange("porgCode", oldPorgCode, porgCode);
    }

    public String getPorgCode() {
        return porgCode;
    }

    public void setPDeptCode(String pDeptCode) {
        String oldPDeptCode = this.pDeptCode;
        this.pDeptCode = pDeptCode;
        propertyChangeSupport.firePropertyChange("pDeptCode", oldPDeptCode, pDeptCode);
    }

    public String getPDeptCode() {
        return pDeptCode;
    }

    public void setPJobOp(String pJobOp) {
        String oldPJobOp = this.pJobOp;
        this.pJobOp = pJobOp;
        propertyChangeSupport.firePropertyChange("pJobOp", oldPJobOp, pJobOp);
    }

    public String getPJobOp() {
        return pJobOp;
    }

    public void setPInstance(String pInstance) {
        String oldPInstance = this.pInstance;
        this.pInstance = pInstance;
        propertyChangeSupport.firePropertyChange("pInstance", oldPInstance, pInstance);
    }

    public String getPInstance() {
        return pInstance;
    }

    public void setPEmp(String pEmp) {
        String oldPEmp = this.pEmp;
        this.pEmp = pEmp;
        propertyChangeSupport.firePropertyChange("pEmp", oldPEmp, pEmp);
    }

    public String getPEmp() {
        return pEmp;
    }

    public void setPResource(String pResource) {
        String oldPResource = this.pResource;
        this.pResource = pResource;
        propertyChangeSupport.firePropertyChange("pResource", oldPResource, pResource);
    }

    public String getPResource() {
        return pResource;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }

    public void setJobNumber(String jobNumber) {
        String oldJobNumber = this.jobNumber;
        this.jobNumber = jobNumber;
        propertyChangeSupport.firePropertyChange("jobNumber", oldJobNumber, jobNumber);
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setOpSeq(String opSeq) {
        String oldOpSeq = this.opSeq;
        this.opSeq = opSeq;
        propertyChangeSupport.firePropertyChange("opSeq", oldOpSeq, opSeq);
    }

    public String getOpSeq() {
        return opSeq;
    }

    public void setOpStartDate(String opStartDate) {
        String oldOpStartDate = this.opStartDate;
        this.opStartDate = opStartDate;
        propertyChangeSupport.firePropertyChange("opStartDate", oldOpStartDate, opStartDate);
    }

    public String getOpStartDate() {
        return opStartDate;
    }

    public void setOpCompletionDate(String opCompletionDate) {
        String oldOpCompletionDate = this.opCompletionDate;
        this.opCompletionDate = opCompletionDate;
        propertyChangeSupport.firePropertyChange("opCompletionDate", oldOpCompletionDate, opCompletionDate);
    }

    public String getOpCompletionDate() {
        return opCompletionDate;
    }

    public void setBadgeNumber(String badgeNumber) {
        String oldBadgeNumber = this.badgeNumber;
        this.badgeNumber = badgeNumber;
        propertyChangeSupport.firePropertyChange("badgeNumber", oldBadgeNumber, badgeNumber);
    }

    public String getBadgeNumber() {
        return badgeNumber;
    }

    public void setAction(String action) {
        String oldAction = this.action;
        this.action = action;
        propertyChangeSupport.firePropertyChange("action", oldAction, action);
    }

    public String getAction() {
        return action;
    }
}
