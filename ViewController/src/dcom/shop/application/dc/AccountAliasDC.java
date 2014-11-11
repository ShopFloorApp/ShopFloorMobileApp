package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.AccountAliasBO;
import dcom.shop.application.mobile.CarrierBO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.adfmf.util.GenericVirtualType;

public class AccountAliasDC extends SyncUtils {
    public AccountAliasDC() {
        super();
    }
    public AccountAliasBO[] getAccountAlias() {

        try {
            AccountAliasBO[] accountAlias = null;
            List returnValue = new ArrayList();
            GenericVirtualType payload = new GenericVirtualType(null, "payload");
            HashMap paramsMap=new HashMap();
            paramsMap.put("resAttriName","AccountAliasDetails");
            paramsMap.put("lovDCName", "AccountAliasLOV_WS");
            paramsMap.put("opeartionName", "process");
            paramsMap.put("payload",payload);
            returnValue = super.getCollection(AccountAliasBO.class, paramsMap);
            accountAlias = (AccountAliasBO[]) returnValue.toArray(new AccountAliasBO[returnValue.size()]);
            return accountAlias;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
