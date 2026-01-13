package zeenea.connector.datasampling;

import static zeenea.connector.datasampling.SampleValueTypes.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import org.locationtech.jts.geom.Geometry;

public interface SampleValue {

  default String jsonify() throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(this);
  }

  static NullSampleValue nullValue() {
    return new NullSampleValue();
  }

  static SampleValue unknowValue() {
    return new UnknownSampleValue();
  }

  static SampleValue of(byte[] bytes) {
    return new BinarySampleValue(bytes);
  }

  static StringSampleValue of(String value) {
    return new StringSampleValue(value);
  }

  static BooleanSampleValue of(Boolean value) {
    return new BooleanSampleValue(value);
  }

  static ByteSampleValue of(Byte value) {
    return new ByteSampleValue(value);
  }

  static ShortSampleValue of(Short value) {
    return new ShortSampleValue(value);
  }

  static IntegerSampleValue of(Integer value) {
    return new IntegerSampleValue(value);
  }

  static LongSampleValue of(Long value) {
    return new LongSampleValue(value);
  }

  static FloatSampleValue of(Float value) {
    return new FloatSampleValue(value);
  }

  static DoubleSampleValue of(Double value) {
    return new DoubleSampleValue(value);
  }

  static BigDecimalSampleValue of(BigDecimal value) {
    return new BigDecimalSampleValue(value);
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

  static DateSampleValue of(LocalDate date) {
    return new DateSampleValue(date);
  }

  static TimeSampleValue of(LocalTime time) {
    return new TimeSampleValue(time);
  }

  static InstantSampleValue of(Instant time) {
    return new InstantSampleValue(time);
  }
}
