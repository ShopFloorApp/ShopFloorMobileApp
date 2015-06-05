package dcom.shop.application.dc.dispatch;

import Decoder.BASE64Decoder;

import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.el.MethodExpression;

import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class AttachmentDC {
    public AttachmentDC() {
        super();
    }

    public String getBlobData() {
        String filePath = AdfmfJavaUtilities.getDirectoryPathRoot(AdfmfJavaUtilities.TemporaryDirectory);
        String fileName = null;
        byte[] bytes = null;
        RestCallerUtil restCallerUtil = new RestCallerUtil();
        String orgCode =
            AdfmfJavaUtilities.evaluateELExpression("#{preferenceScope.feature.dcom.shop.MyWarehouse.OrgCodePG.OrgCode}").toString();
        String fileId = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.fileId}").toString();

        String fileNameStr = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.fileName}").toString();
        String fileType = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.fileType}").toString();
        if ("SHORT TEXT".equals(fileType.toUpperCase())) {
            String shortText = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.TextToDisplay}").toString();
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(), "showAlert", new Object[] {
                                                                      "Short Text", shortText, "ok"
            });
        } else {
            if (!("".equals(fileNameStr))) {
                StringBuffer strPayload = new StringBuffer();
                strPayload.append("{\n" + "  \"x\": {\n" + "    \"RESTHeader\": {\n" +
                                  "      \"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" +
                                  "      \"RespApplication\": \"ONT\",\n" + "      \"SecurityGroup\": \"STANDARD\",\n" +
                                  "      \"NLSLanguage\": \"AMERICAN\",\n" + "      \"Org_Id\": \"82\"\n" + "    },\n" +
                                  "    \"InputParameters\": {\n" + "      \"P_FILE_ID\": \"" + fileId + "\",\n" +
                                  "      \"P_FILE_TYPE\": \"" + fileType + "\"\n" + "    }\n" + "  }\n" + "}");

                String jsonArrayAsString =
                    restCallerUtil.invokeUPDATE(RestURI.PostGetBlobData(), strPayload.toString());
                if (jsonArrayAsString != "") {
                    JSONObject jsObjectParent = null;

                    try {
                        JSONParser parser = new JSONParser();
                        Object object;
                        object = parser.parse(jsonArrayAsString);
                        JSONObject jsonObject = (JSONObject) object;
                        JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                        String status = (String) jsObject.get("XSTATUS");
                        if ("S".equals(status)) {
                            String fileData = (String) jsObject.get("P_FILE_DATA");
                            if ("LONG TEXT".equals(fileType.toUpperCase())) {
                                AdfmfJavaUtilities.setELValue("#{pageFlowScope.TextToDisplay}", fileData);
                                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                                          "showAlert", new Object[] {
                                                                                          "Long Text", fileData, "ok"
                                });
                            } else {
                                AdfmfJavaUtilities.setELValue("#{pageFlowScope.RenderPopup}", false);
                                BASE64Decoder decode = new BASE64Decoder();
                                bytes = decode.decodeBuffer(fileData);
                                System.out.println(" bytes is " + bytes);
                                //AdfmfJavaUtilities.decryptStringBase64(fileData);
                                fileName = (String) jsObject.get("P_FILE_NAME");
                                //bytes = (fileData.getBytes());
                                System.out.println(" bytes is " + bytes);
                                File file = new File(filePath + "/" + fileName);
                                System.out.println("After File");
                                if (file.exists()) {
                                    file.delete();
                                }
                                // if file doesnt exists, then create it
                                if (!file.exists()) {
                                    try {
                                        System.out.println("Creating File");
                                        file.createNewFile();
                                        FileOutputStream out = new FileOutputStream(file);
                                        //byte [] array = b ( 1, ( int ) ItemResponse.getFileBlobData().length() );
                                        System.out.println("Writing File");
                                        out.write(bytes);
                                        out.close();
                                    } catch (IOException io) {
                                        System.out.println("io" + io.getStackTrace());

                                    }
                                }
                                AdfmfJavaUtilities.setELValue("#{pageFlowScope.filePath}",
                                                              "file://" + filePath + "/" + fileName);

                                MethodExpression me =
                                    AdfmfJavaUtilities.getMethodExpression("#{bindings.displayFile.execute}",
                                                                           Object.class, new Class[] { });
                                me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
                                return "";
                            }
                        }
                    } catch (Exception e) {
                        e.getMessage();
                    }
                }
                return "";
            } else {
                return "TextPage";
            }
        }
        return "";
    }
}

