package zeenea.connector.contact;

import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;
import zeenea.connector.SourceItem;

/**
 * A physical individual, team, entity, or moral person that can be linked to a SourceItem.
 *
 * @see SourceContactRelation
 * @see SourceItem
 * @since 1.0.0
 */
public class SourceContact {
  private final String name;
  private final String email;
  private final String phoneNumber;

  SourceContact(String name, String email, String phoneNumber) {
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
  }

  /**
   * Get the name of the source contact. This is an optional property.
   *
   * @return The name of the source contact, or {@code Optional.empty()} if absent
   */
  public Optional<String> getName() {
    return Optional.ofNullable(name);
  }

  /**
   * Get the email address of the source contact.
   *
   * @return The email address of the source contact
   */
  public String getEmail() {
    return email;
  }

  /**
   * Get the phone number of the source contact. This is an optional property.
   *
   * @return The phone number of the source contact, or {@code Optional.empty()} if absent
   */
  public Optional<String> getPhoneNumber() {
    return Optional.ofNullable(phoneNumber);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SourceContact that = (SourceContact) o;
    return Objects.equals(name, that.name)
        && Objects.equals(email, that.email)
        && Objects.equals(phoneNumber, that.phoneNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, email, phoneNumber);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", SourceContact.class.getSimpleName() + "[", "]")
        .add("name='" + name + "'")
        .add("email='" + email + "'")
        .add("phoneNumber='" + phoneNumber + "'")
        .toString();
  }
}
