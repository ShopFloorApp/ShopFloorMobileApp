package dcom.shop.Transaction.page.bean;

import dcom.shop.application.dc.transaction.receiving.ReceivingTxnDC;

import dcom.shop.application.mobile.transaction.receiving.LinesBO;

import dcom.shop.application.mobile.transaction.receiving.PurchaseOrderBO;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.util.ArrayList;

import javax.el.MethodExpression;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.util.Utility;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReceivingTxnUtilBean {
    ReceivingTxnDC receiveDc=new ReceivingTxnDC();
    public ReceivingTxnUtilBean() {
        super();
    }
    public void getLinesFromDocumentNo(ActionEvent ae){
        receiveDc.executeShipmentLines();
    }
    
    public void subInvChnage(ValueChangeEvent valueChangeEvent) {
        MethodExpression me =
            AdfmfJavaUtilities.getMethodExpression("#{bindings.refreshLocators.execute}", Object.class, new Class[] {
                                                   });
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
    }
    public void addRecord(ActionEvent ae){        
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.lineAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.subInvAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.locatorAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.quantityAdd}", null);
    }
    public void updateRecord(ActionEvent ae){
        String line = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.lineAdd}");
        String subInv = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.subInvAdd}");
        String locator = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.locatorAdd}");
        String quantity = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.quantityAdd}");
        String uom = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.uomAdd}");
        String lpn = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.lpnAdd}");
        ReceivingTxnDC.s_lines.add(new LinesBO(line,subInv,locator,quantity,uom,lpn,"Y"));
    }
    public void removeRecords(ActionEvent ae){
//        ArrayList coll=new ArrayList();
//        for(int i=0;i<receiveDc.s_lines.size();i++){
//            LinesBO lines = (LinesBO) receiveDc.s_lines.get(i);
//            if(!lines.getIsNewEntity().equalsIgnoreCase("Y")){
//                coll.add(lines);
//            }
//        }
//        receiveDc.s_lines=coll;
        receiveDc.s_lines.clear();
    }
    
    public void processReceive(ActionEvent ae){
        System.out.println("Inside orgItem");
        Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
        String restURI = RestURI.PostGetPurchaseOrder();
        RestCallerUtil rcu = new RestCallerUtil();
        String documentNo = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.documnetNumber}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.documnetNumber}"));
        String receivingType = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.receivingType}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.receivingType}"));
        String carrier = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.carrier}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.carrier}"));
        String packSlip = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.packSlip}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.packSlip}"));
        String bol = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.bol}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.bol}"));
        String wayAirBill = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.wayAirBill}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.wayAirBill}"));
        String shipment = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.shipment}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.shipment}"));
        String shippedDate = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.shippedDate}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.shippedDate}"));
        String comments = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.comments}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.comments}"));        
        
        if(("I").equals(receivingType)){
            receivingType="IR";
        }else if(("S").equals(receivingType)){
            receivingType="PO";
        }else if(("C").equals(receivingType)){
            receivingType="RMA";
        }else{
            receivingType="";
        }
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
    }
//        if (jsonArrayAsString != null) {
//            try {
//                JSONParser parser = new JSONParser();
//                Object object;
//
//                object = parser.parse(jsonArrayAsString);
//
//                JSONObject jsonObject = (JSONObject) object;
//                JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
//                JSONObject jsObject1 = (JSONObject) jsObject.get("XPO");
//                JSONArray array = (JSONArray) jsObject1.get("XPO_ITEM");
//
//                if (array != null) {
//                    int size = array.size();
//                    for (int i = 0; i < size; i++) {
//
//                        PurchaseOrderBO purchaseOrderItems = new PurchaseOrderBO();
//                        JSONObject jsObject2 = (JSONObject) array.get(i);
//
//                        purchaseOrderItems.setOuName((jsObject2.get("OUNAME").toString()));
//                        purchaseOrderItems.setPoNumber((jsObject2.get("PONUMBER").toString()));
//        //                        purchaseOrderItems.setLines((jsObject2.get("LINES").toString()));
//
//                        s_purchaseOrder.add(purchaseOrderItems);
//
//                    }
//                    PurchaseOrderBO[] purchaseOrderArray =
//                        (PurchaseOrderBO[]) s_purchaseOrder.toArray(new PurchaseOrderBO[s_purchaseOrder.size()]);
//                    return purchaseOrderArray;
//                }
//            } catch (ParseException e) {
//                e.getMessage();
//            } catch (ClassCastException c) {
//                
//                try {
//                    JSONParser parser = new JSONParser();
//                    Object object;
//
//                    object = parser.parse(jsonArrayAsString);
//
//                    JSONObject jsonObject = (JSONObject) object;
//                    JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
//                    JSONObject jsObject1 = (JSONObject) jsObject.get("XPO");
//                    JSONObject jsObject2 = (JSONObject) jsObject1.get("XPO_ITEM");
//
//                    PurchaseOrderBO purchaseOrderItems = new PurchaseOrderBO();
//
//                    purchaseOrderItems.setOuName((jsObject2.get("OUNAME").toString()));
//                    purchaseOrderItems.setPoNumber((jsObject2.get("PONUMBER").toString()));
//        //                    purchaseOrderItems.setLines((jsObject2.get("LINES").toString()));
//
//                    s_purchaseOrder.add(purchaseOrderItems);
//
//                    PurchaseOrderBO[] purchaseOrderArray =
//                        (PurchaseOrderBO[]) s_purchaseOrder.toArray(new PurchaseOrderBO[s_purchaseOrder.size()]);
//                    return purchaseOrderArray;
//
//                } catch (ParseException e) {
//                    e.getMessage();
//                }

}
