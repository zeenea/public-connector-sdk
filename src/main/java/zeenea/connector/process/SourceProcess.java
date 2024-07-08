package zeenea.connector.process;

import java.util.List;
import zeenea.connector.SourceItem;
import zeenea.connector.common.SourceItemReference;

public final class SourceProcess extends SourceItem {

  private final List<SourceItemReference> sourceItemReference;
  private final List<SourceItemReference> targetItemReference;

  private SourceProcess(Builder builder) {
    super(builder);
    this.sourceItemReference = builder.sourceItemReference;
    this.targetItemReference = builder.targetItemReference;
  }

  public List<SourceItemReference> getSourceItemReference() {
    return sourceItemReference;
  }

  public List<SourceItemReference> getTargetItemReference() {
    return targetItemReference;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder extends SourceItem.Builder<SourceProcess, Builder> {

    private List<SourceItemReference> sourceItemReference;
    private List<SourceItemReference> targetItemReference;

    private Builder() {}

    public SourceProcess.Builder sourceItemReference(
        List<SourceItemReference> sourceItemReference) {
      this.sourceItemReference = sourceItemReference;
      return this;
    }

    public SourceProcess.Builder targetItemReference(
        List<SourceItemReference> targetItemReference) {
      this.targetItemReference = targetItemReference;
      return this;
    }

    @Override
    protected SourceProcess performBuild() {
      throwIfNull("sourceItemReference", this.sourceItemReference);
      throwIfNull("targetItemReference", this.targetItemReference);
      return new SourceProcess(this);
    }
  }
}
