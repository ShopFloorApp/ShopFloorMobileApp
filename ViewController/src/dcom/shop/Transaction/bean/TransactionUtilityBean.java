package dcom.shop.Transaction.bean;

import dcom.shop.application.base.GPSLocatorBean;

import java.util.HashMap;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;

public class TransactionUtilityBean {
    public TransactionUtilityBean() {
        super();
    }
    public void getLocation(ActionEvent ae){
        System.out.println("test");;
        GPSLocatorBean gPSLocatorBean=new GPSLocatorBean();
        HashMap points=gPSLocatorBean.getDefaultLocation();
        String subinv = (String) points.get("subinv");
        String locator = (String) points.get("locator");
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.FromSubinventory}", subinv);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.FromLocator}", locator);
    }
}
