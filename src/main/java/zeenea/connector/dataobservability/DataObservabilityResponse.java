package zeenea.connector.dataobservability;

import java.util.List;
import java.util.Objects;

public class DataObservabilityResponse {
  private final String toolName;
  private final List<DataObservabilityCheck> checks;

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
