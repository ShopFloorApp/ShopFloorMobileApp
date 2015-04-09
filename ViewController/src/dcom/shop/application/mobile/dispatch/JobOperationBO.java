package dcom.shop.application.mobile.dispatch;

import dcom.shop.application.base.AEntity;

import java.util.HashMap;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class JobOperationBO extends AEntity {
    private String jobNumber;
    private String jobOps;
    private String jobDesc;
    private String jobStatus;
    private String readyStatus;
    private String assembly;
    private String assemblyDesc;
    private String schStartDate;
    private String nextOpSeq;
    private String compSubInv;
    private String compLocator;
    private String assemblyUom;
    private String lastDept;
    private String lastOpSeq;
    private String nextDept;
    private String opSeq;

    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public JobOperationBO() {
        super();
    }

    public void setBOClassRow(HashMap hashMap) {
        this.setJobNumber((String) hashMap.get("jobnumber"));
        this.setJobOps((String) hashMap.get("jobops"));
        this.setJobDesc((String) hashMap.get("jobdesc"));
        this.setJobStatus((String) hashMap.get("jobstatus"));
        this.setReadyStatus((String) hashMap.get("readystatus"));
        this.setAssembly((String) hashMap.get("assembly"));
        this.setAssemblyDesc((String) hashMap.get("assemblydesc"));
        this.setSchStartDate((String) hashMap.get("schstartdate"));

    }

    public HashMap getBOClassRow(JobOperationBO jobOperation) {
        HashMap map = new HashMap();
        map.put("jobnumber", jobOperation.getJobNumber());
        map.put("jobops", jobOperation.getJobOps());
        map.put("jobdesc", jobOperation.getJobDesc());
        map.put("jobstatus", jobOperation.getJobStatus());
        map.put("readystatus", jobOperation.getReadyStatus());
        map.put("assembly", jobOperation.getAssembly());
        map.put("assemblydesc", jobOperation.getAssemblyDesc());
        map.put("schstartdate", jobOperation.getSchStartDate());
        return map;
    }


    public void setNextOpSeq(String nextOpSeq) {
        nextOpSeq = getAttributeValue(nextOpSeq);
        String oldNextOpSeq = this.nextOpSeq;
        this.nextOpSeq = nextOpSeq;
        propertyChangeSupport.firePropertyChange("nextOpSeq", oldNextOpSeq, nextOpSeq);
    }

    public String getNextOpSeq() {
        return nextOpSeq;
    }

    public void setCompSubInv(String compSubInv) {
        compSubInv = getAttributeValue(compSubInv);
        String oldCompSubInv = this.compSubInv;
        this.compSubInv = compSubInv;
        propertyChangeSupport.firePropertyChange("compSubInv", oldCompSubInv, compSubInv);
    }

    public String getCompSubInv() {
        return compSubInv;
    }

    public void setCompLocator(String compLocator) {
        compLocator = getAttributeValue(compLocator);
        String oldCompLocator = this.compLocator;
        this.compLocator = compLocator;
        propertyChangeSupport.firePropertyChange("compLocator", oldCompLocator, compLocator);
    }

    public String getCompLocator() {
        return compLocator;
    }

    public void setAssemblyUom(String assemblyUom) {
        assemblyUom = getAttributeValue(assemblyUom);
        String oldAssemblyUom = this.assemblyUom;
        this.assemblyUom = assemblyUom;
        propertyChangeSupport.firePropertyChange("assemblyUom", oldAssemblyUom, assemblyUom);
    }

    public String getAssemblyUom() {
        return assemblyUom;
    }

    public void setLastDept(String lastDept) {
        lastDept = getAttributeValue(lastDept);
        String oldLastDept = this.lastDept;
        this.lastDept = lastDept;
        propertyChangeSupport.firePropertyChange("lastDept", oldLastDept, lastDept);
    }

    public String getLastDept() {
        return lastDept;
    }

    public void setLastOpSeq(String lastOpSeq) {
        lastOpSeq = getAttributeValue(lastOpSeq);
        String oldLastOpSeq = this.lastOpSeq;
        this.lastOpSeq = lastOpSeq;
        propertyChangeSupport.firePropertyChange("lastOpSeq", oldLastOpSeq, lastOpSeq);
    }

    public String getLastOpSeq() {
        return lastOpSeq;
    }

    public void setNextDept(String nextDept) {
        nextDept = getAttributeValue(nextDept);
        String oldNextDept = this.nextDept;
        this.nextDept = nextDept;
        propertyChangeSupport.firePropertyChange("nextDept", oldNextDept, nextDept);
    }

    public String getNextDept() {
        return nextDept;
    }

    public void setJobNumber(String jobNumber) {
        jobNumber = getAttributeValue(jobNumber);
        String oldJobNumber = this.jobNumber;
        this.jobNumber = jobNumber;
        propertyChangeSupport.firePropertyChange("jobNumber", oldJobNumber, jobNumber);
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobOps(String jobOps) {
        jobOps = getAttributeValue(jobOps);
        try {
            String opSeqValue = jobOps.split(":")[1];
            setOpSeq(opSeqValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String oldJobOps = this.jobOps;
        this.jobOps = jobOps;
        propertyChangeSupport.firePropertyChange("jobOps", oldJobOps, jobOps);
    }

    public String getJobOps() {
        return jobOps;
    }

    public void setJobDesc(String jobDesc) {
        jobDesc = getAttributeValue(jobDesc);
        String oldJobDesc = this.jobDesc;
        this.jobDesc = jobDesc;
        propertyChangeSupport.firePropertyChange("jobDesc", oldJobDesc, jobDesc);

    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobStatus(String jobStatus) {
        jobStatus = getAttributeValue(jobStatus);
        String oldJobStatus = this.jobStatus;
        this.jobStatus = jobStatus;
        propertyChangeSupport.firePropertyChange("jobStatus", oldJobStatus, jobStatus);
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setReadyStatus(String readyStatus) {
        readyStatus = getAttributeValue(readyStatus);
        String oldReadyStatus = this.readyStatus;
        this.readyStatus = readyStatus;
        propertyChangeSupport.firePropertyChange("readyStatus", oldReadyStatus, readyStatus);
    }

    public String getReadyStatus() {
        return readyStatus;
    }

    public void setAssembly(String assembly) {
        assembly = getAttributeValue(assembly);
        String oldAssembly = this.assembly;
        this.assembly = assembly;
        propertyChangeSupport.firePropertyChange("assembly", oldAssembly, assembly);
    }

    public String getAssembly() {
        return assembly;
    }

    public void setAssemblyDesc(String assemblyDesc) {
        assemblyDesc = getAttributeValue(assemblyDesc);
        String oldAssemblyDesc = this.assemblyDesc;
        this.assemblyDesc = assemblyDesc;
        propertyChangeSupport.firePropertyChange("assemblyDesc", oldAssemblyDesc, assemblyDesc);

    }

    public String getAssemblyDesc() {
        return assemblyDesc;
    }

    public void setSchStartDate(String schStartDate) {
        schStartDate = getAttributeValue(schStartDate);
        schStartDate = super.toUnixDate(schStartDate);
        String oldSchStartDate = this.schStartDate;
        this.schStartDate = schStartDate;
        propertyChangeSupport.firePropertyChange("schStartDate", oldSchStartDate, schStartDate);
    }

    public String getSchStartDate() {
        return super.toDate(schStartDate);
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }


    public void setOpSeq(String opSeq) {
        String oldOpSeq = this.opSeq;
        this.opSeq = opSeq;
        propertyChangeSupport.firePropertyChange("opSeq", oldOpSeq, opSeq);
    }

    public String getOpSeq() {
        return opSeq;
    }
}
