package dcom.shop.application.dc.txn;

import dcom.shop.application.base.SyncUtils;

import dcom.shop.application.mobile.txn.ConcurrentProgramBO;
import dcom.shop.application.mobile.WarehouseBO;

import dcom.shop.application.mobile.transaction.SubInventoryTxnBO;
import dcom.shop.application.mobile.txn.ConcProgramParamLovBO;
import dcom.shop.application.mobile.txn.ConcProgramParamsBO;
import dcom.shop.application.mobile.txn.RequestsBO;

import dcom.shop.restURIDetails.RestCallerUtil;

import dcom.shop.restURIDetails.RestURI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.GenericVirtualType;
import oracle.adfmf.util.Utility;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ConcurrentProgramDC extends SyncUtils {
    protected static List s_requests = new ArrayList();
    protected static List s_concurrentProgram = new ArrayList();
    protected static List s_concurrentProgramParams = new ArrayList();
    public static List s_concurrentProgramParamLov = new ArrayList();
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    

    public ConcurrentProgramDC() {
        super();
    }

    public void addProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.addProviderChangeListener(l);
    }

    public void removeProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.removeProviderChangeListener(l);
    }
    
    public void populateConcPrograms(){
        s_concurrentProgram.clear();
        System.out.println("Inside orgItem");
        Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
        String restURI = RestURI.PostProgramListURI();
        RestCallerUtil rcu = new RestCallerUtil();
        String payload =
            "{\n" + "\"GET_SO_PER_ORG_Input\":\n" + "{\n" +
            "\"@xmlns\": \"http://xmlns.oracle.com/apps/fnd/rest/GetSoPerOrgSvc/get_so_per_org/\",\n" +
            "   \"RESTHeader\": {\"@xmlns\": \"http://xmlns.oracle.com/apps/fnd/rest/GetSoPerOrgSvc/header\",\n" +
            "                  \"Responsibility\": \"DCOM_MOBILE_USER\",\n" +
            "                  \"RespApplication\": \"INV\",\n" +
            "                  \"SecurityGroup\": \"STANDARD\",\n" +
            "                  \"NLSLanguage\": \"AMERICAN\",\n" + "                  \"Org_Id\": \"100\"\n" +
            "                 },\n" + "   \"InputParameters\": \n" + "      {\"PUSERNAME\": \"1\",\n" +
            "       \"PROLENAME\": \"1\"\n }\n" + "}\n" + "}\n";
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
                JSONObject jsObject1 = (JSONObject) jsObject.get("XCPLIST");
                if(jsObject1==null){
                    return ;
                }
                JSONArray array = (JSONArray) jsObject1.get("XCPLIST_ITEM");
                if (array != null) {
                    int size = array.size();

                    for (int i = 0; i < size; i++) {

                        ConcurrentProgramBO concProgItems = new ConcurrentProgramBO();
                        JSONObject jsObject2 = (JSONObject) array.get(i);

                        concProgItems.setShortName((jsObject2.get("SHORTNAME").toString()));
                        concProgItems.setName((jsObject2.get("NAME").toString()));
                        concProgItems.setApplCode((jsObject2.get("APPLCODE").toString()));
                        concProgItems.setApplName((jsObject2.get("APPLNAME").toString()));

                        s_concurrentProgram.add(concProgItems);

                    }
                    
                }
            } catch (ParseException e) {
                e.getMessage();
            }
        } else {
            return ;
        }
        return ;
    }

    public ConcurrentProgramBO[] getConcurrentPrograms() {
        
                    ConcurrentProgramBO[] concProgArray =
                        (ConcurrentProgramBO[]) s_concurrentProgram.toArray(new ConcurrentProgramBO[s_concurrentProgram.size()]);
                    return concProgArray;
               
    }
    public ConcurrentProgramBO[] getFilteredConcurrentPrograms() {
        String application = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.applicationName}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.applicationName}"));
        HashMap filterFileds = new HashMap();
        
        filterFileds.put("applcode", application);

        HashMap paramMap = new HashMap();
        paramMap.put("collection", s_concurrentProgram);
        paramMap.put("filterFieldsValues", filterFileds); 

        ArrayList s_filteredConcProgram = (ArrayList) getFileteredCollection(ConcurrentProgramBO.class, paramMap);      
        
                    ConcurrentProgramBO[] concProgArray =
                        (ConcurrentProgramBO[]) s_filteredConcProgram.toArray(new ConcurrentProgramBO[s_filteredConcProgram.size()]);
                    return concProgArray;
               
    }
    
    public void refreshFilteredConcurrentPrograms() {
        providerChangeSupport.fireProviderRefresh("filteredConcurrentPrograms");
    }

    public RequestsBO[] getRequest() {
        s_requests.clear();
        System.out.println("Inside orgItem");
        Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
        String restURI = RestURI.PostRequestsURI();
        RestCallerUtil rcu = new RestCallerUtil();
        String requestId = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.requestIdS}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.requestIdS}"));
        String program = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.name}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.name}"));
        String application = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.applicationName}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.applicationName}"));
        String username = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.requestorName}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.requestorName}"));;
        String role = "";
        String phase = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.phase}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.phase}"));
        String status = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.status}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.status}"));
        String startdate = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.startDate}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.startDate}"));
        String enddate = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.endDate}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.endDate}"));
        String dateType=(String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.dateType}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.dateType}"));
        String payload =
            "{\n" + "\"GET_SO_PER_ORG_Input\":\n" + "{\n" +
            "\"@xmlns\": \"http://xmlns.oracle.com/apps/fnd/rest/GetSoPerOrgSvc/get_so_per_org/\",\n" +
            "   \"RESTHeader\": {\"@xmlns\": \"http://xmlns.oracle.com/apps/fnd/rest/GetSoPerOrgSvc/header\",\n" +
            "                  \"Responsibility\": \"DCOM_MOBILE_USER\",\n" +
            "                  \"RespApplication\": \"INV\",\n" +
            "                  \"SecurityGroup\": \"STANDARD\",\n" +
            "                  \"NLSLanguage\": \"AMERICAN\",\n" + 
            "                  \"Org_Id\": \"100\"\n" +
            "                 },\n" + "   \"InputParameters\": \n" + 
            "                {\"PREQUESTID\": \""+requestId+"\",\n" + "\n" +
            "                \"PPROGCODE\": \""+program+"\",\n" + "\n" +
            "                \"PPROGAPPL\": \""+application+"\",\n" + "\n" +
            "                \"PUSERNAME\": \""+username+"\",\n" + "\n" +
            "                \"PROLENAME\": \""+role+"\",\n" + "\n" +
            "                \"PSTATUS\": \""+status+"\",\n" + "\n" +
            "                \"PPHASE\": \""+phase+"\",\n" + "\n" +
            "                \"PDATETYPE\": \""+dateType+"\",\n" + "\n" +
            "                \"PFROMDATE\": \""+startdate+"\",\n" + "\n" +
            "                \"PTODATE\": \""+enddate+"\"\n }\n" + "}\n" + "}\n";
        System.out.println("Calling create method");
        String jsonArrayAsString = rcu.invokeUPDATE(restURI, payload);
        System.out.println("Received response");
        RequestsBO[] requests = null;
        if (jsonArrayAsString != null) {
            try {
                JSONParser parser = new JSONParser();
                Object object;

                object = parser.parse(jsonArrayAsString);

                JSONObject jsonObject = (JSONObject) object;
                JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                JSONObject jsObject1 = (JSONObject) jsObject.get("XREQUEST");
                if(jsObject1==null){
                    return null;
                }
                JSONArray array = (JSONArray) jsObject1.get("XREQUEST_ITEM");
                
                if (array != null) {
                    int size = array.size();
//                    RequestsBO requestItem = new RequestsBO();
//                    s_requests.add(requestItem);
                    for (int i = 0; i < size; i++) {

                        RequestsBO requestItems = new RequestsBO();
                        JSONObject jsObject2 = (JSONObject) array.get(i);

                        requestItems.setRequestId((jsObject2.get("REQUESTID").toString()));
                        requestItems.setProgName((jsObject2.get("PROGNAME").toString()));
                        requestItems.setApplName((jsObject2.get("APPLNAME").toString()));
                        requestItems.setPhase((jsObject2.get("PHASE").toString()));
                        requestItems.setStatus((jsObject2.get("STATUS").toString()));
                        requestItems.setHrsTaken((jsObject2.get("HRSTAKEN").toString()));
                        requestItems.setMinTaken((jsObject2.get("MINTAKEN").toString()));
                        requestItems.setSsTaken((jsObject2.get("SSTAKEN").toString()));
                        requestItems.setMsTaken((jsObject2.get("MSTAKEN").toString()));
                        requestItems.setSubmitDate((jsObject2.get("SUBMITDATE").toString()));
                        requestItems.setActualStart((jsObject2.get("ACTUALSTART").toString()));
                        requestItems.setActualCompl((jsObject2.get("ACTUALCOMPL").toString()));
                        requestItems.setSubmittedBy((jsObject2.get("SUBMITTEDBY").toString()));
                        requestItems.setRespName((jsObject2.get("RESPNAME").toString()));
                        requestItems.setLogSize((jsObject2.get("LOGSIZE").toString()));
                        requestItems.setOutPutSize((jsObject2.get("OUTPUTSIZE").toString()));

                        s_requests.add(requestItems);

                    }
                    RequestsBO[] requestArray =
                        (RequestsBO[]) s_requests.toArray(new RequestsBO[s_requests.size()]);
                    return requestArray;
                }
            } catch (ParseException e) {
                e.getMessage();
            }catch(ClassCastException c){
                JSONParser parser = new JSONParser();
                try{
                Object object = parser.parse(jsonArrayAsString);
                
                JSONObject jsonObject = (JSONObject) object;
                JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                JSONObject jsObject1 = (JSONObject) jsObject.get("XREQUEST");
                JSONObject jsObject2 = (JSONObject) jsObject1.get("XREQUEST_ITEM");
                RequestsBO requestItems = new RequestsBO();
                requestItems.setRequestId((jsObject2.get("REQUESTID").toString()));
                requestItems.setProgName((jsObject2.get("PROGNAME").toString()));
                requestItems.setApplName((jsObject2.get("APPLNAME").toString()));
                requestItems.setPhase((jsObject2.get("PHASE").toString()));
                requestItems.setStatus((jsObject2.get("STATUS").toString()));
                requestItems.setHrsTaken((jsObject2.get("HRSTAKEN").toString()));
                requestItems.setMinTaken((jsObject2.get("MINTAKEN").toString()));
                requestItems.setSsTaken((jsObject2.get("SSTAKEN").toString()));
                requestItems.setMsTaken((jsObject2.get("MSTAKEN").toString()));
                requestItems.setSubmitDate((jsObject2.get("SUBMITDATE").toString()));
                requestItems.setActualStart((jsObject2.get("ACTUALSTART").toString()));
                requestItems.setActualCompl((jsObject2.get("ACTUALCOMPL").toString()));
                requestItems.setSubmittedBy((jsObject2.get("SUBMITTEDBY").toString()));
                requestItems.setRespName((jsObject2.get("RESPNAME").toString()));
                requestItems.setLogSize((jsObject2.get("LOGSIZE").toString()));
                requestItems.setOutPutSize((jsObject2.get("OUTPUTSIZE").toString()));

                s_requests.add(requestItems);
                RequestsBO[] requestArray =
                    (RequestsBO[]) s_requests.toArray(new RequestsBO[s_requests.size()]);
                return requestArray;
                }catch(ParseException e) {
                e.getMessage();
                }
            }
        } else {
            return null;
        }
        return null;
    }

    public void populateProgramsParams(){
        s_concurrentProgramParams.clear();
        System.out.println("Inside orgItem");
        Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
        String restURI = RestURI.PostProgramParamsURI();
        RestCallerUtil rcu = new RestCallerUtil();
        String shortName = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.shortName}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.shortName}"));
        String applCcde = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.applCode}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.applCode}"));
        String payload =
            "{\n" + "\"GET_SO_PER_ORG_Input\":\n" + "{\n" +
            "\"@xmlns\": \"http://xmlns.oracle.com/apps/fnd/rest/GetSoPerOrgSvc/get_so_per_org/\",\n" +
            "   \"RESTHeader\": {\"@xmlns\": \"http://xmlns.oracle.com/apps/fnd/rest/GetSoPerOrgSvc/header\",\n" +
            "                  \"Responsibility\": \"DCOM_MOBILE_USER\",\n" +
            "                  \"RespApplication\": \"INV\",\n" +
            "                  \"SecurityGroup\": \"STANDARD\",\n" +
            "                  \"NLSLanguage\": \"AMERICAN\",\n" + 
            "                  \"Org_Id\": \"82\"\n" +
            "                 },\n" + "   \"InputParameters\": \n" + 
            "                   {\"PPROGCODE\": \""+shortName+"\",\n" +
            "                    \"PPROGAPPL\": \""+applCcde+"\"\n }\n" + "}\n" + "}\n";
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
                JSONObject jsObject1 = (JSONObject) jsObject.get("XPARAMLIST");
                if(jsObject1==null){
                    return ;
                }
                JSONArray array = (JSONArray) jsObject1.get("XPARAMLIST_ITEM");
                
                if (array != null) {
                    int size = array.size();
                    for (int i = 0; i < size; i++) {

                        ConcProgramParamsBO concProgramParamsItems = new ConcProgramParamsBO();
                        JSONObject jsObject2 = (JSONObject) array.get(i);

                        concProgramParamsItems.setSeqNum((jsObject2.get("SEQNUM").toString()));
                        concProgramParamsItems.setParamName((jsObject2.get("PARAMNAME").toString()));
                        concProgramParamsItems.setRequired((jsObject2.get("REQUIRED").toString()));
                        concProgramParamsItems.setDisplayed((jsObject2.get("DISPLAYED").toString()));
                        concProgramParamsItems.setIsLov((jsObject2.get("ISLOV").toString()));
                        concProgramParamsItems.setDefaultType((jsObject2.get("DEFAULTTYPE").toString()));
                        concProgramParamsItems.setDefaultValue(jsObject2.get("DEFAULTVALUE").toString());
                        
                        concProgramParamsItems.setParamType((jsObject2.get("PARAMTYPE").toString()));
                        concProgramParamsItems.setValueSetName((jsObject2.get("VALUESETNAME").toString()));
                        concProgramParamsItems.setRefParam1((jsObject2.get("REFPARAM1").toString()));
                        concProgramParamsItems.setRefParam2((jsObject2.get("REFPARAM2").toString()));
                        concProgramParamsItems.setRefParam3((jsObject2.get("REFPARAM3").toString()));
                        concProgramParamsItems.setRefParam4((jsObject2.get("REFPARAM4").toString()));
                        concProgramParamsItems.setRefParam5((jsObject2.get("REFPARAM5").toString()));
                        concProgramParamsItems.setParamDispValue((jsObject2.get("DEFAULTVALUE").toString()));

                        s_concurrentProgramParams.add(concProgramParamsItems);

                    }
                   
                }
            } catch (ParseException e) {
                e.getMessage();
            }catch(ClassCastException c){
                JSONParser parser = new JSONParser();
                try{
                Object object = parser.parse(jsonArrayAsString);
                
                JSONObject jsonObject = (JSONObject) object;
                JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                JSONObject jsObject1 = (JSONObject) jsObject.get("XPARAMLIST");
                JSONObject jsObject2 = (JSONObject) jsObject1.get("XPARAMLIST_ITEM");                                                                                         
                    ConcProgramParamsBO concProgramParamsItem = new ConcProgramParamsBO();
             

                    concProgramParamsItem.setSeqNum((jsObject2.get("SEQNUM").toString()));
                    concProgramParamsItem.setParamName((jsObject2.get("PARAMNAME").toString()));
                    concProgramParamsItem.setRequired((jsObject2.get("REQUIRED").toString()));
                    concProgramParamsItem.setDisplayed((jsObject2.get("DISPLAYED").toString()));
                    concProgramParamsItem.setIsLov((jsObject2.get("ISLOV").toString()));
                    concProgramParamsItem.setDefaultType((jsObject2.get("DEFAULTTYPE").toString()));
                    concProgramParamsItem.setDefaultValue((jsObject2.get("DEFAULTVALUE").toString()));
                    concProgramParamsItem.setParamType((jsObject2.get("PARAMTYPE").toString()));
                    concProgramParamsItem.setValueSetName((jsObject2.get("VALUESETNAME").toString()));
                    concProgramParamsItem.setRefParam1((jsObject2.get("REFPARAM1").toString()));
                    concProgramParamsItem.setRefParam2((jsObject2.get("REFPARAM2").toString()));
                    concProgramParamsItem.setRefParam3((jsObject2.get("REFPARAM3").toString()));
                    concProgramParamsItem.setRefParam4((jsObject2.get("REFPARAM4").toString()));
                    concProgramParamsItem.setRefParam5((jsObject2.get("REFPARAM5").toString()));
                    concProgramParamsItem.setParamDispValue((jsObject2.get("DEFAULTVALUE").toString()));
                    
                    s_concurrentProgramParams.add(concProgramParamsItem);

                }catch(ParseException e) {
                e.getMessage();
                }
            } 
        } else {
            return ;
        }
        return ;
    }

    public ConcProgramParamsBO[] getConcProgramParams() {
        
                    ConcProgramParamsBO[] requestArray =
                        (ConcProgramParamsBO[]) s_concurrentProgramParams.toArray(new ConcProgramParamsBO[s_concurrentProgramParams.size()]);
                    return requestArray;                
    }
    
    public void refreshConcProgParams(){
        providerChangeSupport.fireProviderRefresh("concProgramParams");
    }
    
    public ConcProgramParamLovBO[] getConcProgramParamLov() {
        System.out.println("Inside orgItem");
        s_concurrentProgramParamLov.clear();
        Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
        String restURI = RestURI.PostProgramParamLovURI();
            String progCode = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.shortName}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.shortName}"));
            String seq = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.seq}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.seq}"));
            String valueSet = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.valueSet}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.valueSet}"));
        String param5 = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.param5}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.param5}"));
        String param1 = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.param1}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.param1}"));
        String param2 = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.param2}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.param2}"));
        String param3 = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.param3}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.param3}"));
        String param4 = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.param4}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.param4}"));
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
            "                  {\"PPROGCODE\": \""+progCode+"\",\n" + 
            "                  \"PSEQ\": \""+seq+"\",\n" +
            "                  \"PREFPARAM5\": \""+param5+"\",\n" +
            "                  \"PREFPARAM1\": \""+param1+"\",\n" +
            "                  \"PREFPARAM2\": \""+param2+"\",\n" +
            "                  \"PREFPARAM3\": \""+param3+"\",\n" +
            "                  \"PREFPARAM4\": \""+param4+"\",\n" +
            "                    \"PVALUESET\": \""+valueSet+"\"\n }\n" + "}\n" + "}\n";
        System.out.println("Calling create method");
        String jsonArrayAsString = rcu.invokeUPDATE(restURI, payload);
        System.out.println("Received response");
        ConcProgramParamLovBO[] concProgramParamLov = null;
        if (jsonArrayAsString != null) {
            try {
                JSONParser parser = new JSONParser();
                Object object;

                object = parser.parse(jsonArrayAsString);

                JSONObject jsonObject = (JSONObject) object;
                JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                JSONObject jsObject1 = (JSONObject) jsObject.get("XLOV");
                if(jsObject1==null){
                    return null;
                }
                JSONArray array = (JSONArray) jsObject1.get("XLOV_ITEM");
                
                if (array != null) {
                    int size = array.size();
                    for (int i = 0; i < size; i++) {

                        ConcProgramParamLovBO concurrentProgramParamLovItems = new ConcProgramParamLovBO();
                        JSONObject jsObject2 = (JSONObject) array.get(i);

                        concurrentProgramParamLovItems.setRef((jsObject2.get("REF").toString()));
                        concurrentProgramParamLovItems.setValue((jsObject2.get("VALUE").toString()));
                        concurrentProgramParamLovItems.setName((jsObject2.get("NAME").toString()));
                        concurrentProgramParamLovItems.setMeaning((jsObject2.get("MEANING").toString()));
                        
                        s_concurrentProgramParamLov.add(concurrentProgramParamLovItems);

                    }
                        ConcProgramParamLovBO[] concurrentProgramParamLovArray =
                            (ConcProgramParamLovBO[]) s_concurrentProgramParamLov.toArray(new ConcProgramParamLovBO[s_concurrentProgramParamLov.size()]);
                    return concurrentProgramParamLovArray;
                }
            } catch (ParseException e) {
                e.getMessage();
            }catch(ClassCastException c){
                JSONParser parser = new JSONParser();
                try{
                Object object = parser.parse(jsonArrayAsString);
                
                JSONObject jsonObject = (JSONObject) object;
                JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                JSONObject jsObject1 = (JSONObject) jsObject.get("XLOV");
                JSONObject jsObject2 = (JSONObject) jsObject1.get("XLOV_ITEM");      
                    
                    ConcProgramParamLovBO concurrentProgramParamLovItems = new ConcProgramParamLovBO();                 

                    concurrentProgramParamLovItems.setRef((jsObject2.get("REF").toString()));
                    concurrentProgramParamLovItems.setValue((jsObject2.get("VALUE").toString()));
                    concurrentProgramParamLovItems.setName((jsObject2.get("NAME").toString()));
                    concurrentProgramParamLovItems.setMeaning((jsObject2.get("MEANING").toString()));
                    
                    s_concurrentProgramParamLov.add(concurrentProgramParamLovItems);

                    
                        ConcProgramParamLovBO[] concurrentProgramParamLovArray =
                        (ConcProgramParamLovBO[]) s_concurrentProgramParamLov.toArray(new ConcProgramParamLovBO[s_concurrentProgramParamLov.size()]);
                    return concurrentProgramParamLovArray;
                    
                }catch(ParseException e) {
                e.getMessage();
                }
            } 
        } else {
            return null;
        }
        return null;
    }
    
}
