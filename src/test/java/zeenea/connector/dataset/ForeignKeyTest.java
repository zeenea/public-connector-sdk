package zeenea.connector.dataset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static zeenea.connector.dataset.DatasetTest.FIELD_KEY;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import zeenea.connector.common.IdentificationProperty;
import zeenea.connector.common.ItemIdentifier;

public class ForeignKeyTest {

  @Test
  @DisplayName("Foreign should implement equals and hashcode correctly")
  void shouldImplementEqualToAndHashCode() {
    EqualsVerifier.forClass(ForeignKey.class).verify();
  }

  @Test
  @DisplayName("Foreign should implement toString correctly")
  void shouldImplementToString() {
    String result =
        ForeignKey.builder()
            .targetDatasetIdentifier(ItemIdentifier.of(IdentificationProperty.of("id", "dataset2")))
            .sourceFieldIdentifiers(
                ItemIdentifier.of(IdentificationProperty.of(FIELD_KEY, "foreign_key")))
            .targetFieldIdentifiers(
                ItemIdentifier.of(IdentificationProperty.of(FIELD_KEY, "primary_key")))
            .name("foreignKey1")
            .build()
            .toString();

    assertEquals(
        "ForeignKey{name='foreignKey1', targetDatasetIdentifier='ItemIdentifier{identificationProperties=[IdentificationProperty{key='id', value='dataset2'}]}', sourceFieldIdentifiers=[ItemIdentifier{identificationProperties=[IdentificationProperty{key='field_key', value='foreign_key'}]}], targetFieldIdentifiers=[ItemIdentifier{identificationProperties=[IdentificationProperty{key='field_key', value='primary_key'}]}]}",
        result);
  }
}
