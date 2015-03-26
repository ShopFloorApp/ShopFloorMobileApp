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

    public void setPACKEDQTY(int PACKEDQTY) {
        int oldPACKEDQTY = this.PACKEDQTY;
        this.PACKEDQTY = PACKEDQTY;
        propertyChangeSupport.firePropertyChange("PACKEDQTY", oldPACKEDQTY, PACKEDQTY);
    }

    public int getPACKEDQTY() {
        return PACKEDQTY;
    }

    public void setLOOSEQTY(int LOOSEQTY) {
        int oldLOOSEQTY = this.LOOSEQTY;
        this.LOOSEQTY = LOOSEQTY;
        propertyChangeSupport.firePropertyChange("LOOSEQTY", oldLOOSEQTY, LOOSEQTY);
    }

    public int getLOOSEQTY() {
        return LOOSEQTY;
    }

    public void setRECEIVINGQTY(int RECEIVINGQTY) {
        int oldRECEIVINGQTY = this.RECEIVINGQTY;
        this.RECEIVINGQTY = RECEIVINGQTY;
        propertyChangeSupport.firePropertyChange("RECEIVINGQTY", oldRECEIVINGQTY, RECEIVINGQTY);
    }

    public int getRECEIVINGQTY() {
        return RECEIVINGQTY;
    }

    public void setINBOUNDQTY(int INBOUNDQTY) {
        int oldINBOUNDQTY = this.INBOUNDQTY;
        this.INBOUNDQTY = INBOUNDQTY;
        propertyChangeSupport.firePropertyChange("INBOUNDQTY", oldINBOUNDQTY, INBOUNDQTY);
    }

    public int getINBOUNDQTY() {
        return INBOUNDQTY;
    }
    private String ITEMDESC;
    private String UOM;
    private String SUBINV;
    private String LOCATOR;
    private String COSTGROUP;
    private int ONHANDQTY;
    private int AVAILABLEQTY;
    private int PACKEDQTY;
    private int LOOSEQTY;
    private int RECEIVINGQTY;
    private int INBOUNDQTY;


    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
