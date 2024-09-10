package zeenea.connector.contact;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ContactRelationTest {

  @Test
  @DisplayName("ContactRelation factory should create contact relation")
  void shouldCreateContactRelation() {
    Contact contact = Contact.of("email@example.com", "John Doe", "1234567890");
    Role role = Role.of("Admin");
    ContactRelation contactRelation = ContactRelation.of(contact, role);
    assertNotNull(contactRelation);
    assertEquals(contact, contactRelation.getContact());
    assertEquals(role, contactRelation.getRole());
  }

  @Test
  @DisplayName("ContactRelation should be equal to another with same properties")
  void shouldBeEqualToAnotherWithSameProperties() {
    Contact contact = Contact.of("email@example.com", "John Doe", "1234567890");
    Role role = Role.of("Admin");
    ContactRelation contactRelation1 = ContactRelation.of(contact, role);
    ContactRelation contactRelation2 = ContactRelation.of(contact, role);
    assertEquals(contactRelation1, contactRelation2);
    assertEquals(contactRelation1.hashCode(), contactRelation2.hashCode());
  }

  @Test
  @DisplayName("ContactRelation should not be equal to another with different properties")
  void shouldNotBeEqualToAnotherWithDifferentProperties() {
    Contact contact = Contact.of("email@example.com", "John Doe", "1234567890");
    Role role1 = Role.of("Admin");
    Role role2 = Role.of("User");
    ContactRelation contactRelation1 = ContactRelation.of(contact, role1);
    ContactRelation contactRelation2 = ContactRelation.of(contact, role2);
    assertNotEquals(contactRelation1, contactRelation2);
  }

  @Test
  @DisplayName("ContactRelation factory should fail with null contact")
  void shouldFailWithNullContact() {
    Role role = Role.of("Admin");
    assertThrows(NullPointerException.class, () -> ContactRelation.of(null, role));
  }

  @Test
  @DisplayName("ContactRelation factory should fail with null role")
  void shouldFailWithNullRole() {
    Contact contact = Contact.of("email@example.com", "John Doe", "1234567890");
    assertThrows(NullPointerException.class, () -> ContactRelation.of(contact, null));
  }
}
