package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.transaction.SubInventoryTxnBO;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.exception.AdfException;

public class InvTrnDC extends RestCallerUtil {
    public InvTrnDC() {
        super();
    }
    private static final String NOT_REACHABLE = "NotReachable"; // Indiates no network connectivity
    protected static List s_invTrxns = new ArrayList();
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
        

    public void ProcessWS() {
        String restURI = RestURI.PostInvTrxnURI();
        String lpn = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.lpnText}");
        String item = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.itemNumber}");
        String uom = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.uom}");
        String qty = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.quantity}");
        String fromSubInv = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.FromSubinventory}");
        String fromLocator = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.FromLocator}");
        String toSubInv = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.ToSubinventory}");
        String toLocator = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.ToLocator}");
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
        String jsonArrayAsString = super.invokeUPDATE(restURI, payload);
        System.out.println("Received response");
        throw new AdfException("Transaction completed", AdfException.INFO);

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
