package dcom.shop.Transaction.page.bean;

import dcom.shop.application.dc.transaction.receiving.ReceivingTxnDC;

import dcom.shop.application.mobile.transaction.receiving.LinesBO;

import javax.el.MethodExpression;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;

public class ReceivingTxnUtilBean {
    ReceivingTxnDC receiveDc=new ReceivingTxnDC();
    public ReceivingTxnUtilBean() {
        super();
    }
    public void getLinesFromDocumentNo(ActionEvent ae){
        receiveDc.executeShipmentLines();
    }
    
    public void subInvChnage(ValueChangeEvent valueChangeEvent) {
        MethodExpression me =
            AdfmfJavaUtilities.getMethodExpression("#{bindings.refreshLocators.execute}", Object.class, new Class[] {
                                                   });
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
    }
    public void addRecord(ActionEvent ae){
        ReceivingTxnDC.s_lines.add(new LinesBO("","","",""));
    }
}
