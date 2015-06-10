package dcom.shop.application.dc.transaction;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.database.ConnectionFactory;
import dcom.shop.application.dc.SerialDC;
import dcom.shop.application.mobile.SerialBO;

import dcom.shop.application.mobile.transaction.ItemTxnBO;

import dcom.shop.application.mobile.transaction.LpnTxnBO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.ParseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.logging.Level;

import javax.el.ValueExpression;

import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.Utility;
import oracle.adfmf.util.logging.Trace;

public class ItemTxnDC extends SyncUtils {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);

    public ItemTxnDC() {
        super();

        refreshTrxnId();
    }
    protected static List s_items = new ArrayList();
    protected static List s_insertItems = new ArrayList();
    protected static List<LpnTxnBO> s_lpnTrxns = new ArrayList<LpnTxnBO>();
    protected static List<ItemTxnBO> s_dbItems = new ArrayList();

    public void refreshTrxnId() {
        s_dbItems.clear();
        s_dbItems = super.getCollectionFromDB(ItemTxnBO.class);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.SubinvTrxnId}", s_dbItems.size());
    }

    public ItemTxnBO[] getItems() {

        try {
            s_items.clear();
            ItemTxnBO[] items = null;
            s_lpnTrxns = super.getCollectionFromDB(LpnTxnBO.class);
            ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.LpnTrxnId}", int.class);
            ve.setValue(AdfmfJavaUtilities.getAdfELContext(), s_lpnTrxns.size());


            Integer trxnId = (Integer) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.LpnTrxnId}");
            String trxnType = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.CallingPage}");

            String whereClause = " WHERE TRXNID = " + trxnId + " AND TRXTYPE = \"" + trxnType + "\"";
            s_items = super.getFilteredCollectionFromDB(ItemTxnBO.class, whereClause);
            items = (ItemTxnBO[]) s_items.toArray(new ItemTxnBO[s_items.size()]);
            return items;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*public static List<ItemTxnBO> selectItems(List<ItemTxnBO> items) {

        //   ArrayList serials = new ArrayList();

        int recordCount = 0;
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ITEMTXN");

            while (rs.next()) {
                recordCount++;
            }

            rs.beforeFirst();

            while (rs.next()) {
                int itemId = rs.getInt(1);
                int trxnId = rs.getInt(2);
                String itemNumber = rs.getString(3);
                String itemName = rs.getString(4);
                String uom = rs.getString(5);
                int itemQty = rs.getInt(6);
                String trxType = rs.getString(7);
                String serialControl = rs.getString(8);
                String lotControl = rs.getString(9);
                if (items != null && items.size() >= recordCount) {
                    ItemTxnBO item = (ItemTxnBO) items.get(itemId);
                    item.setItemId(itemId);
                    item.setItemName(itemName);
                    item.setItemNumber(itemNumber);
                    item.setUom(uom);
                    item.setQuantity(itemQty);
                    item.setTrxnId(trxnId);
                    item.setTrxType(trxType);
                    item.setSerialControl(serialControl);
                    item.setLotControl(lotControl);
                } else {
                    ItemTxnBO item = new ItemTxnBO(trxnId, itemId, itemNumber, itemName, uom,itemQty, trxType,serialControl,lotControl);
                    items.add(item);
                }
            }

        } catch (SQLException e) {
            Trace.log(Utility.ApplicationLogger, Level.SEVERE, ItemTxnDC.class, "items",
                      "##############SQL Exception:  " + e.getMessage());
            e.printStackTrace();
        } catch (Exception exception) {
            Trace.log(Utility.ApplicationLogger, Level.SEVERE, ItemTxnDC.class, "items",
                      "##############Exception:  " + exception.getMessage());

        }

        return items;
    }


    public void filterItems() {
        try {
            System.out.println("inside filter code");
            s_filteredItems.clear();

            HashMap filterFileds = new HashMap();
            ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.LpnTrxnId}", int.class);

            filterFileds.put("trxnid", (Integer) ve.getValue(AdfmfJavaUtilities.getAdfELContext()));
            ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.ParentPage}", String.class);
            filterFileds.put("trxtype", (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext()));

            HashMap paramMap = new HashMap();
            paramMap.put("collection", s_items);
            paramMap.put("filterFieldsValues", filterFileds);
            System.out.println("called super filtered class");

            s_filteredItems = (List) super.getFileteredCollection(ItemTxnBO.class, paramMap);
            System.out.println("collection size is " + s_filteredItems.size());
        } catch (Exception e) {
            throw new RuntimeException("My Code Error " + e);
        }
    }*/

    public void InsertItems() {
        s_insertItems.clear();
        String networkStatus =
            (String) AdfmfJavaUtilities.evaluateELExpression("#{deviceScope.hardware.networkStatus}");
        ItemTxnBO item = new ItemTxnBO();
        String itemEnable = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.ItemEnable}");
        boolean result = Boolean.valueOf(itemEnable);
        if (result) {
            int itemId = (Integer) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.SubinvTrxnId}");
            int trxnId = (Integer) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.LpnTrxnId}");
            String itemNumber = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.searchKeyword}");
            if ("".equals(itemNumber) || itemNumber == null)
                AdfmfJavaUtilities.setELValue("#{pageFlowScope.errMsg}", "Please Enter Item Number!");
         //       AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
           //                                                               "showAlert", new Object[] {
             //                                                             "Failure", "Please Enter Item Number!", "ok"
               // });
            else {
                String itemName = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.description}");
                String uom = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.uom}");
                String qty = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.quantity}");
                try{
                
                if ("".equals(qty) || qty == null)
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.errMsg}", "Please Enter Quantity!");
               //     AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                 //                                                             "showAlert", new Object[] {
                  //                                                            "Failure", "Please Enter Quantity!", "ok"
                   // });
                else {
                    Integer.parseInt(qty);
                    
                    Integer quantity =
                        (Integer) Integer.parseInt((String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.quantity}"));
                    
                    String trxType = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.CallingPage}");
                    String serialControl =
                        (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.SerialControl}");
                    String lotControl = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.LotControl}");

                    item.setItemId(itemId);
                    item.setTrxnId(trxnId);
                    item.setItemNumber(itemNumber);
                    item.setItemName(itemName);
                    item.setUom(uom);
                    item.setQuantity(quantity);
                    item.setTrxType(trxType);
                    item.setSerialControl(serialControl);
                    item.setLotControl(lotControl);

                    s_insertItems.add(item);
                    boolean insertStatus = super.insertSqlLiteTable(ItemTxnBO.class, s_insertItems);
                    if (insertStatus) {
                        clearItems();
                        refreshTrxnId();

                    }

                    providerChangeSupport.fireProviderRefresh("items");
                }
            }catch(Exception e) {
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.errMsg}", "Please Enter Quantity in numbers only!");
                }
                }
        }

    }

    public void clearItems() {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchKeyword}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.quantity}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.availableQty}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.description}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.uom}", null);
    }

    public void refresh() {
        providerChangeSupport.fireProviderRefresh("items");
    }

    public void addProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.addProviderChangeListener(l);
    }

    public void removeProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.removeProviderChangeListener(l);
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }

}
