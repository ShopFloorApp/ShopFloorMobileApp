package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;

import dcom.shop.application.mobile.ConcurrentProgramBO;
import dcom.shop.application.mobile.WarehouseBO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.GenericVirtualType;

public class ConcurrentProgramDC extends SyncUtils {
    private List filtered_ConcurrentPrograms=new ArrayList();
//    private String warehouseFilter = "";
//    private String nameFilter = "";
//    protected static List s_warehouse = new ArrayList();
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    
    public ConcurrentProgramDC() {
        try {
            GenericVirtualType payload = new GenericVirtualType(null, "payload");
            HashMap paramsMap=new HashMap();
            paramsMap.put("resAttriName","ConcurrentProg");
            paramsMap.put("lovDCName", "ConcurrentProgramLOV_WS");
            paramsMap.put("opeartionName", "process");
            paramsMap.put("payload",payload);
            filtered_ConcurrentPrograms = super.getCollection(ConcurrentProgramBO.class, paramsMap);
//            filterWarehouses();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public ConcurrentProgramBO[] getConcurrentPrograms() {

        try {
            System.out.println("called collection warehouse");
            ConcurrentProgramBO[] concurrentPrograms = null;
            concurrentPrograms = (ConcurrentProgramBO[]) filtered_ConcurrentPrograms.toArray(new ConcurrentProgramBO[filtered_ConcurrentPrograms.size()]);
            return concurrentPrograms;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
