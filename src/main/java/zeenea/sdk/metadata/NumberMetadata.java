package zeenea.sdk.metadata;

/**
 * Sub-class of {@link Metadata} associated to a value of type {@code BigDecimal}.
 *
 * @see Metadata
 * @see NumberMetadataValue
 * @since 1.0.0
 * @deprecated since 2.0.0, see 'zeenea.connector' package
 */
@Deprecated
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
