package dcom.shop.application.mobile;

import java.math.BigDecimal;

import java.util.HashMap;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.util.GenericVirtualType;

public class DepartmentBO implements Comparable {
    private String orgCode;
    private BigDecimal deptId;
    private String deptDesc;

    private BigDecimal openJob;
    private String deptName;
    private GenericVirtualType Attributes;

    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);


    public void setOrgCode(String orgCode) {
        String oldOrgCode = this.orgCode;
        this.orgCode = orgCode;
        propertyChangeSupport.firePropertyChange("orgCode", oldOrgCode, orgCode);
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setDeptId(BigDecimal deptId) {
        BigDecimal oldDeptId = this.deptId;
        this.deptId = deptId;
        propertyChangeSupport.firePropertyChange("deptId", oldDeptId, deptId);
    }

    public BigDecimal getDeptId() {
        return deptId;
    }

    public void setDeptDesc(String deptDesc) {
        String oldDeptDesc = this.deptDesc;
        this.deptDesc = deptDesc;
        propertyChangeSupport.firePropertyChange("deptDesc", oldDeptDesc, deptDesc);
    }

    public String getDeptDesc() {
        return deptDesc;
    }

    public void setOpenJob(BigDecimal openJob) {
        BigDecimal oldOpenJob = this.openJob;
        this.openJob = openJob;
        propertyChangeSupport.firePropertyChange("openJob", oldOpenJob, openJob);
    }

    public BigDecimal getOpenJob() {
        return openJob;
    }

    public void setDeptName(String deptName) {
        String oldDeptName = this.deptName;
        this.deptName = deptName;
        propertyChangeSupport.firePropertyChange("deptName", oldDeptName, deptName);
    }

    public String getDeptName() {
        return deptName;
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


    public void setAttributes(GenericVirtualType Attributes) {
        GenericVirtualType oldAttributes = this.Attributes;
        this.Attributes = Attributes;
        propertyChangeSupport.firePropertyChange("Attributes", oldAttributes, Attributes);
    }

    public GenericVirtualType getAttributes() {
        return Attributes;
    }

    public DepartmentBO() {
        super();
    }

    /* public void setBOClassRow(HashMap hashMap) {
        this.setWhse((String) hashMap.get("whse"));
        this.setDeptCode((String) hashMap.get("deptcode"));
        this.setDesc((String) hashMap.get("desc"));
    }

    public HashMap getBOClassRow(DepartmentBO department) {
        HashMap map = new HashMap();
        map.put("whse", department.getWhse());
        map.put("deptcode", department.getDeptCode());
        map.put("desc", department.getDesc());
        return map;
    } */

    public int compareTo(Object o) {
        // TODO Implement this method
        return 0;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
