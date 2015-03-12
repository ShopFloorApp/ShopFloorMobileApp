package dcom.shop.Request.bean;

import dcom.shop.application.mobile.txn.ConcProgramParamLovBO;
import dcom.shop.application.mobile.txn.ConcProgramParamsBO;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.util.Utility;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RequestUtilBean {
    ConcProgramParamsBO param=new ConcProgramParamsBO();
    public RequestUtilBean() {
    }

    public void paramValueChange(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
    }

    public void listAction(ActionEvent actionEvent) {
        // Add event code here...
        
        param.valueMap.clear();
        param.valueDispMap.clear();
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.seq}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.selectedDispParamsLovVal}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.selectedParamsLovVal}", null);
    }

    public void submitProgram(ActionEvent actionEvent) {
        // Add event code here...
        HashMap paramValueMap=new HashMap();
        HashMap paramDispValueMap=new HashMap();
        paramValueMap.putAll(param.valueMap);
        paramDispValueMap.putAll(param.valueDispMap);
        StringBuffer paramJson=new StringBuffer();
        Iterator entries = paramDispValueMap.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            System.out.println("Key = " + key + ", Value = " + value);
            String ref="";
            if(paramValueMap.get(key)==null){
                ref=value;
            }else{
                ref = (String) paramValueMap.get(key);
            }
            ref=ref==null?"":ref;
            value=value==null?"":value;
            paramJson.append("{");
            paramJson.append("\"SEQ\" :\""+key+"\",");
            paramJson.append("\"REF\" :\""+ref+"\",");
            paramJson.append("\"VALUE\" :\""+value+"\"");
            paramJson.append("},");
        }
        String finalJsonPayload=paramJson.toString();
        finalJsonPayload=finalJsonPayload.substring(0,finalJsonPayload.length()-1);
        String requestId=getConcProgramParamLov(finalJsonPayload);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.requestId}", requestId);
    }
    
    public String getConcProgramParamLov(String paramJsonList) {
        System.out.println("Inside orgItem");
        Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
        String restURI = RestURI.PostSubmitProgramURI();
        String shortName = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.shortName}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.shortName}"));
        String applCcde = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.applCode}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.applCode}"));
        RestCallerUtil rcu = new RestCallerUtil();
        String payload =
            "{\n" + "\"GET_SO_PER_ORG_Input\":\n" + "{\n" +
            "\"@xmlns\": \"http://xmlns.oracle.com/apps/fnd/rest/GetSoPerOrgSvc/get_so_per_org/\",\n" +
            "   \"RESTHeader\": {\"@xmlns\": \"http://xmlns.oracle.com/apps/fnd/rest/GetSoPerOrgSvc/header\",\n" +
            "                  \"Responsibility\": \"DCOM_MOBILE_USER\",\n" +
            "                  \"RespApplication\": \"INV\",\n" +
            "                  \"SecurityGroup\": \"STANDARD\",\n" +
            "                  \"NLSLanguage\": \"AMERICAN\",\n" + "                  \"Org_Id\": \"82\"\n" +
            "                 },\n" + "   \"InputParameters\": \n" + 
            "                  {\"PPROGCODE\": \""+shortName+"\",\n" + 
            "                  \"PPROGAPPL\": \""+applCcde+"\",\n" +
            "                    \"PPARAMLIST\": {\n" + 
            "                   \"PPARAMLIST_ITEM\" :["+paramJsonList+"]\n" + 
            "}\n }\n" + "}\n" + "}\n";
        System.out.println("Calling create method");
        String jsonArrayAsString = rcu.invokeUPDATE(restURI, payload);
        System.out.println("Received response");
        if (jsonArrayAsString != null) {
            try {
                JSONParser parser = new JSONParser();
                Object object;

                object = parser.parse(jsonArrayAsString);

                JSONObject jsonObject = (JSONObject) object;
                JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                String requestId=jsObject.get("XREQUESTID").toString();
                
                return requestId;
                
            } catch (ParseException e) {
                e.getMessage();
            }
            }
        return null;
    }
}
