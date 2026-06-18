package zeenea.connector.dataprofiling;

import java.util.List;
import zeenea.connector.common.ItemDesignator;
import zeenea.connector.common.ItemIdentifier;

public class DataProfilingRequest {
  private final long rowCount;
  private final ItemDesignator itemToProfile;
  private final List<ItemIdentifier> selectedFields;

  public DataProfilingRequest(
      long rowCount, ItemDesignator itemToProfile, List<ItemIdentifier> selectedFields) {
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

  public List<ItemIdentifier> getSelectedFields() {
    return selectedFields;
  }
}
