package zeenea.sdk;

import zeenea.sdk.property.*;

import java.time.Instant;
import java.util.*;

public abstract class SourceItem {

    // max 1024 exemple : Application
    // obligatoire
    private final String name;

    // obligatoire
    // max 1024
    // identifiant externe du custom item
    // nécessite une unicité sinon écrasement :fearful:
    private final String id;

    // max 32 * 1024
    private final String description;

    // nécessaire pour exploiter dans le moteur de recherche
    private final Map<UUID, PropertyValue> metadata;

    // last update time
    private final Instant updateTime;

    private final Collection<ContactRelation> contactRelations;

    protected SourceItem(Builder<?, ?> builder) {
        this.name = builder.name;
        this.id = builder.id;
        this.description = builder.description;
        this.metadata = builder.metadata;
        this.updateTime = builder.updateTime;
        this.contactRelations = builder.contactRelations;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    public Map<UUID, PropertyValue> getMetadata() {
        return Collections.unmodifiableMap(metadata);
    }

    public Optional<Instant> getUpdateTime() {
        return Optional.ofNullable(updateTime);
    }

    public Collection<ContactRelation> getContactRelations() {
        return Collections.unmodifiableCollection(contactRelations);
    }

    public static abstract class Builder<T, SELF extends Builder<T, ?>> {

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
            return performBuild(self());
        }

        protected abstract T performBuild(SELF self);

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
}
