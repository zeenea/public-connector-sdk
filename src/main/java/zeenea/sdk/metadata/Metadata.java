package zeenea.sdk.metadata;

import zeenea.sdk.annotations.Beta;

import java.util.UUID;

@Beta
public abstract class Metadata {

    private final String code;
    private final UUID id;
    private final MetadataType type;

    Metadata(String connectorId, String code, MetadataType type) {
        this.code = code;
        this.type = type;
        this.id = UUID.nameUUIDFromBytes((connectorId + code + type.name()).getBytes());
    }

    public String getCode() {
        return code;
    }

    public UUID getId() {
        return id;
    }

    public MetadataType getType() {
        return type;
    }
}
