package dcom.shop.application.mobile.transaction;

import java.util.HashMap;


public class ItemTxnBO {
    public ItemTxnBO() {
        super();
    }

    public ItemTxnBO(int trxnid, int itemID, String itemNumber, String itemName, String uom, int quantity,
                     String trxType, String SerialControl, String LotControl) {
        this.setTrxnId(trxnid);
        this.setItemId(itemID);
        this.setItemNumber(itemNumber);
        this.setUom(uom);
        this.setQuantity(quantity);
        this.setTrxType(trxType);
        this.setItemName(itemName);
        this.setSerialControl(SerialControl);
        this.setLotControl(LotControl);

    }

    public void setBOClassRow(HashMap hashMap) {
        this.setTrxnId((Integer) hashMap.get("trxnid"));
        this.setItemId((Integer) hashMap.get("itemid"));
        this.setItemNumber((String) hashMap.get("itemnumber"));
        this.setItemName((String) hashMap.get("itemname"));
        this.setUom((String) hashMap.get("uom"));
        this.setQuantity((Integer) hashMap.get("quantity"));
        this.setTrxType((String) hashMap.get("trxtype"));
        this.setSerialControl((String) hashMap.get("serialcontrol"));
        this.setLotControl((String) hashMap.get("lotcontrol"));
    }

    public HashMap getBOClassRow(ItemTxnBO itemTxn) {
        HashMap map = new HashMap();
        map.put("trxnid", itemTxn.getTrxnId());
        map.put("itemid", itemTxn.getItemId());
        map.put("itemnumber", itemTxn.getItemNumber());
        map.put("itemname", itemTxn.getItemName());
        map.put("uom", itemTxn.getUom());
        map.put("quantity", itemTxn.getQuantity());
        map.put("trxtype", itemTxn.getTrxType());
        map.put("serialcontrol", itemTxn.getSerialControl());
        map.put("lotcontrol", itemTxn.getLotControl());
        return map;
    }

    public void setTrxnId(int TrxnId) {
        this.TrxnId = TrxnId;
    }

    public int getTrxnId() {
        return TrxnId;
    }

    public void setItemName(String ItemName) {
        this.ItemName = ItemName;
    }

    public String getItemName() {
        return ItemName;
    }
    private int ItemId;
    private int TrxnId;
    private String ItemNumber;
    private String ItemName;
    private String Uom;
    private int Quantity;

    public void setItemId(int ItemId) {
        this.ItemId = ItemId;
    }

    public int getItemId() {
        return ItemId;
    }

    public void setItemNumber(String ItemNumber) {
        this.ItemNumber = ItemNumber;
    }

    public String getItemNumber() {
        return ItemNumber;
    }

    public void setUom(String Uom) {
        this.Uom = Uom;
    }

    public String getUom() {
        return Uom;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setTrxType(String TrxType) {
        this.TrxType = TrxType;
    }

    public String getTrxType() {
        return TrxType;
    }
    private String TrxType;
    private String SerialControl;
    private String LotControl;

    public void setSerialControl(String SerialControl) {
        this.SerialControl = SerialControl;
    }

    public String getSerialControl() {
        return SerialControl;
    }

    public void setLotControl(String LotControl) {
        this.LotControl = LotControl;
    }

    public String getLotControl() {
        return LotControl;
    }


}
