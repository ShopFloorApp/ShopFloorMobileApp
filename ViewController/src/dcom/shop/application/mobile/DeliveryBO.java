package dcom.shop.application.mobile;

public class DeliveryBO {
    public DeliveryBO() {
        super();
    }
    
    String DELIVERYID;
    String SHIPMETHOD;
    String NETWT;
    String GROSSWT;
    String WAYBILL;


    public void setDELIVERYID(String DELIVERYID) {
        this.DELIVERYID = DELIVERYID;
    }

    public String getDELIVERYID() {
        return DELIVERYID;
    }

    public void setSHIPMETHOD(String SHIPMETHOD) {
        this.SHIPMETHOD = SHIPMETHOD;
    }

    public String getSHIPMETHOD() {
        return SHIPMETHOD;
    }

    public void setNETWT(String NETWT) {
        this.NETWT = NETWT;
    }

    public String getNETWT() {
        return NETWT;
    }

    public void setGROSSWT(String GROSSWT) {
        this.GROSSWT = GROSSWT;
    }

    public String getGROSSWT() {
        return GROSSWT;
    }

    public void setWAYBILL(String WAYBILL) {
        this.WAYBILL = WAYBILL;
    }

    public String getWAYBILL() {
        return WAYBILL;
    }

}
