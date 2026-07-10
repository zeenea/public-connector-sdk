package zeenea.connector.dataprofiling;

import java.util.List;
import zeenea.connector.common.ItemDesignator;

/** Request a Data Profiling */
public class DataProfilingRequest {
  /** The number of rows to sample when collecting the profile */
  private final long rowCount;

  /** The item designator of the dataset to profile */
  private final ItemDesignator itemToProfile;

  /** The fields to include in the profile */
  private final List<FieldToProfile> selectedFields;

  /**
   * @param rowCount the number of rows to sample when collecting the profile
   * @param itemToProfile the item designator of the dataset to profile
   * @param selectedFields the fields to include in the profile
   */
  public DataProfilingRequest(
      long rowCount, ItemDesignator itemToProfile, List<FieldToProfile> selectedFields) {
    this.rowCount = rowCount;
    this.itemToProfile = itemToProfile;
    this.selectedFields = selectedFields;
  }

  public long getRowCount() {
    return rowCount;
  }

  public ItemDesignator getItemToProfile() {
    return itemToProfile;
  }

  public List<FieldToProfile> getSelectedFields() {
    return selectedFields;
  }
}
