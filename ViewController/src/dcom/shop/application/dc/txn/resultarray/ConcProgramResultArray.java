package dcom.shop.application.dc.txn.resultarray;

import dcom.shop.application.mobile.txn.ConcProgramParamLovBO;
import dcom.shop.application.mobile.txn.ConcProgramParamsBO;
import dcom.shop.application.mobile.txn.ConcurrentProgramBO;
import dcom.shop.application.mobile.txn.RequestsBO;

public class ConcProgramResultArray {
    private XXDCOM_CP_TAB XCPLIST=null;
    
    public ConcProgramResultArray() {
        super();
    }

    public void setXCPLIST(ConcProgramResultArray.XXDCOM_CP_TAB XCPLIST) {
        this.XCPLIST = XCPLIST;
    }

    public ConcProgramResultArray.XXDCOM_CP_TAB getXCPLIST() {
        return XCPLIST;
    }


    public class XXDCOM_CP_TAB {
       private ConcurrentProgramBO[] XCPLIST_ITEM;

        public void setXCPLIST_ITEM(ConcurrentProgramBO[] XCPLIST_ITEM) {
            this.XCPLIST_ITEM = XCPLIST_ITEM;
        }

        public ConcurrentProgramBO[] getXCPLIST_ITEM() {
            return XCPLIST_ITEM;
        }
    }
}
