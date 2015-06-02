package dcom.shop.application.dc.transaction;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.database.ConnectionFactory;
import dcom.shop.application.mobile.LotBO;
import dcom.shop.application.mobile.SerialBO;
import dcom.shop.application.mobile.transaction.ItemTxnBO;
import dcom.shop.application.mobile.transaction.LpnTxnBO;
import dcom.shop.application.mobile.transaction.MiscTxnBO;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import javax.el.ValueExpression;

import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.util.Utility;
import oracle.adfmf.util.logging.Trace;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class LpnTxnDC extends SyncUtils {
    public LpnTxnDC() {
        try {
            filtered_LpnTxn = super.getOfflineCollection(LpnTxnBO.class);
            if (s_lpnTrxns.size() == 0) {
                s_lpnTrxns = new ArrayList<LpnTxnBO>();
                s_lpnTrxns.clear();
                this.getLpnTrxns();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    protected static List<LpnTxnBO> s_lpnTrxns = new ArrayList<LpnTxnBO>();
    private List filtered_LpnTxn = new ArrayList();
    protected static List s_itemTrxns = new ArrayList();
    private List<LpnTxnBO> s_filteredItemTxn = new ArrayList<LpnTxnBO>();
    protected static List s_serialTrxns = new ArrayList();
    protected static List s_lotTrxns = new ArrayList();
    protected static List s_filteredSerialTrxns = new ArrayList();
    protected static List s_filteredLotTrxns = new ArrayList();

    public synchronized List<LpnTxnBO> getLpnTrxns() {
        MiscTxnBO SubInventoryTxnBO[] = null;
        MiscTxnBO subinventory = null;
        s_lpnTrxns = selectLpnTrxns(s_lpnTrxns);
        int trxnId = s_lpnTrxns.size();
        // ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.LpnTrxnId}", int.class);
        // ve.setValue(AdfmfJavaUtilities.getAdfELContext(), trxnId);

        return s_lpnTrxns;
    }

    public static List<LpnTxnBO> selectLpnTrxns(List<LpnTxnBO> lpntxns) {

        //   ArrayList stocks = new ArrayList();

        int recordCount = 0;
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM LPNTXN ORDER BY TRXNID");

            while (rs.next()) {
                recordCount++;
            }

            rs.beforeFirst();

            while (rs.next()) {
                int trxnid = rs.getInt(1);
                String fromSubinv = rs.getString(2);
                String fromLoc = rs.getString(3);
                String lpnFrom = rs.getString(4);
                String lpnTo = rs.getString(5);
                String trxntime = rs.getString(6);
                String orgCode = rs.getString(7);
                String trxType = rs.getString(8);
                String serialControl = rs.getString(9);
                String lotControl = rs.getString(10);

                if (lpntxns != null && lpntxns.size() >= recordCount) {
                    LpnTxnBO lpnTxn = (LpnTxnBO) lpntxns.get(trxnid);
                    lpnTxn.setTrxnId(trxnid);
                    lpnTxn.setSubinventory(fromSubinv);
                    lpnTxn.setLocator(fromLoc);
                    lpnTxn.setTrxTime(trxntime);
                    lpnTxn.setLpnFrom(lpnFrom);
                    lpnTxn.setTrxType(trxType);
                    lpnTxn.setLpnTo(lpnTo);
                    lpnTxn.setOrgCode(orgCode);
                } else {
                    LpnTxnBO lpn =
                        new LpnTxnBO(trxnid, fromSubinv, lpnFrom, lpnTo, trxntime, orgCode, fromLoc, trxType);
                    lpntxns.add(lpn);
                }
            }

        } catch (SQLException e) {
            Trace.log(Utility.ApplicationLogger, Level.SEVERE, SubInventoryTxnDC.class, "selectStocks",
                      "##############SQL Exception:  " + e.getMessage());
            e.printStackTrace();
        } catch (Exception exception) {
            Trace.log(Utility.ApplicationLogger, Level.SEVERE, SubInventoryTxnDC.class, "selectStocks",
                      "##############Exception:  " + exception.getMessage());

        }
        return lpntxns;
    }

    public String InsertTransaction() {
        s_lpnTrxns.clear();
        String errMsg = null;
        String networkStatus =
            (String) AdfmfJavaUtilities.evaluateELExpression("#{deviceScope.hardware.networkStatus}");
        String lpnPage = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.LpnPage}");
        if (!("MERGE_FROM".equals(lpnPage))) {
            ItemTxnDC item = new ItemTxnDC();
            String itemNumber = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.searchKeyword}");
            if (!("".equals(itemNumber)) && itemNumber != null && !("null".equals(itemNumber)))
                item.InsertItems(); //Inserting current item on the page
        }
        LpnTxnBO lpnTxn = new LpnTxnBO();


        Integer trxnId = (Integer) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.LpnTrxnId}");
        String fromLpn = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.searchLpnKeyword}");
        String subinv = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.FromSubinventory}");
        String locator = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.FromLocator}");
        String toLpn = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.searchToLpnKeyword}");
        /*
         * Below block is for validation
         */
        if (("".equals(fromLpn.trim()) || fromLpn == null)) {
            errMsg = "From LPN:"+ (String.format("%0$" + 70 + "s", "You must enter a value. ")+"\n");
        }
        if ("PACK_FROM".equals(lpnPage) || "UNPACK_FROM".equals(lpnPage)) {

            if ("0".equals(subinv) || subinv == null) {
                errMsg = (errMsg == null ? "" : errMsg) + "Subinventory:"+ (String.format("%0$" + 64 + "s", "You must enter a value. ")+"\n");
            }
            if ("0".equals(locator) || locator == null) {
                errMsg = (errMsg == null ? "" : errMsg) + "Locator:"+ (String.format("%0$" + 75 + "s", "You must enter a value. ")+"\n");
            }
        }
        if (!("PACK_FROM".equals(lpnPage))) {
            if ("".equals(toLpn) || toLpn == null) {
                errMsg = (errMsg == null ? "" : errMsg) + "To LPN:"+ (String.format("%0$" + 76 + "s", "You must enter a value. ")+"\n");
            }
        }


        if (errMsg != null) {
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(), "showAlert", new Object[] {
                                                                      "Error", errMsg, "ok"
            });
            return null;
        } else {
            lpnTxn.setTrxnId(trxnId);
            lpnTxn.setLpnFrom(fromLpn == "null" ? "" : fromLpn);
            lpnTxn.setSubinventory(subinv == "null" ? "" : subinv);
            lpnTxn.setLocator(locator == "null" ? "" : locator);
            lpnTxn.setLpnTo(toLpn == "null" ? "" : toLpn);
            lpnTxn.setTrxType(lpnPage == "null" ? "" : lpnPage);
            s_lpnTrxns.add(lpnTxn);
            SyncUtils syncUtils = new SyncUtils();
            syncUtils.insertSqlLiteTable(LpnTxnBO.class, s_lpnTrxns);
            return ProcessWS(trxnId);
        }

    }

    public void GenerateLpnWS() {
        String restURI = RestURI.GenerateLpnURI();
        String orgCode =
            (String) AdfmfJavaUtilities.evaluateELExpression("#{preferenceScope.feature.dcom.shop.MyWarehouse.OrgCodePG.OrgCode}");
        String payload =
            "{\n" + "\"Input_Parameters\":\n" + "{\n" +
            "   \"RESTHeader\": {\"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" +
            "                  \"RespApplication\": \"ONT\",\n" +
            "                  \"SecurityGroup\": \"STANDARD\",\n" +
            "                  \"NLSLanguage\": \"AMERICAN\",\n" + "                  \"Org_Id\": \"82\"\n" +
            "                 },\n" + "   \"InputParameters\": \n" + "      {\"PORGCODE\": \"" + orgCode + "\" }}}";
        try {
            RestCallerUtil rest = new RestCallerUtil();
            String jsonArrayAsString = rest.invokeUPDATE(restURI, payload);
            System.out.println("Received response" + jsonArrayAsString);
            if (jsonArrayAsString != null && !("".equals(jsonArrayAsString))) {
                JSONObject jsObject1 = null;
                JSONParser parser = new JSONParser();
                Object object;

                object = parser.parse(jsonArrayAsString);

                JSONObject jsonObject = (JSONObject) object;
                JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                String result = jsObject.get("XSTATUS").toString();
                if ("F".equals(result)) {
                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                              "showAlert", new Object[] {
                                                                              "Error", "Generate LPN Failed.", "ok"
                    });

                } else {
                    String lpn = jsObject.get("XLPN").toString();
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchLpnKeyword}", lpn);
                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                              "showAlert", new Object[] {
                                                                              "Success", "LPN " + lpn + " Generated!",
                                                                              "ok"
                    });
                }
            } else {
                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                          "showAlert", new Object[] {
                                                                          "Error", "Generate LPN Failed.", "ok"
                });
            }
        } catch (Exception e) {
            System.out.println("error " + e.toString());
        }
    }

    public String ProcessWS(Integer trxnId) {
        String restURI = RestURI.PostLpnTrxnURI();
        String returnResult = null;
        s_lpnTrxns.clear();
        SyncUtils sync = new SyncUtils();
        LpnTxnBO lpnTxn = null;
        s_lpnTrxns = sync.getCollectionFromDB(LpnTxnBO.class);
        filterLpnTrxns(trxnId);
        LpnTxnBO[] lpnTxnArray = (LpnTxnBO[]) filtered_LpnTxn.toArray(new LpnTxnBO[filtered_LpnTxn.size()]);
        for (int i = 0; i < filtered_LpnTxn.size(); i++) {
            lpnTxn = lpnTxnArray[0];
        }
        s_serialTrxns = sync.getCollectionFromDB(SerialBO.class);

        s_lotTrxns = sync.getCollectionFromDB(LotBO.class);
        s_itemTrxns = sync.getCollectionFromDB(ItemTxnBO.class);
        filterItems();

        String lpnFrom =
            lpnTxn.getLpnFrom(); //(String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.lpnText}");
        String lpnTo =
            lpnTxn.getLpnTo(); //(String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.itemNumber}");
        String subInv =
            lpnTxn.getSubinventory(); //(String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.FromSubinventory}");
        if (subInv.equals("{\"@xsi:nil\":\"true\"}")) {
            subInv = "";
        }
        String locator =
            lpnTxn.getLocator(); // (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.FromLocator}");
        if (locator.equals("{\"@xsi:nil\":\"true\"}")) {
            locator = "";
        }
        String trxType =
            lpnTxn.getTrxType(); //(String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.ToSubinventory}");
        String destOrg = "100";
        String sourceOrg =
            (String) AdfmfJavaUtilities.evaluateELExpression("#{preferenceScope.feature.dcom.shop.MyWarehouse.OrgCodePG.OrgCode}");
        ;
        String onHandQty = "0";
        String availQty = "0";
        String payload =
            "{\n" + "\"Input_Parameters\":\n" + "{\n" +
            "   \"RESTHeader\": {\"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" +
            "                  \"RespApplication\": \"ONT\",\n" +
            "                  \"SecurityGroup\": \"STANDARD\",\n" +
            "                  \"NLSLanguage\": \"AMERICAN\",\n" + "                  \"Org_Id\": \"82\"\n" +
            "                 },\n" + "   \"InputParameters\": \n" + "      {\"PLPNTXN\": { \"ORGCODE\": \"" +
            sourceOrg + "\",\"TXNTYPE\": \"" + trxType + "\", \"LPNFROM\": \"" + lpnFrom + "\", \"LPNTO\": \"" + lpnTo +
            "\",\"SUBINV\": \"" + subInv + "\", \"LOCATOR\": \"" + locator + "\" \n";
        System.out.println("Calling create method");
        ItemTxnBO item = new ItemTxnBO();
        Iterator k = s_filteredItemTxn.iterator();
        //  if(s_filteredItemTxn.size()>0){
        payload = payload + ", \"CONTENTS\": { \"CONTENTS_ITEM\": [   ";
        //}
        while (k.hasNext()) {
            item = (ItemTxnBO) k.next();
            payload =
                payload + "{\"ITEM\":\"" + item.getItemNumber() + "\",\"ITEMDESC\": \"" + item.getItemName() +
                "\",\"UOM\": \"" + item.getUom() + "\",\"ONHANDQTY\": \"" + onHandQty + "\",\"AVAILABLEQTY\": \"" +
                item.getQuantity() + "\"   ";

            filterLots(item.getItemId());
            filterSerials(item.getItemId());

            LotBO lot = new LotBO();
            Iterator j = s_filteredLotTrxns.iterator();
            //if (s_filteredLotTrxns.size() > 0) {
            payload = payload + ",\"LOTS\": { \"LOTS_ITEM\": [   ";

            //}
            while (j.hasNext()) {
                lot = (LotBO) j.next();
                payload = payload + "{\"LOT\":\"" + lot.getLotNo() + "\",\"LOTQTY\": \"" + lot.getLotQty() + "\"},";

            }
            payload = payload.substring(0, payload.length() - 1);
            SerialBO serial = new SerialBO();
            Iterator i = s_filteredSerialTrxns.iterator();
            if (s_filteredSerialTrxns.size() > 0) {
                payload = payload + "]},\"SERIALS\": { \"XXDCOM_SERIAL_TAB\": [";


            }
            while (i.hasNext()) {
                serial = (SerialBO) i.next();
                payload =
                    payload + "{\"FROMSERIAL\":\"" + serial.getFromSerial() + "\",\"TOSERIAL\": \"" +
                    serial.getToSerial() + "\",\"SERIALQTY\": \"" + serial.getSerialQty() + "\"},";

            }
            payload = payload.substring(0, payload.length() - 1);
            payload = payload + "]}},";
        }
        payload = payload.substring(0, payload.length() - 1);
        payload = payload + "]" + "}}}}}";
        try {
            RestCallerUtil rest = new RestCallerUtil();
            String jsonArrayAsString = rest.invokeUPDATE(restURI, payload);
            System.out.println("Received response" + jsonArrayAsString);
            if (jsonArrayAsString != null && !("".equals(jsonArrayAsString))) {
                JSONObject jsObject1 = null;
                JSONParser parser = new JSONParser();
                Object object;

                object = parser.parse(jsonArrayAsString);

                JSONObject jsonObject = (JSONObject) object;
                JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                String result = jsObject.get("XSTATUS").toString();
                if ("S".equals(result)) {
                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                              "showAlert", new Object[] {
                                                                              "Success",
                                                                              "Transaction has been submitted successfully.",
                                                                              "ok"
                    });
                    returnResult = "Back";

                } else {
                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                              "showAlert", new Object[] {
                                                                              "Error",
                                                                              "Transaction submission failed.\n" +
                                                                              jsObject.get("XMSG").toString(), "ok"
                    });
                    returnResult = "";
                }
            }
        } catch (Exception e) {
            System.out.println("error " + e.toString());
        }

        //    throw new AdfException("Transaction completed", AdfException.INFO);

        return returnResult;
    }

    public void filterLpnTrxns(Integer trxnId) {
        try {
            System.out.println("inside filter code");
            filtered_LpnTxn.clear();

            HashMap filterFileds = new HashMap();
            filterFileds.put("trxnid", trxnId);


            HashMap paramMap = new HashMap();
            paramMap.put("collection", s_lpnTrxns);
            paramMap.put("filterFieldsValues", filterFileds);
            System.out.println("called super filtered class");
            if (s_lpnTrxns.size() > 0)
                filtered_LpnTxn = (List) super.getFileteredCollection(LpnTxnBO.class, paramMap);
            System.out.println("collection size is " + filtered_LpnTxn.size());
        } catch (Exception e) {
            throw new RuntimeException("My Code Error " + e);
        }
    }

    public void filterSerials(Integer itemId) {
        try {
            System.out.println("inside filter code");
            s_filteredSerialTrxns.clear();

            HashMap filterFileds = new HashMap();
            String trxnType = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.ParentPage}");
            filterFileds.put("trxnid", itemId);
            filterFileds.put("trxtype", trxnType);


            HashMap paramMap = new HashMap();
            paramMap.put("collection", s_serialTrxns);
            paramMap.put("filterFieldsValues", filterFileds);
            System.out.println("called super filtered class");
            SyncUtils sync = new SyncUtils();
            if (s_serialTrxns.size() > 0)
                s_filteredSerialTrxns = (List) sync.getFileteredCollection(SerialBO.class, paramMap);
            System.out.println("collection size is " + s_filteredSerialTrxns.size());
        } catch (Exception e) {
            throw new RuntimeException("My Code Error " + e);
        }
    }

    public void filterLots(Integer itemId) {
        try {
            System.out.println("inside filter code");
            s_filteredLotTrxns.clear();

            HashMap filterFileds = new HashMap();
            String trxnType = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.ParentPage}");
            filterFileds.put("trxnid", itemId);
            filterFileds.put("trxtype", trxnType);


            HashMap paramMap = new HashMap();
            paramMap.put("collection", s_lotTrxns);
            paramMap.put("filterFieldsValues", filterFileds);
            System.out.println("called super filtered class");
            SyncUtils sync = new SyncUtils();
            if (s_lotTrxns.size() > 0)
                s_filteredLotTrxns = (List) sync.getFileteredCollection(LotBO.class, paramMap);
            System.out.println("collection size is " + s_filteredLotTrxns.size());
        } catch (Exception e) {
            throw new RuntimeException("My Code Error " + e);
        }
    }

    public void filterItems() {
        try {
            System.out.println("inside filter code");
            s_filteredItemTxn.clear();

            HashMap filterFileds = new HashMap();
            ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.LpnTrxnId}", int.class);

            filterFileds.put("trxnid", (Integer) ve.getValue(AdfmfJavaUtilities.getAdfELContext()));
            ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.CallingPage}", String.class);
            filterFileds.put("trxtype", (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext()));

            HashMap paramMap = new HashMap();
            paramMap.put("collection", s_itemTrxns);
            paramMap.put("filterFieldsValues", filterFileds);
            System.out.println("called super filtered class");
            if (s_itemTrxns.size() > 0)
                s_filteredItemTxn = super.getFileteredCollection(ItemTxnBO.class, paramMap);
            System.out.println("collection size is " + s_filteredItemTxn.size());
        } catch (Exception e) {
            throw new RuntimeException("My Code Error " + e);
        }
    }
}
