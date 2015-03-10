package dcom.shop.application.dc;

import dcom.shop.application.mobile.InvTrnBO;
import dcom.shop.application.mobile.InvTrnReqBO;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.JSONBeanSerializationHelper;
import oracle.adfmf.framework.exception.AdfException;

public class InvTrnDC extends RestCallerUtil {
    public InvTrnDC() {
        super();
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
            "                 },\n" + "   \"InputParameters\": \n" + "      {\"PINVTXN\": { \"LPN\": \"" + lpn +
            "\",\"ITEM\": \"" + item + "\", \"SOURCSUBINV\": \"" + fromSubInv + "\", \"SOURCELOCATOR\": \"" +
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
