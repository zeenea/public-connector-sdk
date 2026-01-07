package zeenea.connector.datasampling;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import zeenea.connector.common.ItemIdentifier;

import java.util.List;

public class DataSampling {
  @JsonProperty("fields")
  @JsonSerialize(contentUsing = ItemIdentifierSerializer.class)
  List<ItemIdentifier> fieldIdentifiers;

  @JsonProperty("samples")
  List<SampleRow> samples;

  public String jsonify() throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(this);
  }
}
