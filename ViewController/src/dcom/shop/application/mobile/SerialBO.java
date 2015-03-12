package dcom.shop.application.mobile;

import java.util.HashMap;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class SerialBO {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public SerialBO() {
        super();
    }

    public SerialBO(int serialId, int trxnId, String fromSerial, String toSerial, int serialQty) {
        this.SerialId = serialId;
        this.TrxnId = trxnId;
        this.FromSerial = fromSerial;
        this.ToSerial = toSerial;
        this.SerialQty = serialQty;
    }

    public void setSerialId(int SerialId) {
        int oldSerialId = this.SerialId;
        this.SerialId = SerialId;
        propertyChangeSupport.firePropertyChange("SerialId", oldSerialId, SerialId);
    }

    public int getSerialId() {
        return SerialId;
    }
    private int SerialId;
    private int TrxnId;
    private String FromSerial;
    private String ToSerial;

    public void setTrxnId(int TrxnId) {
        int oldTrxnId = this.TrxnId;
        this.TrxnId = TrxnId;
        propertyChangeSupport.firePropertyChange("TrxnId", oldTrxnId, TrxnId);
    }

    public int getTrxnId() {
        return TrxnId;
    }

    public void setFromSerial(String FromSerial) {
        String oldFromSerial = this.FromSerial;
        this.FromSerial = FromSerial;
        propertyChangeSupport.firePropertyChange("FromSerial", oldFromSerial, FromSerial);
    }

    public String getFromSerial() {
        return FromSerial;
    }

    public void setToSerial(String ToSerial) {
        String oldToSerial = this.ToSerial;
        this.ToSerial = ToSerial;
        propertyChangeSupport.firePropertyChange("ToSerial", oldToSerial, ToSerial);
    }

    public String getToSerial() {
        return ToSerial;
    }

    public void setSerialQty(int SerialQty) {
        int oldSerialQty = this.SerialQty;
        this.SerialQty = SerialQty;
        propertyChangeSupport.firePropertyChange("SerialQty", oldSerialQty, SerialQty);
    }

    public int getSerialQty() {
        return SerialQty;
    }
    private int SerialQty;

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }

    public void setBOClassRow(HashMap hashMap) {
        Object serialidObj = hashMap.get("serialid");
        Integer serialId = null;
        if (serialidObj instanceof Integer) {
            serialId = ((Integer) serialidObj).intValue();
        }
        Object trxnIdObj = hashMap.get("trxnid");
        Integer trxnId = null;
        if (trxnIdObj instanceof Integer) {
            trxnId = ((Integer) trxnIdObj).intValue();
        }
        Object serialQtyObj = hashMap.get("serialqty");
        Integer serialQty = null;
        if (serialQtyObj instanceof Integer) {
            serialQty = ((Integer) serialQtyObj).intValue();
        }
        this.setSerialId(serialId);
        this.setTrxnId(trxnId);
        this.setFromSerial((String) hashMap.get("fromserial"));
        this.setToSerial((String) hashMap.get("toserial"));
        this.setSerialQty(serialQty);

    }

    public HashMap getBOClassRow(SerialBO serial) {
        HashMap map = new HashMap();
        map.put("serialid",serial.getSerialId());
        map.put("trxnid", serial.getTrxnId());
        map.put("fromserial", serial.getFromSerial());
        map.put("toserial", serial.getToSerial());
        map.put("serialqty", (serial.getSerialQty()));
        return map;
    }
}
