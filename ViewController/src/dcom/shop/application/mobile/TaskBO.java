package dcom.shop.application.mobile;

import dcom.shop.application.base.AEntity;

import dcom.shop.application.mobile.dispatch.JobOperationBO;

import java.util.Date;

import java.util.HashMap;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;

public class TaskBO extends AEntity {
    public TaskBO() {
        super();
    }
    
    String ORGCODE;
    String TASKNUM;
    String TASKLINE;
    String ITEM;
    String ITEMDESC;
    String QTY;
    String UOM;
    String PRIORITY;
    String TASKTYPE;
    String SUBINV;
    String LOCATOR;
    String STATUS;
    Date CREATIONDATE;
    String DESTSUBINV;
    String DESTLOCATOR;
    String EMPLOYEE;
    Date STARTDATE;
    Date ENDDATE;
    String USERNAME;
    protected transient PropertyChangeSupport   
                propertyChangeSupport = new PropertyChangeSupport(this);  
      
      public void addPropertyChangeListener(PropertyChangeListener l) {  
         propertyChangeSupport.addPropertyChangeListener(l);  
      }  
     public void removePropertyChangeListener(PropertyChangeListener l) {  
         propertyChangeSupport.removePropertyChangeListener(l);  
      }  


    public void setORGCODE(String ORGCODE) {
        ORGCODE=getAttributeValue(ORGCODE);
        String oldORGCODE=this.ORGCODE;
        this.ORGCODE = ORGCODE;
        propertyChangeSupport.firePropertyChange("ORGCODE", oldORGCODE, ORGCODE);
    }

    public String getORGCODE() {
        return ORGCODE;
    }

    public void setTASKNUM(String TASKNUM) {
        TASKNUM=getAttributeValue(TASKNUM);
        String oldTASKNUM=this.TASKNUM;
        this.TASKNUM = TASKNUM;
        propertyChangeSupport.firePropertyChange("TASKNUM", oldTASKNUM, TASKNUM);
    }

    public String getTASKNUM() {
        return TASKNUM;
    }

    public void setTASKLINE(String TASKLINE) {
        this.TASKLINE = TASKLINE;
    }

    public String getTASKLINE() {
        return TASKLINE;
    }

    public void setITEM(String ITEM) {
        this.ITEM = ITEM;
    }

    public String getITEM() {
        return ITEM;
    }

    public void setITEMDESC(String ITEMDESC) {
        this.ITEMDESC = ITEMDESC;
    }

    public String getITEMDESC() {
        return ITEMDESC;
    }

    public void setQTY(String QTY) {
        this.QTY = QTY;
    }

    public String getQTY() {
        return QTY;
    }

    public void setUOM(String UOM) {
        this.UOM = UOM;
    }

    public String getUOM() {
        return UOM;
    }

    public void setPRIORITY(String PRIORITY) {
        PRIORITY=getAttributeValue(PRIORITY);
        String oldPRIORITY=this.PRIORITY;
        this.PRIORITY = PRIORITY;
        propertyChangeSupport.firePropertyChange("PRIORITY", oldPRIORITY, PRIORITY);
    }

    public String getPRIORITY() {
        return PRIORITY;
    }

    public void setTASKTYPE(String TASKTYPE) {
        TASKTYPE=getAttributeValue(TASKTYPE);
        String oldTASKTYPE=this.TASKTYPE;
        this.TASKTYPE = TASKTYPE;
        propertyChangeSupport.firePropertyChange("TASKTYPE", oldTASKTYPE, TASKTYPE);
    }

    public String getTASKTYPE() {
        return TASKTYPE;
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

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setCREATIONDATE(Date CREATIONDATE) {
        this.CREATIONDATE = CREATIONDATE;
    }

    public Date getCREATIONDATE() {
        return CREATIONDATE;
    }

    public void setDESTSUBINV(String DESTSUBINV) {
        this.DESTSUBINV = DESTSUBINV;
    }

    public String getDESTSUBINV() {
        return DESTSUBINV;
    }

    public void setDESTLOCATOR(String DESTLOCATOR) {
        this.DESTLOCATOR = DESTLOCATOR;
    }

    public String getDESTLOCATOR() {
        return DESTLOCATOR;
    }

    public void setEMPLOYEE(String EMPLOYEE) {
        this.EMPLOYEE = EMPLOYEE;
    }

    public String getEMPLOYEE() {
        return EMPLOYEE;
    }

    public void setSTARTDATE(Date STARTDATE) {
        this.STARTDATE = STARTDATE;
    }

    public Date getSTARTDATE() {
        return STARTDATE;
    }

    public void setENDDATE(Date ENDDATE) {
        this.ENDDATE = ENDDATE;
    }

    public Date getENDDATE() {
        return ENDDATE;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getUSERNAME() {
        return USERNAME;
    }
    
    public HashMap getBOClassRow(TaskBO task) {
        HashMap map = new HashMap();
        map.put("tasknum", task.getTASKNUM());
        map.put("tasktype", task.getTASKTYPE());
        map.put("priority", task.getPRIORITY());
        return map;
    }

}
