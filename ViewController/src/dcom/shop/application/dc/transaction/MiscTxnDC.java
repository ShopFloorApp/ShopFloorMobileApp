package dcom.shop.application.dc.transaction;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.database.ConnectionFactory;
import dcom.shop.application.mobile.transaction.MiscTxnBO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.el.ValueExpression;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.Utility;
import oracle.adfmf.util.logging.Trace;

public class MiscTxnDC extends SyncUtils {
    public MiscTxnDC() {
        try {
            filtered_MiscTxn = super.getOfflineCollection(MiscTxnBO.class);
            if (s_misctxns == null) {
                s_misctxns = new ArrayList<MiscTxnBO>();
                s_misctxns.clear();
                this.getMiscTrxns();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List filtered_MiscTxn = new ArrayList();
    protected static List<MiscTxnBO> s_misctxns = null;
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);


    public void refresh() {
        providerChangeSupport.fireProviderRefresh("miscTrxns");
    }

    public synchronized List<MiscTxnBO> getMiscTrxns() {
        MiscTxnBO SubInventoryTxnBO[] = null;
        MiscTxnBO subinventory = null;
        s_misctxns.clear();
        s_misctxns = selectMiscTrxns(s_misctxns);
        int trxnId = s_misctxns.size();
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.SubinvTrxnId}", int.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), trxnId);
        if (trxnId == 0) {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.MiscTrxnResults}", "No transaction to review");
        } else {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.MiscTrxnResults}", "");
        }
        return s_misctxns;
    }

    public static List<MiscTxnBO> selectMiscTrxns(List<MiscTxnBO> misctxns) {

        //   ArrayList stocks = new ArrayList();

        int recordCount = 0;
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM MISCTXN ORDER BY TRXNID");

            while (rs.next()) {
                recordCount++;
            }

            rs.beforeFirst();

            while (rs.next()) {
                int trxnid = rs.getInt(1);
                String fromSubinv = rs.getString(2);
                String fromLoc = rs.getString(3);
                String itemNumber = rs.getString(4);
                String itemName = rs.getString(5);
                String qty = rs.getString(6);
                String trxntime = rs.getString(7);
                String completeFlag = rs.getString(8);
                String trxType = rs.getString(9);
                String accountAlias = rs.getString(10);
                String status = rs.getString(11);
                String txnuom = rs.getString(12);
                String serialControl = rs.getString(13);
                String lotControl = rs.getString(14);

                if (misctxns != null && misctxns.size() >= recordCount) {
                    MiscTxnBO miscTxn = (MiscTxnBO) misctxns.get(trxnid);
                    miscTxn.setTrxnId(trxnid);
                    miscTxn.setFromInventory(fromSubinv);
                    miscTxn.setLocator(fromLoc);
                    miscTxn.setItemNumber(itemNumber);
                    miscTxn.setItemName(itemName);
                    miscTxn.setQuantity(qty);
                    miscTxn.setTxnTime(trxntime);
                    miscTxn.setCompleteFlag(completeFlag);
                    miscTxn.setTrxType(trxType);
                    miscTxn.setStatus(status);
                    miscTxn.setAccountAlias(accountAlias);
                    miscTxn.setTxnUom(txnuom);
                    miscTxn.setSerialControl(serialControl);
                    miscTxn.setLotControl(lotControl);
                } else {
                    MiscTxnBO subinv =
                        new MiscTxnBO(trxnid, fromSubinv, itemNumber, itemName, qty, trxntime, completeFlag,
                                      fromLoc ,status , trxType, accountAlias, txnuom, serialControl, lotControl);
                      
                    misctxns.add(subinv);
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
        return misctxns;
    }

    public MiscTxnBO[] getMiscTxn() {

        try {
            System.out.println("called collection subinv txn");
            MiscTxnBO[] getMiscTxn = null;
            getMiscTxn = (MiscTxnBO[]) filtered_MiscTxn.toArray(new MiscTxnBO[filtered_MiscTxn.size()]);
            return getMiscTxn;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void addProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.addProviderChangeListener(l);
    }

    public void removeProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.removeProviderChangeListener(l);
    }


}
