package dcom.shop.application.mobile.transaction.receiving;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class LinesBO {
    private String Lines;
    private String subInv;
    private String locator;
    private String quantity;
    private String Uom;
    private String Lpn;
    private String isNewEntity;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void setLines(String Lines) {
        String oldLines = this.Lines;
        this.Lines = Lines;
        propertyChangeSupport.firePropertyChange("Lines", oldLines, Lines);
    }

    public void setUom(String Uom) {
        String oldUom = this.Uom;
        this.Uom = Uom;
        propertyChangeSupport.firePropertyChange("Uom", oldUom, Uom);
    }

    public String getUom() {
        return Uom;
    }

    public void setLpn(String Lpn) {
        String oldLpn = this.Lpn;
        this.Lpn = Lpn;
        propertyChangeSupport.firePropertyChange("Lpn", oldLpn, Lpn);
    }

    public String getLpn() {
        return Lpn;
    }

    public String getLines() {
        return Lines;
    }

    public void setIsNewEntity(String isNewEntity) {
        String oldIsNewEntity = this.isNewEntity;
        this.isNewEntity = isNewEntity;
        propertyChangeSupport.firePropertyChange("isNewEntity", oldIsNewEntity, isNewEntity);
    }

    public String getIsNewEntity() {
        return isNewEntity;
    }

    public void setSubInv(String subInv) {
        String oldSubInv = this.subInv;
        this.subInv = subInv;
        propertyChangeSupport.firePropertyChange("subInv", oldSubInv, subInv);
    }

    public String getSubInv() {
        return subInv;
    }

    public void setLocator(String locator) {
        String oldLocator = this.locator;
        this.locator = locator;
        propertyChangeSupport.firePropertyChange("locator", oldLocator, locator);
    }

    public String getLocator() {
        return locator;
    }

    public void setQuantity(String quantity) {
        String oldQuantity = this.quantity;
        this.quantity = quantity;
        propertyChangeSupport.firePropertyChange("quantity", oldQuantity, quantity);
    }

    public String getQuantity() {
        return quantity;
    }

    public LinesBO() {
        super();
    }
    public LinesBO(String line,String subInv,String locator,String Quantity,String Uom,String Lpn,String isNewEntity) {
        super();
        this.Lines=line;
        this.subInv=subInv;
        this.locator=locator;
        this.quantity=Quantity;
        this.isNewEntity=isNewEntity;
        this.Uom=Uom;
        this.Lpn=Lpn;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
