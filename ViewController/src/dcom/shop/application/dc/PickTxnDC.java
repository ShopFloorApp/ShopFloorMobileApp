package dcom.shop.application.dc;

import dcom.shop.application.mobile.LPNBO;
import dcom.shop.application.mobile.PickTxnBO;
import dcom.shop.application.mobile.transaction.SubInventoryTxnBO;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.logging.Level;

import javax.el.ValueExpression;

import oracle.adfmf.framework.api.AdfmfContainerUtilities;
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
                    String primaryUom = (jsObject2.get("PRIMARYUOM").toString());
                    pickItems.setPRIMARYUOM(primaryUom);
                    String subInv = jsObject2.get("SUBINV").toString();
                    pickItems.setSUBINV(subInv);
                    BigDecimal PickId = new BigDecimal(jsObject2.get("PICKID").toString());
                    pickItems.setPICKID(PickId);
                    String toSubinv = (jsObject2.get("TOSUBINV").toString());
                    pickItems.setTOSUBINV(toSubinv);
                    String toLocator = (jsObject2.get("TOLOCATOR").toString());
                    pickItems.setTOLOCATOR(toLocator);
                    String subRest = (jsObject2.get("SUBREST").toString());
                    pickItems.setSUBREST(subRest);
                    String locRest = (jsObject2.get("LOCREST").toString());
                    pickItems.setLOCREST(locRest);
                    String locCode = (jsObject2.get("LOCATORCODE").toString());
                    pickItems.setLOCATORCODE(locCode);
                    BigDecimal taskId = new BigDecimal((jsObject2.get("TASKID").toString()));
                    pickItems.setTASKID(taskId);
                    BigDecimal trxnHdrId = new BigDecimal((jsObject2.get("TRXHDRID").toString()));
                    pickItems.setTRXHDRID(trxnHdrId);
                    String packSlipNum = (jsObject2.get("PACKSLIPNUM").toString());
                    pickItems.setPACKSLIPNUM(packSlipNum);
                    String locator = jsObject2.get("LOCATOR").toString();
                    pickItems.setLOCATOR(locator);
                    String lotControl = jsObject2.get("LOTCONTROL").toString();
                    pickItems.setLOTCONTROL(lotControl);
                    String lotAlloc = (jsObject2.get("LOTALLOC").toString());
                    pickItems.setLOTALLOC(lotAlloc);
                    String serialControl = jsObject2.get("SERIALCONTROL").toString();
                    pickItems.setSERIALCONTROL(serialControl);
                    String serialAlloc = (jsObject2.get("SERIALALLOC").toString());
                    pickItems.setSERIALALLOC(serialAlloc);
                    String lpn = jsObject2.get("LPN").toString();
                    pickItems.setLPN(lpn);

                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.PrimaryUom}",
                                                  primaryUom.contains("@xsi") ? "" : primaryUom);
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.SubRest}", subRest.contains("@xsi") ? "" : subRest);
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.LocRest}", locRest.contains("@xsi") ? "" : locRest);
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.LocatorCode}",
                                                  locCode.contains("@xsi") ? "" : locCode);
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.PackSlipNum}",
                                                  packSlipNum.contains("@xsi") ? "" : packSlipNum);
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.LotAlloc}",
                                                  lotAlloc.contains("@xsi") ? "" : lotAlloc);
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.SerialAlloc}",
                                                  serialAlloc.contains("@xsi") ? "" : serialAlloc);
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.PickTaskId}", taskId);
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.PickId}", pickId);
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.FromSubinventory}",
                                                  subInv.contains("@xsi") ? "" : subInv);
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.FromLocator}",
                                                  locator.contains("@xsi") ? "" : locator);
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.ToSubinventory}",
                                                  toSubinv.contains("@xsi") ? "" : toSubinv);
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.ToLocator}",
                                                  toLocator.contains("@xsi") ? "" : toLocator);
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchLpnKeyword}", lpn.contains("@xsi") ? "" : lpn);
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchKeyword}", item.contains("@xsi") ? "" : item);
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.ItemDesc}",
                                                  itemDesc.contains("@xsi") ? "" : itemDesc);
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.PickQty}", txnQty);
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.PickTrxHdrId}", trxnHdrId);
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.SerialControl}",
                                                  serialControl.contains("@xsi") ? "" : serialControl);
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.LotControl}",
                                                  lotControl.contains("@xsi") ? "" : lotControl);
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

    public void loadPick() {
        ValueExpression ve = null;

        System.out.println("Inside lpn search");
        Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
        String pTempId = null;
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.PickTrxHdrId}", String.class);
        pTempId = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());

        String primaryUom = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.PrimaryUom}");
        String subRest = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.SubRest}");
        String locRest = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.LocRest}");
        String locCode = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.LocatorCode}");
        String packSlipNum = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.PackSlipNum}");
        String lotAlloc = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.LotAlloc}");
        String serialAlloc = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.SerialAlloc}");
        String taskId = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.PickTaskId}");
        String subInv = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.FromSubinventory}");
        String locator = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.FromLocator}");
        String toSubinv = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.ToSubinventory}");
        String toLocator = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.ToLocator}");
        String lpn = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.searchLpnKeyword}");
        String item = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.searchKeyword}");
        String itemDesc = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.ItemDesc}");
        String txnQty = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.PickQty}");
        String trxnHdrId = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.PickTrxHdrId}");
        String serialControl = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.SerialControl}");
        String lotControl = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.LotControl}");
        String pickId = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.PickId}");
        String xferlpn = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.XferLPN}");
        

        String restURI = RestURI.PostLoadPickURI();
        RestCallerUtil rcu = new RestCallerUtil();
        String orgCode =
            (String) AdfmfJavaUtilities.evaluateELExpression("#{preferenceScope.feature.dcom.shop.MyWarehouse.OrgCodePG.OrgCode}");

        String payload =
            "{\n" + "  \"Input_Parameters\": {\n" + "    \"RESTHeader\": {\n" +
            "      \"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" + "      \"RespApplication\": \"ONT\",\n" +
            "      \"SecurityGroup\": \"STANDARD\",\n" + "      \"NLSLanguage\": \"AMERICAN\",\n" +
            "      \"Org_Id\": \"82\"\n" + "    },\n" + "    \"InputParameters\": {\n" + "        \"PORGCODE\": \"" +
            orgCode + "\",\n" + "        \"PPICKLOAD\": { \"ITEM\": \"" + item + "\",\n" + "\"ITEMDESC\": \"" +
            itemDesc + "\",\n" + "\"TXNUOM\": \"" + primaryUom + "\",\n" + "\"TXNQTY\": \"" + txnQty + "\",\n" +
            "\"PRIMARYUOM\": \"" + primaryUom + "\",\n" + "\"SUBINV\": \"" + subInv + "\",\n" + "\"PICKID\": \"" +
            pickId + "\",\n" + "\"TOSUBINV\": \"" + toSubinv + "\",\n" + "\"TOLOCATOR\": \"" + toLocator + "\",\n" +
            "\"SUBREST\": \"" + subRest + "\",\n" + "\"LOCREST\": \"" + locRest + "\",\n" + "\"LOCATORCODE\": \"" +
            locCode + "\",\n" + "\"TASKID\": \"" + taskId + "\",\n" + "\"TRXHDRID\": \"" + trxnHdrId + "\",\n" +
            "\"PACKSLIPNUM\": \"" + packSlipNum + "\",\n" + "\"LOCATOR\": \"" + locator + "\",\n" +
            "\"LOTCONTROL\": \"" + lotControl + "\",\n" + "\"LOTALLOC\": \"" + lotAlloc + "\",\n" +
            "\"SERIALCONTROL\": \"" + serialControl + "\",\n" + "\"SERIALALLOC\": \"" + serialAlloc + "\",\n" +
            "\"LPN\": \"" + lpn + "\",\n"+
            "\"XFERLPN\": \"" + xferlpn + "\"\n" + "}\n }\n" + "    }\n" + "  }\n";
        System.out.println("Calling create method");
        try {
            String jsonArrayAsString = (rcu.invokeUPDATE(restURI, payload)).toString();
            System.out.println("Received response");

            if (jsonArrayAsString != null) {
                JSONObject jsObject1 = null;
                JSONParser parser = new JSONParser();
                Object object;

                object = parser.parse(jsonArrayAsString);

                JSONObject jsonObject = (JSONObject) object;
                JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                String result = jsObject.get("XSTATUS").toString();
                if ("S".equals(result)) {
                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                              "showAlert", new Object[] {
                                                                              "Success",
                                                                              "Transaction has been submitted successfully.",
                                                                              "ok"
                    });
                } else {
                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                              "showAlert", new Object[] {
                                                                              "Error",
                                                                              "Transaction submission failed. \n" +
                                                                              jsObject.get("XMSG").toString(), "ok"
                    });
                }
            } else {
                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                          "showAlert", new Object[] {
                                                                          "Error", "Transaction submission failed.",
                                                                          "ok"
                });
            }
        } catch (Exception e) {
            System.out.println("error " + e.toString());
        }
    }

    public void getInfo() {
        ValueExpression ve = null;

        System.out.println("Inside lpn search");
        Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
        String pTempId = null;
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.PickTrxHdrId}", String.class);
        pTempId = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());

        String restURI = RestURI.PostGetInfoURI();
        RestCallerUtil rcu = new RestCallerUtil();
        String orgCode =
            (String) AdfmfJavaUtilities.evaluateELExpression("#{preferenceScope.feature.dcom.shop.MyWarehouse.OrgCodePG.OrgCode}");

        String payload =
            "{\n" + "  \"Input_Parameters\": {\n" + "    \"RESTHeader\": {\n" +
            "      \"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" + "      \"RespApplication\": \"ONT\",\n" +
            "      \"SecurityGroup\": \"STANDARD\",\n" + "      \"NLSLanguage\": \"AMERICAN\",\n" +
            "      \"Org_Id\": \"82\"\n" + "    },\n" + "    \"InputParameters\": {\n" + "        \"PORGCODE\": \"" +
            orgCode + "\",\n" + "        \"PTEMPID\": \"" + pTempId + "\"" + "}\n" + "    }\n" + "  }\n";
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

            JSONObject jsObject2 = (JSONObject) jsObject.get("XINFO");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.InfoDeliveryId}", "DELI");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.InfoOrderNumber}", "ORD");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.InfoPartyName}", "PART");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.InfoAccountNum}", "ACC");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.InfoShipTo}", "SHIP");
            String obj = jsObject2.get(0).toString();

            if (jsObject2 != null && !(obj.contains("@xsi"))) {


                // JSONObject jsObject2 = (JSONObject)
                BigDecimal deliveryId = new BigDecimal(jsObject2.get("DELIVERYID").toString());
                BigDecimal orderNumber = new BigDecimal(jsObject2.get("ORDERNUMBER").toString());
                String partyName = jsObject2.get("PARTYNAME").toString();
                String accountNum = jsObject2.get("ACCOUNTUM").toString();
                String shipTo = jsObject2.get("SHIPTO").toString();

                AdfmfJavaUtilities.setELValue("#{pageFlowScope.InfoDeliveryId}", deliveryId);
                AdfmfJavaUtilities.setELValue("#{pageFlowScope.InfoOrderNumber}", orderNumber);
                AdfmfJavaUtilities.setELValue("#{pageFlowScope.InfoPartyName}",
                                              partyName.contains("@xsi") ? "" : partyName);
                AdfmfJavaUtilities.setELValue("#{pageFlowScope.InfoAccountNum}",
                                              accountNum.contains("@xsi") ? "" : accountNum);
                AdfmfJavaUtilities.setELValue("#{pageFlowScope.InfoShipTo}", shipTo.contains("@xsi") ? "" : shipTo);
            }


        } catch (Exception e) {
            Trace.log("REST_JSON", Level.SEVERE, this.getClass(), "PickTxnBO", e.getLocalizedMessage());
        }

    }
}

