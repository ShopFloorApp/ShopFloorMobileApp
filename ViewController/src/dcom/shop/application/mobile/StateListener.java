package dcom.shop.application.mobile;

import dcom.shop.application.dc.LocatorDC;

import dcom.shop.application.mobile.txn.RequestsBO;

import java.util.ArrayList;

import java.util.List;

import java.util.Map;

import javax.el.MethodExpression;
import javax.el.ValueExpression;

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
        Map pageFlow = (Map) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope}");
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{bindings.FromSubInv.inputValue}", String.class);
        String subinv = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        //          String subinv = (String)valueChangeEvent.getNewValue();

        pageFlow.put("FromSubinventory", subinv);

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
            s.setValue(requestArray[i].getDesc());
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
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{bindings.ToSubInv.inputValue}", String.class);
        String subinv = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        //          String subinv = (String)valueChangeEvent.getNewValue();

        pageFlow.put("ToSubinventory", subinv);

        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.refreshToLocators.execute}", Object.class, new Class[] {
                                                                     });
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
    }
}
