package zeenea.sdk.property;

import java.time.Instant;

public class InstantPropertyValue implements PropertyValue {

    private final Instant value;

    public InstantPropertyValue(Instant value) {
        this.value = value;
    }

    public Instant getValue() {
        return this.value;
    }

}
