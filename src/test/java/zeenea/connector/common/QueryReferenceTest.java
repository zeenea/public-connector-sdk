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
        QueryReference.of("SELECT * FROM table", "SQL", dataSourceIdentifier);
    assertNotNull(queryReference);
    assertEquals("SELECT * FROM table", queryReference.getSqlQuery());
    assertEquals("SQL", queryReference.getSqlDialect());
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
        QueryReference.of("SELECT * FROM table", "SQL", dataSourceIdentifier);
    QueryReference queryReference2 =
        QueryReference.of("SELECT * FROM table", "SQL", dataSourceIdentifier);
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
        QueryReference.of("SELECT * FROM table1", "SQL", dataSourceIdentifier1);
    QueryReference queryReference2 =
        QueryReference.of("SELECT * FROM table2", "SQL", dataSourceIdentifier2);
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
        NullPointerException.class, () -> QueryReference.of(null, "SQL", dataSourceIdentifier));
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
    QueryReference queryReference = QueryReference.of("SELECT * FROM table", "SQL");
    assertNotNull(queryReference);
    assertEquals("SELECT * FROM table", queryReference.getSqlQuery());
    assertEquals("SQL", queryReference.getSqlDialect());
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
        QueryReference.of("SELECT * FROM table", "SQL", dataSourceIdentifier);
    assertNotNull(queryReference);
    assertEquals("SELECT * FROM table", queryReference.getSqlQuery());
    assertEquals("SQL", queryReference.getSqlDialect());
    assertEquals(Optional.of(dataSourceIdentifier), queryReference.getDataSourceIdentifier());
  }
}
