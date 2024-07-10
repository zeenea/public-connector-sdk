package zeenea.connector.dataset;

import java.util.List;
import zeenea.connector.SourceItem;

public final class SourceDataset extends SourceItem {

  private final List<SourceField> fields;
  private final List<String> primaryKeys;
  private final List<SourceForeignKey> foreignKeys;
  private final List<SourcePartitioning> partitions;

  private SourceDataset(Builder builder) {
    super(builder);
    this.fields = builder.fields;
    this.primaryKeys = builder.primaryKeys;
    this.foreignKeys = builder.foreignKeys;
    this.partitions = builder.partitions;
  }

  public static Builder builder() {
    return new Builder();
  }

  public List<SourceField> getFields() {
    return fields;
  }

  public List<SourceForeignKey> getForeignKeys() {
    return foreignKeys;
  }

  public List<SourcePartitioning> getPartitions() {
    return partitions;
  }

  public List<String> getPrimaryKeys() {
    return primaryKeys;
  }

  public static class Builder extends SourceItem.Builder<SourceDataset, Builder> {

    private List<SourceField> fields;
    private List<String> primaryKeys;
    private List<SourceForeignKey> foreignKeys;
    private List<SourcePartitioning> partitions;

    private Builder() {}

    public SourceDataset.Builder fields(List<SourceField> fields) {
      this.fields = fields;
      return this;
    }

    public SourceDataset.Builder primaryKeys(List<String> primaryKeys) {
      this.primaryKeys = primaryKeys;
      return this;
    }

    public SourceDataset.Builder foreignKeys(List<SourceForeignKey> foreignKeys) {
      this.foreignKeys = foreignKeys;
      return this;
    }

    public SourceDataset.Builder partitions(List<SourcePartitioning> partitions) {
      this.partitions = partitions;
      return this;
    }

    @Override
    protected SourceDataset performBuild() {
      throwIfNull("fields", this.fields);
      return new SourceDataset(this);
    }
  }
}
