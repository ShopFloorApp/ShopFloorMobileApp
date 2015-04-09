package dcom.shop.Inquiry;

import javax.el.ValueExpression;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;

public class NavigationVarUtilBean {
    public NavigationVarUtilBean() {
        super();
    }
    public String getOnhandNavigations(){
        ValueExpression ve = null;
        String callingPage = null;
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.callingPg}", String.class);
        callingPage = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        
        if (callingPage.equals("prodInq")){
            return "prodOnhandListBack";
        }
        else 
            return "__back";
    }
    public String getOnhandDetailsNavigations(){
        ValueExpression ve = null;
        String callingPage = null;
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.callingPg}", String.class);
        callingPage = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        
        if (callingPage.equals("prodInq")){
            return "prodOnhandDetailsBack";
        }
        else 
            return "__back";
    }
}
