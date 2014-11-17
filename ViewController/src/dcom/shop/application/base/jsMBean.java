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
}
