package zeenea.sdk.metadata;

import java.util.Objects;
import java.util.StringJoiner;

public class StringMetadataValue implements MetadataValue {

    private final String value;

    public StringMetadataValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringMetadataValue that = (StringMetadataValue) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", StringMetadataValue.class.getSimpleName() + "[", "]")
                .add("value='" + value + "'")
                .toString();
    }
}
