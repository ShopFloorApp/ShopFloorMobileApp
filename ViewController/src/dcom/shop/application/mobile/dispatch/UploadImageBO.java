package dcom.shop.application.mobile.dispatch;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class UploadImageBO {
    String fileType;
    String imageDescription;
    String imageDataB64;
    String fileName;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public UploadImageBO() {
        super();
    }
    
    public UploadImageBO(String fileType, String imageDescription, String imageDataB64,String fileName) {
        super();
        this.fileType = fileType;
        this.imageDescription = imageDescription;
        this.imageDataB64 = imageDataB64;
        this.fileName=fileName;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }


    public String getFileType() {
        return fileType;
    }

    public void setImageDescription(String imageDescription) {
        String oldImageDescription = this.imageDescription;
        this.imageDescription = imageDescription;
        propertyChangeSupport.firePropertyChange("imageDescription", oldImageDescription, imageDescription);
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDataB64(String imageDataB64) {
        String oldImageDataB64 = this.imageDataB64;
        this.imageDataB64 = imageDataB64;
        propertyChangeSupport.firePropertyChange("imageDataB64", oldImageDataB64, imageDataB64);
    }

    public String getImageDataB64() {
        return imageDataB64;
    }

    public void setFileName(String fileName) {
        String oldFileName = this.fileName;
        this.fileName = fileName;
        propertyChangeSupport.firePropertyChange("fileName", oldFileName, fileName);
    }

    public String getFileName() {
        return fileName;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
