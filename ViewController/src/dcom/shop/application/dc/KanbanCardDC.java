package dcom.shop.application.dc;

import dcom.shop.application.base.SyncUtils;
import dcom.shop.application.mobile.CarrierBO;

import dcom.shop.application.mobile.KanbanCardBO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.adfmf.util.GenericVirtualType;

public class KanbanCardDC extends SyncUtils {
    public KanbanCardDC() {
        super();
    }
    public KanbanCardBO[] getKanbanCards() {

        try {
            KanbanCardBO[] kanbanCards = null;
            List returnValue = new ArrayList();
            GenericVirtualType payload = new GenericVirtualType(null, "payload");
            payload.defineAttribute(null, "Whse", String.class, "");
            payload.defineAttribute(null, "ItemNum", String.class, "");
            HashMap paramsMap=new HashMap();
            paramsMap.put("resAttriName","KanbanCardDetails");
            paramsMap.put("lovDCName", "KanbanCardLOV_WS");
            paramsMap.put("opeartionName", "process");
            paramsMap.put("payload",payload);
            returnValue = super.getCollection(KanbanCardBO.class, paramsMap);
            kanbanCards = (KanbanCardBO[]) returnValue.toArray(new KanbanCardBO[returnValue.size()]);
            return kanbanCards;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
