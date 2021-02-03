package zeenea.sdk.businessterm;

import zeenea.sdk.SourceItem;
import zeenea.sdk.annotations.Beta;

import java.util.Collections;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Synchronized or manually created Item corresponding to a business or technical concept used in the enterprise.
 * Custom items are used in the catalog to bring context to technical assets.
 *
 * @since 1.0.0
 */
@Beta
public final class SourceBusinessTerm extends SourceItem {

    // no schemaVersion because no inventory

    private SourceBusinessTerm(Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends SourceItem.Builder<SourceBusinessTerm, Builder> {
        @Override
        protected SourceBusinessTerm performBuild() {
            return new SourceBusinessTerm(this);
        }
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

}

