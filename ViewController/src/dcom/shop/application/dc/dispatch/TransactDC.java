package dcom.shop.application.dc.dispatch;

import dcom.shop.application.mobile.dispatch.TransactBO;

public class TransactDC {
    public TransactDC() {
        super();
    }
    
    public TransactBO[] getTransact(){
        TransactBO transactBO = new TransactBO();
        TransactBO[] transactArray = new TransactBO[1];
        transactArray[0]= transactBO;
        return transactArray;
    }
    
    public String saveTransaction(TransactBO transactBo){
        StringBuffer strPayload = new StringBuffer();
        /*
        strPayload.append("{\n" + "  \"x\": {\n" + "    \"RESTHeader\": {\n" +
                          "      \"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" +
                          "      \"RespApplication\": \"ONT\",\n" + "      \"SecurityGroup\": \"STANDARD\",\n" +
                          "      \"NLSLanguage\": \"AMERICAN\",\n" + "      \"Org_Id\": \"82\"\n" + "    },\n" +
                          "    \"InputParameters\": {\n" + "      \"PORGCODE\": \"" + orgCode + "\",\n" +
                          "      \"PDEPTCODE\": \"" + deptCode + "\",\n" + "      \"PJOB\": \"" + jobNumber + "\"\n" +
                          "    }\n" + "  }\n" + "}");


        String jsonArrayAsString = restCallerUtil.invokeUPDATE(RestURI.PostGetJobProp(), strPayload.toString());
        */
        return null;
    }
}
