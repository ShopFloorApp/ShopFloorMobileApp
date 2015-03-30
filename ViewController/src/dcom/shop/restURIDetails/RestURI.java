package dcom.shop.restURIDetails;


public class RestURI {
    public RestURI() {
        super();
    }
    private static final String ITEM_INQUIRY_URI = "/webservices/rest/DCOMInquiry/getitems/";

    public static String PostItemInquiryURI() {
        return ITEM_INQUIRY_URI;
    }
    private static final String PROCESS_INV_TRXN_URI = "/webservices/rest/DcomTrns/processinvtxn/";

    public static String PostInvTrxnURI() {
        return PROCESS_INV_TRXN_URI;
    }
    
    private static final String ONHAND_INQUIRY_URI = "/webservices/rest/DCOMInquiry/getonhand/";

    public static String PostOnHandInquiryURI() {
        return ONHAND_INQUIRY_URI;
    }

    private static final String DELIVERY_INQUIRY_URI = "/webservices/rest/DCOMShip/getdelivery/";

    public static String PostDeliveryInquiryURI() {
        return DELIVERY_INQUIRY_URI;
    }
    
    private static final String QUICK_SHIP_URI = "/webservices/rest/DCOMShip/quickship/";

    public static String PostQuickShipURI() {
        return QUICK_SHIP_URI;
    }
    
    private static final String MTLTXN_INQUIRY_URI = "/webservices/rest/DCOMInquiry/getmtltxn/";

    public static String PostMtlTxnInquiryURI() {
        return MTLTXN_INQUIRY_URI;
    }
    private static final String ITEM_CATEG_INQUIRY_URI = "/webservices/rest/DCOMInquiry/getitemcateg/";

    public static String PostItemCategInquiryURI() {
        return ITEM_CATEG_INQUIRY_URI;
    }
    
    private static final String ITEM_UDA_INQUIRY_URI = "/webservices/rest/DCOMInquiry/getitemuda/";

    public static String PostItemUdaInquiryURI() {
        return ITEM_UDA_INQUIRY_URI;
    }

    private static final String ProgramListURI = "/webservices/rest/DCOMSRS/getproglist/";
    private static final String ProgramParamsURI = "/webservices/rest/DCOMSRS/getprogparam/";
    private static final String ProgramParamLovURI = "/webservices/rest/DCOMSRS/getlov/";
    private static final String SubmitProgramURI = "/webservices/rest/DCOMSRS/submitprog/";
    private static final String RequestsURI = "/webservices/rest/DCOMSRS/getrequest/";

    public static String PostProgramListURI() {
        return ProgramListURI;
    }

    public static String PostProgramParamsURI() {
        return ProgramParamsURI;
    }

    public static String PostProgramParamLovURI() {
        return ProgramParamLovURI;
    }

    public static String PostSubmitProgramURI() {
        return SubmitProgramURI;
    }

    public static String PostRequestsURI() {
        return RequestsURI;
    }
    private static final String LPN_DETAILS_URI = "/webservices/rest/DCOMInquiry/getlpn/";

    public static String PostLpnDetailsURI() {
        return LPN_DETAILS_URI;
    }

    private static final String DOCKDOOR_DETAILS_URI = "/webservices/rest/DCOMInquiry/getdockdoor/";

    public static String PostDockDoorDetailsURI() {
        return DOCKDOOR_DETAILS_URI;
    }
    private static final String LPN_INQUIRY_URI = "/webservices/rest/DCOMShip/getlpn/";

    public static String PostLpnInquiryURI() {
        return LPN_INQUIRY_URI;

    }
    private static final String ITEM_DETAILS_URI = "/webservices/rest/DCOMInquiry/getitem/";

    public static String PostItemDetailsURI() {
        return ITEM_DETAILS_URI;
    }
    /*
     * Dispatch Module
     */
    private static final String GET_DEPT = "/webservices/rest/DCOMDispatchTxn/getdept/";

    public static String PostGetDeptURI() {
        return GET_DEPT;
    }

    private static final String GET_JOBOPS = "/webservices/rest/DCOMDispatchTxn/getjobops/";

    public static String PostGetJobOps() {
        return GET_JOBOPS;
    }

    private static final String GET_JOBOP = "/webservices/rest/DCOMDispatchTxn/getjobop/";

    public static String PostGetJobOp() {
        return GET_JOBOP;
    }
    private static final String GET_JOBPROP = "/webservices/rest/DCOMDispatchTxn/getjobdetails/";

    public static String PostGetJobProp() {
        return GET_JOBPROP;
    }

    private static final String GET_RESOURCES = "/webservices/rest/DCOMDispatchTxn/getjobresources/";

    public static String PostGetResources() {
        return GET_RESOURCES;
    }

    private static final String GET_COMPONENTS = "/webservices/rest/DCOMDispatchTxn/getjobcomponents/";

    public static String PostGetComponents() {
        return GET_COMPONENTS;
    }
    private static final String GET_OPSEQ = "/webservices/rest/DCOMDispatchTxn/getopseq/";

    public static String PostGetOpSeq() {
        return GET_OPSEQ;
    }
    /*
     * Dispatch Lists end
     */
    
    private static final String GET_SALES_ORDER = "/webservices/rest/DCOMLOV/getsalesorder/";

    public static String PostGetSalesOrder() {
        return GET_SALES_ORDER;
    }

    private static final String GET_REQUISITION = "/webservices/rest/DCOMLOV/getreq/";

    public static String PostGetRequisition() {
        return GET_REQUISITION;
    }

    private static final String GET_PURCHASE_ORDER = "/webservices/rest/DCOMLOV/getpo/";

    public static String PostGetPurchaseOrder() {
        return GET_PURCHASE_ORDER;
    }
	private static final String GET_SHIPMENT = "/webservices/rest/DCOMRCV/getshipment/";

    public static String PostGetShipment() {
        return GET_SHIPMENT;
    }
    
    private static final String POST_RECEIVE = "/webservices/rest/DCOMRCV/receive/";

    public static String PostReceiveTxn() {
    return POST_RECEIVE;
    }
}
