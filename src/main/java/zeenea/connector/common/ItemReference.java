package zeenea.connector.common;

import java.util.Objects;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/** Represents a reference to an item in the Zeenea Data Catalog. */
public final class ItemReference {
  /** The identifier for the item. */
  @NotNull private final ItemIdentifier itemIdentifier;

  /** The data source identifier associated with the item */
  @Nullable private final DataSourceIdentifier dataSourceIdentifier;

  /**
   * Constructs an ItemReference instance using the provided builder.
   *
   * @param builder the builder used to create the ItemReference instance
   */
  private ItemReference(Builder builder) {
    this.itemIdentifier = Objects.requireNonNull(builder.itemIdentifier, "itemIdentifier");
    this.dataSourceIdentifier = builder.dataSourceIdentifier;
  }

  /**
   * Creates a new ItemReference instance with the specified item identifier and connection
   * reference.
   *
   * @param itemIdentifier the identifier for the item
   * @param dataSourceIdentifier the data source identifier associated with the item
   * @return a new ItemReference instance
   */
  public static ItemReference of(
      @NotNull ItemIdentifier itemIdentifier, @Nullable DataSourceIdentifier dataSourceIdentifier) {
    return builder()
        .itemIdentifier(itemIdentifier)
        .dataSourceIdentifier(dataSourceIdentifier)
        .build();
  }

  /**
   * Creates a new ItemReference instance with the specified item identifier, without any connection
   * reference.
   *
   * @param itemIdentifier the identifier for the item
   * @return a new ItemReference instance
   */
  public static ItemReference of(@NotNull ItemIdentifier itemIdentifier) {
    return of(itemIdentifier, null);
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
   * Gets the data source identifier associated with the item, if any.
   *
   * @return an Optional containing the data source identifier associated with the item if present,
   *     otherwise an empty Optional
   */
  public Optional<DataSourceIdentifier> getDataSourceIdentifier() {
    return Optional.ofNullable(dataSourceIdentifier);
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
        && Objects.equals(dataSourceIdentifier, that.dataSourceIdentifier);
  }

  /**
   * Computes the hash code for this ItemReference.
   *
   * @return the hash code of this ItemReference
   */
  @Override
  public int hashCode() {
    return Objects.hash(itemIdentifier, dataSourceIdentifier);
  }

  /**
   * Returns a string representation of this ItemReference.
   *
   * @return a string representation of this ItemReference
   */
  @Override
  public String toString() {
    return "ItemReference{"
        + "itemIdentifier="
        + itemIdentifier
        + ", dataSourceIdentifier="
        + dataSourceIdentifier
        + "}";
  }

  /** Builder class for creating instances of ItemReference. */
  public static class Builder {

    /** The identifier for the item. */
    private ItemIdentifier itemIdentifier;

    /** The data source identifier associated with the item, if any. */
    private DataSourceIdentifier dataSourceIdentifier;

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
     * Sets the identifier for the item.
     *
     * @param itemIdentifier the identifier for the item
     * @return the Builder instance
     */
    public Builder itemIdentifier(IdentificationProperty... itemIdentifier) {
      this.itemIdentifier = ItemIdentifier.of(itemIdentifier);
      return this;
    }

    /**
     * Sets the data source identifier for the item.
     *
     * @param dataSourceIdentifier the data source identifier to set
     * @return the builder instance
     */
    public Builder dataSourceIdentifier(@Nullable DataSourceIdentifier dataSourceIdentifier) {
      this.dataSourceIdentifier = dataSourceIdentifier;
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
