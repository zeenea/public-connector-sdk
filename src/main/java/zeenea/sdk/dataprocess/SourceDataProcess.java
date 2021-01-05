package zeenea.sdk.dataprocess;

import zeenea.sdk.SourceItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class SourceDataProcess extends SourceItem {

    private final String externalId;

    private final Collection<DatasetReference> inputs;
    private final Collection<DatasetReference> outputs;

    private SourceDataProcess(Builder builder) {
        super(builder);
        this.externalId = builder.externalId;
        this.inputs = builder.inputs;
        this.outputs = builder.outputs;
    }

    public String getExternalId() {
        return externalId;
    }

    public Collection<DatasetReference> getInputs() {
        return Collections.unmodifiableCollection(inputs);
    }

    public Collection<DatasetReference> getOutputs() {
        return Collections.unmodifiableCollection(outputs);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends SourceItem.Builder<SourceDataProcess, Builder> {

        private final List<DatasetReference> inputs = new ArrayList<>();
        private final List<DatasetReference> outputs = new ArrayList<>();
        private String externalId;

        public Builder externalId(String externalId) {
            this.externalId = externalId;
            return this;
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
        protected SourceDataProcess performBuild(Builder builder) {
            throwIfNull("externalId", externalId);
            return new SourceDataProcess(builder);
        }
    }
}
