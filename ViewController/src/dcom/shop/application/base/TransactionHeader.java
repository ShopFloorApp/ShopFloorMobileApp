package dcom.shop.application.base;

import java.text.SimpleDateFormat;

import java.util.Date;

import oracle.adfmf.util.GenericType;
import oracle.adfmf.util.GenericVirtualType;

public class TransactionHeader {
    public TransactionHeader() {
        super();
    }

    protected static GenericVirtualType getTransactionHeader() {
        GenericVirtualType transactionHeader = new GenericVirtualType(null, "TransactionHeader");
        transactionHeader.defineAttribute(null, "CallingInterfaceName", String.class, "1");
        transactionHeader.defineAttribute(null, "CallingSystemName", String.class, "1");
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date lastSyncDate = new Date();
        String date = DATE_FORMAT.format(lastSyncDate);
        transactionHeader.defineAttribute(null, "LastSyncDateTime", Object.class,date);
        transactionHeader.defineAttribute(null, "RoleName", String.class, "1");
        transactionHeader.defineAttribute(null, "UserIdentifier", String.class, "1");

        //Fetching Status
        GenericVirtualType status = new GenericVirtualType(null, "Status");
        status.defineAttribute(null, "Code", String.class, "1");
        status.defineAttribute(null, "Msg", String.class, "1");

        transactionHeader.defineAttribute(null, "Status", GenericType.class, status);

        return transactionHeader;
    }
}
