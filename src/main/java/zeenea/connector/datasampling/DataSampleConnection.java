package zeenea.connector.datasampling;

import zeenea.connector.Connection;
import zeenea.connector.common.ItemReference;

/** Adds Data Sampling capability to a connector. */
public interface DataSampleConnection extends Connection {

  /**
   * Collects a data sample for a given item identifier.
   *
   * @param itemReference the item reference to sample data from
   * @param sampleSize the number of samples to collect
   * @return DataSampling object containing the collected samples with corresponding field
   *     identifiers
   */
  DataSample collectDataSample(ItemReference itemReference, int sampleSize);
}
