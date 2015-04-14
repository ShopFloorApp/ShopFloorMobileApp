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
        OpSeqBO[] opSeqArray = null;
        if (opSeqList.size() > 0) {
            opSeqList.clear();
        }
        //Trying to check network connectivity
        if (!isOffline()) {
            //Calling Web service
            getCollectionFromWS();
            if (opSeqList.size() > 0) {
                opSeqArray = opSeqList.toArray(new OpSeqBO[opSeqList.size()]);
            }

        }
        return opSeqArray;
    }

    private void getCollectionFromWS() {
        RestCallerUtil restCallerUtil = new RestCallerUtil();


        String orgCode = AdfmfJavaUtilities.evaluateELExpression("#{preferenceScope.feature.dcom.shop.MyWarehouse.OrgCodePG.OrgCode}").toString();
        String jobNumber = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.jobNumber}").toString();

        StringBuffer strPayload = new StringBuffer();
        strPayload.append("{\n" + "  \"x\": {\n" + "    \"RESTHeader\": {\n" +
                          "      \"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" +
                          "      \"RespApplication\": \"ONT\",\n" + "      \"SecurityGroup\": \"STANDARD\",\n" +
                          "      \"NLSLanguage\": \"AMERICAN\",\n" + "      \"Org_Id\": \"82\"\n" + "    },\n" +
                          "    \"InputParameters\": {\n" + "      \"PORGCODE\": \"" + orgCode + "\",\n" +
                          "      \"PJOBOP\": \"" + jobNumber + "\",\n" + "      \"PCONTEXT\":\"\"\n" + "    }\n" +
                          "  }\n" + "}");


        String jsonArrayAsString = restCallerUtil.invokeUPDATE(RestURI.PostGetOpSeq(), strPayload.toString());
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
                    opSeqList.add(opSeqObj);

                }
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

    public static String getOpCode(String opSeq) {
        for (OpSeqBO opseqObj : opSeqList) {
            String localOpSeq = opseqObj.getOpSeq().toString();
            if (localOpSeq.equals(opSeq)) {
                return opseqObj.getOPcode();
            }
        }
        return null;
    }

    public static String getDept(String opSeq) {
        for (OpSeqBO opseqObj : opSeqList) {
            String localOpSeq = opseqObj.getOpSeq().toString();
            if (localOpSeq.equals(opSeq)) {
                return opseqObj.getDeptCode();
            }
        }
        return null;
    }
}
