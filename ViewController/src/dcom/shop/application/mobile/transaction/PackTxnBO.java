package dcom.shop.application.mobile.transaction;

import dcom.shop.application.base.CustomPojo;

import java.util.HashMap;

public class PackTxnBO extends CustomPojo{
    public PackTxnBO() {
        super();
    }
    public PackTxnBO(int trxnid,String DockDoor, String LPN, String SubInv, String Locator,
                             String Item, String Qty, String OrderNum, String LineNum, String serialControl, String lotControl){
        this.setTrxnId(trxnid);
        this.setDockDoor(DockDoor);
        this.setLPN(LPN);
        this.setSubInv(SubInv);
        this.setLocator(Locator);
        this.setItem(Item);
        this.setQty(Qty);
        this.setOrderNum(OrderNum);
        this.setLineNum(LineNum);
        this.setSerialControl(serialControl);
        this.setLotControl(lotControl);
    }
    private int TrxnId;
    private String DockDoor;
    private String LPN;
    private String SubInv;
    private String Locator;
    private String Item;
    private String Qty;

    public void setTrxnId(int TrxnId) {
        this.TrxnId = TrxnId;
    }

    public int getTrxnId() {
        return TrxnId;
    }

    public void setDockDoor(String DockDoor) {
        this.DockDoor = DockDoor;
    }

    public String getDockDoor() {
        return DockDoor;
    }

    public void setLPN(String LPN) {
        this.LPN = LPN;
    }

    public String getLPN() {
        return LPN;
    }

    public void setSubInv(String SubInv) {
        this.SubInv = SubInv;
    }

    public String getSubInv() {
        return SubInv;
    }

    public void setLocator(String Locator) {
        this.Locator = Locator;
    }

    public String getLocator() {
        return Locator;
    }

    public void setItem(String Item) {
        this.Item = Item;
    }

    public String getItem() {
        return Item;
    }

    public void setQty(String Qty) {
        this.Qty = Qty;
    }

    public String getQty() {
        return Qty;
    }

    public void setOrderNum(String OrderNum) {
        this.OrderNum = OrderNum;
    }

    public String getOrderNum() {
        return OrderNum;
    }

    public void setLineNum(String LineNum) {
        this.LineNum = LineNum;
    }

    public String getLineNum() {
        return LineNum;
    }

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
    private String OrderNum;
    private String LineNum;
    private String SerialControl;
    private String LotControl;
    
    public void setBOClassRow(HashMap hashMap) {
        this.setTrxnId((Integer)hashMap.get("trxnid"));
        this.setDockDoor((String)hashMap.get("dockdoor"));
        this.setLPN((String)hashMap.get("lpn"));
        this.setSubInv((String)hashMap.get("subinv"));
        this.setLocator((String)hashMap.get("locator"));
        this.setItem((String)hashMap.get("item"));
        this.setQty((String)hashMap.get("qty"));
        this.setOrderNum((String)hashMap.get("ordernum"));
        this.setLineNum((String)hashMap.get("linenum"));
        this.setSerialControl((String)hashMap.get("serialcontrol"));
        this.setLotControl((String)hashMap.get("lotcontrol"));
    }

    public HashMap getBOClassRow(PackTxnBO packTxn) {
        HashMap map = new HashMap();
        map.put("trxnid",packTxn.getTrxnId());
        map.put("dockdoor",packTxn.getDockDoor());
        map.put("lpn",packTxn.getLPN());
        map.put("subinv", packTxn.getSubInv());
        map.put("locator", packTxn.getLocator());
        map.put("item", packTxn.getItem());
        map.put("qty", packTxn.getQty());
        map.put("ordernum", packTxn.getOrderNum());
        map.put("linenum", packTxn.getLineNum());
        map.put("serialcontrol", packTxn.getSerialControl());
        map.put("lotcontrol", packTxn.getLotControl());
        return map;
    }
}
