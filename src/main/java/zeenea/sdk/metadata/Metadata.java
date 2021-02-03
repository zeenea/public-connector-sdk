package zeenea.sdk.metadata;

import zeenea.sdk.annotations.Beta;

import java.util.Objects;
import java.util.StringJoiner;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Metadata metadata = (Metadata) o;
        return Objects.equals(code, metadata.code) && type == metadata.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, type);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Metadata.class.getSimpleName() + "[", "]")
                .add("code='" + code + "'")
                .add("type=" + type)
                .toString();
    }
}
