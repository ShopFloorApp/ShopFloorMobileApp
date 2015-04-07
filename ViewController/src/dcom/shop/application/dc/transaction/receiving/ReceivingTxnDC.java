package dcom.shop.application.dc.transaction.receiving;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.CarrierBO;
import dcom.shop.application.mobile.LPNBO;
import dcom.shop.application.mobile.LocatorBO;
import dcom.shop.application.mobile.LotBO;
import dcom.shop.application.mobile.SerialBO;
import dcom.shop.application.mobile.SubinventoryBO;
import dcom.shop.application.mobile.UOMBO;
import dcom.shop.application.mobile.transaction.SubInventoryTxnBO;
import dcom.shop.application.mobile.transaction.receiving.LinesBO;
import dcom.shop.application.mobile.transaction.receiving.PurchaseOrderBO;
import dcom.shop.application.mobile.transaction.receiving.RequisitionBO;
import dcom.shop.application.mobile.transaction.receiving.SalesOrderBO;
import dcom.shop.application.mobile.transaction.receiving.SalesOrderLineBO;
import dcom.shop.application.mobile.transaction.receiving.ShipmentBO;
import dcom.shop.application.mobile.transaction.receiving.ShipmentLinesBO;
import dcom.shop.application.mobile.txn.ConcProgramParamsBO;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.el.MethodExpression;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.Utility;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReceivingTxnDC extends SyncUtils{
    private static final String NOT_REACHABLE = "NotReachable";
    protected static List s_salesOrder = new ArrayList();
    protected static List s_salesOrderLines = new ArrayList();
    protected static List s_purchaseOrder = new ArrayList();
    protected static List s_requisition = new ArrayList();
    protected static List s_carriers = new ArrayList();
    public static List s_shipmentLines = new ArrayList();
    protected static List s_subInv = new ArrayList();
    protected static List s_locator = new ArrayList();
    public static List s_lines = new ArrayList();
    protected static List s_uom = new ArrayList();
    protected static List s_lpn = new ArrayList();
    protected static List s_quedReceiveTxn = new ArrayList();
    private String supplierCustomerName;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);

    public ReceivingTxnDC() {
        super();
    }

    public void setSupplierCustomerName(String supplierCustomerName) {
        String oldSupplierCustomerName = this.supplierCustomerName;
        this.supplierCustomerName = supplierCustomerName;
        propertyChangeSupport.firePropertyChange("supplierCustomerName", oldSupplierCustomerName, supplierCustomerName);
        
    }

    public String getSupplierCustomerName() {
        return supplierCustomerName;
    }

    public SalesOrderBO[] getSalesOrder() {
        s_salesOrder.clear();
        System.out.println("Inside orgItem");
        Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
        String pType = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.pTypeSalesOrd}")==null?"RMA":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.pTypeSalesOrd}"));
        String restURI = RestURI.PostGetSalesOrder();
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
            "                   {\"POU\": \"\",\n" +
            "                   \"PTYPE\": \""+pType+"\",\n" +
               "                   \"PORDER\": \"\",\n" +
            "                    \"PWAREHOUSE\": \"\"\n }\n" + "}\n" + "}\n";
        System.out.println("Calling create method");
        String jsonArrayAsString = rcu.invokeUPDATE(restURI, payload);
        System.out.println("Received response");
        /*
        SalesOrderBO salesOrderItems=new SalesOrderBO();
        
        salesOrderItems.setOrderType("Test");
        salesOrderItems.setOrderNumber("100002");
        
        SalesOrderLineBO lineItem=new SalesOrderLineBO();
        lineItem.setLINENUM("1");
        s_salesOrderLines.add(lineItem);
        lineItem=new SalesOrderLineBO();
        lineItem.setLINENUM("2");
        s_salesOrderLines.add(lineItem);
        lineItem=new SalesOrderLineBO();
        lineItem.setLINENUM("3");
        s_salesOrderLines.add(lineItem);
        
        SalesOrderLineBO[] orderLinesArray=
            (SalesOrderLineBO[])s_salesOrderLines.toArray(new SalesOrderLineBO[s_salesOrderLines.size()]);
        
        salesOrderItems.setLines(orderLinesArray);
        salesOrderItems.setOrderLines(new String[]{"11","22","33"});
        
        s_salesOrder.add(salesOrderItems);
        
        SalesOrderBO[] salesOrderArray =
            (SalesOrderBO[]) s_salesOrder.toArray(new SalesOrderBO[s_salesOrder.size()]);
        return salesOrderArray;
        */
        
        
        if (jsonArrayAsString != null) {
            try {
                JSONParser parser = new JSONParser();
                Object object;

                object = parser.parse(jsonArrayAsString);

                JSONObject jsonObject = (JSONObject) object;
                JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                JSONObject jsObject1 = (JSONObject) jsObject.get("XORDER");
                JSONArray array = (JSONArray) jsObject1.get("XORDER_ITEM");

                if (array != null) {
                    int size = array.size();
                    for (int i = 0; i < size; i++) {

                        SalesOrderBO salesOrderItems = new SalesOrderBO();
                        JSONObject jsObject2 = (JSONObject) array.get(i);

                        salesOrderItems.setOrderType((jsObject2.get("ORDERTYPE").toString()));
                        salesOrderItems.setOrderNumber((jsObject2.get("ORDERNUMBER").toString()));
                        
                        //LinesBO[] salesOrderLines
                        JSONArray lines= (JSONArray)jsObject2.get("LINES");
                        if (array != null) {
                            int lineSize = array.size();
                            for (int j = 0; j < lineSize; j++) {
                                SalesOrderLineBO lineItem=new SalesOrderLineBO();
                                JSONObject jsLine = (JSONObject)lines.get(j);
                                lineItem.setLINENUM(jsLine.get("LINENUM")+"");
                                lineItem.setITEM(jsLine.get("ITEM")+"");
                                lineItem.setLOTCONTROL(jsLine.get("LOTCONTROL")+"");
                                lineItem.setSERIALCONTROL(jsLine.get("SERIALCONTROL")+"");
                                lineItem.setLINEQTY(jsLine.get("LINEQTY")+"");
                                lineItem.setUOM(jsLine.get("UOM")+"");
                                
                                s_salesOrderLines.add(lineItem);
                            }
                            
                            SalesOrderLineBO[] orderLinesArray=
                                (SalesOrderLineBO[])s_salesOrderLines.toArray(new SalesOrderLineBO[s_salesOrderLines.size()]);
                        
                            salesOrderItems.setLines(orderLinesArray);
                        }

                        s_salesOrder.add(salesOrderItems);

                    }
                    SalesOrderBO[] salesOrderArray =
                        (SalesOrderBO[]) s_salesOrder.toArray(new SalesOrderBO[s_salesOrder.size()]);
                    return salesOrderArray;
                }
            } catch (ParseException e) {
                e.getMessage();
            } catch (ClassCastException c) {
                JSONParser parser = new JSONParser();
                try {
                    Object object = parser.parse(jsonArrayAsString);

                    JSONObject jsonObject = (JSONObject) object;
                    JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                    JSONObject jsObject1 = (JSONObject) jsObject.get("XORDER");
                    JSONObject jsObject2 = (JSONObject) jsObject1.get("XORDER_ITEM");

                    SalesOrderBO salesOrderItems = new SalesOrderBO();

                    salesOrderItems.setOrderType((jsObject2.get("ORDERTYPE").toString()));
                    salesOrderItems.setOrderNumber((jsObject2.get("ORDERNUMBER").toString()));
//                    salesOrderItems.setLines((jsObject2.get("LINES").toString()));

                    s_salesOrder.add(salesOrderItems);


                    SalesOrderBO[] salesOrderArray =
                        (SalesOrderBO[]) s_salesOrder.toArray(new SalesOrderBO[s_salesOrder.size()]);
                    return salesOrderArray;
                } catch (ParseException e) {
                    e.getMessage();
                }
            }
        } else {
            return null;
        }
        return null;
        
    }
    
    public SalesOrderLineBO[] getSalesOrderLines() {
        /*SalesOrderBO[] orderArray=getSalesOrder();
        
        for(int i=0;i<orderArray.length;i++){
            String order = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.documnetNumber}");
            if(orderArray[i].getOrderNumber().equals(order)){
                    return orderArray[i].getLines();
                }
            }
        return null;
*/
        SalesOrderLineBO[] orderLinesArray=
            (SalesOrderLineBO[])s_salesOrderLines.toArray(new SalesOrderLineBO[s_salesOrderLines.size()]);
        
        return orderLinesArray;
    }
    

    public RequisitionBO[] getRequisition() {
        s_requisition.clear();
        System.out.println("Inside orgItem");
        Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
        String restURI = RestURI.PostGetRequisition();
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
            "                   {\"POU\": \"\",\n" +
            "                   \"PREQ\": \"\",\n" +
            "                    \"PWAREHOUSE\": \"\"\n }\n" + "}\n" + "}\n";
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
                JSONObject jsObject1 = (JSONObject) jsObject.get("XREQ");
                JSONArray array = (JSONArray) jsObject1.get("XREQ_ITEM");

                if (array != null) {
                    int size = array.size();
                    for (int i = 0; i < size; i++) {

                        RequisitionBO requisitionItems = new RequisitionBO();
                        JSONObject jsObject2 = (JSONObject) array.get(i);

                        requisitionItems.setOuName((jsObject2.get("OUNAME").toString()));
                        requisitionItems.setReqNumber((jsObject2.get("REQNUMBER").toString()));
//                        requisitionItems.setLines((jsObject2.get("LINES").toString()));

                        s_requisition.add(requisitionItems);

                    }
                    RequisitionBO[] requisitionArray =
                        (RequisitionBO[]) s_requisition.toArray(new RequisitionBO[s_requisition.size()]);
                    return requisitionArray;
                }
            } catch (ParseException e) {
                e.getMessage();
            } catch (ClassCastException c) {
                JSONParser parser = new JSONParser();
                try {
                    Object object = parser.parse(jsonArrayAsString);

                    JSONObject jsonObject = (JSONObject) object;
                    JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                    JSONObject jsObject1 = (JSONObject) jsObject.get("XREQ");
                    JSONObject jsObject2 = (JSONObject) jsObject1.get("XREQ_ITEM");

                    RequisitionBO requisitionItems = new RequisitionBO();

                    requisitionItems.setOuName((jsObject2.get("OUNAME").toString()));
                    requisitionItems.setReqNumber((jsObject2.get("REQNUMBER").toString()));
//                    requisitionItems.setLines((jsObject2.get("LINES").toString()));

                    s_requisition.add(requisitionItems);


                    RequisitionBO[] requisitionArray =
                        (RequisitionBO[]) s_requisition.toArray(new RequisitionBO[s_requisition.size()]);
                    return requisitionArray;
                } catch (ParseException e) {
                    e.getMessage();
                }
            }
        } else {
            return null;
        }
        return null;
    }

    public PurchaseOrderBO[] getPurchaseOrder() {
        s_purchaseOrder.clear();
        System.out.println("Inside orgItem");
        Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
        String restURI = RestURI.PostGetPurchaseOrder();
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
            "                   {\"POU\": \"\",\n" +
            "                   \"PPO\": \"\",\n" +
            "                    \"PWAREHOUSE\": \"\"\n }\n" + "}\n" + "}\n";
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
                JSONObject jsObject1 = (JSONObject) jsObject.get("XPO");
                JSONArray array = (JSONArray) jsObject1.get("XPO_ITEM");

                if (array != null) {
                    int size = array.size();
                    for (int i = 0; i < size; i++) {

                        PurchaseOrderBO purchaseOrderItems = new PurchaseOrderBO();
                        JSONObject jsObject2 = (JSONObject) array.get(i);

                        purchaseOrderItems.setOuName((jsObject2.get("OUNAME").toString()));
                        purchaseOrderItems.setPoNumber((jsObject2.get("PONUMBER").toString()));
//                        purchaseOrderItems.setLines((jsObject2.get("LINES").toString()));

                        s_purchaseOrder.add(purchaseOrderItems);

                    }
                    PurchaseOrderBO[] purchaseOrderArray =
                        (PurchaseOrderBO[]) s_purchaseOrder.toArray(new PurchaseOrderBO[s_purchaseOrder.size()]);
                    return purchaseOrderArray;
                }
            } catch (ParseException e) {
                e.getMessage();
            } catch (ClassCastException c) {
                
                try {
                    JSONParser parser = new JSONParser();
                    Object object;

                    object = parser.parse(jsonArrayAsString);

                    JSONObject jsonObject = (JSONObject) object;
                    JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                    JSONObject jsObject1 = (JSONObject) jsObject.get("XPO");
                    JSONObject jsObject2 = (JSONObject) jsObject1.get("XPO_ITEM");

                    PurchaseOrderBO purchaseOrderItems = new PurchaseOrderBO();

                    purchaseOrderItems.setOuName((jsObject2.get("OUNAME").toString()));
                    purchaseOrderItems.setPoNumber((jsObject2.get("PONUMBER").toString()));
//                    purchaseOrderItems.setLines((jsObject2.get("LINES").toString()));

                    s_purchaseOrder.add(purchaseOrderItems);

                    PurchaseOrderBO[] purchaseOrderArray =
                        (PurchaseOrderBO[]) s_purchaseOrder.toArray(new PurchaseOrderBO[s_purchaseOrder.size()]);
                    return purchaseOrderArray;

                } catch (ParseException e) {
                    e.getMessage();
                }
            }
        } else {
            return null;
        }
        return null;
    }
    
    public CarrierBO[] getCarriers(){
        s_carriers.clear();
        s_carriers=super.getCollectionFromDB(CarrierBO.class);
        CarrierBO[] carriersArray =
            (CarrierBO[]) s_carriers.toArray(new CarrierBO[s_carriers.size()]);
        return carriersArray;
    }
    
    public SubinventoryBO[] getSubInventories(){
        s_subInv.clear();
        s_subInv=super.getCollectionFromDB(SubinventoryBO.class);
        SubinventoryBO[] subInventoriesArray =
            (SubinventoryBO[]) s_subInv.toArray(new SubinventoryBO[s_subInv.size()]);
        return subInventoriesArray;
    }
    
    public UOMBO[] getUoms(){
        s_uom.clear();
        s_uom=super.getCollectionFromDB(UOMBO.class);
        UOMBO[] uomsArray =
            (UOMBO[]) s_uom.toArray(new UOMBO[s_uom.size()]);
        return uomsArray;
    }
    
    public LPNBO[] getLpns(){
        s_lpn.clear();
        s_lpn=super.getCollectionFromDB(UOMBO.class);
        LPNBO[] lpnArray =
            (LPNBO[]) s_lpn.toArray(new LPNBO[s_lpn.size()]);
        return lpnArray;
    }
    
    public void refreshLocators() {
        providerChangeSupport.fireProviderRefresh("locators");
    }
    
    public void refreshLines() {
        providerChangeSupport.fireProviderRefresh("lines");
    }
    
    public LocatorBO[] getLocators(){
        s_locator.clear();
        String subInv = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.subInvAdd}");
        String whereClause="WHERE SUBINV=\""+subInv+"\"";
        s_locator=super.getFilteredCollectionFromDB(LocatorBO.class,whereClause);
        LocatorBO[] locatorsArray =
            (LocatorBO[]) s_locator.toArray(new LocatorBO[s_locator.size()]);
        return locatorsArray;
    }
    
    public LinesBO[] getLines(){
        LinesBO[] linesArray =
            (LinesBO[]) s_lines.toArray(new LinesBO[s_lines.size()]);
        return linesArray;
    }
    
    public void executeShipmentLines(){
        s_shipmentLines.clear();
        System.out.println("Inside orgItem");
        Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
        String documentNo = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.documnetNumber}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.documnetNumber}"));
        String receivingType = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.receivingType}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.receivingType}"));
        if(("I").equals(receivingType)){
            receivingType="IR";
        }else if(("S").equals(receivingType)){
            receivingType="PO";
        }else if(("C").equals(receivingType)){
            receivingType="RMA";
        }else{
            receivingType="";
        }
        String restURI = RestURI.PostGetShipment();
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
            "                   {\"PORGCODE\": \"100\",\n" +
            "                   \"PDOCTYPE\": \""+receivingType+"\",\n" +
            "                    \"PDOCREF\": \""+documentNo+"\"\n }\n" + "}\n" + "}\n";
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
                JSONObject jsObject1 = (JSONObject) jsObject.get("XSHIPMENT");
                if(receivingType.equals("PO")){
                    supplierCustomerName=(String)jsObject1.get("VENDOR");
                }else{
                    supplierCustomerName=(String)jsObject1.get("CUSTOMER");
                }
                AdfmfJavaUtilities.setELValue("#{pageFlowScope.suppCustName}", supplierCustomerName);
                
                
                JSONObject jsObject2 = (JSONObject) jsObject1.get("LINES");
                if(jsObject2==null){
                    return;
                }
                JSONArray array = (JSONArray) jsObject2.get("LINES_ITEM");
                
                if (array != null) {
                    int size = array.size();
                    for (int i = 0; i < size; i++) {

                        ShipmentLinesBO shipmentLinesItems = new ShipmentLinesBO();
                        JSONObject jsObject3 = (JSONObject) array.get(i);

                        shipmentLinesItems.setItem((jsObject3.get("ITEM").toString()));
                        shipmentLinesItems.setItemDecs((jsObject3.get("ITEMDESC").toString()));
                        shipmentLinesItems.setUom((jsObject3.get("UOM").toString()));
                        shipmentLinesItems.setLpn((jsObject3.get("LPN").toString()));
                        shipmentLinesItems.setSubInv((jsObject3.get("SUBINV").toString()));
                        shipmentLinesItems.setLocator((jsObject3.get("LOCATOR").toString()));
                        shipmentLinesItems.setQty((jsObject3.get("QTY").toString()));
                        shipmentLinesItems.setDocRefLine((jsObject3.get("DOCREFLINE").toString()));
                        shipmentLinesItems.setShipmentLine((jsObject3.get("SHIPMENTLINE").toString()));               
                        shipmentLinesItems.setLotControl((jsObject3.get("LOTCONTROL").toString()));
                        shipmentLinesItems.setSerialControl((jsObject3.get("SERIALCONTROL").toString()));

                        s_shipmentLines.add(shipmentLinesItems);

                    }
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.shipmentLinesCount}", s_shipmentLines.size());
                }
            } catch (ParseException e) {
                e.getMessage();
            } catch (ClassCastException c) {
              
                try {
                    JSONParser parser = new JSONParser();
                    Object object;

                    object = parser.parse(jsonArrayAsString);

                    JSONObject jsonObject = (JSONObject) object;
                    JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                    JSONObject jsObject1 = (JSONObject) jsObject.get("XSHIPMENT");
                    if(receivingType.equals("PO")){
                        supplierCustomerName=(String)jsObject1.get("VENDOR");
                    }else{
                        supplierCustomerName=(String)jsObject1.get("CUSTOMER");
                    }
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.suppCustName}", supplierCustomerName);
                    JSONObject jsObject2 = (JSONObject) jsObject1.get("LINES");
                    JSONObject jsObject3 = (JSONObject) jsObject2.get("LINES_ITEM");
             
                            ShipmentLinesBO shipmentLinesItems = new ShipmentLinesBO();
               
                            shipmentLinesItems.setItem((jsObject3.get("ITEM").toString()));
                            shipmentLinesItems.setItemDecs((jsObject3.get("ITEMDESC").toString()));
                            shipmentLinesItems.setUom((jsObject3.get("UOM").toString()));
                            shipmentLinesItems.setLpn((jsObject3.get("LPN").toString()));
                            shipmentLinesItems.setSubInv((jsObject3.get("SUBINV").toString()));
                            shipmentLinesItems.setLocator((jsObject3.get("LOCATOR").toString()));
                            shipmentLinesItems.setQty((jsObject3.get("QTY").toString()));
                            shipmentLinesItems.setDocRefLine((jsObject3.get("DOCREFLINE").toString()));
                            shipmentLinesItems.setShipmentLine((jsObject3.get("SHIPMENTLINE").toString()));        
                            shipmentLinesItems.setLotControl((jsObject3.get("LOTCONTROL").toString()));
                            shipmentLinesItems.setSerialControl((jsObject3.get("SERIALCONTROL").toString()));
                    
                            s_shipmentLines.add(shipmentLinesItems);
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.shipmentLinesCount}", s_shipmentLines.size());
                        
                        
                } catch (ParseException e) {
                    e.getMessage();
                }
            }
        } 
    }
    
    public ShipmentLinesBO[] getShipmentLines(){
        ShipmentLinesBO[] shipmentLinesArray =
            (ShipmentLinesBO[]) s_shipmentLines.toArray(new ShipmentLinesBO[s_shipmentLines.size()]);
        return shipmentLinesArray;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }

    public void addProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.addProviderChangeListener(l);
    }

    public void removeProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.removeProviderChangeListener(l);
    }
    public void getPendingReceiveTxn(){
        s_quedReceiveTxn=super.getOfflineCollection(ShipmentBO.class);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.ReceiveTxnId}",s_quedReceiveTxn.size());
    }
    public ShipmentBO[] getShipments(){
        ShipmentBO[] shipmentArray =
            (ShipmentBO[]) s_quedReceiveTxn.toArray(new ShipmentBO[s_quedReceiveTxn.size()]);
        return shipmentArray;
    }
    
    public void refreshShipments(){
        providerChangeSupport.fireProviderRefresh("shipments");
    }
    
    public void DeleteTransaction(Integer trxnId) {
     
        HashMap whereClause = new HashMap();
        whereClause.put("receiveTxnId", trxnId);
        // whereClause.put("trxtype", trxnType);

        boolean resultShipment = super.deleteSqlLiteTable(ShipmentBO.class, whereClause);
        boolean resultShipmentLines = super.deleteSqlLiteTable(ShipmentLinesBO.class, whereClause);
        
        whereClause.clear();
        whereClause.put("trxnid", trxnId);
        whereClause.put("trxtype", "ReceiveTxn");
        boolean resultLot = super.deleteSqlLiteTable(LotBO.class, whereClause);
        boolean resultSerial = super.deleteSqlLiteTable(SerialBO.class, whereClause);
        
        
        
        if (resultShipment) {
            refreshShipments();
        }
    }
    
    public void processReceive(String actionType){
        System.out.println("Inside orgItem");

        String documentNo = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.documnetNumber}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.documnetNumber}"));
        String receivingType = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.receivingType}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.receivingType}"));
        String carrier = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.carrier}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.carrier}"));
        String packSlip = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.packSlip}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.packSlip}"));
        String bol = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.bol}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.bol}"));
        String wayAirBill = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.wayAirBill}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.wayAirBill}"));
        String shipment = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.shipment}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.shipment}"));
        String shippedDate = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.shippedDate}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.shippedDate}"));
        String comments = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.comments}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.comments}"));        
        String customer = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.suppCustName}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.suppCustName}"));
        if(("I").equals(receivingType)){
            receivingType="IR";
        }else if(("S").equals(receivingType)){
            receivingType="PO";
        }else if(("C").equals(receivingType)){
            receivingType="RMA";
        }else{
            receivingType="";
        }
        ShipmentBO shipmentBO=new ShipmentBO();
        shipmentBO.setBol(bol);
        shipmentBO.setCarrier(carrier);
        shipmentBO.setComments(comments);
        shipmentBO.setCustomer(customer);
        shipmentBO.setDocRef(documentNo);
        shipmentBO.setDocType(receivingType);
        shipmentBO.setOrgCode("100");
        shipmentBO.setPackingSlip(packSlip);
        shipmentBO.setReceiveTxnId((Integer)AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.ReceiveTxnId}"));
        shipmentBO.setShipmentNum(shipment);
        shipmentBO.setShippedDate(shippedDate);
        shipmentBO.setVendor(customer);
        shipmentBO.setWayAirBill(wayAirBill);
        
        String networkStatus =
            (String) AdfmfJavaUtilities.evaluateELExpression("#{deviceScope.hardware.networkStatus}");
        ArrayList s_shipmentValues=new ArrayList();
        s_shipmentValues.add(shipmentBO);
        if(actionType.equalsIgnoreCase("SAVE")){
            super.insertSqlLiteTable(ShipmentBO.class, s_shipmentValues);
            super.insertSqlLiteTable(LinesBO.class, s_lines);
        }else{
            if (networkStatus.equals(NOT_REACHABLE)) {
                super.insertSqlLiteTable(ShipmentBO.class, s_shipmentValues);
                super.insertSqlLiteTable(LinesBO.class, s_lines);
            } else {
                submitReceiveRequest(s_shipmentValues,s_lines);
            }
            
        }
        
 
    }
    
    public void completeTxn(){
        Integer receiveTxnId = (Integer) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.receiveTxnIdProc}");
        ArrayList<ShipmentBO> shipmentBO =
            (ArrayList<ShipmentBO>) super.getFilteredCollectionFromDB(ShipmentBO.class,
                                                                      "WHERE RECEIVETXNID=" + receiveTxnId);
        ArrayList<LinesBO> LinesBO =
            (ArrayList<LinesBO>) super.getFilteredCollectionFromDB(LinesBO.class,
                                                                      "WHERE RECEIVETXNID=" + receiveTxnId);
        submitReceiveRequest(shipmentBO,LinesBO);
    }
    
    public void submitReceiveRequest(List<ShipmentBO> shipmentList,List<LinesBO> linesList){   
        Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
        String restURI = RestURI.PostReceiveTxn();
        RestCallerUtil rcu = new RestCallerUtil();
        String linesJson="";
        if(linesList.size() > 0) {
            linesJson = "{\"LINES_ITEM\":[";
            String lotJson=":{\"XXDCOM_LOT_TAB\":[";
            String seriesJson=":{\"XXDCOM_SERIAL_TAB\":[";
            for (int i = 0; i < linesList.size(); i++) {
                ArrayList<LotBO> s_lotLines = (ArrayList<LotBO>) super.getFilteredCollectionFromDB(LotBO.class, "WHERE TRXTYPE='ReceiveTxn' AND TRXNID="+linesList.get(i).getRowLineIdx());
                ArrayList<LotBO> s_serialLines = (ArrayList<LotBO>) super.getFilteredCollectionFromDB(SerialBO.class, "WHERE TRXTYPE='ReceiveTxn' AND TRXNID="+linesList.get(i).getRowLineIdx());
//                s_lotLines = (ArrayList<LotBO>) super.getCollectionFromDB(LotBO.class);
                
                if(s_lotLines.size()>0){
                    LotBO lot = new LotBO();
                    Iterator j = s_lotLines.iterator();
                    while (j.hasNext()) {
                        lot = (LotBO) j.next();
                        lotJson = lotJson+"{\"LOT\":\"" + lot.getLotNo() + "\",\"LOTQTY\": \"" + lot.getLotQty() + "\"},";

                    }
                    lotJson = lotJson.substring(0, lotJson.length() - 1);  
                    lotJson=lotJson+"]}";
                }else{
                    lotJson="\"\"";
                }
                
                if(s_serialLines.size()>0){
                    SerialBO serial = new SerialBO();
                    Iterator k = s_serialLines.iterator();
                    while (k.hasNext()) {
                        serial = (SerialBO) k.next();
                        seriesJson =seriesJson+"{\"FROMSERIAL\":\"" + serial.getFromSerial() + "\",\"TOSERIAL\": \"" + serial.getToSerial() +
                            "\",\"SERIALQTY\": \"" + serial.getSerialQty() + "\"},";

                    }
                    seriesJson = seriesJson.substring(0, seriesJson.length() - 1);
                    seriesJson=seriesJson+"]}";
                }else{
                    seriesJson="\"\"";
                }
                
                
                linesJson =linesJson+
                    "{\"ITEM\":\"" + linesList.get(i).getLines() + "\",\"UOM\":\"" + linesList.get(i).getUom() +
                    "\",\"LPN\":\"" + linesList.get(i).getLpn() + "\",\"SUBINV\":\"" + linesList.get(i).getSubInv() +
                    "\",\"LOCATOR\":\"" + linesList.get(i).getLocator() + "\",\"QTY\":\"" + linesList.get(i).getQuantity() +"\",\"LOTS\""+lotJson+",\"SERIALS\""+seriesJson+"},";
            }
            linesJson=linesJson.substring(0,linesJson.length()-1);
            linesJson=linesJson+"]}";
        } else {
            linesJson="\"\"";
        }
        
        
        String payload =
            "{\n" + "\"GET_SO_PER_ORG_Input\":\n" + "{\n" +
            "\"@xmlns\": \"http://xmlns.oracle.com/apps/fnd/rest/GetSoPerOrgSvc/get_so_per_org/\",\n" +
            "   \"RESTHeader\": {\"@xmlns\": \"http://xmlns.oracle.com/apps/fnd/rest/GetSoPerOrgSvc/header\",\n" +
            "                  \"Responsibility\": \"DCOM_MOBILE_USER\",\n" +
            "                  \"RespApplication\": \"INV\",\n" +
            "                  \"SecurityGroup\": \"STANDARD\",\n" +
            "                  \"NLSLanguage\": \"AMERICAN\",\n" + "                  \"Org_Id\": \"82\"\n" +
            "                 },\n" + "   \"InputParameters\": \n" + 
            "                   {\"PORGCODE\": \"100\",\n" +
            "                   \"PDOCTYPE\": \""+shipmentList.get(0).getDocType()+"\",\n" +
            "                   \"PDOCREF\": \""+shipmentList.get(0).getDocRef()+"\",\n" +
            "                   \"CARRIER\": \""+shipmentList.get(0).getCarrier()+"\",\n" +
            "                   \"PACKINGSLIP\": \""+shipmentList.get(0).getPackingSlip()+"\",\n" +
            "                   \"BOL\": \""+shipmentList.get(0).getBol()+"\",\n" +
            "                   \"WAYAIRBILL\": \""+shipmentList.get(0).getWayAirBill()+"\",\n" +
            "                   \"SHIPMENTNUM\": \""+shipmentList.get(0).getShipmentNum()+"\",\n" +
            "                   \"SHIPPEDDATE\": \""+shipmentList.get(0).getShippedDate()+"\",\n" +
            "                   \"COMMENTS\": \""+shipmentList.get(0).getComments()+"\",\n" +
            "                    \"LINES\": "+linesJson+"\n }\n" + "}}" ;
        System.out.println("Calling create method");
        String jsonArrayAsString = rcu.invokeUPDATE(restURI, payload);
        System.out.println("Received response");
        DeleteTransaction(shipmentList.get(0).getReceiveTxnId());
            try {
        JSONParser parser = new JSONParser();
        Object object;

        
            object = parser.parse(jsonArrayAsString);
        

        JSONObject jsonObject = (JSONObject) object;
        JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
        String msg = (String) jsObject.get("XMSG");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.successMsg}", msg);
        } catch (ParseException e) {
            e.getMessage();
        }
    }

}

