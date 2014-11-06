package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.base.TransactionHeader;
import dcom.shop.application.database.ConnectionFactory;
import dcom.shop.application.mobile.WarehouseBO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.exception.AdfInvocationException;
import oracle.adfmf.util.GenericType;
import oracle.adfmf.util.GenericVirtualType;
import oracle.adfmf.util.Utility;

public class WarehouseDC extends SyncUtils {
    

    public WarehouseDC() {
        super();
    }

    public WarehouseBO[] getWarehouses() {
        
        try{
        WarehouseBO[] warehouses = null;
        warehouses = (WarehouseBO[]) super.getCollection(WarehouseBO.class,"WarehouseLOV_WS","process");
            return warehouses;
        }
        catch(Exception e){
            throw new RuntimeException("Called Error "+e);
        }
        /*
         * String networkStatus =
            (String) AdfmfJavaUtilities.evaluateELExpression("#{deviceScope.hardware.networkStatus}");

        if (networkStatus.equals(NOT_REACHABLE)) {
            System.out.println("In not reachable");
            warehouses = getWarehousesFromDB();
        } else {
            System.out.println("Getting from Warehouse web service");
            warehouses = getWarehousesFromWS();
            updateWhseSqlLiteTable(warehouses);
        }
*/
       

    }

    public WarehouseBO[] getWarehousesOF() {
        WarehouseBO[] warehouses = null;
        warehouses = getWarehousesFromDB();
        return warehouses;

    }

