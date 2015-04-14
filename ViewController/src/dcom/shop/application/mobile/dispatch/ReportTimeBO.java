package dcom.shop.application.mobile.dispatch;

import java.math.BigDecimal;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class ReportTimeBO {
    private String jobOp;
    private String resourceCode;
    private String instanceNum;
    private String employee;
    private String startTime;
    private String endTime;
    private BigDecimal qty;
    private String uom;
    private String orgCode;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public ReportTimeBO() {
        super();
    }

    public void setJobOp(String jobOp) {
        String oldJobOp = this.jobOp;
        this.jobOp = jobOp;
        propertyChangeSupport.firePropertyChange("jobOp", oldJobOp, jobOp);
    }

    public String getJobOp() {
        return jobOp;
    }

    public void setResourceCode(String resourceCode) {
        String oldResourceCode = this.resourceCode;
        this.resourceCode = resourceCode;
        propertyChangeSupport.firePropertyChange("resourceCode", oldResourceCode, resourceCode);
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setInstanceNum(String instanceNum) {
        String oldInstanceNum = this.instanceNum;
        this.instanceNum = instanceNum;
        propertyChangeSupport.firePropertyChange("instanceNum", oldInstanceNum, instanceNum);
    }

    public String getInstanceNum() {
        return instanceNum;
    }

    public void setEmployee(String employee) {
        String oldEmployee = this.employee;
        this.employee = employee;
        propertyChangeSupport.firePropertyChange("employee", oldEmployee, employee);
    }

    public String getEmployee() {
        return employee;
    }

    public void setStartTime(String startTime) {
        String oldStartTime = this.startTime;
        this.startTime = startTime;
        propertyChangeSupport.firePropertyChange("startTime", oldStartTime, startTime);
    }

    public String getStartTime() {
        return startTime;
    }

    public void setEndTime(String endTime) {
        String oldEndTime = this.endTime;
        this.endTime = endTime;
        propertyChangeSupport.firePropertyChange("endTime", oldEndTime, endTime);
    }

    public String getEndTime() {
        return endTime;
    }


    public void setQty(BigDecimal qty) {
        BigDecimal oldQty = this.qty;
        this.qty = qty;
        propertyChangeSupport.firePropertyChange("qty", oldQty, qty);
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setUom(String uom) {
        String oldUom = this.uom;
        this.uom = uom;
        propertyChangeSupport.firePropertyChange("uom", oldUom, uom);
    }

    public String getUom() {
        return uom;
    }

    public void setOrgCode(String orgCode) {
        String oldOrgCode = this.orgCode;
        this.orgCode = orgCode;
        propertyChangeSupport.firePropertyChange("orgCode", oldOrgCode, orgCode);
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
