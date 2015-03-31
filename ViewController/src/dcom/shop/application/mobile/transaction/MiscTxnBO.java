package dcom.shop.application.mobile.transaction;

import java.util.HashMap;

public class MiscTxnBO {
  
    public MiscTxnBO() {
        super();
    }

    public MiscTxnBO(int trxnid, String fromInventory, String itemNumber, String itemName, String quantity,
                     String txnTime, String completeFlag, String locator, String status, String trxType,
                     String accountAlias, String txnUom, String serialControl, String lotControl) {
        this.setTrxnId(trxnid);
        this.setFromInventory(fromInventory);
        this.setItemName(itemName);
        this.setItemNumber(itemNumber);
        this.setQuantity(quantity);
        this.setTxnTime(txnTime);
        this.setTrxType(trxType);
        this.setAccountAlias(accountAlias);
        this.setLocator(locator);
        this.setStatus(status);
        this.setCompleteFlag(completeFlag);
        this.setTxnUom(txnUom);
        this.setSerialControl(serialControl);
        this.setLotControl(lotControl);

    }

    public void setBOClassRow(HashMap hashMap) {
        this.setTrxnId((Integer) hashMap.get("trxnid"));
        this.setItemName((String) hashMap.get("itemname"));
        this.setItemNumber((String) hashMap.get("itemnumber"));
        this.setQuantity((String) hashMap.get("quantity"));
        this.setTxnTime((String) hashMap.get("txntime"));
        this.setFromInventory((String) hashMap.get("frominventory"));
        this.setTrxType((String) hashMap.get("trxtype"));
        this.setAccountAlias((String) hashMap.get("accountalias"));
        this.setLocator((String) hashMap.get("locator"));
        this.setStatus((String) hashMap.get("status"));
        this.setCompleteFlag((String) hashMap.get("completeflag"));
        this.setTxnUom((String) hashMap.get("txnuom"));
        this.setSerialControl((String) hashMap.get("serialcontrol"));
        this.setLotControl((String) hashMap.get("lotcontrol"));
    }

    public HashMap getBOClassRow(MiscTxnBO miscTxn) {
        HashMap map = new HashMap();
        map.put("trxnid", miscTxn.getTrxnId());
        map.put("frominventory", miscTxn.getFromInventory());
        map.put("locator", miscTxn.getLocator());
        map.put("accountalias", miscTxn.getAccountAlias());
        map.put("quantity", miscTxn.getQuantity());
        map.put("txntime", miscTxn.getTxnTime());
        map.put("status", miscTxn.getStatus());
        map.put("itemname", miscTxn.getItemName());
        map.put("itemnumber", miscTxn.getItemNumber());
        map.put("trxtype", miscTxn.getTrxType());
        map.put("completeflag", miscTxn.getCompleteFlag());
        map.put("txnuom", miscTxn.getTxnUom());
        map.put("serialcontrol", miscTxn.getSerialControl());
        map.put("lotcontrol", miscTxn.getLotControl());
        return map;
    }
    private int TrxnId;
    private String ItemNumber;

    public void setTrxnId(int TrxnId) {
        this.TrxnId = TrxnId;
    }

    public int getTrxnId() {
        return TrxnId;
    }

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

    public void setTxnTime(String TxnTime) {
        this.TxnTime = TxnTime;
    }

    public String getTxnTime() {
        return TxnTime;
    }

    public void setCompleteFlag(String CompleteFlag) {
        this.CompleteFlag = CompleteFlag;
    }

    public String getCompleteFlag() {
        return CompleteFlag;
    }

    public void setAccountAlias(String AccountAlias) {
        this.AccountAlias = AccountAlias;
    }

    public String getAccountAlias() {
        return AccountAlias;
    }

    public void setFromInventory(String FromInventory) {
        this.FromInventory = FromInventory;
    }

    public String getFromInventory() {
        return FromInventory;
    }

    public void setLocator(String Locator) {
        this.Locator = Locator;
    }

    public String getLocator() {
        return Locator;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getStatus() {
        return Status;
    }
    private String ItemName;
    private String Quantity;


    private String TrxType;
    private String TxnTime;
    private String CompleteFlag;
    private String AccountAlias;
    private String FromInventory;
    private String Locator;

    public void setTxnUom(String TxnUom) {
        this.TxnUom = TxnUom;
    }

    public String getTxnUom() {
        return TxnUom;
    }

    public void setSerialControl(String serialControl) {
        this.serialControl = serialControl;
    }

    public String getSerialControl() {
        return serialControl;
    }

    public void setLotControl(String lotControl) {
        this.lotControl = lotControl;
    }

    public String getLotControl() {
        return lotControl;
    }
    private String Status;
    private String TxnUom;
    private String serialControl;
    private String lotControl;


}
