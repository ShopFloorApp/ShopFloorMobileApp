package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.CostGroupBO;
import dcom.shop.application.mobile.LocatorBO;
import dcom.shop.restURIDetails.RestCallerUtil;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.util.Utility;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CostGroupDC extends SyncUtils {
    public CostGroupDC() {
        super();

    }
    protected static List s_costGroups = new ArrayList();
    private static final String NOT_REACHABLE = "NotReachable"; // Indiates no network connectivity
    //SyncUtils syncUtils = new SyncUtils();
    public void syncLocalDB(){
        s_costGroups.clear();
        String networkStatus =
            (String) AdfmfJavaUtilities.evaluateELExpression("#{deviceScope.hardware.networkStatus}");
        List collections;
        if (networkStatus.equals(NOT_REACHABLE)) {
            s_costGroups = super.getCollectionFromDB(CostGroupBO.class);
        } else {
            System.out.println("Inside orgItem");
            Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
            String restURI = "/webservices/rest/DCOMLOV/getcostgroup/";
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
                "       \"PCG\": \"\"\n }\n" + "}\n" + "}\n";
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
                    JSONObject jsObject1 = (JSONObject) jsObject.get("XCG");
                    JSONArray array = (JSONArray) jsObject1.get("XCG_ITEM");
                    if (array != null) {
                        int size = array.size();
                        //  ProductSearchEntity[] prodItems= new ProductSearchEntity[size];
                        for (int i = 0; i < size; i++) {


                            CostGroupBO costgroupItems = new CostGroupBO();
                            JSONObject jsObject2 = (JSONObject) array.get(i);

                            costgroupItems.setWhse((jsObject2.get("WHSE").toString()));
                            costgroupItems.setCostGroup((jsObject2.get("COSTGROUP").toString()));
                            costgroupItems.setDescription((jsObject2.get("DESCRIPTION").toString()));
                            s_costGroups.add(costgroupItems);


                        }

                        super.updateSqlLiteTable(CostGroupBO.class, s_costGroups);
                    }
                } catch (ParseException e) {
                    e.getMessage();
                }
            }
        }
    }

    public CostGroupBO[] getCostGroup() {
        s_costGroups = super.getOfflineCollection(CostGroupBO.class);
        CostGroupBO[] costgroupArray = (CostGroupBO[]) s_costGroups.toArray(new CostGroupBO[s_costGroups.size()]);
        
        return costgroupArray;
    }

}
