package zeenea.connector.property;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Sub-class of {@link SourcePropertyValue} containing a value of type {@code String}.
 *
 * @see SourcePropertyValue
 * @see StringSourcePropertyDefinition
 * @since 1.0.0
 */
public class StringSourcePropertyValue implements SourcePropertyValue {

  private final String value;

  /**
   * Create a new instance of StringMetadataValue, containing a value of type {@code String}.
   *
   * @param value The metadata value
   */
  public StringSourcePropertyValue(String value) {
    this.value = value;
  }

  /**
   * Get the {@code String} value of the metadata.
   *
   * @return The {@code String} value of the metadata
   */
  public String getValue() {
    return this.value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    StringSourcePropertyValue that = (StringSourcePropertyValue) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", StringSourcePropertyValue.class.getSimpleName() + "[", "]")
        .add("value='" + value + "'")
        .toString();
  }
}
