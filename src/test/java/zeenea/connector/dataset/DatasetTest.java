package zeenea.connector.dataset;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import zeenea.connector.common.*;
import zeenea.connector.field.Field;

class DatasetTest {

  @Test
  @DisplayName("Dataset builder should create dataset")
  void shouldCreateDatasetWithBuilder() {
    List<Field> fields =
        List.of(
            Field.builder()
                .id(ItemIdentifier.of(IdentificationProperty.of("key", "field")))
                .name("FieldName")
                .dataType(DataType.String)
                .nativeType("String")
                .nativeIndex(1)
                .nullable(true)
                .multivalued(false)
                .description("Field description")
                .build());
    List<String> primaryKeys = List.of("key1", "key2");
    List<ForeignKey> foreignKeys =
        List.of(
            ForeignKey.builder()
                .targetDataset("Dataset2")
                .sourceFields(List.of("foreignKey1"))
                .targetFields(List.of("primaryKey1"))
                .name("foreignKey1")
                .build());
    List<Partitioning> partitions =
        List.of(Partitioning.builder().partitionType("type").column("field").build());
    List<ItemReference> sourceDatasets =
        List.of(
            ItemReference.of(
                ItemIdentifier.of(List.of(IdentificationProperty.of("name", "source1"))),
                DataSourceIdentifier.of(
                    List.of(
                        IdentificationProperty.of("host", "localhost"),
                        IdentificationProperty.of("port", "1111")))));
    ItemIdentifier itemIdentifier =
        ItemIdentifier.of(List.of(IdentificationProperty.of("key", "dataset1")));
    Dataset dataset =
        Dataset.builder()
            .id(itemIdentifier)
            .name("Dataset1")
            .description("Description")
            .fields(fields)
            .primaryKeys(primaryKeys)
            .foreignKeys(foreignKeys)
            .partitions(partitions)
            .sourceDatasets(sourceDatasets)
            .build();
    assertNotNull(dataset);
    assertEquals(itemIdentifier, dataset.getId());
    assertEquals(fields, dataset.getFields());
    assertEquals(primaryKeys, dataset.getPrimaryKeys());
    assertEquals(foreignKeys, dataset.getForeignKeys());
    assertEquals(partitions, dataset.getPartitions());
    assertEquals(sourceDatasets, dataset.getSourceDatasets());
  }

  @Test
  @DisplayName("Dataset should be equal to another with same properties")
  void shouldBeEqualToAnotherWithSameProperties() {
    List<Field> fields =
        List.of(
            Field.builder()
                .id(ItemIdentifier.of(IdentificationProperty.of("key", "field")))
                .name("FieldName")
                .dataType(DataType.String)
                .nativeType("String")
                .nativeIndex(1)
                .nullable(true)
                .multivalued(false)
                .description("Field description")
                .build());
    List<String> primaryKeys = List.of("key1", "key2");
    List<ForeignKey> foreignKeys =
        List.of(
            ForeignKey.builder()
                .targetDataset("dataset2")
                .sourceFields(List.of("foreignKey1"))
                .targetFields(List.of("primaryKey1"))
                .name("foreignKey1")
                .build());
    List<Partitioning> partitions =
        List.of(Partitioning.builder().partitionType("type").column("field").build());
    List<ItemReference> sourceDatasets =
        List.of(
            ItemReference.of(
                ItemIdentifier.of(List.of(IdentificationProperty.of("name", "source1"))),
                DataSourceIdentifier.of(
                    List.of(
                        IdentificationProperty.of("host", "localhost"),
                        IdentificationProperty.of("port", "1111")))));
    ItemIdentifier itemIdentifier =
        ItemIdentifier.of(List.of(IdentificationProperty.of("key", "dataset1")));
    Dataset dataset1 =
        Dataset.builder()
            .id(itemIdentifier)
            .name("Dataset1")
            .description("Description")
            .fields(fields)
            .primaryKeys(primaryKeys)
            .foreignKeys(foreignKeys)
            .partitions(partitions)
            .sourceDatasets(sourceDatasets)
            .build();
    Dataset dataset2 =
        Dataset.builder()
            .id(itemIdentifier)
            .name("Dataset1")
            .description("Description")
            .fields(fields)
            .primaryKeys(primaryKeys)
            .foreignKeys(foreignKeys)
            .partitions(partitions)
            .sourceDatasets(sourceDatasets)
            .build();
    assertEquals(dataset1, dataset2);
    assertEquals(dataset1.hashCode(), dataset2.hashCode());
  }

