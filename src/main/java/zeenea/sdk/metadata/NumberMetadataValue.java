package zeenea.sdk.metadata;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

public class NumberMetadataValue implements MetadataValue {

    private final BigDecimal value;

    public NumberMetadataValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberMetadataValue that = (NumberMetadataValue) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", NumberMetadataValue.class.getSimpleName() + "[", "]")
                .add("value=" + value)
                .toString();
    }
}
