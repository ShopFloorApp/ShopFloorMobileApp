package dcom.shop.application.mobile.dispatch;

import java.math.BigDecimal;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class JobPropertiesBO {
    private String jobNumber;
    private String jobOps;
    private String jobDesc;
    private String jobStatus;
    private String readyStatus;
    private String assembly;
    private String assemblyDesc;
    private String jobStartDate;
    private String schStartDate;
    private String schComplDate;
    private String dueDate;
    private BigDecimal startQty;
    private BigDecimal jobQty;
    private BigDecimal priorQty;
    private String uom;
    private String unitNumber;
    private String nextDept;
    private BigDecimal buildSeq;
    private String scheduleGrp;
    private BigDecimal line;
    private String project;
    private String tasks;
    private String compSubInv;
    private String compLocator;
    private String attrib;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);


    public JobPropertiesBO() {
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

    public void setJobStartDate(String jobStartDate) {
        String oldJobStartDate = this.jobStartDate;
        this.jobStartDate = jobStartDate;
        propertyChangeSupport.firePropertyChange("jobStartDate", oldJobStartDate, jobStartDate);
    }

    public String getJobStartDate() {
        return jobStartDate;
    }

    public void setSchStartDate(String schStartDate) {
        String oldSchStartDate = this.schStartDate;
        this.schStartDate = schStartDate;
        propertyChangeSupport.firePropertyChange("schStartDate", oldSchStartDate, schStartDate);
    }

    public String getSchStartDate() {
        return schStartDate;
    }

    public void setSchComplDate(String schComplDate) {
        String oldSchComplDate = this.schComplDate;
        this.schComplDate = schComplDate;
        propertyChangeSupport.firePropertyChange("schComplDate", oldSchComplDate, schComplDate);
    }

    public String getSchComplDate() {
        return schComplDate;
    }

    public void setDueDate(String dueDate) {
        String oldDueDate = this.dueDate;
        this.dueDate = dueDate;
        propertyChangeSupport.firePropertyChange("dueDate", oldDueDate, dueDate);
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setStartQty(BigDecimal startQty) {
        BigDecimal oldStartQty = this.startQty;
        this.startQty = startQty;
        propertyChangeSupport.firePropertyChange("startQty", oldStartQty, startQty);
    }

    public BigDecimal getStartQty() {
        return startQty;
    }

    public void setJobQty(BigDecimal jobQty) {
        BigDecimal oldJobQty = this.jobQty;
        this.jobQty = jobQty;
        propertyChangeSupport.firePropertyChange("jobQty", oldJobQty, jobQty);
    }

    public BigDecimal getJobQty() {
        return jobQty;
    }

    public void setPriorQty(BigDecimal priorQty) {
        BigDecimal oldPriorQty = this.priorQty;
        this.priorQty = priorQty;
        propertyChangeSupport.firePropertyChange("priorQty", oldPriorQty, priorQty);
    }

    public BigDecimal getPriorQty() {
        return priorQty;
    }

    public void setUom(String uom) {
        String oldUom = this.uom;
        this.uom = uom;
        propertyChangeSupport.firePropertyChange("uom", oldUom, uom);
    }

    public String getUom() {
        return uom;
    }

    public void setUnitNumber(String unitNumber) {
        String oldUnitNumber = this.unitNumber;
        this.unitNumber = unitNumber;
        propertyChangeSupport.firePropertyChange("unitNumber", oldUnitNumber, unitNumber);
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setNextDept(String nextDept) {
        String oldNextDept = this.nextDept;
        this.nextDept = nextDept;
        propertyChangeSupport.firePropertyChange("nextDept", oldNextDept, nextDept);
    }

    public String getNextDept() {
        return nextDept;
    }

    public void setBuildSeq(BigDecimal buildSeq) {
        BigDecimal oldBuildSeq = this.buildSeq;
        this.buildSeq = buildSeq;
        propertyChangeSupport.firePropertyChange("buildSeq", oldBuildSeq, buildSeq);
    }

    public BigDecimal getBuildSeq() {
        return buildSeq;
    }

    public void setScheduleGrp(String scheduleGrp) {
        String oldScheduleGrp = this.scheduleGrp;
        this.scheduleGrp = scheduleGrp;
        propertyChangeSupport.firePropertyChange("scheduleGrp", oldScheduleGrp, scheduleGrp);
    }

    public String getScheduleGrp() {
        return scheduleGrp;
    }

    public void setLine(BigDecimal line) {
        BigDecimal oldLine = this.line;
        this.line = line;
        propertyChangeSupport.firePropertyChange("line", oldLine, line);
    }

    public BigDecimal getLine() {
        return line;
    }

    public void setProject(String project) {
        String oldProject = this.project;
        this.project = project;
        propertyChangeSupport.firePropertyChange("project", oldProject, project);
    }

    public String getProject() {
        return project;
    }

    public void setTasks(String tasks) {
        String oldTasks = this.tasks;
        this.tasks = tasks;
        propertyChangeSupport.firePropertyChange("tasks", oldTasks, tasks);
    }

    public String getTasks() {
        return tasks;
    }

    public void setCompSubInv(String compSubInv) {
        String oldCompSubInv = this.compSubInv;
        this.compSubInv = compSubInv;
        propertyChangeSupport.firePropertyChange("compSubInv", oldCompSubInv, compSubInv);
    }

    public String getCompSubInv() {
        return compSubInv;
    }

    public void setCompLocator(String compLocator) {
        String oldCompLocator = this.compLocator;
        this.compLocator = compLocator;
        propertyChangeSupport.firePropertyChange("compLocator", oldCompLocator, compLocator);
    }

    public String getCompLocator() {
        return compLocator;
    }

    public void setAttrib(String attrib) {
        String oldAttrib = this.attrib;
        this.attrib = attrib;
        propertyChangeSupport.firePropertyChange("attrib", oldAttrib, attrib);
    }

    public String getAttrib() {
        return attrib;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
