package zeenea.connector.property;

import java.util.Objects;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a property definition. This is an abstract class that provides the basic structure for
 * property definitions.
 *
 * <p>Properties are uniquely identified by their name and type.
 */
public abstract class PropertyDefinition {

  /**
   * The name of the property definition.
   *
   * <p>The name must be not null and must not change to ensure the uniqueness of the property.
   */
  @NotNull private final String name;

  /**
   * The type of the property definition.
   *
   * <p>The type must be not null and must not change to ensure the uniqueness of the property.
   */
  @NotNull private final PropertyType type;

  /**
   * Constructs a PropertyDefinition instance with the specified code and type.
   *
   * @param code the code of the property definition
   * @param type the type of the property definition
   * @param label the label of the property definition
   */
  PropertyDefinition(@NotNull String code, @NotNull PropertyType type, @Nullable String label) {
    this(label == null ? Objects.requireNonNull(code) : label, type);
  }

  /**
   * Constructs a PropertyDefinition instance with the specified name and type.
   *
   * @param name the name of the property definition
   * @param type the type of the property definition
   */
  PropertyDefinition(@NotNull String name, @NotNull PropertyType type) {
    this.name = Objects.requireNonNull(name);
    this.type = Objects.requireNonNull(type);
  }

  /**
   * Gets the code of the property definition.
   *
   * @return the code of the property definition
   */
  @Deprecated(
      since =
          "Deprecated since version 2.3.3, use getName() instead. Scheduled for removal in version 3.0.0.",
      forRemoval = true)
  public @NotNull String getCode() {
    return name;
  }

  public @NotNull String getName() {
    return name;
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
   * Gets the label of the property definition.
   *
   * @return the label of the property definition
   */
  @Deprecated(
      since =
          "Deprecated since version 2.3.3, use getName() instead. Scheduled for removal in version 3.0.0.",
      forRemoval = true)
  public Optional<String> getLabel() {
    return Optional.of(name);
  }

  /**
   * Creates a new StringPropertyDefinition with the specified name.
   *
   * @param name the name of the property definition
   * @return a new StringPropertyDefinition instance
   */
  public static StringPropertyDefinition string(String name) {
    return new StringPropertyDefinition(name);
  }

  /**
   * Creates a new StringPropertyDefinition with the specified code and label.
   *
   * @param code the code of the property definition
   * @param label the label of the property definition
   * @return a new StringPropertyDefinition instance
   */
  @Deprecated(
      since =
          "Deprecated since version 2.3.3, use string(String name) instead. Scheduled for removal in version 3.0.0.",
      forRemoval = true)
  public static StringPropertyDefinition string(String code, String label) {
    return new StringPropertyDefinition(label == null ? code : label);
  }

  /**
   * Creates a new LongTextPropertyDefinition with the specified name.
   *
   * @param name the name of the property definition
   * @return a new LongTextPropertyDefinition instance
   */
  public static LongTextPropertyDefinition longText(String name) {
    return new LongTextPropertyDefinition(name);
  }

  /**
   * Creates a new LongTextPropertyDefinition with the specified code and label.
   *
   * @param code the code of the property definition
   * @param label the label of the property definition
   * @return a new LongTextPropertyDefinition instance
   */
  @Deprecated(
      since =
          "Deprecated since version 2.3.3, use longText(String name) instead. Scheduled for removal in version 3.0.0.",
      forRemoval = true)
  public static LongTextPropertyDefinition longText(String code, String label) {
    return new LongTextPropertyDefinition(label);
  }

  /**
   * Creates a new InstantPropertyDefinition with the specified code.
   *
   * @param name the name of the property definition
   * @return a new InstantPropertyDefinition instance
   */
  public static InstantPropertyDefinition instant(String name) {
    return new InstantPropertyDefinition(name);
  }

  /**
   * Creates a new InstantPropertyDefinition with the specified code and label.
   *
   * @param code the code of the property definition
   * @param label the label of the property definition
   * @return a new InstantPropertyDefinition instance
   */
  @Deprecated(
      since =
          "Deprecated since version 2.3.3, use instant(String name) instead. Scheduled for removal in version 3.0.0.",
      forRemoval = true)
  public static InstantPropertyDefinition instant(String code, String label) {
    return new InstantPropertyDefinition(label);
  }

  /**
   * Creates a new NumberPropertyDefinition with the specified name.
   *
   * @param name the name of the property definition
   * @return a new NumberPropertyDefinition instance
   */
  public static NumberPropertyDefinition number(String name) {
    return new NumberPropertyDefinition(name);
  }

  /**
   * Creates a new NumberPropertyDefinition with the specified code and label.
   *
   * @param code the code of the property definition
   * @param label the label of the property definition
   * @return a new NumberPropertyDefinition instance
   */
  @Deprecated(
      since =
          "Deprecated since version 2.3.3, use number(String name) instead. Scheduled for removal in version 3.0.0.",
      forRemoval = true)
  public static NumberPropertyDefinition number(String code, String label) {
    return new NumberPropertyDefinition(label);
  }

  /**
   * Creates a new TagPropertyDefinition with the specified name.
   *
   * @param name the name of the property definition
   * @return a new TagPropertyDefinition instance
   */
  public static TagPropertyDefinition tag(String name) {
    return new TagPropertyDefinition(name);
  }

  /**
   * Creates a new TagPropertyDefinition with the specified code and label.
   *
   * @param code the code of the property definition
   * @param label the label of the property definition
   * @return a new TagPropertyDefinition instance
   */
  @Deprecated(
      since =
          "Deprecated since version 2.3.3, use tag(String name) instead. Scheduled for removal in version 3.0.0.",
      forRemoval = true)
  public static TagPropertyDefinition tag(String code, String label) {
    return new TagPropertyDefinition(label);
  }

  /**
   * Creates a new UrlPropertyDefinition with the specified name.
   *
   * @param name the name of the property definition
   * @return a new UrlPropertyDefinition instance
   */
  public static UrlPropertyDefinition url(String name) {
    return new UrlPropertyDefinition(name);
  }

  /**
   * Creates a new UrlPropertyDefinition with the specified code and label.
   *
   * @param code the code of the property definition
   * @param label the label of the property definition
   * @return a new UrlPropertyDefinition instance
   */
  @Deprecated(
      since =
          "Deprecated since version 2.3.3, use url(String name) instead. Scheduled for removal in version 3.0.0.",
      forRemoval = true)
  public static UrlPropertyDefinition url(String code, String label) {
    return new UrlPropertyDefinition(label);
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
    return Objects.equals(name, sourcePropertyDefinition.name)
        && type == sourcePropertyDefinition.type;
  }

  /**
   * Returns the hash code of this PropertyDefinition.
   *
   * @return the hash code of this PropertyDefinition
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, type);
  }

  /**
   * Returns the string representation of this PropertyDefinition.
   *
   * @return the string representation of this PropertyDefinition
   */
  @Override
  public String toString() {
    return "PropertyDefinition{" + "name='" + name + "', type=" + type + "}";
  }
}
