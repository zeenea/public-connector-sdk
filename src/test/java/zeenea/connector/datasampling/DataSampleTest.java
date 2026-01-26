package zeenea.connector.datasampling;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import zeenea.connector.common.IdentificationProperty;
import zeenea.connector.common.ItemIdentifier;

class DataSampleTest {

  private static final ItemIdentifier NAME_IDENTIFIER =
      ItemIdentifier.of(
          IdentificationProperty.of("database", "zeenea_db"),
          IdentificationProperty.of("schema", "music"),
          IdentificationProperty.of("table", "artists"),
          IdentificationProperty.of("field", "name"));

  private static final ItemIdentifier AGE_IDENTIFIER =
      ItemIdentifier.of(
          IdentificationProperty.of("database", "zeenea_db"),
          IdentificationProperty.of("schema", "music"),
          IdentificationProperty.of("table", "artists"),
          IdentificationProperty.of("field", "age"));

  private static final ItemIdentifier ACTIVE_IDENTIFIER =
      ItemIdentifier.of(
          IdentificationProperty.of("database", "zeenea_db"),
          IdentificationProperty.of("schema", "music"),
          IdentificationProperty.of("table", "artists"),
          IdentificationProperty.of("field", "active"));

  @Test
  void jsonify() throws JsonProcessingException {
    DataSample dataSample =
        DataSample.builder(NAME_IDENTIFIER, AGE_IDENTIFIER, ACTIVE_IDENTIFIER)
            .addRow(SampleValue.of("Alice"), SampleValue.of(30L), SampleValue.of(false))
            .addRow(SampleValue.of("Kalle"), SampleValue.of(92L), SampleValue.of(true))
            .build();

    String expectedJson =
        "{\"fields\":[{\"database\":\"zeenea_db\",\"schema\":\"music\",\"table\":\"artists\",\"field\":\"name\"},{\"database\":\"zeenea_db\",\"schema\":\"music\",\"table\":\"artists\",\"field\":\"age\"},{\"database\":\"zeenea_db\",\"schema\":\"music\",\"table\":\"artists\",\"field\":\"active\"}],\"samples\":[[\"Alice\",30,false],[\"Kalle\",92,true]]}";
    assertThat(dataSample.jsonify()).isEqualTo(expectedJson);
  }

  @Test
  void allowDataSampling_EmptySamples() throws JsonProcessingException {
    DataSample dataSample = DataSample.builder(NAME_IDENTIFIER).build();

    String expectedJson =
        "{\"fields\":[{\"database\":\"zeenea_db\",\"schema\":\"music\",\"table\":\"artists\",\"field\":\"name\"}],\"samples\":[]}";
    assertThat(dataSample.jsonify()).isEqualTo(expectedJson);
  }

  @Test
  void invalidDataSampling_SampleTooShort() {
    assertThatThrownBy(
            () ->
                DataSample.builder(NAME_IDENTIFIER, AGE_IDENTIFIER)
                    .addRow(SampleValue.of("Alice")) // Missing age value;
            )
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void invalidDataSampling_SampleTooLong() {
    assertThatThrownBy(
            () ->
                DataSample.builder(NAME_IDENTIFIER)
                    .addRow(SampleValue.of("Alice"), SampleValue.of(30L)) // Extra value
            )
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void invalidDataSampling_MixedSampleLengths() {
    assertThatThrownBy(
            () ->
                DataSample.builder(NAME_IDENTIFIER, AGE_IDENTIFIER)
                    .addRow(SampleValue.of("Alice"), SampleValue.of(30L)) // Valid sample
                    .addRow(SampleValue.of("Bob")) // Invalid sample - too short
            )
        .isInstanceOf(IllegalArgumentException.class);
  }
}
