package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.CarrierBO;

import dcom.shop.application.mobile.PhysicalCountBO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.adfmf.util.GenericVirtualType;

public class PhysicalCountDC extends SyncUtils {
    public PhysicalCountDC() {
        super();
    }
    
    public PhysicalCountBO[] getPhysicalCounts() {

        try {
            PhysicalCountBO[] physicalCounts = null;
            List returnValue = new ArrayList();
            GenericVirtualType payload = new GenericVirtualType(null, "payload");
            payload.defineAttribute(null, "Whse", String.class, "");
            payload.defineAttribute(null, "CountName", String.class, "");
            HashMap paramsMap=new HashMap();
            paramsMap.put("resAttriName","PhyCountTagDetails");
            paramsMap.put("lovDCName", "PhysicalCountLOV_WS");
            paramsMap.put("opeartionName", "process");
            paramsMap.put("payload",payload);
            returnValue = super.getCollection(PhysicalCountBO.class, paramsMap);
            physicalCounts = (PhysicalCountBO[]) returnValue.toArray(new PhysicalCountBO[returnValue.size()]);
            return physicalCounts;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
