package zeenea.connector.metadata;

import java.time.Instant;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Sub-class of {@link MetadataValue} containing a value of type {@code Instant}.
 *
 * @see MetadataValue
 * @see InstantMetadata
 * @since 1.0.0
 */
public class InstantMetadataValue implements MetadataValue {

  private final Instant value;

  /**
   * Create a new instance of InstantMetadataValue, containing a value of type {@code Instant}.
   *
   * @param value The metadata value
   */
  public InstantMetadataValue(Instant value) {
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
    InstantMetadataValue that = (InstantMetadataValue) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", InstantMetadataValue.class.getSimpleName() + "[", "]")
        .add("value=" + value)
        .toString();
  }
}
