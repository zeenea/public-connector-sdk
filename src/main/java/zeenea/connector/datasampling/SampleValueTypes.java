package zeenea.connector.datasampling;

import com.fasterxml.jackson.annotation.JsonValue;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.spi.DateFormatProvider;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.locationtech.jts.geom.Geometry;

public class SampleValueTypes {

    private SampleValueTypes() {
    }

    public abstract static class GenericSampleValue<T> implements SampleValue {
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

    public static class BinarySampleValue extends GenericSampleValue<byte[]> {

        private static final int MAX_LENGTH = 5;

        public BinarySampleValue(byte[] bytes) {
            super(Arrays.copyOf(bytes, Math.min(MAX_LENGTH + 1, bytes.length)));
        }

        @JsonValue
        public String getValue() {
            StringBuilder builder = new StringBuilder();
            builder.append("Binary [");
            for (int i = 0; i < value.length; i++) {
                if (i < MAX_LENGTH) {
                    builder.append(String.format("0x%02x", value[i]));
                } else {
                    builder.append("...");
                }

                if (i < value.length - 1) {
                    builder.append(", ");
                }

            }
            builder.append("]");
            return builder.toString();
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

    public static class ByteSampleValue extends GenericSampleValue<Byte> {
        public ByteSampleValue(Byte value) {
            super(value);
        }

        @JsonValue
        public Byte getValue() {
            return value;
        }
    }

    public static class ShortSampleValue extends GenericSampleValue<Short> {
        public ShortSampleValue(Short value) {
            super(value);
        }

        @JsonValue
        public Short getValue() {
            return value;
        }
    }

    public static class IntegerSampleValue extends GenericSampleValue<Integer> {
        public IntegerSampleValue(Integer value) {
            super(value);
        }

        @JsonValue
        public Integer getValue() {
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

    public static class FloatSampleValue extends GenericSampleValue<Float> {
        public FloatSampleValue(Float value) {
            super(value);
        }

        @JsonValue
        public Float getValue() {
            return value;
        }
    }

    public static class DoubleSampleValue extends GenericSampleValue<Double> {
        public DoubleSampleValue(Double value) {
            super(value);
        }

        @JsonValue
        public Double getValue() {
            return value;
        }
    }

    public static class BigDecimalSampleValue extends GenericSampleValue<BigDecimal> {
        public BigDecimalSampleValue(BigDecimal value) {
            super(value);
        }

        @JsonValue
        public BigDecimal getValue() {
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

    public static class StructSampleValue extends LinkedHashMap<String, SampleValue>
            implements SampleValue {
        public StructSampleValue(StructEntrySampleValue... structEntries) {
            Arrays.stream(structEntries)
                    .forEach(structEntry -> this.put(structEntry.getKey(), structEntry.getValue()));
        }
    }

    public static class DateSampleValue extends GenericSampleValue<Date> {
        public DateSampleValue(Date value) {
            super(value);
        }

        @JsonValue
        public String getValue() {
            // format Java Date to ISO String

            return DateTimeFormatter.ISO_DATE_TIME.format(value.toInstant());
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
