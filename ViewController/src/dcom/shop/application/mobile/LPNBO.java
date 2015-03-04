package dcom.shop.application.mobile;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.util.GenericVirtualType;

public class LPNBO {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public LPNBO() {
        super();
    }
    private String Whse;
    private String LPNNum;
    private String LPNStatus;
    private String Subinv;
    private String Locator;
    private GenericVirtualType Attributes;


    public void setWhse(String Whse) {
        String oldWhse = this.Whse;
        this.Whse = Whse;
        propertyChangeSupport.firePropertyChange("Whse", oldWhse, Whse);
    }

    public String getWhse() {
        return Whse;
    }

    public void setLPNNum(String LPNNum) {
        String oldLPNNum = this.LPNNum;
        this.LPNNum = LPNNum;
        propertyChangeSupport.firePropertyChange("LPNNum", oldLPNNum, LPNNum);
    }

    public String getLPNNum() {
        return LPNNum;
    }

    public void setLPNStatus(String LPNStatus) {
        String oldLPNStatus = this.LPNStatus;
        this.LPNStatus = LPNStatus;
        propertyChangeSupport.firePropertyChange("LPNStatus", oldLPNStatus, LPNStatus);
    }

    public String getLPNStatus() {
        return LPNStatus;
    }

    public void setSubinv(String Subinv) {
        String oldSubinv = this.Subinv;
        this.Subinv = Subinv;
        propertyChangeSupport.firePropertyChange("Subinv", oldSubinv, Subinv);
    }

    public String getSubinv() {
        return Subinv;
    }

    public void setLocator(String Locator) {
        String oldLocator = this.Locator;
        this.Locator = Locator;
        propertyChangeSupport.firePropertyChange("Locator", oldLocator, Locator);
    }

    public String getLocator() {
        return Locator;
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
