package dcom.shop.Inquiry.ProductDetails;

import dcom.shop.application.base.AEntity;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class ProductDetailsEntity  extends AEntity  {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public ProductDetailsEntity() {
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

    public void setITEMSTATUS(String ITEMSTATUS) {
        String oldITEMSTATUS = this.ITEMSTATUS;
        this.ITEMSTATUS = ITEMSTATUS;
        propertyChangeSupport.firePropertyChange("ITEMSTATUS", oldITEMSTATUS, ITEMSTATUS);
    }

    public String getITEMSTATUS() {
        return ITEMSTATUS;
    }

    public void setPRIMARYUOM(String PRIMARYUOM) {
        String oldPRIMARYUOM = this.PRIMARYUOM;
        this.PRIMARYUOM = PRIMARYUOM;
        propertyChangeSupport.firePropertyChange("PRIMARYUOM", oldPRIMARYUOM, PRIMARYUOM);
    }

    public String getPRIMARYUOM() {
        return PRIMARYUOM;
    }

    public void setREVISION(String REVISION) {
        String oldREVISION = this.REVISION;
        this.REVISION = REVISION;
        propertyChangeSupport.firePropertyChange("REVISION", oldREVISION, REVISION);
    }

    public String getREVISION() {
        return REVISION;
    }

    public void setITEMTYPE(String ITEMTYPE) {
        String oldITEMTYPE = this.ITEMTYPE;
        this.ITEMTYPE = ITEMTYPE;
        propertyChangeSupport.firePropertyChange("ITEMTYPE", oldITEMTYPE, ITEMTYPE);
    }

    public String getITEMTYPE() {
        return ITEMTYPE;
    }

    public void setITEMCATALOG(String ITEMCATALOG) {
        ITEMCATALOG=getAttributeValue(ITEMCATALOG);
        String oldITEMCATALOG = this.ITEMCATALOG;
        this.ITEMCATALOG = ITEMCATALOG;
        propertyChangeSupport.firePropertyChange("ITEMCATALOG", oldITEMCATALOG, ITEMCATALOG);
    }

    public String getITEMCATALOG() {
        return ITEMCATALOG;
    }

    public void setDATECREATED(String DATECREATED) {
        String oldDATECREATED = this.DATECREATED;
        this.DATECREATED = DATECREATED;
        propertyChangeSupport.firePropertyChange("DATECREATED", oldDATECREATED, DATECREATED);
    }

    public String getDATECREATED() {
        return DATECREATED;
    }

    public void setLOGDESC(String LOGDESC) {
        LOGDESC=getAttributeValue(LOGDESC);
        String oldLOGDESC = this.LOGDESC;
        this.LOGDESC = LOGDESC;
        propertyChangeSupport.firePropertyChange("LOGDESC", oldLOGDESC, LOGDESC);
    }

    public String getLOGDESC() {
        return LOGDESC;
    }

    public void setWEIGHTUOM(String WEIGHTUOM) {
        WEIGHTUOM=getAttributeValue(WEIGHTUOM);
        String oldWEIGHTUOM = this.WEIGHTUOM;
        this.WEIGHTUOM = WEIGHTUOM;
        propertyChangeSupport.firePropertyChange("WEIGHTUOM", oldWEIGHTUOM, WEIGHTUOM);
    }

    public String getWEIGHTUOM() {
        return WEIGHTUOM;
    }

    public void setWEIGHT(float WEIGHT) {
        ITEMCATALOG=getAttributeValue(ITEMCATALOG);
        float oldWEIGHT = this.WEIGHT;
        this.WEIGHT = WEIGHT;
        propertyChangeSupport.firePropertyChange("WEIGHT", oldWEIGHT, WEIGHT);
    }

    public float getWEIGHT() {
        return WEIGHT;
    }

    public void setVOLUMEUOM(String VOLUMEUOM) {
        VOLUMEUOM=getAttributeValue(VOLUMEUOM);
        String oldVOLUMEUOM = this.VOLUMEUOM;
        this.VOLUMEUOM = VOLUMEUOM;
        propertyChangeSupport.firePropertyChange("VOLUMEUOM", oldVOLUMEUOM, VOLUMEUOM);
    }

    public String getVOLUMEUOM() {
        return VOLUMEUOM;
    }

    public void setDIMUOM(String DIMUOM) {
        DIMUOM=getAttributeValue(DIMUOM);
        String oldDIMUOM = this.DIMUOM;
        this.DIMUOM = DIMUOM;
        propertyChangeSupport.firePropertyChange("DIMUOM", oldDIMUOM, DIMUOM);
    }

    public String getDIMUOM() {
        return DIMUOM;
    }

    public void setWIDTH(float WIDTH) {
        float oldWIDTH = this.WIDTH;
        this.WIDTH = WIDTH;
        propertyChangeSupport.firePropertyChange("WIDTH", oldWIDTH, WIDTH);
    }

    public float getWIDTH() {
        return WIDTH;
    }

    public void setLENGTH(float LENGTH) {
        ITEMCATALOG=getAttributeValue(ITEMCATALOG);
        float oldLENGTH = this.LENGTH;
        this.LENGTH = LENGTH;
        propertyChangeSupport.firePropertyChange("LENGTH", oldLENGTH, LENGTH);
    }

    public float getLENGTH() {
        return LENGTH;
    }

    public void setHEIGHT(float HEIGHT) {
        float oldHEIGHT = this.HEIGHT;
        this.HEIGHT = HEIGHT;
        propertyChangeSupport.firePropertyChange("HEIGHT", oldHEIGHT, HEIGHT);
    }

    public float getHEIGHT() {
        return HEIGHT;
    }

    public void setISCONTAINER(String ISCONTAINER) {
        ISCONTAINER=getAttributeValue(ISCONTAINER);
        String oldISCONTAINER = this.ISCONTAINER;
        this.ISCONTAINER = ISCONTAINER;
        propertyChangeSupport.firePropertyChange("ISCONTAINER", oldISCONTAINER, ISCONTAINER);
    }

    public String getISCONTAINER() {
        return ISCONTAINER;
    }

    public void setCONTAINERTYPE(String CONTAINERTYPE) {
        CONTAINERTYPE=getAttributeValue(CONTAINERTYPE);
        String oldCONTAINERTYPE = this.CONTAINERTYPE;
        this.CONTAINERTYPE = CONTAINERTYPE;
        propertyChangeSupport.firePropertyChange("CONTAINERTYPE", oldCONTAINERTYPE, CONTAINERTYPE);
    }

    public String getCONTAINERTYPE() {
        return CONTAINERTYPE;
    }

    public void setMAXLOAD(String MAXLOAD) {
        MAXLOAD=getAttributeValue(MAXLOAD);
        String oldMAXLOAD = this.MAXLOAD;
        this.MAXLOAD = MAXLOAD;
        propertyChangeSupport.firePropertyChange("MAXLOAD", oldMAXLOAD, MAXLOAD);
    }

    public String getMAXLOAD() {
        return MAXLOAD;
    }
    private String ITEMDESC;
    private String ITEMSTATUS;
    private String PRIMARYUOM;
    private String REVISION;
    private String ITEMTYPE;
    private String ITEMCATALOG;
    private String DATECREATED;
    private String LOGDESC;
    private String WEIGHTUOM;
    private float WEIGHT;
    private String VOLUMEUOM;
    private float VOLUME;

    public void setPropertyChangeSupport(PropertyChangeSupport propertyChangeSupport) {
        PropertyChangeSupport oldPropertyChangeSupport = this.propertyChangeSupport;
        this.propertyChangeSupport = propertyChangeSupport;
        propertyChangeSupport.firePropertyChange("propertyChangeSupport", oldPropertyChangeSupport,
                                                 propertyChangeSupport);
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }

    public void setVOLUME(float VOLUME) {
        float oldVOLUME = this.VOLUME;
        this.VOLUME = VOLUME;
        propertyChangeSupport.firePropertyChange("VOLUME", oldVOLUME, VOLUME);
    }

    public float getVOLUME() {
        return VOLUME;
    }
    private String DIMUOM;
    private float WIDTH;
    private float LENGTH;
    private float HEIGHT;
    private String ISCONTAINER;
    private String CONTAINERTYPE;
    private String MAXLOAD;

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
