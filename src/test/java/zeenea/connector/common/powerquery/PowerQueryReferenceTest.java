package zeenea.connector.common.powerquery;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PowerQueryReferenceTest {

  @Test
  @DisplayName("PowerQueryReference factory should create power query reference")
  void shouldCreatePowerQueryReference() {
    OdbcDsn odbcDsn =
        OdbcDsn.of(
            "testDSN",
            OdbcEngine.SQLSERVER,
            List.of(Attribute.of("host", "localhost"), Attribute.of("port", "1433")));
    PowerQueryReference powerQueryReference =
        PowerQueryReference.of(
            "let source = Odbc.Query(\"dsn=testDSN\", \"SELECT * FROM table\") in source",
            List.of(odbcDsn));
    assertNotNull(powerQueryReference);
    assertEquals(
        "let source = Odbc.Query(\"dsn=testDSN\", \"SELECT * FROM table\") in source",
        powerQueryReference.getPowerQuery());
    assertEquals(List.of(odbcDsn), powerQueryReference.getDsns());
  }

  @Test
  @DisplayName("PowerQueryReference should be equal to another with same properties")
  void shouldBeEqualToAnotherWithSameProperties() {
    OdbcDsn odbcDsn =
        OdbcDsn.of(
            "testDSN",
            OdbcEngine.SQLSERVER,
            List.of(Attribute.of("host", "localhost"), Attribute.of("port", "1433")));
    PowerQueryReference powerQueryReference1 =
        PowerQueryReference.of(
            "let source = Odbc.Query(\"dsn=testDSN\", \"SELECT * FROM table\") in source",
            List.of(odbcDsn));
    PowerQueryReference powerQueryReference2 =
        PowerQueryReference.of(
            "let source = Odbc.Query(\"dsn=testDSN\", \"SELECT * FROM table\") in source",
            List.of(odbcDsn));
    assertEquals(powerQueryReference1, powerQueryReference2);
    assertEquals(powerQueryReference1.hashCode(), powerQueryReference2.hashCode());
  }

  @Test
  @DisplayName("PowerQueryReference should not be equal to another with different properties")
  void shouldNotBeEqualToAnotherWithDifferentProperties() {
    OdbcDsn odbcDsn1 =
        OdbcDsn.of(
            "testDSN1",
            OdbcEngine.SQLSERVER,
            List.of(Attribute.of("host", "localhost"), Attribute.of("port", "1433")));
    OdbcDsn odbcDsn2 =
        OdbcDsn.of(
            "testDSN2",
            OdbcEngine.ORACLE,
            List.of(Attribute.of("host", "remotehost"), Attribute.of("port", "1521")));
    PowerQueryReference powerQueryReference1 =
        PowerQueryReference.of(
            "let source = Odbc.Query(\"dsn=testDSN1\", \"SELECT * FROM table1\") in source",
            List.of(odbcDsn1));
    PowerQueryReference powerQueryReference2 =
        PowerQueryReference.of(
            "let source = Odbc.Query(\"dsn=testDSN2\", \"SELECT * FROM table2\") in source",
            List.of(odbcDsn2));
    assertNotEquals(powerQueryReference1, powerQueryReference2);
  }

  @Test
  @DisplayName("PowerQueryReference factory should fail with null Power Query")
  void shouldFailWithNullPowerQuery() {
    OdbcDsn odbcDsn =
        OdbcDsn.of(
            "testDSN",
            OdbcEngine.SQLSERVER,
            List.of(Attribute.of("host", "localhost"), Attribute.of("port", "1433")));
    assertThrows(NullPointerException.class, () -> PowerQueryReference.of(null, List.of(odbcDsn)));
  }

  @Test
  @DisplayName("PowerQueryReference.of should create a power query reference without DSNs")
  void shouldCreatePowerQueryReferenceWithoutDsns() {
    PowerQueryReference powerQueryReference =
        PowerQueryReference.of(
            "let source = Excel.Workbook(File.Contents(\"C:\\data.xlsx\")) in source");
    assertNotNull(powerQueryReference);
    assertEquals(
        "let source = Excel.Workbook(File.Contents(\"C:\\data.xlsx\")) in source",
        powerQueryReference.getPowerQuery());
    assertEquals(List.of(), powerQueryReference.getDsns());
  }

  @Test
  @DisplayName("PowerQueryReference builder should handle valid inputs")
  void shouldCreatePowerQueryReferenceWithBuilder() {
    OdbcDsn odbcDsn =
        OdbcDsn.of(
            "testDSN",
            OdbcEngine.SQLSERVER,
            List.of(Attribute.of("host", "localhost"), Attribute.of("port", "1433")));
    PowerQueryReference powerQueryReference =
        PowerQueryReference.builder()
            .powerQuery(
                "let source = Odbc.Query(\"dsn=testDSN\", \"SELECT * FROM table\") in source")
            .dsns(List.of(odbcDsn))
            .build();
    assertNotNull(powerQueryReference);
    assertEquals(
        "let source = Odbc.Query(\"dsn=testDSN\", \"SELECT * FROM table\") in source",
        powerQueryReference.getPowerQuery());
    assertEquals(List.of(odbcDsn), powerQueryReference.getDsns());
  }

  @Test
  @DisplayName("PowerQueryReference builder should handle null DSNs")
  void shouldCreatePowerQueryReferenceWithBuilderNullDsns() {
    PowerQueryReference powerQueryReference =
        PowerQueryReference.builder()
            .powerQuery("let source = Excel.Workbook(File.Contents(\"C:\\data.xlsx\")) in source")
            .dsns(null)
            .build();
    assertNotNull(powerQueryReference);
    assertEquals(
        "let source = Excel.Workbook(File.Contents(\"C:\\data.xlsx\")) in source",
        powerQueryReference.getPowerQuery());
    assertEquals(List.of(), powerQueryReference.getDsns());
  }
}
