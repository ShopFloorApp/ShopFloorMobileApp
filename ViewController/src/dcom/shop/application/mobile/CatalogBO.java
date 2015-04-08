package dcom.shop.application.mobile;

import java.util.HashMap;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class CatalogBO {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public CatalogBO() {
        super();
    }

    public void setBOClassRow(HashMap hashMap) {
        this.setCatalog((String) hashMap.get("catalog"));
    }

    public HashMap getBOClassRow(CatalogBO catalog) {
        HashMap map = new HashMap();
        map.put("catalog", catalog.getCatalog());
        return map;
    }

    public void setCatalog(String Catalog) {
        String oldCatalog = this.Catalog;
        this.Catalog = Catalog;
        propertyChangeSupport.firePropertyChange("Catalog", oldCatalog, Catalog);
    }

    public String getCatalog() {
        return Catalog;
    }
    private String Catalog;

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
