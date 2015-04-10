package dcom.shop.Transaction.page.bean;

import dcom.shop.application.dc.transaction.receiving.ReceivingTxnDC;

import dcom.shop.application.mobile.transaction.receiving.LinesBO;

import dcom.shop.application.mobile.transaction.receiving.PurchaseOrderBO;
import dcom.shop.application.mobile.transaction.receiving.SalesOrderBO;
import dcom.shop.application.mobile.transaction.receiving.ShipmentLinesBO;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.util.ArrayList;
import java.util.List;

import javax.el.MethodExpression;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.exception.AdfException;
import oracle.adfmf.javax.faces.model.SelectItem;
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
    List<SelectItem> orderLineItem;


    public void setOrderLineItem(List<SelectItem> orderLineItem) {
        this.orderLineItem = orderLineItem;
    }

    public List<SelectItem> getOrderLineItem() {
        return orderLineItem;
    }

    public void getSalesOrderLines(ActionEvent ae){
        SalesOrderBO[] orderArray=receiveDc.getSalesOrder();
        System.out.println("Order length: "+orderArray.length);
        
        List<SelectItem> temp = new ArrayList<SelectItem>();
        SelectItem si=new SelectItem();
        si.setValue("123");
        
        SelectItem s1=new SelectItem();
        s1.setValue("456");
        temp.add(si);
        temp.add(s1);
        setOrderLineItem(temp);
        
        for(int i=0;i<orderArray.length;i++){
            String order = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.documnetNumber}");
            System.out.println("Order: "+orderArray[i].getOrderNumber());
            System.out.println("Order lines: "+orderArray[i].getOrderLines());
            if(orderArray[i].getOrderNumber().equals(order)){
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.orderLines}", orderLineItem);   
                    return;
                }
            }
        
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
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.docRefLineAdd}", AdfmfJavaUtilities.evaluateELExpression("#{bindings.docRefLine.inputValue}"));
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.shipmentLineAdd}", AdfmfJavaUtilities.evaluateELExpression("#{bindings.shipmentLine.inputValue}"));
    }
    
    public void addRecord(ActionEvent ae){
        if(idx==0){
           idx = (Integer) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.nextLinesCount}");            
        }
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.rowIdxAdd}", idx+1);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.lineAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.subInvAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.locatorAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.quantityAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.uomAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.lpnAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.docRefLineAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.shipmentLineAdd}", null);
    }
    public void updateRecord(ActionEvent ae){
//        quantityValidation(ae);
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
        String docRefLine = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.docRefLineAdd}");
        String shipmentLine = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.shipmentLineAdd}");
        ReceivingTxnDC.s_lines.add(new LinesBO(currentItem,line,subInv,locator,quantity,uom,lpn,docRefLine,shipmentLine,"Y"));
    
//    ArrayList linColl = (ArrayList) receiveDc.s_shipmentLines;
//        for(int j=0;j<receiveDc.s_lines.size();j++){
//            ShipmentLinesBO lines = (ShipmentLinesBO) receiveDc.s_lines.get(j);
//            if(lines.getDocRefLine().equalsIgnoreCase(docRefLine) && lines.getShipmentLine().equalsIgnoreCase(shipmentLine)){
//                String lineQty=lines.getQty();
//                Integer availableQty=Integer.parseInt(lineQty)-Integer.parseInt(quantity);
//                if(availableQty==0){
//                    linColl.remove(j); 
//                }else{
//                    ((ShipmentLinesBO)linColl.remove(j)).setQty(availableQty.toString());
//                }
//            }
//        }
//        receiveDc.s_shipmentLines=linColl;
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
    
    public void quantityValidation(ActionEvent ae){
        String quantityEntered = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.quantityAdd}");
        String quantityReceivable = (String) AdfmfJavaUtilities.evaluateELExpression("#{bindings.qty.inputValue}");
        if (quantityEntered==null){
            quantityEntered="0";
        }
        if (quantityReceivable==null){
            quantityReceivable="0";
        }
        if(Integer.parseInt(quantityEntered)>Integer.parseInt(quantityReceivable)){
            throw new AdfException("Entered quantity exceeds receivable Qty", AdfException.ERROR);
        }
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
        MethodExpression me1 =
            AdfmfJavaUtilities.getMethodExpression("#{bindings.getPendingReceiveTxn.execute}", Object.class, new Class[] {
                                                   });
        me1.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
    }
}
