package dcom.shop.application.mobile;

import oracle.adfmf.java.beans.PropertyChangeSupport;

public class PackAndDirectShipBO {
    public PackAndDirectShipBO() {
        super();
    }
    
    private String dockDoor;
    private String lpn;
    private String subInventory;
    private String locator;
    private String item;
    private String quantity;
    private String lotNumber;
    private String serialNumber;
    private String orderNumber;
    private String orderLineNumber;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);


    public void setDockDoor(String dockDoor) {
        this.dockDoor = dockDoor;
    }

    public String getDockDoor() {
        return dockDoor;
    }

    public void setLpn(String lpn) {
        this.lpn = lpn;
    }

    public String getLpn() {
        return lpn;
    }

    public void setSubInventory(String subInventory) {
        this.subInventory = subInventory;
    }

    public String getSubInventory() {
        return subInventory;
    }

    public void setLocator(String locator) {
        this.locator = locator;
    }

    public String getLocator() {
        return locator;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderLineNumber(String orderLineNumber) {
        this.orderLineNumber = orderLineNumber;
    }

    public String getOrderLineNumber() {
        return orderLineNumber;
    }

    public void setPropertyChangeSupport(PropertyChangeSupport propertyChangeSupport) {
        this.propertyChangeSupport = propertyChangeSupport;
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }

}
