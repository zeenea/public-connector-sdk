package zeenea.connector.property;

import java.time.Instant;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a property value of type Instant. This class is immutable and implements the
 * PropertyValue interface.
 */
public final class InstantPropertyValue implements PropertyValue {

  /** The value of the property. */
  @NotNull private final Instant value;

  /**
   * Constructs an InstantPropertyValue instance with the specified value.
   *
   * @param value the value of the property
   */
  public InstantPropertyValue(@NotNull Instant value) {
    this.value = value;
  }

  /**
   * Gets the value of the property.
   *
   * @return the value of the property
   */
  public @NotNull Instant getValue() {
    return this.value;
  }

  /**
   * Checks if this InstantPropertyValue is equal to another object.
   *
   * @param o the object to compare with
   * @return true if this InstantPropertyValue is equal to the specified object, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    InstantPropertyValue that = (InstantPropertyValue) o;
    return Objects.equals(value, that.value);
  }

  /**
   * Returns the hash code of this InstantPropertyValue.
   *
   * @return the hash code of this InstantPropertyValue
   */
  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  /**
   * Returns the string representation of this InstantPropertyValue.
   *
   * @return the string representation of this InstantPropertyValue
   */
  @Override
  public String toString() {
    return "InstantPropertyValue{" + "value=" + value + "}";
  }
}
