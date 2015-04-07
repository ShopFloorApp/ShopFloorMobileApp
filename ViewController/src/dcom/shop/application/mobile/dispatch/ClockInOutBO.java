package dcom.shop.application.mobile.dispatch;

import dcom.shop.application.base.AEntity;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class ClockInOutBO extends AEntity{
    private String porgCode;
    private String pDeptCode;
    private String pJobOp;
    private String pInstance;
    private String pEmp;
    private String pResource;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public ClockInOutBO() {
        super();
    }


    public void setPorgCode(String porgCode) {
        String oldPorgCode = this.porgCode;
        this.porgCode = porgCode;
        propertyChangeSupport.firePropertyChange("porgCode", oldPorgCode, porgCode);
    }

    public String getPorgCode() {
        return porgCode;
    }

    public void setPDeptCode(String pDeptCode) {
        String oldPDeptCode = this.pDeptCode;
        this.pDeptCode = pDeptCode;
        propertyChangeSupport.firePropertyChange("pDeptCode", oldPDeptCode, pDeptCode);
    }

    public String getPDeptCode() {
        return pDeptCode;
    }

    public void setPJobOp(String pJobOp) {
        String oldPJobOp = this.pJobOp;
        this.pJobOp = pJobOp;
        propertyChangeSupport.firePropertyChange("pJobOp", oldPJobOp, pJobOp);
    }

    public String getPJobOp() {
        return pJobOp;
    }

    public void setPInstance(String pInstance) {
        String oldPInstance = this.pInstance;
        this.pInstance = pInstance;
        propertyChangeSupport.firePropertyChange("pInstance", oldPInstance, pInstance);
    }

    public String getPInstance() {
        return pInstance;
    }

    public void setPEmp(String pEmp) {
        String oldPEmp = this.pEmp;
        this.pEmp = pEmp;
        propertyChangeSupport.firePropertyChange("pEmp", oldPEmp, pEmp);
    }

    public String getPEmp() {
        return pEmp;
    }

    public void setPResource(String pResource) {
        String oldPResource = this.pResource;
        this.pResource = pResource;
        propertyChangeSupport.firePropertyChange("pResource", oldPResource, pResource);
    }

    public String getPResource() {
        return pResource;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
