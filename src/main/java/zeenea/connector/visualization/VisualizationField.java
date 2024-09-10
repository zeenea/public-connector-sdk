package zeenea.connector.visualization;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import org.jetbrains.annotations.NotNull;
import zeenea.connector.common.ItemReference;
import zeenea.connector.dataset.Field;

/** Represents a field in a visualization. */
public final class VisualizationField extends Field {

  /** The type of the field. */
  @NotNull private final VisualizationFieldType fieldType;

  /** The list of item references associated with the field. */
  @NotNull private final List<ItemReference> itemReferenceList;

  /**
   * Constructs a VisualizationField instance using the builder.
   *
   * @param builder the builder to construct the VisualizationField instance
   */
  private VisualizationField(Builder builder) {
    super(builder);
    this.fieldType = Objects.requireNonNull(builder.fieldType, "fieldType");
    this.itemReferenceList = builder.itemReferenceList;
  }

  /**
   * Gets the type of the field.
   *
   * @return the type of the field
   */
  public @NotNull VisualizationFieldType getFieldType() {
    return fieldType;
  }

  /**
   * Gets the list of item references associated with the field.
   *
   * @return the list of item references associated with the field
   */
  public List<ItemReference> getSourceItemReferenceList() {
    return itemReferenceList;
  }

  /**
   * Checks if this VisualizationField is equal to another object.
   *
   * @param o the object to compare with
   * @return true if this VisualizationField is equal to the specified object, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    VisualizationField that = (VisualizationField) o;
    return fieldType == that.fieldType && Objects.equals(itemReferenceList, that.itemReferenceList);
  }

  /**
   * Returns the hash code of this VisualizationField.
   *
   * @return the hash code of this VisualizationField
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), fieldType, itemReferenceList);
  }

  /**
   * Returns the string representation of this VisualizationField.
   *
   * @return the string representation of this VisualizationField
   */
  @Override
  public String toString() {
    return new StringJoiner(", ", VisualizationField.class.getSimpleName() + "[", "]")
        .add("fieldType=" + fieldType)
        .add("sourceItemReferenceList=" + itemReferenceList)
        .toString();
  }

  /**
   * Creates a builder for VisualizationField.
   *
   * @return a new Builder instance
   */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder class for constructing VisualizationField instances. */
  public static class Builder extends Field.Builder<VisualizationField, Builder> {

    /** The type of the field. */
    private VisualizationFieldType fieldType;

    /** The list of item references associated with the field. */
    private List<ItemReference> itemReferenceList = List.of();

    /** Constructs a Builder instance. */
    private Builder() {}

    /**
     * Sets the type of the field.
     *
     * @param fieldType the type of the field
     * @return the Builder instance
     */
    public Builder fieldType(VisualizationFieldType fieldType) {
      this.fieldType = fieldType;
      return this;
    }

    /**
     * Sets the list of item references associated with the field.
     *
     * @param itemReferenceList the list of item references associated with the field
     * @return the Builder instance
     */
    public Builder sourceItemReferenceList(List<ItemReference> itemReferenceList) {
      this.itemReferenceList = itemReferenceList;
      return this;
    }

    /**
     * Builds and returns a VisualizationField instance.
     *
     * @return a new VisualizationField instance
     */
    @Override
    public VisualizationField build() {
      return new VisualizationField(this);
    }
  }
}
