package dcom.shop.application.dc.txn;

import dcom.shop.application.base.SyncUtils;

import dcom.shop.application.mobile.txn.ConcurrentProgramBO;
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
             filtered_ConcurrentPrograms = super.getOfflineCollection(ConcurrentProgramBO.class);
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
