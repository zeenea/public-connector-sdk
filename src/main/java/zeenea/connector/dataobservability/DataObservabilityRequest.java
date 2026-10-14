package zeenea.connector.dataobservability;

import zeenea.connector.common.ItemDesignator;

/** Request a Data Observability statement */
public class DataObservabilityRequest {
  /** The item designator of the dataset to read */
  private final ItemDesignator item;

  /**
   * @param item the item designator of the dataset to read
   */
  public DataObservabilityRequest(ItemDesignator item) {
    this.item = item;
  }

  public ItemDesignator getItem() {
    return item;
  }
}
