package zeenea.connector.property;

/**
 * Sub-class of {@link SourcePropertyDefinition} associated to a value of type {@code Instant}.
 *
 * @see SourcePropertyDefinition
 * @see InstantSourcePropertyValue
 * @since 1.0.0
 */
public class InstantSourcePropertyDefinition extends SourcePropertyDefinition {

  /**
   * Create a new instance of InstantMetadata, a metadata of type {@code MetadataType.INSTANT}.
   *
   * @param code The metadata code
   */
  public InstantSourcePropertyDefinition(String code) {
    super(code, SourcePropertyType.INSTANT);
  }
}
