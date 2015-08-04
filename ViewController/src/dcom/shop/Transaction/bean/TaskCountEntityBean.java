package dcom.shop.Transaction.bean;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class TaskCountEntityBean {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public TaskCountEntityBean() {
        super();
    }
    private String ITEM;

    public void setITEM(String ITEM) {
        String oldITEM = this.ITEM;
        this.ITEM = ITEM;
        propertyChangeSupport.firePropertyChange("ITEM", oldITEM, ITEM);
    }

    public String getITEM() {
        return ITEM;
    }

    public void setSUBINVENTORY(String SUBINVENTORY) {
        String oldSUBINVENTORY = this.SUBINVENTORY;
        this.SUBINVENTORY = SUBINVENTORY;
        propertyChangeSupport.firePropertyChange("SUBINVENTORY", oldSUBINVENTORY, SUBINVENTORY);
    }

    public String getSUBINVENTORY() {
        return SUBINVENTORY;
    }

    public void setLOCATOR(String LOCATOR) {
        String oldLOCATOR = this.LOCATOR;
        this.LOCATOR = LOCATOR;
        propertyChangeSupport.firePropertyChange("LOCATOR", oldLOCATOR, LOCATOR);
    }

    public String getLOCATOR() {
        return LOCATOR;
    }

    public void setLPN(String LPN) {
        String oldLPN = this.LPN;
        this.LPN = LPN;
        propertyChangeSupport.firePropertyChange("LPN", oldLPN, LPN);
    }

    public String getLPN() {
        return LPN;
    }

    public void setCCNAME(String CCNAME) {
        String oldCCNAME = this.CCNAME;
        this.CCNAME = CCNAME;
        propertyChangeSupport.firePropertyChange("CCNAME", oldCCNAME, CCNAME);
    }

    public String getCCNAME() {
        return CCNAME;
    }
    private String SUBINVENTORY;
    private String LOCATOR;
    private String LPN;
    private String CCNAME;
    private String UOM;
    private float OHQTY;

    public void setOHQTY(float OHQTY) {
        float oldOHQTY = this.OHQTY;
        this.OHQTY = OHQTY;
        propertyChangeSupport.firePropertyChange("OHQTY", oldOHQTY, OHQTY);
    }

    public float getOHQTY() {
        return OHQTY;
    }

    public void setITEM_DESC(String ITEM_DESC) {
        String oldITEM_DESC = this.ITEM_DESC;
        this.ITEM_DESC = ITEM_DESC;
        propertyChangeSupport.firePropertyChange("ITEM_DESC", oldITEM_DESC, ITEM_DESC);
    }

    public String getITEM_DESC() {
        return ITEM_DESC;
    }
    private String ITEM_DESC;
    public void setUOM(String UOM) {
        String oldUOM = this.UOM;
        this.UOM = UOM;
        propertyChangeSupport.firePropertyChange("UOM", oldUOM, UOM);
    }

    public String getUOM() {
        return UOM;
    }

   

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
