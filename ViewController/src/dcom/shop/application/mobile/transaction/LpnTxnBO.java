package dcom.shop.application.mobile.transaction;

import java.util.HashMap;


public class LpnTxnBO {
    public LpnTxnBO() {
        super();
    }

    public LpnTxnBO(int trxnid, String subInventory, String lpnFrom, String lpnTo, String quantity, String trxTime,
                    String orgCode, String locator, String trxType) {
        this.setTrxnId(trxnid);
        this.setSubinventory(subInventory);
        this.setLpnFrom(lpnFrom);
        this.setLpnTo(lpnTo);
        this.setQuantity(quantity);
        this.setTrxTime(trxTime);
        this.setTrxType(trxType);
        this.setOrgCode(orgCode);
        this.setLocator(locator);


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
       
    }

    public HashMap getBOClassRow(MiscTxnBO miscTxn) {
        HashMap map = new HashMap();
        map.put("trxnid", miscTxn.getTrxnId());
        map.put("subinventory", miscTxn.getFromInventory());
        map.put("lpnfrom", miscTxn.getLocator());
        map.put("lpnto", miscTxn.getAccountAlias());
        map.put("quantity", miscTxn.getQuantity());
        map.put("trxtime", miscTxn.getTxnTime());
        map.put("trxtype", miscTxn.getStatus());
        map.put("orgcode", miscTxn.getItemName());
        map.put("locator", miscTxn.getItemNumber());
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
    private String OrgCode;
    private String TrxType;
}
