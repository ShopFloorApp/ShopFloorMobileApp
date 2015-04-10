package dcom.shop.Inquiry.onhand;

import dcom.shop.application.base.AEntity;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class OnHandDetailsEntity  extends AEntity  {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public OnHandDetailsEntity() {
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

    public void setUOM(String UOM) {
        String oldUOM = this.UOM;
        this.UOM = UOM;
        propertyChangeSupport.firePropertyChange("UOM", oldUOM, UOM);
    }

    public String getUOM() {
        return UOM;
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

    public void setONHANDQTY(Integer ONHANDQTY) {
        Integer oldONHANDQTY = this.ONHANDQTY;
        this.ONHANDQTY = ONHANDQTY;
        propertyChangeSupport.firePropertyChange("ONHANDQTY", oldONHANDQTY, ONHANDQTY);
    }

    public Integer getONHANDQTY() {
        return ONHANDQTY;
    }

    public void setAVAILABLEQTY(Integer AVAILABLEQTY) {
        Integer oldAVAILABLEQTY = this.AVAILABLEQTY;
        this.AVAILABLEQTY = AVAILABLEQTY;
        propertyChangeSupport.firePropertyChange("AVAILABLEQTY", oldAVAILABLEQTY, AVAILABLEQTY);
    }

    public Integer getAVAILABLEQTY() {
        return AVAILABLEQTY;
    }

    public void setPACKEDQTY(Integer PACKEDQTY) {
        Integer oldPACKEDQTY = this.PACKEDQTY;
        this.PACKEDQTY = PACKEDQTY;
        propertyChangeSupport.firePropertyChange("PACKEDQTY", oldPACKEDQTY, PACKEDQTY);
    }

    public Integer getPACKEDQTY() {
        return PACKEDQTY;
    }

    public void setLOOSEQTY(Integer LOOSEQTY) {
        Integer oldLOOSEQTY = this.LOOSEQTY;
        this.LOOSEQTY = LOOSEQTY;
        propertyChangeSupport.firePropertyChange("LOOSEQTY", oldLOOSEQTY, LOOSEQTY);
    }

    public Integer getLOOSEQTY() {
        return LOOSEQTY;
    }

    public void setRECEIVINGQTY(Integer RECEIVINGQTY) {
        Integer oldRECEIVINGQTY = this.RECEIVINGQTY;
        this.RECEIVINGQTY = RECEIVINGQTY;
        propertyChangeSupport.firePropertyChange("RECEIVINGQTY", oldRECEIVINGQTY, RECEIVINGQTY);
    }

    public Integer getRECEIVINGQTY() {
        return RECEIVINGQTY;
    }

    public void setINBOUNDQTY(Integer INBOUNDQTY) {
        Integer oldINBOUNDQTY = this.INBOUNDQTY;
        this.INBOUNDQTY = INBOUNDQTY;
        propertyChangeSupport.firePropertyChange("INBOUNDQTY", oldINBOUNDQTY, INBOUNDQTY);
    }

    public Integer getINBOUNDQTY() {
        return INBOUNDQTY;
    }
    private String ITEMDESC;
    private String UOM;
    private String SUBINV;
    private String LOCATOR;
    private String COSTGROUP;
    private Integer ONHANDQTY;
    private Integer AVAILABLEQTY;
    private Integer PACKEDQTY;
    private Integer LOOSEQTY;
    private Integer RECEIVINGQTY;
    private Integer INBOUNDQTY;


    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
