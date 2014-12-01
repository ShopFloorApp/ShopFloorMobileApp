package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.DepartmentBO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.GenericVirtualType;

public class DepartmentDC extends SyncUtils {
    private List filtered_Departments=new ArrayList();
    private String deptFilter = "";

    
    private String nameFilter = "";
    protected static List s_dept = new ArrayList();
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);


    public DepartmentDC() {
        try {
            GenericVirtualType payload = new GenericVirtualType(null, "payload");
            HashMap paramsMap=new HashMap();
            paramsMap.put("resAttriName","DeptDetails");
            paramsMap.put("lovDCName", "DepartmentLOV_WS");
            paramsMap.put("opeartionName", "process");
            paramsMap.put("payload",payload);
            s_dept = super.getCollection(DepartmentBO.class, paramsMap);
            filterDepartments();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setDeptFilter(String deptFilter) {
        String oldDeptFilter = this.deptFilter;
        this.deptFilter = deptFilter;
        propertyChangeSupport.firePropertyChange("deptFilter", oldDeptFilter, deptFilter);
    }

    public String getDeptFilter() {
        return deptFilter;
    }

    public void setNameFilter(String nameFilter) {
        String oldNameFilter = this.nameFilter;
        this.nameFilter = nameFilter;
        propertyChangeSupport.firePropertyChange("nameFilter", oldNameFilter, nameFilter);
    }

    public String getNameFilter() {
        return nameFilter;
    }

    public DepartmentBO[] getDepartments() {

        try {
            System.out.println("called collection departments");
            DepartmentBO[] departments = null;
            departments = (DepartmentBO[]) filtered_Departments.toArray(new DepartmentBO[filtered_Departments.size()]);
            return departments;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void filterDepartments() {
        try {
            System.out.println("inside filter code");
            filtered_Departments.clear();

            HashMap filterFileds = new HashMap();
            filterFileds.put("DeptCode", getDeptFilter());
            filterFileds.put("Desc", getNameFilter());
            

            HashMap paramMap = new HashMap();
            paramMap.put("collection", s_dept);
            paramMap.put("filterFieldsValues", filterFileds);
            System.out.println("called super filtered class");
            
            filtered_Departments = (List)super.getFileteredCollection(DepartmentBO.class, paramMap);
            System.out.println("collection size is " + filtered_Departments.size());
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

