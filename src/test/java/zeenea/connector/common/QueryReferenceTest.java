package zeenea.connector.common;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueryReferenceTest {

  @Test
  @DisplayName("QueryReference factory should create query reference")
  void shouldCreateQueryReference() {
    DataSourceIdentifier dataSourceIdentifier =
        DataSourceIdentifier.of(
            List.of(
                IdentificationProperty.of("host", "localhost"),
                IdentificationProperty.of("port", "1111")));
    QueryReference queryReference =
        QueryReference.of("SELECT * FROM table", SqlDialect.MYSQL, dataSourceIdentifier);
    assertNotNull(queryReference);
    assertEquals("SELECT * FROM table", queryReference.getSqlQuery());
    assertEquals(SqlDialect.MYSQL, queryReference.getSqlDialect());
    assertEquals(Optional.of(dataSourceIdentifier), queryReference.getDataSourceIdentifier());
  }

  @Test
  @DisplayName("QueryReference should be equal to another with same properties")
  void shouldBeEqualToAnotherWithSameProperties() {
    DataSourceIdentifier dataSourceIdentifier =
        DataSourceIdentifier.of(
            List.of(
                IdentificationProperty.of("host", "localhost"),
                IdentificationProperty.of("port", "1111")));
    QueryReference queryReference1 =
        QueryReference.of("SELECT * FROM table", SqlDialect.MYSQL, dataSourceIdentifier);
    QueryReference queryReference2 =
        QueryReference.of("SELECT * FROM table", SqlDialect.MYSQL, dataSourceIdentifier);
    assertEquals(queryReference1, queryReference2);
    assertEquals(queryReference1.hashCode(), queryReference2.hashCode());
  }

  @Test
  @DisplayName("QueryReference should not be equal to another with different properties")
  void shouldNotBeEqualToAnotherWithDifferentProperties() {
    DataSourceIdentifier dataSourceIdentifier1 =
        DataSourceIdentifier.of(
            List.of(
                IdentificationProperty.of("host", "localhost"),
                IdentificationProperty.of("port", "1111")));
    DataSourceIdentifier dataSourceIdentifier2 =
        DataSourceIdentifier.of(
            List.of(
                IdentificationProperty.of("host", "localhost"),
                IdentificationProperty.of("port", "2222")));
    QueryReference queryReference1 =
        QueryReference.of("SELECT * FROM table1", SqlDialect.MYSQL, dataSourceIdentifier1);
    QueryReference queryReference2 =
        QueryReference.of("SELECT * FROM table2", SqlDialect.MYSQL, dataSourceIdentifier2);
    assertNotEquals(queryReference1, queryReference2);
  }

  @Test
  @DisplayName("QueryReference factory should fail with null SQL query")
  void shouldFailWithNullSqlQuery() {
    DataSourceIdentifier dataSourceIdentifier =
        DataSourceIdentifier.of(
            List.of(
                IdentificationProperty.of("host", "localhost"),
                IdentificationProperty.of("port", "1111")));
    assertThrows(
        NullPointerException.class,
        () -> QueryReference.of(null, SqlDialect.MYSQL, dataSourceIdentifier));
  }

  @Test
  @DisplayName("QueryReference factory should fail with null SQL dialect")
  void shouldFailWithNullSqlDialect() {
    DataSourceIdentifier dataSourceIdentifier =
        DataSourceIdentifier.of(
            List.of(
                IdentificationProperty.of("host", "localhost"),
                IdentificationProperty.of("port", "1111")));
    assertThrows(
        NullPointerException.class,
        () -> QueryReference.of("SELECT * FROM table", null, dataSourceIdentifier));
  }

  @Test
  @DisplayName("QueryReference.of should create a query reference without data source identifier")
  void shouldCreateQueryReferenceWithoutDataSourceIdentifier() {
    QueryReference queryReference = QueryReference.of("SELECT * FROM table", SqlDialect.MYSQL);
    assertNotNull(queryReference);
    assertEquals("SELECT * FROM table", queryReference.getSqlQuery());
    assertEquals(SqlDialect.MYSQL, queryReference.getSqlDialect());
    assertEquals(Optional.empty(), queryReference.getDataSourceIdentifier());
  }

  @Test
  @DisplayName("QueryReference.of should create a query reference with data source identifier")
  void shouldCreateQueryReferenceWithDataSourceIdentifier() {
    DataSourceIdentifier dataSourceIdentifier =
        DataSourceIdentifier.of(
            List.of(
                IdentificationProperty.of("host", "localhost"),
                IdentificationProperty.of("port", "1111")));
    QueryReference queryReference =
        QueryReference.of("SELECT * FROM table", SqlDialect.MYSQL, dataSourceIdentifier);
    assertNotNull(queryReference);
    assertEquals("SELECT * FROM table", queryReference.getSqlQuery());
    assertEquals(SqlDialect.MYSQL, queryReference.getSqlDialect());
    assertEquals(Optional.of(dataSourceIdentifier), queryReference.getDataSourceIdentifier());
  }

  @Test
  @DisplayName("QueryReference builder should handle valid String dialect")
  void shouldCreateQueryReferenceWithBuilderDialectValid() {
    QueryReference queryReference =
        QueryReference.builder().sqlQuery("SELECT * FROM table").sqlDialect("mysql").build();
    assertNotNull(queryReference);
    assertEquals("SELECT * FROM table", queryReference.getSqlQuery());
    assertEquals(SqlDialect.MYSQL, queryReference.getSqlDialect());
  }

  @Test
  @DisplayName("QueryReference builder should handle not found String dialect")
  void shouldCreateQueryReferenceWithBuilderDialectNotFound() {
    QueryReference queryReference =
        QueryReference.builder()
            .sqlQuery("SELECT * FROM table")
            .sqlDialect("super-database")
            .build();
    assertNotNull(queryReference);
    assertEquals("SELECT * FROM table", queryReference.getSqlQuery());
    assertEquals(SqlDialect.ANSI, queryReference.getSqlDialect());
  }

  @Test
  @DisplayName("QueryReference builder should handle null String dialect")
  void shouldCreateQueryReferenceWithBuilderDialectNull() {
    QueryReference queryReference =
        QueryReference.builder().sqlQuery("SELECT * FROM table").sqlDialect((String) null).build();
    assertNotNull(queryReference);
    assertEquals("SELECT * FROM table", queryReference.getSqlQuery());
    assertEquals(SqlDialect.ANSI, queryReference.getSqlDialect());
  }

  @Test
  @DisplayName("QueryReference builder should set defaultSchema and defaultCatalog")
  void shouldCreateQueryReferenceWithDefaultSchemaAndCatalog() {
    QueryReference queryReference =
        QueryReference.builder()
            .sqlQuery("SELECT * FROM table")
            .sqlDialect(SqlDialect.MYSQL)
            .defaultCatalog("my_catalog")
            .defaultSchema("my_schema")
            .build();
    assertNotNull(queryReference);
    assertEquals(Optional.of("my_catalog"), queryReference.getDefaultCatalog());
    assertEquals(Optional.of("my_schema"), queryReference.getDefaultSchema());
  }

  @Test
  @DisplayName(
      "QueryReference should return empty Optional for absent defaultSchema and defaultCatalog")
  void shouldReturnEmptyOptionalForAbsentDefaultSchemaAndCatalog() {
    QueryReference queryReference =
        QueryReference.builder()
            .sqlQuery("SELECT * FROM table")
            .sqlDialect(SqlDialect.MYSQL)
            .build();
    assertEquals(Optional.empty(), queryReference.getDefaultCatalog());
    assertEquals(Optional.empty(), queryReference.getDefaultSchema());
  }

  @Test
  @DisplayName(
      "QueryReference builder should treat null defaultSchema and defaultCatalog as absent")
  void shouldTreatNullDefaultSchemaAndCatalogAsAbsent() {
    QueryReference queryReference =
        QueryReference.builder()
            .sqlQuery("SELECT * FROM table")
            .sqlDialect(SqlDialect.MYSQL)
            .defaultCatalog(null)
            .defaultSchema(null)
            .build();
    assertEquals(Optional.empty(), queryReference.getDefaultCatalog());
    assertEquals(Optional.empty(), queryReference.getDefaultSchema());
  }

  @Test
  @DisplayName("QueryReference builder should allow setting only defaultCatalog")
  void shouldCreateQueryReferenceWithOnlyDefaultCatalog() {
    QueryReference queryReference =
        QueryReference.builder()
            .sqlQuery("SELECT * FROM table")
            .sqlDialect(SqlDialect.MYSQL)
            .defaultCatalog("my_catalog")
            .build();
    assertEquals(Optional.of("my_catalog"), queryReference.getDefaultCatalog());
    assertEquals(Optional.empty(), queryReference.getDefaultSchema());
  }

  @Test
  @DisplayName("QueryReference builder should allow setting only defaultSchema")
  void shouldCreateQueryReferenceWithOnlyDefaultSchema() {
    QueryReference queryReference =
        QueryReference.builder()
            .sqlQuery("SELECT * FROM table")
            .sqlDialect(SqlDialect.MYSQL)
            .defaultSchema("my_schema")
            .build();
    assertEquals(Optional.empty(), queryReference.getDefaultCatalog());
    assertEquals(Optional.of("my_schema"), queryReference.getDefaultSchema());
  }

  @Test
  @DisplayName("QueryReference equality should account for defaultSchema and defaultCatalog")
  void shouldNotBeEqualWhenDefaultSchemaOrCatalogDiffer() {
    QueryReference base =
        QueryReference.builder()
            .sqlQuery("SELECT * FROM table")
            .sqlDialect(SqlDialect.MYSQL)
            .defaultCatalog("cat1")
            .defaultSchema("schema1")
            .build();
    QueryReference differentCatalog =
        QueryReference.builder()
            .sqlQuery("SELECT * FROM table")
            .sqlDialect(SqlDialect.MYSQL)
            .defaultCatalog("cat2")
            .defaultSchema("schema1")
            .build();
    QueryReference differentSchema =
        QueryReference.builder()
            .sqlQuery("SELECT * FROM table")
            .sqlDialect(SqlDialect.MYSQL)
            .defaultCatalog("cat1")
            .defaultSchema("schema2")
            .build();
    QueryReference same =
        QueryReference.builder()
            .sqlQuery("SELECT * FROM table")
            .sqlDialect(SqlDialect.MYSQL)
            .defaultCatalog("cat1")
            .defaultSchema("schema1")
            .build();
    assertNotEquals(base, differentCatalog);
    assertNotEquals(base, differentSchema);
    assertEquals(base, same);
    assertEquals(base.hashCode(), same.hashCode());
  }
}
