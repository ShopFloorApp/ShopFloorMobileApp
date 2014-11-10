package dcom.shop.application.mobile;

import java.util.HashMap;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.util.GenericVirtualType;

public class CategoryCodeBO implements Comparable {
    
    private String CatgCode;
    private String StructureId;
    private String Desc;
    private GenericVirtualType Attributes;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);


    public CategoryCodeBO() {
        super();
    }
    
    public void setBOClassRow(HashMap hashMap) {
        this.setCatgCode((String) hashMap.get("catgcode"));
        this.setStructureId((String) hashMap.get("structureid"));
        this.setDesc((String) hashMap.get("desc"));
    }

    public HashMap getBOClassRow(CategoryCodeBO categoryCodes) {
        HashMap map = new HashMap();
        map.put("catgcode", categoryCodes.getCatgCode());
        map.put("structureid", categoryCodes.getStructureId());
        map.put("desc", categoryCodes.getDesc());
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

    public void setDesc(String Desc) {
        String oldDesc = this.Desc;
        this.Desc = Desc;
        propertyChangeSupport.firePropertyChange("Desc", oldDesc, Desc);
    }

    public String getDesc() {
        return Desc;
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
