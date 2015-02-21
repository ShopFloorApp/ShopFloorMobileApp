package dcom.shop.application.dc.txn;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.txn.ConcurrentProgramBO;
import dcom.shop.application.mobile.txn.RequestsBO;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RequestsDC extends SyncUtils {
    private List filtered_Requests=new ArrayList();
    public RequestsDC() {
        super();
    }
    
    public RequestsBO[] getRequests() {

        try {
            System.out.println("called collection warehouse");
            Date lastDate=new Date();
            SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currDate=newFormat.format(lastDate);
            filtered_Requests.clear();
            filtered_Requests.add(new RequestsBO("Diagonostics : OM Order information","abc","1","1,MTL,2344,0","Completed",currDate,currDate,
                                                 "Normal Completion","American English","Normal","KausPatel",currDate));
            filtered_Requests.add(new RequestsBO("Diagonostics : OM ","pqr","231","1,MTL,234,0","Warning",currDate,currDate,
                                                 "Warning","American English","Normal","KausPatel",currDate));
            filtered_Requests.add(new RequestsBO("OM Order information","xyz","8796","1,MTL,44,0","Error",currDate,currDate,
                                                 "Error in Completion","American English","Normal","KausPatel",currDate));
            RequestsBO[] requests = null;
            requests = (RequestsBO[]) filtered_Requests.toArray(new RequestsBO[filtered_Requests.size()]);
            return requests;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
