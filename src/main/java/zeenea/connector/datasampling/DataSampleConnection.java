package zeenea.connector.datasampling;

import zeenea.connector.Connection;

/** Adds Data Sampling capability to a connector. */
public interface DataSampleConnection extends Connection {

  /**
   * Collects a data sample for a given item identifier.
   *
   * @return DataSampling object containing the collected samples with corresponding field
   *     identifiers
   */
  DataSampleResponse collectDataSample(DataSampleRequest request);
}
