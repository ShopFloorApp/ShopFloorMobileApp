package dcom.shop.application.mobile.txn;

import dcom.shop.application.mobile.WarehouseBO;

import java.util.HashMap;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class ConcurrentProgramBO implements Comparable {
    private String ConcProgId;
    private String ProgramShortName;
    private String ApplicationShortName;
    private String ProgramName;
    private String IsNewEntity;
    private String RowOperation;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void setConcProgId(String ConcProgId) {
        String oldConcProgId = this.ConcProgId;
        this.ConcProgId = ConcProgId;
        propertyChangeSupport.firePropertyChange("ConcProgId", oldConcProgId, ConcProgId);
    }

    public String getConcProgId() {
        return ConcProgId;
    }

    public void setProgramShortName(String ProgramShortName) {
        String oldProgramShortName = this.ProgramShortName;
        this.ProgramShortName = ProgramShortName;
        propertyChangeSupport.firePropertyChange("ProgramShortName", oldProgramShortName, ProgramShortName);
    }

    public String getProgramShortName() {
        return ProgramShortName;
    }

    public void setApplicationShortName(String ApplicationShortName) {
        String oldApplicationShortName = this.ApplicationShortName;
        this.ApplicationShortName = ApplicationShortName;
        propertyChangeSupport.firePropertyChange("ApplicationShortName", oldApplicationShortName, ApplicationShortName);
    }

    public String getApplicationShortName() {
        return ApplicationShortName;
    }

    public void setProgramName(String ProgramName) {
        String oldProgramName = this.ProgramName;
        this.ProgramName = ProgramName;
        propertyChangeSupport.firePropertyChange("ProgramName", oldProgramName, ProgramName);
    }

    public String getProgramName() {
        return ProgramName;
    }

    public void setIsNewEntity(String IsNewEntity) {
        String oldIsNewEntity = this.IsNewEntity;
        this.IsNewEntity = IsNewEntity;
        propertyChangeSupport.firePropertyChange("IsNewEntity", oldIsNewEntity, IsNewEntity);
    }

    public String getIsNewEntity() {
        return IsNewEntity;
    }

    public void setRowOperation(String RowOperation) {
        String oldRowOperation = this.RowOperation;
        this.RowOperation = RowOperation;
        propertyChangeSupport.firePropertyChange("RowOperation", oldRowOperation, RowOperation);
    }

    public String getRowOperation() {
        return RowOperation;
    }


    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
    public int compareTo(Object o) {
        // TODO Implement this method
        return 0;
    }
    public void setBOClassRow(HashMap hashMap) {
        this.setConcProgId((String)hashMap.get("concprogid"));
        this.setProgramName((String) hashMap.get("programname"));
        this.setProgramShortName((String) hashMap.get("programshortname"));
        this.setApplicationShortName((String) hashMap.get("applicationshortname"));
        this.setIsNewEntity((String) hashMap.get("isnewentity"));
        this.setRowOperation((String) hashMap.get("rowoperation"));
    }

    public HashMap getBOClassRow(ConcurrentProgramBO concurrentProgram) {
        HashMap map = new HashMap();
        map.put("concprogid", concurrentProgram.getConcProgId());
        map.put("programname", concurrentProgram.getProgramName());
        map.put("programshortname", concurrentProgram.getProgramShortName());
        map.put("applicationshortname", concurrentProgram.getApplicationShortName());
        map.put("isnewentity", concurrentProgram.getIsNewEntity());
        map.put("rowoperation", concurrentProgram.getRowOperation());
        return map;
    }
}
