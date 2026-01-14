package zeenea.connector.datasampling;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import org.junit.jupiter.api.Test;
import zeenea.connector.common.IdentificationProperty;
import zeenea.connector.common.ItemIdentifier;

class DataSampleTest {

  private static final ItemIdentifier NAME_IDENTIFIER =
      ItemIdentifier.of(
          List.of(
              IdentificationProperty.of("database", "zeenea_db"),
              IdentificationProperty.of("schema", "music"),
              IdentificationProperty.of("table", "artists"),
              IdentificationProperty.of("field", "name")));

  private static final ItemIdentifier AGE_IDENTIFIER =
      ItemIdentifier.of(
          List.of(
              IdentificationProperty.of("database", "zeenea_db"),
              IdentificationProperty.of("schema", "music"),
              IdentificationProperty.of("table", "artists"),
              IdentificationProperty.of("field", "age")));

  private static final ItemIdentifier ACTIVE_IDENTIFIER =
      ItemIdentifier.of(
          List.of(
              IdentificationProperty.of("database", "zeenea_db"),
              IdentificationProperty.of("schema", "music"),
              IdentificationProperty.of("table", "artists"),
              IdentificationProperty.of("field", "active")));

  @Test
  void jsonify() throws JsonProcessingException {
    DataSample dataSample =
        DataSample.of(
            List.of(NAME_IDENTIFIER, AGE_IDENTIFIER, ACTIVE_IDENTIFIER),
            List.of(SampleValue.of("Alice"), SampleValue.of(30L), SampleValue.of(false)),
            List.of(SampleValue.of("Kalle"), SampleValue.of(92L), SampleValue.of(true)));

    String expectedJson =
        "{\"fields\":[{\"database\":\"zeenea_db\",\"schema\":\"music\",\"table\":\"artists\",\"field\":\"name\"},{\"database\":\"zeenea_db\",\"schema\":\"music\",\"table\":\"artists\",\"field\":\"age\"},{\"database\":\"zeenea_db\",\"schema\":\"music\",\"table\":\"artists\",\"field\":\"active\"}],\"samples\":[[\"Alice\",30,false],[\"Kalle\",92,true]]}";
    assertEquals(expectedJson, dataSample.jsonify());
  }

  @Test
  void invalidDataSampling_EmptySamples() {
    assertThrows(
        IllegalArgumentException.class, () -> DataSample.of(List.of(NAME_IDENTIFIER), List.of()));
  }

  @Test
  void invalidDataSampling_SampleTooShort() {
    assertThrows(
        IllegalArgumentException.class,
        () ->
            DataSample.of(
                List.of(NAME_IDENTIFIER, AGE_IDENTIFIER),
                List.of(SampleValue.of("Alice")) // Missing age value
                ));
  }

  @Test
  void invalidDataSampling_SampleTooLong() {
    assertThrows(
        IllegalArgumentException.class,
        () ->
            DataSample.of(
                List.of(NAME_IDENTIFIER),
                List.of(SampleValue.of("Alice"), SampleValue.of(30L)) // Extra value
                ));
  }

  @Test
  void invalidDataSampling_MixedSampleLengths() {
    assertThrows(
        IllegalArgumentException.class,
        () ->
            DataSample.of(
                List.of(NAME_IDENTIFIER, AGE_IDENTIFIER),
                List.of(SampleValue.of("Alice"), SampleValue.of(30L)), // Valid sample
                List.of(SampleValue.of("Bob")) // Invalid sample - too short
                ));
  }
}
