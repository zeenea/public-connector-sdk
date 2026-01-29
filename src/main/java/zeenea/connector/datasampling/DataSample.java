package zeenea.connector.datasampling;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import zeenea.connector.common.ItemIdentifier;

public class DataSample {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  public static Builder builder(ItemIdentifier... fieldIdentifiers) {
    return builder(Arrays.asList(fieldIdentifiers));
  }

  public static Builder builder(List<ItemIdentifier> fieldIdentifiers) {
    if (fieldIdentifiers == null) {
      throw new IllegalArgumentException("Invalid headers: should not be null");
    }
    if (fieldIdentifiers.isEmpty()) {
      throw new IllegalArgumentException("Invalid headers: should not be empty");
    }

    return new Builder(fieldIdentifiers);
  }

  private DataSample(List<ItemIdentifier> fieldIdentifiers, List<List<SampleValue>> samples) {
    this.fieldIdentifiers = fieldIdentifiers;
    this.samples = samples;
  }

  @JsonProperty("fields")
  @JsonSerialize(contentUsing = ItemIdentifierSerializer.class)
  List<ItemIdentifier> fieldIdentifiers;

  @JsonProperty("samples")
  List<List<SampleValue>> samples;

  public List<ItemIdentifier> getFieldIdentifiers() {
    return fieldIdentifiers;
  }

  public List<List<SampleValue>> getSamples() {
    return samples;
  }

  // TODO : Move this to scanner
  public String jsonify() throws JsonProcessingException {
    return MAPPER.writeValueAsString(this);
  }

  public static class Builder {

    private final List<ItemIdentifier> fieldIdentifiers;

    private final List<List<SampleValue>> samples;

    private Builder(List<ItemIdentifier> fieldIdentifiers) {

      this.fieldIdentifiers = fieldIdentifiers;
      this.samples = new ArrayList<>();
    }

    public Builder addRow(SampleValue... row) {
      return addRow(Arrays.asList(row));
    }

    public Builder addRow(List<SampleValue> row) {

      if (row != null && fieldIdentifiers.size() != row.size()) {
        throw new IllegalArgumentException("Invalid samples: row count should equal header count");
      }

      this.samples.add(row);

      return this;
    }

    public DataSample build() {
      return new DataSample(this.fieldIdentifiers, this.samples);
    }
  }
}
