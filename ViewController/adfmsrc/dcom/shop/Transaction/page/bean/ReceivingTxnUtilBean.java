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
        for(int j=0;j<receiveDc.s_shipmentLines.size();j++){
            ShipmentLinesBO sl = (ShipmentLinesBO) receiveDc.s_shipmentLines.get(j);
            if(sl.getDocRefLine().equals(AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.docRefLineAdd}"))){
                        AdfmfJavaUtilities.setELValue("#{pageFlowScope.subInvAdd}", sl.getSubInv());
                        AdfmfJavaUtilities.setELValue("#{pageFlowScope.locatorAdd}", sl.getLocator());
                        AdfmfJavaUtilities.setELValue("#{pageFlowScope.quantityAdd}", sl.getQty());
                        AdfmfJavaUtilities.setELValue("#{pageFlowScope.quantityDefAdd}", sl.getQty());
                        AdfmfJavaUtilities.setELValue("#{pageFlowScope.uomAdd}", sl.getUom());
                        AdfmfJavaUtilities.setELValue("#{pageFlowScope.docRefLineAdd}",sl.getDocRefLine());
                        AdfmfJavaUtilities.setELValue("#{pageFlowScope.shipmentLineAdd}", sl.getShipmentLine());
                        AdfmfJavaUtilities.setELValue("#{pageFlowScope.lineAdd}", sl.getItem());
                        AdfmfJavaUtilities.setELValue("#{pageFlowScope.lotControlAdd}",sl.getLotControl());
                        AdfmfJavaUtilities.setELValue("#{pageFlowScope.serialControlAdd}", sl.getSerialControl());  
            }          
        }
