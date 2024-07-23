package zeenea.connector.dataset;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;
import zeenea.connector.property.PropertyValue;

public class Field {

  private final String name;
  private final DataType dataType;
  private final String nativeType;
  private final int nativeIndex;
  private final List<String> keys;
  private final boolean nullable;
  private final boolean multivalued;
  private final String description;
  private final Map<String, PropertyValue> metadata;

  public Field(Field.Builder<?, ?> builder) {
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

  public DataType getDataType() {
    return dataType;
  }

  public String getDescription() {
    return description;
  }

  public List<String> getKeys() {
    return keys;
  }

  public Map<String, PropertyValue> getMetadata() {
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

  public static class Builder<T, SELF extends Field.Builder<T, ?>> {

    private String name;
    private DataType dataType;
    private String nativeType;
    private int nativeIndex;
    private List<String> keys;
    private boolean nullable;
    private boolean multivalued;
    private String description;
    private Map<String, PropertyValue> metadata;

    protected Builder() {}

    protected static void throwIfNull(String attributeName, Object attributeValue) {
      if (attributeValue == null)
        throw new NullPointerException("Attribute \"" + attributeName + "\" cannot be null");
    }

    public SELF name(String name) {
      this.name = name;
      return self();
    }

    public SELF dataType(DataType dataType) {
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

    public SELF metadata(Map<String, PropertyValue> metadata) {
      this.metadata = metadata;
      return self();
    }

    protected Field build() {
      return new Field(this);
    }

    @SuppressWarnings("unchecked")
    private SELF self() {
      return (SELF) this;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Field that = (Field) o;
    return nativeIndex == that.nativeIndex
        && nullable == that.nullable
        && multivalued == that.multivalued
        && Objects.equals(name, that.name)
        && dataType == that.dataType
        && Objects.equals(nativeType, that.nativeType)
        && Objects.equals(keys, that.keys)
        && Objects.equals(description, that.description)
        && Objects.equals(metadata, that.metadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        name,
        dataType,
        nativeType,
        nativeIndex,
        keys,
        nullable,
        multivalued,
        description,
        metadata);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Field.class.getSimpleName() + "[", "]")
        .add("dataType=" + dataType)
        .add("name='" + name + "'")
        .add("nativeType='" + nativeType + "'")
        .add("nativeIndex=" + nativeIndex)
        .add("keys=" + keys)
        .add("nullable=" + nullable)
        .add("multivalued=" + multivalued)
        .add("description='" + description + "'")
        .add("metadata=" + metadata)
        .toString();
  }
}
