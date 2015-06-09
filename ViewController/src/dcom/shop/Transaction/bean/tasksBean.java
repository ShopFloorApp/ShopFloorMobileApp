package dcom.shop.Transaction.bean;

import java.util.ArrayList;

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

           String selectedCustomerNames =
                (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.filteredList}");

            
            MethodExpression methodExpression =
                AdfmfJavaUtilities.getMethodExpression("#{bindings.filterTasks.execute}", Object.class, new Class[] {
                                                       });
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.filterValues}", selectedCustomerNames);
            methodExpression.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void filterVL(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        Object[] newVal = (Object[]) valueChangeEvent.getNewValue();

         Object[]  oldVal = (Object[]) valueChangeEvent.getOldValue();
         ArrayList<Integer> selectedCustomers = new ArrayList<Integer>();      
         for (int i = 0; i < newVal.length; i++) {
             selectedCustomers.add(new Integer((String)newVal[i]));
         }
         AmxAttributeBinding customerList = (AmxAttributeBinding) AdfmfJavaUtilities
                                       .evaluateELExpression("#{bindings.filterData}");

         String selectedCustomerNames = "";

         AmxIteratorBinding amxListIterator =  customerList.getIteratorBinding();
         
          BasicIterator      basicIterator = amxListIterator.getIterator();
                
          for (Integer customerIndx : selectedCustomers) {
             basicIterator.setCurrentIndex(customerIndx.intValue());
             SelectItem customer = (SelectItem) basicIterator.getDataProvider();
             selectedCustomerNames = selectedCustomerNames+("'"+ customer.getValue()+"',");
          }
          
        
          
        if (selectedCustomerNames.length() > 0) {
           selectedCustomerNames = selectedCustomerNames.substring(0, selectedCustomerNames.length() - 1);
        }
      
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.filteredList}",selectedCustomerNames);
        
        
    }
}
