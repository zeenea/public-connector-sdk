package zeenea.connector.dataprofiling;

import java.util.stream.Stream;
import zeenea.connector.datasampling.DataSample;

/** Response of a Data Profiling */
public class DataProfilingResponse {
  /** The collected samples, or an empty stream if the profile could not be collected */
  private final Stream<DataSample> samples;

  /**
   * @param samples the collected samples, or an empty stream if none could be collected
   */
  public DataProfilingResponse(Stream<DataSample> samples) {
    this.samples = samples;
  }

  public Stream<DataSample> getSamples() {
    return samples;
  }
}
