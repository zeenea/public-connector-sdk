package zeenea.connector.sample;

import lombok.Value;
import zeenea.connector.dataset.DataType;

@Value(staticConstructor = "of")
public class SampleHeader {
  String name;
  DataType cellType;
}
