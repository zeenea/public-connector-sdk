package zeenea.connector.visualization;

import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import zeenea.connector.Item;
import zeenea.connector.common.ItemReference;
import zeenea.connector.exception.ExceptionUtils;

/** Represents a visualization item. */
public final class Visualization extends Item {

  /** The fields of the visualization. */
  @NotNull private final List<VisualizationField> fields;

  /** The linked dataset references of the visualization. */
  @NotNull private final List<ItemReference> linkedDataset;

  /**
   * Constructs a Visualization instance using the builder.
   *
   * @param builder the builder to construct the Visualization instance
   */
  private Visualization(Builder builder) {
    super(builder);
    ExceptionUtils.requireNonNull("fields", builder.fields);
    ExceptionUtils.requireNonNull("linkedDataset", builder.linkedDataset);
    this.fields = List.copyOf(builder.fields);
    this.linkedDataset = List.copyOf(builder.linkedDataset);
  }

  /**
   * Gets the fields of the visualization.
   *
   * @return the fields of the visualization
   */
  public @NotNull List<VisualizationField> getFields() {
    return fields;
  }

  /**
   * Gets the linked dataset references of the visualization.
   *
   * @return the linked dataset references of the visualization
   */
  public @NotNull List<ItemReference> getLinkedDataset() {
    return linkedDataset;
  }

  /**
   * Checks if this Visualization is equal to another object.
   *
   * @param o the object to compare with
   * @return true if this Visualization is equal to the specified object, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Visualization that = (Visualization) o;
    return Objects.equals(fields, that.fields) && Objects.equals(linkedDataset, that.linkedDataset);
  }

  /**
   * Returns the hash code of this Visualization.
   *
   * @return the hash code of this Visualization
   */
  @Override
  public int hashCode() {
    return Objects.hash(fields, linkedDataset);
  }

  /**
   * Returns the string representation of this Visualization.
   *
   * @return the string representation of this Visualization
   */
  @Override
  public String toString() {
    return "Visualization{"
        + "id="
        + getId()
        + ", name='"
        + getName()
        + "', description="
        + getDescription()
        + ", contactRelations="
        + getContactRelations()
        + ", properties="
        + getProperties()
        + "fields="
        + fields
        + ", linkedDataset="
        + linkedDataset
        + "}";
  }

  /**
   * Creates a builder for Visualization.
   *
   * @return a new Builder instance
   */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder class for constructing Visualization instances. */
  public static class Builder extends Item.Builder<Visualization, Builder> {

    /** The fields of the visualization. */
    private List<VisualizationField> fields;

    /** The linked dataset references of the visualization. */
    private List<ItemReference> linkedDataset;

    /**
     * Sets the fields of the visualization.
     *
     * @param fields the fields of the visualization
     * @return the Builder instance
     */
    public Builder fields(@NotNull List<VisualizationField> fields) {
      this.fields = List.copyOf(fields);
      return this;
    }

    /**
     * Sets the linked dataset references of the visualization.
     *
     * @param linkedDataset the linked dataset references of the visualization
     * @return the Builder instance
     */
    public Builder linkedDataset(@NotNull List<ItemReference> linkedDataset) {
      this.linkedDataset = List.copyOf(linkedDataset);
      return this;
    }

    /**
     * Builds and returns a Visualization instance.
     *
     * @return a new Visualization instance
     */
    @Override
    protected Visualization performBuild() {
      return new Visualization(this);
    }
  }
}
