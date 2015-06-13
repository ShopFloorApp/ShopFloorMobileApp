package dcom.shop.Transaction.page.bean;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.DeliveryBO;
import dcom.shop.application.mobile.LotBO;
import dcom.shop.application.mobile.SerialBO;
import dcom.shop.application.mobile.TaskBO;
import dcom.shop.application.mobile.transaction.LpnTxnBO;
import dcom.shop.application.mobile.transaction.PackTxnBO;
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

public class PackAndDirectShipBean {
    public PackAndDirectShipBean() {
    }
    protected static List s_serialTrxns = new ArrayList();
    protected static List s_lotTrxns = new ArrayList();
    protected static List s_filteredSerialTrxns = new ArrayList();
    protected static List s_filteredLotTrxns = new ArrayList();
    
    public void callDirectShipService(ActionEvent actionEvent){       
        ValueExpression ve = null;
        String response = null;
        System.out.println("Inside callPackService");
        String DELIVERYID=null;
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.DELIVERYID}", String.class);
        DELIVERYID = (ve.getValue(AdfmfJavaUtilities.getAdfELContext())+"").trim();
        if("{\"@xsi:nil\":\"true\"}".equals(DELIVERYID)){
            DELIVERYID = "";
        }   
                
        String SHIPMETHOD=null;
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.SHIPMETHOD}", String.class);
        SHIPMETHOD = (ve.getValue(AdfmfJavaUtilities.getAdfELContext())+"").trim();
        if("{\"@xsi:nil\":\"true\"}".equals(SHIPMETHOD)){
            SHIPMETHOD = "";
        }   
        
        String NETWT=null;
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.NETWT}", String.class);
        NETWT = (ve.getValue(AdfmfJavaUtilities.getAdfELContext())+"").trim();

        if("{\"@xsi:nil\":\"true\"}".equals(NETWT)){
            NETWT = "";
        }       
        
        String GROSSWT=null;
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.GROSSWT}", String.class);
        GROSSWT = ( ve.getValue(AdfmfJavaUtilities.getAdfELContext())+"").trim();
        if("{\"@xsi:nil\":\"true\"}".equals(GROSSWT)){
            GROSSWT = "";
        }       
        
        String WAYBILL=null;
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.WAYBILL}", String.class);
        WAYBILL = ( ve.getValue(AdfmfJavaUtilities.getAdfELContext())+"").trim();
        if("{\"@xsi:nil\":\"true\"}".equals(WAYBILL)){
            WAYBILL = "";
        } 
        String restURI = RestURI.PostDirectShipURI();
        RestCallerUtil rcu = new RestCallerUtil();
        String payload = null;
        
        payload = "{\"x\":\n" + 
        "{\n" + 
        "   \"RESTHeader\": {\"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" + 
        "                  \"RespApplication\": \"ONT\",\n" + 
        "                  \"SecurityGroup\": \"STANDARD\",\n" + 
        "                  \"NLSLanguage\": \"AMERICAN\",\n" + 
        "                  \"Org_Id\": \"82\"\n" + 
        "                 },\n" + 
        "   \"InputParameters\": \n" + 
        "      {\"PPACK\": {\"DOCKDOOR\": \""+DELIVERYID
                  +"\",\"LPN\": \""+SHIPMETHOD
                  +"\",\"SUBINV\": \""+NETWT
                  +"\",\"LOCATOR\": \""+GROSSWT
                  +"\",\"ITEM\": \""+WAYBILL
                  +"\"}}\n" + 
        "}\n" + 
        "}";
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
                
                String status =jsObject.get("XSTATUS").toString();
                String msg = jsObject.get("XMSG").toString();
                Map pageFlow = (Map) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope}");
                pageFlow.put("txnMsg", msg);
              
            }
        
        }catch(Exception e){
                Trace.log("REST_JSON",Level.SEVERE, this.getClass(),"ProductDetailsEntity", e.getLocalizedMessage());
            }
        
        
    }
    
    public void callPackService(ActionEvent actionEvent) {
        ValueExpression ve = null;
        String response = null;
        System.out.println("Inside callPackService");
        String dockDoor=null;
        List s_packList = new ArrayList();
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.dockDoor}", String.class);
        dockDoor = (ve.getValue(AdfmfJavaUtilities.getAdfELContext())+"").trim();
        if("{\"@xsi:nil\":\"true\"}".equals(dockDoor)){
            dockDoor = "";
        }   
        
        String lpn=null;
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchLpnKeyword}", String.class);
        lpn = (ve.getValue(AdfmfJavaUtilities.getAdfELContext())+"").trim();
        if("{\"@xsi:nil\":\"true\"}".equals(lpn)){
            lpn = "";
        }   
        
        String subInventory=null;
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.subInv}", String.class);
        subInventory = (ve.getValue(AdfmfJavaUtilities.getAdfELContext())+"").trim();

        if("{\"@xsi:nil\":\"true\"}".equals(subInventory)){
            subInventory = "";
        }       
        
        String locator=null;
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.locator}", String.class);
        locator = (ve.getValue(AdfmfJavaUtilities.getAdfELContext())+"").trim();
        if("{\"@xsi:nil\":\"true\"}".equals(locator)){
            locator = "";
        }       
        
        String item=null;
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchKeyword}", String.class);
        item = (ve.getValue(AdfmfJavaUtilities.getAdfELContext())+"").trim();
        if("{\"@xsi:nil\":\"true\"}".equals(item)){
            item = "";
        } 
        
        String quantity=null;
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.quantity}", String.class);
        quantity = (ve.getValue(AdfmfJavaUtilities.getAdfELContext())+"").trim();
        if("{\"@xsi:nil\":\"true\"}".equals(quantity)){
            quantity = "";
        } 
        
        String lotNumber=null;
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.lotNo}", String.class);
        lotNumber = (ve.getValue(AdfmfJavaUtilities.getAdfELContext())+"").trim();
        if("{\"@xsi:nil\":\"true\"}".equals(lotNumber)){
            lotNumber = "";
        } 
        
        String serialNumber=null;
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.serialNo}", String.class);
        serialNumber = (ve.getValue(AdfmfJavaUtilities.getAdfELContext())+"").trim();
        if("{\"@xsi:nil\":\"true\"}".equals(serialNumber)){
            serialNumber = "";
        } 
        
        String orderNumber=null;
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.documnetNumber}", String.class);
        orderNumber = (ve.getValue(AdfmfJavaUtilities.getAdfELContext())+"").trim();
        if("{\"@xsi:nil\":\"true\"}".equals(orderNumber)){
            orderNumber = "";
        } 
        
        String orderLineNumber=null;
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.orderLineNumber}", String.class);
        orderLineNumber = (ve.getValue(AdfmfJavaUtilities.getAdfELContext())+"").trim();
        if("{\"@xsi:nil\":\"true\"}".equals(orderLineNumber)){
            orderLineNumber = "";
        } 
        PackTxnBO packTxn = new PackTxnBO();
        Integer trxnId = (Integer) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.SubinvTrxnId}");
        packTxn.setTrxnId(trxnId);
        packTxn.setDockDoor(dockDoor);
        packTxn.setItem(item);
        packTxn.setLPN(lpn);
        packTxn.setLineNum(orderLineNumber);
        packTxn.setLocator(locator);
        packTxn.setOrderNum(orderNumber);
        packTxn.setQty(quantity);
        packTxn.setSubInv(subInventory);
        SyncUtils sync = new SyncUtils();
        s_serialTrxns = sync.getCollectionFromDB(SerialBO.class);
        filterSerials(trxnId);
        if (s_filteredSerialTrxns.size() > 0)
            packTxn.setSerialControl("0");
        else
            packTxn.setSerialControl("1");
        s_lotTrxns = sync.getCollectionFromDB(LotBO.class);
        filterLots(trxnId);
        if (s_filteredLotTrxns.size() > 0)
            packTxn.setLotControl("0");
        else 
           packTxn.setLotControl("1");
        
        sync.insertSqlLiteTable(PackTxnBO.class, s_packList);
        
        String orgCode =
            (String) AdfmfJavaUtilities.evaluateELExpression("#{preferenceScope.feature.dcom.shop.MyWarehouse.OrgCodePG.OrgCode}");

        String restURI = RestURI.PostPackURI();
        RestCallerUtil rcu = new RestCallerUtil();
        String payload = null;
        
        payload = "{\"x\":\n" + 
        "{\n" + 
        "   \"RESTHeader\": {\"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" + 
        "                  \"RespApplication\": \"ONT\",\n" + 
        "                  \"SecurityGroup\": \"STANDARD\",\n" + 
        "                  \"NLSLanguage\": \"AMERICAN\",\n" + 
        "                  \"Org_Id\": \"82\"\n" + 
        "                 },\n" + 
        "   \"InputParameters\": \n" + 
        "      { \"PORGCODE\":  \""+orgCode+"\""+
            ",\"PPACK\": {\"DOCKDOOR\": \""+dockDoor
                  +"\",\"LPN\": \""+lpn
                  +"\",\"SUBINV\": \""+subInventory
                  +"\",\"LOCATOR\": \""+locator
                  +"\",\"ITEM\": \""+item
                  +"\",\"QTY\": \""+quantity
                  +"\",\"ORDERNUM\": \""+orderNumber
                  +"\",\"LINENUM\": \""+orderLineNumber
                  +"\"\n" 
       ;
        
        LotBO lot = new LotBO();
        Iterator j = s_filteredLotTrxns.iterator();
        //  if(s_filteredLotTrxns.size()>0){
        payload = payload + ", \"LOTS\": { \"XXDCOM_LOT_TAB\": [  ";
        // }
        while (j.hasNext()) {
            lot = (LotBO) j.next();
            payload = payload + "{\"LOT\":\"" + lot.getLotNo() + "\",\"LOTQTY\": \"" + lot.getLotQty() + "\"},";

        }
        payload = payload.substring(0, payload.length() - 1);

        SerialBO serial = new SerialBO();
        if (s_filteredSerialTrxns.size() > 0) {
            payload = payload + "]},\"SERIALS\": { \"XXDCOM_SERIAL_TAB\": [";
        }
        Iterator i = s_filteredSerialTrxns.iterator();
        while (i.hasNext()) {
            serial = (SerialBO) i.next();
            payload =
                payload + "{\"FROMSERIAL\":\"" + serial.getFromSerial() + "\",\"TOSERIAL\": \"" + serial.getToSerial() +
                "\",\"SERIALQTY\": \"" + serial.getSerialQty() + "\"},";

        }
        payload = payload.substring(0, payload.length() - 1);
        payload = payload + "]}\n" + "}}}}";
        
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
                
                String status =jsObject.get("XSTATUS").toString();
                String msg = jsObject.get("XMSG").toString();
                JSONObject jsObject1 = (JSONObject) jsObject.get("XOUT");
                
                DeliveryBO deliveryBO = new DeliveryBO();
                deliveryBO.setDELIVERYID(jsObject1.get("DELIVERYID")==null?"":jsObject1.get("DELIVERYID").toString());
                deliveryBO.setSHIPMETHOD(jsObject1.get("SHIPMETHOD")==null?"":jsObject1.get("SHIPMETHOD").toString());
                deliveryBO.setNETWT(jsObject1.get("NETWT")==null?"":jsObject1.get("NETWT").toString());
                deliveryBO.setGROSSWT(jsObject1.get("GROSSWT")==null?"":jsObject1.get("GROSSWT").toString());
                deliveryBO.setWAYBILL(jsObject1.get("WAYBILL")==null?"":jsObject1.get("WAYBILL").toString());
                
                AdfmfJavaUtilities.setELValue("#{pageFlowScope.DELIVERYID}", deliveryBO.getDELIVERYID());
                AdfmfJavaUtilities.setELValue("#{pageFlowScope.SHIPMETHOD}", deliveryBO.getSHIPMETHOD());
                AdfmfJavaUtilities.setELValue("#{pageFlowScope.NETWT}", deliveryBO.getNETWT());
                AdfmfJavaUtilities.setELValue("#{pageFlowScope.GROSSWT}", deliveryBO.getGROSSWT());
                AdfmfJavaUtilities.setELValue("#{pageFlowScope.DELIVERYID}", deliveryBO.getWAYBILL());
            }
        
        }catch(Exception e){
                Trace.log("REST_JSON",Level.SEVERE, this.getClass(),"ProductDetailsEntity", e.getLocalizedMessage());
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
