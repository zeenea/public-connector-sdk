package zeenea.connector.datasampling;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import org.locationtech.jts.geom.Geometry;

public interface SampleValue {
  default String jsonify() throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(this);
  }

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

  static SampleValue of(Geometry geometry) {
    return new GeometrySampleValue(geometry);
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

    @JsonValue
    public String getValue() {
      return value;
    }
  }

  class BooleanSampleValue extends GenericSampleValue<Boolean> {
    private BooleanSampleValue(Boolean value) {
      super(value);
    }

    @JsonValue
    public Boolean getValue() {
      return value;
    }
  }

  class LongSampleValue extends GenericSampleValue<Long> {
    private LongSampleValue(Long value) {
      super(value);
    }

    @JsonValue
    public Long getValue() {
      return value;
    }
  }

  class MultiValuedSampleValue implements SampleValue {
    private final List<SampleValue> values;

    private MultiValuedSampleValue(SampleValue... value) {
      this.values = List.of(value);
    }

    @JsonValue
    public List<SampleValue> getValues() {
      return values;
    }
  }

  class StructEntrySampleValue {
    private final String key;
    private final SampleValue value;

    StructEntrySampleValue(String key, SampleValue value) {
      this.key = key;
      this.value = value;
    }

    public String getKey() {
      return key;
    }

    public SampleValue getValue() {
      return value;
    }
  }

  class StructSampleValue extends LinkedHashMap<String, SampleValue> implements SampleValue {
    private StructSampleValue(StructEntrySampleValue... structEntries) {
      Arrays.stream(structEntries)
          .forEach(structEntry -> this.put(structEntry.getKey(), structEntry.getValue()));
    }
  }

  /**
   * The Geometry is a JTS notion, from the Open GeoTools project
   * https://locationtech.github.io/jts/
   *
   * <p>Have been tested : - Point - Linestring - Polygons - MultiPoint - MultiLinestring -
   * MultiPolygons
   */
  class GeometrySampleValue extends GenericSampleValue<Geometry> {
    private GeometrySampleValue(Geometry value) {
      super(value);
    }

    @JsonValue
    public String getValue() {
      return value.toText();
    }
  }
}
