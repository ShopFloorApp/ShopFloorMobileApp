package dcom.shop.application.dc.dispatch;

import dcom.shop.application.base.AViewObject;
import dcom.shop.application.mobile.dispatch.OpSeqBO;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class OpSeqDC extends AViewObject {
    protected static List<OpSeqBO> opSeqList = new ArrayList<OpSeqBO>();

    public OpSeqDC() {
        super();
    }

    public OpSeqBO[] getOpSeq() {
        RestCallerUtil restCallerUtil = new RestCallerUtil();
        OpSeqBO[] opSeqArray = null;

        String orgCode = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.orgCode}").toString();
        String jobNumber = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.jobNumber}").toString();

        StringBuffer strPayload = new StringBuffer();
        strPayload.append("{\n" + "  \"x\": {\n" + "    \"RESTHeader\": {\n" +
                          "      \"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" +
                          "      \"RespApplication\": \"ONT\",\n" + "      \"SecurityGroup\": \"STANDARD\",\n" +
                          "      \"NLSLanguage\": \"AMERICAN\",\n" + "      \"Org_Id\": \"82\"\n" + "    },\n" +
                          "    \"InputParameters\": {\n" + "      \"PORGCODE\": \"" + orgCode + "\",\n" +
                          "      \"PJOBOP\": \"" + jobNumber + "\",\n" + "      \"PCONTEXT\":\"\"\n" + "    }\n" +
                          "  }\n" + "}");


        String jsonArrayAsString = restCallerUtil.invokeUPDATE(RestURI.PostGetResources(), strPayload.toString());
        if (jsonArrayAsString != null) {
            try {
                JSONParser parser = new JSONParser();
                Object object;
                object = parser.parse(jsonArrayAsString);
                JSONObject jsonObject = (JSONObject) object;
                JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                JSONObject jsObjectParent = (JSONObject) jsObject.get("XOP");
                Boolean isArray = (jsObjectParent.size() > 1 ? true : false);
                if (isArray) {
                    JSONArray xopArray = (JSONArray) jsObjectParent.get("XOP_ITEM");
                    if (xopArray != null) {
                        int size = xopArray.size();
                        OpSeqBO opSeqObj;
                        for (int i = 0; i < size; i++) {
                            opSeqObj = new OpSeqBO();
                            JSONObject opSeqJson = (JSONObject) xopArray.get(i);

                            //Populating the base object
                            opSeqObj.setOPcode(opSeqJson.get("OPCODE").toString());
                            opSeqObj.setRunQty(getAttributeValue(opSeqJson.get("RUNQTY")));

                            opSeqObj.setToMoveQty(getAttributeValue(opSeqJson.get("TOMOVEQTY")));
                            opSeqObj.setCompQty(getAttributeValue(opSeqJson.get("COMPQTY")));
                            opSeqObj.setOpSeq(getAttributeValue(opSeqJson.get("OPSEQ")));
                            opSeqObj.setRejectQty(getAttributeValue(opSeqJson.get("REJECTQTY")));
                            opSeqObj.setDeptCode(opSeqJson.get("DEPTCODE").toString());
                            opSeqObj.setOrgCode(opSeqJson.get("ORGCODE").toString());
                            opSeqObj.setScrapQty(getAttributeValue(opSeqJson.get("SCRAPQTY")));

                            opSeqObj.setScrapQty(getAttributeValue(opSeqJson.get("CSCRAPQTY")));
                            opSeqObj.setScrapQty(getAttributeValue(opSeqJson.get("QUEUEQTY")));

                            opSeqList.add(opSeqObj);
                            opSeqArray = opSeqList.toArray(new OpSeqBO[opSeqList.size()]);
                            return opSeqArray;

                        }
                    }
                } else {
                    JSONObject xopObject = (JSONObject) jsObjectParent.get("XOP_ITEM");
                    OpSeqBO opSeqObj = new OpSeqBO();

                    //Populating the base object
                    opSeqObj.setOPcode(xopObject.get("OPCODE").toString());
                    opSeqObj.setRunQty(getAttributeValue(xopObject.get("RUNQTY")));

                    opSeqObj.setToMoveQty(getAttributeValue(xopObject.get("TOMOVEQTY")));
                    opSeqObj.setCompQty(getAttributeValue(xopObject.get("COMPQTY")));
                    opSeqObj.setOpSeq(getAttributeValue(xopObject.get("OPSEQ")));
                    opSeqObj.setRejectQty(getAttributeValue(xopObject.get("REJECTQTY")));
                    opSeqObj.setDeptCode(xopObject.get("DEPTCODE").toString());
                    opSeqObj.setOrgCode(xopObject.get("ORGCODE").toString());
                    opSeqObj.setScrapQty(getAttributeValue(xopObject.get("SCRAPQTY")));

                    opSeqObj.setScrapQty(getAttributeValue(xopObject.get("CSCRAPQTY")));
                    opSeqObj.setScrapQty(getAttributeValue(xopObject.get("QUEUEQTY")));
                    opSeqArray = new OpSeqBO[1];
                    opSeqArray[1] = opSeqObj;
                    return opSeqArray;
                }
            } catch (Exception e) {
                e.getMessage();
            }
        }
        return opSeqArray;
    }
}
