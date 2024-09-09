package zeenea.connector.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import org.jetbrains.annotations.NotNull;
import zeenea.connector.exception.ExceptionUtils;

/**
 * Represents an inventory of items identified by an ItemIdentifier and associated with a label
 * path.
 */
public final class ItemInventory {
  /** The identifier for the item. */
  @NotNull private final ItemIdentifier itemIdentifier;

  /** The path of labels associated with the item. */
  @NotNull private final List<String> labelPath;

  /**
   * Constructs an ItemInventory instance using the provided builder.
   *
   * @param builder the builder used to create the ItemInventory instance
   */
  private ItemInventory(Builder builder) {
    ExceptionUtils.requireNonNull("label", builder.labelPath);
    this.itemIdentifier = Objects.requireNonNull(builder.itemIdentifier, "itemIdentifier");
    this.labelPath = List.copyOf(builder.labelPath);
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
    return new ItemInventory.Builder(itemIdentifier).addLabels(labelPath).build();
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
   * Gets the path of labels associated with the item.
   *
   * @return the path of labels associated with the item
   */
  public @NotNull List<String> getLabelPath() {
    return labelPath;
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
        && Objects.equals(labelPath, that.labelPath);
  }

  /**
   * Computes the hash code for this ItemInventory.
   *
   * @return the hash code of this ItemInventory
   */
  @Override
  public int hashCode() {
    return Objects.hash(itemIdentifier, labelPath);
  }

  /**
   * Returns a string representation of this ItemInventory.
   *
   * @return a string representation of this ItemInventory
   */
  @Override
  public String toString() {
    return new StringJoiner(", ", ItemInventory.class.getSimpleName() + "[", "]")
        .add("itemIdentifier=" + itemIdentifier)
        .add("labelPath=" + labelPath)
        .toString();
  }

  /**
   * Creates a new builder for the ItemInventory class.
   *
   * @param itemIdentifier the identifier for the item
   * @return a new Builder instance
   */
  public static Builder builder(@NotNull ItemIdentifier itemIdentifier) {
    return new Builder(itemIdentifier);
  }

  /** Builder class for creating instances of ItemInventory. */
  public static class Builder {

    /** The identifier for the item. */
    private final ItemIdentifier itemIdentifier;

    /** The path of labels associated with the item. */
    private final List<String> labelPath = new ArrayList<>();

    /**
     * Constructs a Builder instance with the specified item identifier.
     *
     * @param itemIdentifier the identifier for the item
     */
    private Builder(ItemIdentifier itemIdentifier) {
      this.itemIdentifier = itemIdentifier;
    }

    /**
     * Adds a label to the builder.
     *
     * @param label the label to add
     * @return the builder instance
     */
    public Builder addLabel(@NotNull String label) {
      this.labelPath.add(label);
      return this;
    }

    /**
     * Adds a list of labels to the builder.
     *
     * @param labels the list of labels to add
     * @return the builder instance
     */
    public Builder addLabels(@NotNull List<String> labels) {
      this.labelPath.addAll(labels);
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
