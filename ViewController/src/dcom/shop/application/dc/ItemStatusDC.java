package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.ItemStatusBO;

import java.util.ArrayList;
import java.util.List;

public class ItemStatusDC extends SyncUtils{
    public ItemStatusDC() {
        super();
    }
    protected static List s_itemStatus = new ArrayList();

    public ItemStatusBO[] getItemType() {
        s_itemStatus.clear();
        s_itemStatus = super.getCollectionFromDB(ItemStatusBO.class);
        ItemStatusBO[] itemType = (ItemStatusBO[]) s_itemStatus.toArray(new ItemStatusBO[s_itemStatus.size()]);
        return itemType;
    }
}
