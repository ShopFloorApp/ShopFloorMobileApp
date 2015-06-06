package dcom.shop.DispatchList.manage;

import java.util.Map;

import javax.el.MethodExpression;
import javax.el.ValueExpression;

import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;


public class DispatchBean {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public DispatchBean() {
        super();
    }

    boolean showAvailQty = true;

    public void setShowAvailQty(boolean showAvailQty) {
        boolean oldShowAvailQty = this.showAvailQty;
        this.showAvailQty = showAvailQty;
        propertyChangeSupport.firePropertyChange("showAvailQty", oldShowAvailQty, showAvailQty);
    }

    public boolean isShowAvailQty() {
        return showAvailQty;
    }

    public void jobOperationsSorting(ValueChangeEvent valueChangeEvent) {
        try {
            String newValue = valueChangeEvent.getNewValue().toString();
            MethodExpression methodExpression =
                AdfmfJavaUtilities.getMethodExpression("#{bindings.sortJobOperation.execute}", Object.class, new Class[] {
                                                       });
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.jobOprSortCol}", newValue);
            methodExpression.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void subInvChangeListner(ValueChangeEvent valueChangeEvent) {
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.refresh.execute}", Object.class, new Class[] {
                                                                     });
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
    }

    public void transactComponentsTrxType(ValueChangeEvent valueChangeEvent) {
        if (valueChangeEvent.getNewValue().equals("WIP Return")) {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.trxType}", "WIP_RETURN");
        } else {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.trxType}", "WIP_ISSUE");
        }
    }

    public void showTrxResult(String status, String message) {
        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(), "showAlert", new Object[] {
                                                                  status, message, "Ok"
        });

    }

    /*
    public void fromStepChangeListener(ValueChangeEvent valueChangeEvent) {
        // Add event code here...

        if (valueChangeEvent.getNewValue().equals("Queue")) {
        setShowAvailQty(true);
        } else if(valueChangeEvent.getNewValue().equals("Run")) {
            setShowAvailQty(true);
        }
        else {
            setShowAvailQty(true);
        }
        }

     */

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }

    public void fromStepListener(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        if (valueChangeEvent.getNewValue().equals("Queue")) {
            setShowAvailQty(true);
        } else if (valueChangeEvent.getNewValue().equals("Run")) {
            setShowAvailQty(true);
        } else {
            setShowAvailQty(true);
        }

    }
}
