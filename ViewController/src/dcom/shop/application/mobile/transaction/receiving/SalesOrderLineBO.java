package dcom.shop.application.mobile.transaction.receiving;

public class SalesOrderLineBO {
    public SalesOrderLineBO() {
        super();
    }
    
    String LINENUM;
    String ITEM;
    String LOTCONTROL;
    String SERIALCONTROL;
    String LINEQTY;
    String UOM;


    public void setLINENUM(String LINENUM) {
        this.LINENUM = LINENUM;
    }

    public String getLINENUM() {
        return LINENUM;
    }

    public void setITEM(String ITEM) {
        this.ITEM = ITEM;
    }

    public String getITEM() {
        return ITEM;
    }

    public void setLOTCONTROL(String LOTCONTROL) {
        this.LOTCONTROL = LOTCONTROL;
    }

    public String getLOTCONTROL() {
        return LOTCONTROL;
    }

    public void setSERIALCONTROL(String SERIALCONTROL) {
        this.SERIALCONTROL = SERIALCONTROL;
    }

    public String getSERIALCONTROL() {
        return SERIALCONTROL;
    }

    public void setLINEQTY(String LINEQTY) {
        this.LINEQTY = LINEQTY;
    }

    public String getLINEQTY() {
        return LINEQTY;
    }

    public void setUOM(String UOM) {
        this.UOM = UOM;
    }

    public String getUOM() {
        return UOM;
    }
}