//        AdfmfJavaUtilities.setELValue("#{pageFlowScope.subInvAdd}", AdfmfJavaUtilities.evaluateELExpression("#{bindings.subInv1.inputValue}"));
//        AdfmfJavaUtilities.setELValue("#{pageFlowScope.locatorAdd}", AdfmfJavaUtilities.evaluateELExpression("#{bindings.locator2.inputValue}"));
//        AdfmfJavaUtilities.setELValue("#{pageFlowScope.quantityAdd}", AdfmfJavaUtilities.evaluateELExpression("#{bindings.qty.inputValue}"));
//        AdfmfJavaUtilities.setELValue("#{pageFlowScope.uomAdd}", AdfmfJavaUtilities.evaluateELExpression("#{bindings.uom.inputValue}"));
//        AdfmfJavaUtilities.setELValue("#{pageFlowScope.docRefLineAdd}", AdfmfJavaUtilities.evaluateELExpression("#{bindings.docRefLine.inputValue}"));
//        AdfmfJavaUtilities.setELValue("#{pageFlowScope.shipmentLineAdd}", AdfmfJavaUtilities.evaluateELExpression("#{bindings.shipmentLine.inputValue}"));
//        AdfmfJavaUtilities.setELValue("#{pageFlowScope.lineAdd}", AdfmfJavaUtilities.evaluateELExpression("#{bindings.lineItemVar1.inputValue}"));
//        AdfmfJavaUtilities.setELValue("#{pageFlowScope.lotControlAdd}", AdfmfJavaUtilities.evaluateELExpression("#{bindings.lotControl.inputValue}"));
//        AdfmfJavaUtilities.setELValue("#{pageFlowScope.serialControlAdd}", AdfmfJavaUtilities.evaluateELExpression("#{bindings.serialControl.inputValue}"));
    }
    
    public void addRecord(ActionEvent ae){
        if(idx==0){
           idx = (Integer) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.nextLinesCount}");            
        }
        idx++;
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.rowIdxAdd}", idx);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.lineAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.subInvAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.locatorAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.quantityAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.quantityDefAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.uomAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.lpnAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.docRefLineAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.shipmentLineAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.lotControlAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.serialControlAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.rowAction}", null);
    }
    public void updateRecord(ActionEvent ae){
        quantityValidation(ae);
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
        String lotControl = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.lotControlAdd}");
        String serialControl = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.serialControlAdd}");
        ReceivingTxnDC.s_lines.add(new LinesBO(currentItem,line,subInv,locator,quantity,uom,lpn,docRefLine,shipmentLine,lotControl,serialControl,"Y"));
    
    ArrayList linColl = (ArrayList) receiveDc.s_shipmentLines;
        for(int j=0;j<receiveDc.s_shipmentLines.size();j++){
            ShipmentLinesBO lines = (ShipmentLinesBO) receiveDc.s_shipmentLines.get(j);
            if(lines.getDocRefLine().equalsIgnoreCase(docRefLine) && lines.getShipmentLine().equalsIgnoreCase(shipmentLine)){
                String lineQty=lines.getQty();
                Integer availableQty=Integer.parseInt(lineQty)-Integer.parseInt(quantity);
                if(availableQty==0){
                    linColl.remove(j); 
                }else{
                    ((ShipmentLinesBO)linColl.get(j)).setQty(availableQty.toString());
                }
            }
        }
        receiveDc.s_shipmentLines=linColl;
    }
    public void removeRecords(ActionEvent ae){
//        receiveDc.s_lines.clear();
        String rowAction = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.rowAction}");
        String docRefLine = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.docRefLineAdd}");
        String shipmentLine = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.shipmentLineAdd}");
        if(rowAction!=null){
            ArrayList linColl = (ArrayList) receiveDc.s_shipmentLines;
            for(int j=0;j<receiveDc.s_shipmentLines.size();j++){
            ShipmentLinesBO lines = (ShipmentLinesBO) receiveDc.s_shipmentLines.get(j);
            if(lines.getDocRefLine().equalsIgnoreCase(docRefLine) && lines.getShipmentLine().equalsIgnoreCase(shipmentLine)){

                    linColl.remove(j); 
                
            }
            }
            receiveDc.s_shipmentLines=linColl;
        }

    }
    public void deleteCurrectRecord(ActionEvent ae){
        Integer currentItem = (Integer) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.currentItem}");
        
        int count=0;
        ArrayList linColl = (ArrayList) receiveDc.s_shipmentLines;

 
        
        ArrayList coll=(ArrayList) receiveDc.s_lines;
                for(int i=0;i<receiveDc.s_lines.size();i++){
                    LinesBO lines = (LinesBO) receiveDc.s_lines.get(i);
                    if(lines.getRowLineIdx()==currentItem){
                        //
                        for(int j=0;j<receiveDc.s_shipmentLines.size();j++){
                            ShipmentLinesBO slines = (ShipmentLinesBO) receiveDc.s_shipmentLines.get(j);
                            if(slines.getDocRefLine().equalsIgnoreCase(lines.getDocRefLine()) && slines.getShipmentLine().equalsIgnoreCase(lines.getShipmentLine())){
                                count++;
                                String lineQty=slines.getQty();
                                Integer availableQty=Integer.parseInt(lineQty)+Integer.parseInt(lines.getQuantity());                    
                                ((ShipmentLinesBO)linColl.get(j)).setQty(availableQty.toString());                 
                            }
                        }
                        //
                        if(count==0){
                            ShipmentLinesBO shipLine=new ShipmentLinesBO();
                            shipLine.setDocRefLine(lines.getDocRefLine());
                            shipLine.setItem(lines.getLines());
                            shipLine.setItemDecs("");
                            shipLine.setLocator(lines.getLocator());
                            shipLine.setLotControl(lines.getLotControl());
                            shipLine.setLpn(lines.getLpn());
                            shipLine.setQty(lines.getQuantity());
                            shipLine.setReceiveTxnId(lines.getReceiveTxnId());
                            shipLine.setSerialControl(lines.getSerialControl());
                            shipLine.setShipmentLine(lines.getShipmentLine());
                            shipLine.setSubInv(lines.getSubInv());
                            shipLine.setUom(lines.getUom());
                            linColl.add(shipLine);
                        }
                        coll.remove(i);
                    }
                }
                
        receiveDc.s_shipmentLines=linColl;
        MethodExpression me =
            AdfmfJavaUtilities.getMethodExpression("#{bindings.refreshLines.execute}", Object.class, new Class[] {
                                                   });
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { }); 
        MethodExpression me1 =
            AdfmfJavaUtilities.getMethodExpression("#{bindings.refreshShipmentLines.execute}", Object.class, new Class[] {
                                                   });
        me1.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { }); 
    }
    
    public void rePopulateLines(ActionEvent ae){
        Integer currentItem = (Integer) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.rowIdxAdd}");
        
        int count=0;
        ArrayList linColl = (ArrayList) receiveDc.s_shipmentLines;

        
        
        ArrayList coll=(ArrayList) receiveDc.s_lines;
                for(int i=0;i<receiveDc.s_lines.size();i++){
                    LinesBO lines = (LinesBO) receiveDc.s_lines.get(i);
                    if(lines.getRowLineIdx()==currentItem){
                        //
                        for(int j=0;j<receiveDc.s_shipmentLines.size();j++){
                            ShipmentLinesBO slines = (ShipmentLinesBO) receiveDc.s_shipmentLines.get(j);
                            if(slines.getDocRefLine().equalsIgnoreCase(lines.getDocRefLine()) && slines.getShipmentLine().equalsIgnoreCase(lines.getShipmentLine())){
                                count++;
                                String lineQty=slines.getQty();
                                Integer availableQty=Integer.parseInt(lineQty)+Integer.parseInt(lines.getQuantity());                    
                                ((ShipmentLinesBO)linColl.get(j)).setQty(availableQty.toString());                 
                            }
                        }
                        //
                        if(count==0){
                            ShipmentLinesBO shipLine=new ShipmentLinesBO();
                            shipLine.setDocRefLine(lines.getDocRefLine());
                            shipLine.setItem(lines.getLines());
                            shipLine.setItemDecs("");
                            shipLine.setLocator(lines.getLocator());
                            shipLine.setLotControl(lines.getLotControl());
                            shipLine.setLpn(lines.getLpn());
                            shipLine.setQty(lines.getQuantity());
                            shipLine.setReceiveTxnId(lines.getReceiveTxnId());
                            shipLine.setSerialControl(lines.getSerialControl());
                            shipLine.setShipmentLine(lines.getShipmentLine());
                            shipLine.setSubInv(lines.getSubInv());
                            shipLine.setUom(lines.getUom());
                            linColl.add(shipLine);
                        }
                       
                    }
                }
                
        receiveDc.s_shipmentLines=linColl;
        MethodExpression me =
            AdfmfJavaUtilities.getMethodExpression("#{bindings.refreshShipmentLines.execute}", Object.class, new Class[] {
                                                   });
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { }); 
        MethodExpression me1 =
            AdfmfJavaUtilities.getMethodExpression("#{bindings.refreshLines.execute}", Object.class, new Class[] {
                                                   });
        me1.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
    }
    
    public void addMore(ActionEvent ae){
        updateRecord(ae);
        addRecord(ae);
        MethodExpression me =
            AdfmfJavaUtilities.getMethodExpression("#{bindings.refreshShipmentLines.execute}", Object.class, new Class[] {
                                                   });
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { }); 
        MethodExpression me1 =
            AdfmfJavaUtilities.getMethodExpression("#{bindings.refreshLines.execute}", Object.class, new Class[] {
                                                   });
        me1.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { }); 
    }
    
    public void quantityValidation(ActionEvent ae){
        String quantityEntered = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.quantityAdd}");
        String quantityReceivable = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.quantityDefAdd}");
        if(Integer.parseInt(quantityEntered)<1){

        
            if (quantityEntered==null || quantityEntered==""){
                quantityEntered="0";
            }
            if (quantityReceivable==null || quantityReceivable==""){
                quantityReceivable="0";
            }
        throw new AdfException("Entered quantity should be more than zero", AdfException.ERROR);
        }
        if(Integer.parseInt(quantityEntered)>Integer.parseInt(quantityReceivable)){
            throw new AdfException("Entered quantity exceeds receivable Qty", AdfException.ERROR);
        }
    }
    
    public void quantityValueChange(ValueChangeEvent vce){
        String quantityEntered = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.quantityAdd}");
        String quantityReceivable = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.quantityDefAdd}");

        if (quantityEntered==null || quantityEntered.equals("")){
            quantityEntered="0";
        }
        if (quantityReceivable==null || quantityReceivable.equals("")){
            quantityReceivable="0";
        }
        if(Integer.parseInt(quantityEntered)<1){
            throw new AdfException("Entered quantity should be more than zero", AdfException.ERROR);
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
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.successMsg}", null);
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
    
    public void enterReceivingPage(ActionEvent ae){
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
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.successMsg}", null);
        receiveDc.s_lines.clear();
        receiveDc.s_shipmentLines.clear();
        MethodExpression me =
            AdfmfJavaUtilities.getMethodExpression("#{bindings.refreshLines.execute}", Object.class, new Class[] {
                                                   });
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
    }
    
    public String navigateToTxnSummary(){
        String status = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.isError}");
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.isError}", null);
        if(status!=null){
            return "abc";
        }else{
            return "pendingTxn";
        }
    }
    
    public void receivingTypeChange(ValueChangeEvent ve){
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
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.successMsg}", null);                                                                                
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
    
    public void documentNoChange(ActionEvent ae){
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.rowIdxAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.lineAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.subInvAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.locatorAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.quantityAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.uomAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.lpnAdd}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.carrier}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.packSlip}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.bol}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.wayAirBill}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.shipment}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.shippedDate}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.comments}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.suppCustName}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.successMsg}", null);                                                                                
        receiveDc.s_lines.clear();
        receiveDc.s_shipmentLines.clear();
        MethodExpression me =
            AdfmfJavaUtilities.getMethodExpression("#{bindings.refreshLines.execute}", Object.class, new Class[] {
                                                   });
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
//        MethodExpression me1 =
//            AdfmfJavaUtilities.getMethodExpression("#{bindings.getPendingReceiveTxn.execute}", Object.class, new Class[] {
//                                                   });
//        me1.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
    }
    
    public String clearLPNPutaway(){
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.lpnMain}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.itemLPN}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.uomLPN}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.qtyReceivedLPN}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.lpnSubLPN}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.subinvLPN}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.locatorLPN}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.descriptionLPN}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.availableQtyLPN}", null);       
        return null;
    }
    
    public void generateLPN(ActionEvent ae){
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchLpnKeyword}", null);
        MethodExpression me =
            AdfmfJavaUtilities.getMethodExpression("#{bindings.GenerateLpnWS.execute}", Object.class, new Class[] {
                                                   });
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.lpnSubLPN}", AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.searchLpnKeyword}"));
    }
}
