package zeenea.connector.datasampling;

import java.util.List;
import java.util.stream.Collectors;

public interface SampleValue {
  String jsonify();

  static StringSampleValue of(String value) {
    return new StringSampleValue(value);
  }
  static BooleanSampleValue of(Boolean value) {
    return new BooleanSampleValue(value);
  }
  static LongSampleValue of(Long value) {
    return new LongSampleValue(value);
  }
  static <T> MultiValuedSampleValue of(GenericSampleValue<T>... multiValues) {
    return new MultiValuedSampleValue(multiValues);
  }
  static StructEntrySampleValue of(String name, SampleValue value) {
    return new StructEntrySampleValue(name, value);
  }
  static StructSampleValue of(StructEntrySampleValue... structValues) {
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
      json.append(
          values.stream().map(SampleValue::jsonify)
              .collect(Collectors.joining(",", "[", "]"))
      );
      return json.toString();
    }
  }

  class StructEntrySampleValue {
    private final String key;
    private final SampleValue value;

    StructEntrySampleValue(String key, SampleValue value) {
      this.key = key;
      this.value = value;
    }

    public String jsonify() {
      StringBuilder json = new StringBuilder();
      json.append("\"").append(key).append("\": ");
      json.append(value.jsonify());
      return json.toString();
    }
  }

  class StructSampleValue implements SampleValue {
    private final List<StructEntrySampleValue> values;

    private StructSampleValue(StructEntrySampleValue... value) {
      this.values = List.of(value);
    }

    @Override
    public String jsonify() {
      StringBuilder json = new StringBuilder();
      json.append(
          values.stream().map(StructEntrySampleValue::jsonify)
              .collect(Collectors.joining(",", "{", "}"))
      );
      return json.toString();
    }
  }
}
