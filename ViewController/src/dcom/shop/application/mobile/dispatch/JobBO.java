package dcom.shop.application.mobile.dispatch;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class JobBO implements Comparable{
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public JobBO() {
        super();
    }
    private String assembly;
    private String departmentId;
    private String jobId;
    private String jobNumber;
    private String jobOps;
    private String jobStatus;
    private String organizationId;
    private String readyStatus;


    public void setAssembly(String assembly) {
        String oldAssembly = this.assembly;
        this.assembly = assembly;
        propertyChangeSupport.firePropertyChange("assembly", oldAssembly, assembly);
    }

    public String getAssembly() {
        return assembly;
    }

    public void setDepartmentId(String departmentId) {
        String oldDepartmentId = this.departmentId;
        this.departmentId = departmentId;
        propertyChangeSupport.firePropertyChange("departmentId", oldDepartmentId, departmentId);
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setJobId(String jobId) {
        String oldJobId = this.jobId;
        this.jobId = jobId;
        propertyChangeSupport.firePropertyChange("jobId", oldJobId, jobId);
    }

    public String getJobId() {
        return jobId;
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

    public void setJobStatus(String jobStatus) {
        String oldJobStatus = this.jobStatus;
        this.jobStatus = jobStatus;
        propertyChangeSupport.firePropertyChange("jobStatus", oldJobStatus, jobStatus);
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setOrganizationId(String organizationId) {
        String oldOrganizationId = this.organizationId;
        this.organizationId = organizationId;
        propertyChangeSupport.firePropertyChange("organizationId", oldOrganizationId, organizationId);
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setReadyStatus(String readyStatus) {
        String oldReadyStatus = this.readyStatus;
        this.readyStatus = readyStatus;
        propertyChangeSupport.firePropertyChange("readyStatus", oldReadyStatus, readyStatus);
    }

    public String getReadyStatus() {
        return readyStatus;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }

    @Override
    public int compareTo(Object o) {
        // TODO Implement this method
        return 0;
    }
}
