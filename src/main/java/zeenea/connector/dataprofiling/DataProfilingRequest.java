package zeenea.connector.dataprofiling;

import java.util.List;
import zeenea.connector.common.ItemDesignator;

public class DataProfilingRequest {
  private final long rowCount;
  private final ItemDesignator itemToProfile;
  private final List<FieldToProfile> selectedFields;

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
