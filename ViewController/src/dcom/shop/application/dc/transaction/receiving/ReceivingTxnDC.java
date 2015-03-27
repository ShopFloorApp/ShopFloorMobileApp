package dcom.shop.application.dc.transaction.receiving;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.CarrierBO;
import dcom.shop.application.mobile.LPNBO;
import dcom.shop.application.mobile.LocatorBO;
import dcom.shop.application.mobile.SubinventoryBO;
import dcom.shop.application.mobile.UOMBO;
import dcom.shop.application.mobile.transaction.receiving.LinesBO;
import dcom.shop.application.mobile.transaction.receiving.PurchaseOrderBO;
import dcom.shop.application.mobile.transaction.receiving.RequisitionBO;
import dcom.shop.application.mobile.transaction.receiving.SalesOrderBO;
import dcom.shop.application.mobile.transaction.receiving.ShipmentBO;
import dcom.shop.application.mobile.transaction.receiving.ShipmentLinesBO;
import dcom.shop.application.mobile.txn.ConcProgramParamsBO;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.util.ArrayList;
import java.util.List;

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
    protected static List s_salesOrder = new ArrayList();
    protected static List s_purchaseOrder = new ArrayList();
    protected static List s_requisition = new ArrayList();
    protected static List s_carriers = new ArrayList();
    protected static List s_shipmentLines = new ArrayList();
    protected static List s_subInv = new ArrayList();
    protected static List s_locator = new ArrayList();
    public static List s_lines = new ArrayList();
    protected static List s_uom = new ArrayList();
    protected static List s_lpn = new ArrayList();
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
//                        salesOrderItems.setLines((jsObject2.get("LINES").toString()));

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

                        s_shipmentLines.add(shipmentLinesItems);

                    }
                    
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

                            s_shipmentLines.add(shipmentLinesItems);

                        
                        
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
}

