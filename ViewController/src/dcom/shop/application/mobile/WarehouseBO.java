package dcom.shop.application.mobile;

import java.util.HashMap;

import java.util.Iterator;
import java.util.Map;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.util.GenericVirtualType;

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
    private GenericVirtualType attributes;


    public WarehouseBO() {
        super();
    }

    public void setAttributes(GenericVirtualType attributes) {
        GenericVirtualType oldAttributes = this.attributes;
        this.attributes = attributes;
        propertyChangeSupport.firePropertyChange("attributes", oldAttributes, attributes);
    }

    public GenericVirtualType getAttributes() {
        return attributes;
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

    public void setBOClassRow(HashMap hashMap) {
        this.setWhse((String) hashMap.get("whse"));
        this.setName((String) hashMap.get("name"));
        this.setLine1((String) hashMap.get("line1"));
        this.setLine2((String) hashMap.get("line2"));
        this.setCity((String) hashMap.get("city"));
        this.setState((String) hashMap.get("state"));
        this.setZip((String) hashMap.get("zip"));
        this.setCountry((String) hashMap.get("country"));
        this.setLocatorControl((String) hashMap.get("locatorControl"));
        this.setIsWMS((String) hashMap.get("isWMS"));
    }

    public HashMap getBOClassRow(WarehouseBO warehouse) {
        HashMap map = new HashMap();
        map.put("whse", warehouse.getWhse());
        map.put("name", warehouse.getName());
        map.put("line1", warehouse.getLine1());
        map.put("line2", warehouse.getLine2());
        map.put("city", warehouse.getCity());
        map.put("state", warehouse.getState());
        map.put("zip", warehouse.getZip());
        map.put("country", warehouse.getCountry());
        map.put("locatorcontrol", warehouse.getLocatorControl());
        map.put("iswms", warehouse.getIsWMS());
        return map;
    }

    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

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

    public class AttributeListType {
        nvAttributeType[] attr;

        public void setAttr(WarehouseBO.AttributeListType.nvAttributeType[] attr) {
            this.attr = attr;
        }

        public WarehouseBO.AttributeListType.nvAttributeType[] getAttr() {
            return attr;
        }

        public class nvAttributeType {
            String n;
            String v;

            public void setN(String n) {
                this.n = n;
            }

            public String getN() {
                return n;
            }

            public void setV(String v) {
                this.v = v;
            }

            public String getV() {
                return v;
            }
        }
    }
}