  @Test
  @DisplayName("Dataset should not be equal to another with different properties")
  void shouldNotBeEqualToAnotherWithDifferentProperties() {
    Dataset dataset1 =
        Dataset.builder()
            .id(ItemIdentifier.of(List.of(IdentificationProperty.of("key", "dataset1"))))
            .name("Dataset1")
            .description("Description")
            .fields(
                List.of(
                    Field.builder()
                        .id(ItemIdentifier.of(IdentificationProperty.of("key", "field")))
                        .name("FieldName")
                        .dataType(DataType.String)
                        .nativeType("String")
                        .nativeIndex(1)
                        .nullable(true)
                        .multivalued(false)
                        .description("Field description")
                        .build()))
            .primaryKeys(List.of("key1", "key2"))
            .foreignKeys(
                List.of(
                    ForeignKey.builder()
                        .targetDataset("dataset2")
                        .sourceFields(List.of("foreignKey1"))
                        .targetFields(List.of("primaryKey1"))
                        .name("foreignKey1")
                        .build()))
            .partitions(
                List.of(Partitioning.builder().partitionType("type").column("field").build()))
            .sourceDatasets(
                List.of(
                    ItemReference.of(
                        ItemIdentifier.of(List.of(IdentificationProperty.of("name", "source1"))),
                        DataSourceIdentifier.of(
                            List.of(
                                IdentificationProperty.of("host", "localhost"),
                                IdentificationProperty.of("port", "1111"))))))
            .build();
    Dataset dataset2 =
        Dataset.builder()
            .id(ItemIdentifier.of(List.of(IdentificationProperty.of("key", "dataset2"))))
            .name("Dataset2")
            .description("Description")
            .fields(List.of())
            .primaryKeys(List.of())
            .foreignKeys(List.of())
            .partitions(List.of())
            .sourceDatasets(List.of())
            .build();
    assertNotEquals(dataset1, dataset2);
  }

  @Test
  @DisplayName("Dataset builder should fail with null fields")
  void builderShouldFailWithNullFields() {
    List<String> primaryKeys = List.of("key1", "key2");
    List<ForeignKey> foreignKeys =
        List.of(
            ForeignKey.builder()
                .targetDataset("Dataset2")
                .sourceFields(List.of("foreignKey1"))
                .targetFields(List.of("primaryKey1"))
                .name("foreignKey1")
                .build());
    List<Partitioning> partitions =
        List.of(Partitioning.builder().partitionType("type").column("field").build());
    List<ItemReference> sourceDatasets =
        List.of(
            ItemReference.of(
                ItemIdentifier.of(List.of(IdentificationProperty.of("name", "source1"))),
                DataSourceIdentifier.of(
                    List.of(
                        IdentificationProperty.of("host", "localhost"),
                        IdentificationProperty.of("port", "1111")))));
    assertThrows(
        NullPointerException.class,
        () ->
            Dataset.builder()
                .id(ItemIdentifier.of(List.of(IdentificationProperty.of("key", "dataset1"))))
                .name("Dataset1")
                .description("Description")
                .fields((Field) null)
                .primaryKeys(primaryKeys)
                .foreignKeys(foreignKeys)
                .partitions(partitions)
                .sourceDatasets(sourceDatasets)
                .build());
  }
}
