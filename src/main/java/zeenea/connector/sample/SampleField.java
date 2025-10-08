package zeenea.connector.sample;

import java.util.Collections;
import java.util.List;
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
    return Collections.unmodifiableList(values);
  }

  public static SampleField of(String header, DataType dataType, List<SampleValue> values) {
    return new SampleField(header, dataType, values);
  }

  public static SampleField ofStrings(String header, List<String> values) {
    List<SampleValue> collect =
        values.stream().map(SampleValueString::new).collect(Collectors.toList());
    return new SampleField(header, DataType.String, collect);
  }

  public SampleField addString(String value) {
    SampleValue sampleValueString = new SampleValueString(value);
    List<SampleValue> collect =
        Stream.concat(this.values.stream(), Stream.of(sampleValueString))
            .collect(Collectors.toList());
    return new SampleField(this.name, this.type, collect);
  }

  public SampleField addInteger(Integer value) {
    SampleValue sampleValue = new SampleValueInteger(value);
    List<SampleValue> collect =
        Stream.concat(this.values.stream(), Stream.of(sampleValue)).collect(Collectors.toList());
    return new SampleField(this.name, this.type, collect);
  }

  public SampleField add(SampleValue value) {
    List<SampleValue> collect =
        Stream.concat(this.values.stream(), Stream.of(value)).collect(Collectors.toList());
    return new SampleField(this.name, this.type, collect);
  }

  //  public static SampleField<Integer> ofIntegers(String header, List<Integer> values) {
  //    return new SampleField<>(header, DataType.Integer, values);
  //  }
  //
  //  public static SampleField<Long> ofLongs(String header, List<Long> values) {
  //    return new SampleField<>(header, DataType.Long, values);
  //  }
  //
  //  public static SampleField<BigDecimal> ofBigDecimals(String header, List<BigDecimal> values) {
  //    return new SampleField<>(header, DataType.BigDecimal, values);
  //  }
  //
  //  public static SampleField<Double> ofDoubles(String header, List<Double> values) {
  //    return new SampleField<>(header, DataType.Double, values);
  //  }
  //
  //  public static SampleField<Boolean> ofBooleans(String header, List<Boolean> values) {
  //    return new SampleField<>(header, DataType.Boolean, values);
  //  }
  //
  //  public static SampleField<LocalDate> ofLocalDates(String header, List<LocalDate> values) {
  //    return new SampleField<>(header, DataType.Date, values);
  //  }
  //
  //  public static SampleField<LocalTime> ofLocalTimes(String header, List<LocalTime> values) {
  //    return new SampleField<>(header, DataType.Time, values);
  //  }
  //
  //  public static SampleField<LocalDateTime> ofLocalDateTimes(
  //      String header, List<LocalDateTime> values) {
  //    return new SampleField<>(header, DataType.Date, values);
  //  }
  //
  //  public static SampleField<Instant> ofInstants(String header, List<Instant> values) {
  //    return new SampleField<>(header, DataType.Timestamp, values);
  //  }
}
