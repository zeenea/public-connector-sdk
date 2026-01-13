package zeenea.connector.datasampling;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.locationtech.jts.geom.Geometry;
import static zeenea.connector.datasampling.SampleValueTypes.*;

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

}
