package zeenea.connector.dataset;

public class SourcePartitioning {
  private final String column;
  private final String partitionType;

  public SourcePartitioning(String column, String partitionType) {
    this.column = column;
    this.partitionType = partitionType;
  }

  public String getColumn() {
    return column;
  }

  public String getPartitionType() {
    return partitionType;
  }
}
