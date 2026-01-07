package zeenea.connector.datasampling;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import zeenea.connector.common.ItemIdentifier;

public class DataSampling {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  @JsonProperty("fields")
  @JsonSerialize(contentUsing = ItemIdentifierSerializer.class)
  List<ItemIdentifier> fieldIdentifiers;

  @JsonProperty("samples")
  List<SampleRow> samples;

  public String jsonify() throws JsonProcessingException {
    return MAPPER.writeValueAsString(this);
  }
}
