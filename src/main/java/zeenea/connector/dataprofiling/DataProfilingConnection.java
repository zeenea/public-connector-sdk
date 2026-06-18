package zeenea.connector.dataprofiling;

import zeenea.connector.Connection;
import zeenea.connector.common.ItemDesignator;

/** Adds Data Profiling capability to a connector. */
public interface DataProfilingConnection extends Connection {

  /**
   * Returns an estimate of the total row count for the given item.
   *
   * <p>The scanner uses this to compute the sample size before calling {@link
   * #collectDataProfile(DataProfilingRequest)}.
   *
   * @param item the item to estimate
   * @return the estimated row count, or 0 if unknown
   */
  long estimatedRowCount(ItemDesignator item);

  /**
   * Collects data profile for a given item.
   *
   * @param request the request
   * @return DataProfiling object containing the collected samples with corresponding field
   *     identifiers
   */
  DataProfilingResponse collectDataProfile(DataProfilingRequest request);
}
