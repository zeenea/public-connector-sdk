package zeenea.connector.common;

import java.util.*;
import org.jetbrains.annotations.NotNull;
import zeenea.connector.exception.ExceptionUtils;

/**
 * Represents an identifier for an data source, consisting of a list of identification properties.
 *
 * <pre>Example : <br>
 * {
 *  "identificationProperties": [
 *    { "key": "host", "value": "localhost" },
 *    { "key": "port", "value": "8080" },
 *    { "key": "database", "value": "mydb" },
 *  ]
 * }
 * </pre>
 */
public final class DataSourceIdentifier {

  /** The list of identification properties. */
  @NotNull private final List<IdentificationProperty> identificationProperties;

  /**
   * Private constructor to enforce the use of the builder.
   *
   * @param builder the builder used to create the DataSourceIdentifier instance
   */
  private DataSourceIdentifier(Builder builder) {
    ExceptionUtils.requireNonNullOrEmpty(
        "identificationProperties", builder.identificationProperties);
    this.identificationProperties = List.copyOf(builder.identificationProperties);
  }

  /**
   * Creates a new DataSourceIdentifier instance with the specified list of identification
   * properties.
   *
   * @param identificationProperties the list of identification properties
   * @return a new DataSourceIdentifier instance
   */
  public static DataSourceIdentifier of(
      @NotNull List<IdentificationProperty> identificationProperties) {
    return new Builder().identificationProperties(identificationProperties).build();
  }

  /**
   * Creates a new DataSourceIdentifier instance with the specified list of identification
   * properties.
   *
   * @param identificationProperties the list of identification properties
   * @return a new DataSourceIdentifier instance
   */
  public static DataSourceIdentifier of(IdentificationProperty... identificationProperties) {
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
   * Creates a new builder for the DataSourceIdentifier class.
   *
   * @return a new Builder instance
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Checks if this DataSourceIdentifier is equal to another object.
   *
   * @param o the object to compare with
   * @return true if this DataSourceIdentifier is equal to the specified object, otherwise false
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DataSourceIdentifier that = (DataSourceIdentifier) o;
    return Objects.equals(identificationProperties, that.identificationProperties);
  }

  /**
   * Computes the hash code for this DataSourceIdentifier.
   *
   * @return the hash code of this DataSourceIdentifier
   */
  @Override
  public int hashCode() {
    return Objects.hashCode(identificationProperties);
  }

  /**
   * Returns a string representation of this DataSourceIdentifier.
   *
   * @return a string representation of this DataSourceIdentifier
   */
  @Override
  public String toString() {
    return "DataSourceIdentifier{" + "identificationProperties=" + identificationProperties + "}";
  }

  /** Builder class for creating instances of DataSourceIdentifier. */
  public static class Builder {

    /** The list of identification properties. */
    private List<IdentificationProperty> identificationProperties;

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
     * Builds and returns an DataSourceIdentifier instance.
     *
     * @return the created DataSourceIdentifier instance
     */
    public DataSourceIdentifier build() {
      return new DataSourceIdentifier(this);
    }
  }
}
