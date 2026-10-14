package zeenea.connector.dataobservability;

import zeenea.connector.Connection;

/** Adds Data Observability capability to a connector. */
public interface DataObservabilityConnection extends Connection {

  /**
   * Collects the data observability statement of a given item.
   *
   * <p>A failed read must be signalled by throwing an exception, never by an empty response.
   *
   * @param request the request
   * @return the statement of the item
   */
  DataObservabilityResponse collectDataObservability(DataObservabilityRequest request);

  /**
   * Tells the scanner whether data observability collection is enabled for this connection.
   *
   * <p>When disabled, the scanner issues no data observability command and deletes nothing.
   *
   * @return true if enabled (default), false otherwise
   */
  default boolean isDataObservabilityEnabled() {
    return true;
  }
}
