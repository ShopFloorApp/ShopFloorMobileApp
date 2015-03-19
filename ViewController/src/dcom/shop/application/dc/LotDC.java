package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.database.ConnectionFactory;
import dcom.shop.application.mobile.LotBO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.el.ValueExpression;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.exception.AdfException;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.Utility;
import oracle.adfmf.util.logging.Trace;

public class LotDC extends SyncUtils{
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    protected static List s_lots = new ArrayList();
    protected static List s_insertLots = new ArrayList();
    private List filtered_lots = new ArrayList();
    protected static int qtyEntered = 0;
    protected static List<LotBO> s_dbLots = null;
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private String lotNo;

    public void setLotQty(int lotQty) {
        int oldLotQty = this.lotQty;
        this.lotQty = lotQty;
        propertyChangeSupport.firePropertyChange("lotQty", oldLotQty, lotQty);
    }

    public int getLotQty() {
        return lotQty;
    }
    private int lotQty;
    public LotDC() {
        super();
        if (s_dbLots == null) {
            s_dbLots = new ArrayList<LotBO>();
            s_dbLots.clear();
            this.getLots();
        }
    }

    public void setDbLots(List<LotBO> s_dbLots) {
        List<LotBO> oldDbLots = LotDC.s_dbLots;
        LotDC.s_dbLots = s_dbLots;
        propertyChangeSupport.firePropertyChange("dbLots", oldDbLots, s_dbLots);
    }

    public static List<LotBO> getDbLots() {
        return s_dbLots;
    }

    public void setLotNo(String lotNo) {
        String oldLotNo = this.lotNo;
        this.lotNo = lotNo;
        propertyChangeSupport.firePropertyChange("lotNo", oldLotNo, lotNo);
    }

    public String getLotNo() {
        return lotNo;
    }
    public LotBO[] getLots() {

        try {
            s_lots.clear();
            LotBO[] lots = null;
            s_lots = super.getOfflineCollection(LotBO.class);
            filterLots();
            lots = (LotBO[]) filtered_lots.toArray(new LotBO[filtered_lots.size()]);
            return lots;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<LotBO> selectLots(List<LotBO> lots) {

         int recordCount = 0;
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM LOT ORDER BY LOT_ID");

            while (rs.next()) {
                recordCount++;
            }

            rs.beforeFirst();

            while (rs.next()) {
                int lotId = rs.getInt(1);
                int trxnId = rs.getInt(2);
                String lotNo = rs.getString(3);
                int lotQty = rs.getInt(4);
                if (lots != null && lots.size() >= recordCount) {
                    LotBO lot = (LotBO) lots.get(lotId);
                    lot.setLotId(lotId);
                    lot.setLotNo(lotNo);
                    lot.setLotQty(lotQty);
                    lot.setTrxnId(trxnId);
                } else {
                    LotBO lot = new LotBO(lotId, trxnId, lotNo, lotQty);
                    lots.add(lot);
                }
            }

        } catch (SQLException e) {
            Trace.log(Utility.ApplicationLogger, Level.SEVERE, LotDC.class, "lots",
                      "##############SQL Exception:  " + e.getMessage());
            e.printStackTrace();
        } catch (Exception exception) {
            Trace.log(Utility.ApplicationLogger, Level.SEVERE, LotDC.class, "lots",
                      "##############Exception:  " + exception.getMessage());

        }

        return lots;
    }

    public void insertLots(String trxnId, String lotNo, int lotQty) {
        s_insertLots.clear();
        qtyEntered = qtyEntered + lotQty;
        Map pageFlow = (Map) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope}");
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.LotQty}", String.class);
        int totalQty = Integer.parseInt(ve.getValue(AdfmfJavaUtilities.getAdfELContext()).toString().trim());
        int diff = totalQty - qtyEntered;
        if (diff < 0) {
            qtyEntered = qtyEntered - lotQty;
            throw new AdfException("Lot quantity exceeds the total Qty", AdfException.ERROR);

        }
        LotBO lot = new LotBO();
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.SubinvTrxnId}", int.class);
        int lotId = s_dbLots.size();
        lot.setLotId(lotId);
        lot.setTrxnId((Integer) ve.getValue(AdfmfJavaUtilities.getAdfELContext()));
        lot.setLotNo(lotNo);
        lot.setLotQty(lotQty);
        s_insertLots.add(lot);
        boolean insertStatus = super.insertSqlLiteTable(LotBO.class, s_insertLots);
        if(insertStatus){
         s_dbLots.add(lot);
        }
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.qtyLotEntered}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), qtyEntered);
        this.setLotNo("");
        this.setLotQty(0);
        providerChangeSupport.fireProviderRefresh("lots");
    }

    public void deleteRecord(int lotId, int lotQty) {
        Connection conn = null;
        try {
            conn = ConnectionFactory.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeQuery("DELETE FROM LOT WHERE LOTID = " + lotId + ";");
            qtyEntered = qtyEntered - lotQty;
            ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.qtyLotEntered}", String.class);
            ve.setValue(AdfmfJavaUtilities.getAdfELContext(), qtyEntered);
            providerChangeSupport.fireProviderRefresh("lots");
        } catch (Exception ex) {
            Utility.ApplicationLogger.severe(ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    @SuppressWarnings("unchecked")
    public void filterLots() {
        try {
            System.out.println("inside filter code");
            filtered_lots.clear();

            HashMap filterFileds = new HashMap();
            ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.SubinvTrxnId}", int.class);

            filterFileds.put("trxnid", (Integer) ve.getValue(AdfmfJavaUtilities.getAdfELContext()));


            HashMap paramMap = new HashMap();
            paramMap.put("collection", s_lots);
            paramMap.put("filterFieldsValues", filterFileds);
            System.out.println("called super filtered class");

            filtered_lots = (List) super.getFileteredCollection(LotBO.class, paramMap);
            System.out.println("collection size is " + filtered_lots.size());
        } catch (Exception e) {
            throw new RuntimeException("My Code Error " + e);
        }
    }

    public void refresh() {
        providerChangeSupport.fireProviderRefresh("lots");
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
