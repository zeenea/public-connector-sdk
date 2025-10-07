package zeenea.connector.sample;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DataSampleTest {

  @Test
  @DisplayName("Ecrire un data sample avec un header string et des valeurs string")
  public void testSample() {
    DataSample dataSample =
        new DataSample(List.of(SampleField.of("header1", List.of("valeur1", "valeur2"))));
    Assertions.assertThat(dataSample)
        .isEqualTo(
            new DataSample(List.of(SampleField.of("header1", List.of("valeur1", "valeur2")))));
  }

  @Test
  @DisplayName("Ecrire un data sample avec un header integer et des valeurs string")
  public void testSampleIntegerHeader() {
    DataSample dataSample = new DataSample(List.of(SampleField.of("header1", List.of(1, 2))));
    Assertions.assertThat(dataSample)
        .isEqualTo(new DataSample(List.of(SampleField.of("header1", List.of(1, 2)))));
  }

  @Test
  @DisplayName(
      "Devrait échouer car le nombre d'élément ne sont pas identique entre tous les champs")
  public void testSampleIntegerHeader2() {
    DataSample dataSample =
        new DataSample(
            List.of(
                SampleField.of("header1", List.of(1, 2)),
                SampleField.of("header2", List.of("valeur1", "valeur2", "valeur3"))));
    Assertions.assertThat(dataSample)
        .isEqualTo(
            new DataSample(
                List.of(
                    SampleField.of("header1", List.of(1, 2)),
                    SampleField.of("header2", List.of("valeur1", "valeur2", "valeur3")))));
  }
}
