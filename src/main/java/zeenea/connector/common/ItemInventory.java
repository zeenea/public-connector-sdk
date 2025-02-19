package zeenea.connector.common;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;

/** Represents an inventory item, identified by an item identifier and eventually with a label. */
public final class ItemInventory {
  /** The unique identifier for the item. */
  @NotNull private final ItemIdentifier itemIdentifier;

  /**
   * Ordered list of labels associated with the inventory item.
   *
   * <p>This human-readable label list will be displayed in Zeenea Studio import modal view.
   *
   * <p>If empty, item identifier will be used in Zeenea Studio import modal view.
   */
  @NotNull private final LabelIdentifier labelIdentifier;

  /**
   * Constructs an ItemInventory instance using the provided builder.
   *
   * @param builder the builder used to create the ItemInventory instance
   */
  private ItemInventory(Builder builder) {
    this.itemIdentifier = Objects.requireNonNull(builder.itemIdentifier, "itemIdentifier");
    this.labelIdentifier = Objects.requireNonNull(builder.labelIdentifier, "labelIdentifier");
  }

  /**
   * Creates a new ItemInventory instance with the specified item identifier and label path.
   *
   * @param itemIdentifier the identifier for the item
   * @param labelPath the path of labels associated with the item
   * @return a new ItemInventory instance
   * @deprecated since 2.3.0
   */
  @Deprecated(
      since = "Deprecated since version 2.3.0. Scheduled for removal in version 3.0.0.",
      forRemoval = true)
  public static ItemInventory of(
      @NotNull ItemIdentifier itemIdentifier, @NotNull List<String> labelPath) {
    return builder().itemIdentifier(itemIdentifier).labels(labelPath).build();
  }

  /**
   * Creates a new ItemInventory instance with the specified item and label identifiers.
   *
   * @param itemIdentifier the identifier for the item
   * @param labelIdentifier the label identifier associated with the item
   * @return a new ItemInventory instance
   */
  public static ItemInventory of(
      @NotNull ItemIdentifier itemIdentifier, @NotNull LabelIdentifier labelIdentifier) {
    return builder().itemIdentifier(itemIdentifier).labelIdentifier(labelIdentifier).build();
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
   * @deprecated since 2.3.0, use getLabelIdentifier() instead
   */
  @Deprecated(
      since =
          "Deprecated since version 2.3.0, use getLabelIdentifier() instead. Scheduled for removal in version 3.0.0.",
      forRemoval = true)
  public @NotNull List<String> getLabels() {
    return labelIdentifier.getIdentificationProperties().stream()
        .map(IdentificationProperty::getValue)
        .collect(Collectors.toUnmodifiableList());
  }

  /**
   * Ordered list of labels associated with the inventory item.
   *
   * <p>This human-readable label list will be displayed in Zeenea Studio import modal view.
   *
   * <p>If empty, item identifier will be used in Zeenea Studio import modal view.
   *
   * @return the label identifier associated with the item
   */
  public @NotNull LabelIdentifier getLabelIdentifier() {
    return labelIdentifier;
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
        && Objects.equals(labelIdentifier, that.labelIdentifier);
  }

  /**
   * Computes the hash code for this ItemInventory.
   *
   * @return the hash code of this ItemInventory
   */
  @Override
  public int hashCode() {
    return Objects.hash(itemIdentifier, labelIdentifier);
  }

  /**
   * Returns a string representation of this ItemInventory.
   *
   * @return a string representation of this ItemInventory
   */
  @Override
  public String toString() {
    return "ItemInventory{"
        + "itemIdentifier="
        + itemIdentifier
        + ", labelIdentifier="
        + labelIdentifier
        + "}";
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

    private ItemIdentifier itemIdentifier;

    private LabelIdentifier labelIdentifier;

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
     * @param identificationProperty the identifier for the item
     * @return the builder instance
     */
    public Builder itemIdentifier(IdentificationProperty identificationProperty) {
      this.itemIdentifier = ItemIdentifier.of(identificationProperty);
      return this;
    }

    /**
     * Set a list of labels for the builder.
     *
     * <p>Retro-compatibility is ensured by generating label keys automatically with "label_" and
     * incremented by the index of list.
     *
     * <p>Examples : label_1, label_2, label_3...
     *
     * @param labels the list of labels to add
     * @return the builder instance
     * @deprecated since 2.3.0, use labelIdentifier() instead
     */
    @Deprecated(
        since =
            "Deprecated since version 2.3.0, use labelIdentifier() instead. Scheduled for removal in version 3.0.0.",
        forRemoval = true)
    public Builder labels(@NotNull List<String> labels) {
      AtomicInteger index = new AtomicInteger(1);
      this.labelIdentifier =
          LabelIdentifier.of(
              labels.stream()
                  .map(
                      label -> IdentificationProperty.of("label_" + index.getAndIncrement(), label))
                  .collect(Collectors.toUnmodifiableList()));
      return this;
    }

    /**
     * Set a list of labels for the builder.
     *
     * <p>Retro-compatibility is ensured by generating label keys automatically with "label_" and
     * incremented by the index of list.
     *
     * <p>Examples : label_1, label_2, label_3...
     *
     * @param labels the list of labels to add
     * @return the builder instance
     * @deprecated since 2.3.0, use labelIdentifier() instead
     */
    @Deprecated(
        since =
            "Deprecated since version 2.3.0, use labelIdentifier() instead. Scheduled for removal in version 3.0.0.",
        forRemoval = true)
    public Builder labels(String... labels) {
      AtomicInteger index = new AtomicInteger(1);
      this.labelIdentifier =
          LabelIdentifier.of(
              Stream.of(labels)
                  .map(
                      label -> IdentificationProperty.of("label_" + index.getAndIncrement(), label))
                  .collect(Collectors.toUnmodifiableList()));
      return this;
    }

    /**
     * Sets the label identifier for the builder.
     *
     * @param labelIdentifier the labels for the item
     * @return the builder instance
     */
    public Builder labelIdentifier(@NotNull LabelIdentifier labelIdentifier) {
      this.labelIdentifier = labelIdentifier;
      return this;
    }

    /**
     * Sets the label identifier for the builder.
     *
     * @param identificationProperty the labels for the item
     * @return the builder instance
     */
    public Builder labelIdentifier(IdentificationProperty identificationProperty) {
      this.labelIdentifier = LabelIdentifier.of(identificationProperty);
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
