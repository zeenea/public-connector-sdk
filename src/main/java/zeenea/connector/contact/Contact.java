package zeenea.connector.contact;

import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;
import zeenea.connector.Item;

/**
 * A physical individual, team, entity, or moral person that can be linked to a SourceItem.
 *
 * @see ContactRelation
 * @see Item
 * @since 1.0.0
 */
public class Contact {
  private final String name;
  private final String email;
  private final String phoneNumber;

  private Contact(Builder builder) {
    this.name = builder.name;
    this.email = builder.email;
    this.phoneNumber = builder.phoneNumber;
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

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private String name;
    private String email;
    private String phoneNumber;

    private Builder() {}

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder email(String email) {
      this.email = email;
      return this;
    }

    public Builder phoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
      return this;
    }

    protected Contact build() {
      if (this.email == null || this.email.isEmpty()) {
        throw new IllegalArgumentException("email must not be null or empty");
      }
      return new Contact(this);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Contact that = (Contact) o;
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
    return new StringJoiner(", ", Contact.class.getSimpleName() + "[", "]")
        .add("name='" + name + "'")
        .add("email='" + email + "'")
        .add("phoneNumber='" + phoneNumber + "'")
        .toString();
  }
}
