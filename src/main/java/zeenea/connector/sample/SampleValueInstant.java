package zeenea.connector.sample;

import java.time.Instant;
import lombok.Value;

@Value
public class SampleValueInstant implements SampleValue {
  Instant value;
}
