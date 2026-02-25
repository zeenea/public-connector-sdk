package zeenea.connector.datasampling;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import zeenea.connector.common.IdentificationProperty;
import zeenea.connector.common.ItemIdentifier;

class DataSampleTest {

  private static final ObjectMapper MAPPER = new ObjectMapper();

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
  void allowDataSampling_DefaultSample() {
    DataSample dataSample =
        DataSample.builder(NAME_IDENTIFIER, AGE_IDENTIFIER, ACTIVE_IDENTIFIER)
            .addRow(SampleValue.of("Alice"), SampleValue.of(30L), SampleValue.of(false))
            .addRow(SampleValue.of("Kalle"), SampleValue.of(92L), SampleValue.of(true))
            .build();

    assertThat(dataSample.getFieldIdentifiers())
        .hasSize(3)
        .containsExactly(NAME_IDENTIFIER, AGE_IDENTIFIER, ACTIVE_IDENTIFIER);
    assertThat(dataSample.getData()).hasSize(2).allMatch(line -> line.size() == 3);
  }

  @Test
  void allowDataSampling_EmptySamples() throws JsonProcessingException {
    DataSample dataSample = DataSample.builder(NAME_IDENTIFIER).build();

    assertThat(dataSample.getFieldIdentifiers()).hasSize(1).containsExactly(NAME_IDENTIFIER);
    assertThat(dataSample.getData()).isEmpty();
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
