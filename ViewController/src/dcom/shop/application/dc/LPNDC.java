package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;

import dcom.shop.application.mobile.ItemBO;
import dcom.shop.application.mobile.LPNBO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.adfmf.framework.exception.AdfInvocationException;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.GenericVirtualType;

public class LPNDC  extends SyncUtils {
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public LPNDC() {
        super();
    }
    List s_lpnsList = new ArrayList();
    private String LPNNum;
    public LPNBO[] getAllLpns() {
           System.out.println("Inside getAllLPNs method " + s_lpnsList.size());
           //ItemWSPayloadResponse[] itemArray =
           //  (ItemWSPayloadResponse[])s_itemsList.toArray(new ItemWSPayloadResponse[s_itemsList.size()]);
           LPNBO[] lpnArray = (LPNBO[])s_lpnsList.toArray(new LPNBO[s_lpnsList.size()]);
           return lpnArray;
       }

    public void setLPNNum(String LPNNum) {
        String oldLPNNum = this.LPNNum;
        this.LPNNum = LPNNum;
        propertyChangeSupport.firePropertyChange("LPNNum", oldLPNNum, LPNNum);
    }

    public String getLPNNum() {
        return LPNNum;
    }

    public void ProcessWS() throws AdfInvocationException {
        s_lpnsList.clear();
        String lpnNum = getLPNNum();
         GenericVirtualType payload = new GenericVirtualType(null, "payload");
        payload.defineAttribute(null, "Whse", String.class, "100");
        payload.defineAttribute(null, "LPNNum", String.class, lpnNum);
        payload.defineAttribute(null, "Action", String.class, "Query");
        
        HashMap paramsMap = new HashMap();
        paramsMap.put("resAttriName", "LPNDetails");
        paramsMap.put("lovDCName", "LPNLOV_WS");
        paramsMap.put("opeartionName", "process");
        paramsMap.put("payload", payload);
        s_lpnsList = super.getCollectionFromWS(LPNBO.class, paramsMap);
        providerChangeSupport.fireProviderRefresh("allLpns");

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
