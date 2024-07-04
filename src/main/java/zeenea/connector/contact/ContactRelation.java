package zeenea.connector.contact;

import java.util.Objects;
import java.util.StringJoiner;
import org.jetbrains.annotations.NotNull;

/** Represents a relation between a contact and a role. */
public final class ContactRelation {
  /** The contact associated with the relation. */
  @NotNull private final Contact contact;

  /** The role associated with the relation. */
  @NotNull private final Role role;

  /**
   * Constructs a ContactRelation instance using the provided builder.
   *
   * @param builder the builder used to create the ContactRelation instance
   */
  private ContactRelation(Builder builder) {
    Objects.requireNonNull(builder.contact, "contact");
    Objects.requireNonNull(builder.role, "role");
    this.contact = builder.contact;
    this.role = builder.role;
  }

  /**
   * Gets the contact associated with the relation.
   *
   * @return the contact associated with the relation
   */
  public @NotNull Contact getContact() {
    return contact;
  }

  /**
   * Gets the role associated with the relation.
   *
   * @return the role associated with the relation
   */
  public @NotNull Role getRole() {
    return role;
  }

  /**
   * Checks if this ContactRelation is equal to another object.
   *
   * @param o the object to compare with
   * @return true if this ContactRelation is equal to the specified object, otherwise false
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactRelation that = (ContactRelation) o;
    return Objects.equals(contact, that.contact) && Objects.equals(role, that.role);
  }

  /**
   * Computes the hash code for this ContactRelation.
   *
   * @return the hash code of this ContactRelation
   */
  @Override
  public int hashCode() {
    return Objects.hash(contact, role);
  }

  /**
   * Returns a string representation of this ContactRelation.
   *
   * @return a string representation of this ContactRelation
   */
  @Override
  public String toString() {
    return new StringJoiner(", ", ContactRelation.class.getSimpleName() + "[", "]")
        .add("contact=" + contact)
        .add("role=" + role)
        .toString();
  }

  /**
   * Creates a new builder for the ContactRelation class.
   *
   * @param contact the contact associated with the relation
   * @param role the role associated with the relation
   * @return a new Builder instance
   */
  public static Builder builder(Contact contact, Role role) {
    return new Builder(contact, role);
  }

  /** Builder class for creating instances of ContactRelation. */
  public static final class Builder {

    /** The contact associated with the relation. */
    private final Contact contact;

    /** The role associated with the relation. */
    private final Role role;

    /**
     * Constructs a Builder instance with the specified contact and role.
     *
     * @param contact the contact associated with the relation
     * @param role the role associated with the relation
     */
    private Builder(@NotNull Contact contact, @NotNull Role role) {
      this.contact = contact;
      this.role = role;
    }

    /**
     * Builds and returns a ContactRelation instance.
     *
     * @return the created ContactRelation instance
     */
    public ContactRelation build() {
      return new ContactRelation(this);
    }
  }
}
