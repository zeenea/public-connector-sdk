package zeenea.connector.dataproduct;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DataContractTest {

  @Test
  @DisplayName("DataContract factory should create data contract")
  void shouldCreateDataContract() {
    DataContract dataContract = DataContract.of(DataContract.Type.Custom, "sourceValue");
    assertThat(dataContract).isNotNull();
    assertThat(dataContract.getType()).isEqualTo(DataContract.Type.Custom);
    assertThat(dataContract.getSource()).isEqualTo("sourceValue");
  }

  @Test
  @DisplayName("DataContract should be equal to another with same properties")
  void shouldBeEqualToAnotherWithSameProperties() {
    DataContract dataContract1 = DataContract.of(DataContract.Type.Custom, "sourceValue");
    DataContract dataContract2 = DataContract.of(DataContract.Type.Custom, "sourceValue");
    assertThat(dataContract1).isEqualTo(dataContract2);
    assertThat(dataContract1.hashCode()).isEqualTo(dataContract2.hashCode());
  }

  @Test
  @DisplayName("DataContract should not be equal to another with different properties")
  void shouldNotBeEqualToAnotherWithDifferentProperties() {
    DataContract dataContract1 = DataContract.of(DataContract.Type.Custom, "sourceValue1");
    DataContract dataContract2 =
        DataContract.of(DataContract.Type.DataContractDotCom, "sourceValue2");
    assertThat(dataContract1).isNotEqualTo(dataContract2);
  }

  @Test
  @DisplayName(
      "DataContract factory should not fail with null type and return Custom type contract")
  void shouldFailWithNullType() {
    assertThat(DataContract.of(null, "sourceValue"))
        .isEqualTo(DataContract.of(DataContract.Type.Custom, "sourceValue"));
  }

  @Test
  @DisplayName("DataContract factory should fail with null source")
  void shouldFailWithNullSource() {
    assertThatThrownBy(() -> DataContract.of(DataContract.Type.Custom, null))
        .isInstanceOf(NullPointerException.class);
  }
}
