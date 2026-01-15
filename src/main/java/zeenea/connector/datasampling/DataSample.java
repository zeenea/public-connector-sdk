package zeenea.connector.datasampling;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.jetbrains.annotations.NotNull;
import zeenea.connector.common.IdentificationProperty;
import zeenea.connector.common.ItemIdentifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DataSample {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  private DataSample( List<ItemIdentifier> fieldIdentifiers, List<List<SampleValue>> samples) {
    this.fieldIdentifiers = fieldIdentifiers;
    this.samples = samples;
  }

  @JsonProperty("fields")
  @JsonSerialize(contentUsing = ItemIdentifierSerializer.class)
  List<ItemIdentifier> fieldIdentifiers;

  @JsonProperty("samples")
  List<List<SampleValue>> samples;

  public static Builder builder(ItemIdentifier... fieldIdentifiers) {
    return builder(Arrays.asList(fieldIdentifiers));
  }

  public static Builder builder(List<ItemIdentifier> fieldIdentifiers) {
    return new Builder(fieldIdentifiers);
  }


  public String jsonify() throws JsonProcessingException {
    return MAPPER.writeValueAsString(this);
  }

  public static class Builder {

    private final List<ItemIdentifier> fieldIdentifiers;

    private final List<List<SampleValue>> samples;

    public Builder(List<ItemIdentifier> fieldIdentifiers) {

      if (fieldIdentifiers == null) {
        throw new IllegalArgumentException("Invalid data sampling: Fields should not be null");
      }
      if (fieldIdentifiers.isEmpty()) {
        throw new IllegalArgumentException("Invalid data sampling: Fields should not be empty");
      }

      this.fieldIdentifiers = fieldIdentifiers;
      this.samples = new ArrayList<>();
    }

    public Builder addRow(SampleValue... row) {
      return addRow(Arrays.asList(row));
    }

    public Builder addRow(List<SampleValue> row) {

      if (row != null && fieldIdentifiers.size() != row.size()) {
        throw new IllegalArgumentException("Invalid data sampling: Inserted Row should have the same size as Fields");
      }

      this.samples.add(row);

      return this;
    }

    public DataSample build() {
      if (samples.isEmpty()) {
        throw new IllegalArgumentException("Invalid data sampling: Samples should not be empty");
      }

      return new DataSample(
              this.fieldIdentifiers,
              this.samples
      );
    }

    public boolean isValid() {
      return samples.stream().allMatch(sample -> sample.size() == fieldIdentifiers.size());
    }

  }
}
