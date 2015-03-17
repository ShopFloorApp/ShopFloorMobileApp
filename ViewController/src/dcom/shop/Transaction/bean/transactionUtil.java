package dcom.shop.Transaction.bean;

import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.util.Utility;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class transactionUtil {
    public transactionUtil() {
    }

    public void executePickRelease(ActionEvent actionEvent) {
            System.out.println("Inside execute Pick Release");
            Utility.ApplicationLogger.info("Inside execute Pick Release");
            String restURI = RestURI.PostSubmitProgramURI();
            String pickRule = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.pickRule}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.pickRule}"));
            String orderNum = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.OrderNumber}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.OrderNumber}"));
            String event = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.Event}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.Event}"));
            RestCallerUtil rcu = new RestCallerUtil();
            String payload =
                "{\n" + 
                "\"PickRelease_Input\":\n" + 
                "{\n" + 
                "\"@xmlns\": \"http://xmlns.oracle.com/apps/per/rest/dcomship/pickrelease/\",\n" + 
                "   \"RESTHeader\": {\"@xmlns\": \"http://xmlns.oracle.com/apps/per/rest/dcomship/header\",\n" + 
                "                  \"Responsibility\": \"DCOM_MOBILE_USER\",\n" + 
                "                  \"RespApplication\": \"INV\",\n" + 
                "                  \"SecurityGroup\": \"STANDARD\",\n" + 
                "                  \"NLSLanguage\": \"AMERICAN\",\n" + 
                "                  \"Org_Id\": \"82\"\n" + 
                "                 },\n" + 
                "   \"InputParameters\": \n" + 
                "        {\"PICKRULE\": \""+pickRule+"\",\n" + 
                "         \"ORDERNUM\": \""+orderNum+"\",\n" +
                "       \"EVENT\": \"\""+event+" \n" + 
                "       }\n" + 
                "}\n" + 
                "}";
            System.out.println("Calling pickrelease execute method");
            String jsonArrayAsString = rcu.invokeUPDATE(restURI, payload);
            System.out.println("Received response");
            if (jsonArrayAsString != null) {
                try {
                    JSONParser parser = new JSONParser();
                    Object object;

                    object = parser.parse(jsonArrayAsString);

                    JSONObject jsonObject = (JSONObject) object;
                    JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                    String status=jsObject.get("XSTATUS").toString();
                    
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.pickRelStatus}", status);

                    
                } catch (ParseException e) {
                    e.getMessage();
                }
                }
    }
}
