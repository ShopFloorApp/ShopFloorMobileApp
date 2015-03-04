package dcom.shop.application.dc;

import dcom.shop.application.mobile.InvTrnBO;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

public class InvTrnDC extends RestCallerUtil{
    public InvTrnDC() {
        super();
    }
    public void ProcessWS(){
        String restURI = RestURI.PostInvTrxnURI();
         String payload = "{\n" + 
          "\"GET_SO_PER_ORG_Input\":\n" + 
          "{\n" + 
          "\"@xmlns\": \"http://xmlns.oracle.com/apps/fnd/rest/GetSoPerOrgSvc/get_so_per_org/\",\n" + 
          "   \"RESTHeader\": {\"@xmlns\": \"http://xmlns.oracle.com/apps/fnd/rest/GetSoPerOrgSvc/header\",\n" + 
          "                  \"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" + 
          "                  \"RespApplication\": \"ONT\",\n" + 
          "                  \"SecurityGroup\": \"STANDARD\",\n" + 
          "                  \"NLSLanguage\": \"AMERICAN\",\n" + 
          "                  \"Org_Id\": \"82\"\n" + 
          "                 },\n" + 
          "   \"InputParameters\": \n" + 
          "      {\"P_ORG_ID\": \"82\"\n" + 
          "       }\n" + 
          "}\n" + 
          "}\n";
          System.out.println("Calling create method");
          String jsonArrayAsString = super.invokeUPDATE(restURI,payload);
          System.out.println("Received response");
        
    }
}
