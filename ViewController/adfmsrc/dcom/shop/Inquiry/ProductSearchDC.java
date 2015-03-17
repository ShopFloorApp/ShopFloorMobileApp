package dcom.shop.Inquiry;

import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.el.ValueExpression;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.Utility;
import oracle.adfmf.util.logging.Trace;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class ProductSearchDC {
    List s_ProdList = new ArrayList();

    public ProductSearchDC() {
        super();
    }
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);


    public ProductSearchEntity[] getAllProdItems() {
        ValueExpression ve = null;
        

        System.out.println("Inside productItem");
        Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
        /* OrgItemEntity orgObj = new OrgItemEntity();
          orgObj.setOrgItem("STR");
          s_OrgList.add(orgObj);
          orgObj.setOrgItem("REN");
          s_OrgList.add(orgObj);*/
        String keyword = null;
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchKeyword}", String.class);
        keyword = (String)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        String restURI = RestURI.PostItemInquiryURI();
        RestCallerUtil rcu = new RestCallerUtil();
        String payload ="{\"x\": {\"RESTHeader\": {\"@xmlns\": \"http://xmlns.oracle.com/apps/inv/rest/DCOMInquiry/header\",\"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\"RespApplication\": \"ONT\",\"SecurityGroup\": \"STANDARD\",\n" + 
        "\"NLSLanguage\": \"AMERICAN\",\"Org_Id\": \"82\"},\"InputParameters\": {\"PITEMREQ\": {\"ORGCODE\": \"999\",\"ITEM\": \"%"+keyword+"%\"}}\n" + 
        "}\n" + 
        "}";
        System.out.println("Calling create method");
        String jsonArrayAsString = (rcu.invokeUPDATE(restURI, payload)).toString();
        System.out.println("Received response");
        ProductSearchEntity[] prodArray =  null;
        //ashish
        try{
    
            JSONParser parser = new JSONParser();
            Object object = parser.parse(jsonArrayAsString);
            JSONObject jsonObject = (JSONObject)object;
            JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
            JSONObject jsObject1 = (JSONObject) jsObject.get("XITEMRES");
            JSONArray array = (JSONArray) jsObject1.get("XITEMRES_ITEM");         
            if(array != null){
            int size =  array.size();
          //  ProductSearchEntity[] prodItems= new ProductSearchEntity[size];
            for (int i = 0; i <size; i++) {


                      ProductSearchEntity prodItems= new ProductSearchEntity();
                        JSONObject jsObject2 = (JSONObject) array.get(i);                         
                           
                           prodItems.setORGCODE((jsObject2.get("ORGCODE").toString()));
                           prodItems.setITEM((jsObject2.get("ITEM").toString()));
                           prodItems.setITEMDESC((jsObject2.get("ITEMDESC").toString()));
                           prodItems.setITEMSTATUS((jsObject2.get("ITEMSTATUS").toString()));
                           prodItems.setPRIMARYUOM((jsObject2.get("PRIMARYUOM").toString()));     
                           prodItems.setITEMTYPE((jsObject2.get("ITEMTYPE").toString())); 
                           prodItems.setITEMCATALOG((jsObject2.get("ITEMCATALOG").toString())); 
                           prodItems.setITEMID(Integer.parseInt((jsObject2.get("ITEMID").toString())));    
                          prodItems.setORGID(Integer.parseInt((jsObject2.get("ORGID").toString()))); 
                           s_ProdList.add(prodItems);

                      
            }
            }
           prodArray= (ProductSearchEntity[])s_ProdList.toArray(new ProductSearchEntity[s_ProdList.size()]);
            
        }catch(Exception e){
                Trace.log("REST_JSON",Level.SEVERE, this.getClass(),"ProductSearchEntity", e.getLocalizedMessage());
            }
        return prodArray;         
    }
    
    public void refresh() {
        providerChangeSupport.fireProviderRefresh("allProdItems");
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
