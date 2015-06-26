package dcom.shop.Inquiry.onhand;

import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.el.ValueExpression;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.util.Utility;
import oracle.adfmf.util.logging.Trace;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class OnHandSearchDC {
    public OnHandSearchDC() {
        super();
    }
    List s_onHandList = new ArrayList();

    public OnHandSearchEntity[] getAllOnhands() {
        s_onHandList.clear();
        ValueExpression ve = null;
        Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
        String item = null;
        String subinv = null;
        String locator = null;
        String costGroup = null;
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchKeyword}", String.class);
        item = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.FromSubinventory}", String.class);
        subinv = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        if (("0".equals(subinv)) || (subinv.contains("xsi:nil"))) {
            subinv = "";
        }
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.FromLocator}", String.class);
        locator = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();
        if (("0".equals(locator) ||(locator.contains("xsi:nil")))) {
            locator = "";
        }
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.costGrp}", String.class);
        costGroup = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();

        String restURI = RestURI.PostOnHandInquiryURI();
        RestCallerUtil rcu = new RestCallerUtil();
        String orgCode =
            (String) AdfmfJavaUtilities.evaluateELExpression("#{preferenceScope.feature.dcom.shop.MyWarehouse.OrgCodePG.OrgCode}");

        String payload =
            "{\"x\":\n" + "{\n" + "   \"RESTHeader\": {\"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" +
            "                  \"RespApplication\": \"ONT\",\n" +
            "                  \"SecurityGroup\": \"STANDARD\",\n" +
            "                  \"NLSLanguage\": \"AMERICAN\",\n" + "                  \"Org_Id\": \"82\"\n" +
            "                 },\n" + "   \"InputParameters\": \n" + "     {\"PONHANDREQ\": {\"ORGCODE\": \"" +
            orgCode + "\",\"ITEM\": \"" + item + "\",\"SUBINV\": \"" + subinv + "\",\"LOCATOR\": \"" + locator +
            "\",\"COSTGROUP\": \""+costGroup+"\"}}\n" + "}\n" + "}";

        String jsonArrayAsString = (rcu.invokeUPDATE(restURI, payload)).toString();
        OnHandSearchEntity[] onHandArray = null;
        try {
            JSONParser parser = new JSONParser();
            Object object = parser.parse(jsonArrayAsString);
            JSONObject jsonObject = (JSONObject) object;
            JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
            JSONObject jsObject1 = (JSONObject) jsObject.get("XONHANDRES");
            try {
                JSONArray array = (JSONArray) jsObject1.get("XONHANDRES_ITEM");
                if (array != null) {
                    int size = array.size();
                    OnHandSearchEntity onhandItems = null;

                    for (int i = 0; i < size; i++) {
                        try {
                            onhandItems = new OnHandSearchEntity();
                            JSONObject jsObjectArrayData = (JSONObject) array.get(i);
                            onhandItems.setITEM((jsObjectArrayData.get("ITEM").toString()));
                            onhandItems.setITEMDESC((jsObjectArrayData.get("ITEMDESC").toString()));
                            onhandItems.setSUBINV((jsObjectArrayData.get("SUBINV").toString()));
                            onhandItems.setLOCATOR((jsObjectArrayData.get("LOCATOR").toString()));
                            onhandItems.setUOM((jsObjectArrayData.get("UOM").toString()));
                            onhandItems.setONHANDQTY(new BigDecimal(jsObjectArrayData.get("ONHANDQTY").toString()));
                            onhandItems.setAVAILABLEQTY(new BigDecimal((jsObjectArrayData.get("AVAILABLEQTY").toString())));
                            AdfmfJavaUtilities.setELValue("#{pageFlowScope.availableQty}", jsObjectArrayData.get("AVAILABLEQTY").toString());
                            AdfmfJavaUtilities.setELValue("#{pageFlowScope.onHandQty}", jsObjectArrayData.get("ONHANDQTY").toString());
                        } catch (Exception e) {
                            e.getMessage();
                        }
                        s_onHandList.add(onhandItems);
                    }
                }
            } catch (ClassCastException e2) {
                JSONObject jsObject2 = (JSONObject) jsObject1.get("XONHANDRES_ITEM");
                if (jsObject2 != null) {
                    OnHandSearchEntity onhandItems = null;
                    try {
                        onhandItems = new OnHandSearchEntity();
                        onhandItems.setITEM((jsObject2.get("ITEM").toString()));
                        onhandItems.setITEMDESC((jsObject2.get("ITEMDESC").toString()));
                        onhandItems.setSUBINV((jsObject2.get("SUBINV").toString()));
                        onhandItems.setUOM((jsObject2.get("UOM").toString()));
                        onhandItems.setLOCATOR((jsObject2.get("LOCATOR").toString()));
                        onhandItems.setONHANDQTY(new BigDecimal(jsObject2.get("ONHANDQTY").toString()));
                        onhandItems.setAVAILABLEQTY(new BigDecimal((jsObject2.get("AVAILABLEQTY").toString())));
                        AdfmfJavaUtilities.setELValue("#{pageFlowScope.availableQty}", jsObject2.get("AVAILABLEQTY").toString());
                        AdfmfJavaUtilities.setELValue("#{pageFlowScope.onHandQty}", jsObject2.get("ONHANDQTY").toString());
                    } catch (Exception e) {
                        e.getMessage();
                    }
                    s_onHandList.add(onhandItems);
                }
            }
            onHandArray = (OnHandSearchEntity[]) s_onHandList.toArray(new OnHandSearchEntity[s_onHandList.size()]);
        } catch (Exception e) {
            Trace.log("REST_JSON", Level.SEVERE, this.getClass(), "OnHandSearchEntity", e.getLocalizedMessage());
        }
        if (s_onHandList.size() != 0) {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.OnHandResults}", "");
        } else {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.OnHandResults}", "No Results Found");
        }
        return onHandArray;
    }
    public void callOnHandService(){
        getAllOnhands();
    }
}
