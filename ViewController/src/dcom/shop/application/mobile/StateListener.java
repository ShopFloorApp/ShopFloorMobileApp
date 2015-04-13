package dcom.shop.application.mobile;

import dcom.shop.application.dc.LocatorDC;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.el.MethodExpression;
import javax.el.ValueExpression;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.javax.faces.model.SelectItem;

public class StateListener {
    public StateListener() {
    }

    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);

    public void FromSubinvValueChange(ValueChangeEvent valueChangeEvent) {
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.refresh.execute}", Object.class, new Class[] {
                                                                     });
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
    }

    public List<SelectItem> getLocatorItems() {

        LocatorDC loc = new LocatorDC();
        LocatorBO[] requestArray =
            (LocatorBO[]) loc.getFiltered_Locators().toArray(new LocatorBO[loc.getFiltered_Locators().size()]);
        ArrayList<SelectItem> s_array = new ArrayList<SelectItem>();
        for (int i = 0; i < loc.getFiltered_Locators().size(); i++) {
            SelectItem s = new SelectItem();
            s.setValue(requestArray[i].getDescription());
            s_array.add(s);
        }
        return s_array;
    }

    public void addProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.addProviderChangeListener(l);
    }

    public void removeProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.removeProviderChangeListener(l);
    }

    public String callFromSubinv() {
        // Add event code here...
        ValueExpression ve;
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.SubinventoryPage}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "From");
        return "Subinv";
    }

    public String callFromLocator() {
        // Add event code here...
        ValueExpression ve;
        ValueExpression Operation;
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.LocatorPage}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "FromSubInv");
        return "Locator";
    }

    public String callToLocator() {
        // Add event code here...
        ValueExpression ve;
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.LocatorPage}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "ToSubInv");
        return "Locator";
    }

    public String callToSubInv() {
        // Add event code here...
        ValueExpression ve;
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.SubinventoryPage}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "To");
        return "Subinv";
    }

    public String callFromMiscTrxn() {
        // Add event code here...
        return "Subinv";
    }

    public String callFromMiscTrxnLoc() {
        // Add event code here...
        return "Locator";
    }

    public void ToSubinvValueChange(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        Map pageFlow = (Map) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope}");
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{bindings.subToInv.inputValue}", String.class);
        String subinv = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        //          String subinv = (String)valueChangeEvent.getNewValue();

        //   pageFlow.put("ToSubinventory", subinv);
        /*ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.refreshToLocator}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "Y");

        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.refreshToSubinventory}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "N");*/

        MethodExpression me =
            AdfmfJavaUtilities.getMethodExpression("#{bindings.refreshToLocators.execute}", Object.class, new Class[] {
                                                   });
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
    }

    public void FromSerialChange(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.fromSerial}", String.class);
        String fromSerial = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        //   ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.toSerial}", String.class);
        // ve.setValue(AdfmfJavaUtilities.getAdfELContext(), fromSerial);

    }

    public void ToSerialChange(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.insertSerials.execute}", Object.class, new Class[] {
                                                                     });
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
    }

    public void SerialChangeListener(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        /*   ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.fromSerialRangeOff}", String.class);
        String serial = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.fromSerial}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), serial);
        ValueExpression tove = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.toSerial}", String.class);
        tove.setValue(AdfmfJavaUtilities.getAdfELContext(), serial);*/
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.insertSerials.execute}", Object.class, new Class[] {
                                                                     });
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
    }

    /* public void FromLocValueChange(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        Map pageFlow = (Map) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope}");
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{bindings.fromLoc.inputValue}", String.class);
        String subinv = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        //          String subinv = (String)valueChangeEvent.getNewValue();

        //  pageFlow.put("FromLocator", subinv);
        /*ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.refreshFromLocator}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "N");

    }



    public void ToLocValueChange(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        Map pageFlow = (Map) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope}");
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{bindings.toLoc.inputValue}", String.class);
        String subinv = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        //          String subinv = (String)valueChangeEvent.getNewValue();

        //  pageFlow.put("ToLocator", subinv);
        /*ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.refreshToLocator}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "N");

    }
*/
    public void lotQtyChange(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.insertLots.execute}", Object.class, new Class[] {
                                                                     });
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
    }

    public void callLovPage(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        // AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(), "adf.mf.api.amx.doNavigation", new Object[] { "ItemLOV" });
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.ItemLovPage}", "SubinvTrfr");

    }

    public void SubinvValueChange(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        Map pageFlow = (Map) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope}");
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{bindings.subFromInv.inputValue}", String.class);
        String subinv = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        //          String subinv = (String)valueChangeEvent.getNewValue();

        pageFlow.put("FromSubinventory", subinv);
        /*ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.refreshFromLocator}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "Y");
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.refreshFromSubinventory}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "N");*/

        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.refresh.execute}", Object.class, new Class[] {
                                                                     });
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
    }

    public String BackToLpnTrxn() {
        // Add event code here...
        Map pageFlow = (Map) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope}");
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.FromSubinventory}", String.class);
        String subinv = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.FromLocator}", String.class);
        String loc = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.RenderSubInvChoice}", String.class);
        if ("{\"@xsi:nil\":\"true\"}".equals(subinv))
            ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "Yes");
        else
            ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "No");
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.RenderLocChoice}", String.class);
        if ("{\"@xsi:nil\":\"true\"}".equals(loc))
            ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "Yes");
        else
            ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "No");
        return "__back";
    }

    public void SwipeLeftSerial(ActionEvent actionEvent) {
        // Add event code here...
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.renderDeleteButton}", true);

    }

    public void SwipeRightSerial(ActionEvent actionEvent) {
        // Add event code here...
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.renderDeleteButton}", false);

    }

    public void ClearSubInvPage(ActionEvent actionEvent) {
        // Add event code here...
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchLpnKeyword}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchKeyword}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.onHandQty}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.quantity}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.FromLocator}", "0");


        AdfmfJavaUtilities.setELValue("#{pageFlowScope.ToLocator}", "0");

        AdfmfJavaUtilities.setELValue("#{pageFlowScope.ToSubinventory}", "0");
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.FromSubinventory}", "0");
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.refresh.execute}", Object.class, new Class[] {
                                                                     });
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
        me = AdfmfJavaUtilities.getMethodExpression("#{bindings.refreshToLocators.execute}", Object.class, new Class[] {
                                                    });
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.availableQty}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.description}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.uom}", null);
    }
    
    public void ClearPackDirectShipPage(ActionEvent actionEvent) {
        // Add event code here...
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.dockDorr}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchLpnKeyword}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchKeyword}", null);
        
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.documnetNumber}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.orderLineNumber}", null);
        
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.quantity}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.locator}", "0");
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.subInv}", "0");
        
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.refresh.execute}", Object.class, new Class[] {
                                                                     });
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
         me =
            AdfmfJavaUtilities.getMethodExpression("#{bindings.refreshToLocators.execute}", Object.class, new Class[] {
                                                   });
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
        
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.availableQty}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.description}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.uom}", null);
    }

    public void ClearMiscTrxnPage(ActionEvent actionEvent) {
        // Add event code here...
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.TrxType}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.accountAlias}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchKeyword}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.onHandQty}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.quantity}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.FromLocator}", "0");


        AdfmfJavaUtilities.setELValue("#{pageFlowScope.FromSubinventory}", "0");
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.refresh.execute}", Object.class, new Class[] {
                                                                     });
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.availableQty}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.description}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.uom}", null);
    }

    public void ClearLpnTrxnPage(ActionEvent actionEvent) {
        // Add event code here...
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchLpnKeyword}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchToLpnKeyword}", null);

        AdfmfJavaUtilities.setELValue("#{pageFlowScope.serialControl}", "0");
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.lotControl}", "0");
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchKeyword}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.onHandQty}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.quantity}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.FromLocator}", "0");
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.FromSubinventory}", "0");
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.refresh.execute}", Object.class, new Class[] {
                                                                     });
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.availableQty}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.description}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.uom}", null);
    }

    public void LpnValueChange(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        System.out.println("here");
            
        if ("".equals(valueChangeEvent.getNewValue())) {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.LpnEnable}", "true");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.ItemEnable}", "true");
        } else {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.LpnEnable}", "true");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.ItemEnable}", "false");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchKeyword}", "");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.description}", "");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.uom}", "");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.availQty}", "");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.quantity}", "");
        }
    }
    
    public void ItemValueChange(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        System.out.println("here");
            
        if ("".equals(valueChangeEvent.getNewValue())) {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.LpnEnable}", "true");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.ItemEnable}", "true");
        } else {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.LpnEnable}", "false");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.ItemEnable}", "true");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchToLpnKeyword}", "");
        }
    }
    
    public void ItemValueChange(ActionEvent actionEvent) {
        // Add event code here...
        String item = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.searchKeyword}");
        if ("".equals(item)) {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.LpnEnable}", "true");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.ItemEnable}", "true");
        } else {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.LpnEnable}", "false");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.ItemEnable}", "true");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchToLpnKeyword}", "");
        }
    }

    public void ToLpnValueChange(ActionEvent actionEvent) {
        // Add event code here...
        String toLpn = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.searchToLpnKeyword}");
        if ("".equals(toLpn)) {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.LpnEnable}", "true");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.ItemEnable}", "true");
        } else {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.LpnEnable}", "true");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.ItemEnable}", "false");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchKeyword}", "");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.description}", "");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.uom}", "");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.availQty}", "");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.quantity}", "");
        }
    }

    public String validateLpnKeyword() {
        // Add event code here...
        ValueExpression ve = null;
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchLpnKeyword}", String.class);
        String keyword =((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        if(keyword.length()<3){
            Map pageFlow = (Map) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope}");
            pageFlow.put("keywrdLpnLenErr", "Yes");
            return null;
        //      throw new AdfException("Enter atleast 3 characters.",AdfException.ERROR);
            
        }
        else{
        return "lpnLOV";
        }
    }

    public String validateAndNavigateItemLov() {
        // Add event code here...
        String item = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.searchKeyword}").toString();
        if(item.length() < 3){
            
        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                  "showAlert", new Object[] {"Error","Please enter atleast 3 characters for Item.","Ok" });
            return "";
        }
        else
        return "ItemLOV";
    }
    
    public String validateAndNavigateLpnLov() {
        // Add event code here...
        String item = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.searchLpnKeyword}").toString();
        if(item.length() < 3){
            
        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                  "showAlert", new Object[] {"Error","Please enter atleast 3 characters for LPN.","Ok" });
            return "";
        }
        else
        return "lpnLOV";
    }
}
