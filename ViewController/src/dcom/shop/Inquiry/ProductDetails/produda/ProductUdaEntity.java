package dcom.shop.Inquiry.ProductDetails.produda;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class ProductUdaEntity {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public ProductUdaEntity() {
        super();
    }
    private String CATALOGNAME;

    public void setCATALOGNAME(String CATALOGNAME) {
        String oldCATALOGNAME = this.CATALOGNAME;
        this.CATALOGNAME = CATALOGNAME;
        propertyChangeSupport.firePropertyChange("CATALOGNAME", oldCATALOGNAME, CATALOGNAME);
    }

    public String getCATALOGNAME() {
        return CATALOGNAME;
    }

    public void setGROUPNAME(String GROUPNAME) {
        String oldGROUPNAME = this.GROUPNAME;
        this.GROUPNAME = GROUPNAME;
        propertyChangeSupport.firePropertyChange("GROUPNAME", oldGROUPNAME, GROUPNAME);
    }

    public String getGROUPNAME() {
        return GROUPNAME;
    }

    public void setATTRIBNAME(String ATTRIBNAME) {
        String oldATTRIBNAME = this.ATTRIBNAME;
        this.ATTRIBNAME = ATTRIBNAME;
        propertyChangeSupport.firePropertyChange("ATTRIBNAME", oldATTRIBNAME, ATTRIBNAME);
    }

    public String getATTRIBNAME() {
        return ATTRIBNAME;
    }

    public void setATTRIBDESC(String ATTRIBDESC) {
        String oldATTRIBDESC = this.ATTRIBDESC;
        this.ATTRIBDESC = ATTRIBDESC;
        propertyChangeSupport.firePropertyChange("ATTRIBDESC", oldATTRIBDESC, ATTRIBDESC);
    }

    public String getATTRIBDESC() {
        return ATTRIBDESC;
    }

    public void setATTRIBVALUE(String ATTRIBVALUE) {
        String oldATTRIBVALUE = this.ATTRIBVALUE;
        this.ATTRIBVALUE = ATTRIBVALUE;
        propertyChangeSupport.firePropertyChange("ATTRIBVALUE", oldATTRIBVALUE, ATTRIBVALUE);
    }

    public String getATTRIBVALUE() {
        return ATTRIBVALUE;
    }
    private String GROUPNAME;
    private String ATTRIBNAME;
    private String ATTRIBDESC;
    private String ATTRIBVALUE;

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
