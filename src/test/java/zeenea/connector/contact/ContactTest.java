package zeenea.connector.contact;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ContactTest {

  @Test
  @DisplayName("Contact factory should create contact")
  void shouldCreateContact() {
    Contact contact1 = Contact.of("email@example.com", "John Doe", "1234567890", "Admin");
    assertThat(contact1).isNotNull();
    assertThat(contact1.getEmail()).isEqualTo("email@example.com");
    assertThat(contact1.getName()).isEqualTo(Optional.of("John Doe"));
    assertThat(contact1.getPhoneNumber()).isEqualTo(Optional.of("1234567890"));
    assertThat(contact1.getRole()).isEqualTo("Admin");
    Contact contact2 = Contact.of("email@example.com", null, null, "Admin");
    assertThat(contact2).isNotNull();
    assertThat(contact2.getEmail()).isEqualTo("email@example.com");
    assertThat(contact2.getName()).isEqualTo(Optional.empty());
    assertThat(contact2.getPhoneNumber()).isEqualTo(Optional.empty());
    assertThat(contact2.getRole()).isEqualTo("Admin");
  }

  @Test
  @DisplayName("Contact should be equal to another with same properties")
  void shouldBeEqualToAnotherWithSameProperties() {
    Contact contact1 = Contact.of("email@example.com", "John Doe", "1234567890", "Admin");
    Contact contact2 = Contact.of("email@example.com", "John Doe", "1234567890", "Admin");
    assertThat(contact1).isEqualTo(contact2);
    assertThat(contact1.hashCode()).isEqualTo(contact2.hashCode());
  }

  @Test
  @DisplayName("Contact should not be equal to another with different properties")
  void shouldNotBeEqualToAnotherWithDifferentProperties() {
    Contact contact1 = Contact.of("email1@example.com", "John Doe", "1234567890", "Admin");
    Contact contact2 = Contact.of("email2@example.com", "Jane Doe", "0987654321", "User");
    assertThat(contact1).isNotEqualTo(contact2);
  }

  @Test
  @DisplayName("Contact factory should fail with null email")
  void shouldFailWithNullEmail() {
    assertThatThrownBy(() -> Contact.of(null, "John Doe", "1234567890", "Admin"))
        .isInstanceOf(NullPointerException.class);
  }
}
