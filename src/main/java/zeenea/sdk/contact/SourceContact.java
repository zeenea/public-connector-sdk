package zeenea.sdk.contact;

import java.util.Optional;

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

}
