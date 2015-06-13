package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.database.ConnectionFactory;
import dcom.shop.application.mobile.PackAndDirectShipBO;
import dcom.shop.application.mobile.transaction.PackTxnBO;
import dcom.shop.restURIDetails.RestCallerUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.el.ValueExpression;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.util.Utility;
import oracle.adfmf.util.logging.Trace;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PackAndDirectShipDC extends SyncUtils{
    public PackAndDirectShipDC() {
        super();
    }
    
    protected static List s_packAndDirectShip = new ArrayList();
    private static final String NOT_REACHABLE = "NotReachable"; // Indiates no network connectivity
    
    protected static List<PackTxnBO> s_packTxns = new ArrayList<PackTxnBO>();;
    
    public void syncLocalDB(){
            s_packAndDirectShip.clear();
            String networkStatus =
                (String) AdfmfJavaUtilities.evaluateELExpression("#{deviceScope.hardware.networkStatus}");
            List collections;
            if (networkStatus.equals(NOT_REACHABLE)) {
                s_packAndDirectShip = super.getCollectionFromDB(PackAndDirectShipBO.class);
            } else {
                System.out.println("Inside orgItem");
                Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
                String restURI = "/webservices/rest/DCOMLOV/getAccountAlias/";
                RestCallerUtil rcu = new RestCallerUtil();
                String payload =
                    "{\n" + "\"GET_SO_PER_ORG_Input\":\n" + "{\n" +
                    "\"@xmlns\": \"http://xmlns.oracle.com/apps/fnd/rest/GetSoPerOrgSvc/get_so_per_org/\",\n" +
                    "   \"RESTHeader\": {\"@xmlns\": \"http://xmlns.oracle.com/apps/fnd/rest/GetSoPerOrgSvc/header\",\n" +
                    "                  \"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" +
                    "                  \"RespApplication\": \"ONT\",\n" +
                    "                  \"SecurityGroup\": \"STANDARD\",\n" +
                    "                  \"NLSLanguage\": \"AMERICAN\",\n" + "                  \"Org_Id\": \"82\"\n" +
                    "                 },\n" + "   \"InputParameters\": \n" + "      {\"PWAREHOUSE\": \"\",\n" +
                    "       \"PALIAS\": \"\"\n }\n" + "}\n" + "}\n";
                System.out.println("Calling create method");
                String jsonArrayAsString = rcu.invokeUPDATE(restURI, payload);
                System.out.println("Received response");
                if (jsonArrayAsString != null) {
                    try {
                        JSONParser parser = new JSONParser();
                        Object object;

                        object = parser.parse(jsonArrayAsString);

                        JSONObject jsonObject = (JSONObject) object;
                        JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                        JSONObject jsObject1 = (JSONObject) jsObject.get("XALIAS");
                        JSONArray array = (JSONArray) jsObject1.get("XALIAS_ITEM");
                        if (array != null) {
                            int size = array.size();
                            //  ProductSearchEntity[] prodItems= new ProductSearchEntity[size];
                            for (int i = 0; i < size; i++) {


                                PackAndDirectShipBO items = new PackAndDirectShipBO();
                                JSONObject jsObject2 = (JSONObject) array.get(i);

                                items.setDockDoor((jsObject2.get("DOCKDOOR").toString()));
                                items.setLpn((jsObject2.get("LPN").toString()));
                                items.setSubInventory((jsObject2.get("SUBINVENTORY").toString()));
                                items.setLocator((jsObject2.get("LOCATOR").toString()));
                                items.setItem((jsObject2.get("ITEM").toString()));
                                items.setQuantity((jsObject2.get("QUANTITY").toString()));
                                items.setLotNumber((jsObject2.get("LOTNUMBER").toString()));
                                items.setSerialNumber((jsObject2.get("SERIALNUMBER").toString()));
                                items.setOrderNumber((jsObject2.get("ORDERNUMBER").toString()));
                                items.setOrderLineNumber((jsObject2.get("ORDERLINENUMBER").toString()));
                                s_packAndDirectShip.add(items);


                            }

                            super.updateSqlLiteTable(PackAndDirectShipBO.class, s_packAndDirectShip);
                        }
                    } catch (ParseException e) {
                        e.getMessage();
                    }
                }
            }
        }
    
    
    public PackAndDirectShipBO[] getPackAndDirectShip() {
        
        PackAndDirectShipBO[] arr = (PackAndDirectShipBO[]) s_packAndDirectShip.toArray(new PackAndDirectShipBO[s_packAndDirectShip.size()]);
        return arr;
    }
    
    public synchronized List<PackTxnBO> getPackTxns() {
        s_packTxns.clear();
        s_packTxns = selectPackTxns(s_packTxns);
        int trxnId = s_packTxns.size();
         AdfmfJavaUtilities.setELValue("#{pageFlowScope.SubinvTrxnId}", trxnId);
         Map map = (Map)AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope}");
         return s_packTxns;
    }

    public static List<PackTxnBO> selectPackTxns(List<PackTxnBO> packTxns) {

        //   ArrayList stocks = new ArrayList();

        int recordCount = 0;
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PACKTXN ORDER BY TRXNID");

            while (rs.next()) {
                recordCount++;
            }

            rs.beforeFirst();

            while (rs.next()) {
                int trxnid = rs.getInt(1);
                String dockDoor = rs.getString(2);
                String lpn = rs.getString(3);
                String subInv = rs.getString(4);
                String locator = rs.getString(5);
                String item = rs.getString(6);
                String Qty = rs.getString(7);
                String orderNum = rs.getString(8);
                String lineNUm = rs.getString(9);
                String serialControl = rs.getString(16);
                String lotControl = rs.getString(17);

                if (packTxns != null && packTxns.size() >= recordCount) {
                    PackTxnBO packTxn = (PackTxnBO) packTxns.get(trxnid);
                    packTxn.setTrxnId(trxnid);
                    packTxn.setDockDoor(dockDoor);
                    packTxn.setLPN(lpn);
                    packTxn.setSubInv(subInv);
                    packTxn.setLocator(locator);
                    packTxn.setItem(item);
                    packTxn.setQty(Qty);
                    packTxn.setOrderNum(orderNum);
                    packTxn.setLineNum(lineNUm);
                    packTxn.setSerialControl(serialControl);
                    packTxn.setLotControl(lotControl);
                } else {
                    PackTxnBO packTxn =
                        new PackTxnBO(trxnid, dockDoor, lpn, subInv, locator, item,
                                              Qty, orderNum, lineNUm, serialControl, lotControl);
                    packTxns.add(packTxn);
                }
            }

        } catch (SQLException e) {
            Trace.log(Utility.ApplicationLogger, Level.SEVERE, PackAndDirectShipDC.class, "packTxns",
                      "##############SQL Exception:  " + e.getMessage());
            e.printStackTrace();
        } catch (Exception exception) {
            Trace.log(Utility.ApplicationLogger, Level.SEVERE, PackAndDirectShipDC.class, "packTxns",
                      "##############Exception:  " + exception.getMessage());

        }
        return packTxns;
    }

}
