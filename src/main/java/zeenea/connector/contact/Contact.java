package zeenea.connector.contact;

import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;
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

  /**
   * Constructs a Contact instance using the provided builder.
   *
   * @param builder the builder used to create the Contact instance
   */
  private Contact(Builder builder) {
    ExceptionUtils.requireNonNullOrEmpty("email", builder.email);
    this.email = builder.email;
    this.name = builder.name;
    this.phoneNumber = builder.phoneNumber;
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
   * Creates a new builder for the Contact class.
   *
   * @param email the email address of the contact
   * @return a new Builder instance
   */
  public static Builder builder(@NotNull String email) {
    return new Builder(email);
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
        && Objects.equals(phoneNumber, that.phoneNumber);
  }

  /**
   * Computes the hash code for this Contact.
   *
   * @return the hash code of this Contact
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, email, phoneNumber);
  }

  /**
   * Returns a string representation of this Contact.
   *
   * @return a string representation of this Contact
   */
  @Override
  public String toString() {
    return new StringJoiner(", ", Contact.class.getSimpleName() + "[", "]")
        .add("name='" + name + "'")
        .add("email='" + email + "'")
        .add("phoneNumber='" + phoneNumber + "'")
        .toString();
  }

  /** Builder class for creating instances of Contact. */
  public static class Builder {

    /** The email address of the contact. */
    private final String email;

    /** The name of the contact, if any. */
    private String name;

    /** The phone number of the contact, if any. */
    private String phoneNumber;

    /**
     * Constructs a Builder instance with the specified email address.
     *
     * @param email the email address of the contact
     */
    private Builder(@NotNull String email) {
      this.email = email;
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
     * Builds and returns a Contact instance.
     *
     * @return the created Contact instance
     */
    public Contact build() {
      return new Contact(this);
    }
  }
}
