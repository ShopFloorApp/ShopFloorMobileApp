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
            String orderNum = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.documnetNumber}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.documnetNumber}"));
            
            String event =  "true".equalsIgnoreCase(AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.Event}").toString())?"CONCURRENT":"ONLINE";
            RestCallerUtil rcu = new RestCallerUtil();
            System.out.println("values to method are 1 "+pickRule+" 2 "+orderNum+" 3 "+event);
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
                    String message=jsObject.get("XMSG").toString();

                    
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.pickRelStatus}", status);
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.pickRelMsg}", message);


                    
                } catch (ParseException e) {
                    e.getMessage();
                }
                }
    }

    public void clearPickRel(ActionEvent actionEvent) {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.pickRule}", "10");
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.documnetNumber}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.Event}", Boolean.TRUE);
    }
}
