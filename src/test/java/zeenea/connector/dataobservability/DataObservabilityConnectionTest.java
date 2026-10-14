package zeenea.connector.dataobservability;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import zeenea.connector.common.DataSourceIdentifier;
import zeenea.connector.common.IdentificationProperty;
import zeenea.connector.common.ItemDesignator;
import zeenea.connector.common.ItemIdentifier;
import zeenea.connector.datasource.DataSource;
import zeenea.connector.property.PropertyDefinition;

class DataObservabilityConnectionTest {

  private static final ItemIdentifier TABLE =
      ItemIdentifier.of(
          IdentificationProperty.of("database", "zeenea_db"),
          IdentificationProperty.of("schema", "music"),
          IdentificationProperty.of("table", "artists"));

  private static final ItemIdentifier FIELD =
      TABLE.withSuffix(IdentificationProperty.of("field", "name"));

  private static final ItemDesignator ITEM =
      ItemDesignator.of(
          TABLE, DataSourceIdentifier.of(IdentificationProperty.of("source", "snow")));

  private static final Instant LAST_UPDATE = Instant.parse("2026-10-14T10:15:30Z");

  /** Minimal connector exercising the capability through its public interface. */
  private static class StubConnection implements DataObservabilityConnection {
    private final DataObservabilityResponse response;

    StubConnection(DataObservabilityResponse response) {
      this.response = response;
    }

    @Override
    public DataObservabilityResponse collectDataObservability(DataObservabilityRequest request) {
      return response;
    }

    @Override
    public List<DataSource> getDataSources() {
      return List.of();
    }

    @Override
    public Set<PropertyDefinition> getProperties() {
      return Set.of();
    }

    @Override
    public void close() {}
  }

  @Test
  void connectorReturnsToolNameAndCheckForRequestedItem() {
    DataObservabilityCheck check =
        new DataObservabilityCheck(
            "not_null_name",
            "Completeness",
            DataObservabilityStatus.SUCCESS,
            LAST_UPDATE,
            "Name must not be null",
            List.of(FIELD));
    DataObservabilityConnection connection =
        new StubConnection(new DataObservabilityResponse("Snowflake DMF", List.of(check)));

    DataObservabilityResponse response =
        connection.collectDataObservability(new DataObservabilityRequest(ITEM));

    assertThat(response.getDqmToolName()).isEqualTo("Snowflake DMF");
    assertThat(response.getChecks()).hasSize(1);
    DataObservabilityCheck read = response.getChecks().get(0);
    assertThat(read.getName()).isEqualTo("not_null_name");
    assertThat(read.getFamily()).hasValue("Completeness");
    assertThat(read.getStatus()).isEqualTo(DataObservabilityStatus.SUCCESS);
    assertThat(read.getLastUpdate()).isEqualTo(LAST_UPDATE);
    assertThat(read.getDescription()).hasValue("Name must not be null");
    assertThat(read.getFields()).containsExactly(FIELD);
    assertThat(new DataObservabilityRequest(ITEM).getItem()).isEqualTo(ITEM);
  }

  @Test
  void checkCarriesAnyStatusFamilyAndDescription() {
    for (DataObservabilityStatus status : DataObservabilityStatus.values()) {
      DataObservabilityCheck check =
          new DataObservabilityCheck(
              "check", "Any free text family", status, LAST_UPDATE, "Any description", List.of());

      assertThat(check.getStatus()).isEqualTo(status);
      assertThat(check.getFamily()).hasValue("Any free text family");
      assertThat(check.getDescription()).hasValue("Any description");
    }
    assertThat(DataObservabilityStatus.values())
        .containsExactlyInAnyOrder(
            DataObservabilityStatus.SUCCESS,
            DataObservabilityStatus.WARNING,
            DataObservabilityStatus.ERROR);
  }

  @Test
  void tableLevelCheckWithoutFieldsIsValid() {
    DataObservabilityCheck check =
        new DataObservabilityCheck(
            "row_count", null, DataObservabilityStatus.WARNING, LAST_UPDATE, null, null);

    assertThat(check.getFields()).isEmpty();
    assertThat(check.getFamily()).isEmpty();
    assertThat(check.getDescription()).isEmpty();
  }

  @Test
  void responseWithoutChecksMeansNoDataObservabilityForTheItem() {
    DataObservabilityConnection connection =
        new StubConnection(new DataObservabilityResponse("Snowflake DMF", List.of()));

    DataObservabilityResponse response =
        connection.collectDataObservability(new DataObservabilityRequest(ITEM));

    assertThat(response.getChecks()).isEmpty();
  }

