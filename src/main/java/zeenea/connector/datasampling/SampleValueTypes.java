package zeenea.connector.datasampling;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.locationtech.jts.geom.Geometry;
import zeenea.connector.datasampling.SampleValue.GenericSampleValue;

public class SampleValueTypes {

  private SampleValueTypes() {}

  static final class StandardSampleValue<T> extends GenericSampleValue<T> {
    public StandardSampleValue(T value) {
      super(value);
    }

    @JsonValue
    public T getValue() {
      return value;
    }
  }

  static final class ConstSampleValue implements SampleValue, Serializable {

    private final String constant;

    public static final ConstSampleValue NULL = new ConstSampleValue(null);
    public static final ConstSampleValue UNKNOWN = new ConstSampleValue("<Unknown>");

    private ConstSampleValue(String constant) {
      this.constant = constant;
    }

    @JsonValue
    public Object getValue() {
      return constant;
    }
  }

  static final class BinarySampleValue extends GenericSampleValue<byte[]> {

    private static final int MAX_LENGTH = 5;

    private final long size;

    BinarySampleValue(byte[] bytes) {
      super(bytes != null ? Arrays.copyOf(bytes, Math.min(MAX_LENGTH, bytes.length)): null);

      this.size = bytes != null ? bytes.length : -1;
    }

    @JsonValue
    public String getValue() {
      if (value == null) return null;

      StringBuilder builder = new StringBuilder();
      builder.append("Binary (");
      builder.append(size);
      builder.append(" bytes) [");
      for (int i = 0; i < value.length; i++) {
        builder.append(String.format("0x%02x", value[i]));

        if (i < value.length - 1) {
          builder.append(", ");
        }
      }
      if (size > MAX_LENGTH) {
        builder.append(", ...");
      }

      builder.append("]");
      return builder.toString();
    }
  }

  static final class TemporalSampleValue<T extends TemporalAccessor> extends GenericSampleValue<T> {

    private final DateTimeFormatter dateTimeFormatter;

    TemporalSampleValue(T value, DateTimeFormatter dateTimeFormatter) {
      super(value);

      this.dateTimeFormatter = dateTimeFormatter;
    }

    @JsonValue
    public String getValue() {
      if (value == null) return null;

      return dateTimeFormatter.format(value);
    }
  }

  public static final class StructSampleValue extends LinkedHashMap<String, SampleValue>
      implements SampleValue {
    StructSampleValue(StructEntrySampleValue... structEntries) {
      Arrays.stream(structEntries)
          .forEach(structEntry -> this.put(structEntry.getKey(), structEntry.getValue()));
    }
  }

  public static final class MapSampleValue<K, V> extends GenericSampleValue<Map<K, V>> {
    MapSampleValue(Map<K, V> value) {
      super(value);
    }

    @JsonValue
    public String getValue() {
      return value.keySet().stream()
          .map(key -> key + "=" + value.get(key))
          .collect(Collectors.joining(", ", "{", "}"));
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
    GeometrySampleValue(Geometry value) {
      super(value);
    }

    @JsonValue
    public String getValue() {
      return value.toText();
    }
  }
}
