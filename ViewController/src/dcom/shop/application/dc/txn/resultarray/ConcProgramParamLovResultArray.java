package dcom.shop.application.dc.txn.resultarray;

import dcom.shop.application.mobile.txn.ConcProgramParamLovBO;

public class ConcProgramParamLovResultArray {
    private ConcProgramParamLovBO[] concProgramParamLov=null;
    public ConcProgramParamLovResultArray() {
        super();
    }

    public void setConcProgramParamLov(ConcProgramParamLovBO[] concProgramParamLov) {
        this.concProgramParamLov = concProgramParamLov;
    }

    public ConcProgramParamLovBO[] getConcProgramParamLov() {
        return concProgramParamLov;
    }
}
