package zeenea.connector.metadata;

/**
 * Sub-class of {@link Metadata} associated to a value of type {@code BigDecimal}.
 *
 * @see Metadata
 * @see NumberMetadataValue
 * @since 1.0.0
 */
public class NumberMetadata extends Metadata {

  /**
   * Create a new instance of NumberMetadata, a metadata of type {@code MetadataType.NUMBER}.
   *
   * @param code The metadata code
   */
  public NumberMetadata(String code) {
    super(code, MetadataType.NUMBER);
  }
}
