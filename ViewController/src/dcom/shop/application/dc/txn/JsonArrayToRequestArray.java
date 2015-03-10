package dcom.shop.application.dc.txn;

import dcom.shop.application.dc.txn.resultarray.ConcProgramParamLovResultArray;
import dcom.shop.application.dc.txn.resultarray.ConcProgramParamsResultArray;
import dcom.shop.application.dc.txn.resultarray.ConcProgramResultArray;
import dcom.shop.application.dc.txn.resultarray.RequestsResultArray;
import dcom.shop.application.mobile.txn.ConcProgramParamLovBO;
import dcom.shop.application.mobile.txn.ConcProgramParamsBO;
import dcom.shop.application.mobile.txn.ConcurrentProgramBO;

import dcom.shop.application.mobile.txn.RequestsBO;

import java.util.logging.Level;

import oracle.adfmf.framework.api.JSONBeanSerializationHelper;
import oracle.adfmf.util.logging.Trace;

public class JsonArrayToRequestArray {
    public JsonArrayToRequestArray() {
        super();
    }
    public static ConcurrentProgramBO[] getConcProgram(String jsonArrayAsString){
        
        //Java object that represents the Java structure for the JSON notation structure. Its simple in 
        //this sample as the array only contains an array of departments with no additional child objects 
        //or primitive values
        ConcProgramResultArray concProgramResultArray = null;
        
        //object that serializes the JSON payload into the Java object
        JSONBeanSerializationHelper jbsh = new JSONBeanSerializationHelper();


        try {
            concProgramResultArray = (ConcProgramResultArray) jbsh.fromJSON(ConcProgramResultArray.class, jsonArrayAsString);    


        } catch (Exception e) {
            Trace.log("JSONArray_to_JavaArray",Level.SEVERE, ConcProgramResultArray.class,"getConcProgram", "Parsing of REST response failed: "+e.getLocalizedMessage());
        }

      return concProgramResultArray.getXCPLIST().getXCPLIST_ITEM();
    }
    
    public static RequestsBO[] getRequests(String jsonArrayAsString){
        
        //Java object that represents the Java structure for the JSON notation structure. Its simple in 
        //this sample as the array only contains an array of departments with no additional child objects 
        //or primitive values
        RequestsResultArray requestsResultArray = null;
        
        //object that serializes the JSON payload into the Java object
        JSONBeanSerializationHelper jbsh = new JSONBeanSerializationHelper();


        try {
            requestsResultArray = (RequestsResultArray) jbsh.fromJSON(RequestsResultArray.class, jsonArrayAsString);    


        } catch (Exception e) {
            Trace.log("JSONArray_to_JavaArray",Level.SEVERE, RequestsResultArray.class,"getRequests", "Parsing of REST response failed: "+e.getLocalizedMessage());
        }

      return requestsResultArray.getRequests();
    }
    
    public static ConcProgramParamsBO[] getConcProgramParams(String jsonArrayAsString){
        
        //Java object that represents the Java structure for the JSON notation structure. Its simple in 
        //this sample as the array only contains an array of departments with no additional child objects 
        //or primitive values
        ConcProgramParamsResultArray concProgramParamsResultArray = null;
        
        //object that serializes the JSON payload into the Java object
        JSONBeanSerializationHelper jbsh = new JSONBeanSerializationHelper();


        try {
            concProgramParamsResultArray = (ConcProgramParamsResultArray) jbsh.fromJSON(ConcProgramParamsResultArray.class, jsonArrayAsString);    


        } catch (Exception e) {
            Trace.log("JSONArray_to_JavaArray",Level.SEVERE, ConcProgramParamsResultArray.class,"getConcProgramParams", "Parsing of REST response failed: "+e.getLocalizedMessage());
        }

      return concProgramParamsResultArray.getConcProgramParams();
    }
    
    public static ConcProgramParamLovBO[] getConcProgramParamLov(String jsonArrayAsString){
        
        //Java object that represents the Java structure for the JSON notation structure. Its simple in 
        //this sample as the array only contains an array of departments with no additional child objects 
        //or primitive values
        ConcProgramParamLovResultArray concProgramParamLovResultArray = null;
        
        //object that serializes the JSON payload into the Java object
        JSONBeanSerializationHelper jbsh = new JSONBeanSerializationHelper();


        try {
            concProgramParamLovResultArray = (ConcProgramParamLovResultArray) jbsh.fromJSON(ConcProgramParamLovResultArray.class, jsonArrayAsString);    


        } catch (Exception e) {
            Trace.log("JSONArray_to_JavaArray",Level.SEVERE, ConcProgramParamLovResultArray.class,"getConcProgramParams", "Parsing of REST response failed: "+e.getLocalizedMessage());
        }

      return concProgramParamLovResultArray.getConcProgramParamLov();
    }
}
