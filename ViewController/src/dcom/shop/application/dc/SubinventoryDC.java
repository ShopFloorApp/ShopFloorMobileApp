package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.SubinventoryBO;
import dcom.shop.restURIDetails.RestCallerUtil;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.util.Utility;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SubinventoryDC extends SyncUtils {
    public SubinventoryDC() {
        super();
    }
    protected static List s_subInventories = new ArrayList();
    private static final String NOT_REACHABLE = "NotReachable"; // Indiates no network connectivity
    //SyncUtils syncUtils = new SyncUtils();


    public SubinventoryBO[] getSubinventory() {
        String networkStatus =
            (String) AdfmfJavaUtilities.evaluateELExpression("#{deviceScope.hardware.networkStatus}");
        List collections;
        if (networkStatus.equals(NOT_REACHABLE)) {
            s_subInventories = super.getCollectionFromDB(SubinventoryBO.class);
        } else {
            System.out.println("Inside orgItem");
            Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
            String restURI = "/webservices/rest/DCOMLOV/getSubinventory/";
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
                "       \"PSUBINV\": \"\"\n }\n" + "}\n" + "}\n";
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
                    JSONObject jsObject1 = (JSONObject) jsObject.get("XSUBINV");
                    JSONArray array = (JSONArray) jsObject1.get("XSUBINV_ITEM");
                    if (array != null) {
                        int size = array.size();
                        //  ProductSearchEntity[] prodItems= new ProductSearchEntity[size];
                        for (int i = 0; i < size; i++) {


                            SubinventoryBO SubinventoryItems = new SubinventoryBO();
                            JSONObject jsObject2 = (JSONObject) array.get(i);

                            SubinventoryItems.setWhse((jsObject2.get("WHSE").toString()));
                            SubinventoryItems.setSubinv((jsObject2.get("SUBINV").toString()));
                            SubinventoryItems.setDescription((jsObject2.get("DESCRIPTION").toString()));
                            SubinventoryItems.setLocatorControl((jsObject2.get("LOCATORCONTROL").toString()));
                            SubinventoryItems.setLPNControl((jsObject2.get("LPNCONTROL").toString()));
                            SubinventoryItems.setDefLocator((jsObject2.get("DEFLOCATOR").toString()));
                            SubinventoryItems.setDefCostGrp((jsObject2.get("DEFCOSTGRP").toString()));
                            s_subInventories.add(SubinventoryItems);


                        }

                        super.updateSqlLiteTable(SubinventoryBO.class, s_subInventories);
                    }
                } catch (ParseException e) {
                    e.getMessage();
                }
            }
        }
        SubinventoryBO[] SubinventoryArray =
            (SubinventoryBO[]) s_subInventories.toArray(new SubinventoryBO[s_subInventories.size()]);
        return SubinventoryArray;
    }
}
