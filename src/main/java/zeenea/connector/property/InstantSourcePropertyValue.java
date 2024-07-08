package zeenea.connector.property;

import java.time.Instant;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Sub-class of {@link SourcePropertyValue} containing a value of type {@code Instant}.
 *
 * @see SourcePropertyValue
 * @see InstantSourcePropertyDefinition
 * @since 1.0.0
 */
public class InstantSourcePropertyValue implements SourcePropertyValue {

  private final Instant value;

  /**
   * Create a new instance of InstantMetadataValue, containing a value of type {@code Instant}.
   *
   * @param value The metadata value
   */
  public InstantSourcePropertyValue(Instant value) {
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
    InstantSourcePropertyValue that = (InstantSourcePropertyValue) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", InstantSourcePropertyValue.class.getSimpleName() + "[", "]")
        .add("value=" + value)
        .toString();
  }
}
