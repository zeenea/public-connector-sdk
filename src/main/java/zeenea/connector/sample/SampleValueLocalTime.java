package zeenea.connector.sample;

import java.time.LocalTime;
import lombok.Value;

@Value
public class SampleValueLocalTime implements SampleValue {
  LocalTime value;
}
