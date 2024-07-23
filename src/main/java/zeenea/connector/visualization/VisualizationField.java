package zeenea.connector.visualization;

import static zeenea.connector.exception.ExceptionUtils.throwIfNull;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import zeenea.connector.common.ItemReference;
import zeenea.connector.dataset.Field;

public class VisualizationField extends Field {

  private final VisualizationFieldType fieldType;
  private final List<ItemReference> itemReferenceList;

  private VisualizationField(VisualizationField.Builder builder) {
    super(builder);
    this.fieldType = builder.fieldType;
    this.itemReferenceList = builder.itemReferenceList;
  }

  public VisualizationFieldType getFieldType() {
    return fieldType;
  }

  public List<ItemReference> getSourceItemReferenceList() {
    return itemReferenceList;
  }

  public static VisualizationField.Builder builder() {
    return new VisualizationField.Builder();
  }

  public static class Builder extends Field.Builder<VisualizationField, Builder> {

    private VisualizationFieldType fieldType;
    private List<ItemReference> itemReferenceList;

    private Builder() {}

    public VisualizationField.Builder fieldType(VisualizationFieldType fieldType) {
      this.fieldType = fieldType;
      return this;
    }

    public VisualizationField.Builder sourceItemReferenceList(
        List<ItemReference> itemReferenceList) {
      this.itemReferenceList = itemReferenceList;
      return this;
    }

    @Override
    protected VisualizationField build() {
      throwIfNull("fieldType", this.fieldType);
      throwIfNull("sourceItemReferenceList", this.itemReferenceList);
      return new VisualizationField(this);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    VisualizationField that = (VisualizationField) o;
    return fieldType == that.fieldType && Objects.equals(itemReferenceList, that.itemReferenceList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), fieldType, itemReferenceList);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", VisualizationField.class.getSimpleName() + "[", "]")
        .add("fieldType=" + fieldType)
        .add("sourceItemReferenceList=" + itemReferenceList)
        .toString();
  }
}
