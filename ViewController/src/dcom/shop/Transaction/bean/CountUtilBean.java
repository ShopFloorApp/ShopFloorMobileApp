package dcom.shop.Transaction.bean;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;

public class CountUtilBean {
    public CountUtilBean() {
    }

    public void clearCountPage(ActionEvent actionEvent) {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.name}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.FromSubinventory}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.FromLocator}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchLpnKeyword}", null);            
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.callingPg}", null);   
    }

    public void clearDetailPage(ActionEvent actionEvent) {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchKeyword}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.description}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.uom}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.availQty}", null);  
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.qty}", null);        
    }
}
