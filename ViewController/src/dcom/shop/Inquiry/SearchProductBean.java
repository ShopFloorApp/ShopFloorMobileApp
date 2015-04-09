package dcom.shop.Inquiry;

import java.util.Map;

import javax.el.ValueExpression;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.exception.AdfException;

public class SearchProductBean {
    public SearchProductBean() {
    }

    public String validateKeyword() {
        ValueExpression ve = null;
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchKeyword}", String.class);
        String keyword =((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        if(keyword.length()<3){
            Map pageFlow = (Map) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope}");
            pageFlow.put("keywrdLenErr", "Yes");
            return null;
      //      throw new AdfException("Enter atleast 3 characters.",AdfException.ERROR);     
            
        }
        else{
        return "productlist";
        }
    }

    public void resetVariable(ActionEvent actionEvent) {
        Map pageFlow = (Map) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope}");
        pageFlow.put("searchKeyword", "");
    }

    public String validateLpnKeyword() {
        // Add event code here...
        ValueExpression ve = null;
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchLpnKeyword}", String.class);
        String keyword =((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        if(keyword.length()<3){
            Map pageFlow = (Map) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope}");
            pageFlow.put("keywrdLpnLenErr", "Yes");
            return null;
        //      throw new AdfException("Enter atleast 3 characters.",AdfException.ERROR);
            
        }
        else{
        return "lpnlist";
        }
    }
}
