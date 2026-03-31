package zeenea.connector.datasampling;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface SampleValue {

  GenericSampleValue<String> NULL = new GenericSampleValue<>(null);
  GenericSampleValue<String> UNKNOWN = new GenericSampleValue<>("<Unknown>");
  Logger log = LoggerFactory.getLogger(SampleValue.class);

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
        .forEach(
            structEntry -> {
              Object previousValue = map.put(structEntry.getKey(), structEntry.getValue());

              if (previousValue != null) {
                log.warn(
                    "Multiple identical keys detected for {}: only the last value has been collected",
                    structEntry.getKey());
              }
            });
    return new GenericSampleValue<>(map);
  }

  /*
   * The Geometry is a JTS notion, from the <a href="https://locationtech.github.io/jts/">Open
   * GeoTools project</a>
   *
   * <p>Have been tested : - Point - Linestring - Polygons - MultiPoint - MultiLinestring -
   * MultiPolygons
   */
  static GenericSampleValue<String> of(Geometry geometry) {
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

  class GenericSampleValue<T> implements SampleValue {
    protected final T value;

    private GenericSampleValue(T value) {
      this.value = value;
    }

    public Object getValue() {
      return value;
    }

    @Override
    public boolean equals(Object o) {
      if (o == null || getClass() != o.getClass()) return false;
      GenericSampleValue<?> that = (GenericSampleValue<?>) o;
      return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
      return Objects.hashCode(value);
    }

    @Override
    public String toString() {
      return "GenericSampleValue{" + "value=" + value + '}';
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

    public String getValue() {
      if (value == null) return null;

      String prefix = "Binary (" + size + " bytes) [";
      String suffix = size <= MAX_LENGTH ? "]" : ", ...]";
      return IntStream.range(0, value.length)
          .mapToObj(b -> String.format("0x%02x", value[b]))
          .collect(Collectors.joining(", ", prefix, suffix));
    }

    @Override
    public boolean equals(Object o) {
      if (o == null || getClass() != o.getClass()) return false;
      if (!super.equals(o)) return false;
      BinarySampleValue that = (BinarySampleValue) o;
      return size == that.size;
    }

    @Override
    public int hashCode() {
      return Objects.hash(super.hashCode(), size);
    }

    @Override
    public String toString() {
      return "BinarySampleValue{" + "size=" + size + '}';
    }
  }

  final class TemporalSampleValue<T extends TemporalAccessor> extends GenericSampleValue<T> {

    private final DateTimeFormatter dateTimeFormatter;

    private TemporalSampleValue(T value, DateTimeFormatter dateTimeFormatter) {
      super(value);

      this.dateTimeFormatter = dateTimeFormatter;
    }

    public String getValue() {
      if (value == null) return null;

      return dateTimeFormatter.format(value);
    }

    @Override
    public boolean equals(Object o) {
      if (o == null || getClass() != o.getClass()) return false;
      if (!super.equals(o)) return false;
      TemporalSampleValue<?> that = (TemporalSampleValue<?>) o;
      return Objects.equals(dateTimeFormatter, that.dateTimeFormatter);
    }

    @Override
    public int hashCode() {
      return Objects.hash(super.hashCode(), dateTimeFormatter);
    }

    @Override
    public String toString() {
      return "TemporalSampleValue{"
          + "value="
          + value
          + ", dateTimeFormatter="
          + dateTimeFormatter
          + '}';
    }
  }
}
