package dcom.shop.application.dc.transaction.receiving;

import dcom.shop.application.mobile.transaction.receiving.PurchaseOrderBO;
import dcom.shop.application.mobile.transaction.receiving.RequisitionBO;
import dcom.shop.application.mobile.transaction.receiving.SalesOrderBO;
import dcom.shop.application.mobile.txn.ConcProgramParamsBO;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.util.Utility;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReceivingTxnDC {
    protected static List s_salesOrder = new ArrayList();
    protected static List s_purchaseOrder = new ArrayList();
    protected static List s_requisition = new ArrayList();

    public ReceivingTxnDC() {
        super();
    }

    public SalesOrderBO[] getSalesOrder() {
        s_salesOrder.clear();
        System.out.println("Inside orgItem");
        Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
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
            "                 },\n" + "   \"InputParameters\": \n" + "                   {\"POU\": \"\",\n" +
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
                        salesOrderItems.setLines((jsObject2.get("LINES").toString()));

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
                    salesOrderItems.setLines((jsObject2.get("LINES").toString()));

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
            "                 },\n" + "   \"InputParameters\": \n" + "                   {\"POU\": \"\",\n" +
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
                        requisitionItems.setLines((jsObject2.get("LINES").toString()));

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
                    requisitionItems.setLines((jsObject2.get("LINES").toString()));

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
            "                 },\n" + "   \"InputParameters\": \n" + "                   {\"POU\": \"\",\n" +
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
                        purchaseOrderItems.setLines((jsObject2.get("LINES").toString()));

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
                    purchaseOrderItems.setLines((jsObject2.get("LINES").toString()));

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

}

