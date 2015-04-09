package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.ItemBO;

import dcom.shop.application.mobile.ItemOnHandBO;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.exception.AdfInvocationException;
import oracle.adfmf.util.GenericVirtualType;

public class ItemOnHandDC extends SyncUtils {
    public ItemOnHandDC() {
        super();
    }
    List s_itemOnHandList = new ArrayList();

    public void ProcessWS(String itemNum, String subInv, String locator, String CG) throws AdfInvocationException {
        GenericVirtualType payload = new GenericVirtualType(null, "payload");
        payload.defineAttribute(null, "ItemNumber", String.class, itemNum);
        String orgCode =
            (String) AdfmfJavaUtilities.evaluateELExpression("#{preferenceScope.feature.dcom.shop.MyWarehouse.OrgCodePG.OrgCode}");

        payload.defineAttribute(null, "OrgCode", String.class, orgCode);
        payload.defineAttribute(null, "SubInv", String.class, subInv);
        payload.defineAttribute(null, "Locator", String.class, locator);
        payload.defineAttribute(null, "CG", String.class, CG);

        HashMap paramsMap = new HashMap();
        paramsMap.put("resAttriName", "ItemOnHandQty_Response");
        paramsMap.put("lovDCName", "ItemOnHand_WS");
        paramsMap.put("opeartionName", "execute");
        paramsMap.put("GetItemOnHandQtyInputMessage", payload);
        s_itemOnHandList = super.getCollectionFromWS(ItemOnHandBO.class, paramsMap);

    }
}
