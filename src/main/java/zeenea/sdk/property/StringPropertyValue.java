package zeenea.sdk.property;

public class StringPropertyValue implements PropertyValue {

    private final String value;

    public StringPropertyValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
