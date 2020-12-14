package zeenea.sdk.customitem;

import zeenea.sdk.BaseBuilder;
import zeenea.sdk.ContactRelation;
import zeenea.sdk.property.PropertyValue;

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
    private final Map<UUID, PropertyValue> metadata;

    // last update time
    private final Instant updateTime;

    private final Collection<ContactRelation> contactRelations;

    // no schemaVersion because no inventory


    private CustomItem(String name, String id, String code, String description, Map<UUID, PropertyValue> metadata, Instant updateTime, Collection<ContactRelation> contactRelations) {
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

    public Map<UUID, PropertyValue> getMetadata() {
        return Collections.unmodifiableMap(metadata);
    }

    public Optional<Instant> getUpdateTime() {
        return Optional.ofNullable(updateTime);
    }

    public Collection<ContactRelation> getContactRelations() {
        return Collections.unmodifiableCollection(contactRelations);
    }

    public static class Builder extends BaseBuilder<CustomItem, Builder> {

        private final String code;

        public Builder(String name, String id, String code) {
            super(name, id);
            this.code = code;
        }

        @Override
        protected CustomItem performBuild(String name, String id, Map<UUID, PropertyValue> metadata, List<ContactRelation> contactRelations, String description, Instant updateTime) {
            return new CustomItem(name, id, code, description, metadata, updateTime, contactRelations);
        }
    }

}

