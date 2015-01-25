package dcom.shop.application.mobile.txn;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class RequestsBO implements Comparable {
    private String Name;
    private String OperatingUnit;
    private String RequestId;
    private String Parameters;
    private String Phase;
    private String DateSubmitted;
    private String DateStarted;
    private String CompletionText;
    private String Language;
    private String Status;
    private String Requestor;
    private String DateCompleted;
    private String TimeTakenHH;
    private String TimeTakenMI;
    private String TimeTakenSS;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public RequestsBO() {
        super();
    }


    public void setTimeTakenHH(String TimeTakenHH) {
        String oldTimeTakenHH = this.TimeTakenHH;
        this.TimeTakenHH = TimeTakenHH;
        propertyChangeSupport.firePropertyChange("TimeTakenHH", oldTimeTakenHH, TimeTakenHH);
    }

    public String getTimeTakenHH() {
        return TimeTakenHH;
    }

    public void setTimeTakenMI(String TimeTakenMI) {
        String oldTimeTakenMI = this.TimeTakenMI;
        this.TimeTakenMI = TimeTakenMI;
        propertyChangeSupport.firePropertyChange("TimeTakenMI", oldTimeTakenMI, TimeTakenMI);
    }

    public String getTimeTakenMI() {
        return TimeTakenMI;
    }

    public void setTimeTakenSS(String TimeTakenSS) {
        String oldTimeTakenSS = this.TimeTakenSS;
        this.TimeTakenSS = TimeTakenSS;
        propertyChangeSupport.firePropertyChange("TimeTakenSS", oldTimeTakenSS, TimeTakenSS);
    }

    public String getTimeTakenSS() {
        return TimeTakenSS;
    }

    public RequestsBO(String Name,String OperatingUnit,String RequestId,String Parameters,String Phase,String DateSubmitted
                      ,String DateStarted,String CompletionText,String Language,String Status,String Requestor,String DateCompleted){
                          this.Name=Name;
                          this.OperatingUnit=OperatingUnit;
                          this.RequestId=RequestId;
                          this.Parameters=Parameters;
                          this.Phase=Phase;
                          this.DateSubmitted=DateSubmitted;
                          this.DateStarted=DateStarted;
                          this.CompletionText=CompletionText;
                          this.Language=Language;
                          this.Status=Status;
                          this.Requestor=Requestor;
                          this.DateCompleted=DateCompleted;
                          this.TimeTakenHH="00";
                          this.TimeTakenMI="01";
                          this.TimeTakenSS="40";
                      }

    public void setName(String Name) {
        String oldName = this.Name;
        this.Name = Name;
        propertyChangeSupport.firePropertyChange("Name", oldName, Name);
    }

    public String getName() {
        return Name;
    }

    public void setOperatingUnit(String OperatingUnit) {
        String oldOperatingUnit = this.OperatingUnit;
        this.OperatingUnit = OperatingUnit;
        propertyChangeSupport.firePropertyChange("OperatingUnit", oldOperatingUnit, OperatingUnit);
    }

    public String getOperatingUnit() {
        return OperatingUnit;
    }

    public void setRequestId(String RequestId) {
        String oldRequestId = this.RequestId;
        this.RequestId = RequestId;
        propertyChangeSupport.firePropertyChange("RequestId", oldRequestId, RequestId);
    }

    public String getRequestId() {
        return RequestId;
    }

    public void setParameters(String Parameters) {
        String oldParameters = this.Parameters;
        this.Parameters = Parameters;
        propertyChangeSupport.firePropertyChange("Parameters", oldParameters, Parameters);
    }

    public String getParameters() {
        return Parameters;
    }

    public void setPhase(String Phase) {
        String oldPhase = this.Phase;
        this.Phase = Phase;
        propertyChangeSupport.firePropertyChange("Phase", oldPhase, Phase);
    }

    public String getPhase() {
        return Phase;
    }

    public void setDateSubmitted(String DateSubmitted) {
        String oldDateSubmitted = this.DateSubmitted;
        this.DateSubmitted = DateSubmitted;
        propertyChangeSupport.firePropertyChange("DateSubmitted", oldDateSubmitted, DateSubmitted);
    }

    public String getDateSubmitted() {
        return DateSubmitted;
    }

    public void setDateStarted(String DateStarted) {
        String oldDateStarted = this.DateStarted;
        this.DateStarted = DateStarted;
        propertyChangeSupport.firePropertyChange("DateStarted", oldDateStarted, DateStarted);
    }

    public String getDateStarted() {
        return DateStarted;
    }

    public void setCompletionText(String CompletionText) {
        String oldCompletionText = this.CompletionText;
        this.CompletionText = CompletionText;
        propertyChangeSupport.firePropertyChange("CompletionText", oldCompletionText, CompletionText);
    }

    public String getCompletionText() {
        return CompletionText;
    }

    public void setLanguage(String Language) {
        String oldLanguage = this.Language;
        this.Language = Language;
        propertyChangeSupport.firePropertyChange("Language", oldLanguage, Language);
    }

    public String getLanguage() {
        return Language;
    }

    public void setStatus(String Status) {
        String oldStatus = this.Status;
        this.Status = Status;
        propertyChangeSupport.firePropertyChange("Status", oldStatus, Status);
    }

    public String getStatus() {
        return Status;
    }

    public void setRequestor(String Requestor) {
        String oldRequestor = this.Requestor;
        this.Requestor = Requestor;
        propertyChangeSupport.firePropertyChange("Requestor", oldRequestor, Requestor);
    }

    public String getRequestor() {
        return Requestor;
    }

    public void setDateCompleted(String DateCompleted) {
        String oldDateCompleted = this.DateCompleted;
        this.DateCompleted = DateCompleted;
        propertyChangeSupport.firePropertyChange("DateCompleted", oldDateCompleted, DateCompleted);
    }

    public String getDateCompleted() {
        return DateCompleted;
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
