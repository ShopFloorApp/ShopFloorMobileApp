package dcom.shop.application.dc;

import dcom.shop.application.mobile.LPNBO;
import dcom.shop.application.mobile.PickTxnBO;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.math.BigDecimal;

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

public class PickTxnDC {
    public PickTxnDC() {
        super();
    }
    List s_PickTxnList = new ArrayList();
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

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

    public PickTxnBO getPickResult() {
        PickTxnBO[] pickTxn = (PickTxnBO[]) s_PickTxnList.toArray(new PickTxnBO[s_PickTxnList.size()]);
        return pickTxn[0];
    }

    public void getPick() {
        ValueExpression ve = null;
        s_PickTxnList.clear();

        System.out.println("Inside lpn search");
        Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
        String order = null;
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.salesOrderNumber}", String.class);
        order = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.moveOrderNumber}", String.class);
        String moveOrder = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.deliveryNumber}", String.class);
        String delivery = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.taskId}", String.class);
        String pickId = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        String restURI = RestURI.PostGetPickURI();
        RestCallerUtil rcu = new RestCallerUtil();
        String orgCode =
            (String) AdfmfJavaUtilities.evaluateELExpression("#{preferenceScope.feature.dcom.shop.MyWarehouse.OrgCodePG.OrgCode}");

        String payload =
            "{\n" + "  \"Input_Parameters\": {\n" + "    \"RESTHeader\": {\n" +
            "      \"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" + "      \"RespApplication\": \"ONT\",\n" +
            "      \"SecurityGroup\": \"STANDARD\",\n" + "      \"NLSLanguage\": \"AMERICAN\",\n" +
            "      \"Org_Id\": \"82\"\n" + "    },\n" + "    \"InputParameters\": {\n" + "        \"PORGCODE\": \"" +
            orgCode + "\",\n" + "        \"PORDER\": \"" + order + "\",\n" + "        \"PMOVEORDER\": \"" + moveOrder +
            "\",\n" + "        \"PDELIVERY\": \"" + delivery + "\",\n" + "        \"PPICKID\": \"" + pickId + "\"\n" +
            "}\n" + "    }\n" + "  }\n";
        System.out.println("Calling create method");
        String jsonArrayAsString = (rcu.invokeUPDATE(restURI, payload)).toString();
        System.out.println("Received response");
        PickTxnBO[] lpnArray = null;
        try {
            JSONParser parser = new JSONParser();
            Object object = parser.parse(jsonArrayAsString);
            JSONObject jsonObject = (JSONObject) object;
            JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
           // JSONObject jsObject1 = (JSONObject) jsObject.get("XLPN");
            try {
                JSONArray array = (JSONArray) jsObject.get("XPICKLOAD");

                if (array != null) {
                    int size = array.size();
                    //  ProductSearchEntity[] prodItems= new ProductSearchEntity[size];
                    for (int i = 0; i < size; i++) {


                        PickTxnBO pickItems = new PickTxnBO();
                        //  JSONObject jsObjectArrayData = (JSONObject) jsObject1.get("XLPNRES_ITEM");
                        JSONObject jsObjectArrayData = (JSONObject) array.get(i);

                        pickItems.setITEM((jsObjectArrayData.get("ITEM").toString()));
                        pickItems.setITEMDESC((jsObjectArrayData.get("ITEMDESC").toString()));
                        pickItems.setTXNUOM((jsObjectArrayData.get("TXNUOM").toString()));
                        pickItems.setTXNQTY(new BigDecimal((jsObjectArrayData.get("SUBINV").toString())));
                        pickItems.setPRIMARYUOM((jsObjectArrayData.get("PRIMARYUOM").toString()));
                        pickItems.setSUBINV((jsObjectArrayData.get("SUBINV").toString()));
                        pickItems.setPICKID(new BigDecimal((jsObjectArrayData.get("PICKID").toString())));
                        pickItems.setTOSUBINV((jsObjectArrayData.get("TOSUBINV").toString()));
                        pickItems.setTOLOCATOR((jsObjectArrayData.get("TOLOCATOR").toString()));
                        pickItems.setSUBREST((jsObjectArrayData.get("SUBREST").toString()));
                        pickItems.setLOCREST((jsObjectArrayData.get("LOCREST").toString()));
                        pickItems.setLOCATORCODE((jsObjectArrayData.get("LOCATORCODE").toString()));
                        pickItems.setTASKID(new BigDecimal((jsObjectArrayData.get("TASKID").toString())));
                        pickItems.setTRXHDRID(new BigDecimal((jsObjectArrayData.get("TRXHDRID").toString())));
                        pickItems.setPACKSLIPNUM((jsObjectArrayData.get("PACKSLIPNUM").toString()));
                        pickItems.setLOCATOR((jsObjectArrayData.get("LOCATOR").toString()));
                        pickItems.setLOTCONTROL((jsObjectArrayData.get("LOTCONTROL").toString()));
                        pickItems.setLOTALLOC((jsObjectArrayData.get("LOTALLOC").toString()));
                        pickItems.setSERIALCONTROL((jsObjectArrayData.get("SERIALCONTROL").toString()));
                        pickItems.setSERIALALLOC((jsObjectArrayData.get("SERIALALLOC").toString()));
                        pickItems.setLPN((jsObjectArrayData.get("LPN").toString()));

                        s_PickTxnList.add(pickItems);
                    }


                }
            } catch (ClassCastException e2) {
                JSONObject jsObject2 = (JSONObject) jsObject.get("XPICKLOAD");
                if (jsObject2 != null) {


                    PickTxnBO pickItems = new PickTxnBO();
                    // JSONObject jsObject2 = (JSONObject)
                    String item = jsObject2.get("ITEM").toString();
                    pickItems.setITEM(item);
                    String itemDesc = jsObject2.get("ITEMDESC").toString();
                    pickItems.setITEMDESC(itemDesc);
                    String txnUom = jsObject2.get("TXNUOM").toString();
                    pickItems.setTXNUOM(txnUom);
                    BigDecimal txnQty = new BigDecimal(jsObject2.get("TXNQTY").toString());
                    pickItems.setTXNQTY(txnQty);
                    pickItems.setPRIMARYUOM((jsObject2.get("PRIMARYUOM").toString()));
                    String subInv = jsObject2.get("SUBINV").toString();
                    pickItems.setSUBINV(subInv);
                    BigDecimal PickId = new BigDecimal(jsObject2.get("PICKID").toString());
                    pickItems.setPICKID(PickId);
                    pickItems.setTOSUBINV((jsObject2.get("TOSUBINV").toString()));
                    pickItems.setTOLOCATOR((jsObject2.get("TOLOCATOR").toString()));
                    pickItems.setSUBREST((jsObject2.get("SUBREST").toString()));
                    pickItems.setLOCREST((jsObject2.get("LOCREST").toString()));
                    pickItems.setLOCATORCODE((jsObject2.get("LOCATORCODE").toString()));
                    pickItems.setTASKID(new BigDecimal((jsObject2.get("TASKID").toString())));
                    pickItems.setTRXHDRID(new BigDecimal((jsObject2.get("TRXHDRID").toString())));
                    pickItems.setPACKSLIPNUM((jsObject2.get("PACKSLIPNUM").toString()));
                    String locator = jsObject2.get("LOCATOR").toString();
                    pickItems.setLOCATOR(locator);
                    String lotControl = jsObject2.get("LOTCONTROL").toString();
                    pickItems.setLOTCONTROL(lotControl);
                    pickItems.setLOTALLOC((jsObject2.get("LOTALLOC").toString()));
                    String serialControl = jsObject2.get("SERIALCONTROL").toString();
                    pickItems.setSERIALCONTROL(serialControl);
                    pickItems.setSERIALALLOC((jsObject2.get("SERIALALLOC").toString()));
                    String lpn = jsObject2.get("LPN").toString();
                    pickItems.setLPN(lpn);

                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.PickSubinv", subInv);
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.PickLocator", locator);
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchLpnKeyword", lpn);
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchKeyword", item);
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.PickQty", txnQty);
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.SerialControl", serialControl);
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.LotControl", lotControl);
                    s_PickTxnList.add(pickItems);


                }
            }
            ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.PickTxnResults}", String.class);

            if (s_PickTxnList.size() > 0) {
                ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "Search results");
            } else {
                ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "No Search results");

            }

        } catch (Exception e) {
            Trace.log("REST_JSON", Level.SEVERE, this.getClass(), "PickTxnBO", e.getLocalizedMessage());
        }

    }
}
