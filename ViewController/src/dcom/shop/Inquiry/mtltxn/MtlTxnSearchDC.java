package dcom.shop.Inquiry.mtltxn;

import dcom.shop.application.base.AEntity;
import dcom.shop.restURIDetails.RestURI;

import dcom.shop.restURIDetails.RestCallerUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.logging.Level;

import javax.el.ValueExpression;

import javax.xml.transform.SourceLocator;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.JSONBeanSerializationHelper;

import oracle.adfmf.util.Utility;
import oracle.adfmf.util.logging.Trace;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class MtlTxnSearchDC {
    public MtlTxnSearchDC() {
        super();
    }
    List s_mtlTxnList = new ArrayList();

    public MtlTxnSearchEntity[] getAllmtlTxn() {
        s_mtlTxnList.clear();
        ValueExpression ve = null;

        System.out.println("Inside mtl txn search");
        Utility.ApplicationLogger.info("Inside script dcomShopFloor.db");
        String item = null;
        String subinv = null;
        String locator = null;
        String lpn = null;
        String txnType = null;
       // Date startDate = null;
   //     Date endDate = null;

        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchKeyword}", String.class);
        item = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();

        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.FromSubinventory}", String.class);
        subinv = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();

        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.FromLocator}", String.class);
        locator = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();

        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchLpnKeyword}", String.class);
        lpn = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();

        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.txnType}", String.class);
        txnType = ((String) ve.getValue(AdfmfJavaUtilities.getAdfELContext())).trim();

      /*  ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.MtlTrxnStartDate}", Date.class);
        startDate = ((Date) ve.getValue(AdfmfJavaUtilities.getAdfELContext()));

        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.MtlTrxnEndDate}", Date.class);
        endDate = ((Date) ve.getValue(AdfmfJavaUtilities.getAdfELContext()));*/
      String startDate = null;
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.MtlTrxnStartDate}", String.class);
        startDate = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        
        if (startDate == null){
            startDate = "";
        }
        else{
          startDate = toDateTime(startDate);
            if (startDate == null){
                startDate = "";
            }
        }
        String endDate = null;
          ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.MtlTrxnEndDate}", String.class);
          endDate = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());
          
          if (endDate==null){
              endDate = "";
          }
          else {
            endDate =  toDateTime(endDate);
              if (endDate==null){
                  endDate = "";
              }
          }
       

        String orgCode = (String)AdfmfJavaUtilities.evaluateELExpression("#{preferenceScope.feature.dcom.shop.MyWarehouse.OrgCodePG.OrgCode}");
        
        String restURI = RestURI.PostMtlTxnInquiryURI();
        RestCallerUtil rcu = new RestCallerUtil();
        String payload =
            "{\"x\":\n" + "{\n" + "   \"RESTHeader\": {\"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" +
            "                  \"RespApplication\": \"ONT\",\n" +
            "                  \"SecurityGroup\": \"STANDARD\",\n" +
            "                  \"NLSLanguage\": \"AMERICAN\",\n" + "                  \"Org_Id\": \"82\"\n" +
            "                 },\n" + "   \"InputParameters\": \n" + "     {\"PMTLTXNREQ\": {\"TRXTYPE\": \"" +
            txnType + "\",\"DATEFROM\": \"" + startDate +"\",\"DATETO\": \"" + endDate +"\",\"ORGCODE\": \""+orgCode+"\",\"ITEM\": \"" + item + "\",\"SUBINV\": \"" + subinv +
            "\",\"LOCATOR\": \"" + locator + "\",\"LPN\": \"" + lpn + "\"}}\n" + "}\n" + "}";

        System.out.println("Calling create method");
        String jsonArrayAsString = (rcu.invokeUPDATE(restURI, payload)).toString();
        System.out.println("Received response");
        MtlTxnSearchEntity[] mtlTxnArray = null;
        //ashish
        try {
            JSONParser parser = new JSONParser();
            Object object = parser.parse(jsonArrayAsString);
            JSONObject jsonObject = (JSONObject) object;
            JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
            JSONObject jsObject1 = (JSONObject) jsObject.get("XMTLTXNRES");
            try {
                JSONArray array = (JSONArray) jsObject1.get("XMTLTXNRES_ITEM");

                if (array != null) {
                    int size = array.size();
                    //  ProductSearchEntity[] prodItems= new ProductSearchEntity[size];
                    for (int i = 0; i < size; i++) {


                        MtlTxnSearchEntity mtlTxnItems = new MtlTxnSearchEntity();
                        //  JSONObject jsObjectArrayData = (JSONObject) jsObject1.get("XLPNRES_ITEM");
                        JSONObject jsObjectArrayData = (JSONObject) array.get(i);
                      
                        mtlTxnItems.setITEM((jsObjectArrayData.get("ITEM").toString()));
                        mtlTxnItems.setSOURCSUBINV((jsObjectArrayData.get("SOURCSUBINV").toString()));
                        mtlTxnItems.setSOURCELOCATOR((jsObjectArrayData.get("SOURCELOCATOR").toString()));
                        mtlTxnItems.setTRXTYPE((jsObjectArrayData.get("TRXTYPE").toString()));
                        mtlTxnItems.setTRXREF(Integer.parseInt((jsObjectArrayData.get("TRXREF").toString())));
                        mtlTxnItems.setTRXQTY(Integer.parseInt((jsObjectArrayData.get("TRXQTY").toString())));
                        mtlTxnItems.setTXNUOM((jsObjectArrayData.get("TXNUOM").toString()));
                        
                        String txnDateStr = ((jsObjectArrayData.get("TRXDATE").toString())).substring(0, 10);
                        try{
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
                            Date date = formatter.parse(txnDateStr);
                            System.out.println(date);
                            System.out.println(formatter.format(date));
                            SimpleDateFormat formatter1 = new SimpleDateFormat("dd-mm-yyyy");
                            Date date1 = formatter1.parse(txnDateStr);
                            System.out.println(formatter1.format(date1));
                            mtlTxnItems.setTRXDATE(date1);
                        }
                        catch (ParseException e) {
                                e.printStackTrace();
                        }
                        

                        //   JSONObject onHandOptObj = (JSONObject) jsObject1.get("XONHANDRES_ITEM");
                        //    JSONObject costGrpObj = (JSONObject) onHandOptObj.get("XONHANDRES_ITEM");
                        //   onhandItems.setCOSTGROUP((costGrpObj.get("COSTGROUP").toString()));


                        s_mtlTxnList.add(mtlTxnItems);
                    }


                }
            } catch (ClassCastException e2) {
                JSONObject jsObject2 = (JSONObject) jsObject1.get("XMTLTXNRES_ITEM");
                if (jsObject2 != null) {


                    MtlTxnSearchEntity mtlTxnItems = new MtlTxnSearchEntity();
                    // JSONObject jsObject2 = (JSONObject)

                    mtlTxnItems.setITEM((jsObject2.get("ITEM").toString()));
                    mtlTxnItems.setSOURCSUBINV((jsObject2.get("SOURCSUBINV").toString()));
                    mtlTxnItems.setSOURCELOCATOR((jsObject2.get("SOURCELOCATOR").toString()));
                    mtlTxnItems.setTRXTYPE((jsObject2.get("TRXTYPE").toString()));
                    mtlTxnItems.setTRXREF(Integer.parseInt((jsObject2.get("TRXREF").toString())));
                    mtlTxnItems.setTRXQTY(Integer.parseInt((jsObject2.get("TRXQTY").toString())));
                    mtlTxnItems.setTXNUOM((jsObject2.get("TXNUOM").toString()));
                    String txnDateStr = ((jsObject2.get("TRXDATE").toString())).substring(0, 10);
                    try{
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
                        Date date = formatter.parse(txnDateStr);
                        System.out.println(date);
                        System.out.println(formatter.format(date));
                        SimpleDateFormat formatter1 = new SimpleDateFormat("dd-mm-yyyy");
                        Date date1 = formatter1.parse(txnDateStr);
                        System.out.println(formatter1.format(date1));
                        mtlTxnItems.setTRXDATE(date1);
                    }
                    catch (ParseException e) {
                            e.printStackTrace();
                    }

                   s_mtlTxnList.add(mtlTxnItems);


                }
            }

            mtlTxnArray = (MtlTxnSearchEntity[]) s_mtlTxnList.toArray(new MtlTxnSearchEntity[s_mtlTxnList.size()]);

        } catch (Exception e) {
            Trace.log("REST_JSON", Level.SEVERE, this.getClass(), "MtlTxnSearchEntity", e.getLocalizedMessage());
        }
        if(s_mtlTxnList.size()!=0){
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.MtlTxnSearchResults}", "");
        }else{
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.MtlTxnSearchResults}", "No Results Found");
        }
        return mtlTxnArray;
    }
    public String toDateTime(String value){
        SimpleDateFormat intialFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newDate = null;
        try {
            Date date = intialFormat.parse(value);
            newDate = newFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDate;
    }
}
