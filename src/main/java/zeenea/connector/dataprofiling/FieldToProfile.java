package zeenea.connector.dataprofiling;

import zeenea.connector.common.ItemIdentifier;
import zeenea.connector.dataset.DataType;

/** A field selected for a Data Profiling process, together with its logical type */
public class FieldToProfile {
  /** The identifier of the field to profile */
  private final ItemIdentifier fieldIdentifier;

  /** The logical type of the field, used to interpret the collected samples */
  private final DataType dataType;

  /**
   * @param fieldIdentifier the identifier of the field to profile
   * @param dataType the logical type of the field
   */
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
