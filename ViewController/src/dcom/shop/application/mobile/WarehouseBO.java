package dcom.shop.application.mobile;

import java.util.HashMap;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;


public class WarehouseBO {
    public WarehouseBO() {
        super();
    }
    
        private String whse;
        private String name;
        private String line1;
        private String line2;
        private String line3;
        private String line4;
        private String city;
        private String province;
    private String state;
        private String zip;
        private String country;
        private String locatorControl;
        private String isWMS;
        private String directShip;

    public void setDirectShip(String directShip) {
        String oldDirectShip = this.directShip;
        this.directShip = directShip;
        propertyChangeSupport.firePropertyChange("directShip", oldDirectShip, directShip);
    }

    public String getDirectShip() {
        return directShip;
    }

    public void setLine3(String line3) {
        String oldLine3 = this.line3;
        this.line3 = line3;
        propertyChangeSupport.firePropertyChange("line3", oldLine3, line3);
    }

    public String getLine3() {
        return line3;
    }

    public void setLine4(String line4) {
        String oldLine4 = this.line4;
        this.line4 = line4;
        propertyChangeSupport.firePropertyChange("line4", oldLine4, line4);
    }

    public String getLine4() {
        return line4;
    }

    public void setProvince(String province) {
        String oldProvince = this.province;
        this.province = province;
        propertyChangeSupport.firePropertyChange("province", oldProvince, province);
    }

    public String getProvince() {
        return province;
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

        
        private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);


        public void addPropertyChangeListener(PropertyChangeListener l) {
            propertyChangeSupport.addPropertyChangeListener(l);
        }

        public void removePropertyChangeListener(PropertyChangeListener l) {
            propertyChangeSupport.removePropertyChangeListener(l);
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
        this.setLine3((String)hashMap.get("line3"));
        this.setLine4((String)hashMap.get("line4"));
        this.setProvince((String)hashMap.get("province"));
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
        map.put("line3", warehouse.getLine3());
        map.put("line4", warehouse.getLine4());
        map.put("province", warehouse.getProvince());
        return map;
    }

}
