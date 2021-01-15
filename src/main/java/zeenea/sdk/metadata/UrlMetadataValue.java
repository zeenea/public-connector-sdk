package zeenea.sdk.metadata;

import java.net.URI;
import java.util.Optional;

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
}
