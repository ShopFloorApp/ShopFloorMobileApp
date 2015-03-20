package dcom.shop.application.mobile.transaction;

import java.util.HashMap;


public class ItemTxnBO {
    public ItemTxnBO() {
        super();
    }
    public ItemTxnBO(int trxnid, int itemID, String itemNumber, String uom, String quantity, String trxType) {
        this.setTrxnId(trxnid);
        this.setItemId(itemID);
        this.setItemNumber(itemNumber);
        this.setUom(uom);
        this.setQuantity(quantity);
        this.setTrxType(trxType);
      

    }
    public void setBOClassRow(HashMap hashMap) {
        this.setTrxnId((Integer) hashMap.get("trxnid"));
        this.setItemId((Integer) hashMap.get("itemid"));
        this.setItemNumber((String) hashMap.get("itemnumber"));
        this.setUom((String) hashMap.get("uom"));
        this.setQuantity((String) hashMap.get("quantity"));
        this.setTrxType((String) hashMap.get("trxtype"));
       
    }

    public HashMap getBOClassRow(MiscTxnBO miscTxn) {
        HashMap map = new HashMap();
        map.put("trxnid", miscTxn.getTrxnId());
        map.put("itemid", miscTxn.getFromInventory());
        map.put("itemnumber", miscTxn.getLocator());
        map.put("uom", miscTxn.getAccountAlias());
        map.put("quantity", miscTxn.getQuantity());
        map.put("trxtype", miscTxn.getStatus());
        return map;
    }

    public void setTrxnId(int TrxnId) {
        this.TrxnId = TrxnId;
    }

    public int getTrxnId() {
        return TrxnId;
    }
    private int ItemId;
    private int TrxnId;
    private String ItemNumber;
    private String Uom;
    private String Quantity;

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

    public void setQuantity(String Quantity) {
        this.Quantity = Quantity;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setTrxType(String TrxType) {
        this.TrxType = TrxType;
    }

    public String getTrxType() {
        return TrxType;
    }
    private String TrxType;
    
    
}
