package zeenea.connector.property;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/** Represents a property value that holds a list of tags. */
public final class TagPropertyValue implements PropertyValue {

  private final List<String> value;

  /**
   * Constructs a new TagPropertyValue with the specified list of tags.
   *
   * @param value the list of tags
   */
  public TagPropertyValue(List<String> value) {
    this.value = value;
  }

  /**
   * Returns the list of tags.
   *
   * @return the list of tags
   */
  public List<String> getValue() {
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
