package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.database.ConnectionFactory;
import dcom.shop.application.mobile.SyncPreferencesBO;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import java.lang.reflect.Method;

import java.sql.Connection;
import java.sql.Statement;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;

public class SyncPreferencesDC extends SyncUtils {
    private List filtered_syncLovs = new ArrayList();
    private String lovnameFilter = "";
    protected static List s_syncLovs = new ArrayList();
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);

    public SyncPreferencesDC() {
        try {
            s_syncLovs = super.getOfflineCollection(SyncPreferencesBO.class);
            filterSyncLovs();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setLovnameFilter(String lovnameFilter) {
        String oldLovnameFilter = this.lovnameFilter;
        this.lovnameFilter = lovnameFilter;
        propertyChangeSupport.firePropertyChange("lovnameFilter", oldLovnameFilter, lovnameFilter);
    }

    public String getLovnameFilter() {
        return lovnameFilter;
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

    public SyncPreferencesBO[] getSyncLovs() {

        try {
            System.out.println("called collection warehouse");
            SyncPreferencesBO[] synclovs = null;
            synclovs = (SyncPreferencesBO[]) filtered_syncLovs.toArray(new SyncPreferencesBO[filtered_syncLovs.size()]);
            return synclovs;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void filterSyncLovs() {
        try {
            System.out.println("inside filter code");
            filtered_syncLovs.clear();

            HashMap filterFileds = new HashMap();
            filterFileds.put("lovname", getLovnameFilter());


            HashMap paramMap = new HashMap();
            paramMap.put("collection", s_syncLovs);
            paramMap.put("filterFieldsValues", filterFileds);
            System.out.println("called super filtered class");

            filtered_syncLovs = (List) super.getFileteredCollection(SyncPreferencesBO.class, paramMap);
            System.out.println("collection size is " + filtered_syncLovs.size());
            providerChangeSupport.fireProviderRefresh("syncLovs");
        } catch (Exception e) {
            throw new RuntimeException("My Code Error " + e);
        }
    }
    
    public void syncAll(){
        for(int i=0;i<filtered_syncLovs.size();i++){
            SyncPreferencesBO syncPreferencesBO = (SyncPreferencesBO) filtered_syncLovs.get(i);
            syncCard(syncPreferencesBO.getLovId(),syncPreferencesBO.getLovClassName(),syncPreferencesBO.getLovCollectVar(),syncPreferencesBO.getRowIdx());
        }
    }

    public void syncCard(String lovId, String lovDCClass, String lovCollectionVar,String rowIdx) {
        try {
            String featureID = AdfmfJavaUtilities.getFeatureId();
            Integer i=new Integer(Integer.parseInt(rowIdx)-1);
            
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(featureID, "deactivateCardLayout", new Object[] {i});
            
            Class lovClass = Class.forName("dcom.shop.application.dc." + lovDCClass);
//            Constructor cons = lovClass.getConstructor(new Class[] { });
//            Object obj = cons.newInstance(null);
            //Calling Sync Method
            Object obj = lovClass.newInstance();
            Method method = lovClass.getMethod("syncLocalDB", new Class[] {});
            method.invoke(obj, new Object[] {});       
            //End of Sync Card Method Call
            Field collectionField = lovClass.getDeclaredField(lovCollectionVar);
            List cardCollection = (List) collectionField.get(obj);
            int collectionSize = cardCollection.size();
            String strCollectionSize = "" + collectionSize;
//            String lovDCClassBOName = lovDCClass.substring(0, lovDCClass.length() - 2) + "BO";
//            Class lovDCClassBOClass = Class.forName("dcom.shop.application.mobile." + lovDCClassBOName);
//            super.updateSqlLiteTable(lovDCClassBOClass, cardCollection);
            Connection conn = null;
            conn = ConnectionFactory.getConnection();
            Statement stmt = conn.createStatement();
            Date currentDate = new Date();
            SimpleDateFormat dbFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strCurrentDate = dbFormat.format(currentDate);
            String updateQuery =
                "UPDATE SYNCPREFERENCES SET SYNCCOUNT='" + strCollectionSize + "',LASTSYNCDATETIME='" + strCurrentDate +
                "' WHERE LOVID='" + lovId + "';";
            System.out.println("update query is " + updateQuery);
            stmt.execute(updateQuery);
            
//            SyncPreferencesBO sync=new SyncPreferencesBO();
//            if(sync.getLovId().equals(lovId)){
//                sync.setSyncCount(strCollectionSize);
//            }
        SimpleDateFormat uiFormat = new SimpleDateFormat("dd-MMM-yyyy");
        String uiCurrentDate=uiFormat.format(currentDate);
        
        System.out.println("code is reaching before javascript "+rowIdx);
        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(featureID, "changeCardLytValues", new Object[] {i,strCollectionSize,uiCurrentDate});
            
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(featureID, "activateCardLayout", new Object[] {i});
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
