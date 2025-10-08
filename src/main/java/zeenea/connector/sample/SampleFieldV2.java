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
public class SampleFieldV2 {
  String name;
  DataType type;
  @Singular List<SampleValue> values;

  public List<SampleValue> getValues() {
    return Optional.ofNullable(values).map(List::copyOf).orElse(Collections.emptyList());
  }

  public static SampleFieldV2 of(String header, DataType dataType, List<SampleValue> values) {
    return new SampleFieldV2(header, dataType, values);
  }

  public SampleFieldV2 add(SampleValue value) {
    List<SampleValue> collect =
        Stream.concat(getValues().stream(), Stream.of(value)).collect(Collectors.toList());
    return new SampleFieldV2(this.name, this.type, collect);
  }

  public SampleFieldV2 add(List<SampleValue> values) {
    List<SampleValue> sampleValues =
        Stream.concat(getValues().stream(), values.stream()).collect(Collectors.toList());
    return new SampleFieldV2(this.name, this.type, sampleValues);
  }

  public static SampleFieldV2 ofStrings(String header, List<String> values) {
    List<SampleValue> samplesValues =
        values.stream().map(SampleValueString::new).collect(Collectors.toList());
    return new SampleFieldV2(header, DataType.String, samplesValues);
  }

  public SampleFieldV2 addString(String value) {
    SampleValue sampleValue = new SampleValueString(value);
    List<SampleValue> sampleValues =
        Stream.concat(getValues().stream(), Stream.of(sampleValue)).collect(Collectors.toList());
    return new SampleFieldV2(this.name, this.type, sampleValues);
  }

  public static SampleFieldV2 ofIntegers(String header, List<Integer> values) {
    List<SampleValue> sampleValues =
        values.stream().map(SampleValueInteger::new).collect(Collectors.toList());
    return new SampleFieldV2(header, DataType.Integer, sampleValues);
  }

  public SampleFieldV2 addInteger(Integer value) {
    SampleValue sampleValue = new SampleValueInteger(value);
    List<SampleValue> sampleValues =
        Stream.concat(getValues().stream(), Stream.of(sampleValue)).collect(Collectors.toList());
    return new SampleFieldV2(this.name, this.type, sampleValues);
  }

  public static SampleFieldV2 ofLongs(String header, List<Long> values) {
    List<SampleValue> sampleValues =
        values.stream().map(SampleValueLong::new).collect(Collectors.toList());
    return new SampleFieldV2(header, DataType.Integer, sampleValues);
  }

  public SampleFieldV2 addLong(Long value) {
    SampleValue sampleValue = new SampleValueLong(value);
    List<SampleValue> sampleValues =
        Stream.concat(getValues().stream(), Stream.of(sampleValue)).collect(Collectors.toList());
    return new SampleFieldV2(this.name, this.type, sampleValues);
  }

  public static SampleFieldV2 ofBigDecimals(String header, List<BigDecimal> values) {
    List<SampleValue> sampleValues =
        values.stream().map(SampleValueBigDecimal::new).collect(Collectors.toList());
    return new SampleFieldV2(header, DataType.BigDecimal, sampleValues);
  }

  public SampleFieldV2 addBigDecimal(BigDecimal value) {
    SampleValue sampleValue = new SampleValueBigDecimal(value);
    List<SampleValue> sampleValues =
        Stream.concat(getValues().stream(), Stream.of(sampleValue)).collect(Collectors.toList());
    return new SampleFieldV2(this.name, this.type, sampleValues);
  }

  public static SampleFieldV2 ofDoubles(String header, List<Double> values) {
    List<SampleValue> sampleValues =
        values.stream().map(SampleValueDouble::new).collect(Collectors.toList());
    return new SampleFieldV2(header, DataType.Double, sampleValues);
  }

  public SampleFieldV2 addDouble(Double value) {
    SampleValue sampleValue = new SampleValueDouble(value);
    List<SampleValue> sampleValues =
        Stream.concat(getValues().stream(), Stream.of(sampleValue)).collect(Collectors.toList());
    return new SampleFieldV2(this.name, this.type, sampleValues);
  }

  public static SampleFieldV2 ofBooleans(String header, List<Boolean> values) {
    List<SampleValue> sampleValues =
        values.stream().map(SampleValueBoolean::new).collect(Collectors.toList());
    return new SampleFieldV2(header, DataType.Boolean, sampleValues);
  }

  public SampleFieldV2 addBoolean(Boolean value) {
    SampleValue sampleValue = new SampleValueBoolean(value);
    List<SampleValue> sampleValues =
        Stream.concat(getValues().stream(), Stream.of(sampleValue)).collect(Collectors.toList());
    return new SampleFieldV2(this.name, this.type, sampleValues);
  }

  public static SampleFieldV2 ofLocalDates(String header, List<LocalDate> values) {
    List<SampleValue> sampleValues =
        values.stream().map(SampleValueLocalDate::new).collect(Collectors.toList());
    return new SampleFieldV2(header, DataType.Date, sampleValues);
  }

  public SampleFieldV2 addLocalDate(LocalDate value) {
    SampleValue sampleValue = new SampleValueLocalDate(value);
    List<SampleValue> sampleValues =
        Stream.concat(getValues().stream(), Stream.of(sampleValue)).collect(Collectors.toList());
    return new SampleFieldV2(this.name, this.type, sampleValues);
  }

  public static SampleFieldV2 ofLocalTimes(String header, List<LocalTime> values) {
    List<SampleValue> sampleValues =
        values.stream().map(SampleValueLocalTime::new).collect(Collectors.toList());
    return new SampleFieldV2(header, DataType.Time, sampleValues);
  }

  public SampleFieldV2 addLocalTime(LocalTime value) {
    SampleValue sampleValue = new SampleValueLocalTime(value);
    List<SampleValue> sampleValues =
        Stream.concat(getValues().stream(), Stream.of(sampleValue)).collect(Collectors.toList());
    return new SampleFieldV2(this.name, this.type, sampleValues);
  }

  public static SampleFieldV2 ofLocalDateTimes(String header, List<LocalDateTime> values) {
    List<SampleValue> sampleValues =
        values.stream().map(SampleValueLocalDateTime::new).collect(Collectors.toList());
    return new SampleFieldV2(header, DataType.Timestamp, sampleValues);
  }

  public SampleFieldV2 addLocalDateTime(LocalDateTime value) {
    SampleValue sampleValue = new SampleValueLocalDateTime(value);
    List<SampleValue> sampleValues =
        Stream.concat(getValues().stream(), Stream.of(sampleValue)).collect(Collectors.toList());
    return new SampleFieldV2(this.name, this.type, sampleValues);
  }

  public static SampleFieldV2 ofInstants(String header, List<Instant> values) {
    List<SampleValue> sampleValues =
        values.stream().map(SampleValueInstant::new).collect(Collectors.toList());
    return new SampleFieldV2(header, DataType.Timestamp, sampleValues);
  }

  public SampleFieldV2 addInstant(Instant value) {
    SampleValue sampleValue = new SampleValueInstant(value);
    List<SampleValue> sampleValues =
        Stream.concat(getValues().stream(), Stream.of(sampleValue)).collect(Collectors.toList());
    return new SampleFieldV2(this.name, this.type, sampleValues);
  }
}
