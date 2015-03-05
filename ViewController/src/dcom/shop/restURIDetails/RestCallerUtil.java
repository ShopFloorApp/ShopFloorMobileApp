package dcom.shop.restURIDetails;


import java.util.logging.Level;

import oracle.adfmf.dc.ws.rest.RestServiceAdapter;
import oracle.adfmf.framework.api.Model;
import oracle.adfmf.util.logging.Trace;


public class RestCallerUtil {
    
    public RestCallerUtil() {
        super();
    }
    
    //GET
    public String invokeREAD(String requestURI){
        return this.invokeRestRequest(RestServiceAdapter.REQUEST_TYPE_GET, requestURI, "");
    }
    
    //POST
    public String invokeUPDATE(String requestURI, String payload){
        
        return this.invokeRestRequest(RestServiceAdapter.REQUEST_TYPE_POST, requestURI, payload);
    }
    
    //PUT
    public String invokeCREATE(String requestURI, String payload){
        System.out.println("Inside invokeCreate");
        return this.invokeRestRequest(RestServiceAdapter.REQUEST_TYPE_PUT, requestURI, payload);
    }
    
    
    //DELETE
    public String invokeDELETE(String requestURI){
        return this.invokeRestRequest(RestServiceAdapter.REQUEST_TYPE_POST, requestURI, "");
    }
    
    /**
     * Method that handles the boilerplate code for obtaining and configuring a service 
     * adapter instance. 
     * 
     * @param httpMethod GET, POST, PUT, DELETE
     * @param requestURI the URI to appends to the base REST URL. URIs are expected to start with "/"
     * @return
     */
    private String invokeRestRequest(String httpMethod, String requestURI, String payload){
    System.out.println("Inside rest");
        String restPayload = "";
        
        RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
        restServiceAdapter.clearRequestProperties();
        //set URL connection defined for this sample. In this sample, the "hrresrconn" connection resolves 
        //to http://127.0.0.1:7101/hrrest/resources/hrappsrvc . The connection has been created for this 
        //sample choosing File | New | From Gallery | General | Connections | URL connection from the JDeveloper menu
        restServiceAdapter.setConnectionName("REST");
        
        //set GET, POST, DELETE, PUT
        restServiceAdapter.setRequestType(httpMethod);
        
        // Set credentials needed to access the REST server 
        String theUsername = "SYSADMIN";
        String thePassword = "tstadm1n"; 
        String userPassword = theUsername + ":" + thePassword;
        String encoding = new sun.misc.BASE64Encoder().encode(userPassword.getBytes());

        restServiceAdapter.addRequestProperty("Authorization", "Basic " + encoding);
        
        //this sample uses JSON only. Thus the media type can be hard-coded in this class
        //the content-type tells the server what format the incoming payload has
       // restServiceAdapter.addRequestProperty("Content-Type", "application/json");
        //the accept header indicates the expected payload fromat to the server
        //restServiceAdapter.addRequestProperty("Accept", "application/json");
        restServiceAdapter.addRequestProperty("Content-Language", "en-US");
        restServiceAdapter.addRequestProperty("Content-Type", "application/json");
        restServiceAdapter.setRequestURI(requestURI);        
        restServiceAdapter.setRetryLimit(0);    
        
        //variable holding the response
        String response = "";
        
        //set payload if there is payload passed with the request
        if(payload != null){   
             //send with empty payload
             restPayload  = payload;
        }

        try {
            response = (restServiceAdapter.send(restPayload)).toString();
        } catch (Exception e) {
            //log error
            Trace.log("REST_JSON",Level.SEVERE, this.getClass(),"invokeRestRequest", "Invoke of REST Resource failed for "+httpMethod+" to "+requestURI);
            Trace.log("REST_JSON",Level.SEVERE, this.getClass(),"invokeRestRequest", e.getLocalizedMessage());
        }
        return response;
    };
}

