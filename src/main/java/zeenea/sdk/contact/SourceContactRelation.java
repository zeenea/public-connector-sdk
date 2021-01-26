package zeenea.sdk.contact;

public class SourceContactRelation {
    private final SourceContact contact;
    private final SourceRole role;

    private SourceContactRelation(Builder builder) {
        this.contact = new SourceContact(builder.firstName, builder.lastName, builder.email, builder.phoneNumber);
        this.role = new SourceRole(builder.role);
    }

    public static Builder builder() {
        return new Builder();
    }

    public SourceContact getContact() {
        return contact;
    }

    public SourceRole getRole() {
        return role;
    }

    public static final class Builder {

        private String role;
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;

        private Builder() {
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
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