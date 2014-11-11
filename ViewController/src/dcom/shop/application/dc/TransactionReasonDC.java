package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.TransactionReasonBO;
import dcom.shop.application.mobile.UOMBO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.adfmf.util.GenericVirtualType;

public class TransactionReasonDC extends SyncUtils {
    public TransactionReasonDC() {
        super();
    }
    
    public TransactionReasonBO[] getTransactionReasons() {

        try {
            TransactionReasonBO[] transactionReasons = null;
            List returnValue = new ArrayList();
            GenericVirtualType payload = new GenericVirtualType(null, "payload");
            HashMap paramsMap=new HashMap();
            paramsMap.put("resAttriName","TrxReasonDetails");
            paramsMap.put("lovDCName", "TransactionReasonLOV_WS");
            paramsMap.put("opeartionName", "process");
            paramsMap.put("payload",payload);
            returnValue = super.getCollection(TransactionReasonBO.class, paramsMap);
            transactionReasons = (TransactionReasonBO[]) returnValue.toArray(new TransactionReasonBO[returnValue.size()]);
            return transactionReasons;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
