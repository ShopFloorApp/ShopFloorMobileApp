package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.PackAndDirectShipBO;
import dcom.shop.application.mobile.PackAndDirectShipBO;
import dcom.shop.application.mobile.txn.ConcurrentProgramBO;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.util.Utility;

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
}
