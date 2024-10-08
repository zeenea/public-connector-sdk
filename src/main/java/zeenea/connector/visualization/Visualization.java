package zeenea.connector.visualization;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import zeenea.connector.Item;
import zeenea.connector.common.ItemReference;
import zeenea.connector.exception.ExceptionUtils;
import zeenea.connector.field.Field;

/** Represents a visualization item. */
public final class Visualization extends Item {

  /** The fields of the visualization. */
  @NotNull private final List<Field> fields;

  /**
   * The list of source datasets associated with the visualization.
   *
   * <p>Used to declare downstream dataset lineage by referencing ItemIdentifier of source datasets.
   */
  @NotNull private final List<ItemReference> sourceDatasets;

  /**
   * Constructs a Visualization instance using the builder.
   *
   * @param builder the builder to construct the Visualization instance
   */
  private Visualization(Builder builder) {
    super(builder);
    ExceptionUtils.requireNonNull("fields", builder.fields);
    ExceptionUtils.requireNonNull("sourceDatasets", builder.sourceDatasets);
    this.fields = List.copyOf(builder.fields);
    this.sourceDatasets = List.copyOf(builder.sourceDatasets);
  }

  /**
   * Gets the fields of the visualization.
   *
   * @return the fields of the visualization
   */
  public @NotNull List<Field> getFields() {
    return fields;
  }

  /**
   * Gets the list of source dataset references.
   *
   * @return the list of source dataset references
   */
  public @NotNull List<ItemReference> getSourceDatasets() {
    return sourceDatasets;
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
    Visualization visualization = (Visualization) o;
    return Objects.equals(getId(), visualization.getId())
        && Objects.equals(getName(), visualization.getName())
        && Objects.equals(getDescription(), visualization.getDescription())
        && Objects.equals(getContacts(), visualization.getContacts())
        && Objects.equals(getProperties(), visualization.getProperties())
        && Objects.equals(fields, visualization.fields)
        && Objects.equals(sourceDatasets, visualization.sourceDatasets);
  }

  /**
   * Returns the hash code of this Visualization.
   *
   * @return the hash code of this Visualization
   */
  @Override
  public int hashCode() {
    return Objects.hash(
        getId(),
        getName(),
        getDescription(),
        getContacts(),
        getProperties(),
        fields,
        sourceDatasets);
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
        + getContacts()
        + ", properties="
        + getProperties()
        + "fields="
        + fields
        + ", sourceDatasets="
        + sourceDatasets
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
    private List<Field> fields = new ArrayList<>();

    /** The list of source dataset references of the visualization. */
    private List<ItemReference> sourceDatasets = new ArrayList<>();

    /**
     * Sets the fields of the visualization.
     *
     * @param fields the fields of the visualization
     * @return the Builder instance
     */
    public Builder fields(@NotNull List<Field> fields) {
      this.fields = List.copyOf(fields);
      return this;
    }

    /**
     * Sets the fields of the visualization.
     *
     * @param fields the fields of the visualization
     * @return the Builder instance
     */
    public Builder fields(Field... fields) {
      this.fields = List.of(fields);
      return this;
    }

    /**
     * Sets the list of source dataset references of the visualization.
     *
     * @param sourceDatasets the list of source dataset references of the visualization
     * @return the Builder instance
     */
    public Builder sourceDatasets(@NotNull List<ItemReference> sourceDatasets) {
      this.sourceDatasets = List.copyOf(sourceDatasets);
      return this;
    }

    /**
     * Sets the list of source dataset references of the visualization.
     *
     * @param sourceDatasets the list of source dataset references of the visualization
     * @return the Builder instance
     */
    public Builder sourceDatasets(ItemReference... sourceDatasets) {
      this.sourceDatasets = List.of(sourceDatasets);
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
