package zeenea.connector.sample;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DataSampleV2Test {

  @Test
  @DisplayName("Ecrire un data sample avec un header et des valeurs string")
  public void testSample() {
    DataSampleV2 dataSample =
        DataSampleV2.builder()
            .field(SampleField.ofStrings("header1", List.of("valeur1", "valeur2")))
            .build();

    Assertions.assertThat(dataSample)
        .isEqualTo(
            new DataSampleV2(
                List.of(SampleField.ofStrings("header1", List.of("valeur1", "valeur2")))));
  }

  @Test
  @DisplayName("Ecrire un data sample avec un header et des valeurs integer")
  public void testSampleIntegerHeader() {
    DataSampleV2 dataSample =
        DataSampleV2.builder().field(SampleField.ofIntegers("header1", List.of(1, 2))).build();

    Assertions.assertThat(dataSample)
        .isEqualTo(new DataSampleV2(List.of(SampleField.ofIntegers("header1", List.of(1, 2)))));
  }
}
