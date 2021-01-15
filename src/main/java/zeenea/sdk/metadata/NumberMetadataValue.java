package zeenea.sdk.metadata;

import java.math.BigDecimal;

public class NumberMetadataValue implements MetadataValue {

    private final BigDecimal value;

    public NumberMetadataValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return this.value;
    }

}
