package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.CarrierBO;

import dcom.shop.application.mobile.CountTypeBO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.adfmf.util.GenericVirtualType;

public class CountTypeDC extends SyncUtils {
    public CountTypeDC() {
        super();
    }
    public CountTypeBO[] getCountTypes() {

        try {
            CountTypeBO[] countTypes = null;
            List returnValue = new ArrayList();
            GenericVirtualType payload = new GenericVirtualType(null, "payload");
            HashMap paramsMap=new HashMap();
            paramsMap.put("resAttriName","CountTypeDetails");
            paramsMap.put("lovDCName", "CountTypeLOV_WS");
            paramsMap.put("opeartionName", "process");
            paramsMap.put("payload",payload);
            returnValue = super.getCollection(CountTypeBO.class, paramsMap);
            countTypes = (CountTypeBO[]) returnValue.toArray(new CountTypeBO[returnValue.size()]);
            return countTypes;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
