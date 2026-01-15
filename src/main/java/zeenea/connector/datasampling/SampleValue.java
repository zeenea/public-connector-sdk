package zeenea.connector.datasampling;

import static zeenea.connector.datasampling.SpecificSampleValues.*;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import org.locationtech.jts.geom.Geometry;

public interface SampleValue {

    default String jsonify() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

    static SampleValue nullValue() {
        return SpecificSampleValues.NULL;
    }

    static SampleValue unknownValue() {
        return SpecificSampleValues.UNKNOWN;
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
    static <T> GenericSampleValue<List<GenericSampleValue<T>>> of(GenericSampleValue<T>... multiValues) {

        return new GenericSampleValue<>(
                Arrays.stream(multiValues)
                        .map(Optional::ofNullable)
                        .map(o -> o.orElse(new GenericSampleValue<>(null)))
                        .collect(Collectors.toList())
        );
    }

    static <K, V> GenericSampleValue<Map<K, V>> of(Map<K, V> value) {
        return new GenericSampleValue<>(value);
    }

    static StructEntrySampleValue of(String name, SampleValue value) {
        return new StructEntrySampleValue(name, value);
    }

    static GenericSampleValue<Map<String, SampleValue>> of(StructEntrySampleValue... structValues) {
        Map<String,SampleValue> map = new LinkedHashMap<>();
        Arrays.stream(structValues)
                .forEach(structEntry -> map.put(structEntry.getKey(), structEntry.getValue()));
        return new GenericSampleValue<>(map);
    }

    static SampleValue of(Geometry geometry) {
        return new GeometrySampleValue(geometry);
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

        GenericSampleValue(T value) {
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

}
