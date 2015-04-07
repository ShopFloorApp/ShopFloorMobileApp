package dcom.shop.application.mobile.transaction.receiving;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class SalesOrderBO {
    private String orderType;
    private String orderNumber;
    private SalesOrderLineBO[] lines;
    private String[] orderLines;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public SalesOrderBO() {
        super();
    }

    public void setOrderLines(String[] orderLines) {
        this.orderLines = orderLines;
    }

    public String[] getOrderLines() {
        return orderLines;
    }

    public void setPropertyChangeSupport(PropertyChangeSupport propertyChangeSupport) {
        this.propertyChangeSupport = propertyChangeSupport;
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }

    public void setOrderType(String orderType) {
        String oldOrderType = this.orderType;
        this.orderType = orderType;
        propertyChangeSupport.firePropertyChange("orderType", oldOrderType, orderType);
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderNumber(String orderNumber) {
        String oldOrderNumber = this.orderNumber;
        this.orderNumber = orderNumber;
        propertyChangeSupport.firePropertyChange("orderNumber", oldOrderNumber, orderNumber);
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setLines(SalesOrderLineBO[] lines) {
        SalesOrderLineBO[] oldLines = this.lines;
        this.lines = lines;
        propertyChangeSupport.firePropertyChange("lines", oldLines, lines);
    }

    public SalesOrderLineBO[] getLines() {
        return lines;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
