package zeenea.connector.datasampling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import zeenea.connector.common.ItemIdentifier;

public class DataSample {

  private static final String VERSION = "2";

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

  private DataSample(List<ItemIdentifier> fieldIdentifiers, List<List<SampleValue>> data) {
    this.fieldIdentifiers = fieldIdentifiers;
    this.data = data;
  }

  List<ItemIdentifier> fieldIdentifiers;

  List<List<SampleValue>> data;

  String version = VERSION;

  public List<ItemIdentifier> getFieldIdentifiers() {
    return fieldIdentifiers;
  }

  public List<List<SampleValue>> getData() {
    return data;
  }

  public String getVersion() {
    return version;
  }

  public static class Builder {

    private final List<ItemIdentifier> fieldIdentifiers;

    private final List<List<SampleValue>> data;

    private Builder(List<ItemIdentifier> fieldIdentifiers) {
      this.fieldIdentifiers = fieldIdentifiers;
      this.data = new ArrayList<>();
    }

    public Builder addRow(SampleValue... row) {
      return addRow(Arrays.asList(row));
    }

    public Builder addRow(List<SampleValue> row) {

      if (row != null && fieldIdentifiers.size() != row.size()) {
        throw new IllegalArgumentException("Invalid samples: row count should equal header count");
      }

      this.data.add(row);

      return this;
    }

    public DataSample build() {
      return new DataSample(this.fieldIdentifiers, this.data);
    }
  }
}
