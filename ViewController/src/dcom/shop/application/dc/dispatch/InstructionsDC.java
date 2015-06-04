package dcom.shop.application.dc.dispatch;

import dcom.shop.application.base.AViewObject;
import dcom.shop.application.mobile.SubinventoryBO;
import dcom.shop.application.mobile.dispatch.ComponentsBO;

import dcom.shop.application.mobile.dispatch.InstructionsBO;

import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class InstructionsDC extends AViewObject {
    protected static List<InstructionsBO> instructionlist = new ArrayList<InstructionsBO>();

    public InstructionsDC() {
        super();
    }

    public InstructionsBO[] getInstructionsBO() {
        instructionlist.clear();
        RestCallerUtil restCallerUtil = new RestCallerUtil();
        InstructionsBO[] instructionsArray = null;
        String orgCode =
            AdfmfJavaUtilities.evaluateELExpression("#{preferenceScope.feature.dcom.shop.MyWarehouse.OrgCodePG.OrgCode}").toString();
        String deptCode = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.deptName}").toString();
        String jobOpsVal = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.jobOps}").toString();
        jobOpsVal = jobOpsVal.substring(0, jobOpsVal.indexOf(":"));

        StringBuffer strPayload = new StringBuffer();
        strPayload.append("{\n" + "  \"x\": {\n" + "    \"RESTHeader\": {\n" +
                          "      \"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" +
                          "      \"RespApplication\": \"ONT\",\n" + "      \"SecurityGroup\": \"STANDARD\",\n" +
                          "      \"NLSLanguage\": \"AMERICAN\",\n" + "      \"Org_Id\": \"82\"\n" + "    },\n" +
                          "    \"InputParameters\": {\n" + "      \"PORGCODE\": \"" + orgCode + "\",\n" +
                          "      \"PDEPTCODE\": \"" + deptCode + "\",\n" + "      \"PJOBOP\": \"" + jobOpsVal + "\"\n" +
                          "    }\n" + "  }\n" + "}");

        String jsonArrayAsString = restCallerUtil.invokeUPDATE(RestURI.PostGetInstructions(), strPayload.toString());
        if (jsonArrayAsString != null) {
            JSONObject jsObjectParent = null;

            try {
                JSONParser parser = new JSONParser();
                Object object;
                object = parser.parse(jsonArrayAsString);
                JSONObject jsonObject = (JSONObject) object;
                JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                jsObjectParent = (JSONObject) jsObject.get("XJOBINSTR");
                JSONArray instructionJsonArray = (JSONArray) jsObjectParent.get("XJOBINSTR_ITEM");

                if (instructionJsonArray != null) {
                    int size = instructionJsonArray.size();
                    InstructionsBO instr;
                    for (int i = 0; i < size; i++) {
                        instr = new InstructionsBO();
                        JSONObject resrcJson = (JSONObject) instructionJsonArray.get(i);

                        //Populating the base object
                        instr.setSEQ(resrcJson.get("SEQ").toString());
                        instr.setCATEGDESC(resrcJson.get("CATEGDESC").toString());
                        instr.setTITLE(resrcJson.get("TITLE").toString());
                        instr.setDOCDESC(resrcJson.get("DOCDESC").toString());
                        instr.setDATATYPE(resrcJson.get("DATATYPE").toString());
                        instr.setFILENAME(resrcJson.get("FILENAME").toString());
                        instr.setURL(resrcJson.get("URL").toString());
                        instr.setIMAGETYPE(resrcJson.get("IMAGETYPE").toString());
                        instr.setSHORTTEXT(resrcJson.get("SHORTTEXT").toString());
                        instr.setCREATIONDATE(resrcJson.get("CREATIONDATE").toString());
                        instr.setCREATEDBY(resrcJson.get("CREATEDBY").toString());
                        instructionlist.add(instr);
                    }
                }
          
            } catch (ClassCastException e2) {
                JSONObject resrcJson = (JSONObject) jsObjectParent.get("XJOBINSTR_ITEM");
                InstructionsBO instr = new InstructionsBO();

                if (resrcJson != null) {
                    instr.setSEQ(resrcJson.get("SEQ").toString());
                    instr.setCATEGDESC(resrcJson.get("CATEGDESC").toString());
                    instr.setTITLE(resrcJson.get("TITLE").toString());
                    instr.setDOCDESC(resrcJson.get("DOCDESC").toString());
                    instr.setDATATYPE(resrcJson.get("DATATYPE").toString());
                    instr.setFILENAME(resrcJson.get("FILENAME").toString());
                    instr.setURL(resrcJson.get("URL").toString());
                    instr.setIMAGETYPE(resrcJson.get("IMAGETYPE").toString());
                    instr.setSHORTTEXT(resrcJson.get("SHORTTEXT").toString());
                    instr.setCREATIONDATE(resrcJson.get("CREATIONDATE").toString());
                    instr.setCREATEDBY(resrcJson.get("CREATEDBY").toString());
                    instructionlist.add(instr);
                }
            } catch (Exception e) {
                e.getMessage();
            }
        }
        instructionsArray = instructionlist.toArray(new InstructionsBO[instructionlist.size()]);
        
        return instructionsArray;
    }
}
