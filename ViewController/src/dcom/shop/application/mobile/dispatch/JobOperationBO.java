package dcom.shop.application.mobile.dispatch;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class JobOperationBO {
    private String jobNumber;
    private String jobOps;
    private String jobDesc;
    private String jobStatus;
    private String readyStatus;
    private String assembly;
    private String assemblyDesc;
    private String schStartDate;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public JobOperationBO() {
        super();
    }

    public void setJobNumber(String jobNumber) {
        String oldJobNumber = this.jobNumber;
        this.jobNumber = jobNumber;
        propertyChangeSupport.firePropertyChange("jobNumber", oldJobNumber, jobNumber);
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobOps(String jobOps) {
        String oldJobOps = this.jobOps;
        this.jobOps = jobOps;
        propertyChangeSupport.firePropertyChange("jobOps", oldJobOps, jobOps);
    }

    public String getJobOps() {
        return jobOps;
    }

    public void setJobDesc(String jobDesc) {
        String oldJobDesc = this.jobDesc;
        this.jobDesc = jobDesc;
        propertyChangeSupport.firePropertyChange("jobDesc", oldJobDesc, jobDesc);
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobStatus(String jobStatus) {
        String oldJobStatus = this.jobStatus;
        this.jobStatus = jobStatus;
        propertyChangeSupport.firePropertyChange("jobStatus", oldJobStatus, jobStatus);
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setReadyStatus(String readyStatus) {
        String oldReadyStatus = this.readyStatus;
        this.readyStatus = readyStatus;
        propertyChangeSupport.firePropertyChange("readyStatus", oldReadyStatus, readyStatus);
    }

    public String getReadyStatus() {
        return readyStatus;
    }

    public void setAssembly(String assembly) {
        String oldAssembly = this.assembly;
        this.assembly = assembly;
        propertyChangeSupport.firePropertyChange("assembly", oldAssembly, assembly);
    }

    public String getAssembly() {
        return assembly;
    }

    public void setAssemblyDesc(String assemblyDesc) {
        String oldAssemblyDesc = this.assemblyDesc;
        this.assemblyDesc = assemblyDesc;
        propertyChangeSupport.firePropertyChange("assemblyDesc", oldAssemblyDesc, assemblyDesc);
    }

    public String getAssemblyDesc() {
        return assemblyDesc;
    }

    public void setSchStartDate(String schStartDate) {
        String oldSchStartDate = this.schStartDate;
        this.schStartDate = schStartDate;
        propertyChangeSupport.firePropertyChange("schStartDate", oldSchStartDate, schStartDate);
    }

    public String getSchStartDate() {
        return schStartDate;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
