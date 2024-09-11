package zeenea.connector.property;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a property value of type String. This class is immutable and implements the
 * PropertyValue interface.
 */
public final class StringPropertyValue implements PropertyValue {

  /** The value of the property. */
  @NotNull private final String value;

  /**
   * Constructs a StringPropertyValue instance with the specified value.
   *
   * @param value the value of the property
   */
  public StringPropertyValue(@NotNull String value) {
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
   * Checks if this StringPropertyValue is equal to another object.
   *
   * @param o the object to compare with
   * @return true if this StringPropertyValue is equal to the specified object, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    StringPropertyValue that = (StringPropertyValue) o;
    return Objects.equals(value, that.value);
  }

  /**
   * Returns the hash code of this StringPropertyValue.
   *
   * @return the hash code of this StringPropertyValue
   */
  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  /**
   * Returns the string representation of this StringPropertyValue.
   *
   * @return the string representation of this StringPropertyValue
   */
  @Override
  public String toString() {
    return "StringPropertyValue{" + "value='" + value + "'}";
  }
}
