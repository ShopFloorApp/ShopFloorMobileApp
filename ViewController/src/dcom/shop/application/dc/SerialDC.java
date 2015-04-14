package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.database.ConnectionFactory;
import dcom.shop.application.mobile.LotBO;
import dcom.shop.application.mobile.SerialBO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

public class SerialDC extends SyncUtils {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public SerialDC() {
        super();
        if (s_dbSerials == null) {
            s_dbSerials = new ArrayList<SerialBO>();
            s_dbSerials.clear();
            this.getSerials();
        }
    }

    protected static List s_serials = new ArrayList();
    protected static List s_insertSerials = new ArrayList();
    private List filtered_serials = new ArrayList();
    protected static int qtyEntered = 0;
    protected static List<SerialBO> s_dbSerials = null;

    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private String fromSerial;

    public void setFromSerial(String fromSerial) {
        String oldFromSerial = this.fromSerial;
        this.fromSerial = fromSerial;
        propertyChangeSupport.firePropertyChange("fromSerial", oldFromSerial, fromSerial);
    }

    public String getFromSerial() {
        return fromSerial;
    }

    public void setToSerial(String toSerial) {
        String oldToSerial = this.toSerial;
        this.toSerial = toSerial;
        propertyChangeSupport.firePropertyChange("toSerial", oldToSerial, toSerial);
    }

    public String getToSerial() {
        return toSerial;
    }

    public void setFromSerialRange(String fromSerialRange) {
        String oldFromSerialRange = this.fromSerialRange;
        this.fromSerialRange = fromSerialRange;
        propertyChangeSupport.firePropertyChange("fromSerialRange", oldFromSerialRange, fromSerialRange);
    }

    public String getFromSerialRange() {
        return fromSerialRange;
    }
    private String toSerial;
    private String fromSerialRange;

    public synchronized List<SerialBO> getDbSerials() {
        SerialBO s[] = null;
        SerialBO serail = null;
        s_dbSerials = selectSerials(s_dbSerials);
        return s_dbSerials;
    }

    public SerialBO[] getSerials() {

        try {
            s_serials.clear();
            Map map = (Map) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope}");
            SerialBO[] serials = null;
            s_serials = super.getOfflineCollection(SerialBO.class);
            s_dbSerials = super.getOfflineCollection(SerialBO.class);
            
            filterSerials();
            serials = (SerialBO[]) filtered_serials.toArray(new SerialBO[filtered_serials.size()]);
            ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.qtyEntered}", String.class);
            Iterator j = filtered_serials.iterator();
            SerialBO serial = new SerialBO();
            int qtyEntered = 0;
            while (j.hasNext()) {
                serial = (SerialBO) j.next();
                qtyEntered = qtyEntered + serial.getSerialQty();

            }
            ve.setValue(AdfmfJavaUtilities.getAdfELContext(), qtyEntered);
            this.qtyEntered = qtyEntered;
            return serials;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<SerialBO> selectSerials(List<SerialBO> serials) {

        //   ArrayList serials = new ArrayList();

        int recordCount = 0;
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM SERIAL ORDER BY SERIAL_ID");

            while (rs.next()) {
                recordCount++;
            }

            rs.beforeFirst();

            while (rs.next()) {
                int serialId = rs.getInt(1);
                int trxnId = rs.getInt(2);
                String fromSerial = rs.getString(3);
                String toSerial = rs.getString(4);
                int serialQty = rs.getInt(5);
                String trxType = rs.getString(6);
                if (serials != null && serials.size() >= recordCount) {
                    SerialBO serial = (SerialBO) serials.get(serialId);
                    serial.setSerialId(serialId);
                    serial.setFromSerial(fromSerial);
                    serial.setToSerial(toSerial);
                    serial.setSerialQty(serialQty);
                    serial.setTrxnId(trxnId);
                    serial.setTrxType(trxType);
                } else {
                    SerialBO serial = new SerialBO(serialId, trxnId, fromSerial, toSerial, serialQty,trxType);
                    serials.add(serial);
                }
            }

        } catch (SQLException e) {
            Trace.log(Utility.ApplicationLogger, Level.SEVERE, SerialDC.class, "serials",
                      "##############SQL Exception:  " + e.getMessage());
            e.printStackTrace();
        } catch (Exception exception) {
            Trace.log(Utility.ApplicationLogger, Level.SEVERE, SerialDC.class, "serials",
                      "##############Exception:  " + exception.getMessage());

        }

