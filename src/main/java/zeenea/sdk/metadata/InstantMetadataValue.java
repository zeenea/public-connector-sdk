package zeenea.sdk.metadata;

import java.time.Instant;

public class InstantMetadataValue implements MetadataValue {

    private final Instant value;

    public InstantMetadataValue(Instant value) {
        this.value = value;
    }

    public Instant getValue() {
        return this.value;
    }

}
