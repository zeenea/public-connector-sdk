package zeenea.connector.datasampling;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SampleValueTest {

  @Test
  void ofString() throws JsonProcessingException {
    SampleValue testSample = SampleValue.of("test");
    assertThat(testSample.jsonify()).isEqualTo("\"test\"");
  }

  @Test
  void ofBoolean() throws JsonProcessingException {
    SampleValue testSample = SampleValue.of(false);
    assertThat(testSample.jsonify()).isEqualTo("false");
  }

  @Test
  void ofLong() throws JsonProcessingException {
    SampleValue testSample = SampleValue.of(1234567890L);
    assertThat(testSample.jsonify()).isEqualTo("1234567890");
  }

  @Test
  void ofMultiValue() throws JsonProcessingException {
    SampleValue testSample = SampleValue.of(
        SampleValue.of("Knatte"),
        SampleValue.of("Fnatte"),
        SampleValue.of("Tjatte")
    );
    assertThat(testSample.jsonify()).isEqualTo("[\"Knatte\",\"Fnatte\",\"Tjatte\"]");
  }

  @Test
  void ofBadMultiValue() throws JsonProcessingException {
    SampleValue testSample = SampleValue.of(
        SampleValue.of("value1"),
        SampleValue.of("true"),
        SampleValue.of("42L")
    );
    assertThat(testSample.jsonify()).isEqualTo("[\"value1\",\"true\",\"42L\"]");
  }

  @Test
  void ofStruct() throws JsonProcessingException {
    SampleValue testSample = SampleValue.of(
        SampleValue.of("name1", SampleValue.of("value1")),
        SampleValue.of("name2", SampleValue.of(true)),
        SampleValue.of("name3", SampleValue.of(42L))
    );
    assertThat(testSample.jsonify()).isEqualTo("{\"name1\":\"value1\",\"name2\":true,\"name3\":42}");
  }

  @Test
  void ofNestedStruct() throws JsonProcessingException {
    SampleValue testSample = SampleValue.of(
        SampleValue.of("name1", SampleValue.of("value1")),
        SampleValue.of("name2", SampleValue.of(true)),
        SampleValue.of("name3", SampleValue.of(42L)),
        SampleValue.of("name4", SampleValue.of(
                SampleValue.of("name1", SampleValue.of("value1")),
                SampleValue.of("name2", SampleValue.of(true)),
                SampleValue.of("name3", SampleValue.of(42L))
        ))
    );
    assertThat(testSample.jsonify()).isEqualTo("{\"name1\":\"value1\",\"name2\":true,\"name3\":42,\"name4\":{\"name1\":\"value1\",\"name2\":true,\"name3\":42}}");
  }
}