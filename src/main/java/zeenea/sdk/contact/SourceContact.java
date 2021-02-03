package zeenea.sdk.contact;

import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;

public class SourceContact {
    private final String name;
    private final String email;
    private final String phoneNumber;

    SourceContact(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }


    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public String getEmail() {
        return email;
    }

    public Optional<String> getPhoneNumber() {
        return Optional.ofNullable(phoneNumber);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SourceContact that = (SourceContact) o;
        return Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(phoneNumber, that.phoneNumber);
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
