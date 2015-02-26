package dcom.shop.application.base;

import dcom.shop.application.mobile.WarehouseBO;

import java.util.HashMap;

public abstract class CustomPojo implements Comparable {
    public CustomPojo() {
        super();
    }

    @Override
    public int compareTo(Object o) {
        // TODO Implement this method
        return 0;
    }

    public abstract void setBOClassRow(HashMap hashMap);

    //    public abstract HashMap getBOClassRow(CustomPojo childPojo);
}

