package zeenea.connector.contact;

import java.util.Objects;
import java.util.StringJoiner;
import org.jetbrains.annotations.NotNull;
import zeenea.connector.exception.ExceptionUtils;

/**
 * The qualifier of the relationship between a Contact and an Item.
 *
 * @see ContactRelation
 * @see Contact
 */
public final class Role {
  /** The name of the role. */
  @NotNull private final String name;

  /**
   * Constructs a Role instance using the provided builder.
   *
   * @param builder the builder used to create the Role instance
   */
  private Role(Builder builder) {
    ExceptionUtils.requireNonNullOrEmpty("name", builder.name);
    this.name = builder.name;
  }

  /**
   * Creates a new Role instance with the specified name.
   *
   * @param name the name of the role
   * @return a new Role instance
   */
  public static Role of(@NotNull String name) {
    return builder().name(name).build();
  }

  /**
   * Gets the name of the role.
   *
   * @return the name of the role
   */
  public @NotNull String getName() {
    return name;
  }

  /**
   * Creates a new builder for a Role instance.
   *
   * @return a new Builder instance
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Checks if this role is equal to another object.
   *
   * @param o the object to compare with
   * @return true if this role is equal to the specified object, otherwise false
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Role that = (Role) o;
    return Objects.equals(name, that.name);
  }

  /**
   * Computes the hash code for this role.
   *
   * @return the hash code of this role
   */
  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  /**
   * Returns a string representation of this role.
   *
   * @return a string representation of this role
   */
  @Override
  public String toString() {
    return new StringJoiner(", ", Role.class.getSimpleName() + "[", "]")
        .add("name='" + name + "'")
        .toString();
  }

  /** Builder class for creating instances of Role. */
  public static class Builder {

    /** The name of the role. */
    private String name;

    /**
     * Sets the role name.
     *
     * @param name the name of the role
     */
    private Builder name(@NotNull String name) {
      this.name = name;
      return this;
    }

    /**
     * Builds and returns the Role instance.
     *
     * @return the created Role instance
     */
    public Role build() {
      return new Role(this);
    }
  }
}
