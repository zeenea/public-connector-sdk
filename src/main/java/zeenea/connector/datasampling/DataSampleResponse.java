package zeenea.connector.datasampling;

/** Response of a Data Sample */
public class DataSampleResponse {

  /** The sampling result */
  private final DataSample sample;

  public DataSampleResponse(DataSample sample) {
    this.sample = sample;
  }

  public DataSample getSample() {
    return sample;
  }
}
