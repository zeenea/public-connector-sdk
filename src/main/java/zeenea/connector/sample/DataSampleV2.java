package zeenea.connector.sample;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Value
@Builder
public class DataSampleV2 {

  @Singular List<SampleFieldV2> fields;

  public DataSampleV2 addFieldValues(List<SampleValue> sampleValues) {
    AtomicInteger index = new AtomicInteger(0);

    List<? extends SampleFieldV2> sampleFields =
        sampleValues.stream()
            .map(
                row -> {
                  SampleFieldV2 sampleField = fields.get(index.getAndIncrement());
                  return sampleField.add(row);
                })
            .collect(Collectors.toList());

    return DataSampleV2.builder().fields(sampleFields).build();
  }

  public DataSampleV2 addFieldValues2(List<Object> sampleValues) {
    AtomicInteger index = new AtomicInteger(0);

    List<? extends SampleFieldV2> sampleFields =
        sampleValues.stream()
            .map(
                row -> {
                  SampleFieldV2 sampleField = fields.get(index.getAndIncrement());
                  return addValue(sampleField, row);
                })
            .collect(Collectors.toList());

    return DataSampleV2.builder().fields(sampleFields).build();
  }

  private SampleFieldV2 addValue(SampleFieldV2 field, Object object) {
    switch (field.getType()) {
      case String:
        return field.addString((String) object);
      case Integer:
        return field.addInteger((Integer) object);
      default:
        throw new IllegalArgumentException("Unsupported data type: " + object);
    }
  }
}
