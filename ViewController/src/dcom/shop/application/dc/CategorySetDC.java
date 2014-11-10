package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.CategorySetBO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.adfmf.util.GenericVirtualType;

public class CategorySetDC extends SyncUtils {
    public CategorySetDC() {
        super();
    }
    
    public CategorySetBO[] getCategorySets() {

        try {
            CategorySetBO[] categorySets = null;
            List returnValue = new ArrayList();
            GenericVirtualType payload = new GenericVirtualType(null, "payload");
            HashMap paramsMap=new HashMap();
            paramsMap.put("resAttriName","CatgSetDetails");
            paramsMap.put("lovDCName", "CategorySetLOV_WS");
            paramsMap.put("opeartionName", "process");
            paramsMap.put("payload",payload);
            returnValue = super.getCollection(CategorySetBO.class, paramsMap);
            categorySets = (CategorySetBO[]) returnValue.toArray(new CategorySetBO[returnValue.size()]);
            return categorySets;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
