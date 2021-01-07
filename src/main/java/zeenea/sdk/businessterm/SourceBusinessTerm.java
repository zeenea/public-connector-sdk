package zeenea.sdk.businessterm;

import zeenea.sdk.SourceItem;

/**
 * Synchronized or manually created Item corresponding to a business or technical concept used in the enterprise.
 * Custom items are used in the catalog to bring context to technical assets.
 *
 * @since 1.0.0
 */
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

}

