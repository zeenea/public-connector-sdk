package zeenea.connector.property;

import java.math.BigDecimal;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a property value of type Number. This class is immutable and implements the
 * PropertyValue interface.
 */
public final class NumberPropertyValue implements PropertyValue {

  /** The value of the property. */
  @NotNull private final BigDecimal value;

  /**
   * Constructs a NumberPropertyValue instance with the specified value.
   *
   * @param value the value of the property
   */
  public NumberPropertyValue(@NotNull BigDecimal value) {
    this.value = value;
  }

  /**
   * Gets the value of the property.
   *
   * @return the value of the property
   */
  public @NotNull BigDecimal getValue() {
    return this.value;
  }

  /**
   * Checks if this NumberPropertyValue is equal to another object.
   *
   * @param o the object to compare with
   * @return true if this NumberPropertyValue is equal to the specified object, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    NumberPropertyValue that = (NumberPropertyValue) o;
    return Objects.equals(value, that.value);
  }

  /**
   * Returns the hash code of this NumberPropertyValue.
   *
   * @return the hash code of this NumberPropertyValue
   */
  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  /**
   * Returns the string representation of this NumberPropertyValue.
   *
   * @return the string representation of this NumberPropertyValue
   */
  @Override
  public String toString() {
    return "NumberPropertyValue{" + "value=" + value + "}";
  }
}
