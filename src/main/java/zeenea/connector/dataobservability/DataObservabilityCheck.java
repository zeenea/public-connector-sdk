package zeenea.connector.dataobservability;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import zeenea.connector.common.ItemIdentifier;

/** A data observability check and its latest result */
public class DataObservabilityCheck {
  private final String name;
  private final String family;
  private final DataObservabilityStatus status;
  private final Instant lastUpdate;
  private final String description;
  private final List<ItemIdentifier> fields;

  /**
   * @param name the check name
   * @param family the check family, free text
   * @param status the check result
   * @param lastUpdate when the check result was last updated
   * @param description the check description
   * @param fields the identifiers of the fields targeted by the check, null or empty for a
   *     table-level check; copied, must not contain null elements
   */
  public DataObservabilityCheck(
      String name,
      String family,
      DataObservabilityStatus status,
      Instant lastUpdate,
      String description,
      List<ItemIdentifier> fields) {
    this.name = Objects.requireNonNull(name, "name must not be null");
    this.family = family;
    this.status = Objects.requireNonNull(status, "status must not be null");
    this.lastUpdate = Objects.requireNonNull(lastUpdate, "lastUpdate must not be null");
    this.description = description;
    this.fields = fields == null ? List.of() : List.copyOf(fields);
  }

  public String getName() {
    return name;
  }

  public Optional<String> getFamily() {
    return Optional.ofNullable(family);
  }

  public DataObservabilityStatus getStatus() {
    return status;
  }

  public Instant getLastUpdate() {
    return lastUpdate;
  }

  public Optional<String> getDescription() {
    return Optional.ofNullable(description);
  }

  public List<ItemIdentifier> getFields() {
    return fields;
  }
}
