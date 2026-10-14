package zeenea.connector.dataobservability;

import java.util.List;
import java.util.Objects;

/** Response of a Data Observability read: the statement of one dataset */
public class DataObservabilityResponse {
  private final String dqmToolName;
  private final List<DataObservabilityCheck> checks;

  /**
   * @param dqmToolName the name of the data quality tool the checks come from
   * @param checks the checks of the dataset, empty if the item has no data observability; copied,
   *     the order is kept
   */
  public DataObservabilityResponse(String dqmToolName, List<DataObservabilityCheck> checks) {
    this.dqmToolName = Objects.requireNonNull(dqmToolName, "dqmToolName must not be null");
    this.checks = List.copyOf(Objects.requireNonNull(checks, "checks must not be null"));
  }

  public String getDqmToolName() {
    return dqmToolName;
  }

  public List<DataObservabilityCheck> getChecks() {
    return checks;
  }
}
