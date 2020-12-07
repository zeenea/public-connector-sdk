package zeenea.sdk.property;

import java.net.URI;
import java.util.Optional;

public class UrlPropertyValue implements PropertyValue {
    private final URI value;
    private final Optional<String> label;

    public UrlPropertyValue(URI value) {
        this.value = value;
        this.label = Optional.empty();
    }

    public UrlPropertyValue(URI value, String label) {
        this.value = value;
        this.label = Optional.of(label);
    }

    public URI getValue() {
        return this.value;
    }
    public Optional<String> getLabel() {
        return this.label;
    }
}
