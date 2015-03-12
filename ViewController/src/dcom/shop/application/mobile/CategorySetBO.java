package dcom.shop.application.mobile;

import java.util.HashMap;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.util.GenericVirtualType;

public class CategorySetBO implements Comparable {
    private String CatgSetName;
    private String StructureId;
    private String Description;
    private String DefCatgCode;
    private String ControlLevel;
    private String MultiCatgAssign;
    private GenericVirtualType Attributes;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public CategorySetBO() {
        super();
    }

    public void setCatgSetName(String CatgSetName) {
        String oldCatgSetName = this.CatgSetName;
        this.CatgSetName = CatgSetName;
        propertyChangeSupport.firePropertyChange("CatgSetName", oldCatgSetName, CatgSetName);
    }

    public String getCatgSetName() {
        return CatgSetName;
    }

    public void setStructureId(String StructureId) {
        String oldStructureId = this.StructureId;
        this.StructureId = StructureId;
        propertyChangeSupport.firePropertyChange("StructureId", oldStructureId, StructureId);
    }

    public String getStructureId() {
        return StructureId;
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

    public void setDefCatgCode(String DefCatgCode) {
        String oldDefCatgCode = this.DefCatgCode;
        this.DefCatgCode = DefCatgCode;
        propertyChangeSupport.firePropertyChange("DefCatgCode", oldDefCatgCode, DefCatgCode);
    }

    public String getDefCatgCode() {
        return DefCatgCode;
    }

    public void setControlLevel(String ControlLevel) {
        String oldControlLevel = this.ControlLevel;
        this.ControlLevel = ControlLevel;
        propertyChangeSupport.firePropertyChange("ControlLevel", oldControlLevel, ControlLevel);
    }

    public String getControlLevel() {
        return ControlLevel;
    }

    public void setMultiCatgAssign(String MultiCatgAssign) {
        String oldMultiCatgAssign = this.MultiCatgAssign;
        this.MultiCatgAssign = MultiCatgAssign;
        propertyChangeSupport.firePropertyChange("MultiCatgAssign", oldMultiCatgAssign, MultiCatgAssign);
    }

    public String getMultiCatgAssign() {
        return MultiCatgAssign;
    }

    public void setAttributes(GenericVirtualType Attributes) {
        GenericVirtualType oldAttributes = this.Attributes;
        this.Attributes = Attributes;
        propertyChangeSupport.firePropertyChange("Attributes", oldAttributes, Attributes);
    }

    public GenericVirtualType getAttributes() {
        return Attributes;
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

    public void setBOClassRow(HashMap hashMap) {
        this.setCatgSetName((String) hashMap.get("catgsetname"));
        this.setStructureId((String) hashMap.get("structureid"));
        this.setDescription((String) hashMap.get("description"));
        this.setDefCatgCode((String) hashMap.get("defcatgcode"));
        this.setControlLevel((String) hashMap.get("controllevel"));
        this.setMultiCatgAssign((String) hashMap.get("multicatgassign"));
    }

    public HashMap getBOClassRow(CategorySetBO categorySet) {
        HashMap map = new HashMap();
        map.put("catgsetname", categorySet.getCatgSetName());
        map.put("structureid", categorySet.getStructureId());
        map.put("description", categorySet.getDescription());
        map.put("defcatgcode", categorySet.getDefCatgCode());
        map.put("controllevel", categorySet.getControlLevel());
        map.put("multicatgassign", categorySet.getMultiCatgAssign());
        return map;
    }
}
