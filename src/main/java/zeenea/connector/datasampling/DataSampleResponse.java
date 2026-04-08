package zeenea.connector.datasampling;

import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/** Response of a Data Sample */
public class DataSampleResponse {

  /** The sampling result */
  @Nullable private final DataSample sample;

  /**
   * Returns an empty DataSampleResponse with no sample. This can be used when sampling fails or is
   * not applicable, allowing the caller to handle the absence of a sample gracefully.
   */
  public DataSampleResponse() {
    this.sample = null;
  }

  /**
   * Returns a DataSampleResponse with the provided sample.
   *
   * @param sample that can not be null
   */
  public DataSampleResponse(@NotNull DataSample sample) {
    this.sample = sample;
  }

  public Optional<DataSample> getSample() {
    return Optional.ofNullable(sample);
  }
}
