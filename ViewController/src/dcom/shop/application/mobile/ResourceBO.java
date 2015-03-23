package dcom.shop.application.mobile;

import java.util.HashMap;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.util.GenericVirtualType;

public class ResourceBO implements Comparable {
    private String Whse;
    private String ResourceCode;
    private String DeptCode;
    private String Description;
    private String ResourceType;
    private String UOM;
    private String ChargeType;
    private String Basis;
    private String instance;
    private GenericVirtualType Attributes;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void setWhse(String Whse) {
        String oldWhse = this.Whse;
        this.Whse = Whse;
        propertyChangeSupport.firePropertyChange("Whse", oldWhse, Whse);
    }

    public void setInstance(String instance) {
        String oldInstance = this.instance;
        this.instance = instance;
        propertyChangeSupport.firePropertyChange("instance", oldInstance, instance);
    }

    public String getInstance() {
        return instance;
    }

    public String getWhse() {
        return Whse;
    }

    public void setResourceCode(String ResourceCode) {
        String oldResourceCode = this.ResourceCode;
        this.ResourceCode = ResourceCode;
        propertyChangeSupport.firePropertyChange("ResourceCode", oldResourceCode, ResourceCode);
    }

    public String getResourceCode() {
        return ResourceCode;
    }

    public void setDeptCode(String DeptCode) {
        String oldDeptCode = this.DeptCode;
        this.DeptCode = DeptCode;
        propertyChangeSupport.firePropertyChange("DeptCode", oldDeptCode, DeptCode);
    }

    public String getDeptCode() {
        return DeptCode;
    }


    public void setDescription(String Description) {
        String oldDescription = this.Description;
        this.Description = Description;
        propertyChangeSupport.firePropertyChange("Description", oldDescription, Description);
    }

    public String getDescription() {
        return Description;
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

    public void setResourceType(String ResourceType) {
        String oldResourceType = this.ResourceType;
        this.ResourceType = ResourceType;
        propertyChangeSupport.firePropertyChange("ResourceType", oldResourceType, ResourceType);
    }

    public String getResourceType() {
        return ResourceType;
    }

    public void setUOM(String UOM) {
        String oldUOM = this.UOM;
        this.UOM = UOM;
        propertyChangeSupport.firePropertyChange("UOM", oldUOM, UOM);
    }

    public String getUOM() {
        return UOM;
    }

    public void setChargeType(String ChargeType) {
        String oldChargeType = this.ChargeType;
        this.ChargeType = ChargeType;
        propertyChangeSupport.firePropertyChange("ChargeType", oldChargeType, ChargeType);
    }

    public String getChargeType() {
        return ChargeType;
    }

    public void setBasis(String Basis) {
        String oldBasis = this.Basis;
        this.Basis = Basis;
        propertyChangeSupport.firePropertyChange("Basis", oldBasis, Basis);
    }

    public String getBasis() {
        return Basis;
    }

    public void setAttributes(GenericVirtualType Attributes) {
        GenericVirtualType oldAttributes = this.Attributes;
        this.Attributes = Attributes;
        propertyChangeSupport.firePropertyChange("Attributes", oldAttributes, Attributes);
    }

    public GenericVirtualType getAttributes() {
        return Attributes;
    }

    public ResourceBO() {
        super();
    }
    
    public void setBOClassRow(HashMap hashMap) {
        this.setWhse((String) hashMap.get("whse"));
        this.setResourceCode((String) hashMap.get("resourcecode"));
        this.setDeptCode((String) hashMap.get("deptcode"));
        this.setDescription((String) hashMap.get("description"));
        this.setResourceType((String) hashMap.get("resourcetype"));
        this.setUOM((String) hashMap.get("uom"));
        this.setChargeType((String) hashMap.get("chargetype"));
        this.setBasis((String) hashMap.get("basis"));
    }

    public HashMap getBOClassRow(ResourceBO resource) {
        HashMap map = new HashMap();
        map.put("whse", resource.getWhse());
        map.put("resourcecode", resource.getResourceCode());
        map.put("deptcode", resource.getDeptCode());
        map.put("description", resource.getDescription());
        map.put("resourcetype", resource.getResourceType());
        map.put("uom", resource.getUOM());
        map.put("chargetype", resource.getChargeType());
        map.put("basis", resource.getBasis());
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
