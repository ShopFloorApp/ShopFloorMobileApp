package dcom.shop.Inquiry.ProductDetails;

import dcom.shop.Inquiry.ProductDetails.ProductDetailsEntity;
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

public class ProductDetailsDC {
    List s_ProdDetailsList = new ArrayList();
    public ProductDetailsDC() {
        super();
    }
    public ProductDetailsEntity[] getAllProdDetails() {
        ValueExpression ve = null;
        

        System.out.println("Inside productItem");
        Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
        /* OrgItemEntity orgObj = new OrgItemEntity();
          orgObj.setOrgItem("STR");
          s_OrgList.add(orgObj);
          orgObj.setOrgItem("REN");
          s_OrgList.add(orgObj);*/
        String itemId = null;
        String orgId = null;
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.itemId}", String.class);
        itemId = ((String)ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.orgId}", String.class);
        orgId = ((String)ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        
        String restURI = RestURI.PostItemDetailsURI();
        RestCallerUtil rcu = new RestCallerUtil();
        String payload ="{\"x\": {\"RESTHeader\": {\"@xmlns\": \"http://xmlns.oracle.com/apps/inv/rest/DCOMInquiry/header\",\"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\"RespApplication\": \"ONT\",\"SecurityGroup\": \"STANDARD\",\n" + 
        "\"NLSLanguage\": \"AMERICAN\",\"Org_Id\": \"82\"},\"InputParameters\": {\"PITEM\": \""+itemId+"\",\"PORG\": \""+orgId+"\"}\n" + 
        "}\n" + 
        "}";
        System.out.println("Calling create method");
        String jsonArrayAsString = (rcu.invokeUPDATE(restURI, payload)).toString();
        System.out.println("Received response");
        ProductDetailsEntity[] prodDetailsArray =  null;
        //ashish
        try{
            JSONParser parser = new JSONParser();
            Object object = parser.parse(jsonArrayAsString);
            JSONObject jsonObject = (JSONObject)object;
            JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
            JSONObject jsObject1 = (JSONObject) jsObject.get("XITEMRES");
            JSONObject jsObject2 = (JSONObject) jsObject1.get("XITEMRES_ITEM");         
            if(jsObject2 != null){



                      ProductDetailsEntity prodItemsDetails= new ProductDetailsEntity();
                    //    JSONObject jsObject2 = (JSONObject) array.get(i);                         
                           
                           prodItemsDetails.setITEM((jsObject2.get("ITEM").toString()));
                           prodItemsDetails.setITEMDESC((jsObject2.get("ITEMDESC").toString()));
                           prodItemsDetails.setITEMSTATUS((jsObject2.get("ITEMSTATUS").toString()));
                           prodItemsDetails.setPRIMARYUOM((jsObject2.get("PRIMARYUOM").toString()));     
                           prodItemsDetails.setREVISION((jsObject2.get("REVISION").toString()));
                           prodItemsDetails.setITEMTYPE((jsObject2.get("ITEMTYPE").toString())); 
                      /*     prodItemsDetails.setITEMCATALOG((jsObject2.get("ITEMCATALOG").toString())); 
                           prodItemsDetails.setDATECREATED((jsObject2.get("DATECREATED").toString()));
                           prodItemsDetails.setLOGDESC((jsObject2.get("LOGDESC").toString()));
                           prodItemsDetails.setWEIGHTUOM((jsObject2.get("WEIGHTUOM").toString()));
                           prodItemsDetails.setWEIGHT(Integer.parseInt((jsObject2.get("WEIGHT").toString())));;
                           prodItemsDetails.setVOLUMEUOM((jsObject2.get("VOLUMEUOM").toString()));
                           prodItemsDetails.setVOLUME(Integer.parseInt((jsObject2.get("VOLUME").toString())));
                           prodItemsDetails.setDIMUOM((jsObject2.get("DIMUOM").toString()));
                           prodItemsDetails.setWIDTH(Integer.parseInt((jsObject2.get("WIDTH").toString())));  
                           prodItemsDetails.setLENGTH(Integer.parseInt((jsObject2.get("LENGTH").toString())));  
                           prodItemsDetails.setHEIGHT(Integer.parseInt((jsObject2.get("HEIGHT").toString()))); 
                           prodItemsDetails.setISCONTAINER((jsObject2.get("ISCONTAINER").toString()));
                           prodItemsDetails.setCONTAINERTYPE((jsObject2.get("CONTAINERTYPE").toString()));
                           prodItemsDetails.setMAXLOAD((jsObject2.get("MAXLOAD").toString()));*/
                           s_ProdDetailsList.add(prodItemsDetails);

            }
           prodDetailsArray= (ProductDetailsEntity[])s_ProdDetailsList.toArray(new ProductDetailsEntity[s_ProdDetailsList.size()]);
            
        }catch(Exception e){
                Trace.log("REST_JSON",Level.SEVERE, this.getClass(),"ProductDetailsEntity", e.getLocalizedMessage());
            }
        return prodDetailsArray;         
    }
    
}
