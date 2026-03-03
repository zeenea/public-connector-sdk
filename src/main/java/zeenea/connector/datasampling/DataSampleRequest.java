package zeenea.connector.datasampling;

import zeenea.connector.common.ItemDesignator;

/** Element to request a Data Sample */
public class DataSampleRequest {
  /** The item designator to sample data from */
  private final ItemDesignator itemDesignator;

  /** The number of samples to collect */
  private final int sampleSize;

  public DataSampleRequest(ItemDesignator itemDesignator, int sampleSize) {
    this.itemDesignator = itemDesignator;
    this.sampleSize = sampleSize;
  }

  public ItemDesignator getItemDesignator() {
    return itemDesignator;
  }

  public int getSampleSize() {
    return sampleSize;
  }
}
