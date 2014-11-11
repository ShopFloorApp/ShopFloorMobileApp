package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.TransactionTypeBO;
import dcom.shop.application.mobile.UOMBO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.adfmf.util.GenericVirtualType;

public class TransactionTypeDC extends SyncUtils {
    public TransactionTypeDC() {
        super();
    }
    
    public TransactionTypeBO[] getTransactionTypes() {

        try {
            TransactionTypeBO[] transactionTypes = null;
            List returnValue = new ArrayList();
            GenericVirtualType payload = new GenericVirtualType(null, "payload");
            HashMap paramsMap=new HashMap();
            paramsMap.put("resAttriName","TrxTypeDetails");
            paramsMap.put("lovDCName", "TransactionTypeLOV_WS");
            paramsMap.put("opeartionName", "process");
            paramsMap.put("payload",payload);
            returnValue = super.getCollection(TransactionTypeBO.class, paramsMap);
            transactionTypes = (TransactionTypeBO[]) returnValue.toArray(new TransactionTypeBO[returnValue.size()]);
            return transactionTypes;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
