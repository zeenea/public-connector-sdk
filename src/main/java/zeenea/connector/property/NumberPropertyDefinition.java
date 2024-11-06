package zeenea.connector.property;

import java.util.UUID;

/**
 * Represents a property definition for a number type. This class extends the PropertyDefinition
 * class.
 */
public final class NumberPropertyDefinition extends PropertyDefinition {

  /**
   * Constructs a NumberPropertyDefinition instance with the specified code.
   *
   * @param code the code for the property definition
   */
  public NumberPropertyDefinition(String code) {
    super(code, PropertyType.NUMBER, null);
  }

  /**
   * Constructs a NumberPropertyDefinition instance with the specified code and UUID.
   *
   * @param code the code for the property definition
   * @param uuid the UUID for the property definition
   */
  public NumberPropertyDefinition(String code, UUID uuid) {
    super(code, PropertyType.NUMBER, uuid);
  }
}
