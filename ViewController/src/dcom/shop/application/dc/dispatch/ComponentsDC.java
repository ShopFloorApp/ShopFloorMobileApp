package dcom.shop.application.dc.dispatch;

import dcom.shop.application.base.AViewObject;
import dcom.shop.application.mobile.dispatch.ComponentsBO;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ComponentsDC extends AViewObject {
    protected static List<ComponentsBO> componentList = new ArrayList<ComponentsBO>();
    public ComponentsDC() {
        super();
    }
    
    public ComponentsBO[] getComponentsBO(){
        componentList.clear();
        RestCallerUtil restCallerUtil = new RestCallerUtil();
        ComponentsBO[] componentsArray = null;
        String orgCode = AdfmfJavaUtilities.evaluateELExpression("#{preferenceScope.feature.dcom.shop.MyWarehouse.OrgCodePG.OrgCode}").toString();
        String deptCode = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.deptName}").toString();
        String jobOpsVal = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.jobOps}").toString();
        StringBuffer strPayload = new StringBuffer();
        strPayload.append("{\n" + "  \"x\": {\n" + "    \"RESTHeader\": {\n" +
                          "      \"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" +
                          "      \"RespApplication\": \"ONT\",\n" + "      \"SecurityGroup\": \"STANDARD\",\n" +
                          "      \"NLSLanguage\": \"AMERICAN\",\n" + "      \"Org_Id\": \"82\"\n" + "    },\n" +
                          "    \"InputParameters\": {\n" + "      \"PORGCODE\": \"" + orgCode + "\",\n" +
                          "      \"PDEPTCODE\": \"" + deptCode + "\",\n" + "      \"PJOBOP\": \"" + jobOpsVal + "\"\n" +
                          "    }\n" + "  }\n" + "}");

        String jsonArrayAsString = restCallerUtil.invokeUPDATE(RestURI.PostGetComponents(), strPayload.toString());
        if (jsonArrayAsString != null) {
            try {
                JSONParser parser = new JSONParser();
                Object object;
                object = parser.parse(jsonArrayAsString);
                JSONObject jsonObject = (JSONObject) object;
                JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                JSONObject jsObjectParent = (JSONObject) jsObject.get("XJOBCOMP");
                JSONArray componentJsonArray = (JSONArray) jsObjectParent.get("XJOBCOMP_ITEM");

                if (componentJsonArray != null) {
                    int size = componentJsonArray.size();
                    ComponentsBO comp;
                    for (int i = 0; i < size; i++) {
                        comp = new ComponentsBO();
                        JSONObject resrcJson = (JSONObject) componentJsonArray.get(i);
                        
                        //Populating the base object
                        comp.setJobNumber(resrcJson.get("JOBNUMBER").toString());
                        comp.setJobOps(resrcJson.get("JOBOPS").toString());
                        
                        comp.setSupplyType(resrcJson.get("SUPPLYTYPE").toString());
                        comp.setUom(resrcJson.get("UOM").toString());
                        comp.setQtyAllocated(getAttributeValue(resrcJson.get("QTYALLOCATED").toString()));
                        comp.setLocator(resrcJson.get("LOCATOR").toString());
                        comp.setDescription(resrcJson.get("DESCRIPTION").toString());
                        comp.setComponent(resrcJson.get("COMPONENT").toString());
                        comp.setQtyOpen(getAttributeValue(resrcJson.get("QTYOPEN").toString()));
                        comp.setQtyRequired(getAttributeValue(resrcJson.get("QTYREQUIRED").toString()));
                        comp.setQtyPerAssembly(getAttributeValue(resrcJson.get("QTYPERASSEMBLY").toString()));
                        comp.setSubInv(resrcJson.get("SUBINV").toString());
                        comp.setQtyOnHand(getAttributeValue(resrcJson.get("QTYONHAND").toString()));
                        comp.setDateRequired(resrcJson.get("DATEREQUIRED").toString());
                        comp.setQtyIssued(getAttributeValue(resrcJson.get("QTYISSUED")));
                        comp.setBasis(resrcJson.get("BASIS").toString());
                        componentList.add(comp);
                    }
                }
                componentsArray = componentList.toArray(new ComponentsBO[componentList.size()]);
                return componentsArray;

            } catch (Exception e) {
                e.getMessage();
            }
        }
        return componentsArray;
    }
}
