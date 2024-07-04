package zeenea.connector.example.movies;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zeenea.connector.action.ItemAction;
import zeenea.connector.example.movies.catalog.MovieTerms;
import zeenea.connector.metadata.Metadata;
import zeenea.connector.source.contact.SourceContactRelation;
import zeenea.connector.synchronize.SynchronizeConnection;
import zeenea.connector.synchronize.SynchronizeResult;

/** */
public class MovieTermsConnector implements SynchronizeConnection {
  private static final Logger LOGGER = LoggerFactory.getLogger(MovieTermsConnector.class);
  private static final String CONNECTOR_ID = "movie-terms-connector";

  public static final SourceContactRelation DEFAULT_CONTACT =
      SourceContactRelation.builder().email("foobar@example.com").role("user").build();

  @Override
  public Set<Metadata> getTechnicalMetadata() {
    LOGGER.debug("getTechnicalMetadata()");
    return new HashSet<>(Collections.singletonList(MovieTerms.ORIGIN));
  }

  @Override
  public SynchronizeResult synchronize() {
    LOGGER.debug("synchronize()");
    return new SynchronizeResult(
        Stream.of(
            ItemAction.upsert(MovieTerms.MOVIE),
            ItemAction.upsert(MovieTerms.CASTING),
            ItemAction.upsert(MovieTerms.RATING),
            ItemAction.upsert(MovieTerms.ADVERTISEMENT)));
  }

  @Override
  public void close() {
    LOGGER.debug("close(): no-op");
    // no op : there is no open connection
  }
}
