package zeenea.sdk.contact;

import java.util.Optional;

public class SourceContact {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phoneNumber;

    SourceContact(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }


    public Optional<String> getFirstName() {
        return Optional.ofNullable(firstName);
    }

    public Optional<String> getLastName() {
        return Optional.ofNullable(lastName);
    }

    public String getEmail() {
        return email;
    }

    public Optional<String> getPhoneNumber() {
        return Optional.ofNullable(phoneNumber);
    }

}
