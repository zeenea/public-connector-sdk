package zeenea.connector.datasampling;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SampleRowTest {

  @Test
  void jsonify() throws JsonProcessingException {
    SampleRow sampleRow = new SampleRow();
    sampleRow.samples = List.of(
        SampleValue.of("Alice"),
        SampleValue.of(30L),
        SampleValue.of(true)
    );

    String expectedJson = "[\"Alice\",30,true]";
    assertEquals(expectedJson, sampleRow.jsonify());
  }
}