package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.KanbanCardBO;
import dcom.shop.restURIDetails.RestCallerUtil;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.util.Utility;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class KanbanCardDC extends SyncUtils {
    public KanbanCardDC() {
        super();
    }
    protected static List s_KanbanCard = new ArrayList();
    private static final String NOT_REACHABLE = "NotReachable"; // Indiates no network connectivity
    //SyncUtils syncUtils = new SyncUtils();


    public KanbanCardBO[] getKanbanCard() {
        s_KanbanCard.clear();
        String networkStatus =
            (String) AdfmfJavaUtilities.evaluateELExpression("#{deviceScope.hardware.networkStatus}");
        List collections;
        if (networkStatus.equals(NOT_REACHABLE)) {
            s_KanbanCard = super.getCollectionFromDB(KanbanCardBO.class);
        } else {
            System.out.println("Inside orgItem");
            Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
            String restURI = "/webservices/rest/DCOMLOV/getkanbancard/";
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
                "       \"PKCARD\": \"\"\n }\n" + "}\n" + "}\n";
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
                    JSONObject jsObject1 = (JSONObject) jsObject.get("XKCARD");
                    JSONArray array = (JSONArray) jsObject1.get("XKCARD_ITEM");
                    if (array != null) {
                        int size = array.size();
                        //  ProductSearchEntity[] prodItems= new ProductSearchEntity[size];
                        for (int i = 0; i < size; i++) {


                            KanbanCardBO KanbanCardItems = new KanbanCardBO();
                            JSONObject jsObject2 = (JSONObject) array.get(i);

                            KanbanCardItems.setWhse((jsObject2.get("WHSE").toString()));
                            KanbanCardItems.setKanbanCardNum((jsObject2.get("KANBANCARDNUM").toString()));
                            KanbanCardItems.setCardStatus((jsObject2.get("CARDSTATUS").toString()));
                            s_KanbanCard.add(KanbanCardItems);


                        }

                        super.updateSqlLiteTable(KanbanCardBO.class, s_KanbanCard);
                    }
                } catch (ParseException e) {
                    e.getMessage();
                }
            }
        }
        KanbanCardBO[] KanbanCardArray = (KanbanCardBO[]) s_KanbanCard.toArray(new KanbanCardBO[s_KanbanCard.size()]);
        return KanbanCardArray;
    }
}
