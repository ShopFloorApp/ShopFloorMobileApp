package dcom.shop.Request.bean;

import dcom.shop.application.dc.txn.ConcurrentProgramDC;
import dcom.shop.application.mobile.txn.ConcProgramParamLovBO;
import dcom.shop.application.mobile.txn.ConcProgramParamsBO;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

import javax.el.MethodExpression;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
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
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.param5}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.param1}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.param2}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.param3}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.param4}", null);
        MethodExpression me =
            AdfmfJavaUtilities.getMethodExpression("#{bindings.populateProgramsParams.execute}", Object.class, new Class[] {
                                                   });
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
    }
    
    public void clearAction(ActionEvent actionEvent) {
        // Add event code here...
        
        param.valueMap.clear();
        param.valueDispMap.clear();
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.seq}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.selectedDispParamsLovVal}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.selectedParamsLovVal}", null);
        MethodExpression me =
            AdfmfJavaUtilities.getMethodExpression("#{bindings.refreshConcProgParams.execute}", Object.class, new Class[] {
                                                   });
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
    }
    
    public void getLov(ActionEvent actionEvent) {
        // Add event code here...
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
            "                  \"NLSLanguage\": \"AMERICAN\",\n" + "                  \"Org_Id\": \"100\"\n" +
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
    public void clearPage(ActionEvent ae){
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.requestIdS}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.name}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.applicationName}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.requestorName}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.phase}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.status}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.startDate}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.endDate}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.dateType}", null);
    }
    
    public void getMyRequests(ActionEvent ae){
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.requestIdS}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.name}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.applicationName}", null);
        
        
        String userName = (String) AdfmfJavaUtilities.evaluateELExpression("#{securityContext.userName}");
        
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.requestorName}",userName.toUpperCase());
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.phase}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.status}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.startDate}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.endDate}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.dateType}", null);
        MethodExpression me =
            AdfmfJavaUtilities.getMethodExpression("#{bindings.searchRequests.execute}", Object.class, new Class[] {
                                                   });
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
    }
    public void getRequestByRequestId(ActionEvent ae){
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.requestIdS}", AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.requestId}"));
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.name}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.applicationName}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.requestorName}", "");
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.phase}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.status}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.startDate}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.endDate}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.dateType}", null);
        MethodExpression me =
            AdfmfJavaUtilities.getMethodExpression("#{bindings.searchRequests.execute}", Object.class, new Class[] {
                                                   });
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
    }
    
    public void callJS(String btn){
        String featureID = AdfmfJavaUtilities.getFeatureId();
        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(featureID, "showPopup", new Object[] {btn});
    }
    
    public void getRefParamValForLov(ActionEvent ae){
        HashMap paramDispMap=new HashMap();
        HashMap paramMap=new HashMap();
        paramMap.putAll(param.valueMap);
        paramDispMap.putAll(param.valueDispMap);
        String param1 = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.param1}")==null?"":paramMap.get(AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.param1}")));
        String param2 = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.param2}")==null?"":paramMap.get(AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.param2}")));
        String param3 = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.param3}")==null?"":paramMap.get(AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.param3}")));
        String param4 = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.param4}")==null?"":paramMap.get(AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.param4}")));

        if(param1==null){
            param1 = (String) paramDispMap.get(AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.param1}"));
        }
        if(param2==null){
            param2 = (String) paramDispMap.get(AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.param2}"));
        }
        if(param3==null){
            param3 = (String) paramDispMap.get(AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.param3}"));
        }
        if(param4==null){
            param4 = (String) paramDispMap.get(AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.param4}"));
        }


        AdfmfJavaUtilities.setELValue("#{pageFlowScope.param1}", param1);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.param2}", param2);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.param3}", param3);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.param4}", param4);
    }

    public void applicationValueChange(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        MethodExpression me =
            AdfmfJavaUtilities.getMethodExpression("#{bindings.refreshFilteredConcurrentPrograms.execute}", Object.class, new Class[] {
                                                   });
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
    }
    
    public void refreshParamValues(ActionEvent ae){
        String seqNo = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.seq}"));   
        String selectedDispValue = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.selectedDispParamsLovVal}"));                                                                                                        
        String selectedValue = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.selectedParamsLovVal}"));
        if(seqNo!=null){
            param.valueMap.put(seqNo, selectedValue);
            param.valueDispMap.put(seqNo, selectedDispValue);
        }
    }
    
    public void searchRequest(ActionEvent ae){
        ConcurrentProgramDC cp=new ConcurrentProgramDC();
        cp.searchRequests();
        Integer requestCount=cp.s_requests.size();
        if(requestCount==0){
            callJS("cb4");
        }else{
            callJS("cb3");
        }
    }
}
