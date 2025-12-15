package zeenea.connector.dataset;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import zeenea.connector.common.*;
import zeenea.connector.field.Field;

class DatasetTest {

  public static String FIELD_KEY = "field_key";

  @Test
  @DisplayName("Dataset builder should create dataset")
  void shouldCreateDatasetWithBuilder() {
    List<Field> fields =
        List.of(
            Field.builder()
                .id(ItemIdentifier.of(IdentificationProperty.of(FIELD_KEY, "field")))
                .name("FieldName")
                .dataType(DataType.String)
                .nativeType("String")
                .nativeIndex(1)
                .nullable(true)
                .multivalued(false)
                .description("Field description")
                .build());
    List<ItemIdentifier> primaryKeyIdentifiers =
        List.of(ItemIdentifier.of(IdentificationProperty.of(FIELD_KEY, "primary_key")));
    List<ForeignKey> foreignKeys =
        List.of(
            ForeignKey.builder()
                .targetDatasetIdentifier(
                    ItemIdentifier.of(IdentificationProperty.of("id", "dataset2")))
                .sourceFieldIdentifiers(
                    List.of(ItemIdentifier.of(IdentificationProperty.of(FIELD_KEY, "foreign_key"))))
                .targetFieldIdentifiers(
                    List.of(ItemIdentifier.of(IdentificationProperty.of(FIELD_KEY, "primary_key"))))
                .name("foreignKey1")
                .build());
    List<ItemReference> sourceDatasets =
        List.of(
            ItemReference.of(
                ItemIdentifier.of(List.of(IdentificationProperty.of("name", "source1"))),
                DataSourceIdentifier.of(
                    List.of(
                        IdentificationProperty.of("host", "localhost"),
                        IdentificationProperty.of("port", "1111")))));
    List<QueryReference> sourceQueries =
        List.of(
            QueryReference.of(
                "SELECT * FROM table",
                SqlDialect.MYSQL,
                DataSourceIdentifier.of(List.of(IdentificationProperty.of("host", "localhost")))));
    ItemIdentifier itemIdentifier =
        ItemIdentifier.of(List.of(IdentificationProperty.of("key", "dataset1")));
    Dataset dataset =
        Dataset.builder()
            .id(itemIdentifier)
            .name("Dataset1")
            .description("Description")
            .fields(fields)
            .primaryKeyIdentifiers(
                List.of(ItemIdentifier.of(IdentificationProperty.of(FIELD_KEY, "primary_key"))))
            .foreignKeys(foreignKeys)
            .sourceDatasets(sourceDatasets)
            .sourceQueries(sourceQueries)
            .build();
    assertNotNull(dataset);
    assertEquals(itemIdentifier, dataset.getId());
    assertEquals(fields, dataset.getFields());
    assertEquals(primaryKeyIdentifiers, dataset.getPrimaryKeyIdentifiers());
    assertEquals(foreignKeys, dataset.getForeignKeys());
    assertEquals(sourceDatasets, dataset.getSourceDatasets());
    assertEquals(sourceQueries, dataset.getSourceQueries());
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
    List<ItemIdentifier> primaryKeyIdentifiers =
        List.of(
            ItemIdentifier.of(IdentificationProperty.of(FIELD_KEY, "primary_key1")),
            ItemIdentifier.of(IdentificationProperty.of(FIELD_KEY, "primary_key2")));
    List<ForeignKey> foreignKeys =
        List.of(
            ForeignKey.builder()
                .targetDatasetIdentifier(
                    ItemIdentifier.of(IdentificationProperty.of("id", "dataset2")))
                .sourceFieldIdentifiers(
                    List.of(
                        ItemIdentifier.of(IdentificationProperty.of(FIELD_KEY, "foreign_key1"))))
                .targetFieldIdentifiers(
                    List.of(
                        ItemIdentifier.of(IdentificationProperty.of(FIELD_KEY, "primary_key1"))))
                .name("foreignKey1")
                .build());
    List<ItemReference> sourceDatasets =
        List.of(
            ItemReference.of(
                ItemIdentifier.of(List.of(IdentificationProperty.of("name", "source1"))),
                DataSourceIdentifier.of(
                    List.of(
                        IdentificationProperty.of("host", "localhost"),
                        IdentificationProperty.of("port", "1111")))));
    List<QueryReference> sourceQueries = List.of(QueryReference.of("SELECT 1", SqlDialect.MYSQL));
    ItemIdentifier itemIdentifier =
        ItemIdentifier.of(List.of(IdentificationProperty.of("key", "dataset1")));
    Dataset dataset1 =
        Dataset.builder()
            .id(itemIdentifier)
            .name("Dataset1")
            .description("Description")
            .fields(fields)
            .primaryKeyIdentifiers(primaryKeyIdentifiers)
            .foreignKeys(foreignKeys)
            .sourceDatasets(sourceDatasets)
            .sourceQueries(sourceQueries)
            .build();
    Dataset dataset2 =
        Dataset.builder()
            .id(itemIdentifier)
            .name("Dataset1")
            .description("Description")
            .fields(fields)
            .primaryKeyIdentifiers(primaryKeyIdentifiers)
            .foreignKeys(foreignKeys)
            .sourceDatasets(sourceDatasets)
            .sourceQueries(sourceQueries)
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
            .primaryKeyIdentifiers(
                List.of(
                    ItemIdentifier.of(IdentificationProperty.of(FIELD_KEY, "primary_key1")),
                    ItemIdentifier.of(IdentificationProperty.of(FIELD_KEY, "primary_key2"))))
            .foreignKeys(
                List.of(
                    ForeignKey.builder()
                        .targetDatasetIdentifier(
                            ItemIdentifier.of(IdentificationProperty.of("id", "dataset2")))
                        .sourceFieldIdentifiers(
                            List.of(
                                ItemIdentifier.of(
                                    IdentificationProperty.of(FIELD_KEY, "foreign_key1"))))
                        .targetFieldIdentifiers(
                            List.of(
                                ItemIdentifier.of(
                                    IdentificationProperty.of(FIELD_KEY, "primary_key1"))))
                        .name("foreignKey1")
                        .build()))
            .sourceDatasets(
                List.of(
                    ItemReference.of(
                        ItemIdentifier.of(List.of(IdentificationProperty.of("name", "source1"))),
                        DataSourceIdentifier.of(
                            List.of(
                                IdentificationProperty.of("host", "localhost"),
                                IdentificationProperty.of("port", "1111"))))))
            .sourceQueries(List.of(QueryReference.of("SELECT * FROM t1", SqlDialect.DB2)))
            .build();
    Dataset dataset2 =
        Dataset.builder()
            .id(ItemIdentifier.of(List.of(IdentificationProperty.of("key", "dataset2"))))
            .name("Dataset2")
            .description("Description")
            .fields(List.of())
            .primaryKeyIdentifiers(List.of())
            .foreignKeys(List.of())
            .sourceDatasets(List.of())
            .sourceQueries(List.of())
            .build();
    assertNotEquals(dataset1, dataset2);
  }

