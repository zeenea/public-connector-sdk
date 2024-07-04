package zeenea.connector.metadata;

/**
 * The type of the underlying value of a {@link Metadata}.
 *
 * @see Metadata
 * @see StringMetadataValue
 * @see NumberMetadataValue
 * @see UrlMetadataValue
 * @see InstantMetadataValue
 * @since 1.0.0
 */
public enum MetadataType {
  STRING,
  NUMBER,
  URL,
  INSTANT
}
