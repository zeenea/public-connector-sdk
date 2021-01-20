package zeenea.sdk.contact;

public class SourceContactRelation {
    private final SourceContact contact;
    private final SourceRole role;

    public SourceContactRelation(SourceContact contact, SourceRole role) {
        this.contact = contact;
        this.role = role;
    }

    public SourceContact getContact() {
        return contact;
    }

    public SourceRole getRole() {
        return role;
    }
}
