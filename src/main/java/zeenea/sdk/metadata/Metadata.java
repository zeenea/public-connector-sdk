package zeenea.sdk.metadata;

import zeenea.sdk.annotations.Beta;

@Beta
public abstract class Metadata {

    private final String code;
    private final MetadataType type;

    Metadata(String code, MetadataType type) {
        this.code = code;
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public MetadataType getType() {
        return type;
    }
}
