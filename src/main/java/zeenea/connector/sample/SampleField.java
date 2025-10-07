package zeenea.connector.sample;

import java.util.List;
import lombok.Value;

@Value(staticConstructor = "of")
public class SampleField<T> {
  String name;
  List<T> values;
}
