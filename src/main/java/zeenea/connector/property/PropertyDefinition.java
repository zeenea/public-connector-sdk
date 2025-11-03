package zeenea.connector.property;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
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
   * The UUID of the property definition when it's a common property such as sql query or schema
   *
   * <p>This field is optional and can be null.
   */
  @Nullable private final UUID uuid;

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
    this.uuid = null;
  }

  /**
   * Constructs a PropertyDefinition instance with the specified name, type and uuid. Used only
   * internally to build common properties
   *
   * @param name the name of the property definition
   * @param type the type of the property definition
   * @param uuid the uuid of the property definition
   */
  PropertyDefinition(@NotNull String name, @NotNull PropertyType type, @NotNull UUID uuid) {
    this.name = Objects.requireNonNull(name);
    this.type = Objects.requireNonNull(type);
    this.uuid = Objects.requireNonNull(uuid);
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
   * Gets the UUID of the property definition, in case of a common property.
   *
   * @return an Optional containing the UUID of the property definition, or an empty Optional if not
   *     present
   */
  public Optional<UUID> getUuid() {
    return Optional.ofNullable(uuid);
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
    return new StringPropertyDefinition(code, label);
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
    return new LongTextPropertyDefinition(code, label);
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
    return new InstantPropertyDefinition(code, label);
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
    return new NumberPropertyDefinition(code, label);
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
    return new TagPropertyDefinition(code, label);
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
    return new UrlPropertyDefinition(code, label);
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
