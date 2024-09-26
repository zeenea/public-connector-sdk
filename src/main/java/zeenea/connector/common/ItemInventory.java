package zeenea.connector.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import zeenea.connector.exception.ExceptionUtils;

/**
 * Represents an inventory of items identified by an ItemIdentifier and associated with a label
 * path.
 */
public final class ItemInventory {
  /** The identifier for the item. */
  @NotNull private final ItemIdentifier itemIdentifier;

  /** The list of labels associated with the item. */
  @NotNull private final List<String> labels;

  /**
   * Constructs an ItemInventory instance using the provided builder.
   *
   * @param builder the builder used to create the ItemInventory instance
   */
  private ItemInventory(Builder builder) {
    ExceptionUtils.requireNonNull("labels", builder.labels);
    this.itemIdentifier = Objects.requireNonNull(builder.itemIdentifier, "itemIdentifier");
    this.labels = List.copyOf(builder.labels);
  }

  /**
   * Creates a new ItemInventory instance with the specified item identifier and label path.
   *
   * @param itemIdentifier the identifier for the item
   * @param labelPath the path of labels associated with the item
   * @return a new ItemInventory instance
   */
  public static ItemInventory of(
      @NotNull ItemIdentifier itemIdentifier, @NotNull List<String> labelPath) {
    return builder().itemIdentifier(itemIdentifier).labels(labelPath).build();
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
   * Gets the list of labels associated with the item.
   *
   * @return the list of labels associated with the item
   */
  public @NotNull List<String> getLabels() {
    return labels;
  }

  /**
   * Checks if this ItemInventory is equal to another object.
   *
   * @param o the object to compare with
   * @return true if this ItemInventory is equal to the specified object, otherwise false
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ItemInventory that = (ItemInventory) o;
    return Objects.equals(itemIdentifier, that.itemIdentifier)
        && Objects.equals(labels, that.labels);
  }

  /**
   * Computes the hash code for this ItemInventory.
   *
   * @return the hash code of this ItemInventory
   */
  @Override
  public int hashCode() {
    return Objects.hash(itemIdentifier, labels);
  }

  /**
   * Returns a string representation of this ItemInventory.
   *
   * @return a string representation of this ItemInventory
   */
  @Override
  public String toString() {
    return "ItemInventory{" + "itemIdentifier=" + itemIdentifier + ", labels=" + labels + "}";
  }

  /**
   * Creates a new builder for the ItemInventory class.
   *
   * @return a new Builder instance
   */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder class for creating instances of ItemInventory. */
  public static class Builder {

    /** The identifier for the item. */
    private ItemIdentifier itemIdentifier;

    /** The path of labels associated with the item. */
    private List<String> labels = new ArrayList<>();

    /**
     * Sets the item identifier for the builder.
     *
     * @param itemIdentifier the identifier for the item
     * @return the builder instance
     */
    public Builder itemIdentifier(@NotNull ItemIdentifier itemIdentifier) {
      this.itemIdentifier = itemIdentifier;
      return this;
    }

    /**
     * Sets the item identifier for the builder.
     *
     * @param itemIdentifier the identifier for the item
     * @return the builder instance
     */
    public Builder itemIdentifier(IdentificationProperty itemIdentifier) {
      this.itemIdentifier = ItemIdentifier.of(itemIdentifier);
      return this;
    }

    /**
     * Set a list of labels for the builder.
     *
     * @param labels the list of labels to add
     * @return the builder instance
     */
    public Builder labels(@NotNull List<String> labels) {
      this.labels = List.copyOf(labels);
      return this;
    }

    /**
     * Set a list of labels for the builder.
     *
     * @param labels the list of labels to add
     * @return the builder instance
     */
    public Builder labels(String... labels) {
      this.labels = List.of(labels);
      return this;
    }

    /**
     * Builds and returns an ItemInventory instance.
     *
     * @return the created ItemInventory instance
     */
    public ItemInventory build() {
      return new ItemInventory(this);
    }
  }
}
