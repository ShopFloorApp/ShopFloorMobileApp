package dcom.shop.Inquiry;

import dcom.shop.application.base.AEntity;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class ProductSearchEntity extends AEntity {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public ProductSearchEntity() {
        super();
    }
    private String ORGCODE;

    public void setORGCODE(String ORGCODE) {
        String oldORGCODE = this.ORGCODE;
        this.ORGCODE = ORGCODE;
        propertyChangeSupport.firePropertyChange("ORGCODE", oldORGCODE, ORGCODE);
    }

    public String getORGCODE() {
        return ORGCODE;
    }

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

    public void setLOTCONTROL(String LOTCONTROL) {
        String oldLOTCONTROL = this.LOTCONTROL;
        this.LOTCONTROL = LOTCONTROL;
        propertyChangeSupport.firePropertyChange("LOTCONTROL", oldLOTCONTROL, LOTCONTROL);
    }

    public String getLOTCONTROL() {
        return LOTCONTROL;
    }

    public void setSERIALCONTROL(String SERIALCONTROL) {
        String oldSERIALCONTROL = this.SERIALCONTROL;
        this.SERIALCONTROL = SERIALCONTROL;
        propertyChangeSupport.firePropertyChange("SERIALCONTROL", oldSERIALCONTROL, SERIALCONTROL);
    }

    public String getSERIALCONTROL() {
        return SERIALCONTROL;
    }

    public void setITEMID(int ITEMID) {
        int oldITEMID = this.ITEMID;
        this.ITEMID = ITEMID;
        propertyChangeSupport.firePropertyChange("ITEMID", oldITEMID, ITEMID);
    }

    public int getITEMID() {
        return ITEMID;
    }

    public void setORGID(int ORGID) {
        int oldORGID = this.ORGID;
        this.ORGID = ORGID;
        propertyChangeSupport.firePropertyChange("ORGID", oldORGID, ORGID);
    }

    public int getORGID() {
        return ORGID;
    }

    public void setProductSearchAttribEntity1(ProductSearchAttribEntity ProductSearchAttribEntity1) {
        ProductSearchAttribEntity oldProductSearchAttribEntity1 = this.ProductSearchAttribEntity1;
        this.ProductSearchAttribEntity1 = ProductSearchAttribEntity1;
        propertyChangeSupport.firePropertyChange("ProductSearchAttribEntity1", oldProductSearchAttribEntity1,
                                                 ProductSearchAttribEntity1);
    }

    public ProductSearchAttribEntity getProductSearchAttribEntity1() {
        return ProductSearchAttribEntity1;
    }
    private String ITEM;
    private String ITEMDESC;
    private String ITEMSTATUS;
    private String PRIMARYUOM;
    private String REVISION;
    private String ITEMTYPE;
    private String ITEMCATALOG;
    private String LOTCONTROL;
    private String SERIALCONTROL;
    private int ITEMID;
    private int ORGID;
    private ProductSearchAttribEntity ProductSearchAttribEntity1;

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
