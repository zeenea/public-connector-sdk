package zeenea.connector.property;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Base class for all kinds of metadata. A metadata is a definition of an element of the metamodel
 * valued by the data source, for an item. Metadata is qualified by type and code.
 *
 * @see PropertyType
 * @see PropertyValue
 * @since 1.0.0
 */
public abstract class PropertyDefinition {

  private final String code;
  private final PropertyType type;

  PropertyDefinition(String code, PropertyType type) {
    this.code = code;
    this.type = type;
  }

  /**
   * Get the code of the metadata.
   *
   * @return The code of the metadata
   */
  public String getCode() {
    return code;
  }

  /**
   * Get the type of the metadata.
   *
   * @return The type of the metadata
   */
  public PropertyType getType() {
    return type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PropertyDefinition sourcePropertyDefinition = (PropertyDefinition) o;
    return Objects.equals(code, sourcePropertyDefinition.code)
        && type == sourcePropertyDefinition.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, type);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", PropertyDefinition.class.getSimpleName() + "[", "]")
        .add("code='" + code + "'")
        .add("type=" + type)
        .toString();
  }
}
