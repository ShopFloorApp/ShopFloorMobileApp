package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.CarrierBO;

import dcom.shop.application.mobile.PickRuleBO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.adfmf.util.GenericVirtualType;

public class PickRuleDC extends SyncUtils {
    public PickRuleDC() {
        super();
    }
    
    public PickRuleBO[] getPickRules() {

        try {
            PickRuleBO[] pickRules = null;
            List returnValue = new ArrayList();
            GenericVirtualType payload = new GenericVirtualType(null, "payload");
            HashMap paramsMap=new HashMap();
            paramsMap.put("resAttriName","PickRuleDetails");
            paramsMap.put("lovDCName", "PickRuleLOV_WS");
            paramsMap.put("opeartionName", "process");
            paramsMap.put("payload",payload);
            returnValue = super.getCollection(PickRuleBO.class, paramsMap);
            pickRules = (PickRuleBO[]) returnValue.toArray(new PickRuleBO[returnValue.size()]);
            return pickRules;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
