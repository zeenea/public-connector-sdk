package zeenea.connector.common;

import java.util.*;
import org.jetbrains.annotations.NotNull;
import zeenea.connector.exception.ExceptionUtils;

/** Represents an identifier for an item, consisting of a list of identification properties. */
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
   * Gets the list of identification properties.
   *
   * @return the list of identification properties
   */
  public @NotNull List<IdentificationProperty> getIdentificationProperties() {
    return identificationProperties;
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
    return new StringJoiner(", ", ItemIdentifier.class.getSimpleName() + "[", "]")
        .add("identificationProperties=" + identificationProperties)
        .toString();
  }

  /** Builder class for creating instances of ItemIdentifier. */
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
     * Builds and returns an ItemIdentifier instance.
     *
     * @return the created ItemIdentifier instance
     */
    public ItemIdentifier build() {
      return new ItemIdentifier(this);
    }
  }
}
