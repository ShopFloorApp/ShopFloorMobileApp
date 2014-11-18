package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.AccountAliasBO;
import dcom.shop.application.mobile.CarrierBO;

import dcom.shop.application.mobile.WarehouseBO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.GenericVirtualType;

public class AccountAliasDC extends SyncUtils {
    private List filtered_AccountAlias=new ArrayList();
    private String accountaliasFilter = "";
    private String descFilter = "";
    protected static List s_accountAlias = new ArrayList();
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    
    public AccountAliasDC() {
        try{
        GenericVirtualType payload = new GenericVirtualType(null, "payload");
        HashMap paramsMap=new HashMap();
        paramsMap.put("resAttriName","AccountAliasDetails");
        paramsMap.put("lovDCName", "AccountAliasLOV_WS");
        paramsMap.put("opeartionName", "process");
        paramsMap.put("payload",payload);
        s_accountAlias = super.getCollection(AccountAliasBO.class, paramsMap);
        filterAccountAlias();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    public AccountAliasBO[] getAccountAlias() {

        try {
            AccountAliasBO[] accountAlias = null;
            accountAlias = (AccountAliasBO[]) filtered_AccountAlias.toArray(new AccountAliasBO[filtered_AccountAlias.size()]);
            return accountAlias;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void filterAccountAlias() {
        try {
            System.out.println("inside filter code");
            filtered_AccountAlias.clear();

            HashMap filterFileds = new HashMap();
            filterFileds.put("accountalias", getAccountaliasFilter());
            filterFileds.put("desc", getDescFilter());
            

            HashMap paramMap = new HashMap();
            paramMap.put("collection", s_accountAlias);
            paramMap.put("filterFieldsValues", filterFileds);
            System.out.println("called super filtered class");
            
            filtered_AccountAlias = (List)super.getFileteredCollection(AccountAliasBO.class, paramMap);
            System.out.println("collection size is " + filtered_AccountAlias.size());
            providerChangeSupport.fireProviderRefresh("accountAlias");
        } catch (Exception e) {
            throw new RuntimeException("My Code Error " + e);
        }
    }

    public void setAccountaliasFilter(String accountaliasFilter) {
        String oldAccountaliasFilter = this.accountaliasFilter;
        this.accountaliasFilter = accountaliasFilter;
        propertyChangeSupport.firePropertyChange("accountaliasFilter", oldAccountaliasFilter, accountaliasFilter);
    }

    public String getAccountaliasFilter() {
        return accountaliasFilter;
    }

    public void setDescFilter(String descFilter) {
        String oldDescFilter = this.descFilter;
        this.descFilter = descFilter;
        propertyChangeSupport.firePropertyChange("descFilter", oldDescFilter, descFilter);
    }

    public String getDescFilter() {
        return descFilter;
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
