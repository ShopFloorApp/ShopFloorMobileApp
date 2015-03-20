package dcom.shop.Transaction.page.bean;

import dcom.shop.application.dc.transaction.receiving.ReceivingTxnDC;

import dcom.shop.application.mobile.transaction.receiving.LinesBO;

import java.util.ArrayList;

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
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.lineAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.subInvAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.locatorAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.quantityAdd}", null);
    }
    public void updateRecord(ActionEvent ae){
        String line = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.lineAdd}");
        String subInv = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.subInvAdd}");
        String locator = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.locatorAdd}");
        String quantity = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.quantityAdd}");
        ReceivingTxnDC.s_lines.add(new LinesBO(line,subInv,locator,quantity,"Y"));
    }
    public void removeRecords(ActionEvent ae){
//        ArrayList coll=new ArrayList();
//        for(int i=0;i<receiveDc.s_lines.size();i++){
//            LinesBO lines = (LinesBO) receiveDc.s_lines.get(i);
//            if(!lines.getIsNewEntity().equalsIgnoreCase("Y")){
//                coll.add(lines);
//            }
//        }
//        receiveDc.s_lines=coll;
        receiveDc.s_lines.clear();
    }
}
