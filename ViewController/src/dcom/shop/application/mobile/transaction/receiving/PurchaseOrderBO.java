package dcom.shop.application.mobile.transaction.receiving;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class PurchaseOrderBO {
    private String ouName;
    private String poNumber;
    private String lines;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public PurchaseOrderBO() {
        super();
    }

    public void setOuName(String ouName) {
        String oldOuName = this.ouName;
        this.ouName = ouName;
        propertyChangeSupport.firePropertyChange("ouName", oldOuName, ouName);
    }

    public String getOuName() {
        return ouName;
    }

    public void setPoNumber(String poNumber) {
        String oldPoNumber = this.poNumber;
        this.poNumber = poNumber;
        propertyChangeSupport.firePropertyChange("poNumber", oldPoNumber, poNumber);
    }

    public String getPoNumber() {
        return poNumber;
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
