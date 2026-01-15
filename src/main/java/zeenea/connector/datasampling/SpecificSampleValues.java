package zeenea.connector.datasampling;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;

import org.locationtech.jts.geom.Geometry;
import zeenea.connector.datasampling.SampleValue.GenericSampleValue;

public class SpecificSampleValues {


  public static final GenericSampleValue<String> NULL = new GenericSampleValue<>(null);
  public static final GenericSampleValue<String> UNKNOWN = new GenericSampleValue<>("<Unknown>");

  private SpecificSampleValues() {}

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
