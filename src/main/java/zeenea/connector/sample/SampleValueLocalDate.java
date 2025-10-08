package zeenea.connector.sample;

import java.time.LocalDate;
import lombok.Value;

@Value
public class SampleValueLocalDate implements SampleValue {
  LocalDate value;
}
