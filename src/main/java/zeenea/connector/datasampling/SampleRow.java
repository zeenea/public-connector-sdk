package zeenea.connector.datasampling;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.List;

public class SampleRow {
  List<SampleValue> samples;

  @JsonValue
  public List<SampleValue> getSamples() {
    return samples;
  }
}
