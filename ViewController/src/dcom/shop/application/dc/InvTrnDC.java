package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.LocatorBO;
import dcom.shop.application.mobile.SerialBO;
import dcom.shop.application.mobile.transaction.SubInventoryTxnBO;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.exception.AdfException;

public class InvTrnDC extends RestCallerUtil {
    public InvTrnDC() {
        super();
    }
    private static final String NOT_REACHABLE = "NotReachable"; // Indiates no network connectivity
    protected static List s_invTrxns = new ArrayList();
    protected static List s_serialTrxns = new ArrayList();
    protected static List s_filteredSerialTrxns = new ArrayList();
    public void InsertTransaction() {
        String networkStatus =
            (String) AdfmfJavaUtilities.evaluateELExpression("#{deviceScope.hardware.networkStatus}");
        SubInventoryTxnBO subInvTxn = new SubInventoryTxnBO();
        
        if (networkStatus.equals(NOT_REACHABLE)) {
           subInvTxn.setCompleteFlag("N"); 
        }else{
            subInvTxn.setCompleteFlag("Y");
        }
        subInvTxn.setIsNewEntity("Y");
        Integer trxnId = (Integer)AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.SubinvTrxnId}");
        String lpn = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.lpnText}");
        String item = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.itemNumber}");
        String itemName = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.description}");
        String qty = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.quantity}");
        String fromSubInv = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.FromSubinventory}");
        String fromSubInv1 = (String) AdfmfJavaUtilities.evaluateELExpression("#{bindings.FromSubInv.inputValue}");
        String fromLocator = (String) AdfmfJavaUtilities.evaluateELExpression("#{bindings.FromLocator.inputValue}");
        String toSubInv = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.ToSubinventory}");
        String toLocator = (String) AdfmfJavaUtilities.evaluateELExpression("#{bindings.ToLocator.inputValue}");
        subInvTxn.setTrxnId(trxnId);
        subInvTxn.setLPN(lpn);
        subInvTxn.setItemNumber(item);
        subInvTxn.setItemName(itemName);
        subInvTxn.setQuantity(qty);
        subInvTxn.setFromInventory(fromSubInv);
        subInvTxn.setFromLoc(fromLocator);
        subInvTxn.setToLoc(toLocator);
        subInvTxn.setToInventory(toSubInv);
        subInvTxn.setItemNumber(item);
        s_invTrxns.add(subInvTxn);
        SyncUtils syncUtils = new SyncUtils();
        syncUtils.insertSqlLiteTable(SubInventoryTxnBO.class, s_invTrxns);
    }
        

    public void ProcessWS(Integer trxnId) {
        String restURI = RestURI.PostInvTrxnURI();
        s_invTrxns.clear();
        SyncUtils sync = new SyncUtils();
        SubInventoryTxnBO invTxn = null;
        s_invTrxns =  sync.getCollectionFromDB(SubInventoryTxnBO.class);
        SubInventoryTxnBO[] invTxnArray = (SubInventoryTxnBO[]) s_invTrxns.toArray(new SubInventoryTxnBO[s_invTrxns.size()]);
        for(int i=0; i < s_invTrxns.size();i++ ){
             invTxn = invTxnArray[0];
        }
        s_serialTrxns = sync.getCollectionFromDB(SerialBO.class);
        filterSerials();
        String lpn =  invTxn.getLPN();//(String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.lpnText}");
        String item = invTxn.getItemNumber();//(String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.itemNumber}");
        String uom = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.uom}");
        String qty = invTxn.getQuantity();//(String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.quantity}");
        String fromSubInv = invTxn.getFromInventory();//(String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.FromSubinventory}");
        String fromLocator = invTxn.getFromLoc();// (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.FromLocator}");
        String toSubInv = invTxn.getToInventory();//(String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.ToSubinventory}");
        String toLocator = invTxn.getToLoc();//(String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.ToLocator}");
        String destOrg = "100";
        String sourceOrg = "100";
        String payload =
            "{\n" + "\"x\":\n" + "{\n" + "   \"RESTHeader\": {\"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" +
            "                  \"RespApplication\": \"ONT\",\n" +
            "                  \"SecurityGroup\": \"STANDARD\",\n" +
            "                  \"NLSLanguage\": \"AMERICAN\",\n" + "                  \"Org_Id\": \"82\"\n" +
            "                 },\n" + "   \"InputParameters\": \n" + "      {\"PINVTXN\": { \"LPN\": \"" + lpn +
            "\",\"ITEM\": \"" + item + "\", \"SOURCSUBINV\": \"" + fromSubInv + "\", \"SOURCELOCATOR\": \"" +
            fromLocator + "\",\"TXNUOM\": \"" + uom + "\", \"TRXQTY\": \"" + qty + "\", \"DESTORG\": \"" + destOrg +
            "\", \"SOURCEORGCODE\": \"" + sourceOrg + "\", \"DESTSUBINV\": \"" + toSubInv + "\", \"DESTLOCATOR\": \"" +
            toLocator + "\"" + ", \"TRXTYPE\": \"SUBINV\" }\n" + "}}}";
        System.out.println("Calling create method");
        SerialBO serial = new SerialBO();
        Iterator i = s_filteredSerialTrxns.iterator();
        while(i.hasNext()){
            serial = (SerialBO)i.next();
            
        }
        
        String jsonArrayAsString = super.invokeUPDATE(restURI, payload);
        System.out.println("Received response");
        throw new AdfException("Transaction completed", AdfException.INFO);

    }
    
    public void filterSerials() {
        try {
            System.out.println("inside filter code");
            s_filteredSerialTrxns.clear();

            HashMap filterFileds = new HashMap();
            Integer trxnId = (Integer) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.SubinvTrxnId}");
            filterFileds.put("trxnid", trxnId);
            //   filterFileds.put("alias", getAliasFilter());


            HashMap paramMap = new HashMap();
            paramMap.put("collection", s_serialTrxns);
            paramMap.put("filterFieldsValues", filterFileds);
            System.out.println("called super filtered class");
             SyncUtils sync = new SyncUtils();
             
            s_filteredSerialTrxns = (List) sync.getFileteredCollection(LocatorBO.class, paramMap);
            System.out.println("collection size is " + s_filteredSerialTrxns.size());
         } catch (Exception e) {
            throw new RuntimeException("My Code Error " + e);
        }
    }


    public void ProcessMiscTrxnWS(String trxType) {
        String restURI = RestURI.PostInvTrxnURI();
        String item = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.itemNumber}");
        String uom = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.uom}");
        String qty = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.quantity}");
        String fromSubInv = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.FromSubinventory}");
        String fromLocator = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.FromLocator}");
        String toSubInv = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.ToSubinventory}");
        String toLocator = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.ToLocator}");
        String trxnUom = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.uom}");
        String lpn = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.lpn}");
        String reason = "";
        String glAccount = "";
        String trxRef = "";
        String action = "";
        String destOrg = "100";
        String sourceOrg = "100";
        String payload =
            "{\n" + "\"x\":\n" + "{\n" + "   \"RESTHeader\": {\"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" +
            "                  \"RespApplication\": \"ONT\",\n" +
            "                  \"SecurityGroup\": \"STANDARD\",\n" +
            "                  \"NLSLanguage\": \"AMERICAN\",\n" + "                  \"Org_Id\": \"82\"\n" +
            "                 },\n" + "   \"InputParameters\": \n" + "      {\"PINVTXN\": { \"ITEM\": \"" + item + "\", \"SOURCSUBINV\": \"" + fromSubInv + "\", \"SOURCELOCATOR\": \"" +
            fromLocator + "\",\"TXNUOM\": \"" + uom + "\", \"TRXQTY\": \"" + qty + "\", \"DESTORG\": \"" + destOrg +
            "\", \"SOURCEORGCODE\": \"" + sourceOrg + "\", \"DESTSUBINV\": \"" + toSubInv + "\", \"DESTLOCATOR\": \"" +
            toLocator + "\"" + "\", \"TXNUOM\": \"" + trxnUom + ", \"TRXTYPE\": \"" + trxType + ", \"REASON\": \"" +
            reason + ", \"GLACCOUNT\": \"" + glAccount + ", \"TRXREF\": \"" + trxRef + ", \"ACTION\": \"" + action +
            "\" }\n" + "}}}";
        System.out.println("Calling create method");
        String jsonArrayAsString = super.invokeUPDATE(restURI, payload);
        System.out.println("Received response");
        throw new AdfException("Transaction completed", AdfException.INFO);

    }
}
