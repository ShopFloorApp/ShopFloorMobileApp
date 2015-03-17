package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.WarehouseBO;
import dcom.shop.restURIDetails.RestCallerUtil;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.util.Utility;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class WarehouseDC extends SyncUtils{
    public WarehouseDC() {
        super();
        
    }
    protected static List s_warehouse = new ArrayList();
    private static final String NOT_REACHABLE = "NotReachable"; // Indiates no network connectivity

    public void syncLocalDB(){
        s_warehouse.clear();
        String networkStatus =
            (String) AdfmfJavaUtilities.evaluateELExpression("#{deviceScope.hardware.networkStatus}");
        //List collections;
        if (networkStatus.equals(NOT_REACHABLE)) {
            s_warehouse = super.getCollectionFromDB(WarehouseBO.class);
        } else {
            System.out.println("Inside orgItem");
            Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
            String restURI = "/webservices/rest/DCOMLOV/getwarehouse/";
            RestCallerUtil rcu = new RestCallerUtil();
            String payload =
                "{\n" + "\"GET_SO_PER_ORG_Input\":\n" + "{\n" +
                "\"@xmlns\": \"http://xmlns.oracle.com/apps/fnd/rest/GetSoPerOrgSvc/get_so_per_org/\",\n" +
                "   \"RESTHeader\": {\"@xmlns\": \"http://xmlns.oracle.com/apps/fnd/rest/GetSoPerOrgSvc/header\",\n" +
                "                  \"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" +
                "                  \"RespApplication\": \"ONT\",\n" +
                "                  \"SecurityGroup\": \"STANDARD\",\n" +
                "                  \"NLSLanguage\": \"AMERICAN\",\n" + "                  \"Org_Id\": \"82\"\n" +
                "                 },\n" + "   \"InputParameters\": \n" + "      {\"POU\": \"\",\n" +
                "       \"PWAREHOUSE\": \"\"\n }\n" + "}\n" + "}\n";
            System.out.println("Calling create method");
            String jsonArrayAsString = rcu.invokeUPDATE(restURI, payload);
            System.out.println("Received response");
            //            WarehouseBO[] warehouse = null;
            if (jsonArrayAsString != null) {
                try {
                    JSONParser parser = new JSONParser();
                    Object object;

                    object = parser.parse(jsonArrayAsString);

                    JSONObject jsonObject = (JSONObject) object;
                    JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                    JSONObject jsObject1 = (JSONObject) jsObject.get("XWAREHOUSE");
                    JSONArray array = (JSONArray) jsObject1.get("XWAREHOUSE_ITEM");
                    if (array != null) {
                        int size = array.size();
                        //  ProductSearchEntity[] prodItems= new ProductSearchEntity[size];
                        for (int i = 0; i < size; i++) {


                            WarehouseBO whseItems = new WarehouseBO();
                            JSONObject jsObject2 = (JSONObject) array.get(i);

                            whseItems.setWhse((jsObject2.get("WHSE").toString()));
                            whseItems.setName((jsObject2.get("NAME").toString()));
                            whseItems.setLine1((jsObject2.get("LINE1").toString()));
                            whseItems.setLine2((jsObject2.get("LINE2").toString()));
                            whseItems.setLine3((jsObject2.get("LINE3").toString()));
                            whseItems.setLine4((jsObject2.get("LINE4").toString()));
                            whseItems.setCity((jsObject2.get("CITY").toString()));
                            whseItems.setProvince((jsObject2.get("PROVINCE").toString()));
                            whseItems.setState((jsObject2.get("STATE").toString()));
                            whseItems.setZip((jsObject2.get("ZIP").toString()));
                            whseItems.setCountry((jsObject2.get("COUNTRY").toString()));
                            whseItems.setLocatorControl((jsObject2.get("LOCATORCONTROL").toString()));
                            whseItems.setIsWMS((jsObject2.get("ISWMS").toString()));

                            s_warehouse.add(whseItems);


                        }

                        super.updateSqlLiteTable(WarehouseBO.class, s_warehouse);
                    }
                } catch (ParseException e) {
                    e.getMessage();
                }
            }
        }
    }
    
    public WarehouseBO[] getWarehouse() {
        
        WarehouseBO[] warehouseArray = (WarehouseBO[]) s_warehouse.toArray(new WarehouseBO[s_warehouse.size()]);
        return warehouseArray;
    }
}
