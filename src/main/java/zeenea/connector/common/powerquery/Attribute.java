package zeenea.connector.common.powerquery;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import zeenea.connector.exception.ExceptionUtils;

/**
 * Represents an attribute of an ODBC data source definition. The name must be one of:
 *
 * <ul>
 *   <li>host
 *   <li>port
 *   <li>database
 *   <li>awsRegion
 * </ul>
 *
 * Names are represented as strings rather than enums to allow for extensibility and to accommodate
 * any additional attributes that may be needed in the future.
 */
public class Attribute {
  /** The name of the attribute. */
  @NotNull private final String name;

  /** The value of the attribute. */
  @NotNull private final String value;

  /**
   * Constructs an Attribute instance using the provided builder.
   *
   * @param builder the builder used to create the Attribute instance
   */
  private Attribute(Attribute.Builder builder) {
    ExceptionUtils.requireNonNullOrEmpty("name", builder.name);
    ExceptionUtils.requireNonNullOrEmpty("value", builder.value);
    this.name = builder.name;
    this.value = builder.value;
  }

  /**
   * Creates a new Attribute instance with the specified name and value.
   *
   * @param name the name of the attribute
   * @param value the value of the attribute
   * @return a new Attribute instance
   */
  public static Attribute of(@NotNull String name, @NotNull String value) {
    return builder().name(name).value(value).build();
  }

  /**
   * Gets the name of the attribute.
   *
   * @return the name of the attribute
   */
  public @NotNull String getName() {
    return name;
  }

  /**
   * Gets the value of the attribute.
   *
   * @return the value of the attribute
   */
  public @NotNull String getValue() {
    return value;
  }

  /**
   * Creates a new builder for an Attribute instance.
   *
   * @return a new Builder instance
   */
  public static Attribute.Builder builder() {
    return new Attribute.Builder();
  }

  /**
   * Checks if this attribute is equal to another object.
   *
   * @param o the object to compare with
   * @return true if this attribute is equal to the specified object, otherwise false
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Attribute that = (Attribute) o;
    return Objects.equals(name, that.name) && Objects.equals(value, that.value);
  }

  /**
   * Computes the hash code for this attribute.
   *
   * @return the hash code of this attribute
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, value);
  }

  /**
   * Returns a string representation of this attribute.
   *
   * @return a string representation of this attribute
   */
  @Override
  public String toString() {
    return "Attribute{" + "name='" + name + "', value='" + value + "'}";
  }

  /** Builder class for creating instances of Attribute. */
  public static class Builder {

    /** The name of the attribute. */
    private String name;

    /** The value of the attribute. */
    private String value;

    /**
     * Sets the name of the attribute.
     *
     * @param name the name of the attribute
     * @return the Builder instance
     */
    public Attribute.Builder name(@NotNull String name) {
      this.name = name;
      return this;
    }

    /**
     * Sets the value of the attribute.
     *
     * @param value the value of the attribute
     * @return the Builder instance
     */
    public Attribute.Builder value(@NotNull String value) {
      this.value = value;
      return this;
    }

    /**
     * Builds and returns the Attribute instance.
     *
     * @return the created Attribute instance
     */
    public Attribute build() {
      return new Attribute(this);
    }
  }
}
