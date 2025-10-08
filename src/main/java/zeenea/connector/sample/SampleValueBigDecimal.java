package zeenea.connector.sample;

import java.math.BigDecimal;
import lombok.Value;

@Value
public class SampleValueBigDecimal implements SampleValue {
  BigDecimal value;
}
