package dcom.shop.application.base;

import java.math.BigDecimal;

import oracle.adf.model.datacontrols.device.DeviceManagerFactory;

public abstract class AViewObject extends SyncUtils{
    public static final String NETWORK_NOT_REACHABLE = "NotReachable"; // Indicates no network connectivity.

    protected boolean isOffline() {
        String status = DeviceManagerFactory.getDeviceManager().getNetworkStatus();
        boolean offline = NETWORK_NOT_REACHABLE.equals(status) || "unknown".equals(status);
        return offline;
    }

    protected BigDecimal getAttributeValue(Object obj) {
        try {
            //Test
            BigDecimal value = new BigDecimal(obj.toString());
            return value;
        } catch (NumberFormatException nex) {
            return null;
        }
    }
}
