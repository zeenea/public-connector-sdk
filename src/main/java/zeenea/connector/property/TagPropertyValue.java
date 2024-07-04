package zeenea.connector.property;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Represents a property value of type Tag. This class is immutable and implements the PropertyValue
 * interface.
 */
public final class TagPropertyValue implements PropertyValue {

  /** The value of the property. */
  private final String value;

  /**
   * Constructs a TagPropertyValue instance with the specified value.
   *
   * @param value the value of the property
   */
  public TagPropertyValue(String value) {
    this.value = value;
  }

  /**
   * Gets the value of the property.
   *
   * @return the value of the property
   */
  public String getValue() {
    return this.value;
  }

  /**
   * Checks if this TagPropertyValue is equal to another object.
   *
   * @param o the object to compare with
   * @return true if this TagPropertyValue is equal to the specified object, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TagPropertyValue that = (TagPropertyValue) o;
    return Objects.equals(value, that.value);
  }

  /**
   * Returns the hash code of this TagPropertyValue.
   *
   * @return the hash code of this TagPropertyValue
   */
  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  /**
   * Returns the string representation of this TagPropertyValue.
   *
   * @return the string representation of this TagPropertyValue
   */
  @Override
  public String toString() {
    return new StringJoiner(", ", TagPropertyValue.class.getSimpleName() + "[", "]")
        .add("value='" + value + "'")
        .toString();
  }
}
