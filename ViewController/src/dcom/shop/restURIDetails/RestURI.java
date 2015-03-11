package dcom.shop.restURIDetails;

public class RestURI {
    public RestURI() {
        super();
    }
    private static final String ITEM_INQUIRY_URI = "/webservices/rest/DCOMInquiry/getitems/";

    public static String PostItemInquiryURI() {
        return ITEM_INQUIRY_URI;
    }
    
    private static final String ITEM_DETAILS_URI = "/webservices/rest/DCOMInquiry/getitem/";

    public static String PostItemDetailsURI() {
        return ITEM_DETAILS_URI;
    }
    
    private static final String LPN_DETAILS_URI = "/webservices/rest/DCOMInquiry/getlpn/";

    public static String PostLpnDetailsURI() {
        return LPN_DETAILS_URI;
    }
    
    
    
    private static final String PROCESS_INV_TRXN_URI = "/webservices/rest/DcomTrns/processinvtxn/";

    public static String PostInvTrxnURI() {
        return PROCESS_INV_TRXN_URI;
    }
	
		
		
		    private static final String ProgramListURI = "/webservices/rest/DCOMSRS/getproglist/";
        private static final String ProgramParamsURI = "/webservices/rest/DCOMSRS/getprogparam/";
        private static final String ProgramParamLovURI = "/webservices/rest/DCOMSRS/getlov/";
        private static final String SubmitProgramURI = "/webservices/rest/DCOMSRS/submitprog/";
        private static final String RequestsURI = "/webservices/rest/DCOMSRS/getrequest/";
		
		    public static String PostProgramListURI(){ return ProgramListURI;}
        public static String PostProgramParamsURI(){ return ProgramParamsURI;}
        public static String PostProgramParamLovURI(){ return ProgramParamLovURI;}
        public static String PostSubmitProgramURI(){ return SubmitProgramURI;}
        public static String PostRequestsURI(){ return RequestsURI;}
}
