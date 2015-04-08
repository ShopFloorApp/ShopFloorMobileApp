package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.AccountAliasBO;
import dcom.shop.application.mobile.CategorySetBO;
import dcom.shop.restURIDetails.RestCallerUtil;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.util.Utility;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CategorySetDC extends SyncUtils {
    public CategorySetDC() {
        super();

    }
    protected static List s_categorySets = new ArrayList();
    private static final String NOT_REACHABLE = "NotReachable"; // Indiates no network connectivity
    //SyncUtils syncUtils = new SyncUtils();

    public void syncLocalDB() {
        s_categorySets.clear();
        String networkStatus =
            (String) AdfmfJavaUtilities.evaluateELExpression("#{deviceScope.hardware.networkStatus}");
        List collections;
        if (networkStatus.equals(NOT_REACHABLE)) {
            s_categorySets = super.getCollectionFromDB(CategorySetBO.class);
        } else {
            System.out.println("Inside orgItem");
            Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
            String restURI = "/webservices/rest/DCOMLOV/getcategories/";
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
                "       \"PCATEG\": \"\"\n }\n" + "}\n" + "}\n";
            System.out.println("Calling create method");
            String jsonArrayAsString = rcu.invokeUPDATE(restURI, payload);
            System.out.println("Received response");
            if (jsonArrayAsString != null) {
                JSONObject jsObject1 = null;
                try {
                    JSONParser parser = new JSONParser();
                    Object object;

                    object = parser.parse(jsonArrayAsString);

                    JSONObject jsonObject = (JSONObject) object;
                    JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                    jsObject1 = (JSONObject) jsObject.get("XCATEG");
                    JSONArray array = (JSONArray) jsObject1.get("XCATEG_ITEM");
                    if (array != null) {
                        int size = array.size();
                        //  ProductSearchEntity[] prodItems= new ProductSearchEntity[size];
                        for (int i = 0; i < size; i++) {


                            CategorySetBO categsetItems = new CategorySetBO();
                            JSONObject jsObject2 = (JSONObject) array.get(i);

                            categsetItems.setCatgSetName((jsObject2.get("CATGSETNAME").toString()));
                            categsetItems.setStructureId((jsObject2.get("STRUCTUREID").toString()));
                            categsetItems.setDescription((jsObject2.get("DESCRIPTION").toString()));
                            categsetItems.setDefCatgCode((jsObject2.get("DEFCATGCODE").toString()));
                            categsetItems.setControlLevel((jsObject2.get("CONTROLLEVEL").toString()));
                            categsetItems.setMultiCatgAssign((jsObject2.get("MULTICATGASSIGN").toString()));
                            s_categorySets.add(categsetItems);


                        }

                        super.updateSqlLiteTable(CategorySetBO.class, s_categorySets);
                    }
                } catch (ClassCastException e2) {
                    JSONObject jsObject2 = (JSONObject) jsObject1.get("XCATEG_ITEM");
                    if (jsObject2 != null) {
                        CategorySetBO categsetItems = new CategorySetBO();
                        categsetItems.setCatgSetName((jsObject2.get("CATGSETNAME").toString()));
                        categsetItems.setStructureId((jsObject2.get("STRUCTUREID").toString()));
                        categsetItems.setDescription((jsObject2.get("DESCRIPTION").toString()));
                        categsetItems.setDefCatgCode((jsObject2.get("DEFCATGCODE").toString()));
                        categsetItems.setControlLevel((jsObject2.get("CONTROLLEVEL").toString()));
                        categsetItems.setMultiCatgAssign((jsObject2.get("MULTICATGASSIGN").toString()));
                        s_categorySets.add(categsetItems);
                        super.updateSqlLiteTable(CategorySetBO.class, s_categorySets);
                    }
                } catch (ParseException e) {
                    e.getMessage();
                }
            }
        }
    }

    public CategorySetBO[] getCategorySet() {

        CategorySetBO[] categsetArray =
            (CategorySetBO[]) s_categorySets.toArray(new CategorySetBO[s_categorySets.size()]);
        return categsetArray;
    }
}
