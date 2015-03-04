package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;

import dcom.shop.application.mobile.ItemBO;

import dcom.shop.application.mobile.SubinventoryBO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.el.ValueExpression;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.exception.AdfInvocationException;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.GenericType;
import oracle.adfmf.util.GenericVirtualType;

public class ItemDC  extends SyncUtils {
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    public ItemDC() {
        super();
    }
    
    List s_itemsList = new ArrayList();
    
    public ItemBO[] getAllItems() {
           System.out.println("Inside getAllItems method " + s_itemsList.size());
           //ItemWSPayloadResponse[] itemArray =
           //  (ItemWSPayloadResponse[])s_itemsList.toArray(new ItemWSPayloadResponse[s_itemsList.size()]);
           ItemBO[] itemArray = (ItemBO[])s_itemsList.toArray(new ItemBO[s_itemsList.size()]);
           return itemArray;
       }

       public void ProcessWS() throws AdfInvocationException {
           s_itemsList.clear();
           String itemNum = getItemNum();
           String itemDesc = getDesc();
           GenericVirtualType payload = new GenericVirtualType(null, "payload");
           payload.defineAttribute(null, "ItemNum", String.class, itemNum);
           payload.defineAttribute(null, "OrganizationId", String.class, "102");
           payload.defineAttribute(null, "Desc", String.class, itemDesc);
           
           HashMap paramsMap = new HashMap();
           paramsMap.put("resAttriName", "ItemList");
           paramsMap.put("lovDCName", "ItemLOV_WS");
           paramsMap.put("opeartionName", "process");
           paramsMap.put("payload", payload);
           s_itemsList = super.getCollectionFromWS(ItemBO.class, paramsMap);
           providerChangeSupport.fireProviderRefresh("allItems");

       }
       
    private String OrganizationId;

    public void setOrganizationId(String OrganizationId) {
        String oldOrganizationId = this.OrganizationId;
        this.OrganizationId = OrganizationId;
        propertyChangeSupport.firePropertyChange("OrganizationId", oldOrganizationId, OrganizationId);
    }

    public String getOrganizationId() {
        return OrganizationId;
    }

    public void setItemNum(String ItemNum) {
        String oldItemNum = this.ItemNum;
        this.ItemNum = ItemNum;
        propertyChangeSupport.firePropertyChange("ItemNum", oldItemNum, ItemNum);
    }

    public String getItemNum() {
        return ItemNum;
    }

    public void setDesc(String Desc) {
        String oldDesc = this.Desc;
        this.Desc = Desc;
        propertyChangeSupport.firePropertyChange("Desc", oldDesc, Desc);
    }

    public String getDesc() {
        return Desc;
    }
    private String ItemNum;
    private String Desc;

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
