package dcom.shop.application.mobile.dispatch;

import java.math.BigDecimal;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class InstructionsBO {
    private String SEQ;
    private String CATEGDESC;
    private String TITLE;
    private String DOCDESC;
    private String DATATYPE;
    private String FILENAME;
    private String URL;
    private String IMAGETYPE;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void setSEQ(String SEQ) {
        String oldSEQ = this.SEQ;
        this.SEQ = SEQ;
        propertyChangeSupport.firePropertyChange("SEQ", oldSEQ, SEQ);
    }

    public String getSEQ() {
        return SEQ;
    }

    public void setCATEGDESC(String CATEGDESC) {
        String oldCATEGDESC = this.CATEGDESC;
        this.CATEGDESC = CATEGDESC;
        propertyChangeSupport.firePropertyChange("CATEGDESC", oldCATEGDESC, CATEGDESC);
    }

    public String getCATEGDESC() {
        return CATEGDESC;
    }

    public void setTITLE(String TITLE) {
        String oldTITLE = this.TITLE;
        this.TITLE = TITLE;
        propertyChangeSupport.firePropertyChange("TITLE", oldTITLE, TITLE);
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setDOCDESC(String DOCDESC) {
        String oldDOCDESC = this.DOCDESC;
        this.DOCDESC = DOCDESC;
        propertyChangeSupport.firePropertyChange("DOCDESC", oldDOCDESC, DOCDESC);
    }

    public String getDOCDESC() {
        return DOCDESC;
    }

    public void setDATATYPE(String DATATYPE) {
        String oldDATATYPE = this.DATATYPE;
        this.DATATYPE = DATATYPE;
        propertyChangeSupport.firePropertyChange("DATATYPE", oldDATATYPE, DATATYPE);
    }

    public String getDATATYPE() {
        return DATATYPE;
    }

    public void setFILENAME(String FILENAME) {
        String oldFILENAME = this.FILENAME;
        this.FILENAME = FILENAME;
        propertyChangeSupport.firePropertyChange("FILENAME", oldFILENAME, FILENAME);
    }

    public String getFILENAME() {
        return FILENAME;
    }

    public void setURL(String URL) {
        String oldURL = this.URL;
        this.URL = URL;
        propertyChangeSupport.firePropertyChange("URL", oldURL, URL);
    }

    public String getURL() {
        return URL;
    }

    public void setIMAGETYPE(String IMAGETYPE) {
        String oldIMAGETYPE = this.IMAGETYPE;
        this.IMAGETYPE = IMAGETYPE;
        propertyChangeSupport.firePropertyChange("IMAGETYPE", oldIMAGETYPE, IMAGETYPE);
    }

    public String getIMAGETYPE() {
        return IMAGETYPE;
    }

    public void setSHORTTEXT(String SHORTTEXT) {
        String oldSHORTTEXT = this.SHORTTEXT;
        this.SHORTTEXT = SHORTTEXT;
        propertyChangeSupport.firePropertyChange("SHORTTEXT", oldSHORTTEXT, SHORTTEXT);
    }

    public String getSHORTTEXT() {
        return SHORTTEXT;
    }

    public void setCREATIONDATE(String CREATIONDATE) {
        String oldCREATIONDATE = this.CREATIONDATE;
        this.CREATIONDATE = CREATIONDATE;
        propertyChangeSupport.firePropertyChange("CREATIONDATE", oldCREATIONDATE, CREATIONDATE);
    }

    public String getCREATIONDATE() {
        return CREATIONDATE;
    }

    public void setCREATEDBY(String CREATEDBY) {
        String oldCREATEDBY = this.CREATEDBY;
        this.CREATEDBY = CREATEDBY;
        propertyChangeSupport.firePropertyChange("CREATEDBY", oldCREATEDBY, CREATEDBY);
    }

    public String getCREATEDBY() {
        return CREATEDBY;
    }

    public void setMEDIAID(BigDecimal MEDIAID) {
        BigDecimal oldMEDIAID = this.MEDIAID;
        this.MEDIAID = MEDIAID;
        propertyChangeSupport.firePropertyChange("MEDIAID", oldMEDIAID, MEDIAID);
    }

    public BigDecimal getMEDIAID() {
        return MEDIAID;
    }
    private String SHORTTEXT;
    private String CREATIONDATE;
    private String CREATEDBY;
    private BigDecimal MEDIAID;
    public InstructionsBO() {
        super();
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
