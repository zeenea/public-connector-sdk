package zeenea.connector.contact;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * The relationship between a Contact and an Item. The relationship is defined by a {@link Contact}
 * and a {@link Role}.
 *
 * @see Contact
 * @see Role
 * @since 1.0.0
 */
public class ContactRelation {
  private final Contact contact;
  private final Role role;

  private ContactRelation(Builder builder) {
    this.contact =
        Contact.builder()
            .name(builder.name)
            .email(builder.email)
            .phoneNumber(builder.phoneNumber)
            .build();
    this.role = Role.builder().name(builder.role).build();
  }

  /**
   * Create a new {@link ContactRelation} builder. This builder is responsible for building the
   * {@link Contact} and {@link Role} attributes of the relation.
   *
   * @return A contact relation builder
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Get the contact of the source contact relation.
   *
   * @return The contact of the source contact relation
   */
  public Contact getContact() {
    return contact;
  }

  /**
   * Get the role of the source contact relation.
   *
   * @return The role of the source contact relation
   */
  public Role getRole() {
    return role;
  }

  /**
   * A utility class to create {@link ContactRelation} instances following the <em>Builder</em>
   * design pattern.
   */
  public static final class Builder {

    private String role;
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

    public Builder role(String role) {
      this.role = role;
      return this;
    }

    public ContactRelation build() {
      if (email == null) throw new NullPointerException("email cannot be null");
      if (role == null) throw new NullPointerException("role cannot be null");
      return new ContactRelation(this);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactRelation that = (ContactRelation) o;
    return Objects.equals(contact, that.contact) && Objects.equals(role, that.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contact, role);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ContactRelation.class.getSimpleName() + "[", "]")
        .add("contact=" + contact)
        .add("role=" + role)
        .toString();
  }
}
