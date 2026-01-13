package zeenea.connector.datasampling;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Arrays;
import java.util.List;
import zeenea.connector.common.ItemIdentifier;

public class DataSampling {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  public static DataSampling of(List<ItemIdentifier> identifiers, List<SampleValue>... samples) {
    DataSampling dataSampling = new DataSampling();
    dataSampling.fieldIdentifiers = identifiers;
    dataSampling.samples = samples != null ? Arrays.asList(samples) : List.of();
    if (!dataSampling.isValid()) throw new IllegalArgumentException("Invalid data sampling");
    return dataSampling;
  }

  @JsonProperty("fields")
  @JsonSerialize(contentUsing = ItemIdentifierSerializer.class)
  List<ItemIdentifier> fieldIdentifiers;

  @JsonProperty("samples")
  List<List<SampleValue>> samples;

  @JsonIgnore
  public boolean isValid() {
    return fieldIdentifiers != null
        && !fieldIdentifiers.isEmpty()
        && samples != null
        && !samples.isEmpty()
        && samples.stream().allMatch(sample -> sample.size() == fieldIdentifiers.size());
  }

  public String jsonify() throws JsonProcessingException {
    return MAPPER.writeValueAsString(this);
  }
}
