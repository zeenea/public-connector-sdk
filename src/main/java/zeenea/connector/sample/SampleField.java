package zeenea.connector.sample;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import zeenea.connector.dataset.DataType;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SampleField<T> {
  String name;
  DataType type;
  List<T> values;

  // WARNING -> we want all the methods to be named "of" but with different parameters
  public static SampleField<String> ofStrings(String header, List<String> values) {
    return new SampleField<>(header, DataType.String, values);
  }

  public static SampleField<Integer> ofIntegers(String header, List<Integer> values) {
    return new SampleField<>(header, DataType.Integer, values);
  }

  public static SampleField<Long> ofLongs(String header, List<Long> values) {
    return new SampleField<>(header, DataType.Long, values);
  }

  public static SampleField<BigDecimal> ofBigDecimals(String header, List<BigDecimal> values) {
    return new SampleField<>(header, DataType.BigDecimal, values);
  }

  public static SampleField<Double> ofDoubles(String header, List<Double> values) {
    return new SampleField<>(header, DataType.Double, values);
  }

  public static SampleField<Boolean> ofBooleans(String header, List<Boolean> values) {
    return new SampleField<>(header, DataType.Boolean, values);
  }

  public static SampleField<LocalDate> ofLocalDates(String header, List<LocalDate> values) {
    return new SampleField<>(header, DataType.Date, values);
  }

  public static SampleField<LocalTime> ofLocalTimes(String header, List<LocalTime> values) {
    return new SampleField<>(header, DataType.Time, values);
  }

  public static SampleField<LocalDateTime> ofLocalDateTimes(
      String header, List<LocalDateTime> values) {
    return new SampleField<>(header, DataType.Date, values);
  }

  public static SampleField<Instant> ofInstants(String header, List<Instant> values) {
    return new SampleField<>(header, DataType.Timestamp, values);
  }
}
