package dcom.shop.application.dc.dispatch;

import dcom.shop.application.base.AViewObject;
import dcom.shop.application.mobile.dispatch.ResourcesBO;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ResourcesDC extends AViewObject  {
    protected static List<ResourcesBO> resourceList = new ArrayList<ResourcesBO>();

    public ResourcesDC() {
        super();
    }

    public ResourcesBO[] getResourcesBO() {
        RestCallerUtil restCallerUtil = new RestCallerUtil();
        ResourcesBO[] resourcesArray = null;
        String orgCode = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.orgCode}").toString();
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


        String jsonArrayAsString = restCallerUtil.invokeUPDATE(RestURI.PostGetResources(), strPayload.toString());
        if (jsonArrayAsString != null) {
            try {
                JSONParser parser = new JSONParser();
                Object object;
                object = parser.parse(jsonArrayAsString);
                JSONObject jsonObject = (JSONObject) object;
                JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                JSONObject jsObjectParent = (JSONObject) jsObject.get("XJOBRSRC");
                JSONArray resourceJsonArray = (JSONArray) jsObjectParent.get("XJOBRSRC_ITEM");

                if (resourceJsonArray != null) {
                    int size = resourceJsonArray.size();
                    ResourcesBO resrc;
                    for (int i = 0; i < size; i++) {
                        resrc = new ResourcesBO();
                        JSONObject resrcJson = (JSONObject) resourceJsonArray.get(i);
                        //Populating the base object
                        resrc.setJobNumber(resrcJson.get("JOBNUMBER").toString());
                        resrc.setJobOps(resrcJson.get("JOBOPS").toString());
                        resrc.setResourceName(resrcJson.get("RESOURCENAME").toString());
                        resrc.setUom(resrcJson.get("UOM").toString());
                        resrc.setEndDate(resrcJson.get("ENDDATE").toString());
                        resrc.setStartDate(resrcJson.get("STARTDATE").toString());
                        resrc.setQtyApplied(getAttributeValue(resrcJson.get("QTYAPPLIED")));
                        resrc.setUsagerate(getAttributeValue(resrcJson.get("USAGERATE").toString()));
                        resrc.setQtyOpen(getAttributeValue(resrcJson.get("QTYOPEN").toString()));
                        resrc.setAssignedunits(getAttributeValue(resrcJson.get("ASSIGNEDUNITS").toString()));
                        resrc.setQtyRequired(getAttributeValue(resrcJson.get("QTYREQUIRED").toString()));
                        resrc.setResourceType(resrcJson.get("RESOURCETYPE").toString());
                        resrc.setBasis(resrcJson.get("BASIS").toString());
                        resourceList.add(resrc);
                    }
                }
                resourcesArray = resourceList.toArray(new ResourcesBO[resourceList.size()]);
                return resourcesArray;

            } catch (Exception e) {
                e.getMessage();
            }
        }
        return resourcesArray;
    }
}