  @Test
  void failedReadIsSignalledByThrowingNotByAnEmptyResponse() {
    DataObservabilityConnection failing =
        new StubConnection(null) {
          @Override
          public DataObservabilityResponse collectDataObservability(
              DataObservabilityRequest request) {
            throw new IllegalStateException("read failed");
          }
        };

    assertThatThrownBy(() -> failing.collectDataObservability(new DataObservabilityRequest(ITEM)))
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("read failed");
  }

  @Test
  void checkWithoutRequiredValuesIsRejected() {
    assertThatThrownBy(
            () ->
                new DataObservabilityCheck(
                    null, null, DataObservabilityStatus.SUCCESS, LAST_UPDATE, null, null))
        .isInstanceOf(NullPointerException.class)
        .hasMessageContaining("name");
    assertThatThrownBy(
            () -> new DataObservabilityCheck("check", null, null, LAST_UPDATE, null, null))
        .isInstanceOf(NullPointerException.class)
        .hasMessageContaining("status");
    assertThatThrownBy(
            () ->
                new DataObservabilityCheck(
                    "check", null, DataObservabilityStatus.SUCCESS, null, null, null))
        .isInstanceOf(NullPointerException.class)
        .hasMessageContaining("lastUpdate");
  }

  @Test
  void responseWithoutRequiredValuesIsRejected() {
    assertThatThrownBy(() -> new DataObservabilityResponse(null, List.of()))
        .isInstanceOf(NullPointerException.class)
        .hasMessageContaining("dqmToolName");
    assertThatThrownBy(() -> new DataObservabilityResponse("Snowflake DMF", null))
        .isInstanceOf(NullPointerException.class)
        .hasMessageContaining("checks");
  }

  @Test
  void responseKeepsCheckOrderAndExposesThemReadOnly() {
    List<DataObservabilityCheck> source = new ArrayList<>();
    for (String name : List.of("c", "a", "b")) {
      source.add(
          new DataObservabilityCheck(
              name, null, DataObservabilityStatus.ERROR, LAST_UPDATE, null, null));
    }
    DataObservabilityResponse response = new DataObservabilityResponse("Snowflake DMF", source);

    source.clear();

    assertThat(response.getChecks())
        .extracting(DataObservabilityCheck::getName)
        .containsExactly("c", "a", "b");
    assertThatThrownBy(() -> response.getChecks().remove(0))
        .isInstanceOf(UnsupportedOperationException.class);
  }

  @Test
  void checkKeepsFieldOrderIndependentlyOfTheSourceList() {
    ItemIdentifier otherField = TABLE.withSuffix(IdentificationProperty.of("field", "country"));
    List<ItemIdentifier> source = new ArrayList<>(List.of(otherField, FIELD));
    DataObservabilityCheck check =
        new DataObservabilityCheck(
            "not_null", null, DataObservabilityStatus.SUCCESS, LAST_UPDATE, null, source);

    source.clear();

    assertThat(check.getFields()).containsExactly(otherField, FIELD);
  }

  @Test
  void checkExposesItsFieldsReadOnly() {
    DataObservabilityCheck check =
        new DataObservabilityCheck(
            "not_null",
            null,
            DataObservabilityStatus.SUCCESS,
            LAST_UPDATE,
            null,
            new ArrayList<>(List.of(FIELD)));

    assertThatThrownBy(() -> check.getFields().remove(0))
        .isInstanceOf(UnsupportedOperationException.class);
  }

  @Test
  void checkRejectsANullField() {
    List<ItemIdentifier> fields = new ArrayList<>();
    fields.add(FIELD);
    fields.add(null);

    assertThatThrownBy(
            () ->
                new DataObservabilityCheck(
                    "not_null", null, DataObservabilityStatus.SUCCESS, LAST_UPDATE, null, fields))
        .isInstanceOf(NullPointerException.class);
  }

  @Test
  void dataObservabilityIsDisabledByDefaultAndConnectorCanDeclareItEnabled() {
    DataObservabilityConnection silent = new StubConnection(null);
    DataObservabilityConnection enabled =
        new StubConnection(null) {
          @Override
          public boolean isDataObservabilityEnabled() {
            return true;
          }
        };

    assertThat(silent.isDataObservabilityEnabled()).isFalse();
    assertThat(enabled.isDataObservabilityEnabled()).isTrue();
  }
}
