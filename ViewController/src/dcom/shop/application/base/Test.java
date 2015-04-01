package dcom.shop.application.base;

import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;

public class Test {
    public Test() {
        super();
    }

    public static void main(String[] args) {
        Test test = new Test();
        String keyword = "%";
        String type="DIRECT_SHIP";
        String payload =
            "{\"x\": {\"RESTHeader\": {\"@xmlns\": \"http://xmlns.oracle.com/apps/inv/rest/DCOMShip/header\",\"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\"RespApplication\": \"ONT\",\"SecurityGroup\": \"STANDARD\",\n" +
            "\"NLSLanguage\": \"AMERICAN\",\"Org_Id\": \"82\"},\"InputParameters\": {\"PDOCKTYPE\": \""+type+"\",\"PORGCODE\": \"100\",\"PDOCKNAME\": \""+keyword+"\"}\n" + "}\n" + "}";
        
        payload =
                    "{\"x\": {\"RESTHeader\": {\"@xmlns\": \"http://xmlns.oracle.com/apps/inv/rest/DCOMInquiry/header\",\"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\"RespApplication\": \"ONT\",\"SecurityGroup\": \"STANDARD\",\n" +
                    "\"NLSLanguage\": \"AMERICAN\",\"Org_Id\": \"82\"},\"InputParameters\": {\"PLPNREQ\": {\"ORGCODE\": \"100\",\"LPN\": \"%" +
                    keyword + "%\"}}\n" + "}\n" + "}";
        /*    
        payload =
                    "{\"x\": {\"RESTHeader\": {\"@xmlns\": \"http://xmlns.oracle.com/apps/inv/rest/DCOMLOV/header\",\"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\"RespApplication\": \"ONT\",\"SecurityGroup\": \"STANDARD\",\n" +
                    "\"NLSLanguage\": \"AMERICAN\",\"Org_Id\": \"82\"},\"InputParameters\": {\"PWAREHOUSE\": \""+type+"\",\"PLOTTYPE\": \"%\",\"PITEM\": \"%\",\"PLOT\": \"%"+keyword+"%\"}\n" + "}\n" + "}";
        
        payload =
                    "{\"x\": {\"RESTHeader\": {\"@xmlns\": \"http://xmlns.oracle.com/apps/inv/rest/DCOMLOV/header\",\"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\"RespApplication\": \"ONT\",\"SecurityGroup\": \"STANDARD\",\n" +
                    "\"NLSLanguage\": \"AMERICAN\",\"Org_Id\": \"82\"},\"InputParameters\": {\"PSERIALTYPE\": \""+type+"\",\"PWAREHOUSE\": \"%\",\"PITEM\": \"%\",\"PSERIAL\": \"%"+keyword+"%\"}\n" + "}\n" + "}";
        */
        
        payload =
                    "{\n" + "\"GET_SO_PER_ORG_Input\":\n" + "{\n" +
                    "\"@xmlns\": \"http://xmlns.oracle.com/apps/fnd/rest/GetSoPerOrgSvc/get_so_per_org/\",\n" +
                    "   \"RESTHeader\": {\"@xmlns\": \"http://xmlns.oracle.com/apps/fnd/rest/GetSoPerOrgSvc/header\",\n" +
                    "                  \"Responsibility\": \"DCOM_MOBILE_USER\",\n" +
                    "                  \"RespApplication\": \"INV\",\n" +
                    "                  \"SecurityGroup\": \"STANDARD\",\n" +
                    "                  \"NLSLanguage\": \"AMERICAN\",\n" + "                  \"Org_Id\": \"82\"\n" +
                    "                 },\n" + "   \"InputParameters\": \n" + 
                    "                   {\"POU\": \"\",\n" +
                    "                   \"PTYPE\": \""+type+"\",\n" +
                       "                   \"PORDER\": \"\",\n" +
                    "                    \"PWAREHOUSE\": \"\"\n }\n" + "}\n" + "}\n";
        System.out.println("payload: "+payload);
    }
}
