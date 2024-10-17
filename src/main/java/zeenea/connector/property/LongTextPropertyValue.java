package zeenea.connector.property;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a property value of long text type. This class is immutable and implements the
 * PropertyValue interface.
 *
 * <p>Long text property value is not indexed and searchable in data catalog.
 */
public final class LongTextPropertyValue implements PropertyValue {

  /** The value of the property. */
  @NotNull private final String value;

  /**
   * Constructs a LongTextPropertyValue instance with the specified value.
   *
   * @param value the value of the property
   */
  public LongTextPropertyValue(@NotNull String value) {
    this.value = value;
  }

  /**
   * Gets the value of the property.
   *
   * @return the value of the property
   */
  public @NotNull String getValue() {
    return this.value;
  }

  /**
   * Checks if this LongTextPropertyValue is equal to another object.
   *
   * @param o the object to compare with
   * @return true if this LongTextPropertyValue is equal to the specified object, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    LongTextPropertyValue that = (LongTextPropertyValue) o;
    return Objects.equals(value, that.value);
  }

  /**
   * Returns the hash code of this LongTextPropertyValue.
   *
   * @return the hash code of this LongTextPropertyValue
   */
  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  /**
   * Returns the string representation of this LongTextPropertyValue.
   *
   * @return the string representation of this LongTextPropertyValue
   */
  @Override
  public String toString() {
    return "LongTextPropertyValue{" + "value='" + value + "'}";
  }
}
