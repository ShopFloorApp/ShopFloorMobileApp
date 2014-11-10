package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.base.TransactionHeader;
import dcom.shop.application.database.ConnectionFactory;
import dcom.shop.application.mobile.WarehouseBO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.exception.AdfInvocationException;
import oracle.adfmf.util.GenericType;
import oracle.adfmf.util.GenericVirtualType;
import oracle.adfmf.util.Utility;

public class WarehouseDC extends SyncUtils {


    public WarehouseDC() {
        super();
    }

    public WarehouseBO[] getWarehouses() {

        try {
            WarehouseBO[] warehouses = null;
            List returnValue = new ArrayList();
            GenericVirtualType payload = new GenericVirtualType(null, "payload");
            HashMap paramsMap=new HashMap();
            paramsMap.put("resAttriName","WhseDetails");
            paramsMap.put("lovDCName", "WarehouseLOV_WS");
            paramsMap.put("opeartionName", "process");
            paramsMap.put("payload",payload);
            returnValue = super.getCollection(WarehouseBO.class, paramsMap);
            warehouses = (WarehouseBO[]) returnValue.toArray(new WarehouseBO[returnValue.size()]);
            return warehouses;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

