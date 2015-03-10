package dcom.shop.application.dc.txn.resultarray;

import dcom.shop.application.mobile.txn.RequestsBO;

public class RequestsResultArray {
    private RequestsBO[] requests=null;
    public RequestsResultArray() {
        super();
    }

    public void setRequests(RequestsBO[] requests) {
        this.requests = requests;
    }

    public RequestsBO[] getRequests() {
        return requests;
    }
}
