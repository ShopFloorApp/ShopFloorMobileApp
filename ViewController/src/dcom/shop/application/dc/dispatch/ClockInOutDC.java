package dcom.shop.application.dc.dispatch;

import dcom.shop.application.mobile.dispatch.ClockInOutBO;

public class ClockInOutDC {
    ClockInOutBO clockInOutBO;

    public ClockInOutDC() {
        super();
    }

    public ClockInOutBO[] getClockInOutBO() {
        this.clockInOutBO = new ClockInOutBO();
        ClockInOutBO[] objArray = new ClockInOutBO[1];
        objArray[0] = this.clockInOutBO;
        return objArray;
    }

    public void rollback() {
        if (clockInOutBO != null) {
            clockInOutBO = null;
        }
    }

    public String saveTransaction(ClockInOutBO newObj) {
        return null;
    }
}
