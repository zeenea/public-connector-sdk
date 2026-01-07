package zeenea.connector.datasampling;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class SampleRow {
  List<SampleValue> samples;

  @JsonValue
  public List<SampleValue> getSamples() {
    return samples;
  }

  String jsonify() throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(this);
  }
}
