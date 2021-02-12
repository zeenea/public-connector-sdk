package zeenea.sdk.metadata;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Sub-class of {@link MetadataValue} containing a value of type {@code BigDecimal}.
 *
 * @see MetadataValue
 * @see NumberMetadata
 * @since 1.0.0
 */
public class NumberMetadataValue implements MetadataValue {

    private final BigDecimal value;

    /**
     * Create a new instance of NumberMetadataValue, containing a value of type {@code BigDecimal}.
     *
     * @param value The metadata value
     */
    public NumberMetadataValue(BigDecimal value) {
        this.value = value;
    }

    /**
     * Get the {@code BigDecimal} value of the metadata.
     *
     * @return The {@code BigDecimal} value of the metadata
     */
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
