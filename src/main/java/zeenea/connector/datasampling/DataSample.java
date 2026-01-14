package zeenea.connector.datasampling;

import zeenea.connector.common.ItemIdentifier;

/** Adds Data Sampling capability to a connector. */
public interface DataSample {

  /**
   * Collects a data sample for a given item identifier.
   *
   * @param item the item identifier to sample data from
   * @param sampleSize the number of samples to collect
   * @return DataSampling object containing the collected samples with corresponding field
   *     identifiers
   */
  DataSampling collectDataSample(ItemIdentifier item, int sampleSize);
}
