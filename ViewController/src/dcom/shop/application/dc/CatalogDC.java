package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.CatalogBO;

import java.util.ArrayList;
import java.util.List;

public class CatalogDC extends SyncUtils {
    public CatalogDC() {
        super();
    }
    protected static List s_catalog = new ArrayList();

    public CatalogBO[] getCatalog() {
        s_catalog.clear();
        s_catalog = super.getCollectionFromDB(CatalogBO.class);
        CatalogBO[] itemType = (CatalogBO[]) s_catalog.toArray(new CatalogBO[s_catalog.size()]);
        return itemType;
    }
}
