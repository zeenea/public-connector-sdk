package zeenea.connector.common;

import java.util.*;
import org.jetbrains.annotations.NotNull;
import zeenea.connector.exception.ExceptionUtils;

/**
 * Represents an ordered list of labels for an item, consisting of a list of identification
 * properties.
 *
 * <p>LabelIdentifier don't have to be unique for each item in the Zeenea Data Catalog.
 *
 * <p>This label list will generate import hierarchy in Zeenea Studio import modal view with values.
 * It must be not empty to display importable items in the Studio.
 *
 * <pre>Example : <br>
 * {
 *  "identificationProperties": [
 *    { "key": "workspace_name", "value": "my_workspace" },
 *    { "key": "report_name", "value": "my_report" },
 *  ]
 * }
 * </pre>
 */
public final class LabelIdentifier {

  /** The list of label identification properties. */
  @NotNull private final List<IdentificationProperty> identificationProperties;

  /**
   * Private constructor to enforce the use of the builder.
   *
   * @param builder the builder used to create the LabelIdentifier instance
   */
  private LabelIdentifier(Builder builder) {
    ExceptionUtils.requireNonNullOrEmpty(
        "identificationProperties", builder.identificationProperties);
    this.identificationProperties = List.copyOf(builder.identificationProperties);
  }

  /**
   * Creates a new LabelIdentifier instance with the specified list of identification properties.
   *
   * @param identificationProperties the list of identification properties
   * @return a new LabelIdentifier instance
   */
  public static LabelIdentifier of(@NotNull List<IdentificationProperty> identificationProperties) {
    return new Builder().identificationProperties(identificationProperties).build();
  }

  /**
   * Creates a new LabelIdentifier instance with the specified list of identification properties.
   *
   * @param identificationProperties the list of identification properties
   * @return a new LabelIdentifier instance
   */
  public static LabelIdentifier of(IdentificationProperty... identificationProperties) {
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
   * Creates a new LabelIdentifier with the specified identification property added as a prefix.
   *
   * @param identificationProperty the identification property to add as a prefix
   * @return a new LabelIdentifier instance with the specified prefix
   */
  public LabelIdentifier withPrefix(IdentificationProperty identificationProperty) {
    var idEntries = new ArrayList<>(Collections.singletonList(identificationProperty));
    idEntries.addAll(this.identificationProperties);
    return LabelIdentifier.of(idEntries);
  }

  /**
   * Creates a new LabelIdentifier with the specified identification property added as a prefix.
   *
   * @param key the key of the identification property.
   * @param value the value of the identification property.
   * @return a new LabelIdentifier instance with the specified prefix
   */
  public LabelIdentifier withPrefix(String key, String value) {
    return withPrefix(IdentificationProperty.of(key, value));
  }

  /**
   * Creates a new LabelIdentifier with the specified identification property added as a suffix.
   *
   * @param identificationProperty the identification property to add as a suffix
   * @return a new LabelIdentifier instance with the specified suffix
   */
  public LabelIdentifier withSuffix(IdentificationProperty identificationProperty) {
    var idEntries = new ArrayList<>(this.identificationProperties);
    idEntries.add(identificationProperty);
    return LabelIdentifier.of(idEntries);
  }

  /**
   * Creates a new LabelIdentifier with the specified identification property added as a suffix.
   *
   * @param key the key of the identification property
   * @param value the value of the identification property
   * @return a new LabelIdentifier instance with the specified suffix
   */
  public LabelIdentifier withSuffix(String key, String value) {
    return withSuffix(IdentificationProperty.of(key, value));
  }

  /**
   * Creates a new builder for the LabelIdentifier class.
   *
   * @return a new Builder instance
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Checks if this LabelIdentifier is equal to another object.
   *
   * @param o the object to compare with
   * @return true if this LabelIdentifier is equal to the specified object, otherwise false
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    LabelIdentifier that = (LabelIdentifier) o;
    return Objects.equals(identificationProperties, that.identificationProperties);
  }

  /**
   * Computes the hash code for this LabelIdentifier.
   *
   * @return the hash code of this LabelIdentifier
   */
  @Override
  public int hashCode() {
    return Objects.hashCode(identificationProperties);
  }

  /**
   * Returns a string representation of this LabelIdentifier.
   *
   * @return a string representation of this LabelIdentifier
   */
  @Override
  public String toString() {
    return "LabelIdentifier{" + "identificationProperties=" + identificationProperties + "}";
  }

  /** Builder class for creating instances of LabelIdentifier. */
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
     * Builds and returns an LabelIdentifier instance.
     *
     * @return the created LabelIdentifier instance
     */
    public LabelIdentifier build() {
      return new LabelIdentifier(this);
    }
  }
}
