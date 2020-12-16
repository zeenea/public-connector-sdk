package zeenea.sdk.dataprocess;

import zeenea.sdk.BaseBuilder;
import zeenea.sdk.ContactRelation;
import zeenea.sdk.SourceItem;
import zeenea.sdk.property.*;

import java.time.Instant;
import java.util.*;

public final class SourceDataProcess implements SourceItem {

    private final String name;

    // obligatoire
    // max 1024
    private final String id;

    private final String externalId;

    // max 32 * 1024
    private final String description;

    // nécessaire pour exploiter dans le moteur de recherche
    private final Map<UUID, PropertyValue> metadata;

    // last update time
    private final Instant updateTime;

    private final Collection<DatasetReference> inputs;
    private final Collection<DatasetReference> outputs;

    private final Collection<ContactRelation> contactRelations;

    private SourceDataProcess(String name, String id, String externalId, String description, Map<UUID, PropertyValue> metadata, Instant updateTime, Collection<DatasetReference> inputs, Collection<DatasetReference> outputs, Collection<ContactRelation> contactRelations) {
        this.name = name;
        this.id = id;
        this.externalId = externalId;
        this.description = description;
        this.metadata = metadata;
        this.updateTime = updateTime;
        this.inputs = inputs;
        this.outputs = outputs;
        this.contactRelations = contactRelations;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getExternalId() {
        return externalId;
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

    public Collection<DatasetReference> getInputs() {
        return Collections.unmodifiableCollection(inputs);
    }

    public Collection<DatasetReference> getOutputs() {
        return Collections.unmodifiableCollection(outputs);
    }

    public static class Builder extends BaseBuilder<SourceDataProcess, Builder> {

        private final String externalId;
        private final List<DatasetReference> inputs = new ArrayList<>();
        private final List<DatasetReference> outputs = new ArrayList<>();

        public Builder(String name, String id, String externalId) {
            super(name, id);
            this.externalId = externalId;
        }

        public Builder addInput(DatasetReference datasetReference) {
            this.inputs.add(datasetReference);
            return this;
        }

        public Builder addOutput(DatasetReference datasetReference) {
            this.outputs.add(datasetReference);
            return this;
        }

        @Override
        protected SourceDataProcess performBuild(String name, String id, Map<UUID, PropertyValue> metadata, List<ContactRelation> contactRelations, String description, Instant updateTime) {
            return new SourceDataProcess(name, id, externalId, description, metadata, updateTime, inputs, outputs, contactRelations);
        }
    }
}
