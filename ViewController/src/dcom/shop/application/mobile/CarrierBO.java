package dcom.shop.application.mobile;

import java.util.HashMap;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.util.GenericVirtualType;

public class CarrierBO implements Comparable {
    private String Whse;
    private String CarrierName;
    private String FreightCode;
    private String SCAC;
    private String ShipMethod;
    private String Desc;
    private GenericVirtualType Attributes;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public CarrierBO() {
        super();
    }
    
    public void setBOClassRow(HashMap hashMap) {
        this.setWhse((String) hashMap.get("whse"));
        this.setCarrierName((String) hashMap.get("carriername"));
        this.setFreightCode((String) hashMap.get("freightcode"));
        this.setSCAC((String) hashMap.get("scac"));
        this.setShipMethod((String) hashMap.get("shipmethod"));
        this.setDesc((String) hashMap.get("desc"));
    }

    public HashMap getBOClassRow(CarrierBO carrier) {
        HashMap map = new HashMap();
        map.put("whse", carrier.getWhse());
        map.put("carriername", carrier.getCarrierName());
        map.put("freightcode", carrier.getFreightCode());
        map.put("scac", carrier.getSCAC());
        map.put("shipmethod", carrier.getShipMethod());
        map.put("desc", carrier.getDesc());
        return map;
    }

    public int compareTo(Object o) {
        // TODO Implement this method
        return 0;
    }

    public void setWhse(String Whse) {
        String oldWhse = this.Whse;
        this.Whse = Whse;
        propertyChangeSupport.firePropertyChange("Whse", oldWhse, Whse);
    }

    public String getWhse() {
        return Whse;
    }

    public void setCarrierName(String CarrierName) {
        String oldCarrierName = this.CarrierName;
        this.CarrierName = CarrierName;
        propertyChangeSupport.firePropertyChange("CarrierName", oldCarrierName, CarrierName);
    }

    public String getCarrierName() {
        return CarrierName;
    }

    public void setFreightCode(String FreightCode) {
        String oldFreightCode = this.FreightCode;
        this.FreightCode = FreightCode;
        propertyChangeSupport.firePropertyChange("FreightCode", oldFreightCode, FreightCode);
    }

    public String getFreightCode() {
        return FreightCode;
    }

    public void setSCAC(String SCAC) {
        String oldSCAC = this.SCAC;
        this.SCAC = SCAC;
        propertyChangeSupport.firePropertyChange("SCAC", oldSCAC, SCAC);
    }

    public String getSCAC() {
        return SCAC;
    }

    public void setShipMethod(String ShipMethod) {
        String oldShipMethod = this.ShipMethod;
        this.ShipMethod = ShipMethod;
        propertyChangeSupport.firePropertyChange("ShipMethod", oldShipMethod, ShipMethod);
    }

    public String getShipMethod() {
        return ShipMethod;
    }

    public void setDesc(String Desc) {
        String oldDesc = this.Desc;
        this.Desc = Desc;
        propertyChangeSupport.firePropertyChange("Desc", oldDesc, Desc);
    }

    public String getDesc() {
        return Desc;
    }

    public void setAttributes(GenericVirtualType Attributes) {
        GenericVirtualType oldAttributes = this.Attributes;
        this.Attributes = Attributes;
        propertyChangeSupport.firePropertyChange("Attributes", oldAttributes, Attributes);
    }

    public GenericVirtualType getAttributes() {
        return Attributes;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
