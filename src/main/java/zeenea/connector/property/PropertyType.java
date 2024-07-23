package zeenea.connector.property;

/**
 * The type of the underlying value of a {@link PropertyDefinition}.
 *
 * @see PropertyDefinition
 * @see StringPropertyValue
 * @see NumberPropertyValue
 * @see UrlPropertyValue
 * @see InstantPropertyValue
 * @since 1.0.0
 */
public enum PropertyType {
  STRING,
  NUMBER,
  URL,
  INSTANT
}
