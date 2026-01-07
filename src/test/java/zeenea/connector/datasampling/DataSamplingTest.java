package zeenea.connector.datasampling;

import org.junit.jupiter.api.Test;
import zeenea.connector.common.IdentificationProperty;
import zeenea.connector.common.ItemIdentifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataSamplingTest {

  @Test
  void jsonify() {
    ItemIdentifier ii = ItemIdentifier.of(List.of(
            IdentificationProperty.of("database", "zeenea_db"),
            IdentificationProperty.of("schema", "music"),
            IdentificationProperty.of("table", "artists")
        ));

    SampleRow sampleRow = new SampleRow();
    sampleRow.samples = List.of(
        SampleValue.of("Alice"),
        SampleValue.of(30L),
        SampleValue.of(true)
    );

    DataSampling dataSampling = new DataSampling();
    dataSampling.fieldIdentifiers = List.of(ii);
    dataSampling.samples = List.of(sampleRow);

    String expectedJson = "{\"fields\":[{\"database\": \"zeenea_db\",\"schema\": \"music\",\"table\": \"artists\"}],\n\"samples\":[[\"Alice\",30,true]]}";
    assertEquals(expectedJson, dataSampling.jsonify());
  }
}