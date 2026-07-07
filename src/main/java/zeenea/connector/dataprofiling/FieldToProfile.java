package zeenea.connector.dataprofiling;

import zeenea.connector.common.ItemIdentifier;
import zeenea.connector.dataset.DataType;

public class FieldToProfile {
  private final ItemIdentifier fieldIdentifier;
  private final DataType dataType;

  public FieldToProfile(ItemIdentifier fieldIdentifier, DataType dataType) {
    this.fieldIdentifier = fieldIdentifier;
    this.dataType = dataType;
  }

  public DataType getDataType() {
    return dataType;
  }

  public ItemIdentifier getFieldIdentifier() {
    return fieldIdentifier;
  }
}
