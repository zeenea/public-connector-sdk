package zeenea.sdk.metadata;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Sub-class of {@link MetadataValue} containing a value of type {@code String}.
 *
 * @see MetadataValue
 * @see StringMetadata
 * @since 1.0.0
 */
public class StringMetadataValue implements MetadataValue {

    private final String value;

    /**
     * Create a new instance of StringMetadataValue, containing a value of type {@code String}.
     *
     * @param value The metadata value
     */
    public StringMetadataValue(String value) {
        this.value = value;
    }

    /**
     * Get the {@code String} value of the metadata.
     *
     * @return The {@code String} value of the metadata
     */
    public String getValue() {
        return this.value;
    }

    /**
     * @hidden Common overrides are better off the Javadoc.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringMetadataValue that = (StringMetadataValue) o;
        return Objects.equals(value, that.value);
    }

    /**
     * @hidden Common overrides are better off the Javadoc.
     */
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    /**
     * @hidden Common overrides are better off the Javadoc.
     */
    @Override
    public String toString() {
        return new StringJoiner(", ", StringMetadataValue.class.getSimpleName() + "[", "]")
                .add("value='" + value + "'")
                .toString();
    }
}
