package dcom.shop.application.mobile.transaction.receiving;



import java.util.HashMap;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class ShipmentBO {
    private Integer receiveTxnId;
    private String orgCode;
    private String docType;
    private String docRef;
    private String vendor;
    private String customer;
    private String carrier;
    private String packingSlip;
    private String bol;
    private String wayAirBill;
    private String shipmentNum;
    private String shippedDate;
    private String comments;
    private String isSubmitted;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    
    public void setBOClassRow(HashMap hashMap) {
        this.setReceiveTxnId((Integer)hashMap.get("receivetxnid"));
        this.setOrgCode((String) hashMap.get("orgcode"));
        this.setDocType((String) hashMap.get("doctype"));
        this.setDocRef((String) hashMap.get("docref"));
        this.setVendor((String) hashMap.get("vendor"));
        this.setCustomer((String) hashMap.get("customer"));
        this.setCarrier((String) hashMap.get("carrier"));
        this.setPackingSlip((String) hashMap.get("packingslip"));
        this.setBol((String) hashMap.get("bol"));
        this.setWayAirBill((String) hashMap.get("wayairbill"));
        this.setShipmentNum((String)hashMap.get("shipmentnum"));
        this.setShippedDate((String)hashMap.get("shippeddate"));
        this.setComments((String)hashMap.get("comments"));
        this.setIsSubmitted((String)hashMap.get("issubmitted"));
    }

    public HashMap getBOClassRow(ShipmentBO shipment) {
        HashMap map = new HashMap();
        map.put("receivetxnid", shipment.getReceiveTxnId());
        map.put("orgcode", shipment.getOrgCode());
        map.put("doctype", shipment.getDocType());
        map.put("docref", shipment.getDocRef());
        map.put("vendor", shipment.getVendor());
        map.put("customer", shipment.getCustomer());
        map.put("carrier", shipment.getCarrier());
        map.put("packingslip", shipment.getPackingSlip());
        map.put("bol", shipment.getBol());
        map.put("wayairbill", shipment.getWayAirBill());
        map.put("shipmentnum", shipment.getShipmentNum());
        map.put("shippeddate", shipment.getShippedDate());
        map.put("comments", shipment.getComments());
        map.put("issubmitted", shipment.getIsSubmitted());
        return map;
    }

    public void setIsSubmitted(String isSubmitted) {
        String oldIsSubmitted = this.isSubmitted;
        this.isSubmitted = isSubmitted;
        propertyChangeSupport.firePropertyChange("isSubmitted", oldIsSubmitted, isSubmitted);
    }

    public String getIsSubmitted() {
        return isSubmitted;
    }

    public void setReceiveTxnId(Integer receiveTxnId) {
        Integer oldReceiveTxnId = this.receiveTxnId;
        this.receiveTxnId = receiveTxnId;
        propertyChangeSupport.firePropertyChange("receiveTxnId", oldReceiveTxnId, receiveTxnId);
    }

    public Integer getReceiveTxnId() {
        return receiveTxnId;
    }

    public ShipmentBO() {
        super();
    }

    public void setOrgCode(String orgCode) {
        String oldOrgCode = this.orgCode;
        this.orgCode = orgCode;
        propertyChangeSupport.firePropertyChange("orgCode", oldOrgCode, orgCode);
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setDocType(String docType) {
        String oldDocType = this.docType;
        this.docType = docType;
        propertyChangeSupport.firePropertyChange("docType", oldDocType, docType);
    }

    public String getDocType() {
        return docType;
    }

    public void setDocRef(String docRef) {
        String oldDocRef = this.docRef;
        this.docRef = docRef;
        propertyChangeSupport.firePropertyChange("docRef", oldDocRef, docRef);
    }

    public String getDocRef() {
        return docRef;
    }

    public void setVendor(String vendor) {
        String oldVendor = this.vendor;
        this.vendor = vendor;
        propertyChangeSupport.firePropertyChange("vendor", oldVendor, vendor);
    }

    public String getVendor() {
        return vendor;
    }

    public void setCustomer(String customer) {
        String oldCustomer = this.customer;
        this.customer = customer;
        propertyChangeSupport.firePropertyChange("customer", oldCustomer, customer);
    }

    public String getCustomer() {
        return customer;
    }

    public void setCarrier(String carrier) {
        String oldCarrier = this.carrier;
        this.carrier = carrier;
        propertyChangeSupport.firePropertyChange("carrier", oldCarrier, carrier);
    }

    public String getCarrier() {
        return carrier;
    }

    public void setPackingSlip(String packingSlip) {
        String oldPackingSlip = this.packingSlip;
        this.packingSlip = packingSlip;
        propertyChangeSupport.firePropertyChange("packingSlip", oldPackingSlip, packingSlip);
    }

    public String getPackingSlip() {
        return packingSlip;
    }

    public void setBol(String bol) {
        String oldBol = this.bol;
        this.bol = bol;
        propertyChangeSupport.firePropertyChange("bol", oldBol, bol);
    }

    public String getBol() {
        return bol;
    }

    public void setWayAirBill(String wayAirBill) {
        String oldWayAirBill = this.wayAirBill;
        this.wayAirBill = wayAirBill;
        propertyChangeSupport.firePropertyChange("wayAirBill", oldWayAirBill, wayAirBill);
    }

    public String getWayAirBill() {
        return wayAirBill;
    }

    public void setShipmentNum(String shipmentNum) {
        String oldShipmentNum = this.shipmentNum;
        this.shipmentNum = shipmentNum;
        propertyChangeSupport.firePropertyChange("shipmentNum", oldShipmentNum, shipmentNum);
    }

    public String getShipmentNum() {
        return shipmentNum;
    }

    public void setShippedDate(String shippedDate) {
        String oldShippedDate = this.shippedDate;
        this.shippedDate = shippedDate;
        propertyChangeSupport.firePropertyChange("shippedDate", oldShippedDate, shippedDate);
    }

    public String getShippedDate() {
        return shippedDate;
    }

    public void setComments(String comments) {
        String oldComments = this.comments;
        this.comments = comments;
        propertyChangeSupport.firePropertyChange("comments", oldComments, comments);
    }

    public String getComments() {
        return comments;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
