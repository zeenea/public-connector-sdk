package zeenea.connector.datasampling;

import zeenea.connector.Connection;
import zeenea.connector.common.ItemReference;

/** Adds Data Sampling capability to a connector. */
public interface DataSampleConnection extends Connection {

  /**
   * Collects a data sample for a given item identifier.
   * @param request the request to proceed Data Sampling
   * @return DataSamplingResult containing the collected samples with corresponding field
   */
   DataSampleResult collectDataSample(DataSampleRequest request);
}
