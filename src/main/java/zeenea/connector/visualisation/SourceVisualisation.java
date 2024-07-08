package zeenea.connector.visualisation;

import java.util.List;
import zeenea.connector.SourceItem;
import zeenea.connector.common.SourceItemReference;

public class SourceVisualisation extends SourceItem {

  private final List<SourceVisualisationField> fields;
  private final List<SourceItemReference> linkedDataset;

  private SourceVisualisation(SourceVisualisation.Builder builder) {
    super(builder);
    this.fields = builder.fields;
    this.linkedDataset = builder.linkedDataset;
  }

  public List<SourceVisualisationField> getFields() {
    return fields;
  }

  public List<SourceItemReference> getLinkedDataset() {
    return linkedDataset;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder
      extends SourceItem.Builder<SourceVisualisation, SourceVisualisation.Builder> {

    private List<SourceVisualisationField> fields;
    private List<SourceItemReference> linkedDataset;

    private Builder() {}

    public SourceVisualisation.Builder fields(List<SourceVisualisationField> fields) {
      this.fields = fields;
      return this;
    }

    public SourceVisualisation.Builder linkedDataset(List<SourceItemReference> linkedDataset) {
      this.linkedDataset = linkedDataset;
      return this;
    }

    @Override
    protected SourceVisualisation performBuild() {
      throwIfNull("fields", this.fields);
      throwIfNull("linkedDataset", this.linkedDataset);
      return new SourceVisualisation(this);
    }
  }
}