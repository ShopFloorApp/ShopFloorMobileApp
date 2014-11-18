package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.UOMBO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.GenericVirtualType;

public class UOMDC extends SyncUtils {
    private List filtered_UOM=new ArrayList();
    private String uomcodeFilter = "";
    private String uomFilter = "";
    protected static List s_uom = new ArrayList();
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);

    public UOMDC() {
        try{
        GenericVirtualType payload = new GenericVirtualType(null, "payload");
        HashMap paramsMap=new HashMap();
        paramsMap.put("resAttriName","UOMDetails");
        paramsMap.put("lovDCName", "UOMLOV_WS");
        paramsMap.put("opeartionName", "process");
        paramsMap.put("payload",payload);
        s_uom = super.getCollection(UOMBO.class, paramsMap);
            filterUOMs();   
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public void setUomcodeFilter(String uomcodeFilter) {
        String oldUomcodeFilter = this.uomcodeFilter;
        this.uomcodeFilter = uomcodeFilter;
        propertyChangeSupport.firePropertyChange("uomcodeFilter", oldUomcodeFilter, uomcodeFilter);
    }

    public String getUomcodeFilter() {
        return uomcodeFilter;
    }

    public void setUomFilter(String uomFilter) {
        String oldUomFilter = this.uomFilter;
        this.uomFilter = uomFilter;
        propertyChangeSupport.firePropertyChange("uomFilter", oldUomFilter, uomFilter);
    }

    public String getUomFilter() {
        return uomFilter;
    }

    public UOMBO[] getUOMs() {

        try {
            UOMBO[] uoms = null;            
            uoms = (UOMBO[]) filtered_UOM.toArray(new UOMBO[filtered_UOM.size()]);
            return uoms;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public void filterUOMs() {
        try {
            System.out.println("inside filter code");
            filtered_UOM.clear();

            HashMap filterFileds = new HashMap();
            filterFileds.put("uomcode", getUomcodeFilter());
            filterFileds.put("uom", getUomFilter());
            

            HashMap paramMap = new HashMap();
            paramMap.put("collection", s_uom);
            paramMap.put("filterFieldsValues", filterFileds);
            System.out.println("called super filtered class");
            
            filtered_UOM = (List)super.getFileteredCollection(UOMBO.class, paramMap);
            System.out.println("collection size is " + filtered_UOM.size());
            providerChangeSupport.fireProviderRefresh("UOMs");
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
