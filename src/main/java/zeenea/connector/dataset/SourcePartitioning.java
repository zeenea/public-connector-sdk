package zeenea.connector.dataset;

import java.util.Objects;
import java.util.StringJoiner;

public class SourcePartitioning {
  private final String column;
  private final String partitionType;

  public SourcePartitioning(Builder builder) {
    this.column = builder.column;
    this.partitionType = builder.partitionType;
  }

  public String getColumn() {
    return column;
  }

  public String getPartitionType() {
    return partitionType;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private String column;
    private String partitionType;

    private Builder() {}

    public Builder column(String column) {
      this.column = column;
      return this;
    }

    public Builder partitionType(String partitionType) {
      this.partitionType = partitionType;
      return this;
    }

    protected SourcePartitioning build() {
      if (this.column == null || this.column.isEmpty()) {
        throw new IllegalArgumentException("column must not be null or empty");
      }
      if (this.partitionType == null || this.partitionType.isEmpty()) {
        throw new IllegalArgumentException("column must not be null or empty");
      }
      return new SourcePartitioning(this);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SourcePartitioning that = (SourcePartitioning) o;
    return Objects.equals(column, that.column) && Objects.equals(partitionType, that.partitionType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(column, partitionType);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", SourcePartitioning.class.getSimpleName() + "[", "]")
        .add("column='" + column + "'")
        .add("partitionType='" + partitionType + "'")
        .toString();
  }
}
