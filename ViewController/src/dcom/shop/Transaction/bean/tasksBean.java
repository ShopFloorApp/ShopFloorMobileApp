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
                AdfmfJavaUtilities.getMethodExpression("#{bindings.sortTasks.execute}", Object.class, new Class[] {
                                                       });
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.sortBy}", newValue);
            methodExpression.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void sortMethod(ActionEvent actionEvent) {
        try {
            MethodExpression methodExpression =
                AdfmfJavaUtilities.getMethodExpression("#{bindings.sortTasks.execute}", Object.class, new Class[] {
                                                       });
            methodExpression.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void filterAL(ActionEvent actionEvent) {
        //lists are represented by the  AmxAttributeBinding
        try{

        AmxAttributeBinding filterList = (AmxAttributeBinding) AdfmfJavaUtilities
                                      .evaluateELExpression("#{bindings.filterData}");

        String selectedCustomerNames = "";

        /*         AmxIteratorBinding amxListIterator =  filterList.getIteratorBinding();
        
        //the basic iterator in the AmxIteratorBinding is what we need to work with
         BasicIterator      basicIterator = amxListIterator.getIterator();
        Integer customerIndx = 0;

         while (basicIterator.hasNext()){
            basicIterator.setCurrentIndex(customerIndx.intValue());
            //get current row. Note that for POJO models you call getDataProvider and cast to 
            //the entity. For SOAP models you call getCurrentRow and cast it to GenericType 
            SelectItem customer = (SelectItem) basicIterator.getDataProvider();
            //for this sample, print selected customers into a text field on the page
            selectedCustomerNames = selectedCustomerNames+ ("'"+customer.getValue()+"',");
             customerIndx++;
         }
         
         if(selectedCustomerNames.length()>0){
             selectedCustomerNames = selectedCustomerNames.substring(0, selectedCustomerNames.length()-1);
         } */
            
            selectedCustomerNames = "'Move Order Issue'";
        
        
        //try {
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
