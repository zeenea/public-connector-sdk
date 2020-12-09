package zeenea.sdk;

import zeenea.sdk.property.*;

import java.time.Instant;
import java.util.*;

public abstract class BaseBuilder<T, SELF extends BaseBuilder<T, ?>> {

    private final String name;
    private final String id;
    private final Map<UUID, PropertyValue> metadata = new HashMap<>();
    private final List<ContactRelation> contactRelations = new ArrayList<>();
    private String description;
    private Instant updateTime;

    public BaseBuilder(String name, String id) {
        this.name = ensureAttributeMaxLength("name", name, 1024);
        this.id = ensureAttributeMaxLength("id", id, 1024);
    }

    public SELF description(String description) {
        this.description = ensureAttributeMaxLength("description", description, 32 * 1024);
        return self();
    }

    public SELF addStringMetadata(StringMetadata metadata, StringPropertyValue value) {
        return addMetadata(metadata, value);
    }

    public SELF addNumberMetadata(NumberMetadata metadata, NumberPropertyValue value) {
        return addMetadata(metadata, value);
    }

    public SELF addUrlMetadata(UrlMetadata metadata, UrlPropertyValue value) {
        return addMetadata(metadata, value);
    }

    public SELF addInstantMetadata(InstantMetadata metadata, InstantPropertyValue value) {
        return addMetadata(metadata, value);
    }

    private SELF addMetadata(Metadata metadata, PropertyValue value) {
        this.metadata.put(metadata.getId(), value);
        return self();
    }

    public SELF updateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return self();
    }

    public SELF addContactRelation(ContactRelation contactRelation) {
        this.contactRelations.add(contactRelation);
        return self();
    }

    public final T build() {
        return performBuild(name, id, metadata, contactRelations, description, updateTime);
    }

    protected abstract T performBuild(String name, String id, Map<UUID, PropertyValue> metadata, List<ContactRelation> contactRelations, String description, Instant updateTime);

    protected static String ensureAttributeMaxLength(String attributeName, String attributeValue, int maxLength) {
        if (attributeValue.length() > maxLength)
            throw new IllegalArgumentException("Attribute \"" + attributeName + "\" cannot be more than " + maxLength + " characters long");
        return attributeValue;
    }

    private SELF self() {
        return (SELF) this;
    }
}