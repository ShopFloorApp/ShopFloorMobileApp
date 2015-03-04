package dcom.shop.application.mobile;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class ItemOnHandBO {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public ItemOnHandBO() {
        super();
    }
    private String ItemNumber;
    private String Description;
    private String SubInv;
    private String Locater;
    private String CostGroup;
    private String Uom;
    private int OnHandQyt;
    private int AvailQty;
    private int PackedQty;
    private int LooseQty;


    public void setItemNumber(String ItemNumber) {
        String oldItemNumber = this.ItemNumber;
        this.ItemNumber = ItemNumber;
        propertyChangeSupport.firePropertyChange("ItemNumber", oldItemNumber, ItemNumber);
    }

    public String getItemNumber() {
        return ItemNumber;
    }

    public void setDescription(String Description) {
        String oldDescription = this.Description;
        this.Description = Description;
        propertyChangeSupport.firePropertyChange("Description", oldDescription, Description);
    }

    public String getDescription() {
        return Description;
    }

    public void setSubInv(String SubInv) {
        String oldSubInv = this.SubInv;
        this.SubInv = SubInv;
        propertyChangeSupport.firePropertyChange("SubInv", oldSubInv, SubInv);
    }

    public String getSubInv() {
        return SubInv;
    }

    public void setLocater(String Locater) {
        String oldLocater = this.Locater;
        this.Locater = Locater;
        propertyChangeSupport.firePropertyChange("Locater", oldLocater, Locater);
    }

    public String getLocater() {
        return Locater;
    }

    public void setCostGroup(String CostGroup) {
        String oldCostGroup = this.CostGroup;
        this.CostGroup = CostGroup;
        propertyChangeSupport.firePropertyChange("CostGroup", oldCostGroup, CostGroup);
    }

    public String getCostGroup() {
        return CostGroup;
    }

    public void setUom(String Uom) {
        String oldUom = this.Uom;
        this.Uom = Uom;
        propertyChangeSupport.firePropertyChange("Uom", oldUom, Uom);
    }

    public String getUom() {
        return Uom;
    }

    public void setOnHandQyt(int OnHandQyt) {
        int oldOnHandQyt = this.OnHandQyt;
        this.OnHandQyt = OnHandQyt;
        propertyChangeSupport.firePropertyChange("OnHandQyt", oldOnHandQyt, OnHandQyt);
    }

    public int getOnHandQyt() {
        return OnHandQyt;
    }

    public void setAvailQty(int AvailQty) {
        int oldAvailQty = this.AvailQty;
        this.AvailQty = AvailQty;
        propertyChangeSupport.firePropertyChange("AvailQty", oldAvailQty, AvailQty);
    }

    public int getAvailQty() {
        return AvailQty;
    }

    public void setPackedQty(int PackedQty) {
        int oldPackedQty = this.PackedQty;
        this.PackedQty = PackedQty;
        propertyChangeSupport.firePropertyChange("PackedQty", oldPackedQty, PackedQty);
    }

    public int getPackedQty() {
        return PackedQty;
    }

    public void setLooseQty(int LooseQty) {
        int oldLooseQty = this.LooseQty;
        this.LooseQty = LooseQty;
        propertyChangeSupport.firePropertyChange("LooseQty", oldLooseQty, LooseQty);
    }

    public int getLooseQty() {
        return LooseQty;
    }
   
    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
