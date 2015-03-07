package dcom.shop.application.mobile;

import dcom.shop.application.dc.LocatorDC;

import javax.el.ValueExpression;

import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;

public class StateListener {
    public StateListener() {
    }
    
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    
    public void FromSubinvValueChange(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        LocatorDC loc = new LocatorDC();
        //providerChangeSupport.fireProviderRefresh("locator");
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
}
