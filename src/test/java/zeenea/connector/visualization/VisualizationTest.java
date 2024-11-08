package zeenea.connector.visualization;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import zeenea.connector.common.ConnectionReferenceCode;
import zeenea.connector.common.IdentificationProperty;
import zeenea.connector.common.ItemIdentifier;
import zeenea.connector.common.ItemReference;
import zeenea.connector.dataset.DataType;
import zeenea.connector.field.Field;

class VisualizationTest {

  @Test
  @DisplayName("Visualization builder should create visualization")
  void shouldCreateVisualizationWithBuilder() {
    List<Field> fields =
        List.of(
            Field.builder()
                .id(ItemIdentifier.of(IdentificationProperty.of("key", "field")))
                .name("field")
                .dataType(DataType.String)
                .nativeType("String")
                .nativeIndex(1)
                .nullable(true)
                .multivalued(false)
                .description("Field description")
                .build());
    List<ItemReference> linkedDataset =
        List.of(
            ItemReference.of(
                ItemIdentifier.of(List.of(IdentificationProperty.of("name", "source"))),
                ConnectionReferenceCode.of("reference")));
    ItemIdentifier itemIdentifier =
        ItemIdentifier.of(List.of(IdentificationProperty.of("key", "visualization")));
    Visualization visualization =
        Visualization.builder()
            .id(itemIdentifier)
            .name("Visualization")
            .description("Description")
            .fields(fields)
            .sourceDatasets(linkedDataset)
            .build();
    assertNotNull(visualization);
    assertEquals(itemIdentifier, visualization.getId());
    assertEquals(fields, visualization.getFields());
    assertEquals(linkedDataset, visualization.getSourceDatasets());
  }

  @Test
  @DisplayName("Visualization should be equal to another with same properties")
  void shouldBeEqualToAnotherWithSameProperties() {
    List<Field> fields =
        List.of(
            Field.builder()
                .id(ItemIdentifier.of(IdentificationProperty.of("key", "field")))
                .name("field")
                .dataType(DataType.String)
                .nativeType("String")
                .nativeIndex(1)
                .nullable(true)
                .multivalued(false)
                .description("Field description")
                .build());
    List<ItemReference> linkedDataset =
        List.of(
            ItemReference.of(
                ItemIdentifier.of(List.of(IdentificationProperty.of("name", "source"))),
                ConnectionReferenceCode.of("reference")));
    ItemIdentifier itemIdentifier =
        ItemIdentifier.of(List.of(IdentificationProperty.of("key", "visualization")));
    Visualization visualization1 =
        Visualization.builder()
            .id(itemIdentifier)
            .name("Visualization")
            .description("Description")
            .fields(fields)
            .sourceDatasets(linkedDataset)
            .build();
    Visualization visualization2 =
        Visualization.builder()
            .id(itemIdentifier)
            .name("Visualization")
            .description("Description")
            .fields(fields)
            .sourceDatasets(linkedDataset)
            .build();
    assertEquals(visualization1, visualization2);
    assertEquals(visualization1.hashCode(), visualization2.hashCode());
  }

  @Test
  @DisplayName("Visualization should not be equal to another with different properties")
  void shouldNotBeEqualToAnotherWithDifferentProperties() {
    List<Field> fields =
        List.of(
            Field.builder()
                .id(ItemIdentifier.of(IdentificationProperty.of("key", "field")))
                .name("field")
                .dataType(DataType.String)
                .nativeType("String")
                .nativeIndex(1)
                .nullable(true)
                .multivalued(false)
                .description("Field description")
                .build());
    List<ItemReference> linkedDataset =
        List.of(
            ItemReference.of(
                ItemIdentifier.of(List.of(IdentificationProperty.of("name", "source"))),
                ConnectionReferenceCode.of("reference")));

    Visualization visualization1 =
        Visualization.builder()
            .id(ItemIdentifier.of(List.of(IdentificationProperty.of("key", "visualization1"))))
            .name("Visualization1")
            .description("Description")
            .fields(fields)
            .sourceDatasets(linkedDataset)
            .build();
    Visualization visualization2 =
        Visualization.builder()
            .id(ItemIdentifier.of(List.of(IdentificationProperty.of("key", "visualization2"))))
            .name("Visualization2")
            .description("Description")
            .fields(List.of())
            .sourceDatasets(List.of())
            .build();
    assertNotEquals(visualization1, visualization2);
  }

  @Test
  @DisplayName("Visualization builder should fail with null fields")
  void builderShouldFailWithNullFields() {
    List<ItemReference> linkedDataset =
        List.of(
            ItemReference.of(
                ItemIdentifier.of(List.of(IdentificationProperty.of("name", "source"))),
                ConnectionReferenceCode.of("reference")));
    assertThrows(
        NullPointerException.class,
        () ->
            Visualization.builder()
                .id(ItemIdentifier.of(List.of(IdentificationProperty.of("key", "visualization"))))
                .name("Visualization")
                .description("Description")
                .fields((List<Field>) null)
                .sourceDatasets(linkedDataset)
                .build());
  }

  @Test
  @DisplayName("Visualization builder should fail with null linked dataset")
  void builderShouldFailWithNullLinkedDataset() {
    List<Field> fields =
        List.of(
            Field.builder()
                .id(ItemIdentifier.of(IdentificationProperty.of("key", "field")))
                .name("field")
                .dataType(DataType.String)
                .nativeType("String")
                .nativeIndex(1)
                .nullable(true)
                .multivalued(false)
                .description("Field description")
                .build());
    assertThrows(
        NullPointerException.class,
        () ->
            Visualization.builder()
                .id(ItemIdentifier.of(List.of(IdentificationProperty.of("key", "visualization"))))
                .name("Visualization")
                .description("Description")
                .fields(fields)
                .sourceDatasets((List<ItemReference>) null)
                .build());
  }
}
