package dcom.shop.application.base;

import dcom.shop.application.database.ConnectionFactory;
import dcom.shop.application.mobile.WarehouseBO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.GenericTypeBeanSerializationHelper;
import oracle.adfmf.util.GenericType;
import oracle.adfmf.util.GenericVirtualType;
import oracle.adfmf.util.Utility;

public abstract class SyncUtils {
    private static final String NOT_REACHABLE = "NotReachable"; // Indiates no network connectivity

    protected static GenericVirtualType getTransactionHeader() {
        GenericVirtualType transactionHeader = new GenericVirtualType(null, "TransactionHeader");
        transactionHeader.defineAttribute(null, "CallingInterfaceName", String.class, "1");
        transactionHeader.defineAttribute(null, "CallingSystemName", String.class, "1");
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date lastSyncDate = new Date();
        String date = DATE_FORMAT.format(lastSyncDate);
        transactionHeader.defineAttribute(null, "LastSyncDateTime", Object.class, date);
        transactionHeader.defineAttribute(null, "RoleName", String.class, "1");
        transactionHeader.defineAttribute(null, "UserIdentifier", String.class, "1");

        //Fetching Status
        GenericVirtualType status = new GenericVirtualType(null, "Status");
        status.defineAttribute(null, "Code", String.class, "1");
        status.defineAttribute(null, "Msg", String.class, "1");

        transactionHeader.defineAttribute(null, "Status", GenericType.class, status);

        return transactionHeader;
    }

    //protected abstract boolean syncCollection();

   // protected Object[] getCollection() {
   //     return null;
   // }

    protected Object[] getCollection(Class collectionClass,String lovDCName,String opeartionName) {
        String networkStatus =
            (String) AdfmfJavaUtilities.evaluateELExpression("#{deviceScope.hardware.networkStatus}");
        Object[] collections;
        if (networkStatus.equals(NOT_REACHABLE)) {
            System.out.println("In not reachable");
            collections = getCollectionFromDB(collectionClass);
        } else {
            System.out.println("Getting from Warehouse web service");
            collections = getCollectionFromWS(collectionClass,lovDCName,opeartionName);
            updateSqlLiteTable(collectionClass, collections);
        }
        return collections;
    }

    private Object[] getCollectionFromDB(Class collectionClass) {
        Connection conn = null;
        List returnValue = new ArrayList();

        try {
            conn = ConnectionFactory.getConnection();

            Statement stmt = conn.createStatement();
            ResultSet result;
            StringBuffer query= new StringBuffer();
            query.append("SELECT ");
            Field[] fields =collectionClass.getFields();
            for(int i=0; i<fields.length;i++){
                
            }
/*           stmt.executeQuery("SELECT WHSE, NAME, LINE_1, LINE_2, CITY, STATE, ZIP, COUNTRY, LOCATOR_CONTROL, IS_WMS, ATTRIBUTE1, ATTRIBUTE2 FROM WAREHOUSE;");
            while (result.next()) {
                WarehouseBO warehouse = new WarehouseBO();
                Utility.ApplicationLogger.severe("Warehouse: " + result.getString("WHSE"));

                warehouse.setWhse(result.getString("WHSE"));
                warehouse.setAttribute1(result.getString("ATTRIBUTE1"));
                warehouse.setAttribute2(result.getString("ATTRIBUTE2"));
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
*/
        } catch (Exception ex) {
            Utility.ApplicationLogger.severe(ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        Collections.sort(returnValue);
        return (WarehouseBO[]) returnValue.toArray(new WarehouseBO[returnValue.size()]);
    }

    private Object[] getCollectionFromWS(Class collectionClass,String lovDCName,String opeartionName) {
        System.out.println("Inside getWarehousesFromWS");
                GenericVirtualType payload = new GenericVirtualType(null, "payload");
         
         
                //Fetching Transaction header
                GenericVirtualType transactionHeader = getTransactionHeader();
                payload.defineAttribute(null, "TransactionHeader", GenericType.class, transactionHeader);
         
                List pnames = new ArrayList();
                List pvals = new ArrayList();
                List ptypes = new ArrayList();
                List collection = new ArrayList();
         
                pnames.add("payload");
                ptypes.add(GenericType.class);
                pvals.add(payload);
                System.out.println("Before Webservice call");
                try {
                    GenericType genericReturnValue =
                        (GenericType) AdfmfJavaUtilities.invokeDataControlMethod(lovDCName, null, opeartionName, pnames,
                                                                                 pvals, ptypes);
                    GenericType respListType = (GenericType) genericReturnValue.getAttribute(1);
                    
                    for (int i = 0; i < respListType.getAttributeCount(); i++) {
                        // Get each individual GenericType instance that holds the attribute key-value pairs of department
        //                GenericType entityGenericType = (GenericType) whseListType.getAttribute(i);
                        // Now create Department instance out of this GenericType
                        // this works fine if payload attr names match department attr names
                        Object obj=collectionClass.newInstance();
                        obj =GenericTypeBeanSerializationHelper.fromGenericType(collectionClass,respListType);
                        collection.add(obj);
                    }
        //            Arrays.sort(warehouse);
                    return collection.toArray(new Object[collection.size()]);
         
                } catch (Exception aie) {
        //            if (AdfInvocationException.CATEGORY_WEBSERVICE.compareTo(aie.getErrorCategory()) == 0) {
        //                throw new RuntimeException("Error with the server. Please try later", aie);
        //            } else {
                        throw new RuntimeException("error here "+aie);
         
        //            }
                }
    }

    private void updateSqlLiteTable(Class collectionClass, Object[] collections) {
    }
}
