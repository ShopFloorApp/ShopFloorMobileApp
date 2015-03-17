package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.CarrierBO;
import dcom.shop.restURIDetails.RestCallerUtil;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.util.Utility;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CarrierDC extends SyncUtils {

    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public CarrierDC() {
        super();

    }
    protected static List s_carrier = new ArrayList();
    private static final String NOT_REACHABLE = "NotReachable"; // Indiates no network connectivity

    public void syncLocalDB(){
        s_carrier.clear();
        String networkStatus =
            (String) AdfmfJavaUtilities.evaluateELExpression("#{deviceScope.hardware.networkStatus}");
        List collections;
        if (networkStatus.equals(NOT_REACHABLE)) {
            s_carrier = super.getCollectionFromDB(CarrierBO.class);
        } else {
            System.out.println("Inside orgItem");
            Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
            String restURI = "/webservices/rest/DCOMLOV/getCarrier/";
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
                "       \"PCARRIER\": \"\"\n }\n" + "}\n" + "}\n";
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
                    JSONObject jsObject1 = (JSONObject) jsObject.get("XCARRIER");
                    JSONArray array = (JSONArray) jsObject1.get("XCARRIER_ITEM");
                    if (array != null) {
                        int size = array.size();
                        //  ProductSearchEntity[] prodItems= new ProductSearchEntity[size];
                        for (int i = 0; i < size; i++) {
                            CarrierBO carrierItems = new CarrierBO();
                            JSONObject jsObject2 = (JSONObject) array.get(i);
                            carrierItems.setWhse((jsObject2.get("WHSE").toString()));
                            carrierItems.setCarrierName((jsObject2.get("CARRIERNAME").toString()));
                            carrierItems.setFreightCode((jsObject2.get("FREIGHTCODE").toString()));
                            carrierItems.setSCAC((jsObject2.get("SCAC").toString()));
                            carrierItems.setShipMethod((jsObject2.get("SHIPMETHOD").toString()));
                            carrierItems.setDescription((jsObject2.get("DESCRIPTION").toString()));
                            s_carrier.add(carrierItems);


                        }

                        super.updateSqlLiteTable(CarrierBO.class, s_carrier);
                    }
                } catch (ParseException e) {
                    e.getMessage();
                }
            }
        }
    }

    public CarrierBO[] getCarrier() {
        
        CarrierBO[] carrierArray = (CarrierBO[]) s_carrier.toArray(new CarrierBO[s_carrier.size()]);
        return carrierArray;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
