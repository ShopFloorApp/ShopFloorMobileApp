package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.LocatorBO;
import dcom.shop.application.mobile.WarehouseBO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.adfmf.util.GenericVirtualType;

public class LocatorDC extends SyncUtils {
    public LocatorDC() {
        super();
    }
    
    public LocatorBO[] getLocators() {

        try {
            LocatorBO[] locators = null;
            List returnValue = new ArrayList();
            GenericVirtualType payload = new GenericVirtualType(null, "payload");
            payload.defineAttribute(null, "Whse", String.class, "100");
            payload.defineAttribute(null, "Subinv", String.class, "RAW");
            HashMap paramsMap=new HashMap();
            paramsMap.put("resAttriName","LocatorDetails");
            paramsMap.put("lovDCName", "LocatorLOV_WS");
            paramsMap.put("opeartionName", "process");
            paramsMap.put("payload",payload);
            returnValue = super.getCollection(LocatorBO.class, paramsMap);
            locators = (LocatorBO[]) returnValue.toArray(new LocatorBO[returnValue.size()]);
            return locators;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
