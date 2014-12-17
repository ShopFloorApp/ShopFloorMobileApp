package dcom.shop.application.base;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.GenericTypeBeanSerializationHelper;
import oracle.adfmf.util.GenericType;
import oracle.adfmf.util.GenericVirtualType;

public class TransactionUtils {
    public TransactionUtils() {
        super();
    }

    public static void main(String[] args) {
        TransactionUtils transactionUtils = new TransactionUtils();
        transactionUtils.initiateWebSerivce();
    }

    public void initiateWebSerivce() {
        GenericVirtualType payload = new GenericVirtualType(null, "payload");
        payload.defineAttribute(null, "TransactionHeader", GenericType.class, SyncUtils.getTransactionHeader());
        payload.defineAttribute(null, "Item", String.class, "1");
        payload.defineAttribute(null, "Quantity", Integer.class, new Integer(1));
        payload.defineAttribute(null, "Uom", String.class, "1");
        payload.defineAttribute(null, "SourceOrgCode", String.class, "1");
        payload.defineAttribute(null, "SourceSubInventory", String.class, "1");
        payload.defineAttribute(null, "Locator", GenericType.class, getLocator());
        payload.defineAttribute(null, "SerialNumber", GenericType.class, getSerialNumber());
        payload.defineAttribute(null, "DestOrgCode", String.class, "1");
        payload.defineAttribute(null, "DestSubInventory  ", String.class, "1");
        payload.defineAttribute(null, "Attributes  ", Object.class, null);

        List pnames = new ArrayList();
        List pvals = new ArrayList();
        List ptypes = new ArrayList();
        List collection = new ArrayList();

        pnames.add("payload");
        ptypes.add(GenericType.class);
        pvals.add(payload);
        System.out.println("Before Webservice call");

        try {
            //            Object obj = collectionClass.newInstance();
            GenericType genericReturnValue =
                (GenericType) AdfmfJavaUtilities.invokeDataControlMethod("SubInventoryTrf_WS", null, "process", pnames,
                                                                         pvals, ptypes);
            GenericType respListType = (GenericType) genericReturnValue.getAttribute(0);


        } catch (Exception aie) {
            throw new RuntimeException(aie);
        }
    }

    private static GenericVirtualType getLocator() {
        GenericVirtualType locator = new GenericVirtualType(null, "Locator");
        locator.defineAttribute(null, "FromLocator", String.class, "1");
        locator.defineAttribute(null, "ToLocator", String.class, "1");
        locator.defineAttribute(null, "Quantity", Integer.class, new Integer(1));
        return locator;
    }

    private GenericVirtualType getSerialNumber() {
        GenericVirtualType serialNumber = new GenericVirtualType(null, "SerialNumber");
        serialNumber.defineAttribute(null, "FromSerial", String.class, "1");
        serialNumber.defineAttribute(null, "ToSerial", String.class, "1");
        serialNumber.defineAttribute(null, "Quantity", Integer.class, new Integer(1));
        return serialNumber;
    }
}
