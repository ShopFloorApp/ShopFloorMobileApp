package dcom.shop.Transaction.page.bean;

import dcom.shop.application.dc.transaction.receiving.ReceivingTxnDC;

import dcom.shop.application.mobile.transaction.receiving.LinesBO;

import dcom.shop.application.mobile.transaction.receiving.PurchaseOrderBO;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.util.ArrayList;

import javax.el.MethodExpression;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.util.Utility;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReceivingTxnUtilBean {
    ReceivingTxnDC receiveDc=new ReceivingTxnDC();
    private static Integer idx=new Integer(0);
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
    
    public void lineItemChange(ValueChangeEvent valueChangeEvent) {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.subInvAdd}", AdfmfJavaUtilities.evaluateELExpression("#{bindings.subInv1.inputValue}"));
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.locatorAdd}", AdfmfJavaUtilities.evaluateELExpression("#{bindings.locator2.inputValue}"));
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.quantityAdd}", AdfmfJavaUtilities.evaluateELExpression("#{bindings.qty.inputValue}"));
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.uomAdd}", AdfmfJavaUtilities.evaluateELExpression("#{bindings.uom.inputValue}"));
    }
    
    public void addRecord(ActionEvent ae){       
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.rowIdxAdd}", idx+1);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.lineAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.subInvAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.locatorAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.quantityAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.uomAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.lpnAdd}", null);
    }
    public void updateRecord(ActionEvent ae){
        Integer currentItem = (Integer) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.rowIdxAdd}");
        ArrayList coll = (ArrayList) receiveDc.s_lines;
                for(int i=0;i<receiveDc.s_lines.size();i++){
                    LinesBO lines = (LinesBO) receiveDc.s_lines.get(i);
                    if(lines.getRowLineIdx()==currentItem){
                        coll.remove(i);
                    }
                }
        receiveDc.s_lines=coll;
        
        String line = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.lineAdd}");
        String subInv = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.subInvAdd}");
        String locator = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.locatorAdd}");
        String quantity = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.quantityAdd}");
        String uom = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.uomAdd}");
        String lpn = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.lpnAdd}");
        ReceivingTxnDC.s_lines.add(new LinesBO(currentItem,line,subInv,locator,quantity,uom,lpn,"Y"));
    }
    public void removeRecords(ActionEvent ae){
        receiveDc.s_lines.clear();
    }
    public void deleteCurrectRecord(ActionEvent ae){
        Integer currentItem = (Integer) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.currentItem}");
        ArrayList coll=(ArrayList) receiveDc.s_lines;
                for(int i=0;i<receiveDc.s_lines.size();i++){
                    LinesBO lines = (LinesBO) receiveDc.s_lines.get(i);
                    if(lines.getRowLineIdx()==currentItem){
                        coll.remove(i);
                    }
                }
        MethodExpression me =
            AdfmfJavaUtilities.getMethodExpression("#{bindings.refreshLines.execute}", Object.class, new Class[] {
                                                   });
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { }); 
    }
    
    public void addMore(ActionEvent ae){
        updateRecord(ae);
        addRecord(ae);
    }
    
    public void clearReceiveTypePage(ActionEvent ae){
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.rowIdxAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.lineAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.subInvAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.locatorAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.quantityAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.uomAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.lpnAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.documnetNumber}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.receivingType}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.carrier}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.packSlip}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.bol}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.wayAirBill}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.shipment}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.shippedDate}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.comments}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.suppCustName}", null);
        receiveDc.s_lines.clear();
        receiveDc.s_shipmentLines.clear();
        MethodExpression me =
            AdfmfJavaUtilities.getMethodExpression("#{bindings.refreshLines.execute}", Object.class, new Class[] {
                                                   });
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
    }
}
