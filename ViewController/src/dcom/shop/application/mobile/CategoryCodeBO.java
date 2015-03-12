package dcom.shop.application.mobile;

import java.util.HashMap;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.util.GenericVirtualType;

public class CategoryCodeBO implements Comparable {
    
    private String CatgCode;
    private String StructureId;
    private String Description;
    private GenericVirtualType Attributes;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);


    public CategoryCodeBO() {
        super();
    }
    
    public void setBOClassRow(HashMap hashMap) {
        this.setCatgCode((String) hashMap.get("catgcode"));
        this.setStructureId((String) hashMap.get("structureid"));
        this.setDescription((String) hashMap.get("description"));
    }

    public HashMap getBOClassRow(CategoryCodeBO categoryCodes) {
        HashMap map = new HashMap();
        map.put("catgcode", categoryCodes.getCatgCode());
        map.put("structureid", categoryCodes.getStructureId());
        map.put("description", categoryCodes.getDescription());
        return map;
    }

    public void setCatgCode(String CatgCode) {
        String oldCatgCode = this.CatgCode;
        this.CatgCode = CatgCode;
        propertyChangeSupport.firePropertyChange("CatgCode", oldCatgCode, CatgCode);
    }

    public String getCatgCode() {
        return CatgCode;
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
}
