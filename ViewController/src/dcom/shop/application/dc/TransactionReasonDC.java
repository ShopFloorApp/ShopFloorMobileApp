package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.TransactionReasonBO;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.GenericVirtualType;

public class TransactionReasonDC extends SyncUtils {
    private List filtered_transactionReason=new ArrayList();
    private String reasonNameFilter = "";
    protected static List s_transactionReason = new ArrayList();
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    public TransactionReasonDC() {
        GenericVirtualType payload = new GenericVirtualType(null, "payload");
        HashMap paramsMap=new HashMap();
        paramsMap.put("resAttriName","TrxReasonDetails");
        paramsMap.put("lovDCName", "TransactionReasonLOV_WS");
        paramsMap.put("opeartionName", "process");
        paramsMap.put("payload",payload);
        s_transactionReason = super.getCollection(TransactionReasonBO.class, paramsMap);
        filterTransactionReason();
    }
    
    public TransactionReasonBO[] getTransactionReasons() {

        try {
            TransactionReasonBO[] transactionReasons = null;
            
            transactionReasons = (TransactionReasonBO[]) filtered_transactionReason.toArray(new TransactionReasonBO[filtered_transactionReason.size()]);
            return transactionReasons;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setReasonNameFilter(String reasonNameFilter) {
        this.reasonNameFilter = reasonNameFilter;
    }

    public String getReasonNameFilter() {
        return reasonNameFilter;
    }

    public void filterTransactionReason() {
        try {
            System.out.println("inside filter code");
            filtered_transactionReason.clear();

            HashMap filterFileds = new HashMap();
            filterFileds.put("reasonname", getReasonNameFilter());
          

            HashMap paramMap = new HashMap();
            paramMap.put("collection", s_transactionReason);
            paramMap.put("filterFieldsValues", filterFileds);
            System.out.println("called super filtered class");
            
            filtered_transactionReason = (List)super.getFileteredCollection(TransactionReasonBO.class, paramMap);
            System.out.println("collection size is " + filtered_transactionReason.size());
            providerChangeSupport.fireProviderRefresh("transactionReasons");
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



