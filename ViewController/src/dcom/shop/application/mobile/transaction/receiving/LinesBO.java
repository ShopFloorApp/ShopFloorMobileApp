package dcom.shop.application.mobile.transaction.receiving;

import java.util.HashMap;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class LinesBO {
    private Integer rowLineIdx;
    private String Lines;
    private String subInv;
    private String locator;
    private String quantity;
    private String Uom;
    private String Lpn;
    private String isNewEntity;
    private Integer receiveTxnId;
    private String docRefLine;
    private String shipmentLine;
    private String lotControl;
    private String serialControl;
    private static Integer idx=new Integer(0);
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void setRowLineIdx(Integer rowLineIdx) {
        Integer oldRowLineIdx = this.rowLineIdx;
        this.rowLineIdx = rowLineIdx;
        propertyChangeSupport.firePropertyChange("rowLineIdx", oldRowLineIdx, rowLineIdx);
    }

    public Integer getRowLineIdx() {
        return rowLineIdx;
    }

    public void setDocRefLine(String docRefLine) {
        String oldDocRefLine = this.docRefLine;
        this.docRefLine = docRefLine;
        propertyChangeSupport.firePropertyChange("docRefLine", oldDocRefLine, docRefLine);
    }

    public void setLotControl(String lotControl) {
        String oldLotControl = this.lotControl;
        this.lotControl = lotControl;
        propertyChangeSupport.firePropertyChange("lotControl", oldLotControl, lotControl);
    }

    public String getLotControl() {
        return lotControl;
    }

    public void setSerialControl(String serialControl) {
        String oldSerialControl = this.serialControl;
        this.serialControl = serialControl;
        propertyChangeSupport.firePropertyChange("serialControl", oldSerialControl, serialControl);
    }

    public String getSerialControl() {
        return serialControl;
    }

    public String getDocRefLine() {
        return docRefLine;
    }

    public void setShipmentLine(String shipmentLine) {
        String oldShipmentLine = this.shipmentLine;
        this.shipmentLine = shipmentLine;
        propertyChangeSupport.firePropertyChange("shipmentLine", oldShipmentLine, shipmentLine);
    }

    public String getShipmentLine() {
        return shipmentLine;
    }

    public void setBOClassRow(HashMap hashMap) {
        this.setRowLineIdx((Integer)hashMap.get("rowlineidx"));
        this.setLines((String) hashMap.get("lines"));
        this.setSubInv((String) hashMap.get("subinv"));
        this.setLocator((String) hashMap.get("locator"));
        this.setQuantity((String) hashMap.get("quantity"));
        this.setUom((String) hashMap.get("uom"));
        this.setLpn((String) hashMap.get("lpn"));
        this.setIsNewEntity((String) hashMap.get("isnewentity"));
        this.setReceiveTxnId((Integer) hashMap.get("receivetxnid"));
    }

    public HashMap getBOClassRow(LinesBO lines) {
        HashMap map = new HashMap();
        map.put("rowlineidx", lines.getRowLineIdx());
        map.put("lines", lines.getLines());
        map.put("subinv", lines.getSubInv());
        map.put("locator", lines.getLocator());
        map.put("quantity", lines.getQuantity());
        map.put("uom", lines.getUom());
        map.put("lpn", lines.getLpn());
        map.put("isnewentity", lines.getIsNewEntity());
        map.put("receivetxnid", lines.getReceiveTxnId());
        return map;
    }



    public void setReceiveTxnId(Integer receiveTxnId) {
        Integer oldReceiveTxnId = this.receiveTxnId;
        this.receiveTxnId = receiveTxnId;
        propertyChangeSupport.firePropertyChange("receiveTxnId", oldReceiveTxnId, receiveTxnId);
    }

    public Integer getReceiveTxnId() {
       receiveTxnId = (Integer) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.ReceiveTxnId}");
        return receiveTxnId;
    }

    public void setLines(String Lines) {
        String oldLines = this.Lines;
        this.Lines = Lines;
        propertyChangeSupport.firePropertyChange("Lines", oldLines, Lines);
    }

    public void setUom(String Uom) {
        String oldUom = this.Uom;
        this.Uom = Uom;
        propertyChangeSupport.firePropertyChange("Uom", oldUom, Uom);
    }

    public String getUom() {
        return Uom;
    }

    public void setLpn(String Lpn) {
        String oldLpn = this.Lpn;
        this.Lpn = Lpn;
        propertyChangeSupport.firePropertyChange("Lpn", oldLpn, Lpn);
    }

    public String getLpn() {
        return Lpn;
    }

    public String getLines() {
        return Lines;
    }

    public void setIsNewEntity(String isNewEntity) {
        String oldIsNewEntity = this.isNewEntity;
        this.isNewEntity = isNewEntity;
        propertyChangeSupport.firePropertyChange("isNewEntity", oldIsNewEntity, isNewEntity);
    }

    public String getIsNewEntity() {
        return isNewEntity;
    }

    public void setSubInv(String subInv) {
        String oldSubInv = this.subInv;
        this.subInv = subInv;
        propertyChangeSupport.firePropertyChange("subInv", oldSubInv, subInv);
    }

    public String getSubInv() {
        return subInv;
    }

    public void setLocator(String locator) {
        String oldLocator = this.locator;
        this.locator = locator;
        propertyChangeSupport.firePropertyChange("locator", oldLocator, locator);
    }

    public String getLocator() {
        return locator;
    }

    public void setQuantity(String quantity) {
        String oldQuantity = this.quantity;
        this.quantity = quantity;
        propertyChangeSupport.firePropertyChange("quantity", oldQuantity, quantity);
    }

    public String getQuantity() {
        return quantity;
    }

    public LinesBO() {
        super();
    }
    public LinesBO(Integer rowIdx,String line,String subInv,String locator,String Quantity,String Uom,String Lpn,String docRefLine,String shipmentLine,String lotCtrl,String serialCtrl,String isNewEntity) {
        super();
        this.rowLineIdx=rowIdx;
        this.Lines=line;
        this.subInv=subInv==null?"":subInv;
        this.locator=locator==null?"":locator;
        this.quantity=Quantity==null?"":Quantity;
        this.isNewEntity=isNewEntity;
        this.Uom=Uom==null?"":Uom;
        this.Lpn=Lpn==null?"":Lpn;
        this.docRefLine=docRefLine==null?"":docRefLine;
        this.shipmentLine=shipmentLine==null?"":shipmentLine;
        this.lotControl=lotCtrl==null?"":lotCtrl;
        this.serialControl=serialCtrl==null?"":serialCtrl;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
