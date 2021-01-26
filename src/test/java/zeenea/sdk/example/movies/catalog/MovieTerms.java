package zeenea.sdk.example.movies.catalog;

import zeenea.sdk.businessterm.SourceBusinessTerm;
import zeenea.sdk.contact.SourceContactRelation;
import zeenea.sdk.metadata.StringMetadata;

import java.time.Instant;

public class MovieTerms {
    public static final StringMetadata ORIGIN = new StringMetadata("origin");
    public static final String HARD_CODED = "hard-coded";

    private static final Instant UPDATE_TIME = Instant.parse("2021-01-14T11:00:37+00:00");
    public static SourceBusinessTerm MOVIE = SourceBusinessTerm.builder()
            .id("movie")
            .name("Movie")
            .description("A film shown in a cinema or on television and often telling a story.")
            .updateTime(UPDATE_TIME)
            .addMetadata(ORIGIN, HARD_CODED)
            .addContactRelation(SourceContactRelation.builder()
                    .email("john.doe@example.com")
                    .name("John Doe")
                    .phoneNumber("+33 6 00 00 00 00")
                    .role("Data owner")
                    .build())
            .build();

    public static SourceBusinessTerm CASTING = SourceBusinessTerm.builder()
            .id("casting")
            .name("Casting")
            .description("The actors in a film, play, or show.")
            .updateTime(UPDATE_TIME)
            .addMetadata(ORIGIN, HARD_CODED)
            .build();


    public static SourceBusinessTerm RATING = SourceBusinessTerm.builder()
            .id("rating")
            .name("Rating")
            .description("A measurement of how good or popular a movie is.")
            .updateTime(UPDATE_TIME)
            .addMetadata(ORIGIN, HARD_CODED)
            .build();


    public static SourceBusinessTerm ADVERTISEMENT = SourceBusinessTerm.builder()
            .id("advertisement")
            .name("Advertisement")
            .description("A short film that tries to persuade people to buy a product or service, displayed at the beginning of a movie trailer.")
            .updateTime(UPDATE_TIME)
            .addMetadata(ORIGIN, HARD_CODED)
            .build();
}
