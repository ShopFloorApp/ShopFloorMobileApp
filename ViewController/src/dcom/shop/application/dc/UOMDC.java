package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.UOMBO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.adfmf.util.GenericVirtualType;

public class UOMDC extends SyncUtils {
    public UOMDC() {
        super();
    }
    
    public UOMBO[] getUOMs() {

        try {
            UOMBO[] uoms = null;
            List returnValue = new ArrayList();
            GenericVirtualType payload = new GenericVirtualType(null, "payload");
            HashMap paramsMap=new HashMap();
            paramsMap.put("resAttriName","UOMDetails");
            paramsMap.put("lovDCName", "UOMLOV_WS");
            paramsMap.put("opeartionName", "process");
            paramsMap.put("payload",payload);
            returnValue = super.getCollection(UOMBO.class, paramsMap);
            uoms = (UOMBO[]) returnValue.toArray(new UOMBO[returnValue.size()]);
            return uoms;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
