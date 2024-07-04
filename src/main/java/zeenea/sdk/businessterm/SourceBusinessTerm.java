package zeenea.sdk.businessterm;

import java.util.Objects;
import java.util.StringJoiner;
import zeenea.sdk.SourceItem;

/**
 * An entry in a Glossary that documents a business concept.
 *
 * @see SourceItem
 * @since 1.0.0
 * @deprecated since 2.0.0, see 'zeenea.connector' package
 */
@Deprecated
public final class SourceBusinessTerm extends SourceItem {

    private SourceBusinessTerm(Builder builder) {
        super(builder);
    }

    /**
     * Create a new {@link SourceBusinessTerm} builder.
     *
     * @return A business term builder
     */
    public static Builder builder() {
        return new Builder();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SourceBusinessTerm that = (SourceBusinessTerm) o;
        return Objects.equals(this.getName(), that.getName()) &&
                Objects.equals(this.getId(), that.getId()) &&
                Objects.equals(this.getDescription(), that.getDescription()) &&
                Objects.equals(this.getMetadata(), that.getMetadata()) &&
                Objects.equals(this.getUpdateTime(), that.getUpdateTime()) &&
                Objects.equals(this.getContactRelations(), that.getContactRelations());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getName(),
                getId(),
                getDescription(),
                getMetadata(),
                getUpdateTime(),
                getContactRelations());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SourceBusinessTerm.class.getSimpleName() + "[", "]")
                .add("name='" + getName() + "'")
                .add("id='" + getId() + "'")
                .add("description='" + getDescription() + "'")
                .add("metadata='" + getMetadata() + "'")
                .add("updateTime='" + getUpdateTime() + "'")
                .add("contactRelations='" + getContactRelations() + "'")
                .toString();
    }

    /**
     * A utility class to create {@link SourceBusinessTerm} instances following the <em>Builder</em> design pattern.
     */
    public static class Builder extends SourceItem.Builder<SourceBusinessTerm, Builder> {
        private Builder() {
        }

        @Override
        protected SourceBusinessTerm performBuild() {
            return new SourceBusinessTerm(this);
        }
    }

}

