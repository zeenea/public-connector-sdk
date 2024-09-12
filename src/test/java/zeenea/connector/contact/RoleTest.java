package zeenea.connector.contact;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RoleTest {

  @Test
  @DisplayName("Role factory should create role")
  void shouldCreateRole() {
    Role role = Role.of("Admin");
    assertNotNull(role);
    assertEquals("Admin", role.getName());
  }

  @Test
  @DisplayName("Role should be equal to another with same name")
  void shouldBeEqualToAnotherWithSameName() {
    Role role1 = Role.of("Admin");
    Role role2 = Role.of("Admin");
    assertEquals(role1, role2);
    assertEquals(role1.hashCode(), role2.hashCode());
  }

  @Test
  @DisplayName("Role should not be equal to another with different name")
  void shouldNotBeEqualToAnotherWithDifferentName() {
    Role role1 = Role.of("Admin");
    Role role2 = Role.of("User");
    assertNotEquals(role1, role2);
  }

  @Test
  @DisplayName("Role factory should fail with null name")
  void shouldFailWithNullName() {
    assertThrows(NullPointerException.class, () -> Role.of(null));
  }
}
