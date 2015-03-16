package dcom.shop.application.base;

import java.math.BigDecimal;

public class AViewObject {

    protected BigDecimal getAttributeValue(Object obj) {
        try {
            BigDecimal value = new BigDecimal(obj.toString());
            return value;
        } catch (NumberFormatException nex) {
            return null;
        }
    }
}
