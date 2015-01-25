package dcom.shop.application.mobile;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class ConcurrentProgramBO implements Comparable {
    private String APPLICATION_SHORT_NAME;
    private String PROGRAM_NAME;
    private String PROGRAM_SHORT_NAME;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public ConcurrentProgramBO() {
        super();
    }

    public void setAPPLICATION_SHORT_NAME(String APPLICATION_SHORT_NAME) {
        String oldAPPLICATION_SHORT_NAME = this.APPLICATION_SHORT_NAME;
        this.APPLICATION_SHORT_NAME = APPLICATION_SHORT_NAME;
        propertyChangeSupport.firePropertyChange("APPLICATION_SHORT_NAME", oldAPPLICATION_SHORT_NAME,
                                                 APPLICATION_SHORT_NAME);
    }

    public String getAPPLICATION_SHORT_NAME() {
        return APPLICATION_SHORT_NAME;
    }

    public void setPROGRAM_NAME(String PROGRAM_NAME) {
        String oldPROGRAM_NAME = this.PROGRAM_NAME;
        this.PROGRAM_NAME = PROGRAM_NAME;
        propertyChangeSupport.firePropertyChange("PROGRAM_NAME", oldPROGRAM_NAME, PROGRAM_NAME);
    }

    public String getPROGRAM_NAME() {
        return PROGRAM_NAME;
    }

    public void setPROGRAM_SHORT_NAME(String PROGRAM_SHORT_NAME) {
        String oldPROGRAM_SHORT_NAME = this.PROGRAM_SHORT_NAME;
        this.PROGRAM_SHORT_NAME = PROGRAM_SHORT_NAME;
        propertyChangeSupport.firePropertyChange("PROGRAM_SHORT_NAME", oldPROGRAM_SHORT_NAME, PROGRAM_SHORT_NAME);
    }

    public String getPROGRAM_SHORT_NAME() {
        return PROGRAM_SHORT_NAME;
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
