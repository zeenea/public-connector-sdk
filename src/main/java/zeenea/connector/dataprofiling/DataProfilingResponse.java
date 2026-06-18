package zeenea.connector.dataprofiling;

import java.util.stream.Stream;
import zeenea.connector.datasampling.DataSample;

public class DataProfilingResponse {
  private final Stream<DataSample> samples;

  public DataProfilingResponse(Stream<DataSample> samples) {
    this.samples = samples;
  }

  public Stream<DataSample> getSamples() {
    return samples;
  }
}
