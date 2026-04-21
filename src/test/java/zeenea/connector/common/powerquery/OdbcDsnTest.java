package zeenea.connector.common.powerquery;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OdbcDsnTest {

  private static final String DEFAULT_DSN_NAME = "testDSN";
  private static final OdbcEngine DEFAULT_ENGINE = OdbcEngine.SQLSERVER;

  @Test
  @DisplayName("OdbcDsn factory should create ODBC DSN with attributes")
  void shouldCreateOdbcDsnWithAttributes() {
    List<Attribute> attributes =
        List.of(Attribute.of("host", "localhost"), Attribute.of("port", "1433"));
    OdbcDsn odbcDsn = OdbcDsn.of(DEFAULT_DSN_NAME, DEFAULT_ENGINE, attributes);

    assertNotNull(odbcDsn);
    assertEquals(DEFAULT_DSN_NAME, odbcDsn.getName());
    assertEquals(DEFAULT_ENGINE, odbcDsn.getEngine());
    assertEquals(attributes, odbcDsn.getAttributes());
  }

  @Test
  @DisplayName("OdbcDsn factory should create ODBC DSN without attributes")
  void shouldCreateOdbcDsnWithoutAttributes() {
    OdbcDsn odbcDsn = OdbcDsn.of(DEFAULT_DSN_NAME, DEFAULT_ENGINE);

    assertNotNull(odbcDsn);
    assertEquals(DEFAULT_DSN_NAME, odbcDsn.getName());
    assertEquals(DEFAULT_ENGINE, odbcDsn.getEngine());
    assertEquals(List.of(), odbcDsn.getAttributes());
  }

  @Test
  @DisplayName("OdbcDsn should be equal to another with same properties")
  void shouldBeEqualToAnotherWithSameProperties() {
    List<Attribute> attributes =
        List.of(Attribute.of("host", "localhost"), Attribute.of("port", "1433"));
    OdbcDsn odbcDsn1 = OdbcDsn.of(DEFAULT_DSN_NAME, DEFAULT_ENGINE, attributes);
    OdbcDsn odbcDsn2 = OdbcDsn.of(DEFAULT_DSN_NAME, DEFAULT_ENGINE, attributes);

    assertEquals(odbcDsn1, odbcDsn2);
    assertEquals(odbcDsn1.hashCode(), odbcDsn2.hashCode());
  }

  @Test
  @DisplayName("OdbcDsn should not be equal to another with different name")
  void shouldNotBeEqualToAnotherWithDifferentName() {
    List<Attribute> attributes =
        List.of(Attribute.of("host", "localhost"), Attribute.of("port", "1433"));
    OdbcDsn odbcDsn1 = OdbcDsn.of("testDSN1", DEFAULT_ENGINE, attributes);
    OdbcDsn odbcDsn2 = OdbcDsn.of("testDSN2", DEFAULT_ENGINE, attributes);

    assertNotEquals(odbcDsn1, odbcDsn2);
  }

  @Test
  @DisplayName("OdbcDsn should not be equal to another with different engine")
  void shouldNotBeEqualToAnotherWithDifferentEngine() {
    List<Attribute> attributes =
        List.of(Attribute.of("host", "localhost"), Attribute.of("port", "1433"));
    OdbcDsn odbcDsn1 = OdbcDsn.of(DEFAULT_DSN_NAME, OdbcEngine.SQLSERVER, attributes);
    OdbcDsn odbcDsn2 = OdbcDsn.of(DEFAULT_DSN_NAME, OdbcEngine.ORACLE, attributes);

    assertNotEquals(odbcDsn1, odbcDsn2);
  }

  @Test
  @DisplayName("OdbcDsn should not be equal to another with different attributes")
  void shouldNotBeEqualToAnotherWithDifferentAttributes() {
    OdbcDsn odbcDsn1 =
        OdbcDsn.of(
            DEFAULT_DSN_NAME,
            DEFAULT_ENGINE,
            List.of(Attribute.of("host", "localhost"), Attribute.of("port", "1433")));
    OdbcDsn odbcDsn2 =
        OdbcDsn.of(
            DEFAULT_DSN_NAME,
            DEFAULT_ENGINE,
            List.of(Attribute.of("host", "remotehost"), Attribute.of("port", "5432")));

    assertNotEquals(odbcDsn1, odbcDsn2);
  }

  @Test
  @DisplayName("OdbcDsn should not be equal to another with different attribute count")
  void shouldNotBeEqualToAnotherWithDifferentAttributeCount() {
    OdbcDsn odbcDsn1 =
        OdbcDsn.of(
            DEFAULT_DSN_NAME,
            DEFAULT_ENGINE,
            List.of(Attribute.of("host", "localhost"), Attribute.of("port", "1433")));
    OdbcDsn odbcDsn2 =
        OdbcDsn.of(DEFAULT_DSN_NAME, DEFAULT_ENGINE, List.of(Attribute.of("host", "localhost")));

    assertNotEquals(odbcDsn1, odbcDsn2);
  }

  @Test
  @DisplayName("OdbcDsn should not be equal to null")
  void shouldNotBeEqualToNull() {
    OdbcDsn odbcDsn = OdbcDsn.of(DEFAULT_DSN_NAME, DEFAULT_ENGINE);
    assertNotEquals(null, odbcDsn);
  }

  @Test
  @DisplayName("OdbcDsn builder should create instance with all properties")
  void shouldCreateOdbcDsnWithBuilder() {
    List<Attribute> attributes =
        List.of(Attribute.of("host", "localhost"), Attribute.of("port", "1433"));
    OdbcDsn odbcDsn =
        OdbcDsn.builder()
            .name(DEFAULT_DSN_NAME)
            .engine(DEFAULT_ENGINE)
            .attributes(attributes)
            .build();

    assertNotNull(odbcDsn);
    assertEquals(DEFAULT_DSN_NAME, odbcDsn.getName());
    assertEquals(DEFAULT_ENGINE, odbcDsn.getEngine());
    assertEquals(attributes, odbcDsn.getAttributes());
  }

  @Test
  @DisplayName("OdbcDsn builder should initialize empty attributes list")
  void shouldCreateOdbcDsnWithBuilderEmptyAttributes() {
    OdbcDsn odbcDsn = OdbcDsn.builder().name(DEFAULT_DSN_NAME).engine(DEFAULT_ENGINE).build();

    assertNotNull(odbcDsn);
    assertEquals(DEFAULT_DSN_NAME, odbcDsn.getName());
    assertEquals(DEFAULT_ENGINE, odbcDsn.getEngine());
    assertEquals(List.of(), odbcDsn.getAttributes());
  }

  @Test
  @DisplayName("OdbcDsn should get attribute by name")
  void shouldGetAttributeByName() {
    List<Attribute> attributes =
        List.of(Attribute.of("host", "localhost"), Attribute.of("port", "1433"));
    OdbcDsn odbcDsn = OdbcDsn.of(DEFAULT_DSN_NAME, DEFAULT_ENGINE, attributes);

    assertEquals(Optional.of("localhost"), odbcDsn.getAttribute("host"));
  }

  @Test
  @DisplayName("OdbcDsn should return empty optional for non-existent attribute")
  void shouldReturnEmptyOptionalForNonExistentAttribute() {
    List<Attribute> attributes =
        List.of(Attribute.of("host", "localhost"), Attribute.of("port", "1433"));
    OdbcDsn odbcDsn = OdbcDsn.of(DEFAULT_DSN_NAME, DEFAULT_ENGINE, attributes);

    assertEquals(Optional.empty(), odbcDsn.getAttribute("database"));
  }

  @Test
  @DisplayName("OdbcDsn should return empty optional when attributes list is empty")
  void shouldReturnEmptyOptionalWhenAttributesListEmpty() {
    OdbcDsn odbcDsn = OdbcDsn.of(DEFAULT_DSN_NAME, DEFAULT_ENGINE);

    assertEquals(Optional.empty(), odbcDsn.getAttribute("host"));
  }

  @Test
  @DisplayName("OdbcDsn should work with all standard attribute names")
  void shouldWorkWithStandardAttributeNames() {
    List<Attribute> attributes =
        List.of(
            Attribute.of("host", "db.example.com"),
            Attribute.of("port", "5432"),
            Attribute.of("database", "mydb"),
            Attribute.of("awsRegion", "us-east-1"));
    OdbcDsn odbcDsn = OdbcDsn.of("postgres", OdbcEngine.REDSHIFT, attributes);

    assertEquals("db.example.com", odbcDsn.getAttribute("host").orElse(null));
    assertEquals("5432", odbcDsn.getAttribute("port").orElse(null));
    assertEquals("mydb", odbcDsn.getAttribute("database").orElse(null));
    assertEquals("us-east-1", odbcDsn.getAttribute("awsRegion").orElse(null));
  }
}
