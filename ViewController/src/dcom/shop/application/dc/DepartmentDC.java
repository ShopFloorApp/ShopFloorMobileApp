package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.CarrierBO;

import dcom.shop.application.mobile.DepartmentBO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.adfmf.util.GenericVirtualType;

public class DepartmentDC extends SyncUtils {
    
    public DepartmentDC() {
        super();
    }
    
    public DepartmentBO[] getDepartments() {

        try {
            DepartmentBO[] departments = null;
            List returnValue = new ArrayList();
            GenericVirtualType payload = new GenericVirtualType(null, "payload");
            HashMap paramsMap=new HashMap();
            paramsMap.put("resAttriName","DeptDetails");
            paramsMap.put("lovDCName", "DepartmentLOV_WS");
            paramsMap.put("opeartionName", "process");
            paramsMap.put("payload",payload);
            returnValue = super.getCollection(DepartmentBO.class, paramsMap);
            departments = (DepartmentBO[]) returnValue.toArray(new DepartmentBO[returnValue.size()]);
            return departments;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
