package dcom.shop.Transaction.bean;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.LotBO;
import dcom.shop.application.mobile.SerialBO;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.el.ValueExpression;



import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.util.Utility;
import oracle.adfmf.util.logging.Trace;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CountUtilBean {
    public CountUtilBean() {
    }
    protected static List s_serialTrxns = new ArrayList();
    protected static List s_lotTrxns = new ArrayList();
    protected static List s_filteredSerialTrxns = new ArrayList();
    protected static List s_filteredLotTrxns = new ArrayList();
    
    public void clearCountPage(ActionEvent actionEvent) {
      //  AdfmfJavaUtilities.setELValue("#{pageFlowScope.name}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.FromSubinventory}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.FromLocator}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.ToSubinventory}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.ToLocator}", null);        
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchLpnKeyword}", null);            
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.callingPg}", null); 
       
        clearDetailPage(actionEvent);
    }

    public void clearDetailPage(ActionEvent actionEvent) {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchKeyword}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.description}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.uom}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.availQty}", null);  
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.qty}", null);    
    }


    
    public void callCountRestService(ActionEvent actionEvent) {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.countTxnMsg}", null); 
        ValueExpression ve = null;
        System.out.println("Insidecycle count service");
        Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
        SyncUtils sync = new SyncUtils();
        /* OrgItemEntity orgObj = new OrgItemEntity();
          orgObj.setOrgItem("STR");
          s_OrgList.add(orgObj);
          orgObj.setOrgItem("REN");
          s_OrgList.add(orgObj);*/
        String lpn = null;
        String subinv = null;
        String loc = null;
        String orgCode= null;
        String ccName = null;
        String itemNum = null;
        String uom = null;
        String trxQty = null;
        String action = null;
        String detailTxnType = null;
        String username = (String)AdfmfJavaUtilities.evaluateELExpression("#{securityContext.userName}");
        String ccType = null;
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.SubinvTrxnId}", String.class);
        String txnid =(String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        if (!((txnid == null) || txnid.equals(""))){
            Integer txnId = Integer.parseInt(txnid);
            s_serialTrxns = sync.getCollectionFromDB(SerialBO.class);
            filterSerials(txnId);
            s_lotTrxns = sync.getCollectionFromDB(LotBO.class);
            filterLots(txnId);
        }
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.name}", String.class);
        ccName = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        
        ve = AdfmfJavaUtilities.getValueExpression("#{preferenceScope.feature.dcom.shop.MyWarehouse.OrgCodePG.OrgCode}", String.class);
        orgCode = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();

        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchLpnKeyword}", String.class);
        lpn = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.ccType}", String.class);
        ccType = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        
        if(ccType.equals("A")){
            ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.FromSubinventory}", String.class);
            subinv = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
            
            ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.FromLocator}", String.class);
            loc = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
            
        }else {
            ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.ToSubinventory}", String.class);
            subinv = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
            
            ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.ToLocator}", String.class);
            loc = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        }
        
        
        
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchKeyword}", String.class);
        itemNum = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.uom}", String.class);
        uom = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.qty}", String.class);
        trxQty = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.callingPg}", String.class);
        action =((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.detTxnType}", String.class);
        detailTxnType =((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        
        String restURI = RestURI.PostCycleCountURI();
        RestCallerUtil rcu = new RestCallerUtil();
        String payload = null;
        
        payload ="{\n" + 
        "    \"x\": {\n" + 
        "        \n" + 
        "        \"RESTHeader\": {\n" + 
        "            \"Responsibility\": \"DCOM_MOBILE_USER\",\n" + 
        "            \"RespApplication\": \"INV\",\n" + 
        "            \"SecurityGroup\": \"STANDARD\",\n" + 
        "            \"NLSLanguage\": \"AMERICAN\",\n" + 
        "            \"Org_Id\": \"82\"\n" + 
        "        },\n" + 
        "        \"InputParameters\": {\n" + 
        "            \"P_WMS_TRX\": {\n" + 
        "                        \"ACTION\": \""+action+"\",\n" + 
        "                        \"ORG_CODE\": \""+orgCode+"\",\n" + 
        "                        \"CC_NAME\": \""+ccName+"\",\n" + 
        "                        \"SUBINV\": \""+subinv+"\",\n" + 
        "                        \"LOCATOR\": \""+loc+"\",\n" + 
        "                        \"PARENT_LPN\": \""+lpn+"\",";
        
        SerialBO serial = new SerialBO(); 
        if (s_filteredSerialTrxns.size() > 0) {
            payload = payload + "\"SERIAL_TAB\": { \"XXDCOM_SERIAL_TAB\": [";
  
                Iterator i = s_filteredSerialTrxns.iterator();
                while (i.hasNext()) {
                    serial = (SerialBO) i.next();
                
                    payload =payload + "{\"FROMSERIAL\":\"" + serial.getFromSerial() + "\",\"TOSERIAL\": \"" + serial.getToSerial() +
                                         "\",\"SERIALQTY\": \"" + serial.getSerialQty() + "\"},";
                }

                payload = payload.substring(0, payload.length() - 1);
                payload = payload + "]},";
        }
        
        LotBO lot = new LotBO();
        if (s_filteredLotTrxns.size() > 0){
            Iterator j = s_filteredLotTrxns.iterator();
            payload = payload + " \"LOT_TAB\": { \"XXDCOM_LOT_TAB\": [  ";
            
            while (j.hasNext()) {
                lot = (LotBO) j.next();
                payload = payload + "{\"LOT\":\"" + lot.getLotNo() + "\",\"LOTQTY\": \"" + lot.getLotQty() + "\"},";

            }
            payload = payload.substring(0, payload.length() - 1);
            payload = payload + "]},";
        }
        
        payload = payload +"\"ITEM_NUMBER\": \""+itemNum+"\",\n" + 
        "                        \"COUNT_UOM\": \""+uom+"\",\n" + 
        "                        \"PRIMARY_UOM_QTY\": \""+trxQty+"\",\n" + 
        "                        \"USER_NAME\": \""+username+"\" \n"+
        "}}}}";
        
        
        
        System.out.println("Calling create method");

       
        String jsonArrayAsString = (rcu.invokeUPDATE(restURI, payload)).toString();
        System.out.println("Received response");
        
        try{
            JSONParser parser = new JSONParser();
            Object object = parser.parse(jsonArrayAsString);
            JSONObject jsonObject = (JSONObject)object;
            JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
            if(jsObject != null){
             // QuickShipEntityBean quickShipStatus = new QuickShipEntityBean();
             
                String status =jsObject.get("X_STATUS").toString();
                String msg = jsObject.get("X_MESSAGE").toString();
               // Map pageFlow = (Map) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope}");
              //  pageFlow.put("countTxnMsg", msg);
                
                if(status.equals("E")){
              AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                                            "showAlert", new Object[] {
                                                                                            "Error", msg, "ok"
                                  });
                }
                else {
                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                                                  "showAlert", new Object[] {
                                                                                                  "Success", msg, "ok"
                                        });
                    
                }
                if (action.equals("DETAIL")){
                if(detailTxnType.equals("SAVE")){
                    clearDetailPage(actionEvent);
                    clearTxnId(actionEvent);
                }
                else {
                    
                    Map pageFlow1 = (Map) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope}");
                    pageFlow1.put("doneMsg", "DoneMsg");
                    clearCountPage(actionEvent);
                    clearCCName(actionEvent);
                    clearTxnId(actionEvent);
                }
                }
                else if(action.equals("SUMMARY")) {
                    clearCountPage(actionEvent);
                }
            }

        }catch(Exception e){
                Trace.log("REST_JSON",Level.SEVERE, this.getClass(),"ProductDetailsEntity", e.getLocalizedMessage());
            }
        
    }

    public void clearCCName(ActionEvent actionEvent) {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.name}", null);
    }
    public void clearTxnId(ActionEvent actionEvent) {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.SubinvTrxnId}", null);
    }

    public void genTxnId(ActionEvent actionEvent) {
        ValueExpression ve = null;
        String txnId;
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.SubinvTrxnId}", String.class);
        txnId =((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        
        if(txnId == null || txnId.equals("")){
            long time = System.nanoTime();
            int id = (int)time;
            id = id/1000;
            txnId = String.valueOf(id);
            Map pageFlow = (Map) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope}");
            pageFlow.put("SubinvTrxnId", txnId);
        }
        
    }
    public void filterSerials(Integer trxnId) {
        try {
            System.out.println("inside filter code");
            s_filteredSerialTrxns.clear();

            HashMap filterFileds = new HashMap();
            //Integer trxnId = (Integer) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.SubinvTrxnId}");
            String trxnType = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.ParentPage}");
            filterFileds.put("trxnid", trxnId);
            filterFileds.put("trxtype", trxnType);


            HashMap paramMap = new HashMap();
            paramMap.put("collection", s_serialTrxns);
            paramMap.put("filterFieldsValues", filterFileds);
            System.out.println("called super filtered class");
            SyncUtils sync = new SyncUtils();
            if(s_serialTrxns.size()>0)
            s_filteredSerialTrxns = (List) sync.getFileteredCollection(SerialBO.class, paramMap);
            System.out.println("collection size is " + s_filteredSerialTrxns.size());
        } catch (Exception e) {
            throw new RuntimeException("My Code Error " + e);
        }
    }

    public void filterLots(Integer trxnId) {
        try {
            System.out.println("inside filter code");
            s_filteredLotTrxns.clear();

            HashMap filterFileds = new HashMap();
            // Integer trxnId = (Integer) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.SubinvTrxnId}");
            String trxnType = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.ParentPage}");
            filterFileds.put("trxnid", trxnId);
            filterFileds.put("trxtype", trxnType);


            HashMap paramMap = new HashMap();
            paramMap.put("collection", s_lotTrxns);
            paramMap.put("filterFieldsValues", filterFileds);
            System.out.println("called super filtered class");
            SyncUtils sync = new SyncUtils();
            if(s_lotTrxns.size()>0)
            s_filteredLotTrxns = (List) sync.getFileteredCollection(LotBO.class, paramMap);
            System.out.println("collection size is " + s_filteredLotTrxns.size());
        } catch (Exception e) {
            throw new RuntimeException("My Code Error " + e);
        }
    }
}
