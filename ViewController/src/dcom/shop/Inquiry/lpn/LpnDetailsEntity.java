package dcom.shop.Inquiry.lpn;

import dcom.shop.application.base.AEntity;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class LpnDetailsEntity extends AEntity  {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public LpnDetailsEntity() {
        super();
    }
    private String ITEM;

    public void setITEM(String ITEM) {
        ITEM=getAttributeValue(ITEM);
        String oldITEM = this.ITEM;
        this.ITEM = ITEM;
        propertyChangeSupport.firePropertyChange("ITEM", oldITEM, ITEM);
    }

    public String getITEM() {
        return ITEM;
    }

    public void setITEMDESC(String ITEMDESC) {
        ITEMDESC=getAttributeValue(ITEMDESC);
        String oldITEMDESC = this.ITEMDESC;
        this.ITEMDESC = ITEMDESC;
        propertyChangeSupport.firePropertyChange("ITEMDESC", oldITEMDESC, ITEMDESC);
    }

    public String getITEMDESC() {
        return ITEMDESC;
    }

    public void setUOM(String UOM) {
        UOM=getAttributeValue(UOM);
        String oldUOM = this.UOM;
        this.UOM = UOM;
        propertyChangeSupport.firePropertyChange("UOM", oldUOM, UOM);
    }

    public String getUOM() {
        return UOM;
    }



    private String ITEMDESC;
    private String UOM;
    private int ONHANDQTY;

    public void setPropertyChangeSupport(PropertyChangeSupport propertyChangeSupport) {
        PropertyChangeSupport oldPropertyChangeSupport = this.propertyChangeSupport;
        this.propertyChangeSupport = propertyChangeSupport;
        propertyChangeSupport.firePropertyChange("propertyChangeSupport", oldPropertyChangeSupport,
                                                 propertyChangeSupport);
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }

    public void setONHANDQTY(int ONHANDQTY) {
        int oldONHANDQTY = this.ONHANDQTY;
        this.ONHANDQTY = ONHANDQTY;
        propertyChangeSupport.firePropertyChange("ONHANDQTY", oldONHANDQTY, ONHANDQTY);
    }

    public void setSERIALCONTROL(String SERIALCONTROL) {
        String oldSERIALCONTROL = this.SERIALCONTROL;
        this.SERIALCONTROL = SERIALCONTROL;
        propertyChangeSupport.firePropertyChange("SERIALCONTROL", oldSERIALCONTROL, SERIALCONTROL);
    }

    public String getSERIALCONTROL() {
        return SERIALCONTROL;
    }

    public void setLOTCONTROL(String LOTCONTROL) {
        String oldLOTCONTROL = this.LOTCONTROL;
        this.LOTCONTROL = LOTCONTROL;
        propertyChangeSupport.firePropertyChange("LOTCONTROL", oldLOTCONTROL, LOTCONTROL);
    }

    public String getLOTCONTROL() {
        return LOTCONTROL;
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
    private int AVAILABLEQTY;
    private String SERIALCONTROL;
    private String LOTCONTROL;

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