    private WarehouseBO[] getWarehousesFromDB() {
        Connection conn = null;
        List returnValue = new ArrayList();

        try {
            conn = ConnectionFactory.getConnection();

            Statement stmt = conn.createStatement();
            ResultSet result =
                stmt.executeQuery("SELECT WHSE, NAME, LINE_1, LINE_2, CITY, STATE, ZIP, COUNTRY, LOCATOR_CONTROL, IS_WMS, ATTRIBUTE1, ATTRIBUTE2 FROM WAREHOUSE;");
            while (result.next()) {
                WarehouseBO warehouse = new WarehouseBO();
                Utility.ApplicationLogger.severe("Warehouse: " + result.getString("WHSE"));

                warehouse.setWhse(result.getString("WHSE"));
                //warehouse.setAttribute1(result.getString("ATTRIBUTE1"));
                //warehouse.setAttribute2(result.getString("ATTRIBUTE2"));
                warehouse.setLine1(result.getString("LINE_1"));
                warehouse.setLine2(result.getString("LINE_2"));
                warehouse.setCity(result.getString("CITY"));
                warehouse.setState(result.getString("STATE"));
                warehouse.setCountry(result.getString("COUNTRY"));
                warehouse.setName(result.getString("NAME"));
                warehouse.setZip(result.getString("ZIP"));
                warehouse.setLocatorControl(result.getString("LOCATOR_CONTROL"));
                warehouse.setIsWMS(result.getString("IS_WMS"));

                returnValue.add(warehouse);
            }

        } catch (Exception ex) {
            Utility.ApplicationLogger.severe(ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        Collections.sort(returnValue);
        return (WarehouseBO[]) returnValue.toArray(new WarehouseBO[returnValue.size()]);
    }


    private WarehouseBO[] getWarehousesFromWS() {
        System.out.println("Inside getWarehousesFromWS");
        GenericVirtualType payload = new GenericVirtualType(null, "payload");


        //Fetching Transaction header
        GenericVirtualType transactionHeader = getTransactionHeader();
        payload.defineAttribute(null, "TransactionHeader", GenericType.class, transactionHeader);

        List pnames = new ArrayList();
        List pvals = new ArrayList();
        List ptypes = new ArrayList();

        pnames.add("payload");
        ptypes.add(GenericType.class);
        pvals.add(payload);
        System.out.println("Before Webservice call");
        try {
            GenericType genericReturnValue =
                (GenericType) AdfmfJavaUtilities.invokeDataControlMethod("WarehouseLOV_WS", null, "process", pnames,
                                                                         pvals, ptypes);
            System.out.println("result attribute count" + genericReturnValue.getAttributeCount());
            GenericType whseListType = (GenericType) genericReturnValue.getAttribute(1);
            System.out.println("whseListType attribute count" + whseListType.getAttributeCount());
            WarehouseBO[] returnValue = new WarehouseBO[whseListType.getAttributeCount()];

            for (int i = 0; i < whseListType.getAttributeCount(); i++) {
                GenericType whseType = (GenericType) whseListType.getAttribute(i);
                WarehouseBO whseObject = new WarehouseBO();
                for (int j = 0; j < 10; j++) {
                    GenericType x = (GenericType) whseType.getAttribute(j);
                    switch (j) {
                    case 0:
                        whseObject.setWhse("" + x.getAttribute(0));
                        break;
                    case 1:
                        whseObject.setName("" + x.getAttribute(0));
                        break;
                    case 2:
                        whseObject.setLine1("" + x.getAttribute(0));
                        break;
                    case 3:
                        whseObject.setLine2("" + x.getAttribute(0));
                        break;
                    case 4:
                        whseObject.setCity("" + x.getAttribute(0));
                        break;
                    case 5:
                        whseObject.setState("" + x.getAttribute(0));
                        break;
                    case 6:
                        whseObject.setZip("" + x.getAttribute(0));
                        break;
                    case 7:
                        whseObject.setCountry("" + x.getAttribute(0));
                        break;
                    case 8:
                        whseObject.setLocatorControl("" + x.getAttribute(0));
                        break;
                    case 9:
                        whseObject.setIsWMS("" + x.getAttribute(0));
                        break;
                    }

                }
                returnValue[i] = whseObject;
            }
            System.out.println("Warehouse array length" + returnValue.length);
            Arrays.sort(returnValue);
            return returnValue;

        } catch (AdfInvocationException aie) {
            if (AdfInvocationException.CATEGORY_WEBSERVICE.compareTo(aie.getErrorCategory()) == 0) {
                throw new RuntimeException("Error with the server. Please try later", aie);
            } else {
                throw new RuntimeException(aie);

            }
        }
    }

    private void updateWhseSqlLiteTable(WarehouseBO[] warehouses) {
        Connection conn = null;
        List returnValue = new ArrayList();

        try {
            conn = ConnectionFactory.getConnection();

            Statement stmt = conn.createStatement();
            stmt.executeQuery("DELETE FROM WAREHOUSE;");
            for (int i = 0; i < warehouses.length; i++) {
                stmt = conn.createStatement();
                StringBuffer query = new StringBuffer();
                query.append("INSERT INTO WAREHOUSE (WHSE,NAME, LINE_1,LINE_2,CITY,STATE,ZIP,COUNTRY,LOCATOR_CONTROL,IS_WMS,ATTRIBUTE1,ATTRIBUTE2) VALUES (");
                query.append("'" + warehouses[i].getWhse() + "', ");
                query.append("'" + warehouses[i].getName() + "', ");
                query.append("'" + warehouses[i].getLine1() + "', ");
                query.append("'" + warehouses[i].getLine2() + "', ");
                query.append("'" + warehouses[i].getCity() + "', ");
                query.append("'" + warehouses[i].getState() + "', ");
                query.append("'" + warehouses[i].getZip() + "', ");
                query.append("'" + warehouses[i].getCountry() + "', ");
                query.append("'" + warehouses[i].getLocatorControl() + "', ");
                query.append("'" + warehouses[i].getIsWMS() + "', ");
                //query.append("'" + warehouses[i].getAttribute1() + "', ");
                //query.append("'" + warehouses[i].getAttribute2() + "'); ");
                System.out.println("INSERT Query:" + query.toString());
                stmt.executeQuery(query.toString());
                System.out.println("Inserted in to query");
            }
        } catch (Exception ex) {
            Utility.ApplicationLogger.severe(ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }


}

