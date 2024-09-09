package zeenea.connector.common;

import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/** Represents a reference to an item in the Zeenea Data Catalog. */
public final class ItemReference {
  /** The identifier for the item. */
  @NotNull private final ItemIdentifier itemIdentifier;

  /** The connection reference associated with the item, if any. */
  @Nullable private final ConnectionReference connectionReference;

  /**
   * Constructs an ItemReference instance using the provided builder.
   *
   * @param builder the builder used to create the ItemReference instance
   */
  private ItemReference(Builder builder) {
    this.itemIdentifier = Objects.requireNonNull(builder.itemIdentifier, "itemIdentifier");
    this.connectionReference = builder.connectionReference;
  }

  /**
   * Creates a new ItemReference instance with the specified item identifier and connection
   * reference.
   *
   * @param itemIdentifier the identifier for the item
   * @param connectionReference the connection reference associated with the item, if any
   * @return a new ItemReference instance
   */
  public static ItemReference of(
      @NotNull ItemIdentifier itemIdentifier, @Nullable ConnectionReference connectionReference) {
    return builder()
        .itemIdentifier(itemIdentifier)
        .connectionReference(connectionReference)
        .build();
  }

  /**
   * Gets the identifier for the item.
   *
   * @return the identifier for the item
   */
  public @NotNull ItemIdentifier getItemIdentifier() {
    return itemIdentifier;
  }

  /**
   * Gets the connection reference associated with the item, if any.
   *
   * @return an Optional containing the connection reference if present, otherwise an empty Optional
   */
  public Optional<ConnectionReference> getConnectionReference() {
    return Optional.ofNullable(connectionReference);
  }

  /**
   * Creates a new builder for the ItemReference class.
   *
   * @return a new Builder instance
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Checks if this ItemReference is equal to another object.
   *
   * @param o the object to compare with
   * @return true if this ItemReference is equal to the specified object, otherwise false
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ItemReference that = (ItemReference) o;
    return Objects.equals(itemIdentifier, that.itemIdentifier)
        && Objects.equals(connectionReference, that.connectionReference);
  }

  /**
   * Computes the hash code for this ItemReference.
   *
   * @return the hash code of this ItemReference
   */
  @Override
  public int hashCode() {
    return Objects.hash(itemIdentifier, connectionReference);
  }

  /**
   * Returns a string representation of this ItemReference.
   *
   * @return a string representation of this ItemReference
   */
  @Override
  public String toString() {
    return new StringJoiner(", ", ItemReference.class.getSimpleName() + "[", "]")
        .add("connectionAlias='" + connectionReference + "'")
        .add("itemIdentifier=" + itemIdentifier)
        .toString();
  }

  /** Builder class for creating instances of ItemReference. */
  public static class Builder {

    /** The identifier for the item. */
    private ItemIdentifier itemIdentifier;

    /** The connection reference associated with the item, if any. */
    private ConnectionReference connectionReference;

    /**
     * Sets the identifier for the item.
     *
     * @param itemIdentifier the identifier for the item
     * @return the Builder instance
     */
    public Builder itemIdentifier(@Nullable ItemIdentifier itemIdentifier) {
      this.itemIdentifier = itemIdentifier;
      return this;
    }

    /**
     * Sets the connection reference for the item.
     *
     * @param connectionReference the connection reference to set
     * @return the builder instance
     */
    public Builder connectionReference(@Nullable ConnectionReference connectionReference) {
      this.connectionReference = connectionReference;
      return this;
    }

    /**
     * Builds and returns an ItemReference instance.
     *
     * @return the created ItemReference instance
     */
    public ItemReference build() {
      return new ItemReference(this);
    }
  }
}
