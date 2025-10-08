package zeenea.connector.sample;

import java.time.LocalDateTime;
import lombok.Value;

@Value
public class SampleValueLocalDateTime implements SampleValue {
  LocalDateTime value;
}
