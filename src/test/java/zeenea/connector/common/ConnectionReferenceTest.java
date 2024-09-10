package zeenea.connector.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ConnectionReferenceTest {

  @Test
  @DisplayName("ConnectionReference factories should create connection reference alias")
  void shouldCreateConnectionReference() {
    ConnectionReferenceAlias alias = ConnectionReferenceAlias.of("aliasValue");
    assertNotNull(alias);
    assertEquals("aliasValue", alias.getValue());
    ConnectionReferenceCode code = ConnectionReferenceCode.of("codeValue");
    assertNotNull(code);
    assertEquals("codeValue", code.getValue());
  }

  @Test
  @DisplayName("ConnectionReferenceAlias should be equal to another with same value")
  void shouldBeEqualToAnotherAliasWithSameValue() {
    ConnectionReferenceAlias alias1 = ConnectionReferenceAlias.of("aliasValue");
    ConnectionReferenceAlias alias2 = ConnectionReferenceAlias.of("aliasValue");
    assertEquals(alias1, alias2);
    assertEquals(alias1.hashCode(), alias2.hashCode());
    ConnectionReferenceCode code1 = ConnectionReferenceCode.of("codeValue");
    ConnectionReferenceCode code2 = ConnectionReferenceCode.of("codeValue");
    assertEquals(code1, code2);
    assertEquals(code1.hashCode(), code2.hashCode());
  }

  @Test
  @DisplayName("ConnectionReference should not be equal to another with different value")
  void shouldNotBeEqualToAnotherWithDifferentValue() {
    ConnectionReferenceAlias alias1 = ConnectionReferenceAlias.of("aliasValue1");
    ConnectionReferenceAlias alias2 = ConnectionReferenceAlias.of("aliasValue2");
    assertNotEquals(alias1, alias2);
    ConnectionReferenceCode code1 = ConnectionReferenceCode.of("codeValue1");
    ConnectionReferenceCode code2 = ConnectionReferenceCode.of("codeValue2");
    assertNotEquals(code1, code2);
  }

  @Test
  @DisplayName("ConnectionReference factory should fail with null value")
  void shouldFailWithNullValue() {
    assertThrows(NullPointerException.class, () -> ConnectionReferenceAlias.of(null));
    assertThrows(NullPointerException.class, () -> ConnectionReferenceCode.of(null));
  }
}
