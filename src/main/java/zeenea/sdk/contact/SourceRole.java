package zeenea.sdk.contact;

import zeenea.sdk.SourceItem;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * The qualifier of the relationship between a SourceContact and a SourceItem.
 *
 * @see SourceContactRelation
 * @see SourceContact
 * @see SourceItem
 * @since 1.0.0
 */
public class SourceRole {
    private final String name;

    SourceRole(String name) {
        this.name = name;
    }

    /**
     * Get the name of the source role.
     *
     * @return The name of the source role
     */
    public String getName() {
        return name;
    }

    /**
     * @hidden Common overrides are better off the Javadoc.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SourceRole that = (SourceRole) o;
        return Objects.equals(name, that.name);
    }

    /**
     * @hidden Common overrides are better off the Javadoc.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * @hidden Common overrides are better off the Javadoc.
     */
    @Override
    public String toString() {
        return new StringJoiner(", ", SourceRole.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .toString();
    }
}
