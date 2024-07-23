package zeenea.connector.property;

import java.time.Instant;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Sub-class of {@link PropertyValue} containing a value of type {@code Instant}.
 *
 * @see PropertyValue
 * @see InstantPropertyDefinition
 * @since 1.0.0
 */
public class InstantPropertyValue implements PropertyValue {

  private final Instant value;

  /**
   * Create a new instance of InstantMetadataValue, containing a value of type {@code Instant}.
   *
   * @param value The metadata value
   */
  public InstantPropertyValue(Instant value) {
    this.value = value;
  }

  /**
   * Get the {@code Instant} value of the metadata.
   *
   * @return The {@code Instant} value of the metadata
   */
  public Instant getValue() {
    return this.value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    InstantPropertyValue that = (InstantPropertyValue) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", InstantPropertyValue.class.getSimpleName() + "[", "]")
        .add("value=" + value)
        .toString();
  }
}
