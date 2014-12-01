package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.CategorySetBO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.GenericVirtualType;

public class CategorySetDC extends SyncUtils {
    private List filtered_CategorySets=new ArrayList();
    private String categorySetFilter = "";
    private String descFilter = "";
    protected static List s_categotySets = new ArrayList();
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);  
    
    public CategorySetDC() {
        GenericVirtualType payload = new GenericVirtualType(null, "payload");
        HashMap paramsMap=new HashMap();
        paramsMap.put("resAttriName","CatgSetDetails");
        paramsMap.put("lovDCName", "CategorySetLOV_WS");
        paramsMap.put("opeartionName", "process");
        paramsMap.put("payload",payload);
        s_categotySets = super.getCollection(CategorySetBO.class, paramsMap);
        filterCategorySets();
    }

    public void setDescFilter(String descFilter) {
        String oldDescFilter = this.descFilter;
        this.descFilter = descFilter;
        propertyChangeSupport.firePropertyChange("descFilter", oldDescFilter, descFilter);
    }

    public String getDescFilter() {
        return descFilter;
    }

    public CategorySetBO[] getCategorySets() {

        try {
            CategorySetBO[] categorySets = null;        
            categorySets = (CategorySetBO[]) filtered_CategorySets.toArray(new CategorySetBO[filtered_CategorySets.size()]);
            return categorySets;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setCategorySetFilter(String categorySetFilter) {
        String oldCategorySetFilter = this.categorySetFilter;
        this.categorySetFilter = categorySetFilter;
        propertyChangeSupport.firePropertyChange("categorySetFilter", oldCategorySetFilter, categorySetFilter);
    }

    public String getCategorySetFilter() {
        return categorySetFilter;
    }




    public void filterCategorySets() {
        try {
            System.out.println("inside filter code");
            filtered_CategorySets.clear();

            HashMap filterFileds = new HashMap();
            filterFileds.put("catgsetname", getCategorySetFilter());
            filterFileds.put("desc", getDescFilter());
            
            HashMap paramMap = new HashMap();
            paramMap.put("collection", s_categotySets);
            paramMap.put("filterFieldsValues", filterFileds);
            System.out.println("called super filtered class");
            
            filtered_CategorySets = (List)super.getFileteredCollection(CategorySetBO.class, paramMap);
            System.out.println("collection size is " + filtered_CategorySets.size());
            providerChangeSupport.fireProviderRefresh("categorySets");
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
