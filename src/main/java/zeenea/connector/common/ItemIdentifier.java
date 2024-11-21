package zeenea.connector.common;

import java.util.*;
import org.jetbrains.annotations.NotNull;
import zeenea.connector.exception.ExceptionUtils;

/**
 * Represents an identifier for an item, consisting of a list of identification properties.
 *
 * <pre>Example : <br>
 * {
 *  "identificationProperties": [
 *    { "key": "database", "value": "mydb" },
 *    { "key": "schema", "value": "myschema" },
 *    { "key": "table", "value": "table_name" },
 *  ]
 * }
 * </pre>
 */
public final class ItemIdentifier {

  /** The list of identification properties. */
  @NotNull private final List<IdentificationProperty> identificationProperties;

  /**
   * Private constructor to enforce the use of the builder.
   *
   * @param builder the builder used to create the ItemIdentifier instance
   */
  private ItemIdentifier(Builder builder) {
    ExceptionUtils.requireNonNullOrEmpty(
        "identificationProperties", builder.identificationProperties);
    this.identificationProperties = List.copyOf(builder.identificationProperties);
  }

  /**
   * Creates a new ItemIdentifier instance with the specified list of identification properties.
   *
   * @param identificationProperties the list of identification properties
   * @return a new ItemIdentifier instance
   */
  public static ItemIdentifier of(@NotNull List<IdentificationProperty> identificationProperties) {
    return new Builder().identificationProperties(identificationProperties).build();
  }

  /**
   * Creates a new ItemIdentifier instance with the specified list of identification properties.
   *
   * @param identificationProperties the list of identification properties
   * @return a new ItemIdentifier instance
   */
  public static ItemIdentifier of(IdentificationProperty... identificationProperties) {
    return of(List.of(identificationProperties));
  }

  /**
   * Gets the list of identification properties.
   *
   * @return the list of identification properties
   */
  public @NotNull List<IdentificationProperty> getIdentificationProperties() {
    return identificationProperties;
  }

  /**
   * Creates a new ItemIdentifier with the specified identification property added as a prefix.
   *
   * @param identificationProperty the identification property to add as a prefix
   * @return a new ItemIdentifier instance with the specified prefix
   */
  public ItemIdentifier withPrefix(IdentificationProperty identificationProperty) {
    var idEntries = new ArrayList<>(Collections.singletonList(identificationProperty));
    idEntries.addAll(this.identificationProperties);
    return ItemIdentifier.of(idEntries);
  }

  /**
   * Creates a new ItemIdentifier with the specified identification property added as a prefix.
   *
   * @param key the key of the identification property.
   * @param value the value of the identification property.
   * @return a new ItemIdentifier instance with the specified prefix
   */
  public ItemIdentifier withPrefix(String key, String value) {
    return withPrefix(IdentificationProperty.of(key, value));
  }

  /**
   * Creates a new ItemIdentifier with the specified identification property added as a suffix.
   *
   * @param identificationProperty the identification property to add as a suffix
   * @return a new ItemIdentifier instance with the specified suffix
   */
  public ItemIdentifier withSuffix(IdentificationProperty identificationProperty) {
    var idEntries = new ArrayList<>(this.identificationProperties);
    idEntries.add(identificationProperty);
    return ItemIdentifier.of(idEntries);
  }

  /**
   * Creates a new ItemIdentifier with the specified identification property added as a suffix.
   *
   * @param key the key of the identification property
   * @param value the value of the identification property
   * @return a new ItemIdentifier instance with the specified suffix
   */
  public ItemIdentifier withSuffix(String key, String value) {
    return withSuffix(IdentificationProperty.of(key, value));
  }

  /**
   * Creates a new builder for the ItemIdentifier class.
   *
   * @return a new Builder instance
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Checks if this ItemIdentifier is equal to another object.
   *
   * @param o the object to compare with
   * @return true if this ItemIdentifier is equal to the specified object, otherwise false
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ItemIdentifier that = (ItemIdentifier) o;
    return Objects.equals(identificationProperties, that.identificationProperties);
  }

  /**
   * Computes the hash code for this ItemIdentifier.
   *
   * @return the hash code of this ItemIdentifier
   */
  @Override
  public int hashCode() {
    return Objects.hashCode(identificationProperties);
  }

  /**
   * Returns a string representation of this ItemIdentifier.
   *
   * @return a string representation of this ItemIdentifier
   */
  @Override
  public String toString() {
    return "ItemIdentifier{" + "identificationProperties=" + identificationProperties + "}";
  }

  /** Builder class for creating instances of ItemIdentifier. */
  public static class Builder {

    /** The list of identification properties. */
    private List<IdentificationProperty> identificationProperties = new ArrayList<>();

    /**
     * Set a collection of identification properties to the builder.
     *
     * @param identificationProperties the collection of identification properties to add
     * @return the builder instance
     */
    public Builder identificationProperties(
        @NotNull Collection<IdentificationProperty> identificationProperties) {
      this.identificationProperties = List.copyOf(identificationProperties);
      return this;
    }

    /**
     * Builds and returns an ItemIdentifier instance.
     *
     * @return the created ItemIdentifier instance
     */
    public ItemIdentifier build() {
      return new ItemIdentifier(this);
    }
  }
}