        return serials;
    }

    public void insertSerials(String trxnId, String fromSerial, String toSerial, String trxType) {
        s_insertSerials.clear();
        long qty = Long.parseLong(toSerial) - Long.parseLong(fromSerial) + 1L;
        qtyEntered = qtyEntered +  Integer.parseInt(Long.toString(qty));
        Map pageFlow = (Map) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope}");
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.SerialQty}", String.class);
        int totalQty = Integer.parseInt(ve.getValue(AdfmfJavaUtilities.getAdfELContext()).toString().trim());
        int diff = totalQty - qtyEntered;
        if (diff < 0) {
            qtyEntered = qtyEntered -  Integer.parseInt(Long.toString(qty));;
            throw new AdfException("Serial quantity exceeds the total Qty", AdfException.ERROR);

        }
        SerialBO serial = new SerialBO();
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.SubinvTrxnId}", int.class);
        int serialId = s_dbSerials.size();
        serial.setSerialId(serialId);
        serial.setTrxnId((Integer) ve.getValue(AdfmfJavaUtilities.getAdfELContext()));
        serial.setFromSerial(fromSerial);
        serial.setToSerial(toSerial);
        serial.setSerialQty( Integer.parseInt(Long.toString(qty)));
        serial.setTrxType(trxType);
        
        s_insertSerials.add(serial);
        boolean insertStatus = super.insertSqlLiteTable(SerialBO.class, s_insertSerials);
        if(insertStatus){
         s_dbSerials.add(serial);
        }
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.qtyEntered}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), qtyEntered);
        this.setFromSerial("");
        this.setToSerial("");
        this.setFromSerialRange("");
        providerChangeSupport.fireProviderRefresh("serials");
    }

    public void deleteRecord(int serialId, int serialQty, String trxType) {
        Connection conn = null;
        try {
            conn = ConnectionFactory.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeQuery("DELETE FROM SERIAL WHERE SERIALID = " + serialId + " " +
                "AND TRXTYPE = \""+trxType+"\" ;");
            qtyEntered = qtyEntered - serialQty;
            ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.qtyEntered}", String.class);
            ve.setValue(AdfmfJavaUtilities.getAdfELContext(), qtyEntered);
            providerChangeSupport.fireProviderRefresh("serials");
        } catch (Exception ex) {
            Utility.ApplicationLogger.severe(ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    @SuppressWarnings("unchecked")
    public void filterSerials() {
        try {
            System.out.println("inside filter code");
            filtered_serials.clear();

            HashMap filterFileds = new HashMap();
            ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.SubinvTrxnId}", int.class);

            filterFileds.put("trxnid", (Integer) ve.getValue(AdfmfJavaUtilities.getAdfELContext()));
            ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.ParentPage}", String.class);
            filterFileds.put("trxtype", (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext()));
            
            HashMap paramMap = new HashMap();
            paramMap.put("collection", s_serials);
            paramMap.put("filterFieldsValues", filterFileds);
            System.out.println("called super filtered class");

            filtered_serials = (List) super.getFileteredCollection(SerialBO.class, paramMap);
            System.out.println("collection size is " + filtered_serials.size());
        } catch (Exception e) {
            throw new RuntimeException("My Code Error " + e);
        }
    }

    public void refresh() {
        providerChangeSupport.fireProviderRefresh("serials");
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
