package zeenea.connector.contact;

import java.util.Objects;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import zeenea.connector.exception.ExceptionUtils;

/** Represents a contact with an email, name, and phone number. */
public final class Contact {
  /** The email address of the contact. */
  @NotNull private final String email;

  /** The name of the contact, if any. */
  @Nullable private final String name;

  /** The phone number of the contact, if any. */
  @Nullable private final String phoneNumber;

  /** The role of the contact. */
  @NotNull private final String role;

  /**
   * Constructs a Contact instance using the provided builder.
   *
   * @param builder the builder used to create the Contact instance
   */
  private Contact(Builder builder) {
    ExceptionUtils.requireNonNullOrEmpty("email", builder.email);
    ExceptionUtils.requireNonNullOrEmpty("role", builder.role);
    this.email = builder.email;
    this.name = builder.name;
    this.phoneNumber = builder.phoneNumber;
    this.role = builder.role;
  }

  /**
   * Creates a Contact instance with the specified email, name, and phone number.
   *
   * @param email the email address of the contact
   * @param name the name of the contact
   * @param phoneNumber the phone number of the contact
   * @param role the role of the contact
   * @return a new Contact instance
   */
  public static Contact of(
      @NotNull String email,
      @Nullable String name,
      @Nullable String phoneNumber,
      @NotNull String role) {
    return builder().email(email).name(name).phoneNumber(phoneNumber).role(role).build();
  }

  /**
   * Gets the email address of the contact.
   *
   * @return the email address of the contact
   */
  public @NotNull String getEmail() {
    return email;
  }

  /**
   * Gets the name of the contact, if any.
   *
   * @return an Optional containing the name if present, otherwise an empty Optional
   */
  public Optional<String> getName() {
    return Optional.ofNullable(name);
  }

  /**
   * Gets the phone number of the contact, if any.
   *
   * @return an Optional containing the phone number if present, otherwise an empty Optional
   */
  public Optional<String> getPhoneNumber() {
    return Optional.ofNullable(phoneNumber);
  }

  /**
   * Gets the role of the contact.
   *
   * @return the role of the contact
   */
  public @NotNull String getRole() {
    return role;
  }

  /**
   * Creates a new builder for the Contact class.
   *
   * @return a new Builder instance
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Checks if this Contact is equal to another object.
   *
   * @param o the object to compare with
   * @return true if this Contact is equal to the specified object, otherwise false
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Contact that = (Contact) o;
    return Objects.equals(name, that.name)
        && Objects.equals(email, that.email)
        && Objects.equals(phoneNumber, that.phoneNumber)
        && Objects.equals(role, that.role);
  }

  /**
   * Computes the hash code for this Contact.
   *
   * @return the hash code of this Contact
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, email, phoneNumber, role);
  }

  /**
   * Returns a string representation of this Contact.
   *
   * @return a string representation of this Contact
   */
  @Override
  public String toString() {
    return "Contact{"
        + "email='"
        + email
        + "', name='"
        + name
        + "', phoneNumber='"
        + phoneNumber
        + "', role='"
        + role
        + "'}";
  }

  /** Builder class for creating instances of Contact. */
  public static class Builder {

    /** The email address of the contact. */
    private String email;

    /** The name of the contact, if any. */
    private String name;

    /** The phone number of the contact, if any. */
    private String phoneNumber;

    /** The role of the contact */
    private String role;

    /**
     * Sets the email of the contact.
     *
     * @param email the email address of the contact
     * @return the builder instance
     */
    public Builder email(@NotNull String email) {
      this.email = email;
      return this;
    }

    /**
     * Sets the name of the contact.
     *
     * @param name the name to set
     * @return the builder instance
     */
    public Builder name(@Nullable String name) {
      this.name = name;
      return this;
    }

    /**
     * Sets the phone number of the contact.
     *
     * @param phoneNumber the phone number to set
     * @return the builder instance
     */
    public Builder phoneNumber(@Nullable String phoneNumber) {
      this.phoneNumber = phoneNumber;
      return this;
    }

    /**
     * Sets the role of the contact.
     *
     * @param role the role to set
     * @return the builder instance
     */
    public Builder role(@NotNull String role) {
      this.role = role;
      return this;
    }

    /**
     * Builds and returns a Contact instance.
     *
     * @return the created Contact instance
     */
    public Contact build() {
      return new Contact(this);
    }
  }
}
