package zeenea.connector.process;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import zeenea.connector.SourceItem;
import zeenea.connector.common.SourceItemReference;

public final class SourceProcess extends SourceItem {

  private final List<SourceItemReference> sourceItemReference;
  private final List<SourceItemReference> targetItemReference;
  private final List<SourceOperation> operations;

  private SourceProcess(Builder builder) {
    super(builder);
    this.sourceItemReference = builder.sourceItemReference;
    this.targetItemReference = builder.targetItemReference;
    this.operations = builder.operations;
  }

  public List<SourceItemReference> getSourceItemReference() {
    return sourceItemReference;
  }

  public List<SourceItemReference> getTargetItemReference() {
    return targetItemReference;
  }

  public List<SourceOperation> getOperations() {
    return operations;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder extends SourceItem.Builder<SourceProcess, Builder> {

    private List<SourceItemReference> sourceItemReference;
    private List<SourceItemReference> targetItemReference;
    private List<SourceOperation> operations;

    private Builder() {}

    public Builder sourceItemReference(List<SourceItemReference> sourceItemReference) {
      this.sourceItemReference = sourceItemReference;
      return this;
    }

    public Builder targetItemReference(List<SourceItemReference> targetItemReference) {
      this.targetItemReference = targetItemReference;
      return this;
    }

    public Builder operations(List<SourceOperation> operations) {
      this.operations = operations;
      return this;
    }

    @Override
    protected SourceProcess performBuild() {
      return new SourceProcess(this);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SourceProcess that = (SourceProcess) o;
    return Objects.equals(sourceItemReference, that.sourceItemReference)
        && Objects.equals(targetItemReference, that.targetItemReference)
        && Objects.equals(operations, that.operations);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sourceItemReference, targetItemReference, operations);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", SourceProcess.class.getSimpleName() + "[", "]")
        .add("operations=" + operations)
        .add("sourceItemReference=" + sourceItemReference)
        .add("targetItemReference=" + targetItemReference)
        .toString();
  }
}
