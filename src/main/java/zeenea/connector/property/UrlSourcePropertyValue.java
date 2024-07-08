package zeenea.connector.property;

import java.net.URI;
import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;

/**
 * Sub-class of {@link SourcePropertyValue} containing a value of type {@code URI} and a label.
 *
 * @see SourcePropertyValue
 * @see UrlSourcePropertyDefinition
 * @since 1.0.0
 */
public class UrlSourcePropertyValue implements SourcePropertyValue {

  private final URI value;
  private final String label;

  /**
   * Create a new instance of UrlMetadataValue, containing a value of type {@code URI} and no label.
   *
   * @param value The metadata value
   */
  public UrlSourcePropertyValue(URI value) {
    this.value = value;
    this.label = null;
  }

  /**
   * Create a new instance of UrlMetadataValue, containing a value of type {@code URI} and a label.
   *
   * @param value The metadata value
   * @param label The label
   */
  public UrlSourcePropertyValue(URI value, String label) {
    this.value = value;
    this.label = label;
  }

  /**
   * Get the {@code URI} value of the metadata.
   *
   * @return The {@code URI} value of the metadata
   */
  public URI getValue() {
    return this.value;
  }

  /**
   * Get the label associated with the metadata value.
   *
   * @return The label associated with the metadata value, or {@code Optional.empty()} if absent
   */
  public Optional<String> getLabel() {
    return Optional.ofNullable(this.label);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UrlSourcePropertyValue that = (UrlSourcePropertyValue) o;
    return Objects.equals(value, that.value) && Objects.equals(label, that.label);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, label);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", UrlSourcePropertyValue.class.getSimpleName() + "[", "]")
        .add("value=" + value)
        .add("label='" + label + "'")
        .toString();
  }
}
