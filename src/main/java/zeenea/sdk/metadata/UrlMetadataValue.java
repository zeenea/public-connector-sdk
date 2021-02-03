package zeenea.sdk.metadata;

import java.net.URI;
import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;

public class UrlMetadataValue implements MetadataValue {

    private final URI value;
    private final String label;

    public UrlMetadataValue(URI value) {
        this.value = value;
        this.label = null;
    }

    public UrlMetadataValue(URI value, String label) {
        this.value = value;
        this.label = label;
    }

    public URI getValue() {
        return this.value;
    }

    public Optional<String> getLabel() {
        return Optional.ofNullable(this.label);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UrlMetadataValue that = (UrlMetadataValue) o;
        return Objects.equals(value, that.value) && Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, label);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UrlMetadataValue.class.getSimpleName() + "[", "]")
                .add("value=" + value)
                .add("label='" + label + "'")
                .toString();
    }
}
