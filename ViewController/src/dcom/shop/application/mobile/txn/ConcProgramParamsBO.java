package dcom.shop.application.mobile.txn;

import dcom.shop.Request.bean.RequestUtilBean;
import dcom.shop.application.dc.txn.ConcurrentProgramDC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.javax.faces.model.SelectItem;

public class ConcProgramParamsBO {
    private String seqNum;
    private String paramName;
    private String required;
    private String displayed;
    private String isLov;
    private String defaultType;
    private String defaultValue;
    private String paramType;
    private String valueSetName;
    private String refParam1;
    private String refParam2;
    private String refParam3;
    private String refParam4;
    private String refParam5;
    private String paramValue;
    private String paramDispValue;
    private String itemType;
    public static HashMap valueMap = new HashMap();
    public static HashMap valueDispMap = new HashMap();
    

    public void setItemType(String itemType) {
        String oldItemType = this.itemType;
        this.itemType = itemType;
        propertyChangeSupport.firePropertyChange("itemType", oldItemType, itemType);
    }

    public String getItemType() {
        if(this.valueSetName.equalsIgnoreCase("FND_DATE4_REQUIRED_STANDARD") ||this.valueSetName.equalsIgnoreCase("FND_DATE4_STANDARD")||this.valueSetName.equalsIgnoreCase("FND_DATE_REQUIRED_STANDARD")
            ||this.valueSetName.equalsIgnoreCase("FND_STANDARD_DATE")||this.valueSetName.equalsIgnoreCase("FND_STANDARD_DATE_REQUIRED")
           ||this.valueSetName.equalsIgnoreCase("FND_STANDARD_DATETIME")||this.valueSetName.equalsIgnoreCase("FND_STANDARD_DATE_TIME")||this.valueSetName.equalsIgnoreCase("WIP_SRS_DATES_OPT_STANDARD")){
               itemType="DATE";
        }else if(this.isLov.equalsIgnoreCase("Y")){
            itemType="LOV";
        }else{
            itemType="TEXT";
        }
        return itemType;
    }
    private SelectItem[] paramList;
    private static int mycount=0;

  
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public ConcProgramParamsBO() {
        super();
        String seqNo = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.seq}"));   
        String selectedDispValue = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.selectedDispParamsLovVal}"));                                                                                                        
        String selectedValue = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.selectedParamsLovVal}"));
        if(seqNo!=null){
            valueMap.put(seqNo, selectedValue);
            valueDispMap.put(seqNo, selectedDispValue);
        }
        
    }

    public void setParamDispValue(String paramDispValue) {
        String oldParamDispValue = this.paramDispValue;
        this.paramDispValue = paramDispValue;
        valueDispMap.put(this.getSeqNum(), paramDispValue);
        propertyChangeSupport.firePropertyChange("paramDispValue", oldParamDispValue, paramDispValue);
    }

    public String getParamDispValue() {
            paramDispValue = (String) valueDispMap.get(this.getSeqNum());
        return paramDispValue;
    }

    public void setParamValue(String paramValue) {
        String oldParamValue = this.paramValue;
        this.paramValue = paramValue;
        propertyChangeSupport.firePropertyChange("paramValue", oldParamValue, paramValue);
    }

    public String getParamValue() {
        
            paramValue = (String) valueMap.get(this.getSeqNum());
        return paramValue;
    }

    public void setParamList(SelectItem[] paramList) {
        SelectItem[] oldParamList = this.paramList;
        this.paramList = paramList;
        propertyChangeSupport.firePropertyChange("paramList", oldParamList, paramList);
    }

    public SelectItem[] getParamList() {
        ArrayList s_array=new ArrayList();
//        if(this.isLov.equalsIgnoreCase("Y")){
//        RequestUtilBean requestUtil=new RequestUtilBean();
//        String shortName = (String) (AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.shortName}")==null?"":AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.shortName}"));
//        List lovCollection=requestUtil.getConcProgramParamLov(shortName, this.getSeqNum(), this.getValueSetName());        
//        for(int i=0;i<lovCollection.size();i++){
//            ConcProgramParamLovBO lovRow = (ConcProgramParamLovBO) lovCollection.get(i);
//            SelectItem s=new SelectItem();
//            s.setLabel(lovRow.getValue());
//            s.setValue(lovRow.getRef());
//            s_array.add(s);
//        }
//        }
        return (SelectItem[]) s_array.toArray(new SelectItem[s_array.size()]);
    }


    public void setSeqNum(String seqNum) {
        String oldSeqNum = this.seqNum;
        this.seqNum = seqNum;
        propertyChangeSupport.firePropertyChange("seqNum", oldSeqNum, seqNum);
    }

    public String getSeqNum() {
        return seqNum;
    }

    public void setParamName(String paramName) {
        String oldParamName = this.paramName;
        this.paramName = paramName;
        propertyChangeSupport.firePropertyChange("paramName", oldParamName, paramName);
    }

    public String getParamName() {
        return paramName;
    }

    public void setRequired(String required) {
        String oldRequired = this.required;
        this.required = required;
        propertyChangeSupport.firePropertyChange("required", oldRequired, required);
    }

    public String getRequired() {
        return required;
    }

    public void setDisplayed(String displayed) {
        String oldDisplayed = this.displayed;
        this.displayed = displayed;
        propertyChangeSupport.firePropertyChange("displayed", oldDisplayed, displayed);
    }

    public String getDisplayed() {
        return displayed;
    }

    public void setIsLov(String isLov) {
        String oldIsLov = this.isLov;
        this.isLov = isLov;
        propertyChangeSupport.firePropertyChange("isLov", oldIsLov, isLov);
    }

    public String getIsLov() {
        return isLov;
    }

    public void setDefaultType(String defaultType) {
        String oldDefaultType = this.defaultType;
        this.defaultType = defaultType;
        propertyChangeSupport.firePropertyChange("defaultType", oldDefaultType, defaultType);
    }

    public String getDefaultType() {
        return defaultType;
    }

    public void setDefaultValue(String defaultValue) {
        String oldDefaultValue = this.defaultValue;
        this.defaultValue = defaultValue;
        propertyChangeSupport.firePropertyChange("defaultValue", oldDefaultValue, defaultValue);
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setParamType(String paramType) {
        String oldParamType = this.paramType;
        this.paramType = paramType;
        propertyChangeSupport.firePropertyChange("paramType", oldParamType, paramType);
    }

    public String getParamType() {
        return paramType;
    }

    public void setValueSetName(String valueSetName) {
        String oldValueSetName = this.valueSetName;
        this.valueSetName = valueSetName;
        propertyChangeSupport.firePropertyChange("valueSetName", oldValueSetName, valueSetName);
    }

    public String getValueSetName() {
        return valueSetName;
    }

    public void setRefParam1(String refParam1) {
        String oldRefParam1 = this.refParam1;
        this.refParam1 = refParam1;
        propertyChangeSupport.firePropertyChange("refParam1", oldRefParam1, refParam1);
    }

    public String getRefParam1() {
        return refParam1;
    }

    public void setRefParam2(String refParam2) {
        String oldRefParam2 = this.refParam2;
        this.refParam2 = refParam2;
        propertyChangeSupport.firePropertyChange("refParam2", oldRefParam2, refParam2);
    }

    public String getRefParam2() {
        return refParam2;
    }

    public void setRefParam3(String refParam3) {
        String oldRefParam3 = this.refParam3;
        this.refParam3 = refParam3;
        propertyChangeSupport.firePropertyChange("refParam3", oldRefParam3, refParam3);
    }

    public String getRefParam3() {
        return refParam3;
    }

    public void setRefParam4(String refParam4) {
        String oldRefParam4 = this.refParam4;
        this.refParam4 = refParam4;
        propertyChangeSupport.firePropertyChange("refParam4", oldRefParam4, refParam4);
    }

    public String getRefParam4() {
        return refParam4;
    }

    public void setRefParam5(String refParam5) {
        String oldRefParam5 = this.refParam5;
        this.refParam5 = refParam5;
        propertyChangeSupport.firePropertyChange("refParam5", oldRefParam5, refParam5);
    }

    public String getRefParam5() {
        return refParam5;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
