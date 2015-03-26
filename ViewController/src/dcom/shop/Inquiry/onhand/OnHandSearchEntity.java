package dcom.shop.Inquiry.onhand;

import dcom.shop.application.base.AEntity;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class OnHandSearchEntity  extends AEntity  {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public OnHandSearchEntity() {
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

    public void setITEMDESC(String ITEMDESC) {
        String oldITEMDESC = this.ITEMDESC;
        this.ITEMDESC = ITEMDESC;
        propertyChangeSupport.firePropertyChange("ITEMDESC", oldITEMDESC, ITEMDESC);
    }

    public String getITEMDESC() {
        return ITEMDESC;
    }

    public void setSUBINV(String SUBINV) {
        String oldSUBINV = this.SUBINV;
        this.SUBINV = SUBINV;
        propertyChangeSupport.firePropertyChange("SUBINV", oldSUBINV, SUBINV);
    }

    public String getSUBINV() {
        return SUBINV;
    }

    public void setLOCATOR(String LOCATOR) {
        LOCATOR=getAttributeValue(LOCATOR);
        String oldLOCATOR = this.LOCATOR;
        this.LOCATOR = LOCATOR;
        propertyChangeSupport.firePropertyChange("LOCATOR", oldLOCATOR, LOCATOR);
    }

    public String getLOCATOR() {
        return LOCATOR;
    }

    public void setCOSTGROUP(String COSTGROUP) {
        COSTGROUP=getAttributeValue(COSTGROUP);
        String oldCOSTGROUP = this.COSTGROUP;
        this.COSTGROUP = COSTGROUP;
        propertyChangeSupport.firePropertyChange("COSTGROUP", oldCOSTGROUP, COSTGROUP);
    }

    public String getCOSTGROUP() {
        return COSTGROUP;
    }

    public void setONHANDQTY(int ONHANDQTY) {
        int oldONHANDQTY = this.ONHANDQTY;
        this.ONHANDQTY = ONHANDQTY;
        propertyChangeSupport.firePropertyChange("ONHANDQTY", oldONHANDQTY, ONHANDQTY);
    }

    public int getONHANDQTY() {
        return ONHANDQTY;
    }

    public void setAVAILABLEQTY(int AVAILABLEQTY) {
        int oldAVAILABLEQTY = this.AVAILABLEQTY;
        this.AVAILABLEQTY = AVAILABLEQTY;
        propertyChangeSupport.firePropertyChange("AVAILABLEQTY", oldAVAILABLEQTY, AVAILABLEQTY);
    }

    public int getAVAILABLEQTY() {
        return AVAILABLEQTY;
    }
    private String ITEMDESC;    
    private String SUBINV;    
    private String LOCATOR;    
    private String COSTGROUP;    
    private int ONHANDQTY;  
    private int AVAILABLEQTY;


    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
