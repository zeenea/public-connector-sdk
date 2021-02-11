package zeenea.sdk.contact;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * The relationship between a Contact and an Item.
 * The relationship is defined by a {@link SourceContact} and a {@link SourceRole}.
 *
 * @see SourceContact
 * @see SourceRole
 * @since 1.0.0
 */
public class SourceContactRelation {
    private final SourceContact contact;
    private final SourceRole role;

    private SourceContactRelation(Builder builder) {
        this.contact = new SourceContact(builder.name, builder.email, builder.phoneNumber);
        this.role = new SourceRole(builder.role);
    }

    /**
     * Create a new {@link SourceContactRelation} builder.
     * This builder is responsible for building the {@link SourceContact} and {@link SourceRole} attributes of the relation.
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
    public SourceContact getContact() {
        return contact;
    }

    /**
     * Get the role of the source contact relation.
     *
     * @return The role of the source contact relation
     */
    public SourceRole getRole() {
        return role;
    }

    /**
     * @hidden Common overrides are better off the Javadoc.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SourceContactRelation that = (SourceContactRelation) o;
        return Objects.equals(contact, that.contact) && Objects.equals(role, that.role);
    }

    /**
     * @hidden Common overrides are better off the Javadoc.
     */
    @Override
    public int hashCode() {
        return Objects.hash(contact, role);
    }

    /**
     * @hidden Common overrides are better off the Javadoc.
     */
    @Override
    public String toString() {
        return new StringJoiner(", ", SourceContactRelation.class.getSimpleName() + "[", "]")
                .add("contact=" + contact)
                .add("role=" + role)
                .toString();
    }

    /**
     * A utility class to create {@link SourceContactRelation} instances following the <em>Builder</em> design pattern.
     */
    public static final class Builder {

        private String role;
        private String name;
        private String email;
        private String phoneNumber;

        private Builder() {
        }

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

        public SourceContactRelation build() {
            if (email == null)
                throw new NullPointerException("email cannot be null");
            if (role == null)
                throw new NullPointerException("role cannot be null");
            return new SourceContactRelation(this);
        }
    }

}
