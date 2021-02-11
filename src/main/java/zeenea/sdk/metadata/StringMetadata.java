package zeenea.sdk.metadata;

/**
 * Sub-class of {@link Metadata} associated to a value of type {@code String}.
 *
 * @see Metadata
 * @see StringMetadataValue
 * @since 1.0.0
 */
public class StringMetadata extends Metadata {

    /**
     * Create a new instance of StringMetadata, a metadata of type {@code MetadataType.STRING}.
     *
     * @param code The metadata code
     */
    public StringMetadata(String code) {
        super(code, MetadataType.STRING);
    }

}
