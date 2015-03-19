package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.LotBO;
import dcom.shop.application.mobile.SerialBO;
import dcom.shop.application.mobile.transaction.MiscTxnBO;
import dcom.shop.application.mobile.transaction.SubInventoryTxnBO;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;

public class InvTrnDC extends RestCallerUtil {
    public InvTrnDC() {
        super();
    }
    private static final String NOT_REACHABLE = "NotReachable"; // Indiates no network connectivity
    protected static List s_invTrxns = new ArrayList();
    protected static List s_miscTrxns = new ArrayList();
    protected static List s_serialTrxns = new ArrayList();
    protected static List s_lotTrxns = new ArrayList();
    protected static List s_filteredSerialTrxns = new ArrayList();
    protected static List s_filteredLotTrxns = new ArrayList();

    public String InsertTransaction(String trxType) {
        String networkStatus =
            (String) AdfmfJavaUtilities.evaluateELExpression("#{deviceScope.hardware.networkStatus}");
        SubInventoryTxnBO subInvTxn = new SubInventoryTxnBO();

        if (networkStatus.equals(NOT_REACHABLE) || "QUEUE".equals(trxType)) {
            subInvTxn.setCompleteFlag("N");
        } else {
            subInvTxn.setCompleteFlag("Y");
        }
        subInvTxn.setIsNewEntity("Y");

        Integer trxnId = (Integer) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.SubinvTrxnId}");
        String lpn = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.searchLpnKeyword}");
        String item = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.searchKeyword}");
        String itemName = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.description}");
        String qty = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.quantity}");
        String fromSubInv = (String) AdfmfJavaUtilities.evaluateELExpression("#{bindings.subFromInv.inputValue}");
        String fromLocator = (String) AdfmfJavaUtilities.evaluateELExpression("#{bindings.fromLoc.inputValue}");
        String toSubInv = (String) AdfmfJavaUtilities.evaluateELExpression("#{bindings.subToInv.inputValue}");
        String toLocator = (String) AdfmfJavaUtilities.evaluateELExpression("#{bindings.toLoc.inputValue}");
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
        if ("SUBMIT".equals(trxType))
            ProcessWS(trxnId);
        return "cancel";
    }


    public void ProcessWS(Integer trxnId) {
        String restURI = RestURI.PostInvTrxnURI();
        s_invTrxns.clear();
        SyncUtils sync = new SyncUtils();
        SubInventoryTxnBO invTxn = null;
        s_invTrxns = sync.getCollectionFromDB(SubInventoryTxnBO.class);
        SubInventoryTxnBO[] invTxnArray =
            (SubInventoryTxnBO[]) s_invTrxns.toArray(new SubInventoryTxnBO[s_invTrxns.size()]);
        for (int i = 0; i < s_invTrxns.size(); i++) {
            invTxn = invTxnArray[0];
        }
        s_serialTrxns = sync.getCollectionFromDB(SerialBO.class);
        filterSerials();
        s_lotTrxns = sync.getCollectionFromDB(LotBO.class);
        filterLots();
        String lpn = invTxn.getLPN(); //(String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.lpnText}");
        String item =
            invTxn.getItemNumber(); //(String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.itemNumber}");
        String uom = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.uom}");
        String qty =
            invTxn.getQuantity(); //(String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.quantity}");
        String fromSubInv =
            invTxn.getFromInventory(); //(String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.FromSubinventory}");
        String fromLocator =
            invTxn.getFromLoc(); // (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.FromLocator}");
        String toSubInv =
            invTxn.getToInventory(); //(String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.ToSubinventory}");
        String toLocator =
            invTxn.getToLoc(); //(String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.ToLocator}");
        String destOrg = "100";
        String sourceOrg = "100";
        String payload =
            "{\n" + "\"PROCESSINVTXN_Input\":\n" + "{\n" +
            "   \"RESTHeader\": {\"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" +
            "                  \"RespApplication\": \"ONT\",\n" +
            "                  \"SecurityGroup\": \"STANDARD\",\n" +
            "                  \"NLSLanguage\": \"AMERICAN\",\n" + "                  \"Org_Id\": \"82\"\n" +
            "                 },\n" + "   \"InputParameters\": \n" + "      {\"PINVTXN\": { \"LPN\": \"" + lpn +
            "\",\"ITEM\": \"" + item + "\", \"SOURCSUBINV\": \"" + fromSubInv + "\", \"SOURCELOCATOR\": \"" +
            fromLocator + "\",\"TXNUOM\": \"" + uom + "\", \"TRXQTY\": \"" + qty + "\", \"DESTORG\": \"" + destOrg +
            "\", \"SOURCEORGCODE\": \"" + sourceOrg + "\", \"DESTSUBINV\": \"" + toSubInv + "\", \"DESTLOCATOR\": \"" +
            toLocator + "\"" + ", \"TRXTYPE\": \"SUBINV\" ,\n" + "          \"LOTS\": {\n" +
            "            \"XXDCOM_LOT_TAB\": [";
        System.out.println("Calling create method");
        LotBO lot = new LotBO();
        Iterator j = s_filteredLotTrxns.iterator();
        while (j.hasNext()) {
            lot = (LotBO) j.next();
            payload = payload + "{\"LOT\":\"" + lot.getLotNo() + "\",\"LOTQTY\": \"" + lot.getLotQty() + "\"},";

        }
        payload = payload.substring(0, payload.length() - 1);
        payload = payload + "]},\"SERIALS\": { \"XXDCOM_SERIAL_TAB\": [";
        SerialBO serial = new SerialBO();
        Iterator i = s_filteredSerialTrxns.iterator();
        while (i.hasNext()) {
            serial = (SerialBO) i.next();
            payload =
                payload + "{\"FROMSERIAL\":\"" + serial.getFromSerial() + "\",\"TOSERIAL\": \"" + serial.getToSerial() +
                "\",\"SERIALQTY\": \"" + serial.getSerialQty() + "\"},";

        }
        payload = payload.substring(0, payload.length() - 1);
        payload = payload + "]}\n" + "}}}}";
        try {
            String jsonArrayAsString = super.invokeUPDATE(restURI, payload);
            System.out.println("Received response" + jsonArrayAsString);
        } catch (Exception e) {
            System.out.println("error " + e.toString());
        }

        //    throw new AdfException("Transaction completed", AdfException.INFO);

    }

    public void filterSerials() {
        try {
            System.out.println("inside filter code");
            s_filteredSerialTrxns.clear();

            HashMap filterFileds = new HashMap();
            Integer trxnId = (Integer) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.SubinvTrxnId}");
            String trxnType = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.TrxType}");
            filterFileds.put("trxnid", trxnId);
            filterFileds.put("trxtype", trxnType);


            HashMap paramMap = new HashMap();
            paramMap.put("collection", s_serialTrxns);
            paramMap.put("filterFieldsValues", filterFileds);
            System.out.println("called super filtered class");
            SyncUtils sync = new SyncUtils();

            s_filteredSerialTrxns = (List) sync.getFileteredCollection(SerialBO.class, paramMap);
            System.out.println("collection size is " + s_filteredSerialTrxns.size());
        } catch (Exception e) {
            throw new RuntimeException("My Code Error " + e);
        }
    }

    public void filterLots() {
        try {
            System.out.println("inside filter code");
            s_filteredLotTrxns.clear();

            HashMap filterFileds = new HashMap();
            Integer trxnId = (Integer) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.SubinvTrxnId}");
            String trxnType = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.TrxType}");
            filterFileds.put("trxnid", trxnId);
            filterFileds.put("trxtype", trxnType);


            HashMap paramMap = new HashMap();
            paramMap.put("collection", s_lotTrxns);
            paramMap.put("filterFieldsValues", filterFileds);
            System.out.println("called super filtered class");
            SyncUtils sync = new SyncUtils();

            s_filteredLotTrxns = (List) sync.getFileteredCollection(LotBO.class, paramMap);
            System.out.println("collection size is " + s_filteredLotTrxns.size());
        } catch (Exception e) {
            throw new RuntimeException("My Code Error " + e);
        }
    }

    public String InsertMiscTransaction(String trxType) {
        String networkStatus =
            (String) AdfmfJavaUtilities.evaluateELExpression("#{deviceScope.hardware.networkStatus}");
        MiscTxnBO miscTxn = new MiscTxnBO();

        if (networkStatus.equals(NOT_REACHABLE) || "QUEUE".equals(trxType)) {
            miscTxn.setCompleteFlag("N");
        } else {
            miscTxn.setCompleteFlag("Y");
        }

        Integer trxnId = (Integer) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.SubinvTrxnId}");
        String item = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.searchKeyword}");
        String itemName = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.description}");
        String qty = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.quantity}");
        String acctAlias = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.accountAlias}");
        String subInv = (String) AdfmfJavaUtilities.evaluateELExpression("#{bindings.subFromInv.inputValue}");
        String locator = (String) AdfmfJavaUtilities.evaluateELExpression("#{bindings.fromLoc.inputValue}");
        String trxnType = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.TrxType}");
        miscTxn.setTrxnId(trxnId);
        miscTxn.setItemNumber(item);
        miscTxn.setItemName(itemName);
        miscTxn.setQuantity(qty);
        miscTxn.setFromInventory(subInv);
        miscTxn.setAccountAlias(acctAlias);
        miscTxn.setLocator(locator);
        miscTxn.setTrxType(trxnType);
        s_miscTrxns.add(miscTxn);
        SyncUtils syncUtils = new SyncUtils();
        syncUtils.insertSqlLiteTable(MiscTxnBO.class, s_miscTrxns);
        if ("SUBMIT".equals(trxType))
            ProcessMiscTrxnWS();
        return "cancel";
    }

    public void ProcessMiscTrxnWS() {
        String restURI = RestURI.PostInvTrxnURI();
        s_miscTrxns.clear();
        s_serialTrxns.clear();
        s_lotTrxns.clear();
        SyncUtils sync = new SyncUtils();
        MiscTxnBO invTxn = null;
        s_miscTrxns = sync.getCollectionFromDB(MiscTxnBO.class);
        MiscTxnBO[] invTxnArray = (MiscTxnBO[]) s_miscTrxns.toArray(new MiscTxnBO[s_miscTrxns.size()]);
        for (int i = 0; i < s_miscTrxns.size(); i++) {
            invTxn = invTxnArray[0];
        }
        s_serialTrxns = sync.getCollectionFromDB(SerialBO.class);
        filterSerials();
        s_lotTrxns = sync.getCollectionFromDB(LotBO.class);
        filterLots();
        String item =
            invTxn.getItemNumber(); //(String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.itemNumber}");
        String uom = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.uom}");
        String qty =
            invTxn.getQuantity(); //(String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.quantity}");
        String fromSubInv =
            invTxn.getFromInventory(); //(String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.FromSubinventory}");
        String fromLocator =
            invTxn.getLocator(); // (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.FromLocator}");
        String acctAlias =
            invTxn.getAccountAlias(); //(String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.ToSubinventory}");
        String trxType =
            invTxn.getTrxType(); //(String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.ToLocator}");
        String destOrg = "100";
        String sourceOrg = "100";
        String payload =
            "{\n" + "\"PROCESSINVTXN_Input\":\n" + "{\n" +
            "   \"RESTHeader\": {\"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" +
            "                  \"RespApplication\": \"ONT\",\n" +
            "                  \"SecurityGroup\": \"STANDARD\",\n" +
            "                  \"NLSLanguage\": \"AMERICAN\",\n" + "                  \"Org_Id\": \"82\"\n" +
            "                 },\n" + "   \"InputParameters\": \n" + "      {\"PINVTXN\": { \"ITEM\": \"" + item +
            "\", \"SOURCSUBINV\": \"" + fromSubInv + "\", \"SOURCELOCATOR\": \"" + fromLocator + "\",\"TXNUOM\": \"" +
            uom + "\", \"TRXQTY\": \"" + qty + "\", \"ACCOUNTALIAS\": \"" + acctAlias + "\", \"SOURCEORGCODE\": \"" +
            sourceOrg + "\", \"TRXTYPE\": \"" + trxType + "\", \"DESTORG\": \"" + destOrg + "          \"LOTS\": {\n" +
            "            \"XXDCOM_LOT_TAB\": [";
        System.out.println("Calling create method");
        LotBO lot = new LotBO();
        Iterator j = s_filteredLotTrxns.iterator();
        while (j.hasNext()) {
            lot = (LotBO) j.next();
            payload = payload + "{\"LOT\":\"" + lot.getLotNo() + "\",\"LOTQTY\": \"" + lot.getLotQty() + "\"},";

        }
        payload = payload.substring(0, payload.length() - 1);
        payload = payload + "]},\"SERIALS\": { \"XXDCOM_SERIAL_TAB\": [";
        SerialBO serial = new SerialBO();
        Iterator i = s_filteredSerialTrxns.iterator();
        while (i.hasNext()) {
            serial = (SerialBO) i.next();
            payload =
                payload + "{\"FROMSERIAL\":\"" + serial.getFromSerial() + "\",\"TOSERIAL\": \"" + serial.getToSerial() +
                "\",\"SERIALQTY\": \"" + serial.getSerialQty() + "\"},";

        }
        payload = payload.substring(0, payload.length() - 1);
        payload = payload + "]}\n" + "}}}}";
        try {
            String jsonArrayAsString = super.invokeUPDATE(restURI, payload);
            System.out.println("Received response" + jsonArrayAsString);
        } catch (Exception e) {
            System.out.println("error " + e.toString());
        }

    }
}
