package dcom.shop.application.mobile;

import java.util.HashMap;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.util.GenericVirtualType;

public class KanbanCardBO implements Comparable {
    private String Whse;
    private String KanbanCardNum;
    private String CardStatus;
    private GenericVirtualType Attributes;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public KanbanCardBO() {
        super();
    }
    
    public void setBOClassRow(HashMap hashMap) {
        this.setWhse((String) hashMap.get("whse"));
        this.setKanbanCardNum((String) hashMap.get("kanbancardnum"));
        this.setCardStatus((String) hashMap.get("cardstatus"));
    }

    public HashMap getBOClassRow(KanbanCardBO kanbanCard) {
        HashMap map = new HashMap();
        map.put("whse", kanbanCard.getWhse());
        map.put("kanbancardnum", kanbanCard.getKanbanCardNum());
        map.put("cardstatus", kanbanCard.getCardStatus());
        return map;
    }

    public void setWhse(String Whse) {
        String oldWhse = this.Whse;
        this.Whse = Whse;
        propertyChangeSupport.firePropertyChange("Whse", oldWhse, Whse);
    }

    public String getWhse() {
        return Whse;
    }

    public void setKanbanCardNum(String KanbanCardNum) {
        String oldKanbanCardNum = this.KanbanCardNum;
        this.KanbanCardNum = KanbanCardNum;
        propertyChangeSupport.firePropertyChange("KanbanCardNum", oldKanbanCardNum, KanbanCardNum);
    }

    public String getKanbanCardNum() {
        return KanbanCardNum;
    }

    public void setCardStatus(String CardStatus) {
        String oldCardStatus = this.CardStatus;
        this.CardStatus = CardStatus;
        propertyChangeSupport.firePropertyChange("CardStatus", oldCardStatus, CardStatus);
    }

    public String getCardStatus() {
        return CardStatus;
    }

    public void setAttributes(GenericVirtualType Attributes) {
        GenericVirtualType oldAttributes = this.Attributes;
        this.Attributes = Attributes;
        propertyChangeSupport.firePropertyChange("Attributes", oldAttributes, Attributes);
    }

    public GenericVirtualType getAttributes() {
        return Attributes;
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
