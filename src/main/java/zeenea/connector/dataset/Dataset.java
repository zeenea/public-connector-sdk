package zeenea.connector.dataset;

import java.util.List;
import zeenea.connector.Item;

public final class Dataset extends Item {

  private final List<Field> fields;
  private final List<String> primaryKeys;
  private final List<ForeignKey> foreignKeys;
  private final List<Partitioning> partitions;

  private Dataset(Builder builder) {
    super(builder);
    this.fields = builder.fields;
    this.primaryKeys = builder.primaryKeys;
    this.foreignKeys = builder.foreignKeys;
    this.partitions = builder.partitions;
  }

  public static Builder builder() {
    return new Builder();
  }

  public List<Field> getFields() {
    return fields;
  }

  public List<ForeignKey> getForeignKeys() {
    return foreignKeys;
  }

  public List<Partitioning> getPartitions() {
    return partitions;
  }

  public List<String> getPrimaryKeys() {
    return primaryKeys;
  }

  public static class Builder extends Item.Builder<Dataset, Builder> {

    private List<Field> fields;
    private List<String> primaryKeys;
    private List<ForeignKey> foreignKeys;
    private List<Partitioning> partitions;

    private Builder() {}

    public Dataset.Builder fields(List<Field> fields) {
      this.fields = fields;
      return this;
    }

    public Dataset.Builder primaryKeys(List<String> primaryKeys) {
      this.primaryKeys = primaryKeys;
      return this;
    }

    public Dataset.Builder foreignKeys(List<ForeignKey> foreignKeys) {
      this.foreignKeys = foreignKeys;
      return this;
    }

    public Dataset.Builder partitions(List<Partitioning> partitions) {
      this.partitions = partitions;
      return this;
    }

    @Override
    protected Dataset performBuild() {
      throwIfNull("fields", this.fields);
      return new Dataset(this);
    }
  }
}
