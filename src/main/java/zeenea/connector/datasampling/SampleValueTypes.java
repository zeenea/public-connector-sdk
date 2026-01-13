package zeenea.connector.datasampling;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.locationtech.jts.geom.Geometry;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class SampleValueTypes {

  private SampleValueTypes() {
  }

  public static abstract class GenericSampleValue<T> implements SampleValue {
    protected final T value;

    private GenericSampleValue(T value) {
      this.value = value;
    }
  }

  public static class NullSampleValue implements SampleValue {
    @JsonValue
    public String getValue() {
      return "NULL";
    }
  }

  public static class UnknownSampleValue implements SampleValue {
    @JsonValue
    public String getValue() {
      return "Unknown";
    }
  }

  public static class StringSampleValue extends GenericSampleValue<String> {
    public StringSampleValue(String value) {
      super(value);
    }

    @JsonValue
    public String getValue() {
      return value;
    }
  }

  public static class BooleanSampleValue extends GenericSampleValue<Boolean> {
    public BooleanSampleValue(Boolean value) {
      super(value);
    }

    @JsonValue
    public Boolean getValue() {
      return value;
    }
  }

  public static class LongSampleValue extends GenericSampleValue<Long> {
    public LongSampleValue(Long value) {
      super(value);
    }

    @JsonValue
    public Long getValue() {
      return value;
    }
  }

  public static class MultiValuedSampleValue implements SampleValue {
    private final List<SampleValue> values;

    public MultiValuedSampleValue(SampleValue... value) {
      this.values = List.of(value);
    }

    @JsonValue
    public List<SampleValue> getValues() {
      return values;
    }
  }

  public static class StructEntrySampleValue {
    private final String key;
    private final SampleValue value;

    public StructEntrySampleValue(String key, SampleValue value) {
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

  public static class StructSampleValue extends LinkedHashMap<String, SampleValue> implements SampleValue {
    public StructSampleValue(StructEntrySampleValue... structEntries) {
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
  public static class GeometrySampleValue extends GenericSampleValue<Geometry> {
    public GeometrySampleValue(Geometry value) {
      super(value);
    }

    @JsonValue
    public String getValue() {
      return value.toText();
    }
  }
}
