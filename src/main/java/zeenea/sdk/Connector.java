package zeenea.sdk;

import zeenea.sdk.annotations.Beta;
import zeenea.sdk.metadata.Metadata;

import java.util.Set;

/**
 * documentation
 * <img alt="Connector sequence diagram" src="/doc-files/connector-sequence-diagram.png">
 */
@Beta
public interface Connector extends Synchronizable, AutoCloseable {

    /**
     * Called before synchronization to get list of all properties that describes items this connector is able to
     * synchronize.
     * <p>
     * If technical metadata are different than previous execution (in case of a new connector release) then
     * synchronize(null) will be called to get all items available. Else an incremental synchronize is called. If some
     * technical metadata is removed, no metadata will be removed from already imported items.
     *
     * @return a set of Metadata
     */
    Set<Metadata> getTechnicalMetadata();

}
