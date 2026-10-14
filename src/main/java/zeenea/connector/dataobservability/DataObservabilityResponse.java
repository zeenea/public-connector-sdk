package zeenea.connector.dataobservability;

import java.util.List;
import java.util.Objects;

/** Response of a Data Observability read: the statement of one dataset */
public class DataObservabilityResponse {
  private final String toolName;
  private final List<DataObservabilityCheck> checks;

  /**
   * An empty list of checks means the read succeeded and the item has no data observability: the
   * scanner may then delete the item's existing statement. A failed or partial read must throw a
   * {@link zeenea.connector.exception.DataObservabilityException} instead, never return an empty
   * list.
   *
   * @param toolName the name of the tool the checks come from
   * @param checks the checks of the dataset, empty if the item has no data observability; copied,
   *     the order is kept
   */
  public DataObservabilityResponse(String toolName, List<DataObservabilityCheck> checks) {
    this.toolName = Objects.requireNonNull(toolName, "toolName must not be null");
    this.checks = List.copyOf(Objects.requireNonNull(checks, "checks must not be null"));
  }

  public String getToolName() {
    return toolName;
  }

  public List<DataObservabilityCheck> getChecks() {
    return checks;
  }
}
