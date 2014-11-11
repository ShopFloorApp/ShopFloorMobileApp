package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.CarrierBO;

import dcom.shop.application.mobile.ResourceBO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.adfmf.util.GenericVirtualType;

public class ResourceDC extends SyncUtils {
    public ResourceDC() {
        super();
    }
    
    public ResourceBO[] getResources() {

        try {
            ResourceBO[] resources = null;
            List returnValue = new ArrayList();
            GenericVirtualType payload = new GenericVirtualType(null, "payload");
            HashMap paramsMap=new HashMap();
            paramsMap.put("resAttriName","ResourceDetails");
            paramsMap.put("lovDCName", "ResourceLOV_WS");
            paramsMap.put("opeartionName", "process");
            paramsMap.put("payload",payload);
            returnValue = super.getCollection(ResourceBO.class, paramsMap);
            resources = (ResourceBO[]) returnValue.toArray(new ResourceBO[returnValue.size()]);
            return resources;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
