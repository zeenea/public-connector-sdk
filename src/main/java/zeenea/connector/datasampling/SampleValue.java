package zeenea.connector.datasampling;

import java.util.List;
import java.util.stream.Collectors;

public interface SampleValue {
  String jsonify();

  static SampleValue of(String value) {
    return new StringSampleValue(value);
  }
  static SampleValue of(Boolean value) {
    return new BooleanSampleValue(value);
  }
  static SampleValue of(Long value) {
    return new LongSampleValue(value);
  }
  static SampleValue of(SampleValue... multiValues) {
    return new MultiValuedSampleValue(multiValues);
  }
  static NamedSampleValue of(String name, SampleValue value) {
    return new NamedSampleValue(name, value);
  }
  static StructSampleValue of(NamedSampleValue... structValues) {
    return new StructSampleValue(structValues);
  }

  abstract class GenericSampleValue<T> implements SampleValue {
    protected final T value;

    private GenericSampleValue(T value) {
      this.value = value;
    }
   }

  class StringSampleValue extends GenericSampleValue<String> {

    private StringSampleValue(String value) {
      super(value);
    }

    @Override
    public String jsonify() {
      if (value == null) {
        return "null";
      }
      return "\"" + escapeJson(value) + "\"";
    }

    private String escapeJson(String str) {
      return str.replace("\"", "\\\"");
    }
  }

  class BooleanSampleValue extends GenericSampleValue<Boolean> {

    private BooleanSampleValue(Boolean value) {
      super(value);
    }

    @Override
    public String jsonify() {
      if (value == null) {
        return "null";
      }
      return value? "true": "false";
    }
  }

  class LongSampleValue extends GenericSampleValue<Long> {

    private LongSampleValue(Long value) {
      super(value);
    }

    @Override
    public String jsonify() {
      if (value == null) {
        return "null";
      }
      return value.toString();
    }
  }

  class MultiValuedSampleValue implements SampleValue {
    private final List<SampleValue> values;

    private MultiValuedSampleValue(SampleValue... value) {
      this.values = List.of(value);
    }

    @Override
    public String jsonify() {
      StringBuilder json = new StringBuilder();
      json.append("[");
      json.append(
          values.stream().map(SampleValue::jsonify)
              .collect(Collectors.joining(",", "", ""))
      );
      json.append("]");
      return json.toString();
    }
  }

  class NamedSampleValue {
    private final String name;
    private final SampleValue value;

    NamedSampleValue(String name, SampleValue value) {
      this.name = name;
      this.value = value;
    }

    public String jsonify() {
      StringBuilder json = new StringBuilder();
      json.append("\"").append(name).append("\": ");
      json.append(value.jsonify());
      return json.toString();
    }
  }

  class StructSampleValue implements SampleValue {
    private final List<NamedSampleValue> values;

    private StructSampleValue(NamedSampleValue... value) {
      this.values = List.of(value);
    }

    @Override
    public String jsonify() {
      StringBuilder json = new StringBuilder();
      json.append("{");
      json.append(
          values.stream().map(NamedSampleValue::jsonify)
              .collect(Collectors.joining(",", "", ""))
      );
      json.append("}");
      return json.toString();
    }
  }
}
