package dcom.shop.Inquiry.ProductDetails.prodcateg;

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

public class ProductCategDC {
    public ProductCategDC() {
        super();
    }

    List s_categList = new ArrayList();
    public ProductCategEntity[] getAllItemCateg() {
        ValueExpression ve = null;


        System.out.println("Inside item categories");
        Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
    String item = null;
                String org = null;
                ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.item}", String.class);
                item = ((String)ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
                
                ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.org}", String.class);
                org = ((String)ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        
        String restURI = RestURI.PostItemCategInquiryURI();
        RestCallerUtil rcu = new RestCallerUtil();
                String payload ="{\"x\": {\"RESTHeader\": {\"@xmlns\": \"http://xmlns.oracle.com/apps/inv/rest/DCOMInquiry/header\",\"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\"RespApplication\": \"ONT\",\"SecurityGroup\": \"STANDARD\",\n" + 
                "\"NLSLanguage\": \"AMERICAN\",\"Org_Id\": \"82\"},\"InputParameters\": {\"PITEM\": \""+item+"\",\"PORG\": \""+org+"\"}\n" + 
                "}\n" + 
        "}";
        System.out.println("Calling create method");
        String jsonArrayAsString = (rcu.invokeUPDATE(restURI, payload)).toString();
        System.out.println("Received response");
        ProductCategEntity[] itemcategArray = null;
        //ashish
        try {
            JSONParser parser = new JSONParser();
            Object object = parser.parse(jsonArrayAsString);
            JSONObject jsonObject = (JSONObject) object;
            JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
            JSONObject jsObject1 = (JSONObject) jsObject.get("XITEMCATRES");
            try {
                JSONArray array = (JSONArray) jsObject1.get("XITEMCATRES_ITEM");

                if (array != null) {
                    int size = array.size();
                    //  ProductSearchEntity[] prodItems= new ProductSearchEntity[size];
                    for (int i = 0; i < size; i++) {


                        ProductCategEntity itemsCateg = new ProductCategEntity();
                      JSONObject jsObjectArrayData = (JSONObject) array.get(i); 

                      
                        itemsCateg.setCATCODE((jsObjectArrayData.get("CATCODE").toString()));
                        itemsCateg.setCATNAME((jsObjectArrayData.get("CATNAME").toString()));
                        itemsCateg.setCATDESC((jsObjectArrayData.get("CATDESC").toString()));
                        
                      
                        s_categList.add(itemsCateg);
                    }


                }
            } catch (ClassCastException e2) {
                JSONObject jsObject2 = (JSONObject) jsObject1.get("XITEMCATRES_ITEM");
                if (jsObject2 != null) {


                    ProductCategEntity itemsCateg = new ProductCategEntity();
                    // JSONObject jsObject2 = (JSONObject)
                    
                    itemsCateg.setCATCODE((jsObject2.get("CATCODE").toString()));
                    itemsCateg.setCATNAME((jsObject2.get("CATNAME").toString()));
                    itemsCateg.setCATDESC((jsObject2.get("CATDESC").toString()));
                    
                    
                    s_categList.add(itemsCateg);


                }
            }

            itemcategArray = (ProductCategEntity[]) s_categList.toArray(new ProductCategEntity[s_categList.size()]);

        } catch (Exception e) {
            Trace.log("REST_JSON", Level.SEVERE, this.getClass(), "ProductCategEntity", e.getLocalizedMessage());
        }
        return itemcategArray;
    }


}
