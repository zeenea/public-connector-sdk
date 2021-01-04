package zeenea.sdk;

import zeenea.sdk.property.*;

import java.time.Instant;
import java.util.*;

public abstract class BaseSourceItemBuilder<T, SELF extends BaseSourceItemBuilder<T, ?>> {

    private final Map<UUID, PropertyValue> metadata = new HashMap<>();
    private final List<ContactRelation> contactRelations = new ArrayList<>();
    private String name;
    private String id;
    private String description;
    private Instant updateTime;

    public SELF name(String name) {
        this.name = name;
        return self();
    }

    public SELF id(String id) {
        this.id = id;
        return self();
    }

    public SELF description(String description) {
        this.description = description;
        return self();
    }

    public SELF addMetadata(StringMetadata metadata, StringPropertyValue value) {
        return putMetadata(metadata, value);
    }

    public SELF addMetadata(NumberMetadata metadata, NumberPropertyValue value) {
        return putMetadata(metadata, value);
    }

    public SELF addMetadata(UrlMetadata metadata, UrlPropertyValue value) {
        return putMetadata(metadata, value);
    }

    public SELF addMetadata(InstantMetadata metadata, InstantPropertyValue value) {
        return putMetadata(metadata, value);
    }

    private SELF putMetadata(Metadata metadata, PropertyValue value) {
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
        throwIfNull("name", name);
        throwIfInvalidLength("name", name, 1024);
        throwIfNull("id", id);
        throwIfInvalidLength("id", id, 1024);
        throwIfInvalidLength("description", description, 32 * 1024);
        return performBuild(name, id, metadata, contactRelations, description, updateTime);
    }

    protected abstract T performBuild(String name, String id, Map<UUID, PropertyValue> metadata, List<ContactRelation> contactRelations, String description, Instant updateTime);

    protected static void throwIfNull(String attributeName, String attributeValue) {
        if (attributeValue == null)
            throw new NullPointerException("Attribute \"" + attributeName + "\" cannot be null");
    }

    protected static void throwIfInvalidLength(String attributeName, String attributeValue, int maxLength) {
        if (attributeValue != null && attributeValue.length() > maxLength)
            throw new IllegalArgumentException("Attribute \"" + attributeName + "\" cannot be more than " + maxLength + " characters long");
    }

    private SELF self() {
        return (SELF) this;
    }
}