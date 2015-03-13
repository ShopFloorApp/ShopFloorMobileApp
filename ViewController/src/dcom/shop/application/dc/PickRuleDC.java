package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.PickRuleBO;
import dcom.shop.restURIDetails.RestCallerUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.util.Utility;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PickRuleDC extends SyncUtils {
    public PickRuleDC() {
        super();
        String networkStatus =
            (String) AdfmfJavaUtilities.evaluateELExpression("#{deviceScope.hardware.networkStatus}");
        List collections;
        if (networkStatus.equals(NOT_REACHABLE)) {
            s_pickrule = super.getCollectionFromDB(PickRuleBO.class);
        } else {
            System.out.println("Inside orgItem");
            Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
            String restURI = "/webservices/rest/DCOMLOV/getPickRule/";
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
                "       \"PPICKRULE\": \"\"\n }\n" + "}\n" + "}\n";
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
                    JSONObject jsObject1 = (JSONObject) jsObject.get("XPICKRULE");
                    JSONArray array = (JSONArray) jsObject1.get("XPICKRULE_ITEM");
                    if (array != null) {
                        int size = array.size();
                        //  ProductSearchEntity[] prodItems= new ProductSearchEntity[size];
                        for (int i = 0; i < size; i++) {


                            PickRuleBO PickRuleItems = new PickRuleBO();
                            JSONObject jsObject2 = (JSONObject) array.get(i);

                            PickRuleItems.setWhse((jsObject2.get("WHSE").toString()));
                            PickRuleItems.setRuleName((jsObject2.get("RULENAME").toString()));
                            PickRuleItems.setDocSetName((jsObject2.get("DOCSETNAME").toString()));
                            PickRuleItems.setReleaseSeqName((jsObject2.get("RELEASESEQNAME").toString()));
                            s_pickrule.add(PickRuleItems);


                        }

                        super.updateSqlLiteTable(PickRuleBO.class, s_pickrule);
                    }
                } catch (ParseException e) {
                    e.getMessage();
                }
            }
        }
    }
    protected static List s_pickrule = new ArrayList();
    private static final String NOT_REACHABLE = "NotReachable"; // Indiates no network connectivity
    //SyncUtils syncUtils = new SyncUtils();


    public PickRuleBO[] getPickRule() {
        
        PickRuleBO[] PickRuleArray = (PickRuleBO[]) s_pickrule.toArray(new PickRuleBO[s_pickrule.size()]);
        return PickRuleArray;
    }
}
