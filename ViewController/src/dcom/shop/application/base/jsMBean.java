package dcom.shop.application.base;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;

public class jsMBean {
    public jsMBean() {
    }

    public void rightNav(ActionEvent actionEvent) {
        String featureID = AdfmfJavaUtilities.getFeatureId();
        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(featureID, "rightNavigationToggleBtn", new Object[] {});
    }

    public String hideRightMenu() {
        // Add event code here...
        String featureID = AdfmfJavaUtilities.getFeatureId();
        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(featureID, "rightNavigationToggleBtn", new Object[] {});
        return null;
    }
    
    public void flipCardBack(ActionEvent actionEvent) {
        System.out.println("code reached in flip card");
        String val = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.deckName}");
        Integer i=new Integer(Integer.parseInt(val)-1);
        String featureID = AdfmfJavaUtilities.getFeatureId();
        System.out.println("code is reaching before javascript "+val);
        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(featureID, "flipCardLayoutAnimActionBack", new Object[] {i});
    }
    
    public void flipCardFront(ActionEvent actionEvent) {
        System.out.println("code reached in flip card");
        String val = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.deckName}");
        Integer i=new Integer(Integer.parseInt(val)-1);
        String featureID = AdfmfJavaUtilities.getFeatureId();
        System.out.println("code is reaching before javascript "+val);
        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(featureID, "flipCardLayoutAnimActionFront", new Object[] {i});
    }
    public void changeListItemValues(ActionEvent actionEvent) {
        // Add event code here...
        String featureID = AdfmfJavaUtilities.getFeatureId();
        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(featureID, "changeCardLytValues", new Object[] {});
    }
}
