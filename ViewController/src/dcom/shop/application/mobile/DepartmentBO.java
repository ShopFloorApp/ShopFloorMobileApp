package dcom.shop.application.mobile;

import java.util.HashMap;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.util.GenericVirtualType;

public class DepartmentBO implements Comparable {
    private String orgCode;
    private String deptId;
    private String deptDesc;

    private String openJob;
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


    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptDesc(String deptDesc) {
        if (!deptDesc.contains("@")) {
            String oldDeptDesc = this.deptDesc;
            this.deptDesc = deptDesc;
            propertyChangeSupport.firePropertyChange("deptDesc", oldDeptDesc, deptDesc);
        }
    }

    public String getDeptDesc() {
        return deptDesc;
    }


    public void setOpenJob(String openJob) {
        this.openJob = openJob;
    }

    public String getOpenJob() {
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

    public void setBOClassRow(HashMap hashMap) {
        this.setDeptDesc((String) hashMap.get("deptdesc"));
        this.setOrgCode((String) hashMap.get("orgcode"));
        this.setDeptId((String) hashMap.get("deptid"));

        this.setOpenJob((String) hashMap.get("openjob"));
        this.setDeptName((String) hashMap.get("deptname"));
    }

    public HashMap getBOClassRow(DepartmentBO department) {
        HashMap map = new HashMap();
        map.put("deptdesc", department.getDeptDesc());
        map.put("orgcode", department.getOrgCode());
        map.put("deptid", department.getDeptId());
        map.put("openjob", department.getOpenJob());
        map.put("deptname", department.getDeptName());
        return map;
    } 

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
