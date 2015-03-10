package dcom.shop.application.dc.txn.resultarray;

import dcom.shop.application.mobile.txn.ConcProgramParamsBO;

public class ConcProgramParamsResultArray {
    private ConcProgramParamsBO[] concProgramParams=null;
    public ConcProgramParamsResultArray() {
        super();
    }

    public void setConcProgramParams(ConcProgramParamsBO[] concProgramParams) {
        this.concProgramParams = concProgramParams;
    }

    public ConcProgramParamsBO[] getConcProgramParams() {
        return concProgramParams;
    }
}
