package dcom.shop.DispatchList.manage;

import java.util.Map;

import javax.el.MethodExpression;
import javax.el.ValueExpression;

import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;

public class DispatchBean {
    public DispatchBean() {
        super();
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
        Map pageFlow = (Map) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope}");
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{bindings.subinv.inputValue}", String.class);
        String subinv = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        pageFlow.put("FromSubinventory", subinv);
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.refresh.execute}", Object.class, new Class[] {
                                                                     });
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
    }
    
    public void locatorChangeListner(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        Map pageFlow = (Map) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope}");
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{bindings.locator.inputValue}", String.class);
        String locator = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        pageFlow.put("FromLocator", locator);
    }

    public void transactComponentsTrxType(ValueChangeEvent valueChangeEvent) {
        if (valueChangeEvent.getNewValue().equals("WIP Return")){
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.trxType}", "WIP_RETURN");
        }else{
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.trxType}", "WIP_ISSUE");
        }
    }
}
