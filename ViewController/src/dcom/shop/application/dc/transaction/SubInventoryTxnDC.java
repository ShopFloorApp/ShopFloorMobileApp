package dcom.shop.application.dc.transaction;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.transaction.SubInventoryTxnBO;
import dcom.shop.application.mobile.txn.ConcurrentProgramBO;

import java.util.ArrayList;
import java.util.List;

public class SubInventoryTxnDC extends SyncUtils{
    private List filtered_SubInventoryTxn=new ArrayList();

    public SubInventoryTxnDC() {
        try {
             filtered_SubInventoryTxn = super.getOfflineCollection(SubInventoryTxnBO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public SubInventoryTxnBO[] getSubInventoryTxn() {

        try {
            System.out.println("called collection subinv txn");
            SubInventoryTxnBO[] getSubInventoryTxn = null;
            getSubInventoryTxn = (SubInventoryTxnBO[]) filtered_SubInventoryTxn.toArray(new SubInventoryTxnBO[filtered_SubInventoryTxn.size()]);
            return getSubInventoryTxn;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    
}
