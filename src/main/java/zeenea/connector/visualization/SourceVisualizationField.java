package zeenea.connector.visualization;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import zeenea.connector.common.ItemReference;
import zeenea.connector.dataset.SourceField;

public class SourceVisualizationField extends SourceField {

  private final SourceVisualizationFieldType fieldType;
  private final List<ItemReference> itemReferenceList;

  public SourceVisualizationField(SourceVisualizationField.Builder builder) {
    super(builder);
    this.fieldType = builder.fieldType;
    this.itemReferenceList = builder.itemReferenceList;
  }

  public SourceVisualizationFieldType getFieldType() {
    return fieldType;
  }

  public List<ItemReference> getSourceItemReferenceList() {
    return itemReferenceList;
  }

  public static SourceVisualizationField.Builder builder() {
    return new SourceVisualizationField.Builder();
  }

  public static class Builder extends SourceField.Builder<SourceVisualizationField, Builder> {

    private SourceVisualizationFieldType fieldType;
    private List<ItemReference> itemReferenceList;

    private Builder() {}

    public SourceVisualizationField.Builder fieldType(SourceVisualizationFieldType fieldType) {
      this.fieldType = fieldType;
      return this;
    }

    public SourceVisualizationField.Builder sourceItemReferenceList(
        List<ItemReference> itemReferenceList) {
      this.itemReferenceList = itemReferenceList;
      return this;
    }

    @Override
    protected SourceVisualizationField build() {
      throwIfNull("fieldType", this.fieldType);
      throwIfNull("sourceItemReferenceList", this.itemReferenceList);
      return new SourceVisualizationField(this);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    SourceVisualizationField that = (SourceVisualizationField) o;
    return fieldType == that.fieldType && Objects.equals(itemReferenceList, that.itemReferenceList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), fieldType, itemReferenceList);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", SourceVisualizationField.class.getSimpleName() + "[", "]")
        .add("fieldType=" + fieldType)
        .add("sourceItemReferenceList=" + itemReferenceList)
        .toString();
  }
}
