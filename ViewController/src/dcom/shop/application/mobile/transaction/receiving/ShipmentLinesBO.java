package dcom.shop.application.mobile.transaction.receiving;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class ShipmentLinesBO {
    private String item;
    private String itemDecs;
    private String uom;
    private String lpn;
    private String subInv;
    private String locator;
    private String qty;
    private String docRefLine;
    private String shipmentLine;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public ShipmentLinesBO() {
        super();
    }

    public void setItem(String item) {
        String oldItem = this.item;
        this.item = item;
        propertyChangeSupport.firePropertyChange("item", oldItem, item);
    }

    public String getItem() {
        return item;
    }

    public void setItemDecs(String itemDecs) {
        String oldItemDecs = this.itemDecs;
        this.itemDecs = itemDecs;
        propertyChangeSupport.firePropertyChange("itemDecs", oldItemDecs, itemDecs);
    }

    public String getItemDecs() {
        return itemDecs;
    }

    public void setUom(String uom) {
        String oldUom = this.uom;
        this.uom = uom;
        propertyChangeSupport.firePropertyChange("uom", oldUom, uom);
    }

    public String getUom() {
        return uom;
    }

    public void setLpn(String lpn) {
        String oldLpn = this.lpn;
        this.lpn = lpn;
        propertyChangeSupport.firePropertyChange("lpn", oldLpn, lpn);
    }

    public String getLpn() {
        return lpn;
    }

    public void setSubInv(String subInv) {
        String oldSubInv = this.subInv;
        this.subInv = subInv;
        propertyChangeSupport.firePropertyChange("subInv", oldSubInv, subInv);
    }

    public String getSubInv() {
        return subInv;
    }

    public void setLocator(String locator) {
        String oldLocator = this.locator;
        this.locator = locator;
        propertyChangeSupport.firePropertyChange("locator", oldLocator, locator);
    }

    public String getLocator() {
        return locator;
    }

    public void setQty(String qty) {
        String oldQty = this.qty;
        this.qty = qty;
        propertyChangeSupport.firePropertyChange("qty", oldQty, qty);
    }

    public String getQty() {
        return qty;
    }

    public void setDocRefLine(String docRefLine) {
        String oldDocRefLine = this.docRefLine;
        this.docRefLine = docRefLine;
        propertyChangeSupport.firePropertyChange("docRefLine", oldDocRefLine, docRefLine);
    }

    public String getDocRefLine() {
        return docRefLine;
    }

    public void setShipmentLine(String shipmentLine) {
        String oldShipmentLine = this.shipmentLine;
        this.shipmentLine = shipmentLine;
        propertyChangeSupport.firePropertyChange("shipmentLine", oldShipmentLine, shipmentLine);
    }

    public String getShipmentLine() {
        return shipmentLine;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
