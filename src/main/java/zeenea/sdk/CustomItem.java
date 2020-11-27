package zeenea.sdk;

import java.time.Instant;
import java.util.*;

/**
 * Synchronized or manually created Item corresponding to a business or technical concept used in the enterprise.
 * Custom items are used in the catalog to bring context to technical assets.
 *
 * @since 1.0.0
 */
public final class CustomItem {

    // max 1024 exemple : Application
    // obligatoire
    private final String name;

    // obligatoire
    // max 1024
    // identifiant externe du custom item
    // nécessite une unicité sinon écrasement :fearful:
    private final String id;

    // ancien custom item type = code (example: APP for Application)
    // obligatoire
    private final String code;

    // max 32 * 1024
    private final String description;

    // nécessaire pour exploiter dans le moteur de recherche
    private final Map<String, Metadata> metadata;

    // last update time
    private final Instant updateTime;

    private final Collection<ContactRelation> contactRelations;

    // no schemaVersion because no inventory


    private CustomItem(String name, String id, String code, String description, Map<String, Metadata> metadata, Instant updateTime, Collection<ContactRelation> contactRelations) {
        this.name = name;
        this.id = id;
        this.code = code;
        this.description = description;
        this.metadata = metadata;
        this.updateTime = updateTime;
        this.contactRelations = contactRelations;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    public Map<String, Metadata> getMetadata() {
        return Collections.unmodifiableMap(metadata);
    }

    public Optional<Instant> getUpdateTime() {
        return Optional.ofNullable(updateTime);
    }

    public Collection<ContactRelation> getContactRelations() {
        return Collections.unmodifiableCollection(contactRelations);
    }

    public static class Builder {

        private final String name;
        private final String id;
        private final String code;
        private final Map<String, Metadata> metadata = new HashMap<>();
        private final List<ContactRelation> contactRelations = new ArrayList<>();
        private String description;
        private Instant updateTime;

        public Builder(String name, String id, String code) {
            this.name = ensureMaxLength(name, "name", 1024);
            this.id = ensureMaxLength(id, "id", 1024);
            this.code = code;
        }

        public Builder description(String description) {
            this.description = ensureMaxLength(id, "id", 32 * 1024);
            return this;
        }

        public Builder addMetadata(String key, Metadata metadata) {
            this.metadata.put(key, metadata);
            return this;
        }

        public Builder updateTime(Instant updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        public Builder addContactRelation(ContactRelation contactRelation) {
            this.contactRelations.add(contactRelation);
            return this;
        }

        public CustomItem build() {
            return new CustomItem(name, id, code, description, metadata, updateTime, contactRelations);
        }

        private static String ensureMaxLength(String value, String name, int maxLength) {
            if (value.length() > maxLength)
                throw new IllegalArgumentException("Attribute \"" + name + "\" cannot be more than " + maxLength + " characters long");
            return value;
        }
    }

}

