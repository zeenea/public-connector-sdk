package zeenea.connector.sample;

import java.util.List;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Value
@Builder
public class DataSample {

  @Singular List<SampleField<?>> fields;
}
