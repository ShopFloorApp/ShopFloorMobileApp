package dcom.shop.application.mobile;

import java.util.HashMap;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class LotBO {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public LotBO() {
        super();
    }
    public LotBO(int lotId, int trxnId, String lotNo, int lotQty,String trxType) {
        this.LotId = lotId;
        this.TrxnId = trxnId;
        this.LotNo = lotNo;
        this.LotQty = lotQty;
        this.TrxType = trxType;
    }
    private int LotId;
    private int TrxnId;

    public void setLotId(int LotId) {
        int oldLotId = this.LotId;
        this.LotId = LotId;
        propertyChangeSupport.firePropertyChange("LotId", oldLotId, LotId);
    }

    public int getLotId() {
        return LotId;
    }

    public void setTrxnId(int TrxnId) {
        int oldTrxnId = this.TrxnId;
        this.TrxnId = TrxnId;
        propertyChangeSupport.firePropertyChange("TrxnId", oldTrxnId, TrxnId);
    }

    public int getTrxnId() {
        return TrxnId;
    }

    public void setLotNo(String LotNo) {
        String oldLotNo = this.LotNo;
        this.LotNo = LotNo;
        propertyChangeSupport.firePropertyChange("LotNo", oldLotNo, LotNo);
    }

    public String getLotNo() {
        return LotNo;
    }

    public void setLotQty(int LotQty) {
        int oldLotQty = this.LotQty;
        this.LotQty = LotQty;
        propertyChangeSupport.firePropertyChange("LotQty", oldLotQty, LotQty);
    }

    public int getLotQty() {
        return LotQty;
    }
    private String LotNo;
    private int LotQty;

    public void setTrxType(String TrxType) {
        this.TrxType = TrxType;
    }

    public String getTrxType() {
        return TrxType;
    }
    private String TrxType;

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
    
    public void setBOClassRow(HashMap hashMap) {
        Object lotObj = hashMap.get("lotid");
        Integer lotid = null;
        if (lotObj instanceof Integer) {
            lotid = ((Integer) lotObj).intValue();
        }
        Object trxnIdObj = hashMap.get("trxnid");
        Integer trxnId = null;
        if (trxnIdObj instanceof Integer) {
            trxnId = ((Integer) trxnIdObj).intValue();
        }
        Object lotQtyObj = hashMap.get("lotqty");
        Integer lotQty = null;
        if (lotQtyObj instanceof Integer) {
            lotQty = ((Integer) lotQtyObj).intValue();
        }
        this.setLotId(lotid);
        this.setTrxnId(trxnId);
        this.setLotNo((String) hashMap.get("lotno"));
         this.setLotQty(lotQty);
         this.setTrxType((String)hashMap.get("trxtype"));

    }

    public HashMap getBOClassRow(LotBO lot) {
        HashMap map = new HashMap();
        map.put("lotid",lot.getLotId());
        map.put("trxnid", lot.getTrxnId());
        map.put("lotno", lot.getLotNo());
         map.put("lotqty", (lot.getLotQty()));
         map.put("trxtype",lot.getTrxType());
        return map;
    }
}
