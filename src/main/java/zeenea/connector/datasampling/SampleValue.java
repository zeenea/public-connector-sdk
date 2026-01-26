package zeenea.connector.datasampling;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.locationtech.jts.geom.Geometry;

public interface SampleValue {

  GenericSampleValue<String> NULL = new GenericSampleValue<>(null);
  GenericSampleValue<String> UNKNOWN = new GenericSampleValue<>("<Unknown>");
  GenericSampleValue<String> INVALID_JSON = new GenericSampleValue<>("<Invalid JSON>");

  default String jsonify() throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(this);
  }

  static SampleValue nullValue() {
    return NULL;
  }

  static SampleValue unknownValue() {
    return UNKNOWN;
  }

  static SampleValue of(byte[] bytes) {
    return new BinarySampleValue(bytes);
  }

  static GenericSampleValue<String> of(String value) {
    return new GenericSampleValue<>(value);
  }

  static GenericSampleValue<Boolean> of(Boolean value) {
    return new GenericSampleValue<>(value);
  }

  static GenericSampleValue<Byte> of(Byte value) {
    return new GenericSampleValue<>(value);
  }

  static GenericSampleValue<Short> of(Short value) {
    return new GenericSampleValue<>(value);
  }

  static GenericSampleValue<Integer> of(Integer value) {
    return new GenericSampleValue<>(value);
  }

  static GenericSampleValue<Long> of(Long value) {
    return new GenericSampleValue<>(value);
  }

  static GenericSampleValue<Float> of(Float value) {
    return new GenericSampleValue<>(value);
  }

  static GenericSampleValue<Double> of(Double value) {
    return new GenericSampleValue<>(value);
  }

  static GenericSampleValue<BigDecimal> of(BigDecimal value) {
    return new GenericSampleValue<>(value);
  }

  @SafeVarargs
  static <T> GenericSampleValue<List<GenericSampleValue<T>>> of(
      GenericSampleValue<T>... multiValues) {

    return new GenericSampleValue<>(
        Arrays.stream(multiValues)
            .map(Optional::ofNullable)
            .map(o -> o.orElse(new GenericSampleValue<>(null)))
            .collect(Collectors.toList()));
  }

  static <K, V> GenericSampleValue<Map<K, V>> of(Map<K, V> value) {
    return new GenericSampleValue<>(value);
  }

  static StructEntrySampleValue of(String name, SampleValue value) {
    return new StructEntrySampleValue(name, value);
  }

  static GenericSampleValue<Map<String, SampleValue>> of(StructEntrySampleValue... structValues) {
    Map<String, SampleValue> map = new LinkedHashMap<>();
    Arrays.stream(structValues)
        .forEach(structEntry -> map.put(structEntry.getKey(), structEntry.getValue()));
    return new GenericSampleValue<>(map);
  }

  /**
   * The Geometry is a JTS notion, from the <a href="https://locationtech.github.io/jts/">Open
   * GeoTools project</a>
   *
   * <p>Have been tested : - Point - Linestring - Polygons - MultiPoint - MultiLinestring -
   * MultiPolygons
   */
  static SampleValue of(Geometry geometry) {
    return new GenericSampleValue<>(geometry != null ? geometry.toText() : null);
  }

  static GenericSampleValue<LocalDate> of(LocalDate date) {
    return new TemporalSampleValue<>(date, DateTimeFormatter.ISO_DATE);
  }

  static GenericSampleValue<LocalTime> of(LocalTime time) {
    return new TemporalSampleValue<>(time, DateTimeFormatter.ISO_TIME);
  }

  static GenericSampleValue<Instant> of(Instant timestamp) {
    return new TemporalSampleValue<>(timestamp, DateTimeFormatter.ISO_INSTANT);
  }

  static SampleValue ofJson(String jsonString) {
    if (jsonString == null) return NULL;

    ObjectMapper mapper = new ObjectMapper();
    try {
      return new GenericSampleValue<>(mapper.readTree(jsonString));
    } catch (JsonProcessingException e) {
      return INVALID_JSON;
    }
  }

  class GenericSampleValue<T> implements SampleValue {
    protected final T value;

    private GenericSampleValue(T value) {
      this.value = value;
    }

    @JsonValue
    public Object getValue() {
      return value;
    }
  }

  final class StructEntrySampleValue {
    private final String key;
    private final SampleValue value;

    private StructEntrySampleValue(String key, SampleValue value) {
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

  final class BinarySampleValue extends GenericSampleValue<byte[]> {

    private static final int MAX_LENGTH = 5;

    private final long size;

    private BinarySampleValue(byte[] bytes) {
      super(bytes != null ? Arrays.copyOf(bytes, Math.min(MAX_LENGTH, bytes.length)) : null);

      this.size = bytes != null ? bytes.length : -1;
    }

    @JsonValue
    public String getValue() {
      if (value == null) return null;

      String prefix = "Binary (" + size + " bytes) [";
      String suffix = size <= MAX_LENGTH ? "]" : ", ...]";
      String strValue =
          IntStream.range(0, value.length)
              .mapToObj(b -> String.format("0x%02x", value[b]))
              .collect(Collectors.joining(", ", prefix, suffix));

      return strValue;
    }
  }

  final class TemporalSampleValue<T extends TemporalAccessor> extends GenericSampleValue<T> {

    private final DateTimeFormatter dateTimeFormatter;

    private TemporalSampleValue(T value, DateTimeFormatter dateTimeFormatter) {
      super(value);

      this.dateTimeFormatter = dateTimeFormatter;
    }

    @JsonValue
    public String getValue() {
      if (value == null) return null;

      return dateTimeFormatter.format(value);
    }
  }
}
