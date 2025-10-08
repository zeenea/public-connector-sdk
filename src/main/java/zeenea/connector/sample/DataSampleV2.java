package zeenea.connector.sample;

import java.util.List;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Value
@Builder
public class DataSampleV2 {

  @Singular List<SampleField<?>> fields;

//  public void addRow(List<Object> rows) {
//      rows.stream().map(row -> row.)
//  }
}
