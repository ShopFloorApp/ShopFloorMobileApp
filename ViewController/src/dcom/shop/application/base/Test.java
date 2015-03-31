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
        String type="SHIP_DOCK";
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
        System.out.println("payload: "+payload);
    }
}
