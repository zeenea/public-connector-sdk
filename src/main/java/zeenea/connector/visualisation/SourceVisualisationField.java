package zeenea.connector.visualisation;

import java.util.List;
import zeenea.connector.common.SourceItemReference;
import zeenea.connector.dataset.SourceField;

public class SourceVisualisationField extends SourceField {

  private final SourceVisualisationFieldType fieldType;
  private final List<SourceItemReference> sourceItemReferenceList;

  public SourceVisualisationField(SourceVisualisationField.Builder builder) {
    super(builder);
    this.fieldType = builder.fieldType;
    this.sourceItemReferenceList = builder.sourceItemReferenceList;
  }

  public SourceVisualisationFieldType getFieldType() {
    return fieldType;
  }

  public List<SourceItemReference> getSourceItemReferenceList() {
    return sourceItemReferenceList;
  }

  public static SourceVisualisationField.Builder builder() {
    return new SourceVisualisationField.Builder();
  }

  public static class Builder extends SourceField.Builder<SourceVisualisationField, Builder> {

    private SourceVisualisationFieldType fieldType;
    private List<SourceItemReference> sourceItemReferenceList;

    private Builder() {}

    public SourceVisualisationField.Builder fieldType(SourceVisualisationFieldType fieldType) {
      this.fieldType = fieldType;
      return this;
    }

    public SourceVisualisationField.Builder sourceItemReferenceList(
        List<SourceItemReference> sourceItemReferenceList) {
      this.sourceItemReferenceList = sourceItemReferenceList;
      return this;
    }

    @Override
    protected SourceVisualisationField build() {
      throwIfNull("fieldType", this.fieldType);
      throwIfNull("sourceItemReferenceList", this.sourceItemReferenceList);
      return new SourceVisualisationField(this);
    }
  }
}