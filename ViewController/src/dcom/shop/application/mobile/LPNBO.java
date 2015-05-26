package dcom.shop.application.mobile;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class LPNBO {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public LPNBO() {
        super();
    }
    private String Lpn;
    private String LpnContext;
    private String WeightUom;
    private String Subinv;
    private String Locator;
    private float GrossWeight;
    private String VolumeUom;

    public void setLpn(String Lpn) {
        String oldLpn = this.Lpn;
        this.Lpn = Lpn;
        propertyChangeSupport.firePropertyChange("Lpn", oldLpn, Lpn);
    }

    public String getLpn() {
        return Lpn;
    }

    public void setLpnContext(String LpnContext) {
        String oldLpnContext = this.LpnContext;
        this.LpnContext = LpnContext;
        propertyChangeSupport.firePropertyChange("LpnContext", oldLpnContext, LpnContext);
    }

    public String getLpnContext() {
        return LpnContext;
    }

    public void setWeightUom(String WeightUom) {
        String oldWeightUom = this.WeightUom;
        this.WeightUom = WeightUom;
        propertyChangeSupport.firePropertyChange("WeightUom", oldWeightUom, WeightUom);
    }

    public String getWeightUom() {
        return WeightUom;
    }

    public void setSubinv(String Subinv) {
        String oldSubinv = this.Subinv;
        this.Subinv = Subinv;
        propertyChangeSupport.firePropertyChange("Subinv", oldSubinv, Subinv);
    }

    public String getSubinv() {
        return Subinv;
    }

    public void setLocator(String Locator) {
        String oldLocator = this.Locator;
        this.Locator = Locator;
        propertyChangeSupport.firePropertyChange("Locator", oldLocator, Locator);
    }

    public String getLocator() {
        return Locator;
    }

    public void setGrossWeight(float GrossWeight) {
        float oldGrossWeight = this.GrossWeight;
        this.GrossWeight = GrossWeight;
        propertyChangeSupport.firePropertyChange("GrossWeight", oldGrossWeight, GrossWeight);
    }

    public float getGrossWeight() {
        return GrossWeight;
    }

    public void setVolumeUom(String VolumeUom) {
        String oldVolumeUom = this.VolumeUom;
        this.VolumeUom = VolumeUom;
        propertyChangeSupport.firePropertyChange("VolumeUom", oldVolumeUom, VolumeUom);
    }

    public String getVolumeUom() {
        return VolumeUom;
    }

    public void setVolume(float Volume) {
        float oldVolume = this.Volume;
        this.Volume = Volume;
        propertyChangeSupport.firePropertyChange("Volume", oldVolume, Volume);
    }

    public float getVolume() {
        return Volume;
    }

    public void setParentLpn(String ParentLpn) {
        String oldParentLpn = this.ParentLpn;
        this.ParentLpn = ParentLpn;
        propertyChangeSupport.firePropertyChange("ParentLpn", oldParentLpn, ParentLpn);
    }

    public String getParentLpn() {
        return ParentLpn;
    }

    public void setOutermostLpn(String OutermostLpn) {
        String oldOutermostLpn = this.OutermostLpn;
        this.OutermostLpn = OutermostLpn;
        propertyChangeSupport.firePropertyChange("OutermostLpn", oldOutermostLpn, OutermostLpn);
    }

    public String getOutermostLpn() {
        return OutermostLpn;
    }

    public void setDeliveryId(int DeliveryId) {
        int oldDeliveryId = this.DeliveryId;
        this.DeliveryId = DeliveryId;
        propertyChangeSupport.firePropertyChange("DeliveryId", oldDeliveryId, DeliveryId);
    }

    public int getDeliveryId() {
        return DeliveryId;
    }

    public void setLoadSeq(String LoadSeq) {
        String oldLoadSeq = this.LoadSeq;
        this.LoadSeq = LoadSeq;
        propertyChangeSupport.firePropertyChange("LoadSeq", oldLoadSeq, LoadSeq);
    }

    public String getLoadSeq() {
        return LoadSeq;
    }
    private float Volume;
    private String ParentLpn;
    private String OutermostLpn;
    private int DeliveryId;
    private String LoadSeq;


    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
