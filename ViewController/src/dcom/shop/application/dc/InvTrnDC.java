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

import javax.el.MethodExpression;

import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
    protected static List s_filteredInvTrxns = new ArrayList();
    protected static List s_filteredMiscTrxns = new ArrayList();
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);

    public void DeleteTransaction(Integer trxnId) {
        SyncUtils syncUtils = new SyncUtils();
        HashMap whereClause = new HashMap();
        whereClause.put("trxnid", trxnId);
        // whereClause.put("trxtype", trxnType);

        boolean result = syncUtils.deleteSqlLiteTable(SubInventoryTxnBO.class, whereClause);
        if (!result) {
            MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.refresh.execute}", Object.class, new Class[] {
                                                                         });
            me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(), "showAlert", new Object[] {
                                                                      "Success",
                                                                      "Transaction has been deleted successfully.", "ok"
            });
        } else {
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(), "showAlert", new Object[] {
                                                                      "Error", "Transaction deletion failed!", "ok"
            });
        }
    }

    public String InsertTransaction(String trxType) {
        s_invTrxns.clear();
        String processResult = null;
        String networkStatus =
            (String) AdfmfJavaUtilities.evaluateELExpression("#{deviceScope.hardware.networkStatus}");
        SubInventoryTxnBO subInvTxn = new SubInventoryTxnBO();
        subInvTxn.setCompleteFlag("N");
        /*if (networkStatus.equals(NOT_REACHABLE) || "QUEUE".equals(trxType)) {
            subInvTxn.setCompleteFlag("N");
        } else {
            subInvTxn.setCompleteFlag("Y");
        }*/
        subInvTxn.setIsNewEntity("Y");

        Integer trxnId = (Integer) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.SubinvTrxnId}");
        String lpn = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.searchLpnKeyword}");
        String item = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.searchKeyword}");
        String itemName = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.description}");
        String qty = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.quantity}");
        String uom = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.uom}");
        String fromSubInv = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.FromSubinventory}");
        String fromLocator = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.FromLocator}");
        String toSubInv = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.ToSubinventory}");
        String toLocator = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.ToLocator}");
        String tranStatus = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.TransactionStatus}");

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
        subInvTxn.setTxnuom(uom);
        SyncUtils sync = new SyncUtils();
        s_serialTrxns = sync.getCollectionFromDB(SerialBO.class);
        filterSerials(trxnId);
        if (s_filteredSerialTrxns.size() > 0)
            subInvTxn.setSerialControl("0");
        else
            subInvTxn.setSerialControl("1");
        s_lotTrxns = sync.getCollectionFromDB(LotBO.class);
        filterLots(trxnId);
        if (s_filteredLotTrxns.size() > 0)
            subInvTxn.setLotControl("0");
        else 
           subInvTxn.setLotControl("1");
        SyncUtils syncUtils = new SyncUtils();
        if ("New".equals(tranStatus)) {
            s_invTrxns.add(subInvTxn);
            syncUtils.insertSqlLiteTable(SubInventoryTxnBO.class, s_invTrxns);
        } else if ("Edit".equals(tranStatus)) {
            s_invTrxns.add(subInvTxn);
            HashMap whereClause = new HashMap();
            whereClause.put("trxnid", trxnId);
            syncUtils.updateSqlLiteTableWithWhere(SubInventoryTxnBO.class, s_invTrxns, whereClause);
        }
        if ("SUBMIT".equals(trxType) && (!(networkStatus.equals(NOT_REACHABLE))))
            processResult = ProcessWS(trxnId);
        return processResult;
    }

    public void CompleteTrxn(Integer trxnId) {
        String processResult = ProcessWS(trxnId);
        if ("cancel".equals(processResult)) {
            MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.refresh.execute}", Object.class, new Class[] {
                                                                         });
            me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(), "showAlert", new Object[] {
                                                                      "Success",
                                                                      "Transaction has been completed successfully.",
                                                                      "ok"
            });
        } else {
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(), "showAlert", new Object[] {
                                                                      "Error", "Transaction completion failed!", "ok"
            });
        }
    }

    public String ProcessWS(Integer trxnId) {
        String processResult = null;
        String restURI = RestURI.PostInvTrxnURI();
        s_invTrxns.clear();
        String jsonArrayAsString = null;
        SyncUtils sync = new SyncUtils();
        SubInventoryTxnBO invTxn = null;
        s_invTrxns = sync.getCollectionFromDB(SubInventoryTxnBO.class);
        filterInvTrxns(trxnId);
        SubInventoryTxnBO[] invTxnArray =
            (SubInventoryTxnBO[]) s_filteredInvTrxns.toArray(new SubInventoryTxnBO[s_filteredInvTrxns.size()]);
        for (int i = 0; i < s_filteredInvTrxns.size(); i++) {
            invTxn = invTxnArray[0];
        }
        s_serialTrxns = sync.getCollectionFromDB(SerialBO.class);
        filterSerials(invTxn.getTrxnId());
        s_lotTrxns = sync.getCollectionFromDB(LotBO.class);
        filterLots(invTxn.getTrxnId());
        String lpn = invTxn.getLPN(); //(String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.lpnText}");
        String item =
            invTxn.getItemNumber(); //(String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.itemNumber}");
        String uom = invTxn.getTxnuom();
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
        String completeFlag = invTxn.getCompleteFlag();
        String destOrg =
            (String) AdfmfJavaUtilities.evaluateELExpression("#{preferenceScope.feature.dcom.shop.MyWarehouse.OrgCodePG.OrgCode}");
        ;
        String sourceOrg =
            (String) AdfmfJavaUtilities.evaluateELExpression("#{preferenceScope.feature.dcom.shop.MyWarehouse.OrgCodePG.OrgCode}");
        ;
        String payload =
            "{\n" + "\"PROCESSINVTXN_Input\":\n" + "{\n" +
            "   \"RESTHeader\": {\"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" +
            "                  \"RespApplication\": \"ONT\",\n" +
            "                  \"SecurityGroup\": \"STANDARD\",\n" +
            "                  \"NLSLanguage\": \"AMERICAN\",\n" + "                  \"Org_Id\": \"82\"\n" +
            "                 },\n" + "   \"InputParameters\": \n" +
            "      {\"PINVTXN\": {  \"TRXTYPE\": \"SUBINV\" , \"SOURCEORGCODE\": \"" + sourceOrg + "\",\"ITEM\": \"" +
            item + "\", \"SOURCESUBINV\": \"" + fromSubInv + "\", \"SOURCELOCATOR\": \"" + fromLocator +
            "\", \"LPN\": \"" + lpn + "\", \"DESTORG\": \"" + destOrg + "\", \"DESTSUBINV\": \"" + toSubInv +
            "\", \"DESTLOCATOR\": \"" + toLocator + "\" , \"TRXQTY\": \"" + qty + "\" ,\"TXNUOM\": \"" + uom + "\"   " +
            " \n";
        System.out.println("Calling create method");

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
        try {
            jsonArrayAsString = super.invokeUPDATE(restURI, payload);
            System.out.println("Received response" + jsonArrayAsString);

            if (jsonArrayAsString != null) {
                JSONObject jsObject1 = null;
                JSONParser parser = new JSONParser();
                Object object;

                object = parser.parse(jsonArrayAsString);

                JSONObject jsonObject = (JSONObject) object;
                JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                String result = jsObject.get("XSTATUS").toString();
                if ("S".equals(result)) {

                    if (!("Y".equals(completeFlag))) {
                        invTxn.setCompleteFlag("Y");
                        s_filteredInvTrxns.clear();
                        s_filteredInvTrxns.add(invTxn);
                        // sync.updateSqlLiteTable(SubInventoryTxnBO.class, s_filteredInvTrxns);
                        HashMap whereClause = new HashMap();
                        whereClause.put("trxnid", trxnId);
                        sync.updateSqlLiteTableWithWhere(SubInventoryTxnBO.class, s_filteredInvTrxns, whereClause);
                    }
                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                              "showAlert", new Object[] {
                                                                              "Success",
                                                                              "Transaction has been submitted successfully.",
                                                                              "ok"
                    });
                    processResult = "cancel";
                } else {
                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                              "showAlert", new Object[] {
                                                                              "Error", "Transaction submission failed. \n"+jsObject.get("XMSG").toString(),
                                                                              "ok"
                    });
                    processResult = "";
                }
                providerChangeSupport.fireProviderRefresh("subinventories");
            } else {
                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                          "showAlert", new Object[] {
                                                                          "Error", "Transaction submission failed.",
                                                                          "ok"
                });
                processResult = "";
            }

        } catch (Exception e) {
            System.out.println("error " + e.toString());
        }
        //    throw new AdfException("Transaction completed", AdfException.INFO);
        return processResult;
    }

    public void filterInvTrxns(Integer trxnId) {
        try {
            System.out.println("inside filter code");
            s_filteredInvTrxns.clear();

            HashMap filterFileds = new HashMap();
            String trxnType = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.ParentPage}");
            filterFileds.put("trxnid", trxnId);
            //filterFileds.put("trxtype", trxnType);


            HashMap paramMap = new HashMap();
            paramMap.put("collection", s_invTrxns);
            paramMap.put("filterFieldsValues", filterFileds);
            System.out.println("called super filtered class");
            SyncUtils sync = new SyncUtils();
            if(s_invTrxns.size()>0)
            s_filteredInvTrxns = (List) sync.getFileteredCollection(SubInventoryTxnBO.class, paramMap);
            System.out.println("collection size is " + s_filteredInvTrxns.size());
        } catch (Exception e) {
            throw new RuntimeException("My Code Error " + e);
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

    public void DeleteMiscTransaction(Integer trxnId) {
        SyncUtils syncUtils = new SyncUtils();
        HashMap whereClause = new HashMap();
        whereClause.put("trxnid", trxnId);
        // whereClause.put("trxtype", trxnType);

        boolean result = syncUtils.deleteSqlLiteTable(MiscTxnBO.class, whereClause);
        if (!result) {
            MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.refresh.execute}", Object.class, new Class[] {
                                                                         });
            me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(), "showAlert", new Object[] {
                                                                      "Success",
                                                                      "Transaction has been deleted successfully.", "ok"
            });
        } else {
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(), "showAlert", new Object[] {
                                                                      "Error", "Transaction deletion failed!", "ok"
            });
        }
    }

    public void CompleteMiscTrxn(Integer trxnId) {
        String processResult = ProcessMiscTrxnWS(trxnId);
        if ("Back".equals(processResult)) {
            MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.refresh.execute}", Object.class, new Class[] {
                                                                         });
            me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(), "showAlert", new Object[] {
                                                                      "Success",
                                                                      "Transaction has been completed successfully.",
                                                                      "ok"
            });
        } else {
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(), "showAlert", new Object[] {
                                                                      "Error", "Transaction completion failed!", "ok"
            });
        }
    }

    public void filterMiscTrxns(Integer trxnId) {
        try {
            System.out.println("inside filter code");
            s_filteredMiscTrxns.clear();

            HashMap filterFileds = new HashMap();
            // String trxnType = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.ParentPage}");
            filterFileds.put("trxnid", trxnId);
            //filterFileds.put("trxtype", trxnType);


            HashMap paramMap = new HashMap();
            paramMap.put("collection", s_miscTrxns);
            paramMap.put("filterFieldsValues", filterFileds);
            System.out.println("called super filtered class");
            SyncUtils sync = new SyncUtils();

            s_filteredMiscTrxns = (List) sync.getFileteredCollection(MiscTxnBO.class, paramMap);
            System.out.println("collection size is " + s_filteredMiscTrxns.size());
        } catch (Exception e) {
            throw new RuntimeException("My Code Error " + e);
        }
    }

    public String InsertMiscTransaction(String trxType) {
        s_miscTrxns.clear();
        String processResult = null;
        String networkStatus =
            (String) AdfmfJavaUtilities.evaluateELExpression("#{deviceScope.hardware.networkStatus}");
        MiscTxnBO miscTxn = new MiscTxnBO();

        /*if (networkStatus.equals(NOT_REACHABLE) || "QUEUE".equals(trxType)) {
            miscTxn.setCompleteFlag("N");
        } else {
            miscTxn.setCompleteFlag("Y");
        }*/
        miscTxn.setCompleteFlag("N");
        Integer trxnId = (Integer) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.SubinvTrxnId}");
        String item = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.searchKeyword}");
        String itemName = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.description}");
        String qty = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.quantity}");
        String acctAlias = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.accountAlias}");
        String subInv = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.FromSubinventory}");
        String locator = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.FromLocator}");
        String trxnType = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.TrxType}");
        String serialControl = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.serialControl}");
        String lotControl = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.lotControl}");
        String uom = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.uom}");
        String tranStatus = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.TransactionStatus}");
        miscTxn.setTrxnId(trxnId);
        miscTxn.setItemNumber(item);
        miscTxn.setItemName(itemName);
        miscTxn.setQuantity(qty);
        miscTxn.setFromInventory(subInv);
        miscTxn.setAccountAlias(acctAlias);
        miscTxn.setLocator(locator);
        miscTxn.setTrxType(trxnType);
        miscTxn.setSerialControl(serialControl);
        miscTxn.setLotControl(lotControl);
        miscTxn.setTxnUom(uom);

        SyncUtils syncUtils = new SyncUtils();
        s_serialTrxns = syncUtils.getCollectionFromDB(SerialBO.class);
        filterSerials(trxnId);
        if (s_filteredSerialTrxns.size() > 0)
            miscTxn.setSerialControl("0");
        else 
            miscTxn.setSerialControl("1");
        s_lotTrxns = syncUtils.getCollectionFromDB(LotBO.class);
        filterLots(trxnId);
        if (s_filteredLotTrxns.size() > 0)
            miscTxn.setLotControl("0");
        else
            miscTxn.setLotControl("1");
        if ("New".equals(tranStatus)) {
            s_miscTrxns.add(miscTxn);
            syncUtils.insertSqlLiteTable(MiscTxnBO.class, s_miscTrxns);
        } else if ("Edit".equals(tranStatus)) {
            s_miscTrxns.clear();
            s_miscTrxns.add(miscTxn);
            HashMap whereClause = new HashMap();
            whereClause.put("trxnid", trxnId);
            syncUtils.updateSqlLiteTableWithWhere(MiscTxnBO.class, s_miscTrxns, whereClause);
        }
        if ("SUBMIT".equals(trxType) && (!(networkStatus.equals(NOT_REACHABLE))))
            processResult = ProcessMiscTrxnWS(trxnId);
        return processResult;
    }

    public String ProcessMiscTrxnWS(Integer trxnId) {
        String processResult = null;
        String restURI = RestURI.PostInvTrxnURI();
        s_miscTrxns.clear();
        s_serialTrxns.clear();
        s_lotTrxns.clear();
        SyncUtils sync = new SyncUtils();
        MiscTxnBO invTxn = null;
        s_miscTrxns = sync.getCollectionFromDB(MiscTxnBO.class);
        filterMiscTrxns(trxnId);

        MiscTxnBO[] invTxnArray = (MiscTxnBO[]) s_filteredMiscTrxns.toArray(new MiscTxnBO[s_filteredMiscTrxns.size()]);
        for (int i = 0; i < s_filteredMiscTrxns.size(); i++) {
            invTxn = invTxnArray[0];
        }
        s_serialTrxns = sync.getCollectionFromDB(SerialBO.class);
        filterSerials(invTxn.getTrxnId());
        s_lotTrxns = sync.getCollectionFromDB(LotBO.class);
        filterLots(invTxn.getTrxnId());
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
            "                 },\n" + "   \"InputParameters\": \n" + "      {\"PINVTXN\": { \"TRXTYPE\": \"" + trxType +
            "\" , \"SOURCEORGCODE\": \"" + sourceOrg + "\"  , \"ITEM\": \"" + item + "\", \"SOURCESUBINV\": \"" +
            fromSubInv + "\", \"SOURCELOCATOR\": \"" + fromLocator + "\" , \"DESTORG\": \"" + destOrg +
            "\" , \"TRXQTY\": \"" + qty + "\" ,\"TXNUOM\": \"" + uom + "\", \"ACCOUNTALIAS\": \"" + acctAlias + "\"";
        System.out.println("Calling create method");
        LotBO lot = new LotBO();
        //  if(s_filteredLotTrxns.size()>0){
        payload = payload + ", \"LOTS\": {\n" + "  \"XXDCOM_LOT_TAB\": [   ";
        //  }
        Iterator j = s_filteredLotTrxns.iterator();
        while (j.hasNext()) {
            lot = (LotBO) j.next();
            payload = payload + "{\"LOT\":\"" + lot.getLotNo() + "\",\"LOTQTY\": \"" + lot.getLotQty() + "\"},";

        }
        payload = payload.substring(0, payload.length() - 1);
        if (s_filteredSerialTrxns.size() > 0)
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

            if (jsonArrayAsString != null) {
                JSONObject jsObject1 = null;
                JSONParser parser = new JSONParser();
                Object object;

                object = parser.parse(jsonArrayAsString);

                JSONObject jsonObject = (JSONObject) object;
                JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                String result = jsObject.get("XSTATUS").toString();
                if ("S".equals(result)) {
                    invTxn.setCompleteFlag("Y");
                    s_filteredMiscTrxns.clear();
                    s_filteredMiscTrxns.add(invTxn);
                    HashMap whereClause = new HashMap();
                    whereClause.put("trxnid", trxnId);
                    sync.updateSqlLiteTableWithWhere(MiscTxnBO.class, s_filteredMiscTrxns, whereClause);
                    processResult = "Back";
                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                              "showAlert", new Object[] {
                                                                              "Success",
                                                                              "Transaction has been submitted successfully.",
                                                                              "ok"
                    });
                    providerChangeSupport.fireProviderRefresh("miscTrxns");
                } else {
                    processResult = "";
                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                              "showAlert", new Object[] {
                                                                              "Error", "Transaction submission failed.\n"+jsObject.get("XMSG").toString(),
                                                                              "ok"
                    });
                }
            } else {
                processResult = "";
                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                          "showAlert", new Object[] {
                                                                          "Error", "Transaction submission failed.",
                                                                          "ok"
                });
            }
        } catch (Exception e) {
            System.out.println("error " + e.toString());
        }
        return processResult;
    }

    public void addProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.addProviderChangeListener(l);
    }

    public void removeProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.removeProviderChangeListener(l);
    }
}
