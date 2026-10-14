package zeenea.connector.dataobservability;

import zeenea.connector.Connection;
import zeenea.connector.exception.DataObservabilityException;

/** Adds Data Observability capability to a connector. */
public interface DataObservabilityConnection extends Connection {

  /**
   * Collects the data observability statement of a given item.
   *
   * <p>A failed read must be signalled by throwing a {@link DataObservabilityException} whose
   * message names the cause (for example a missing role), never by an empty response. The scanner
   * logs it and deletes nothing.
   *
   * @param request the request
   * @return the statement of the item
   * @throws DataObservabilityException if the read failed
   */
  DataObservabilityResponse collectDataObservability(DataObservabilityRequest request);

  /**
   * Tells the scanner whether data observability collection is enabled for this connection.
   *
   * <p>When disabled, the scanner issues no data observability command and deletes nothing.
   *
   * <p>Disabled by default: a connector must opt in by overriding this method to return true. A
   * connector that implements the capability but does not opt in therefore sends no command and
   * deletes nothing.
   *
   * @return true if enabled, false otherwise (default)
   */
  default boolean isDataObservabilityEnabled() {
    return false;
  }
}
