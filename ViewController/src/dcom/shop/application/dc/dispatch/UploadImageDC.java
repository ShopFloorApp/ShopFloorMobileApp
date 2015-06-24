package dcom.shop.application.dc.dispatch;

import dcom.shop.application.dc.dispatch.TransactDC.TrxResult;
import dcom.shop.application.mobile.dispatch.ComponentsBO;
import dcom.shop.application.mobile.dispatch.UploadImageBO;

import dcom.shop.restURIDetails.RestCallerUtil;

import dcom.shop.restURIDetails.RestURI;

import java.io.UnsupportedEncodingException;

import java.net.URLEncoder;

import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;

import oracle.adf.model.datacontrols.device.DeviceManager;

import oracle.adf.model.datacontrols.device.DeviceManagerFactory;

import oracle.adfmf.dc.ws.rest.RestServiceAdapter;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.logging.Trace;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class UploadImageDC {
    static List<UploadImageBO> imagesList = new ArrayList<UploadImageBO>();

    TrxResult trxResult = null;

    class TrxResult {
        String status;
        String message;

        public TrxResult(String statusStr, String messageStr) {
            this.status = statusStr;
            this.message = messageStr;
        }

        public String getStatus() {
            if (this.status.contains("@xsi")) {
                return "";
            }
            return status;
        }

        public String getMessage() {
            if (this.message.contains("@xsi")) {
                return "";
            }
            return message;
        }

        String getResult() {
            if (getStatus().equals("S")) {
                return "Transaction Successful!";
            } else {
                return getMessage();
            }
        }
    }

    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);

    public void addProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.addProviderChangeListener(l);
    }

    public void removeProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.removeProviderChangeListener(l);
    }

    public UploadImageDC() {
        super();
    }

    public UploadImageBO[] getUploadImage() {
        UploadImageBO[] imagesArray = imagesList.toArray(new UploadImageBO[imagesList.size()]);
        return imagesArray;
    }

    public void addImage() {
        DeviceManager dm = DeviceManagerFactory.getDeviceManager();
        String imageDataB64 =
            dm.getPicture(40, DeviceManager.CAMERA_DESTINATIONTYPE_DATA_URL,
                          DeviceManager.CAMERA_SOURCETYPE_CAMERA, true, DeviceManager.CAMERA_ENCODINGTYPE_PNG, 100,
                          100);
        /*try {
            imageDataB64 = URLEncoder.encode(imageDataB64, java.nio.charset.StandardCharsets.UTF_8.toString());
        } catch (Exception e) {
            Trace.log("REST_JSON", Level.SEVERE, this.getClass(), "invokeRestRequest",
                      "Invoke of REST Resource failed for " + RestServiceAdapter.REQUEST_TYPE_POST + " to ");
            Trace.log("REST_JSON", Level.SEVERE, this.getClass(), "invokeRestRequest", e.getLocalizedMessage());
        }*/

        //Fetch Value of Job Number and Job Op
        String jobOps = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.jobOps}").toString();
        String jobNumber = AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.jobNumber}").toString();
        java.util.Date date = new java.util.Date();
        String fileName = jobNumber + ":" + jobOps + ":" + date.getTime();
        UploadImageBO newImage = new UploadImageBO("PNG", "", imageDataB64, fileName);
        imagesList.add(newImage);
        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(), "showAlert", new Object[] {
                                                                  "Success", "Image clicked, Please click on upload button to store on server", "ok"
        });
        providerChangeSupport.fireProviderRefresh("uploadImage");
    }

    public String uploadImage(UploadImageBO imageObj) {
        StringBuffer strPayload = new StringBuffer();
        RestCallerUtil restCallerUtil = new RestCallerUtil();

        strPayload.append("{\n" + "  \"PROCESSINVTXN_Input\": {\n" + "    \"RESTHeader\": {\n" +
                          "      \"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" +
                          "      \"RespApplication\": \"ONT\",\n" + "      \"SecurityGroup\": \"STANDARD\",\n" +
                          "      \"NLSLanguage\": \"AMERICAN\",\n" + "      \"Org_Id\": \"82\"\n" + "    },\n" +
                          "    \"InputParameters\": {\n" + "      \"P_FILE_NAME\": \"" + imageObj.getFileName() +
                          "\",\n" + "      \"P_FILE_TYPE\": \"" + imageObj.getFileType() + "\",\n" +
                          "      \"P_DESCRIPTION\": \"" + imageObj.getImageDescription() + "\",\n" +
                          "      \"P_FILE_DATA\": \"" + imageObj.getImageDataB64() + "\"\n" + "    }\n" + "  }\n" +
                          "}");
        String jsonArrayAsString = restCallerUtil.invokeUPDATE(RestURI.UploadImage(), strPayload.toString());
        if (jsonArrayAsString != null) {
            try {
                JSONParser parser = new JSONParser();
                Object object;
                object = parser.parse(jsonArrayAsString);
                JSONObject jsonObject = (JSONObject) object;
                JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                String status = jsObject.get("XSTATUS").toString();
                String msg = jsObject.get("XMSG").toString();
                trxResult = new TrxResult(status, msg);
                return trxResult.getResult();
            } catch (Exception e) {
                e.getMessage();
            }
        }
        trxResult = new TrxResult("E", "File is successfully uploaded!");
        return trxResult.getResult();
    }

    public void rollback() {
        if (imagesList != null) {
            imagesList.clear();
        }
        if (trxResult != null) {
            trxResult = null;
        }
    }

    public void deleteImage(UploadImageBO imageObj) {
        for (UploadImageBO uploadImageBO : imagesList) {
            if (imageObj.getFileName().equals(uploadImageBO.getFileName()))
                imagesList.remove(uploadImageBO);
            break;
        }
        providerChangeSupport.fireProviderRefresh("uploadImage");
    }
}
