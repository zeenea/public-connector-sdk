package zeenea.connector.property;

/**
 * Sub-class of {@link PropertyDefinition} associated to a value of type {@code BigDecimal}.
 *
 * @see PropertyDefinition
 * @see NumberPropertyValue
 * @since 1.0.0
 */
public class NumberPropertyDefinition extends PropertyDefinition {

  /**
   * Create a new instance of NumberMetadata, a metadata of type {@code MetadataType.NUMBER}.
   *
   * @param code The metadata code
   */
  public NumberPropertyDefinition(String code) {
    super(code, PropertyType.NUMBER);
  }
}
