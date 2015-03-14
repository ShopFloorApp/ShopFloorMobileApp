package dcom.shop.application.mobile.transaction.receiving;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class RequisitionBO {
    private String ouName;
    private String reqNumber;
    private String lines;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public RequisitionBO() {
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

    public void setReqNumber(String reqNumber) {
        String oldReqNumber = this.reqNumber;
        this.reqNumber = reqNumber;
        propertyChangeSupport.firePropertyChange("reqNumber", oldReqNumber, reqNumber);
    }

    public String getReqNumber() {
        return reqNumber;
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
