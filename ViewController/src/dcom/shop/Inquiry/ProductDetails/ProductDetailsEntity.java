package dcom.shop.Inquiry.ProductDetails;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class ProductDetailsEntity {
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
        String oldLOGDESC = this.LOGDESC;
        this.LOGDESC = LOGDESC;
        propertyChangeSupport.firePropertyChange("LOGDESC", oldLOGDESC, LOGDESC);
    }

    public String getLOGDESC() {
        return LOGDESC;
    }

    public void setWEIGHTUOM(String WEIGHTUOM) {
        String oldWEIGHTUOM = this.WEIGHTUOM;
        this.WEIGHTUOM = WEIGHTUOM;
        propertyChangeSupport.firePropertyChange("WEIGHTUOM", oldWEIGHTUOM, WEIGHTUOM);
    }

    public String getWEIGHTUOM() {
        return WEIGHTUOM;
    }

    public void setWEIGHT(int WEIGHT) {
        int oldWEIGHT = this.WEIGHT;
        this.WEIGHT = WEIGHT;
        propertyChangeSupport.firePropertyChange("WEIGHT", oldWEIGHT, WEIGHT);
    }

    public int getWEIGHT() {
        return WEIGHT;
    }

    public void setVOLUMEUOM(String VOLUMEUOM) {
        String oldVOLUMEUOM = this.VOLUMEUOM;
        this.VOLUMEUOM = VOLUMEUOM;
        propertyChangeSupport.firePropertyChange("VOLUMEUOM", oldVOLUMEUOM, VOLUMEUOM);
    }

    public String getVOLUMEUOM() {
        return VOLUMEUOM;
    }

    public void setDIMUOM(String DIMUOM) {
        String oldDIMUOM = this.DIMUOM;
        this.DIMUOM = DIMUOM;
        propertyChangeSupport.firePropertyChange("DIMUOM", oldDIMUOM, DIMUOM);
    }

    public String getDIMUOM() {
        return DIMUOM;
    }

    public void setWIDTH(int WIDTH) {
        int oldWIDTH = this.WIDTH;
        this.WIDTH = WIDTH;
        propertyChangeSupport.firePropertyChange("WIDTH", oldWIDTH, WIDTH);
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public void setLENGTH(int LENGTH) {
        int oldLENGTH = this.LENGTH;
        this.LENGTH = LENGTH;
        propertyChangeSupport.firePropertyChange("LENGTH", oldLENGTH, LENGTH);
    }

    public int getLENGTH() {
        return LENGTH;
    }

    public void setHEIGHT(int HEIGHT) {
        int oldHEIGHT = this.HEIGHT;
        this.HEIGHT = HEIGHT;
        propertyChangeSupport.firePropertyChange("HEIGHT", oldHEIGHT, HEIGHT);
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public void setISCONTAINER(String ISCONTAINER) {
        String oldISCONTAINER = this.ISCONTAINER;
        this.ISCONTAINER = ISCONTAINER;
        propertyChangeSupport.firePropertyChange("ISCONTAINER", oldISCONTAINER, ISCONTAINER);
    }

    public String getISCONTAINER() {
        return ISCONTAINER;
    }

    public void setCONTAINERTYPE(String CONTAINERTYPE) {
        String oldCONTAINERTYPE = this.CONTAINERTYPE;
        this.CONTAINERTYPE = CONTAINERTYPE;
        propertyChangeSupport.firePropertyChange("CONTAINERTYPE", oldCONTAINERTYPE, CONTAINERTYPE);
    }

    public String getCONTAINERTYPE() {
        return CONTAINERTYPE;
    }

    public void setMAXLOAD(String MAXLOAD) {
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
    private int WEIGHT;
    private String VOLUMEUOM;
    private int VOLUME;

    public void setPropertyChangeSupport(PropertyChangeSupport propertyChangeSupport) {
        PropertyChangeSupport oldPropertyChangeSupport = this.propertyChangeSupport;
        this.propertyChangeSupport = propertyChangeSupport;
        propertyChangeSupport.firePropertyChange("propertyChangeSupport", oldPropertyChangeSupport,
                                                 propertyChangeSupport);
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }

    public void setVOLUME(int VOLUME) {
        int oldVOLUME = this.VOLUME;
        this.VOLUME = VOLUME;
        propertyChangeSupport.firePropertyChange("VOLUME", oldVOLUME, VOLUME);
    }

    public int getVOLUME() {
        return VOLUME;
    }
    private String DIMUOM;
    private int WIDTH;
    private int LENGTH;
    private int HEIGHT;
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