package dcom.shop.application.mobile;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class WarehouseBO implements Comparable {
    private String whse;
    private String name;
    private String line1;
    private String line2;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String locatorControl;
    private String isWMS;
    private String attribute1;
    private String attribute2;


    public WarehouseBO() {
        super();
    }


    public void setWhse(String whse) {
        String oldWhse = this.whse;
        this.whse = whse;
        propertyChangeSupport.firePropertyChange("whse", oldWhse, whse);
    }

    public String getWhse() {
        return whse;
    }

    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        propertyChangeSupport.firePropertyChange("name", oldName, name);
    }

    public String getName() {
        return name;
    }

    public void setLine1(String line1) {
        String oldLine1 = this.line1;
        this.line1 = line1;
        propertyChangeSupport.firePropertyChange("line1", oldLine1, line1);
    }

    public String getLine1() {
        return line1;
    }

    public void setLine2(String line2) {
        String oldLine2 = this.line2;
        this.line2 = line2;
        propertyChangeSupport.firePropertyChange("line2", oldLine2, line2);
    }

    public String getLine2() {
        return line2;
    }

    public void setCity(String city) {
        String oldCity = this.city;
        this.city = city;
        propertyChangeSupport.firePropertyChange("city", oldCity, city);
    }

    public String getCity() {
        return city;
    }

    public void setState(String state) {
        String oldState = this.state;
        this.state = state;
        propertyChangeSupport.firePropertyChange("state", oldState, state);
    }

    public String getState() {
        return state;
    }

    public void setZip(String zip) {
        String oldZip = this.zip;
        this.zip = zip;
        propertyChangeSupport.firePropertyChange("zip", oldZip, zip);
    }

    public String getZip() {
        return zip;
    }

    public void setCountry(String country) {
        String oldCountry = this.country;
        this.country = country;
        propertyChangeSupport.firePropertyChange("country", oldCountry, country);
    }

    public String getCountry() {
        return country;
    }


    public void setLocatorControl(String locatorControl) {
        String oldLocatorControl = this.locatorControl;
        this.locatorControl = locatorControl;
        propertyChangeSupport.firePropertyChange("locatorControl", oldLocatorControl, locatorControl);
    }

    public String getLocatorControl() {
        return locatorControl;
    }

    public void setIsWMS(String isWMS) {
        String oldIsWMS = this.isWMS;
        this.isWMS = isWMS;
        propertyChangeSupport.firePropertyChange("isWMS", oldIsWMS, isWMS);
    }

    public String getIsWMS() {
        return isWMS;
    }

    public void setAttribute1(String attribute1) {
        String oldAttribute1 = this.attribute1;
        this.attribute1 = attribute1;
        propertyChangeSupport.firePropertyChange("attribute1", oldAttribute1, attribute1);
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute2(String attribute2) {
        String oldAttribute2 = this.attribute2;
        this.attribute2 = attribute2;
        propertyChangeSupport.firePropertyChange("attribute2", oldAttribute2, attribute2);
    }

    public String getAttribute2() {
        return attribute2;
    }
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);


    public void setPropertyChangeSupport(PropertyChangeSupport propertyChangeSupport) {
        PropertyChangeSupport oldPropertyChangeSupport = this.propertyChangeSupport;
        this.propertyChangeSupport = propertyChangeSupport;
        propertyChangeSupport.firePropertyChange("propertyChangeSupport", oldPropertyChangeSupport,
                                                 propertyChangeSupport);
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof WarehouseBO)) {
            return false;
        }
        final WarehouseBO other = (WarehouseBO) object;
        if (!(name == null ? other.name == null : name.equals(other.name))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        final int PRIME = 37;
        int result = 1;
        result = PRIME * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }


    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }

    public int compareTo(Object o) {
        if (this.equals(o)) {
            return 0;
        } else if (o instanceof WarehouseBO) {
            return this.name.compareTo(((WarehouseBO) o).getName());
        } else {
            throw new ClassCastException("Warehouse Class expected.");
        }
    }
}