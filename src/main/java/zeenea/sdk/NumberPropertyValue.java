package zeenea.sdk;

import java.math.BigDecimal;

public class NumberPropertyValue implements PropertyValue {

    private final BigDecimal value;

    public NumberPropertyValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return this.value;
    }

}
