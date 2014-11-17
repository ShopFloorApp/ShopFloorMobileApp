package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.WarehouseBO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.GenericVirtualType;

public class WarehouseDC extends SyncUtils {
    private List filtered_Warehouses=new ArrayList();
    private String warehouseFilter = "";
    private String nameFilter = "";
    protected static List s_warehouse = new ArrayList();
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);


    public WarehouseDC() {
        try {
            GenericVirtualType payload = new GenericVirtualType(null, "payload");
            HashMap paramsMap=new HashMap();
            paramsMap.put("resAttriName","WhseDetails");
            paramsMap.put("lovDCName", "WarehouseLOV_WS");
            paramsMap.put("opeartionName", "process");
            paramsMap.put("payload",payload);
            s_warehouse = super.getCollection(WarehouseBO.class, paramsMap);
            filterWarehouses();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setWarehouseFilter(String warehouseFilter) {
        String oldWarehouseFilter = this.warehouseFilter;
        this.warehouseFilter = warehouseFilter;
        propertyChangeSupport.firePropertyChange("warehouseFilter", oldWarehouseFilter, warehouseFilter);
    }

    public String getWarehouseFilter() {
        return warehouseFilter;
    }

    public void setNameFilter(String nameFilter) {
        String oldNameFilter = this.nameFilter;
        this.nameFilter = nameFilter;
        propertyChangeSupport.firePropertyChange("nameFilter", oldNameFilter, nameFilter);
    }

    public String getNameFilter() {
        return nameFilter;
    }

    public WarehouseBO[] getWarehouses() {

        try {
            System.out.println("called collection warehouse");
            WarehouseBO[] warehouses = null;
            warehouses = (WarehouseBO[]) filtered_Warehouses.toArray(new WarehouseBO[filtered_Warehouses.size()]);
            return warehouses;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void filterWarehouses() {
        try {
            System.out.println("inside filter code");
            filtered_Warehouses.clear();

            HashMap filterFileds = new HashMap();
            filterFileds.put("whse", getWarehouseFilter());
            filterFileds.put("name", getNameFilter());
            

            HashMap paramMap = new HashMap();
            paramMap.put("collection", s_warehouse);
            paramMap.put("filterFieldsValues", filterFileds);
            System.out.println("called super filtered class");
            
            filtered_Warehouses = (List)super.getFileteredCollection(WarehouseBO.class, paramMap);
            System.out.println("collection size is " + filtered_Warehouses.size());
            providerChangeSupport.fireProviderRefresh("warehouses");
        } catch (Exception e) {
            throw new RuntimeException("My Code Error " + e);
        }
    }



    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
    
    public void addProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.addProviderChangeListener(l);
    }

    public void removeProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.removeProviderChangeListener(l);
    }
}

