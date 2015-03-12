package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;

import dcom.shop.application.mobile.AccountAliasBO;

import dcom.shop.restURIDetails.RestCallerUtil;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.util.Utility;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class AccountAliasDC extends SyncUtils {
    public AccountAliasDC() {
        super();
    }
    protected static List s_accountAlias = new ArrayList();
    private static final String NOT_REACHABLE = "NotReachable"; // Indiates no network connectivity
    //SyncUtils syncUtils = new SyncUtils();


    public AccountAliasBO[] getAccountAlias() {
        String networkStatus =
            (String) AdfmfJavaUtilities.evaluateELExpression("#{deviceScope.hardware.networkStatus}");
        List collections;
        if (networkStatus.equals(NOT_REACHABLE)) {
            s_accountAlias = super.getCollectionFromDB(AccountAliasBO.class);
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


                            AccountAliasBO aliasItems = new AccountAliasBO();
                            JSONObject jsObject2 = (JSONObject) array.get(i);

                            aliasItems.setWhse((jsObject2.get("WHSE").toString()));
                            aliasItems.setAccountAlias((jsObject2.get("ACCOUNTALIAS").toString()));
                            aliasItems.setDescription((jsObject2.get("DESCRIPTION").toString()));
                            aliasItems.setAccountSegment((jsObject2.get("ACCOUNTSEGMENT").toString()));
                            s_accountAlias.add(aliasItems);


                        }

                        super.updateSqlLiteTable(AccountAliasBO.class, s_accountAlias);
                    }
                } catch (ParseException e) {
                    e.getMessage();
                }
            }
        }
        AccountAliasBO[] aliasArray = (AccountAliasBO[]) s_accountAlias.toArray(new AccountAliasBO[s_accountAlias.size()]);
        return aliasArray;
    }
}
