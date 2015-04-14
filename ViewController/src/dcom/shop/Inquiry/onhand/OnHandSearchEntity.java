package dcom.shop.Inquiry.onhand;

import dcom.shop.application.base.AEntity;

import java.math.BigDecimal;

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

    public void setONHANDQTY(BigDecimal ONHANDQTY) {
        BigDecimal oldONHANDQTY = this.ONHANDQTY;
        this.ONHANDQTY = ONHANDQTY;
        propertyChangeSupport.firePropertyChange("ONHANDQTY", oldONHANDQTY, ONHANDQTY);
    }

    public BigDecimal getONHANDQTY() {
        return ONHANDQTY;
    }

    public void setAVAILABLEQTY(BigDecimal AVAILABLEQTY) {
        BigDecimal oldAVAILABLEQTY = this.AVAILABLEQTY;
        this.AVAILABLEQTY = AVAILABLEQTY;
        propertyChangeSupport.firePropertyChange("AVAILABLEQTY", oldAVAILABLEQTY, AVAILABLEQTY);
    }

    public BigDecimal getAVAILABLEQTY() {
        return AVAILABLEQTY;
    }

    private String ITEMDESC;    
    private String SUBINV;    
    private String LOCATOR;    
    private String COSTGROUP;    
    private BigDecimal ONHANDQTY;  
    private BigDecimal AVAILABLEQTY;


    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
