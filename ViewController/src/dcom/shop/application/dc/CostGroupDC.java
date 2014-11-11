package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.CostGroupBO;
import dcom.shop.application.mobile.UOMBO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.adfmf.util.GenericVirtualType;

public class CostGroupDC extends SyncUtils {
    public CostGroupDC() {
        super();
    }
    
    public CostGroupBO[] getCostGroups() {

        try {
            CostGroupBO[] costGroups = null;
            List returnValue = new ArrayList();
            GenericVirtualType payload = new GenericVirtualType(null, "payload");
            HashMap paramsMap=new HashMap();
            paramsMap.put("resAttriName","CostGroupDetails");
            paramsMap.put("lovDCName", "CostGroupLOV_WS");
            paramsMap.put("opeartionName", "process");
            paramsMap.put("payload",payload);
            returnValue = super.getCollection(CostGroupBO.class, paramsMap);
            costGroups = (CostGroupBO[]) returnValue.toArray(new CostGroupBO[returnValue.size()]);
            return costGroups;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