  @Test
  @DisplayName("Dataset builder should fail with null fields")
  void builderShouldFailWithNullFields() {
    List<ItemIdentifier> primaryKeyIdentifiers =
        List.of(
            ItemIdentifier.of(IdentificationProperty.of(FIELD_KEY, "primary_key1")),
            ItemIdentifier.of(IdentificationProperty.of(FIELD_KEY, "primary_key2")));
    List<ForeignKey> foreignKeys =
        List.of(
            ForeignKey.builder()
                .targetDatasetIdentifier(
                    ItemIdentifier.of(IdentificationProperty.of("id", "dataset2")))
                .sourceFieldIdentifiers(
                    List.of(
                        ItemIdentifier.of(IdentificationProperty.of(FIELD_KEY, "foreign_key1"))))
                .targetFieldIdentifiers(
                    List.of(
                        ItemIdentifier.of(IdentificationProperty.of(FIELD_KEY, "primary_key1"))))
                .name("foreignKey1")
                .build());
    List<ItemReference> sourceDatasets =
        List.of(
            ItemReference.of(
                ItemIdentifier.of(List.of(IdentificationProperty.of("name", "source1"))),
                DataSourceIdentifier.of(
                    List.of(
                        IdentificationProperty.of("host", "localhost"),
                        IdentificationProperty.of("port", "1111")))));
    List<QueryReference> sourceQueries = List.of(QueryReference.of("SELECT 1", SqlDialect.MYSQL));
    assertThrows(
        NullPointerException.class,
        () ->
            Dataset.builder()
                .id(ItemIdentifier.of(List.of(IdentificationProperty.of("key", "dataset1"))))
                .name("Dataset1")
                .description("Description")
                .fields((Field) null)
                .primaryKeyIdentifiers(primaryKeyIdentifiers)
                .foreignKeys(foreignKeys)
                .sourceDatasets(sourceDatasets)
                .sourceQueries(sourceQueries)
                .build());
  }
}
