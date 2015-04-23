package dcom.shop.application.dc.txn;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.txn.UnloadTruckBO;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.el.ValueExpression;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.Utility;
import oracle.adfmf.util.logging.Trace;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class UnloadTruckDC {
    public UnloadTruckDC() {
        super();        
    }
    protected static List s_dockDoor = new ArrayList();
    protected static List s_LPN = new ArrayList();
    protected static List s_subInvLocator = new ArrayList();    
    public void saveUnloadTruck() {
            UnloadTruckBO unloadTruck = new UnloadTruckBO();
            
            String lpn = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.searchLpnKeyword}");
            String dockDoor = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.searchDockDoor}");            
            String subInvLocator = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.subInvLocator}");            
            
            unloadTruck.setDockDoor(dockDoor);
            unloadTruck.setLPN(lpn);
            unloadTruck.setSubInvLocator(subInvLocator);
            SyncUtils sync = new SyncUtils();
            
            
        }
       
}
