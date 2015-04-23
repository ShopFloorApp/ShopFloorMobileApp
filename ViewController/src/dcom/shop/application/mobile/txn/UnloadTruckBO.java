package dcom.shop.application.mobile.txn;

public class UnloadTruckBO {
    public UnloadTruckBO() {
        super();
    }
    private String dockDoor;
    private String LPN;
    private String subInvLocator;

    public void setSubInvLocator(String subInvLocator) {
        this.subInvLocator = subInvLocator;
    }

    public String getSubInvLocator() {
        return subInvLocator;
    }

    public void setDockDoor(String dockDoor) {
        this.dockDoor = dockDoor;
    }

    public String getDockDoor() {
        return dockDoor;
    }

    public void setLPN(String LPN) {
        this.LPN = LPN;
    }

    public String getLPN() {
        return LPN;
    }
    
}
