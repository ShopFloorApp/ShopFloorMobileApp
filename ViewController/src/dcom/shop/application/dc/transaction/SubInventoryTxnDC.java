package dcom.shop.application.dc.transaction;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.database.ConnectionFactory;
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
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.Utility;
import oracle.adfmf.util.logging.Trace;

public class SubInventoryTxnDC extends SyncUtils {
    private List filtered_SubInventoryTxn = new ArrayList();
    protected static List<SubInventoryTxnBO> s_subinvs = null;
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);

    public SubInventoryTxnDC() {
        try {
            filtered_SubInventoryTxn = super.getOfflineCollection(SubInventoryTxnBO.class);
            if (s_subinvs == null) {
                s_subinvs = new ArrayList<SubInventoryTxnBO>();
                s_subinvs.clear();
                this.getSubinventories();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void refresh() {
        providerChangeSupport.fireProviderRefresh("subinventories");
    }

    public synchronized List<SubInventoryTxnBO> getSubinventories() {
        SubInventoryTxnBO s[] = null;
        SubInventoryTxnBO subinventory = null;
        s_subinvs = selectSubinventories(s_subinvs);
        int trxnId = s_subinvs.size();
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.SubinvTrxnId}", int.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), trxnId);
        if (trxnId == 0){
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.SubInvTrxnResults}", "No transaction to review");
        }else{
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.SubInvTrxnResults}", "");
              }
        return s_subinvs;
    }

    public static List<SubInventoryTxnBO> selectSubinventories(List<SubInventoryTxnBO> subinvs) {

        //   ArrayList stocks = new ArrayList();

        int recordCount = 0;
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM SUBINVENTORYTXN ORDER BY TRXNID");

            while (rs.next()) {
                recordCount++;
            }

            rs.beforeFirst();

            while (rs.next()) {
                int trxnid = rs.getInt(1);
                String isNewEntity = rs.getString(2);
                String rowOperation = rs.getString(3);
                String fromSubinv = rs.getString(4);
                String toSubinv = rs.getString(5);
                String itemNumber = rs.getString(6);
                String itemName = rs.getString(7);
                String qty = rs.getString(8);
                String trxntime = rs.getString(9);
                String completeFlag = rs.getString(10);
                String lpn = rs.getString(11);
                String fromLoc = rs.getString(12);
                String toLoc = rs.getString(13);
                String status = rs.getString(14);
                String txnuom = rs.getString(15);

                if (subinvs != null && subinvs.size() >= recordCount) {
                    SubInventoryTxnBO subinv = (SubInventoryTxnBO) subinvs.get(trxnid);
                    subinv.setTrxnId(trxnid);
                    subinv.setIsNewEntity(isNewEntity);
                    subinv.setRowOperation(rowOperation);
                    subinv.setFromInventory(fromSubinv);
                    subinv.setToInventory(toSubinv);
                    subinv.setFromLoc(fromLoc);
                    subinv.setToLoc(toLoc);
                    subinv.setQuantity(qty);
                    subinv.setTxnTime(trxntime);
                    subinv.setStatus(status);
                    subinv.setItemName(itemName);
                    subinv.setItemNumber(itemNumber);
                    subinv.setLPN(lpn);
                    subinv.setCompleteFlag(completeFlag);
                    subinv.setRowOperation(rowOperation);
                    subinv.setTxnuom(txnuom);
                } else {
                    SubInventoryTxnBO subinv =
                        new SubInventoryTxnBO(trxnid, isNewEntity, rowOperation, fromSubinv, toSubinv, itemNumber,
                                              itemName, qty, trxntime, completeFlag, lpn, fromLoc, toLoc, status,txnuom);
                    subinvs.add(subinv);
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
        return subinvs;
    }

    public SubInventoryTxnBO[] getSubInventoryTxn() {

        try {
            System.out.println("called collection subinv txn");
            SubInventoryTxnBO[] getSubInventoryTxn = null;
            getSubInventoryTxn =
                (SubInventoryTxnBO[]) filtered_SubInventoryTxn.toArray(new SubInventoryTxnBO[filtered_SubInventoryTxn.size()]);
            return getSubInventoryTxn;
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
