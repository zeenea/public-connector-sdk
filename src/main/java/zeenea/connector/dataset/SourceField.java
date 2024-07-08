package zeenea.connector.dataset;

import java.util.List;
import java.util.Map;
import zeenea.connector.property.SourcePropertyValue;

public class SourceField {

  private final String name;
  private final SourceDataType dataType;
  private final String nativeType;
  private final int nativeIndex;
  private final List<String> keys;
  private final boolean nullable;
  private final boolean multivalued;
  private final String description;
  private final Map<String, SourcePropertyValue> metadata;

  public SourceField(SourceField.Builder<?, ?> builder) {
    this.name = builder.name;
    this.dataType = builder.dataType;
    this.nativeType = builder.nativeType;
    this.nativeIndex = builder.nativeIndex;
    this.keys = builder.keys;
    this.nullable = builder.nullable;
    this.multivalued = builder.multivalued;
    this.description = builder.description;
    this.metadata = builder.metadata;
  }

  public SourceDataType getDataType() {
    return dataType;
  }

  public String getDescription() {
    return description;
  }

  public List<String> getKeys() {
    return keys;
  }

  public Map<String, SourcePropertyValue> getMetadata() {
    return metadata;
  }

  public boolean isMultivalued() {
    return multivalued;
  }

  public String getName() {
    return name;
  }

  public int getNativeIndex() {
    return nativeIndex;
  }

  public String getNativeType() {
    return nativeType;
  }

  public boolean isNullable() {
    return nullable;
  }

  public static class Builder<T, SELF extends SourceField.Builder<T, ?>> {

    private String name;
    private SourceDataType dataType;
    private String nativeType;
    private int nativeIndex;
    private List<String> keys;
    private boolean nullable;
    private boolean multivalued;
    private String description;
    private Map<String, SourcePropertyValue> metadata;

    protected Builder() {}

    protected static void throwIfNull(String attributeName, Object attributeValue) {
      if (attributeValue == null)
        throw new NullPointerException("Attribute \"" + attributeName + "\" cannot be null");
    }

    public SELF name(String name) {
      this.name = name;
      return self();
    }

    public SELF dataType(SourceDataType dataType) {
      this.dataType = dataType;
      return self();
    }

    public SELF nativeType(String nativeType) {
      this.nativeType = nativeType;
      return self();
    }

    public SELF nativeIndex(int nativeIndex) {
      this.nativeIndex = nativeIndex;
      return self();
    }

    public SELF keys(List<String> keys) {
      this.keys = keys;
      return self();
    }

    public SELF nullable(boolean nullable) {
      this.nullable = nullable;
      return self();
    }

    public SELF multivalued(boolean multivalued) {
      this.multivalued = multivalued;
      return self();
    }

    public SELF description(String description) {
      this.description = description;
      return self();
    }

    public SELF metadata(Map<String, SourcePropertyValue> metadata) {
      this.metadata = metadata;
      return self();
    }

    protected SourceField build() {
      return new SourceField(this);
    }

    @SuppressWarnings("unchecked")
    private SELF self() {
      return (SELF) this;
    }
  }
}
