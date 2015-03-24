package dcom.shop.DispatchList.manage;

import javax.el.MethodExpression;

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
                AdfmfJavaUtilities.getMethodExpression("#{bindings.sortJobOperation.execute}", Object.class, new Class[]{});
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.jobOprSortCol}", newValue);
            methodExpression.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
