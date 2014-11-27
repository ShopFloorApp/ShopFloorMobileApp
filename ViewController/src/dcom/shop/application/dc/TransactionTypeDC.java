package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.TransactionTypeBO;
import dcom.shop.application.mobile.UOMBO;

import dcom.shop.application.mobile.WarehouseBO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.GenericVirtualType;

public class TransactionTypeDC extends SyncUtils {    
    private List filtered_TransactionTypes=new ArrayList();
    private String transactiontypeFilter = "";
    protected static List s_transactionType = new ArrayList();
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    
    public TransactionTypeDC() {
        try{
        GenericVirtualType payload = new GenericVirtualType(null, "payload");
        HashMap paramsMap=new HashMap();
        paramsMap.put("resAttriName","TrxTypeDetails");
        paramsMap.put("lovDCName", "TransactionTypeLOV_WS");
        paramsMap.put("opeartionName", "process");
        paramsMap.put("payload",payload);
        s_transactionType = super.getCollection(TransactionTypeBO.class, paramsMap);
            filterTransactionTypes();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public TransactionTypeBO[] getTransactionTypes() {

        try {
            TransactionTypeBO[] transactionTypes = null;            
            transactionTypes = (TransactionTypeBO[]) filtered_TransactionTypes.toArray(new TransactionTypeBO[filtered_TransactionTypes.size()]);
            return transactionTypes;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public void filterTransactionTypes() {
        try {
            System.out.println("inside filter code");
            filtered_TransactionTypes.clear();

            HashMap filterFileds = new HashMap();
            filterFileds.put("transactiontype", getTransactiontypeFilter());
            

            HashMap paramMap = new HashMap();
            paramMap.put("collection", s_transactionType);
            paramMap.put("filterFieldsValues", filterFileds);
            System.out.println("called super filtered class");
            
            filtered_TransactionTypes = (List)super.getFileteredCollection(TransactionTypeBO.class, paramMap);
            System.out.println("collection size is " + filtered_TransactionTypes.size());
            providerChangeSupport.fireProviderRefresh("transactionTypes");
        } catch (Exception e) {
            throw new RuntimeException("My Code Error " + e);
        }
    }

    public void setTransactiontypeFilter(String transactiontypeFilter) {
        String oldTransactiontypeFilter = this.transactiontypeFilter;
        this.transactiontypeFilter = transactiontypeFilter;
        propertyChangeSupport.firePropertyChange("transactiontypeFilter", oldTransactiontypeFilter,
                                                 transactiontypeFilter);
    }

    public String getTransactiontypeFilter() {
        return transactiontypeFilter;
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
