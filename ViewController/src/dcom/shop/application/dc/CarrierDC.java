package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.CarrierBO;
import dcom.shop.application.mobile.UOMBO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.adfmf.util.GenericVirtualType;

public class CarrierDC extends SyncUtils {
    public CarrierDC() {
        super();
    }
    public CarrierBO[] getCarriers() {

        try {
            CarrierBO[] carriers = null;
            List returnValue = new ArrayList();
            GenericVirtualType payload = new GenericVirtualType(null, "payload");
            HashMap paramsMap=new HashMap();
            paramsMap.put("resAttriName","CarrierDetails");
            paramsMap.put("lovDCName", "CarrierLOV_WS");
            paramsMap.put("opeartionName", "process");
            paramsMap.put("payload",payload);
            returnValue = super.getCollection(CarrierBO.class, paramsMap);
            carriers = (CarrierBO[]) returnValue.toArray(new CarrierBO[returnValue.size()]);
            return carriers;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
