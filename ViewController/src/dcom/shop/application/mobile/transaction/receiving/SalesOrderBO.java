package dcom.shop.application.mobile.transaction.receiving;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class SalesOrderBO {
    private String orderType;
    private String orderNumber;
    private String lines;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public SalesOrderBO() {
        super();
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

    public void setLines(String lines) {
        String oldLines = this.lines;
        this.lines = lines;
        propertyChangeSupport.firePropertyChange("lines", oldLines, lines);
    }

    public String getLines() {
        return lines;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
