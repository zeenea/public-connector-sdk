package zeenea.connector.dataproduct;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DataContractTest {

  @Test
  @DisplayName("DataContract factory should create data contract")
  void shouldCreateDataContract() {
    DataContract dataContract = DataContract.of(DataContract.Type.Custom, "sourceValue");
    assertNotNull(dataContract);
    assertEquals(DataContract.Type.Custom, dataContract.getType());
    assertEquals("sourceValue", dataContract.getSource());
  }

  @Test
  @DisplayName("DataContract should be equal to another with same properties")
  void shouldBeEqualToAnotherWithSameProperties() {
    DataContract dataContract1 = DataContract.of(DataContract.Type.Custom, "sourceValue");
    DataContract dataContract2 = DataContract.of(DataContract.Type.Custom, "sourceValue");
    assertEquals(dataContract1, dataContract2);
    assertEquals(dataContract1.hashCode(), dataContract2.hashCode());
  }

  @Test
  @DisplayName("DataContract should not be equal to another with different properties")
  void shouldNotBeEqualToAnotherWithDifferentProperties() {
    DataContract dataContract1 = DataContract.of(DataContract.Type.Custom, "sourceValue1");
    DataContract dataContract2 =
        DataContract.of(DataContract.Type.DataContractDotCom, "sourceValue2");
    assertNotEquals(dataContract1, dataContract2);
  }

  @Test
  @DisplayName(
      "DataContract factory should not fail with null type and return Custom type contract")
  void shouldFailWithNullType() {
    assertEquals(
        DataContract.of(null, "sourceValue"),
        DataContract.of(DataContract.Type.Custom, "sourceValue"));
  }

  @Test
  @DisplayName("DataContract factory should fail with null source")
  void shouldFailWithNullSource() {
    assertThrows(NullPointerException.class, () -> DataContract.of(DataContract.Type.Custom, null));
  }
}
