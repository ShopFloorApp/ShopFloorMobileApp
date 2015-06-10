package dcom.shop.mobile.barcode;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class BarcodeBean {
    private String barcodeResult = "No barcode scanned yet";
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public BarcodeBean() {
        super();
    }

    public void scanBarcode(ActionEvent event) {
        // Our AMX page includes a small JavaScript function which wraps the Cordova
        // barcode scanning function in a manner that makes it more suitable for invocation
        // from Java bean code. This is the function we are invoking below:
        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                  "scanBarcodeFromJavaBean", new Object[] { });
    }

    public void setBarcodeResult(String barcodeResult) {
        // When the barcode scanning is complete, the success callback of the Cordova
        // barcode scanning JavaScript function will invoke this method indirectly, by
        // evaluating the EL expression #{viewScope.BarcodeBean.barcodeResult}. It will
        // pass us the barcode that was scanned (or an error message).
        String oldBarcodeResult = "Bean:setBarcodeResult" + this.barcodeResult;
        this.barcodeResult = barcodeResult;
        String barCodeField = (String) AdfmfJavaUtilities.getELValue("#{pageFlowScope.barCodeField}");
        if ("Item".equals(barCodeField)) {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchKeyword}", barcodeResult);
        } else if ("LPN".equals(barCodeField)) {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchLpnKeyword}", barcodeResult);
        } else if ("TOLPN".equals(barCodeField)) {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchToLpnKeyword}", barcodeResult);

        } else if ("Dock".equals(barCodeField)) {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.dockDoor}", barcodeResult);
        } else if ("Document".equals(barCodeField)) {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.documnetNumber}", barcodeResult);
        } else if ("Job".equals(barCodeField)) {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchJobKeyword}", barcodeResult);
        } else if ("SONumber".equals(barCodeField)) {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.salesOrderNumber}", barcodeResult);
        }else if ("MONumber".equals(barCodeField)) {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.moveOrderNumber}", barcodeResult);
        }else if ("DeliveryNumber".equals(barCodeField)) {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.deliveryNumber}", barcodeResult);
        }else if ("TaskId".equals(barCodeField)) {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.taskId}", barcodeResult);
        }else if ("ShipMethod".equals(barCodeField)) {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.SHIPMETHOD}", barcodeResult);
        }
        
        propertyChangeSupport.firePropertyChange("barcodeResult", oldBarcodeResult, barcodeResult);
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }

    public String getBarcodeResult() {
        return barcodeResult;
    }
}
