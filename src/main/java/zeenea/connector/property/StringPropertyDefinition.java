package zeenea.connector.property;

import java.util.UUID;

/**
 * Represents a property definition for a string type. This class extends the PropertyDefinition
 * class.
 */
public final class StringPropertyDefinition extends PropertyDefinition {

  /**
   * Constructs a StringPropertyDefinition instance with the specified code.
   *
   * @param code the code for the property definition
   */
  public StringPropertyDefinition(String code) {
    super(code, PropertyType.STRING, null);
  }

  /**
   * Constructs a StringPropertyDefinition instance with the specified code and UUID.
   *
   * @param code the code for the property definition
   * @param uuid the UUID for the property definition
   */
  public StringPropertyDefinition(String code, UUID uuid) {
    super(code, PropertyType.STRING, uuid);
  }
}
