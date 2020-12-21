package zeenea.sdk.businessterm;

import zeenea.sdk.BaseSourceItemBuilder;
import zeenea.sdk.ContactRelation;
import zeenea.sdk.SourceItem;
import zeenea.sdk.property.PropertyValue;

import java.time.Instant;
import java.util.*;

/**
 * Synchronized or manually created Item corresponding to a business or technical concept used in the enterprise.
 * Custom items are used in the catalog to bring context to technical assets.
 *
 * @since 1.0.0
 */
public final class SourceBusinessTerm implements SourceItem {

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

    // no schemaVersion because no inventory


    private SourceBusinessTerm(String name, String id, String description, Map<UUID, PropertyValue> metadata, Instant updateTime, Collection<ContactRelation> contactRelations) {
        this.name = name;
        this.id = id;
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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends BaseSourceItemBuilder<SourceBusinessTerm, Builder> {

        @Override
        protected SourceBusinessTerm performBuild(String name, String id, Map<UUID, PropertyValue> metadata, List<ContactRelation> contactRelations, String description, Instant updateTime) {
            return new SourceBusinessTerm(name, id, description, metadata, updateTime, contactRelations);
        }
    }

}

