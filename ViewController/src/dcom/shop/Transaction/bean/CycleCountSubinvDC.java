package dcom.shop.Transaction.bean;

import dcom.shop.Inquiry.ProductSearchEntity;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;
import dcom.shop.application.dc.SubinventoryDC;

import java.util.ArrayList;
import java.util.List;

import java.util.Map;
import java.util.logging.Level;

import javax.el.ValueExpression;

import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.util.Utility;
import oracle.adfmf.util.logging.Trace;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CycleCountSubinvDC {
    public CycleCountSubinvDC() {
    }
    List s_SubinvList = new ArrayList();
    

    
    public CycleCountSubinvEntityBean[] getAllSubInv() {

        s_SubinvList.clear();
        ValueExpression ve = null;
        System.out.println("Inside cycle count subinv service");
        Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
        String ccName = null;
        String orgCode = null;
        
        ve = AdfmfJavaUtilities.getValueExpression("#{preferenceScope.feature.dcom.shop.MyWarehouse.OrgCodePG.OrgCode}", String.class);
        orgCode = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.name}", String.class);
        ccName = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        
        String restURI = RestURI.PostCycleCountSubinvURI();
        RestCallerUtil rcu = new RestCallerUtil();
        String payload = null;
        
        payload = "{\n" + 
        "    \"x\": {\n" + 
        "        \n" + 
        "        \"RESTHeader\": {\n" + 
        "            \"Responsibility\": \"DCOM_MOBILE_USER\",\n" + 
        "            \"RespApplication\": \"INV\",\n" + 
        "            \"SecurityGroup\": \"STANDARD\",\n" + 
        "            \"NLSLanguage\": \"AMERICAN\",\n" + 
        "            \"Org_Id\": \"82\"\n" + 
        "        },\n" + 
        "        \"InputParameters\": \n" + 
        "         { \"P_ORGANIZATION_CODE\": \""+orgCode+"\",\"P_CC_NAME\":\""+ccName+"\"}\n" + 
        "    }\n" + 
        "}";  
        
        System.out.println("Calling create method");
        String jsonArrayAsString = (rcu.invokeUPDATE(restURI, payload)).toString();
        System.out.println("Received response");
        
        CycleCountSubinvEntityBean[] subinvArray = null;
        
        try {

            JSONParser parser = new JSONParser();
            Object object = parser.parse(jsonArrayAsString);
            JSONObject jsonObject = (JSONObject) object;
            JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
            String ccType = jsObject.get("X_CC_TYPE").toString();
            Map pageFlow = (Map) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope}");
            pageFlow.put("ccType", ccType);
            JSONObject jsObject1 = (JSONObject) jsObject.get("X_CC_TBL");
            try {
                JSONArray array = (JSONArray) jsObject1.get("X_CC_TBL_ITEM");
                if (array != null) {
                    int size = array.size();
                    //  ProductSearchEntity[] prodItems= new ProductSearchEntity[size];
                    for (int i = 0; i < size; i++) {


                        CycleCountSubinvEntityBean subinvItems = new CycleCountSubinvEntityBean();
                        JSONObject jsObjectArrayData = (JSONObject) array.get(i);

                        subinvItems.setSUBINVENTORY((jsObjectArrayData.get("SUBINVENTORY").toString()));                          
                        s_SubinvList.add(subinvItems);
                    }
                }
            } catch (ClassCastException e2) {
                JSONObject jsObject2 = (JSONObject) jsObject1.get("X_CC_TBL_ITEM");
                if (jsObject2 != null) {
                    CycleCountSubinvEntityBean subinvItems = new CycleCountSubinvEntityBean();
                    subinvItems.setSUBINVENTORY((jsObject2.get("SUBINVENTORY").toString()));                          
                    s_SubinvList.add(subinvItems);
                    
                }
            }


            subinvArray = (CycleCountSubinvEntityBean[]) s_SubinvList.toArray(new CycleCountSubinvEntityBean[s_SubinvList.size()]);
            

        } catch (Exception e) {
            Trace.log("REST_JSON", Level.SEVERE, this.getClass(), "ProductSearchEntity", e.getLocalizedMessage());
        }
        
      return subinvArray;
    }
}
