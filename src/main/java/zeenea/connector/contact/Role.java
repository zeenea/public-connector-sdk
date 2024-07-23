package zeenea.connector.contact;

import java.util.Objects;
import java.util.StringJoiner;
import zeenea.connector.Item;

/**
 * The qualifier of the relationship between a SourceContact and a SourceItem.
 *
 * @see ContactRelation
 * @see Contact
 * @see Item
 * @since 1.0.0
 */
public class Role {
  private final String name;

  private Role(Builder builder) {
    this.name = builder.name;
  }

  /**
   * Get the name of the source role.
   *
   * @return The name of the source role
   */
  public String getName() {
    return name;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private String name;

    private Builder() {}

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    protected Role build() {
      if (this.name == null || this.name.isEmpty()) {
        throw new IllegalArgumentException("role name must not be null or empty");
      }
      return new Role(this);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Role that = (Role) o;
    return Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Role.class.getSimpleName() + "[", "]")
        .add("name='" + name + "'")
        .toString();
  }
}
