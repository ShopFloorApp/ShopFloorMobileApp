package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.CategorySetBO;

import dcom.shop.application.mobile.SubinventoryBO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.adfmf.util.GenericVirtualType;

public class SubinventoryDC extends SyncUtils {
    public SubinventoryDC() {
        super();
    }
    
    public SubinventoryBO[] getSubinventories() {

        try {
            SubinventoryBO[] subInventories = null;
            List returnValue = new ArrayList();
            GenericVirtualType payload = new GenericVirtualType(null, "payload");
            payload.defineAttribute(null, "Whse", String.class, "100");
            HashMap paramsMap=new HashMap();
            paramsMap.put("resAttriName","SubinvDetails");
            paramsMap.put("lovDCName", "SubinventoryLOV_WS");
            paramsMap.put("opeartionName", "process");
            paramsMap.put("payload",payload);
            returnValue = super.getCollection(SubinventoryBO.class, paramsMap);
            subInventories = (SubinventoryBO[]) returnValue.toArray(new SubinventoryBO[returnValue.size()]);
            return subInventories;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
