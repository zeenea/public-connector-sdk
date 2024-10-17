package zeenea.connector.property;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a property definition. This is an abstract class that provides the basic structure for
 * property definitions.
 */
public abstract class PropertyDefinition {

  /** The code of the property definition. */
  @NotNull private final String code;

  /** The type of the property definition. */
  @NotNull private final PropertyType type;

  /**
   * Constructs a PropertyDefinition instance with the specified code and type.
   *
   * @param code the code of the property definition
   * @param type the type of the property definition
   */
  PropertyDefinition(@NotNull String code, @NotNull PropertyType type) {
    this.code = Objects.requireNonNull(code);
    this.type = Objects.requireNonNull(type);
  }

  /**
   * Gets the code of the property definition.
   *
   * @return the code of the property definition
   */
  public @NotNull String getCode() {
    return code;
  }

  /**
   * Gets the type of the property definition.
   *
   * @return the type of the property definition
   */
  public @NotNull PropertyType getType() {
    return type;
  }

  /**
   * Creates a new StringPropertyDefinition with the specified code.
   *
   * @param code the code of the property definition
   * @return a new StringPropertyDefinition instance
   */
  public static StringPropertyDefinition string(String code) {
    return new StringPropertyDefinition(code);
  }

  /**
   * Creates a new LongTextPropertyDefinition with the specified code.
   *
   * @param code the code of the property definition
   * @return a new LongTextPropertyDefinition instance
   */
  public static LongTextPropertyDefinition longText(String code) {
    return new LongTextPropertyDefinition(code);
  }

  /**
   * Creates a new InstantPropertyDefinition with the specified code.
   *
   * @param code the code of the property definition
   * @return a new InstantPropertyDefinition instance
   */
  public static InstantPropertyDefinition instant(String code) {
    return new InstantPropertyDefinition(code);
  }

  /**
   * Creates a new NumberPropertyDefinition with the specified code.
   *
   * @param code the code of the property definition
   * @return a new NumberPropertyDefinition instance
   */
  public static NumberPropertyDefinition number(String code) {
    return new NumberPropertyDefinition(code);
  }

  /**
   * Creates a new TagPropertyDefinition with the specified code.
   *
   * @param code the code of the property definition
   * @return a new TagPropertyDefinition instance
   */
  public static TagPropertyDefinition tag(String code) {
    return new TagPropertyDefinition(code);
  }

  /**
   * Creates a new UrlPropertyDefinition with the specified code.
   *
   * @param code the code of the property definition
   * @return a new UrlPropertyDefinition instance
   */
  public static UrlPropertyDefinition url(String code) {
    return new UrlPropertyDefinition(code);
  }

  /**
   * Checks if this PropertyDefinition is equal to another object.
   *
   * @param o the object to compare with
   * @return true if this PropertyDefinition is equal to the specified object, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PropertyDefinition sourcePropertyDefinition = (PropertyDefinition) o;
    return Objects.equals(code, sourcePropertyDefinition.code)
        && type == sourcePropertyDefinition.type;
  }

  /**
   * Returns the hash code of this PropertyDefinition.
   *
   * @return the hash code of this PropertyDefinition
   */
  @Override
  public int hashCode() {
    return Objects.hash(code, type);
  }

  /**
   * Returns the string representation of this PropertyDefinition.
   *
   * @return the string representation of this PropertyDefinition
   */
  @Override
  public String toString() {
    return "PropertyDefinition{" + "code='" + code + ", type=" + type + "}";
  }
}
