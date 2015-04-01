package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.ResourceBO;
import dcom.shop.restURIDetails.RestCallerUtil;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.util.Utility;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ResourceDC extends SyncUtils {
    public ResourceDC() {
        super();

    }
    
    protected static List s_resources = new ArrayList();
    
    private static final String NOT_REACHABLE = "NotReachable"; // Indiates no network connectivity
    
    //SyncUtils syncUtils = new SyncUtils();
    public void syncLocalDB() {
        s_resources.clear();
        String networkStatus =
            (String) AdfmfJavaUtilities.evaluateELExpression("#{deviceScope.hardware.networkStatus}");
        List collections;
        if (networkStatus.equals(NOT_REACHABLE)) {
            s_resources = super.getCollectionFromDB(ResourceBO.class);
        } else {
            System.out.println("Inside orgItem");
            Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
            String restURI = "/webservices/rest/DCOMLOV/getresources/";
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
                "       \"PRESOURCE\": \"\"\n }\n" + "}\n" + "}\n";
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
                    JSONObject jsObject1 = (JSONObject) jsObject.get("XRESOURCE");
                    JSONArray array = (JSONArray) jsObject1.get("XRESOURCE_ITEM");
                    if (array != null) {
                        int size = array.size();
                        //  ProductSearchEntity[] prodItems= new ProductSearchEntity[size];
                        for (int i = 0; i < size; i++) {


                            ResourceBO ResourceItems = new ResourceBO();
                            JSONObject jsObject2 = (JSONObject) array.get(i);

                            ResourceItems.setWhse((jsObject2.get("WHSE").toString()));
                            ResourceItems.setResourceCode((jsObject2.get("RESOURCECODE").toString()));
                            ResourceItems.setDeptCode((jsObject2.get("DEPTCODE").toString()));
                            ResourceItems.setDescription((jsObject2.get("DESCRIPTION").toString()));
                            ResourceItems.setResourceType((jsObject2.get("RESOURCETYPE").toString()));
                            ResourceItems.setUOM((jsObject2.get("UOM").toString()));
                            ResourceItems.setChargeType((jsObject2.get("CHARGETYPE").toString()));
                            ResourceItems.setBasis((jsObject2.get("BASIS").toString()));
                            // ResourceItems.setInstance((jsObject2.get("INSTANCE").toString()));
                            s_resources.add(ResourceItems);


                        }

                        super.updateSqlLiteTable(ResourceBO.class, s_resources);
                    }
                } catch (ParseException e) {
                    e.getMessage();
                }
            }
        }
    }

    public ResourceBO[] getResource() {

        ResourceBO[] ResourceArray = (ResourceBO[]) s_resources.toArray(new ResourceBO[s_resources.size()]);
        return ResourceArray;
    }
}
