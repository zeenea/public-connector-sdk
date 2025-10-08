package zeenea.connector.sample;

import lombok.Value;

@Value
public class SampleValueInteger implements SampleValue {

  Integer value;

  public Integer getValue() {
    return value;
  }
}
