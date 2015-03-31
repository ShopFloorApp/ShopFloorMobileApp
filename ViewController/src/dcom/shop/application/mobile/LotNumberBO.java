package dcom.shop.application.mobile;

public class LotNumberBO {
    public LotNumberBO() {
        super();
    }
    
    String WHSE;
    String ITEM;
    String LOTNUM;
    String LOTSTATUS;
    String SUBINV;
    String LOCATOR;
    String ATTRIBUTES;


    public void setWHSE(String WHSE) {
        this.WHSE = WHSE;
    }

    public String getWHSE() {
        return WHSE;
    }

    public void setITEM(String ITEM) {
        this.ITEM = ITEM;
    }

    public String getITEM() {
        return ITEM;
    }

    public void setLOTNUM(String LOTNUM) {
        this.LOTNUM = LOTNUM;
    }

    public String getLOTNUM() {
        return LOTNUM;
    }

    public void setLOTSTATUS(String LOTSTATUS) {
        this.LOTSTATUS = LOTSTATUS;
    }

    public String getLOTSTATUS() {
        return LOTSTATUS;
    }

    public void setSUBINV(String SUBINV) {
        this.SUBINV = SUBINV;
    }

    public String getSUBINV() {
        return SUBINV;
    }

    public void setLOCATOR(String LOCATOR) {
        this.LOCATOR = LOCATOR;
    }

    public String getLOCATOR() {
        return LOCATOR;
    }

    public void setATTRIBUTES(String ATTRIBUTES) {
        this.ATTRIBUTES = ATTRIBUTES;
    }

    public String getATTRIBUTES() {
        return ATTRIBUTES;
    }
}
