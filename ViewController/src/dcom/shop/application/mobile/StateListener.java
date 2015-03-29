package dcom.shop.application.mobile;

import dcom.shop.application.dc.LocatorDC;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.el.MethodExpression;
import javax.el.ValueExpression;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.javax.faces.model.SelectItem;

public class StateListener {
    public StateListener() {
    }

    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);

    public void FromSubinvValueChange(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        String s = (String)valueChangeEvent.getOldValue();
        Map pageFlow = (Map) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope}");
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{bindings.subFromInv.inputValue}", String.class);
        String subinv = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        //          String subinv = (String)valueChangeEvent.getNewValue();

       // pageFlow.put("FromSubinventory", subinv);
        /*ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.refreshFromLocator}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "Y");
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.refreshFromSubinventory}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "N");*/

        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.refresh.execute}", Object.class, new Class[] {
                                                                     });
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
        //providerChangeSupport.fireProviderRefresh("locators");
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

    public void FromLocValueChange(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        Map pageFlow = (Map) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope}");
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{bindings.fromLoc.inputValue}", String.class);
        String subinv = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        //          String subinv = (String)valueChangeEvent.getNewValue();

      //  pageFlow.put("FromLocator", subinv);
        /*ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.refreshFromLocator}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "N");*/

    }

    public void ToLocValueChange(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        Map pageFlow = (Map) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope}");
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{bindings.toLoc.inputValue}", String.class);
        String subinv = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        //          String subinv = (String)valueChangeEvent.getNewValue();

      //  pageFlow.put("ToLocator", subinv);
        /*ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.refreshToLocator}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "N");*/

    }

    public void lotQtyChange(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.insertLots.execute}", Object.class, new Class[] {
                                                                     });
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
    }
    
    public void callLovPage(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
       // AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(), "adf.mf.api.amx.doNavigation", new Object[] { "ItemLOV" });
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.ItemLovPage}","SubinvTrfr");
       
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
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.Subinventory}", String.class);
        String subinv = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.Locator}", String.class);
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
        return "Back";
    }

    public void SwipeLeftSerial(ActionEvent actionEvent) {
        // Add event code here...
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.renderDeleteButton}",true);
        
    }
    public void SwipeRightSerial(ActionEvent actionEvent) {
        // Add event code here...
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.renderDeleteButton}",false);
        
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
}
