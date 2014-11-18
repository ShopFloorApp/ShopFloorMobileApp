package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.CategoryCodeBO;
import dcom.shop.application.mobile.WarehouseBO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.GenericVirtualType;

public class CategoryCodeDC extends SyncUtils {
    private List filtered_CategoryCode=new ArrayList();
    private String catgcodeFilter = "";
    private String descFilter = "";
    protected static List s_categorycode = new ArrayList();
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);


    public void setCatgcodeFilter(String catgcodeFilter) {
        String oldCatgcodeFilter = this.catgcodeFilter;
        this.catgcodeFilter = catgcodeFilter;
        propertyChangeSupport.firePropertyChange("catgcodeFilter", oldCatgcodeFilter, catgcodeFilter);
    }

    public String getCatgcodeFilter() {
        return catgcodeFilter;
    }

    public void setDescFilter(String descFilter) {
        String oldDescFilter = this.descFilter;
        this.descFilter = descFilter;
        propertyChangeSupport.firePropertyChange("descFilter", oldDescFilter, descFilter);
    }

    public String getDescFilter() {
        return descFilter;
    }

    public CategoryCodeDC() {
        try{
        GenericVirtualType payload = new GenericVirtualType(null, "payload");
        HashMap paramsMap=new HashMap();
        paramsMap.put("resAttriName","CatgCodeDetails");
        paramsMap.put("lovDCName", "CategoryCodeLOV_WS");
        paramsMap.put("opeartionName", "process");
        paramsMap.put("payload",payload);
        s_categorycode = super.getCollection(CategoryCodeBO.class, paramsMap);
        filterCategoryCodes();
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    
    public CategoryCodeBO[] getCategoryCodes() {

        try {
            CategoryCodeBO[] categoryCodes = null;         
            categoryCodes = (CategoryCodeBO[]) filtered_CategoryCode.toArray(new CategoryCodeBO[filtered_CategoryCode.size()]);
            return categoryCodes;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void filterCategoryCodes() {
        try {
            System.out.println("inside filter code");
            filtered_CategoryCode.clear();

            HashMap filterFileds = new HashMap();
            filterFileds.put("catgcode", getCatgcodeFilter());
            filterFileds.put("desc", getDescFilter());
            

            HashMap paramMap = new HashMap();
            paramMap.put("collection", s_categorycode);
            paramMap.put("filterFieldsValues", filterFileds);
            System.out.println("called super filtered class");
            
            filtered_CategoryCode = (List)super.getFileteredCollection(CategoryCodeBO.class, paramMap);
            System.out.println("collection size is " + filtered_CategoryCode.size());
            providerChangeSupport.fireProviderRefresh("categoryCodes");
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
