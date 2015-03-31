package dcom.shop.application.mobile.transaction;

import java.util.HashMap;


public class LpnTxnBO {
    public LpnTxnBO() {
        super();
    }

    public LpnTxnBO(int trxnid, String subInventory, String lpnFrom, String lpnTo, String quantity, String trxTime,
                    String orgCode, String locator, String trxType, String ItemName, String ItemNumber, String TxnUom,
                    String SerialControl, String LotControl) {
        this.setTrxnId(trxnid);
        this.setSubinventory(subInventory);
        this.setLpnFrom(lpnFrom);
        this.setLpnTo(lpnTo);
        this.setQuantity(quantity);
        this.setTrxTime(trxTime);
        this.setTrxType(trxType);
        this.setOrgCode(orgCode);
        this.setLocator(locator);
        this.setItemName(ItemName);
        this.setItemNumber(ItemNumber);
        this.setTxnUom(TxnUom);
        this.setSerialControl(SerialControl);
        this.setLotControl(LotControl);


    }

    public void setBOClassRow(HashMap hashMap) {
        this.setTrxnId((Integer) hashMap.get("trxnid"));
        this.setSubinventory((String) hashMap.get("subinventory"));
        this.setLpnFrom((String) hashMap.get("lpnfrom"));
        this.setLpnTo((String) hashMap.get("lpnto"));
        this.setQuantity((String) hashMap.get("quantity"));
        this.setTrxTime((String) hashMap.get("trxtime"));
        this.setTrxType((String) hashMap.get("trxtype"));
        this.setOrgCode((String) hashMap.get("orgcode"));
        this.setLocator((String) hashMap.get("locator"));
        this.setItemName((String) hashMap.get("itemname"));
        this.setItemNumber((String) hashMap.get("itemnumber"));
        this.setTxnUom((String) hashMap.get("txnuom"));
        this.setSerialControl((String) hashMap.get("serialcontrol"));
        this.setLotControl((String) hashMap.get("lotcontrol"));

    }

    public HashMap getBOClassRow(LpnTxnBO lpnTxn) {
        HashMap map = new HashMap();
        map.put("trxnid", lpnTxn.getTrxnId());
        map.put("subinventory", lpnTxn.getSubinventory());
        map.put("lpnfrom", lpnTxn.getLpnFrom());
        map.put("lpnto", lpnTxn.getLpnTo());
        map.put("quantity", lpnTxn.getQuantity());
        map.put("trxtime", lpnTxn.getTrxTime());
        map.put("trxtype", lpnTxn.getTrxType());
        map.put("orgcode", lpnTxn.getOrgCode());
        map.put("locator", lpnTxn.getLocator());
        map.put("itemname", lpnTxn.getItemName());
        map.put("itemnumber", lpnTxn.getItemNumber());
        map.put("txnuom", lpnTxn.getTxnUom());
        map.put("serialcontrol", lpnTxn.getSerialControl());
        map.put("lotcontrol", lpnTxn.getLotControl());
        return map;
    }
    private int TrxnId;
    private String Subinventory;
    private String Locator;
    private String LpnFrom;
    private String LpnTo;

    public void setTrxnId(int TrxnId) {
        this.TrxnId = TrxnId;
    }

    public int getTrxnId() {
        return TrxnId;
    }

    public void setSubinventory(String Subinventory) {
        this.Subinventory = Subinventory;
    }

    public String getSubinventory() {
        return Subinventory;
    }

    public void setLocator(String Locator) {
        this.Locator = Locator;
    }

    public String getLocator() {
        return Locator;
    }

    public void setLpnFrom(String LpnFrom) {
        this.LpnFrom = LpnFrom;
    }

    public String getLpnFrom() {
        return LpnFrom;
    }

    public void setLpnTo(String LpnTo) {
        this.LpnTo = LpnTo;
    }

    public String getLpnTo() {
        return LpnTo;
    }

    public void setQuantity(String Quantity) {
        this.Quantity = Quantity;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setTrxTime(String TrxTime) {
        this.TrxTime = TrxTime;
    }

    public String getTrxTime() {
        return TrxTime;
    }

    public void setOrgCode(String OrgCode) {
        this.OrgCode = OrgCode;
    }

    public String getOrgCode() {
        return OrgCode;
    }

    public void setTrxType(String TrxType) {
        this.TrxType = TrxType;
    }

    public String getTrxType() {
        return TrxType;
    }
    private String Quantity;
    private String TrxTime;

    public void setItemNumber(String ItemNumber) {
        this.ItemNumber = ItemNumber;
    }

    public String getItemNumber() {
        return ItemNumber;
    }

    public void setItemName(String ItemName) {
        this.ItemName = ItemName;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setTxnUom(String TxnUom) {
        this.TxnUom = TxnUom;
    }

    public String getTxnUom() {
        return TxnUom;
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
    private String OrgCode;
    private String TrxType;
    private String ItemNumber;
    private String ItemName;
    private String TxnUom;
    private String SerialControl;
    private String LotControl;
}
