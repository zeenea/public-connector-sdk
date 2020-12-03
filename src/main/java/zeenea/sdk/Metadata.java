package zeenea.sdk;

import java.util.Objects;
import java.util.UUID;

// TODO Metadata doit-être typesafe
public class Metadata{

    private final String code;
    private final PropertyType type;
    private final UUID id;

    public Metadata(String connectorId, String code, PropertyType type) {
        this.code = code;
        this.type = type;
        this.id = UUID.nameUUIDFromBytes((connectorId + code + type.name()).getBytes());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Metadata metadata = (Metadata) o;
        return id.equals(metadata.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
