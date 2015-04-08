package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.ItemTypeBO;

import java.util.ArrayList;
import java.util.List;

public class ItemTypeDC extends SyncUtils {
    public ItemTypeDC() {
        super();
    }
    protected static List s_itemTypes = new ArrayList();

    public ItemTypeBO[] getItemType() {
        s_itemTypes.clear();
        s_itemTypes = super.getCollectionFromDB(ItemTypeBO.class);
        ItemTypeBO[] itemType = (ItemTypeBO[]) s_itemTypes.toArray(new ItemTypeBO[s_itemTypes.size()]);
        return itemType;
    }
}
