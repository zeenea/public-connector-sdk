package zeenea.sdk.example.movies;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zeenea.sdk.Connector;
import zeenea.sdk.contact.SourceContactRelation;
import zeenea.sdk.example.movies.catalog.MovieTerms;
import zeenea.sdk.metadata.Metadata;
import zeenea.sdk.synchronization.SourceItemAction;
import zeenea.sdk.synchronization.SynchronizationResult;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class MovieTermsConnector implements Connector {
    private static final Logger LOGGER = LoggerFactory.getLogger(MovieTermsConnector.class);
    private static final String CONNECTOR_ID = "movie-terms-connector";

    public static final SourceContactRelation DEFAULT_CONTACT = SourceContactRelation.builder()
            .email("foobar@example.com")
            .role("user")
            .build();

    @Override
    public Set<Metadata> getTechnicalMetadata() {
        LOGGER.debug("getTechnicalMetadata()");
        return new HashSet<>(Arrays.asList(
                MovieTerms.ORIGIN
        ));
    }

    @Override
    public SynchronizationResult synchronize() {
        LOGGER.debug("synchronize()");
        return new SynchronizationResult(Arrays.asList(
                SourceItemAction.upsert(MovieTerms.MOVIE),
                SourceItemAction.upsert(MovieTerms.CASTING),
                SourceItemAction.upsert(MovieTerms.RATING),
                SourceItemAction.upsert(MovieTerms.ADVERTISEMENT)
        ).stream());
    }

    @Override
    public void close() throws Exception {
        LOGGER.debug("close(): no-op");
        //no op : there is no open connection
    }
}
