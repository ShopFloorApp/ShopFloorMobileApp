package dcom.shop.application.base;

import dcom.shop.application.dc.SyncPreferencesDC;

public class BackgroundProcess implements Runnable {
    public BackgroundProcess() {
        super();
    }

    @Override
    public void run() {
        // TODO Implement this method
        SyncPreferencesDC sync=new SyncPreferencesDC();
        sync.getSyncLovs();
        sync.syncAll(); 
    }
}
