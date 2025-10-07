package zeenea.connector.sample;

import java.util.List;
import lombok.Value;

@Value
public class DataSample {
  List<SampleField<?>> data;

  public DataSample(List<SampleField<?>> data) {
    this.data = data;
    // Maybe add some validation here about the size of sampleField.values?
  }
}
