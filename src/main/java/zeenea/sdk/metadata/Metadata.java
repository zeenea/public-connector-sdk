package zeenea.sdk.metadata;

import zeenea.sdk.annotations.Beta;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Base class for all kinds of metadata.
 * A metadata is a definition of an element of the metamodel valued by the data source, for an item.
 * Metadata is qualified by type and code.
 *
 * @see MetadataType
 * @see MetadataValue
 * @since 1.0.0
 */
@Beta
public abstract class Metadata {

    private final String code;
    private final MetadataType type;

    Metadata(String code, MetadataType type) {
        this.code = code;
        this.type = type;
    }

    /**
     * Get the code of the metadata.
     *
     * @return The code of the metadata
     */
    public String getCode() {
        return code;
    }

    /**
     * Get the type of the metadata.
     *
     * @return The type of the metadata
     */
    public MetadataType getType() {
        return type;
    }

    /**
     * @hidden Common overrides are better off the Javadoc.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Metadata metadata = (Metadata) o;
        return Objects.equals(code, metadata.code) && type == metadata.type;
    }

    /**
     * @hidden Common overrides are better off the Javadoc.
     */
    @Override
    public int hashCode() {
        return Objects.hash(code, type);
    }

    /**
     * @hidden Common overrides are better off the Javadoc.
     */
    @Override
    public String toString() {
        return new StringJoiner(", ", Metadata.class.getSimpleName() + "[", "]")
                .add("code='" + code + "'")
                .add("type=" + type)
                .toString();
    }
}
