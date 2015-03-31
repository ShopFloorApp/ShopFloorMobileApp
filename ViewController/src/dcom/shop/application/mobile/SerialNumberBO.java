package dcom.shop.application.mobile;

public class SerialNumberBO {
    public SerialNumberBO() {
        super();
    }
    String SERIALNUMBER;
    String SERIALSTATUS;


    public void setSERIALNUMBER(String SERIALNUMBER) {
        this.SERIALNUMBER = SERIALNUMBER;
    }

    public String getSERIALNUMBER() {
        return SERIALNUMBER;
    }

    public void setSERIALSTATUS(String SERIALSTATUS) {
        this.SERIALSTATUS = SERIALSTATUS;
    }

    public String getSERIALSTATUS() {
        return SERIALSTATUS;
    }

}
