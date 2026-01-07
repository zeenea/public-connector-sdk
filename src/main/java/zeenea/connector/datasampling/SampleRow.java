package zeenea.connector.datasampling;

import java.util.List;
import java.util.stream.Collectors;

public class SampleRow {
  List<SampleValue> samples;

  String jsonify() {
    StringBuilder json = new StringBuilder();
    json.append(
        samples.stream().map(SampleValue::jsonify)
            .collect(Collectors.joining(",", "[", "]"))
    );
    return json.toString();
  }
}
