package dcom.shop.application.mobile.transaction;

import dcom.shop.application.base.CustomPojo;

import dcom.shop.application.mobile.WarehouseBO;

import java.sql.Time;

import java.util.HashMap;

public class SubInventoryTxnBO extends CustomPojo {
    private String PrimaryKeyId;
    private String IsNewEntity;
    private String RowOperation;


    public void setPrimaryKeyId(String PrimaryKeyId) {
        this.PrimaryKeyId = PrimaryKeyId;
    }

    public String getPrimaryKeyId() {
        return PrimaryKeyId;
    }

    public void setIsNewEntity(String IsNewEntity) {
        this.IsNewEntity = IsNewEntity;
    }

    public String getIsNewEntity() {
        return IsNewEntity;
    }

    public void setRowOperation(String RowOperation) {
        this.RowOperation = RowOperation;
    }

    public String getRowOperation() {
        return RowOperation;
    }
    private String FromInventory;
    private String ToInventory;
    private String ItemNumber;
    private String ItemName;
    private String Quantity;
    private String TxnTime;
    private String CompleteFlag;
    private String LPN;
    private String FromLoc;
    private String ToLoc;
    private String Status;

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getStatus() {
        return Status;
    }

    public void setFromInventory(String FromInventory) {
        this.FromInventory = FromInventory;
    }

    public String getFromInventory() {
        return FromInventory;
    }

    public void setToInventory(String ToInventory) {
        this.ToInventory = ToInventory;
    }

    public String getToInventory() {
        return ToInventory;
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

    public void setLPN(String LPN) {
        this.LPN = LPN;
    }

    public String getLPN() {
        return LPN;
    }

    public void setFromLoc(String FromLoc) {
        this.FromLoc = FromLoc;
    }

    public String getFromLoc() {
        return FromLoc;
    }

    public void setToLoc(String ToLoc) {
        this.ToLoc = ToLoc;
    }

    public String getToLoc() {
        return ToLoc;
    }


    public SubInventoryTxnBO() {
        super();
    }

    @Override
    public int compareTo(Object o) {
        // TODO Implement this method
        return 0;
    }


        
    public void setBOClassRow(HashMap hashMap) {
        this.setFromInventory((String) hashMap.get("frominventory"));
        this.setToInventory((String) hashMap.get("toinventory"));
        this.setFromLoc((String) hashMap.get("fromloc"));
        this.setToLoc((String) hashMap.get("toloc"));
        this.setQuantity((String) hashMap.get("quantity"));
        this.setTxnTime((String) hashMap.get("txntime"));
        this.setStatus((String) hashMap.get("status"));
        this.setItemName((String) hashMap.get("itemname"));
        this.setItemNumber((String) hashMap.get("itemnumber"));
        this.setLPN((String) hashMap.get("lpn"));
        this.setCompleteFlag((String)hashMap.get("completeflag"));
        this.setRowOperation((String)hashMap.get("rowoperation"));
    }

    public HashMap getBOClassRow(SubInventoryTxnBO subInventoryTxn) {
        HashMap map = new HashMap();
        map.put("frominventory", subInventoryTxn.getFromInventory());
        map.put("toinventory", subInventoryTxn.getToInventory());
        map.put("fromloc", subInventoryTxn.getFromLoc());
        map.put("toloc", subInventoryTxn.getToLoc());
        map.put("quantity", subInventoryTxn.getQuantity());
        map.put("txntime", subInventoryTxn.getTxnTime());
        map.put("status", subInventoryTxn.getStatus());
        map.put("itemname", subInventoryTxn.getItemName());
        map.put("itemnumber", subInventoryTxn.getItemNumber());
        map.put("lpn", subInventoryTxn.getLPN());
        map.put("completeflag",subInventoryTxn.getCompleteFlag());
        map.put("rowoperation", subInventoryTxn.getRowOperation());
        return map;
    }
//    @Override
//    public HashMap getBOClassRow(CustomPojo childPojo) {
//        // TODO Implement this method
//        return null;
//    }
}
