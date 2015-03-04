package dcom.shop.restURIDetails;

public class RestURI {
    public RestURI() {
        super();
    }
        private static final String ITEM_INQUIRY_URI = "/webservices/rest/DCOMInquiry/getitem/";
        public static String PostItemInquiryURI(){ return ITEM_INQUIRY_URI;}
}
