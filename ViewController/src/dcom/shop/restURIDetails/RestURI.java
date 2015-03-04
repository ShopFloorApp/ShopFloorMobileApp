package dcom.shop.restURIDetails;

public class RestURI {
    public RestURI() {
        super();
    }
    private static final String ITEM_INQUIRY_URI = "/webservices/rest/DCOMInquiry/getitem/";

    public static String PostItemInquiryURI() {
        return ITEM_INQUIRY_URI;
    }
    private static final String PROCESS_INV_TRXN_URI = "/webservices/rest/DcomTrns/processinvtxn/";

    public static String PostInvTrxnURI() {
        return PROCESS_INV_TRXN_URI;
    }
}
