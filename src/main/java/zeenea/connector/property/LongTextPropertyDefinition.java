package zeenea.connector.property;

import java.util.UUID;

/**
 * Represents a property definition for a long text type. This class extends the PropertyDefinition
 * class.
 */
public final class LongTextPropertyDefinition extends PropertyDefinition {

  /**
   * Constructs a LongTextPropertyDefinition instance with the specified name.
   *
   * @param name the name for the property definition
   */
  public LongTextPropertyDefinition(String name) {
    super(name, PropertyType.LONG_TEXT);
  }

  /**
   * Constructs a LongTextPropertyDefinition instance with the specified name and UUID. Used only
   * internally for common properties
   *
   * @param name the name for the property definition
   * @param uuid the UUID for the property definition
   */
  LongTextPropertyDefinition(String name, UUID uuid) {
    super(name, PropertyType.LONG_TEXT, uuid);
  }

  /**
   * Constructs a LongTextPropertyDefinition instance with the specified code and label.
   *
   * @param code the code for the property definition
   * @param label the label for the property definition
   */
  @Deprecated(
      since =
          "Deprecated since version 2.3.3, use LongTextPropertyDefinition(String name) instead. Scheduled for removal in version 3.0.0.",
      forRemoval = true)
  public LongTextPropertyDefinition(String code, String label) {
    super(code, PropertyType.LONG_TEXT, label);
  }
}
