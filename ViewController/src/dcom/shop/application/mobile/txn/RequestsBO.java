package dcom.shop.application.mobile.txn;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class RequestsBO implements Comparable {
    private String requestId;
    private String progName;
    private String applName;
    private String phase;
    private String status;
    private String hrsTaken;
    private String minTaken;
    private String ssTaken;
    private String msTaken;
    private String submitDate;
    private String actualStart;
    private String actualCompl;
    private String submittedBy;
    private String respName;
    private String ofileType;
    private String logSize;
    private String outPutSize;
    private String colorStyle;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void setRequestId(String requestId) {
        String oldRequestId = this.requestId;
        this.requestId = requestId;
        propertyChangeSupport.firePropertyChange("requestId", oldRequestId, requestId);
    }

    public String getRequestId() {
        return requestId;
    }

    public void setColorStyle(String colorStyle) {
        String oldColorStyle = this.colorStyle;
        this.colorStyle = colorStyle;
        propertyChangeSupport.firePropertyChange("colorStyle", oldColorStyle, colorStyle);
    }

    public String getColorStyle() {
        String className=null;
        if(this.getPhase().equalsIgnoreCase("Pending")||this.getPhase().equalsIgnoreCase("Running")){
            className="time-btn-completed";
        }else if(this.getPhase().equalsIgnoreCase("Inactive")||this.getStatus().equalsIgnoreCase("Warning")){
            className="time-btn-warning";
        }else if(this.getStatus().equalsIgnoreCase("Error")){
            className="time-btn-error";
        }else{
            className="time-btn-warning";
        }
        return className;
    }

    public void setProgName(String progName) {
        String oldProgName = this.progName;
        this.progName = progName;
        propertyChangeSupport.firePropertyChange("progName", oldProgName, progName);
    }

    public String getProgName() {
        return progName;
    }

    public void setApplName(String applName) {
        String oldApplName = this.applName;
        this.applName = applName;
        propertyChangeSupport.firePropertyChange("applName", oldApplName, applName);
    }

    public String getApplName() {
        return applName;
    }

    public void setPhase(String phase) {
        String oldPhase = this.phase;
        this.phase = phase;
        propertyChangeSupport.firePropertyChange("phase", oldPhase, phase);
    }

    public String getPhase() {
        return phase;
    }

    public void setStatus(String status) {
        String oldStatus = this.status;
        this.status = status;
        propertyChangeSupport.firePropertyChange("status", oldStatus, status);
    }

    public String getStatus() {
        return status;
    }

    public void setSubmitDate(String submitDate) {
        String oldSubmitDate = this.submitDate;
        this.submitDate = submitDate;
        propertyChangeSupport.firePropertyChange("submitDate", oldSubmitDate, submitDate);
    }

    public String getSubmitDate() {
        return submitDate;
    }

    public void setActualStart(String actualStart) {
        String oldActualStart = this.actualStart;
        this.actualStart = actualStart;
        propertyChangeSupport.firePropertyChange("actualStart", oldActualStart, actualStart);
    }

    public String getActualStart() {
        return actualStart;
    }

    public void setActualCompl(String actualCompl) {
        String oldActualCompl = this.actualCompl;
        this.actualCompl = actualCompl;
        propertyChangeSupport.firePropertyChange("actualCompl", oldActualCompl, actualCompl);
    }

    public String getActualCompl() {
        return actualCompl;
    }

    public void setSubmittedBy(String submittedBy) {
        String oldSubmittedBy = this.submittedBy;
        this.submittedBy = submittedBy;
        propertyChangeSupport.firePropertyChange("submittedBy", oldSubmittedBy, submittedBy);
    }

    public String getSubmittedBy() {
        return submittedBy;
    }

    public void setRespName(String respName) {
        String oldRespName = this.respName;
        this.respName = respName;
        propertyChangeSupport.firePropertyChange("respName", oldRespName, respName);
    }

    public String getRespName() {
        return respName;
    }

    public void setOfileType(String ofileType) {
        String oldOfileType = this.ofileType;
        this.ofileType = ofileType;
        propertyChangeSupport.firePropertyChange("ofileType", oldOfileType, ofileType);
    }

    public String getOfileType() {
        return ofileType;
    }

    public void setLogSize(String logSize) {
        String oldLogSize = this.logSize;
        this.logSize = logSize;
        propertyChangeSupport.firePropertyChange("logSize", oldLogSize, logSize);
    }

    public String getLogSize() {
        return logSize;
    }

    public void setOutPutSize(String outPutSize) {
        String oldOutPutSize = this.outPutSize;
        this.outPutSize = outPutSize;
        propertyChangeSupport.firePropertyChange("outPutSize", oldOutPutSize, outPutSize);
    }

    public String getOutPutSize() {
        return outPutSize;
    }

    public void setPropertyChangeSupport(PropertyChangeSupport propertyChangeSupport) {
        PropertyChangeSupport oldPropertyChangeSupport = this.propertyChangeSupport;
        this.propertyChangeSupport = propertyChangeSupport;
        propertyChangeSupport.firePropertyChange("propertyChangeSupport", oldPropertyChangeSupport,
                                                 propertyChangeSupport);
    }

    public void setHrsTaken(String hrsTaken) {
        String oldHrsTaken = this.hrsTaken;
        this.hrsTaken = hrsTaken;
        propertyChangeSupport.firePropertyChange("hrsTaken", oldHrsTaken, hrsTaken);
    }

    public String getHrsTaken() {
        return hrsTaken;
    }

    public void setMinTaken(String minTaken) {
        String oldMinTaken = this.minTaken;
        this.minTaken = minTaken;
        propertyChangeSupport.firePropertyChange("minTaken", oldMinTaken, minTaken);
    }

    public String getMinTaken() {
        return minTaken;
    }

    public void setSsTaken(String ssTaken) {
        String oldSsTaken = this.ssTaken;
        this.ssTaken = ssTaken;
        propertyChangeSupport.firePropertyChange("ssTaken", oldSsTaken, ssTaken);
    }

    public String getSsTaken() {
        return ssTaken;
    }

    public void setMsTaken(String msTaken) {
        String oldMsTaken = this.msTaken;
        this.msTaken = msTaken;
        propertyChangeSupport.firePropertyChange("msTaken", oldMsTaken, msTaken);
    }

    public String getMsTaken() {
        return msTaken;
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }


    public int compareTo(Object o) {
        // TODO Implement this method
        return 0;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
