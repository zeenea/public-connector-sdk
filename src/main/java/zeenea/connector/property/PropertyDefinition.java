package zeenea.connector.property;

import java.util.Objects;
import java.util.StringJoiner;
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
    return new StringJoiner(", ", PropertyDefinition.class.getSimpleName() + "[", "]")
        .add("code='" + code + "'")
        .add("type=" + type)
        .toString();
  }
}
