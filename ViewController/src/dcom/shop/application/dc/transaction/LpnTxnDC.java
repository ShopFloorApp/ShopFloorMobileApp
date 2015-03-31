package dcom.shop.application.dc.transaction;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.database.ConnectionFactory;
import dcom.shop.application.mobile.transaction.LpnTxnBO;
import dcom.shop.application.mobile.transaction.MiscTxnBO;
import dcom.shop.application.mobile.transaction.SubInventoryTxnBO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;

import javax.el.ValueExpression;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.util.Utility;
import oracle.adfmf.util.logging.Trace;

public class LpnTxnDC extends SyncUtils {
    public LpnTxnDC() {
        try {
            filtered_LpnTxn = super.getOfflineCollection(LpnTxnBO.class);
            if (s_lpnTrxns == null) {
                s_lpnTrxns = new ArrayList<LpnTxnBO>();
                s_lpnTrxns.clear();
                this.getLpnTrxns();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    protected static List s_lpnTrxns = new ArrayList();
    private List filtered_LpnTxn = new ArrayList();

    public synchronized List<LpnTxnBO> getLpnTrxns() {
        MiscTxnBO SubInventoryTxnBO[] = null;
        MiscTxnBO subinventory = null;
        s_lpnTrxns = selectLpnTrxns(s_lpnTrxns);
        int trxnId = s_lpnTrxns.size();
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.LpnTrxnId}", int.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), trxnId);

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
                String itemNumber = rs.getString(6);
                String itemName = rs.getString(7);
                String qty = rs.getString(8);
                String trxntime = rs.getString(9);
                String orgCode = rs.getString(10);
                String trxType = rs.getString(11);
                String txnuom = rs.getString(12);
                String serialControl = rs.getString(13);
                String lotControl = rs.getString(14);

                if (lpntxns != null && lpntxns.size() >= recordCount) {
                    LpnTxnBO lpnTxn = (LpnTxnBO) lpntxns.get(trxnid);
                    lpnTxn.setTrxnId(trxnid);
                    lpnTxn.setSubinventory(fromSubinv);
                    lpnTxn.setLocator(fromLoc);
                    lpnTxn.setItemNumber(itemNumber);
                    lpnTxn.setItemName(itemName);
                    lpnTxn.setQuantity(qty);
                    lpnTxn.setTrxTime(trxntime);
                    lpnTxn.setLpnFrom(lpnFrom);
                    lpnTxn.setTrxType(trxType);
                    lpnTxn.setLpnTo(lpnTo);
                    lpnTxn.setOrgCode(orgCode);
                    lpnTxn.setTxnUom(txnuom);
                    lpnTxn.setSerialControl(serialControl);
                    lpnTxn.setLotControl(lotControl);
                } else {
                    LpnTxnBO lpn =
                        new LpnTxnBO(trxnid, fromSubinv, lpnFrom, lpnTo, qty, trxntime, orgCode, fromLoc, trxType,
                                     itemName, itemNumber, txnuom, serialControl, lotControl);
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

    public void InsertTransaction(String trxType) {
        String networkStatus =
            (String) AdfmfJavaUtilities.evaluateELExpression("#{deviceScope.hardware.networkStatus}");
        LpnTxnBO lpnTxn = new LpnTxnBO();


        Integer trxnId = (Integer) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.SubinvTrxnId}");
        String fromSubInv = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.FromSubinventory}");
        String fromLocator = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.FromLocator}");
        String item = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.searchKeyword}");
        String itemName = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.description}");
        String fromLpn = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.searchLpnKeyword}");
        String toLpn = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.toLpn}");
        String qty = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.quantity}");
        String uom = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.uom}");
        String serialControl = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.SerialControl}");
        String lotControl = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.LotControl}");
        lpnTxn.setTrxnId(trxnId);
        lpnTxn.setLpnFrom(fromLpn);
        lpnTxn.setItemNumber(item);
        lpnTxn.setItemName(itemName);
        lpnTxn.setQuantity(qty);
        lpnTxn.setSubinventory(fromSubInv);
        lpnTxn.setLocator(fromLocator);
        lpnTxn.setLpnTo(toLpn);
        lpnTxn.setSerialControl(serialControl);
        lpnTxn.setLotControl(lotControl);
        lpnTxn.setTxnUom(uom);
        lpnTxn.setTrxType(trxType);
        s_lpnTrxns.add(lpnTxn);
        SyncUtils syncUtils = new SyncUtils();
        syncUtils.insertSqlLiteTable(LpnTxnBO.class, s_lpnTrxns);
        //     ProcessWS(trxnId);

    }
}
