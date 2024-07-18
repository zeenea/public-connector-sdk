package zeenea.connector.visualization;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import zeenea.connector.SourceItem;
import zeenea.connector.common.ItemReference;

public final class SourceVisualization extends SourceItem {

  private final List<SourceVisualizationField> fields;
  private final List<ItemReference> linkedDataset;

  private SourceVisualization(SourceVisualization.Builder builder) {
    super(builder);
    this.fields = builder.fields;
    this.linkedDataset = builder.linkedDataset;
  }

  public List<SourceVisualizationField> getFields() {
    return fields;
  }

  public List<ItemReference> getLinkedDataset() {
    return linkedDataset;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder extends SourceItem.Builder<SourceVisualization, Builder> {

    private List<SourceVisualizationField> fields;
    private List<ItemReference> linkedDataset;

    private Builder() {}

    public SourceVisualization.Builder fields(List<SourceVisualizationField> fields) {
      this.fields = fields;
      return this;
    }

    public SourceVisualization.Builder linkedDataset(List<ItemReference> linkedDataset) {
      this.linkedDataset = linkedDataset;
      return this;
    }

    @Override
    protected SourceVisualization performBuild() {
      throwIfNull("fields", this.fields);
      throwIfNull("linkedDataset", this.linkedDataset);
      return new SourceVisualization(this);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SourceVisualization that = (SourceVisualization) o;
    return Objects.equals(fields, that.fields) && Objects.equals(linkedDataset, that.linkedDataset);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fields, linkedDataset);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", SourceVisualization.class.getSimpleName() + "[", "]")
        .add("fields=" + fields)
        .add("linkedDataset=" + linkedDataset)
        .toString();
  }
}
