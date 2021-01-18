package zeenea.sdk.metadata;

public class StringMetadataValue implements MetadataValue {

    private final String value;

    public StringMetadataValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
