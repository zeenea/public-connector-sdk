package zeenea.sdk.property;

import java.util.UUID;

public abstract class Metadata {

    private final String code;
    private final UUID id;
    private final PropertyType type;

    Metadata(String connectorId, String code, PropertyType type) {
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

    public PropertyType getType() {
        return type;
    }
}
