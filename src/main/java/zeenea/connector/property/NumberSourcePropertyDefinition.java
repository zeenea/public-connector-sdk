package zeenea.connector.property;

/**
 * Sub-class of {@link SourcePropertyDefinition} associated to a value of type {@code BigDecimal}.
 *
 * @see SourcePropertyDefinition
 * @see NumberSourcePropertyValue
 * @since 1.0.0
 */
public class NumberSourcePropertyDefinition extends SourcePropertyDefinition {

  /**
   * Create a new instance of NumberMetadata, a metadata of type {@code MetadataType.NUMBER}.
   *
   * @param code The metadata code
   */
  public NumberSourcePropertyDefinition(String code) {
    super(code, SourcePropertyType.NUMBER);
  }
}
