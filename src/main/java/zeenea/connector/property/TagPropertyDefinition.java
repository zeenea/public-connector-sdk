package zeenea.connector.property;

import java.util.UUID;

/**
 * Represents a property definition for a tag type. This class extends the PropertyDefinition class.
 */
public final class TagPropertyDefinition extends PropertyDefinition {

  /**
   * Constructs a TagPropertyDefinition instance with the specified name.
   *
   * @param name the name for the property definition
   */
  public TagPropertyDefinition(String name) {
    super(name, PropertyType.TAG);
  }

  /**
   * Constructs a TagPropertyDefinition instance with the specified name and UUID. Used only
   * internally for common properties
   *
   * @param name the name for the property definition
   * @param uuid the UUID for the property definition
   */
  TagPropertyDefinition(String name, UUID uuid) {
    super(name, PropertyType.TAG, uuid);
  }

  /**
   * Constructs a TagPropertyDefinition instance with the specified code and label.
   *
   * @param code the code for the property definition
   * @param label the label for the property definition
   */
  @Deprecated(
      since =
          "Deprecated since version 2.3.3, use TagPropertyDefinition(String name) instead. Scheduled for removal in version 3.0.0.",
      forRemoval = true)
  public TagPropertyDefinition(String code, String label) {
    super(code, PropertyType.TAG, label);
  }
}
