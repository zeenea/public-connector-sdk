package zeenea.connector.property;

import java.net.URI;
import java.util.Objects;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a property value of type URL. This class is immutable and implements the PropertyValue
 * interface.
 */
public final class UrlPropertyValue implements PropertyValue {

  /** The value of the property. */
  @NotNull private final URI value;

  /** An optional label for the property. */
  @Nullable private final String label;

  /**
   * Constructs a UrlPropertyValue instance with the specified value.
   *
   * @param value the value of the property
   */
  public UrlPropertyValue(@NotNull URI value) {
    this.value = value;
    this.label = null;
  }

  /**
   * Constructs a UrlPropertyValue instance with the specified value and label.
   *
   * @param value the value of the property
   * @param label an optional label for the property
   */
  public UrlPropertyValue(@NotNull URI value, @Nullable String label) {
    this.value = value;
    this.label = label;
  }

  /**
   * Gets the value of the property.
   *
   * @return the value of the property
   */
  public @NotNull URI getValue() {
    return this.value;
  }

  /**
   * Gets the optional label of the property.
   *
   * @return an Optional containing the label if present, otherwise an empty Optional
   */
  public Optional<String> getLabel() {
    return Optional.ofNullable(this.label);
  }

  /**
   * Checks if this UrlPropertyValue is equal to another object.
   *
   * @param o the object to compare with
   * @return true if this UrlPropertyValue is equal to the specified object, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UrlPropertyValue that = (UrlPropertyValue) o;
    return Objects.equals(value, that.value) && Objects.equals(label, that.label);
  }

  /**
   * Returns the hash code of this UrlPropertyValue.
   *
   * @return the hash code of this UrlPropertyValue
   */
  @Override
  public int hashCode() {
    return Objects.hash(value, label);
  }

  /**
   * Returns the string representation of this UrlPropertyValue.
   *
   * @return the string representation of this UrlPropertyValue
   */
  @Override
  public String toString() {
    return "UrlPropertyValue{" + "label='" + label + ", value=" + value + "}";
  }
}
