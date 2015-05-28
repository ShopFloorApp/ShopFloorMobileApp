package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.CountTypeBO;
import dcom.shop.restURIDetails.RestCallerUtil;

import java.util.ArrayList;
import java.util.List;

import javax.el.ValueExpression;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.Utility;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CountTypeDC extends SyncUtils {
    public CountTypeDC() {
        super();
    }
    protected static List s_CountType = new ArrayList();
    private static final String NOT_REACHABLE = "NotReachable"; // Indiates no network connectivity
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    //SyncUtils syncUtils = new SyncUtils();


    public CountTypeBO[] getCountType() {
        s_CountType.clear();
        String networkStatus =
            (String) AdfmfJavaUtilities.evaluateELExpression("#{deviceScope.hardware.networkStatus}");
        List collections;
        if (networkStatus.equals(NOT_REACHABLE)) {
            s_CountType = super.getCollectionFromDB(CountTypeBO.class);
        } else {
            System.out.println("Inside orgItem");
            Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
            String restURI = "/webservices/rest/DCOMLOV/getcyclecount/";
            RestCallerUtil rcu = new RestCallerUtil();
            String orgCode = null;
            String ccName = null;
            ValueExpression ve = null;

            ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.name}", String.class);
            ccName = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();

            ve =
                AdfmfJavaUtilities.getValueExpression("#{preferenceScope.feature.dcom.shop.MyWarehouse.OrgCodePG.OrgCode}",
                                                      String.class);
            orgCode = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();

            String payload =
                /* "{\n" + "\"GET_SO_PER_ORG_Input\":\n" + "{\n" +
                "\"@xmlns\": \"http://xmlns.oracle.com/apps/fnd/rest/GetSoPerOrgSvc/get_so_per_org/\",\n" +
                "   \"RESTHeader\": {\"@xmlns\": \"http://xmlns.oracle.com/apps/fnd/rest/GetSoPerOrgSvc/header\",\n" +
                "                  \"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" +
                "                  \"RespApplication\": \"ONT\",\n" +
                "                  \"SecurityGroup\": \"STANDARD\",\n" +
                "                  \"NLSLanguage\": \"AMERICAN\",\n" + "                  \"Org_Id\": \"82\"\n" +
                "                 },\n" + "   \"InputParameters\": \n" + "      {\"PWAREHOUSE\": \"\",\n" +
                "       \"PCC\": \"\"\n }\n" + "}\n" + "}\n";*/
                "{\n" + "    \"x\": {\n" + "        \n" + "        \"RESTHeader\": {\n" +
                "            \"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" +
                "            \"RespApplication\": \"ONT\",\n" + "            \"SecurityGroup\": \"STANDARD\",\n" +
                "            \"NLSLanguage\": \"AMERICAN\",\n" + "            \"Org_Id\": \"82\"\n" + "        },\n" +
                "        \"InputParameters\": {\n" + "            \"PWAREHOUSE\": \"" + orgCode + "\",\n" +
                "            \"PCC\": \"" + ccName + "\"\n" + "        }\n" + "    }\n" + "}";
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
                    JSONObject jsObject1 = (JSONObject) jsObject.get("XCC");
                    if (jsObject1 != null) {
                        JSONArray array = (JSONArray) jsObject1.get("XCC_ITEM");
                        if (array != null) {
                            int size = array.size();
                            //  ProductSearchEntity[] prodItems= new ProductSearchEntity[size];
                            for (int i = 0; i < size; i++) {


                                CountTypeBO CountTypeItems = new CountTypeBO();
                                JSONObject jsObject2 = (JSONObject) array.get(i);
                                CountTypeItems.setWhse((jsObject2.get("WHSE").toString()));
                                CountTypeItems.setCountType((jsObject2.get("COUNTTYPE").toString()));
                                CountTypeItems.setCountName((jsObject2.get("COUNTNAME").toString()));
                                CountTypeItems.setDescription((jsObject2.get("DESCRIPTION").toString()));
                                s_CountType.add(CountTypeItems);


                            }

                            super.updateSqlLiteTable(CountTypeBO.class, s_CountType);
                        }
                    }
                } catch (ParseException e) {
                    e.getMessage();
                }
            }
        }
        CountTypeBO[] CountTypeArray = (CountTypeBO[]) s_CountType.toArray(new CountTypeBO[s_CountType.size()]);
        if (s_CountType.size() != 0) {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.CycleNameResults}", "");
        } else {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.CycleNameResults}", "No Search Results");
        }


        return CountTypeArray;
    }

    public void refresh() {
        providerChangeSupport.fireProviderRefresh("countType");
    }

    public void addProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.addProviderChangeListener(l);
    }

    public void removeProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.removeProviderChangeListener(l);
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }

}
