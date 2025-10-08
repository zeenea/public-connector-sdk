package zeenea.connector.sample;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Singular;
import lombok.Value;
import zeenea.connector.dataset.DataType;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SampleField {
  String name;
  DataType type;
  @Singular List<SampleValue> values;

  public List<SampleValue> getValues() {
    return Optional.ofNullable(values).map(List::copyOf).orElse(Collections.emptyList());
  }

  public static SampleField of(String header, DataType dataType, List<SampleValue> values) {
    return new SampleField(header, dataType, values);
  }

  public SampleField add(SampleValue value) {
    List<SampleValue> collect =
        Stream.concat(getValues().stream(), Stream.of(value)).collect(Collectors.toList());
    return new SampleField(this.name, this.type, collect);
  }

  public static SampleField ofStrings(String header, List<String> values) {
    List<SampleValue> samplesValues =
        values.stream().map(SampleValueString::new).collect(Collectors.toList());
    return new SampleField(header, DataType.String, samplesValues);
  }

  public SampleField addString(String value) {
    SampleValue sampleValue = new SampleValueString(value);
    List<SampleValue> sampleValues =
        Stream.concat(getValues().stream(), Stream.of(sampleValue)).collect(Collectors.toList());
    return new SampleField(this.name, this.type, sampleValues);
  }

  public static SampleField ofIntegers(String header, List<Integer> values) {
    List<SampleValue> sampleValues =
        values.stream().map(SampleValueInteger::new).collect(Collectors.toList());
    return new SampleField(header, DataType.Integer, sampleValues);
  }

  public SampleField addInteger(Integer value) {
    SampleValue sampleValue = new SampleValueInteger(value);
    List<SampleValue> sampleValues =
        Stream.concat(getValues().stream(), Stream.of(sampleValue)).collect(Collectors.toList());
    return new SampleField(this.name, this.type, sampleValues);
  }

  public static SampleField ofLongs(String header, List<Long> values) {
    List<SampleValue> sampleValues =
        values.stream().map(SampleValueLong::new).collect(Collectors.toList());
    return new SampleField(header, DataType.Integer, sampleValues);
  }

  public SampleField addLong(Long value) {
    SampleValue sampleValue = new SampleValueLong(value);
    List<SampleValue> sampleValues =
        Stream.concat(getValues().stream(), Stream.of(sampleValue)).collect(Collectors.toList());
    return new SampleField(this.name, this.type, sampleValues);
  }

  public static SampleField ofBigDecimals(String header, List<BigDecimal> values) {
    List<SampleValue> sampleValues =
        values.stream().map(SampleValueBigDecimal::new).collect(Collectors.toList());
    return new SampleField(header, DataType.BigDecimal, sampleValues);
  }

  public SampleField addBigDecimal(BigDecimal value) {
    SampleValue sampleValue = new SampleValueBigDecimal(value);
    List<SampleValue> sampleValues =
        Stream.concat(getValues().stream(), Stream.of(sampleValue)).collect(Collectors.toList());
    return new SampleField(this.name, this.type, sampleValues);
  }

  public static SampleField ofDoubles(String header, List<Double> values) {
    List<SampleValue> sampleValues =
        values.stream().map(SampleValueDouble::new).collect(Collectors.toList());
    return new SampleField(header, DataType.Double, sampleValues);
  }

  public SampleField addDouble(Double value) {
    SampleValue sampleValue = new SampleValueDouble(value);
    List<SampleValue> sampleValues =
        Stream.concat(getValues().stream(), Stream.of(sampleValue)).collect(Collectors.toList());
    return new SampleField(this.name, this.type, sampleValues);
  }

  public static SampleField ofBooleans(String header, List<Boolean> values) {
    List<SampleValue> sampleValues =
        values.stream().map(SampleValueBoolean::new).collect(Collectors.toList());
    return new SampleField(header, DataType.Boolean, sampleValues);
  }

  public SampleField addBoolean(Boolean value) {
    SampleValue sampleValue = new SampleValueBoolean(value);
    List<SampleValue> sampleValues =
        Stream.concat(getValues().stream(), Stream.of(sampleValue)).collect(Collectors.toList());
    return new SampleField(this.name, this.type, sampleValues);
  }

  public static SampleField ofLocalDates(String header, List<LocalDate> values) {
    List<SampleValue> sampleValues =
        values.stream().map(SampleValueLocalDate::new).collect(Collectors.toList());
    return new SampleField(header, DataType.Date, sampleValues);
  }

  public SampleField addLocalDate(LocalDate value) {
    SampleValue sampleValue = new SampleValueLocalDate(value);
    List<SampleValue> sampleValues =
        Stream.concat(getValues().stream(), Stream.of(sampleValue)).collect(Collectors.toList());
    return new SampleField(this.name, this.type, sampleValues);
  }

  public static SampleField ofLocalTimes(String header, List<LocalTime> values) {
    List<SampleValue> sampleValues =
        values.stream().map(SampleValueLocalTime::new).collect(Collectors.toList());
    return new SampleField(header, DataType.Time, sampleValues);
  }

  public SampleField addLocalTime(LocalTime value) {
    SampleValue sampleValue = new SampleValueLocalTime(value);
    List<SampleValue> sampleValues =
        Stream.concat(getValues().stream(), Stream.of(sampleValue)).collect(Collectors.toList());
    return new SampleField(this.name, this.type, sampleValues);
  }

  public static SampleField ofLocalDateTimes(String header, List<LocalDateTime> values) {
    List<SampleValue> sampleValues =
        values.stream().map(SampleValueLocalDateTime::new).collect(Collectors.toList());
    return new SampleField(header, DataType.Timestamp, sampleValues);
  }

  public SampleField addLocalDateTime(LocalDateTime value) {
    SampleValue sampleValue = new SampleValueLocalDateTime(value);
    List<SampleValue> sampleValues =
        Stream.concat(getValues().stream(), Stream.of(sampleValue)).collect(Collectors.toList());
    return new SampleField(this.name, this.type, sampleValues);
  }

  public static SampleField ofInstants(String header, List<Instant> values) {
    List<SampleValue> sampleValues =
        values.stream().map(SampleValueInstant::new).collect(Collectors.toList());
    return new SampleField(header, DataType.Timestamp, sampleValues);
  }

  public SampleField addInstant(Instant value) {
    SampleValue sampleValue = new SampleValueInstant(value);
    List<SampleValue> sampleValues =
        Stream.concat(getValues().stream(), Stream.of(sampleValue)).collect(Collectors.toList());
    return new SampleField(this.name, this.type, sampleValues);
  }
}
