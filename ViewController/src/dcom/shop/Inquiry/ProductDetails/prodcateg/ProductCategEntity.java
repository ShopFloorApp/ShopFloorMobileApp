package dcom.shop.Inquiry.ProductDetails.prodcateg;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class ProductCategEntity {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public ProductCategEntity() {
        super();
    }
    private String CATNAME;

    public void setCATNAME(String CATNAME) {
        String oldCATNAME = this.CATNAME;
        this.CATNAME = CATNAME;
        propertyChangeSupport.firePropertyChange("CATNAME", oldCATNAME, CATNAME);
    }

    public String getCATNAME() {
        return CATNAME;
    }

    public void setCATCODE(String CATCODE) {
        String oldCATCODE = this.CATCODE;
        this.CATCODE = CATCODE;
        propertyChangeSupport.firePropertyChange("CATCODE", oldCATCODE, CATCODE);
    }

    public String getCATCODE() {
        return CATCODE;
    }

    public void setCATDESC(String CATDESC) {
        String oldCATDESC = this.CATDESC;
        this.CATDESC = CATDESC;
        propertyChangeSupport.firePropertyChange("CATDESC", oldCATDESC, CATDESC);
    }

    public String getCATDESC() {
        return CATDESC;
    }
    private String CATCODE;
    private String CATDESC;

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
