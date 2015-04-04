package dcom.shop.Inquiry;


import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;

public class SearchUtilBean {
    public SearchUtilBean() {
    }

    public void clearPage(ActionEvent ae) {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchKeyword}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.itemCat}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.itemType}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.itemStatus}", null);
        
    }
    public void clearLPNPage(ActionEvent ae) {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchLpnKeyword}", null);
    }
    public void clearOHPage(ActionEvent ae) {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchKeyword}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.ItemLovPage}", null);
        AdfmfJavaUtilities.setELValue("#{bindings.subInv.inputValue}", 0);
        AdfmfJavaUtilities.setELValue("#{bindings.fromLoc.inputValue}", 0);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.costGrp}", 0);       
    } 
    public void clearTrxPage(ActionEvent ae) {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchKeyword}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.ItemLovPage}", null);
        AdfmfJavaUtilities.setELValue("#{bindings.subFromInv.inputValue}", null);
        AdfmfJavaUtilities.setELValue("#{bindings.fromLoc.inputValue}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchLpnKeyword}", null);       
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.LpnLovPage}", null);       
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.txnType}", null);       
    }

    public void clearOnHandPageStart(ActionEvent actionEvent) {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchKeyword}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.ItemLovPage}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.costGrp}", null); 
    }

    public void clearTrxPageStart(ActionEvent actionEvent) {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchKeyword}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.ItemLovPage}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchLpnKeyword}", null);       
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.LpnLovPage}", null);       
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.txnType}", null);   
    }
}
