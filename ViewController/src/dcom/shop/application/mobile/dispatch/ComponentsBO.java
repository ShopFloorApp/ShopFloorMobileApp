package dcom.shop.application.mobile.dispatch;

import dcom.shop.application.base.AEntity;

import java.math.BigDecimal;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class ComponentsBO extends AEntity{
    
    private String jobNumber;
    private String jobOps;
    private String component;
    private String description;
    private String dateRequired;
    private String basis;
    private BigDecimal qtyPerAssembly;
    private BigDecimal qtyRequired;
    private BigDecimal qtyIssued;
    private BigDecimal qtyOpen;
    private BigDecimal qtyAllocated;
    private BigDecimal qtyOnHand;
    private String uom;
    private String supplyType;
    private String subInv;
    private String locator;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public ComponentsBO() {
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

    public void setComponent(String components) {
        components= getAttributeValue(components);
        String oldComponents = this.component;
        this.component = components;
        propertyChangeSupport.firePropertyChange("components", oldComponents, components);
    }

    public String getComponent() {
        return component;
    }

    public void setDescription(String description) {
        description= getAttributeValue(description);
        String oldDescription = this.description;
        this.description = description;
        propertyChangeSupport.firePropertyChange("description", oldDescription, description);
    }

    public String getDescription() {
        return description;
    }

    public void setDateRequired(String dateRequired) {
        dateRequired = getAttributeValue(dateRequired);
        String oldDateRequired = this.dateRequired;
        this.dateRequired = dateRequired;
        propertyChangeSupport.firePropertyChange("dateRequired", oldDateRequired, dateRequired);
    }

    public String getDateRequired() {
        return dateRequired;
    }

    public void setBasis(String basis) {
        basis= getAttributeValue(basis);
        String oldBasis = this.basis;
        this.basis = basis;
        propertyChangeSupport.firePropertyChange("basis", oldBasis, basis);
    }

    public String getBasis() {
        return basis;
    }

    public void setQtyPerAssembly(BigDecimal qtyPerAssembly) {
        BigDecimal oldQtyPerAssembly = this.qtyPerAssembly;
        this.qtyPerAssembly = qtyPerAssembly;
        propertyChangeSupport.firePropertyChange("qtyPerAssembly", oldQtyPerAssembly, qtyPerAssembly);
    }

    public BigDecimal getQtyPerAssembly() {
        return qtyPerAssembly;
    }

    public void setQtyRequired(BigDecimal qtyRequired) {
        BigDecimal oldQtyRequired = this.qtyRequired;
        this.qtyRequired = qtyRequired;
        propertyChangeSupport.firePropertyChange("qtyRequired", oldQtyRequired, qtyRequired);
    }

    public BigDecimal getQtyRequired() {
        return qtyRequired;
    }

    public void setQtyIssued(BigDecimal qtyIssued) {
        BigDecimal oldQtyIssued = this.qtyIssued;
        this.qtyIssued = qtyIssued;
        propertyChangeSupport.firePropertyChange("qtyIssued", oldQtyIssued, qtyIssued);
    }

    public BigDecimal getQtyIssued() {
        return qtyIssued;
    }

    public void setQtyOpen(BigDecimal qtyOpen) {
        BigDecimal oldQtyOpen = this.qtyOpen;
        this.qtyOpen = qtyOpen;
        propertyChangeSupport.firePropertyChange("qtyOpen", oldQtyOpen, qtyOpen);
    }

    public BigDecimal getQtyOpen() {
        return qtyOpen;
    }

    public void setQtyAllocated(BigDecimal qtyAllocated) {
        BigDecimal oldQtyAllocated = this.qtyAllocated;
        this.qtyAllocated = qtyAllocated;
        propertyChangeSupport.firePropertyChange("qtyAllocated", oldQtyAllocated, qtyAllocated);
    }

    public BigDecimal getQtyAllocated() {
        return qtyAllocated;
    }

    public void setQtyOnHand(BigDecimal qtyOnHand) {
        BigDecimal oldQtyOnHand = this.qtyOnHand;
        this.qtyOnHand = qtyOnHand;
        propertyChangeSupport.firePropertyChange("qtyOnHand", oldQtyOnHand, qtyOnHand);
    }

    public BigDecimal getQtyOnHand() {
        return qtyOnHand;
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

    public void setSupplyType(String supplyType) {
        supplyType = getAttributeValue(supplyType);
        String oldSupplyType = this.supplyType;
        this.supplyType = supplyType;
        propertyChangeSupport.firePropertyChange("supplyType", oldSupplyType, supplyType);
    }

    public String getSupplyType() {
        return supplyType;
    }

    public void setSubInv(String subInv) {
        subInv= getAttributeValue(subInv);
        String oldSubInv = this.subInv;
        this.subInv = subInv;
        propertyChangeSupport.firePropertyChange("subInv", oldSubInv, subInv);
    }

    public String getSubInv() {
        return subInv;
    }

    public void setLocator(String locator) {
        locator= getAttributeValue(locator);
        String oldLocator = this.locator;
        this.locator = locator;
        propertyChangeSupport.firePropertyChange("locator", oldLocator, locator);
    }

    public String getLocator() {
        return locator;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
