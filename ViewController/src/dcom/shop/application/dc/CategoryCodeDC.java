package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.CategoryCodeBO;
import dcom.shop.application.mobile.WarehouseBO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.adfmf.util.GenericVirtualType;

public class CategoryCodeDC extends SyncUtils {
    public CategoryCodeDC() {
        super();
    }
    
    public CategoryCodeBO[] getCategoryCodes() {

        try {
            CategoryCodeBO[] categoryCodes = null;
            List returnValue = new ArrayList();
            GenericVirtualType payload = new GenericVirtualType(null, "payload");
            HashMap paramsMap=new HashMap();
            paramsMap.put("resAttriName","CatgCodeDetails");
            paramsMap.put("lovDCName", "CategoryCodeLOV_WS");
            paramsMap.put("opeartionName", "process");
            paramsMap.put("payload",payload);
            returnValue = super.getCollection(CategoryCodeBO.class, paramsMap);
            categoryCodes = (CategoryCodeBO[]) returnValue.toArray(new CategoryCodeBO[returnValue.size()]);
            return categoryCodes;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
