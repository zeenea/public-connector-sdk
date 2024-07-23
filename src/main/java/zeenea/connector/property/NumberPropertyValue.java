package zeenea.connector.property;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Sub-class of {@link PropertyValue} containing a value of type {@code BigDecimal}.
 *
 * @see PropertyValue
 * @see NumberPropertyDefinition
 * @since 1.0.0
 */
public class NumberPropertyValue implements PropertyValue {

  private final BigDecimal value;

  /**
   * Create a new instance of NumberMetadataValue, containing a value of type {@code BigDecimal}.
   *
   * @param value The metadata value
   */
  public NumberPropertyValue(BigDecimal value) {
    this.value = value;
  }

  /**
   * Get the {@code BigDecimal} value of the metadata.
   *
   * @return The {@code BigDecimal} value of the metadata
   */
  public BigDecimal getValue() {
    return this.value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    NumberPropertyValue that = (NumberPropertyValue) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", NumberPropertyValue.class.getSimpleName() + "[", "]")
        .add("value=" + value)
        .toString();
  }
}
