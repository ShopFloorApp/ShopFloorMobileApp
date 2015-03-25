package dcom.shop.application.mobile.dispatch;

import dcom.shop.application.base.AEntity;

import java.math.BigDecimal;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class ResourcesBO extends AEntity{
    private String jobNumber;
    private String jobOps;
    private String resourceName;
    private String resourceType;
    private BigDecimal qtyRequired;
    private BigDecimal qtyApplied;
    private BigDecimal qtyOpen;
    private String uom;
    private String basis;
    private BigDecimal usagerate;
    private BigDecimal assignedunits;
    private String startDate;
    private String endDate;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public ResourcesBO() {
        super();
    }


    public void setJobNumber(String jobNumber) {
        jobNumber=getAttributeValue(jobNumber);
        String oldJobNumber = this.jobNumber;
        this.jobNumber = jobNumber;
        propertyChangeSupport.firePropertyChange("jobNumber", oldJobNumber, jobNumber);
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobOps(String jobOps) {
        jobOps = getAttributeValue(jobOps);
        String oldJobOps = this.jobOps;
        this.jobOps = jobOps;
        propertyChangeSupport.firePropertyChange("jobOps", oldJobOps, jobOps);
    }

    public String getJobOps() {
        return jobOps;
    }

    public void setResourceName(String resourceName) {
        resourceName=getAttributeValue(resourceName);
        String oldResourceName = this.resourceName;
        this.resourceName = resourceName;
        propertyChangeSupport.firePropertyChange("resourceName", oldResourceName, resourceName);
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceType(String resourceType) {
        resourceType=getAttributeValue(resourceType);
        String oldResourceType = this.resourceType;
        this.resourceType = resourceType;
        propertyChangeSupport.firePropertyChange("resourceType", oldResourceType, resourceType);
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setQtyRequired(BigDecimal qtyRequired) {
        BigDecimal oldQtyRequired = this.qtyRequired;
        this.qtyRequired = qtyRequired;
        propertyChangeSupport.firePropertyChange("qtyRequired", oldQtyRequired, qtyRequired);
    }

    public BigDecimal getQtyRequired() {
        return qtyRequired;
    }

    public void setQtyApplied(BigDecimal qtyApplied) {
        BigDecimal oldQtyApplied = this.qtyApplied;
        this.qtyApplied = qtyApplied;
        propertyChangeSupport.firePropertyChange("qtyApplied", oldQtyApplied, qtyApplied);
    }

    public BigDecimal getQtyApplied() {
        return qtyApplied;
    }

    public void setQtyOpen(BigDecimal qtyOpen) {
        BigDecimal oldQtyOpen = this.qtyOpen;
        this.qtyOpen = qtyOpen;
        propertyChangeSupport.firePropertyChange("qtyOpen", oldQtyOpen, qtyOpen);
    }

    public BigDecimal getQtyOpen() {
        return qtyOpen;
    }

    public void setUom(String uom) {
        uom = getAttributeValue(uom);
        String oldUom = this.uom;
        this.uom = uom;
        propertyChangeSupport.firePropertyChange("uom", oldUom, uom);
    }

    public String getUom() {
        return uom;
    }

    public void setBasis(String basis) {
        basis = getAttributeValue(basis);
        String oldBasis = this.basis;
        this.basis = basis;
        propertyChangeSupport.firePropertyChange("basis", oldBasis, basis);
    }

    public String getBasis() {
        return basis;
    }

    public void setUsagerate(BigDecimal usagerate) {
        BigDecimal oldUsagerate = this.usagerate;
        this.usagerate = usagerate;
        propertyChangeSupport.firePropertyChange("usagerate", oldUsagerate, usagerate);
    }

    public BigDecimal getUsagerate() {
        return usagerate;
    }

    public void setAssignedunits(BigDecimal assignedunits) {
        BigDecimal oldAssignedunits = this.assignedunits;
        this.assignedunits = assignedunits;
        propertyChangeSupport.firePropertyChange("assignedunits", oldAssignedunits, assignedunits);
    }

    public BigDecimal getAssignedunits() {
        return assignedunits;
    }

    public void setStartDate(String startDate) {
        startDate = getAttributeValue(startDate);
        String oldStartDate = this.startDate;
        this.startDate = startDate;
        propertyChangeSupport.firePropertyChange("startDate", oldStartDate, startDate);
    }

    public String getStartDate() {
        return startDate;
    }

    public void setEndDate(String endDate) {
        endDate = getAttributeValue(endDate);
        String oldEndDate = this.endDate;
        this.endDate = endDate;
        propertyChangeSupport.firePropertyChange("endDate", oldEndDate, endDate);
    }

    public String getEndDate() {
        return endDate;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
