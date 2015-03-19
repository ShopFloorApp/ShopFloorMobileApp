package dcom.shop.Inquiry.ProductDetails.produda;

import dcom.shop.Inquiry.ProductDetails.produda.ProductUdaEntity;
import dcom.shop.restURIDetails.RestURI;

import dcom.shop.restURIDetails.RestCallerUtil;

import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;

import javax.el.ValueExpression;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.JSONBeanSerializationHelper;

import oracle.adfmf.util.Utility;
import oracle.adfmf.util.logging.Trace;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ProductUdaDC {
    public ProductUdaDC() {
        super();
    }
    List s_udaList = new ArrayList();

    public ProductUdaEntity[] getAllItemUda() {
        ValueExpression ve = null;


        System.out.println("Inside item uda");
        Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
        String item = null;
        String org = null;
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.item}", String.class);
        item = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();

        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.org}", String.class);
        org = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();

        String restURI = RestURI.PostItemUdaInquiryURI();
        RestCallerUtil rcu = new RestCallerUtil();
        String payload =
            "{\"x\": {\"RESTHeader\": {\"@xmlns\": \"http://xmlns.oracle.com/apps/inv/rest/DCOMInquiry/header\",\"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\"RespApplication\": \"ONT\",\"SecurityGroup\": \"STANDARD\",\n" +
            "\"NLSLanguage\": \"AMERICAN\",\"Org_Id\": \"82\"},\"InputParameters\": {\"PITEM\": \"" + item +
            "\",\"PORG\": \"" + org + "\"}\n" + "}\n" + "}";
        System.out.println("Calling create method");
        String jsonArrayAsString = (rcu.invokeUPDATE(restURI, payload)).toString();
        System.out.println("Received response");
        ProductUdaEntity[] itemUdaArray = null;
        //ashish
        try {
            JSONParser parser = new JSONParser();
            Object object = parser.parse(jsonArrayAsString);
            JSONObject jsonObject = (JSONObject) object;
            JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
            JSONObject jsObject1 = (JSONObject) jsObject.get("XITEMUDARES");
            try {
                JSONArray array = (JSONArray) jsObject1.get("XITEMUDARES_ITEM");

                if (array != null) {
                    int size = array.size();
                    //  ProductSearchEntity[] prodItems= new ProductSearchEntity[size];
                    for (int i = 0; i < size; i++) {


                        ProductUdaEntity itemsUda = new ProductUdaEntity();
                        JSONObject jsObjectArrayData = (JSONObject) array.get(i);


                        itemsUda.setCATALOGNAME((jsObjectArrayData.get("CATALOGNAME").toString()));
                        itemsUda.setGROUPNAME((jsObjectArrayData.get("GROUPNAME").toString()));
                        itemsUda.setATTRIBNAME((jsObjectArrayData.get("ATTRIBNAME").toString()));
                        itemsUda.setATTRIBVALUE((jsObjectArrayData.get("ATTRIBVALUE").toString()));


                        s_udaList.add(itemsUda);
                    }


                }
            } catch (ClassCastException e2) {
                JSONObject jsObject2 = (JSONObject) jsObject1.get("XITEMUDARES_ITEM");
                if (jsObject2 != null) {


                    ProductUdaEntity itemsUda = new ProductUdaEntity();
                    // JSONObject jsObject2 = (JSONObject)

                    itemsUda.setCATALOGNAME((jsObject2.get("CATALOGNAME").toString()));
                    itemsUda.setGROUPNAME((jsObject2.get("GROUPNAME").toString()));
                    itemsUda.setATTRIBNAME((jsObject2.get("ATTRIBNAME").toString()));
                    itemsUda.setATTRIBVALUE((jsObject2.get("ATTRIBVALUE").toString()));


                    s_udaList.add(itemsUda);


                }
            }

            itemUdaArray = (ProductUdaEntity[]) s_udaList.toArray(new ProductUdaEntity[s_udaList.size()]);

        } catch (Exception e) {
            Trace.log("REST_JSON", Level.SEVERE, this.getClass(), "ProductUdaEntity", e.getLocalizedMessage());
        }
        return itemUdaArray;
    }
}
