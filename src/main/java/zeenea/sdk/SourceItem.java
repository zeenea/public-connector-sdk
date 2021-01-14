package zeenea.sdk;

import zeenea.sdk.annotations.Beta;
import zeenea.sdk.property.*;

import java.time.Instant;
import java.util.*;

@Beta
public abstract class SourceItem {

    private final String name;

    private final String id;

    private final String description;

    private final Map<UUID, PropertyValue> metadata;

    private final Instant updateTime;

    private final Collection<ContactRelation> contactRelations;

    protected SourceItem(Builder<?, ?> builder) {
        this.name = builder.name;
        this.id = builder.id;
        this.description = builder.description;
        this.metadata = new HashMap<>(builder.metadata);
        this.updateTime = builder.updateTime;
        this.contactRelations = new ArrayList<>(builder.contactRelations);
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

        public String getName() {
            return name;
        }

        /**
         * Set the name of the source item.
         * This is required to build a SourceItem.
         *
         * @param name The name of the source item, cannot be longer than 1024 characters
         * @return This builder
         */
        public SELF name(String name) {
            this.name = name;
            return self();
        }

        public String getId() {
            return id;
        }

        /**
         * Set the id of the source item.
         * Must be unique to the source item.
         * This is required to build a SourceItem.
         *
         * @param id The id of the source item, cannot be longer than 1024 characters
         * @return This builder
         */
        public SELF id(String id) {
            this.id = id;
            return self();
        }

        public String getDescription() {
            return description;
        }

        /**
         * Set the description of the source item.
         *
         * @param description The description of the source item, cannot be longer than 32768 (32x1024) characters
         * @return This builder
         */
        public SELF description(String description) {
            this.description = description;
            return self();
        }

        public Map<UUID, PropertyValue> getMetadata() {
            return metadata;
        }

        /**
         * Add a StringMetadata and its value to the source item.
         * Metadata are necessary for search engine usage.
         *
         * @param metadata the metadata
         * @param value the value for this metadata
         * @return This builder
         */
        public SELF addMetadata(StringMetadata metadata, StringPropertyValue value) {
            return putMetadata(metadata, value);
        }

        /**
         * Add a NumberMetadata and its value to the source item.
         * Metadata are necessary for search engine usage.
         *
         * @param metadata the metadata
         * @param value the value for this metadata
         * @return This builder
         */
        public SELF addMetadata(NumberMetadata metadata, NumberPropertyValue value) {
            return putMetadata(metadata, value);
        }

        /**
         * Add an UrlMetadata and its value to the source item.
         * Metadata are necessary for search engine usage.
         *
         * @param metadata the metadata
         * @param value the value for this metadata
         * @return This builder
         */
        public SELF addMetadata(UrlMetadata metadata, UrlPropertyValue value) {
            return putMetadata(metadata, value);
        }

        /**
         * Add an InstantMetadata and its value to the source item.
         * Metadata are necessary for search engine usage.
         *
         * @param metadata the metadata
         * @param value the value for this metadata
         * @return This builder
         */
        public SELF addMetadata(InstantMetadata metadata, InstantPropertyValue value) {
            return putMetadata(metadata, value);
        }

        private SELF putMetadata(Metadata metadata, PropertyValue value) {
            this.metadata.put(metadata.getId(), value);
            return self();
        }

        public Instant getUpdateTime() {
            return updateTime;
        }

        /**
         * Set the last update time of the source item.
         *
         * @param updateTime The last update time of the source item
         * @return This builder
         */
        public SELF updateTime(Instant updateTime) {
            this.updateTime = updateTime;
            return self();
        }

        public List<ContactRelation> getContactRelations() {
            return contactRelations;
        }

        /**
         * Add a ContactRelation to the source item.
         *
         * @param contactRelation the ContactRelation to add
         * @return This builder
         */
        public SELF addContactRelation(ContactRelation contactRelation) {
            this.contactRelations.add(contactRelation);
            return self();
        }

        /**
         * Build the source item.
         *
         * @return The source item
         */
        public final T build() {
            throwIfNull("name", name);
            throwIfInvalidLength("name", name, 1024);
            throwIfNull("id", id);
            throwIfInvalidLength("id", id, 1024);
            throwIfInvalidLength("description", description, 32 * 1024);
            return performBuild();
        }

        protected abstract T performBuild();

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
