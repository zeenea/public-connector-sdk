package zeenea.sdk.example.movies;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zeenea.sdk.ContactRelation;
import zeenea.sdk.SynchronizationResult;
import zeenea.sdk.businessterm.BusinessTermConnector;
import zeenea.sdk.businessterm.SourceBusinessTerm;
import zeenea.sdk.example.movies.catalog.MovieTerms;
import zeenea.sdk.metadata.Metadata;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class MovieTermsConnector implements BusinessTermConnector {
    private static final Logger LOGGER = LoggerFactory.getLogger(MovieTermsConnector.class);
    public static final String CONNECTOR_ID = "movie-terms-connector";

    public static final ContactRelation DEFAULT_CONTACT = new ContactRelation() {
    };

    @Override
    public Set<Metadata> getTechnicalMetadata() {
        LOGGER.debug("getTechnicalMetadata()");
        return new HashSet<>(Arrays.asList(
                MovieTerms.ORIGIN
        ));
    }

    @Override
    public SynchronizationResult<SourceBusinessTerm> synchronize(Long lastSuccessfulVersion) {
        LOGGER.debug("synchronize({})", lastSuccessfulVersion);
        return new SynchronizationResult<>(Arrays.asList(
                MovieTerms.MOVIE,
                MovieTerms.CASTING,
                MovieTerms.RATING,
                MovieTerms.ADVERTISEMENT
        ).stream());
    }

    @Override
    public void close() throws Exception {
        LOGGER.debug("close(): no-op");
        //no op : there is no open connection
    }
}
