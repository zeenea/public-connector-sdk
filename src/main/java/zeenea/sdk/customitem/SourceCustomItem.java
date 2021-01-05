package zeenea.sdk.customitem;

import zeenea.sdk.SourceItem;

/**
 * Synchronized or manually created Item corresponding to a business or technical concept used in the enterprise.
 * Custom items are used in the catalog to bring context to technical assets.
 *
 * @since 1.0.0
 */
public final class SourceCustomItem extends SourceItem {

    // ancien custom item type = code (example: APP for Application)
    // obligatoire
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

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        @Override
        protected SourceCustomItem performBuild(Builder builder) {
            throwIfNull("code", code);
            return new SourceCustomItem(builder);
        }
    }

}

