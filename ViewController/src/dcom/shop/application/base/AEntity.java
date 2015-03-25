package dcom.shop.application.base;


public class AEntity {
    public AEntity() {
        super();
    }

    protected String getAttributeValue(String value) {
        if(value.contains("@xsi")){
            return "";
        }
        return value;
    }
}
