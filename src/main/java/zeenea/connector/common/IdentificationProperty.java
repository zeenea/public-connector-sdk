package zeenea.connector.common;

import java.util.Objects;
import java.util.StringJoiner;

public class IdentificationProperty {

  private final String key;
  private final String value;

  public IdentificationProperty(Builder builder) {
    this.key = builder.key;
    this.value = builder.value;
  }

  public String getKey() {
    return key;
  }

  public String getValue() {
    return value;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private String key;
    private String value;

    private Builder() {}

    public Builder key(String key) {
      this.key = key;
      return this;
    }

    public Builder value(String value) {
      this.value = value;
      return this;
    }

    public IdentificationProperty build() {
      if (this.key == null || this.key.isEmpty()) {
        throw new IllegalArgumentException("key must not be null or empty");
      }
      if (this.value == null || this.value.isEmpty()) {
        throw new IllegalArgumentException("value must not be null or empty");
      }
      return new IdentificationProperty(this);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    IdentificationProperty that = (IdentificationProperty) o;
    return Objects.equals(key, that.key) && Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, value);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", IdentificationProperty.class.getSimpleName() + "[", "]")
        .add("key='" + key + "'")
        .add("value='" + value + "'")
        .toString();
  }
}
