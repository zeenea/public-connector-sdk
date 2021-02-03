package zeenea.sdk.customitem;

import zeenea.sdk.SourceItem;
import zeenea.sdk.annotations.Beta;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Synchronized or manually created Item corresponding to a business or technical concept used in the enterprise.
 * Custom items are used in the catalog to bring context to technical assets.
 *
 * @since 1.0.0
 */
@Beta
public final class SourceCustomItem extends SourceItem {

    private final String code;

    // no schemaVersion because no inventory

    private SourceCustomItem(Builder builder) {
        super(builder);
        this.code = builder.code;
    }

    public String getCode() {
        return code;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends SourceItem.Builder<SourceCustomItem, Builder> {

        private String code;

        /**
         * Set the code of the custom item.
         * This is required.
         *
         * @param code The code of the custom item
         * @return This builder
         */
        public Builder code(String code) {
            this.code = code;
            return this;
        }

        @Override
        protected SourceCustomItem performBuild() {
            throwIfNull("code", code);
            return new SourceCustomItem(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SourceCustomItem that = (SourceCustomItem) o;
        return Objects.equals(this.getName(), that.getName()) &&
                Objects.equals(this.getId(), that.getId()) &&
                Objects.equals(this.getDescription(), that.getDescription()) &&
                Objects.equals(this.getMetadata(), that.getMetadata()) &&
                Objects.equals(this.getUpdateTime(), that.getUpdateTime()) &&
                Objects.equals(this.getContactRelations(), that.getContactRelations()) &&
                Objects.equals(this.code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getName(),
                getId(),
                getDescription(),
                getMetadata(),
                getUpdateTime(),
                getContactRelations(),
                code);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SourceCustomItem.class.getSimpleName() + "[", "]")
                .add("name='" + getName() + "'")
                .add("id='" + getId() + "'")
                .add("description='" + getDescription() + "'")
                .add("metadata='" + getMetadata() + "'")
                .add("updateTime='" + getUpdateTime() + "'")
                .add("contactRelations='" + getContactRelations() + "'")
                .add("code='" + code + "'")
                .toString();
    }
}

