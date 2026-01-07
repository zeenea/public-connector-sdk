package zeenea.connector.datasampling;

import zeenea.connector.common.ItemIdentifier;

import java.util.List;
import java.util.stream.Collectors;

public class DataSampling {
  List<ItemIdentifier> fieldIdentifiers;
  List<SampleRow> samples;

  public String jsonify() {
    StringBuilder json = new StringBuilder();
    json.append("{");
    json.append("\"fields\":[");
    json.append(
        fieldIdentifiers.stream().map(this::jsonify)
            .collect(Collectors.joining(",", "{", "}"))
    );
    json.append("],\n");
    json.append("\"samples\":[");
    json.append(
        samples.stream().map(SampleRow::jsonify)
            .collect(Collectors.joining(",", "", ""))
    );
    json.append("]");
    json.append("}");
    return json.toString();
  }

  private String jsonify(ItemIdentifier ii) {
    return ii.getIdentificationProperties().stream()
        .map(ip -> "\"" + ip.getKey() + "\": \"" + ip.getValue() + "\"")
        .collect(Collectors.joining(",", "", ""));
  }
}
