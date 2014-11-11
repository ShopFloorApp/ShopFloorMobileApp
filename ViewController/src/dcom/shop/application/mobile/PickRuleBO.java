package dcom.shop.application.mobile;

import java.util.HashMap;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.util.GenericVirtualType;

public class PickRuleBO implements Comparable {
    private String Whse;
    private String RuleName;
    private String DocSetName;
    private String ReleaseSeqName;
    private GenericVirtualType Attributes;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public PickRuleBO() {
        super();
    }
    
    public void setBOClassRow(HashMap hashMap) {
        this.setWhse((String) hashMap.get("whse"));
        this.setRuleName((String) hashMap.get("rulename"));
        this.setDocSetName((String) hashMap.get("docsetname"));
        this.setReleaseSeqName((String) hashMap.get("releaseseqname"));
    }

    public HashMap getBOClassRow(PickRuleBO pickRule) {
        HashMap map = new HashMap();
        map.put("whse", pickRule.getWhse());
        map.put("rulename", pickRule.getRuleName());
        map.put("docsetname", pickRule.getDocSetName());
        map.put("releaseseqname", pickRule.getReleaseSeqName());
        return map;
    }

    public int compareTo(Object o) {
        // TODO Implement this method
        return 0;
    }

    public void setWhse(String Whse) {
        String oldWhse = this.Whse;
        this.Whse = Whse;
        propertyChangeSupport.firePropertyChange("Whse", oldWhse, Whse);
    }

    public String getWhse() {
        return Whse;
    }

    public void setRuleName(String RuleName) {
        String oldRuleName = this.RuleName;
        this.RuleName = RuleName;
        propertyChangeSupport.firePropertyChange("RuleName", oldRuleName, RuleName);
    }

    public String getRuleName() {
        return RuleName;
    }

    public void setDocSetName(String DocSetName) {
        String oldDocSetName = this.DocSetName;
        this.DocSetName = DocSetName;
        propertyChangeSupport.firePropertyChange("DocSetName", oldDocSetName, DocSetName);
    }

    public String getDocSetName() {
        return DocSetName;
    }

    public void setReleaseSeqName(String ReleaseSeqName) {
        String oldReleaseSeqName = this.ReleaseSeqName;
        this.ReleaseSeqName = ReleaseSeqName;
        propertyChangeSupport.firePropertyChange("ReleaseSeqName", oldReleaseSeqName, ReleaseSeqName);
    }

    public String getReleaseSeqName() {
        return ReleaseSeqName;
    }

    public void setAttributes(GenericVirtualType Attributes) {
        GenericVirtualType oldAttributes = this.Attributes;
        this.Attributes = Attributes;
        propertyChangeSupport.firePropertyChange("Attributes", oldAttributes, Attributes);
    }

    public GenericVirtualType getAttributes() {
        return Attributes;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
