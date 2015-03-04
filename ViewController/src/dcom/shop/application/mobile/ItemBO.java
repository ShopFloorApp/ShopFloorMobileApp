package dcom.shop.application.mobile;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.util.GenericVirtualType;

public class ItemBO {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public ItemBO() {
        super();
    }
    private String InventoryItemId;
    private String ItemNum;
    private String Desc;
    private String PrimaryUOMCode;
    private GenericVirtualType Attributes;


    public void setInventoryItemId(String InventoryItemId) {
        String oldInventoryItemId = this.InventoryItemId;
        this.InventoryItemId = InventoryItemId;
        propertyChangeSupport.firePropertyChange("InventoryItemId", oldInventoryItemId, InventoryItemId);
    }

    public String getInventoryItemId() {
        return InventoryItemId;
    }

    public void setItemNum(String ItemNum) {
        String oldItemNum = this.ItemNum;
        this.ItemNum = ItemNum;
        propertyChangeSupport.firePropertyChange("ItemNum", oldItemNum, ItemNum);
    }

    public String getItemNum() {
        return ItemNum;
    }

    public void setDesc(String Desc) {
        String oldDesc = this.Desc;
        this.Desc = Desc;
        propertyChangeSupport.firePropertyChange("Desc", oldDesc, Desc);
    }

    public String getDesc() {
        return Desc;
    }

    public void setPrimaryUOMCode(String PrimaryUOMCode) {
        String oldPrimaryUOMCode = this.PrimaryUOMCode;
        this.PrimaryUOMCode = PrimaryUOMCode;
        propertyChangeSupport.firePropertyChange("PrimaryUOMCode", oldPrimaryUOMCode, PrimaryUOMCode);
    }

    public String getPrimaryUOMCode() {
        return PrimaryUOMCode;
    }

    public void setAttributes(GenericVirtualType Attributes) {
        GenericVirtualType oldAttributes = this.Attributes;
        this.Attributes = Attributes;
        propertyChangeSupport.firePropertyChange("Attributes", oldAttributes, Attributes);
    }

    public GenericVirtualType getAttributes() {
        return Attributes;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
