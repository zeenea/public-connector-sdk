package zeenea.connector.contact;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ContactTest {

  @Test
  @DisplayName("Contact factory should create contact")
  void shouldCreateContact() {
    Contact contact1 = Contact.of("email@example.com", "John Doe", "1234567890", "Admin");
    assertNotNull(contact1);
    assertEquals("email@example.com", contact1.getEmail());
    assertEquals(Optional.of("John Doe"), contact1.getName());
    assertEquals(Optional.of("1234567890"), contact1.getPhoneNumber());
    assertEquals("Admin", contact1.getRole());
    Contact contact2 = Contact.of("email@example.com", null, null, "Admin");
    assertNotNull(contact2);
    assertEquals("email@example.com", contact2.getEmail());
    assertEquals(Optional.empty(), contact2.getName());
    assertEquals(Optional.empty(), contact2.getPhoneNumber());
    assertEquals("Admin", contact2.getRole());
  }

  @Test
  @DisplayName("Contact should be equal to another with same properties")
  void shouldBeEqualToAnotherWithSameProperties() {
    Contact contact1 = Contact.of("email@example.com", "John Doe", "1234567890", "Admin");
    Contact contact2 = Contact.of("email@example.com", "John Doe", "1234567890", "Admin");
    assertEquals(contact1, contact2);
    assertEquals(contact1.hashCode(), contact2.hashCode());
  }

  @Test
  @DisplayName("Contact should not be equal to another with different properties")
  void shouldNotBeEqualToAnotherWithDifferentProperties() {
    Contact contact1 = Contact.of("email1@example.com", "John Doe", "1234567890", "Admin");
    Contact contact2 = Contact.of("email2@example.com", "Jane Doe", "0987654321", "User");
    assertNotEquals(contact1, contact2);
  }

  @Test
  @DisplayName("Contact factory should fail with null email")
  void shouldFailWithNullEmail() {
    assertThrows(
        NullPointerException.class, () -> Contact.of(null, "John Doe", "1234567890", "Admin"));
  }
}
