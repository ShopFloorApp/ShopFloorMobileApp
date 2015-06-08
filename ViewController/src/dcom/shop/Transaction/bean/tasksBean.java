package dcom.shop.Transaction.bean;

import javax.el.MethodExpression;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.bindings.dbf.AmxAttributeBinding;
import oracle.adfmf.bindings.dbf.AmxIteratorBinding;
import oracle.adfmf.bindings.iterator.BasicIterator;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.javax.faces.model.SelectItem;

public class tasksBean {
    public tasksBean() {
    }

    public void sortTaskVL(ValueChangeEvent valueChangeEvent) {
        try {
            String newValue = valueChangeEvent.getNewValue().toString();
            MethodExpression methodExpression =
                AdfmfJavaUtilities.getMethodExpression("#{bindings.sortTasks.execute}", Object.class, new Class[] { });
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.sortBy}", newValue);
            methodExpression.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void sortMethod(ActionEvent actionEvent) {
        try {
            MethodExpression methodExpression =
                AdfmfJavaUtilities.getMethodExpression("#{bindings.sortTasks.execute}", Object.class, new Class[] { });
            methodExpression.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void filterAL(ActionEvent actionEvent) {
        //lists are represented by the  AmxAttributeBinding
        try {

            AmxAttributeBinding filterList =
                (AmxAttributeBinding) AdfmfJavaUtilities.evaluateELExpression("#{bindings.filterData}");

            String selectedCustomerNames = "";

            AmxIteratorBinding amxListIterator = filterList.getIteratorBinding();

            BasicIterator basicIterator = amxListIterator.getIterator();
            Integer customerIndx = 0;

            while (basicIterator.hasNext()) {
                basicIterator.setCurrentIndex(customerIndx.intValue());
                SelectItem customer = (SelectItem) basicIterator.getDataProvider();
                selectedCustomerNames = selectedCustomerNames + ("'" + customer.getValue() + "',");
                customerIndx++;
            }

            if (selectedCustomerNames.length() > 0) {
                selectedCustomerNames = selectedCustomerNames.substring(0, selectedCustomerNames.length() - 1);
            }

               selectedCustomerNames = "'Move Order Issue'";

            MethodExpression methodExpression =
                AdfmfJavaUtilities.getMethodExpression("#{bindings.filterTasks.execute}", Object.class, new Class[] {
                                                       });
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.filterValues}", selectedCustomerNames);
            methodExpression.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
