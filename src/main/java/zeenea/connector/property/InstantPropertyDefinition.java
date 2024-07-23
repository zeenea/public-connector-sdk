package zeenea.connector.property;

/**
 * Sub-class of {@link PropertyDefinition} associated to a value of type {@code Instant}.
 *
 * @see PropertyDefinition
 * @see InstantPropertyValue
 * @since 1.0.0
 */
public class InstantPropertyDefinition extends PropertyDefinition {

  /**
   * Create a new instance of InstantMetadata, a metadata of type {@code MetadataType.INSTANT}.
   *
   * @param code The metadata code
   */
  public InstantPropertyDefinition(String code) {
    super(code, PropertyType.INSTANT);
  }
}
