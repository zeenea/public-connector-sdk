package zeenea.connector.dataobservability;

import zeenea.connector.Connection;

public interface DataObservabilityConnection extends Connection {

  /**
   * Collects the data observability statement of a given item.
   *
   * <p>A failed read must be signalled by throwing an exception whose message names the cause (for
   * example a missing role), never by an empty response. The scanner logs it and deletes nothing.
   *
   * @param request the request
   * @return the statement of the item
   */
  DataObservabilityResponse collectDataObservability(DataObservabilityRequest request);
}
