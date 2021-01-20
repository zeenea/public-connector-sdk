package zeenea.sdk.businessterm;

import org.junit.jupiter.api.Test;
import zeenea.sdk.contact.SourceContact;
import zeenea.sdk.contact.SourceContactRelation;
import zeenea.sdk.contact.SourceRole;
import zeenea.sdk.metadata.*;

import java.math.BigDecimal;
import java.net.URI;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
import static zeenea.sdk.TestUtils.longString;

public class SourceBusinessTermTest {

    static final String DEFAULT_NAME = "business-term-name";
    static final String LONG_NAME = longString(1024 + 1);
    static final String DEFAULT_ID = "business-term-id";
    static final String LONG_ID = longString(1024 + 1);
    static final String DEFAULT_DESCRIPTION = "business-term-description";
    static final String LONG_DESCRIPTION = longString(32 * 1024 + 1);
    static final StringMetadata DEFAULT_METADATA_KEY = new StringMetadata("test", "business-term-property-key");
    static final StringMetadataValue DEFAULT_PROPERTY_VALUE = new StringMetadataValue("some-value");
    static final SourceContactRelation DEFAULT_CONTACT_RELATION = new SourceContactRelation(SourceContact.builder().email("foobar@example.com").build(), new SourceRole("user"));
    static final Instant DEFAULT_UPDATE_TIME = Instant.now();

    @Test
    public void businessTermBuilderShouldReturnAProperBusinessTerm() {

        SourceBusinessTerm sourceBusinessTerm = SourceBusinessTerm.builder()
                .name(DEFAULT_NAME)
                .id(DEFAULT_ID)
                .description(DEFAULT_DESCRIPTION)
                .addMetadata(DEFAULT_METADATA_KEY, DEFAULT_PROPERTY_VALUE)
                .addContactRelation(DEFAULT_CONTACT_RELATION)
                .updateTime(DEFAULT_UPDATE_TIME)
                .build();

        assertEquals(DEFAULT_NAME, sourceBusinessTerm.getName());
        assertEquals(DEFAULT_ID, sourceBusinessTerm.getId());
        assertEquals(DEFAULT_DESCRIPTION, sourceBusinessTerm.getDescription().get());
        assertTrue(sourceBusinessTerm.getMetadata().containsKey(DEFAULT_METADATA_KEY.getId()));
        assertTrue(sourceBusinessTerm.getMetadata().containsValue(DEFAULT_PROPERTY_VALUE));
        assertSame(DEFAULT_CONTACT_RELATION, sourceBusinessTerm.getContactRelations().iterator().next());
        assertEquals(DEFAULT_UPDATE_TIME, sourceBusinessTerm.getUpdateTime().get());
    }

    @Test
    public void businessTermBuilderShouldAddProperties() {
        StringMetadata stringMetadata = new StringMetadata("test", "property1");
        StringMetadataValue stringValue = new StringMetadataValue("a string value");
        NumberMetadata numberMetadata = new NumberMetadata("test", "property2");
        NumberMetadataValue numberValue = new NumberMetadataValue(new BigDecimal("42.01"));
        InstantMetadata instantMetadata = new InstantMetadata("test", "property3");
        InstantMetadataValue instantValue = new InstantMetadataValue(Instant.now());
        UrlMetadata urlMetadataWithoutLabel = new UrlMetadata("test", "property4");
        UrlMetadataValue urlValueWithoutLabel = new UrlMetadataValue(URI.create("http://localhost:9000"));
        UrlMetadata urlMetadataWithLabel = new UrlMetadata("test", "property5");
        UrlMetadataValue urlValueWithLabel = new UrlMetadataValue(URI.create("http://localhost:9000"), "zeenea");

        SourceBusinessTerm sourceBusinessTerm = SourceBusinessTerm.builder()
                .name(DEFAULT_NAME)
                .id(DEFAULT_ID)
                .addMetadata(stringMetadata, stringValue)
                .addMetadata(numberMetadata, numberValue)
                .addMetadata(instantMetadata, instantValue)
                .addMetadata(urlMetadataWithoutLabel, urlValueWithoutLabel)
                .addMetadata(urlMetadataWithLabel, urlValueWithLabel)
                .build();

        assertEquals(stringValue, sourceBusinessTerm.getMetadata().get(stringMetadata.getId()));
        assertEquals(numberValue, sourceBusinessTerm.getMetadata().get(numberMetadata.getId()));
        assertEquals(instantValue, sourceBusinessTerm.getMetadata().get(instantMetadata.getId()));
        assertEquals(urlValueWithoutLabel, sourceBusinessTerm.getMetadata().get(urlMetadataWithoutLabel.getId()));
        assertEquals(urlValueWithLabel, sourceBusinessTerm.getMetadata().get(urlMetadataWithLabel.getId()));
    }

    @Test
    public void businessTermBuilderShouldForbidLongName() {

        assertThrows(IllegalArgumentException.class, () -> SourceBusinessTerm.builder()
                .name(LONG_NAME)
                .id(DEFAULT_ID)
                .description(DEFAULT_DESCRIPTION)
                .addContactRelation(DEFAULT_CONTACT_RELATION)
                .updateTime(DEFAULT_UPDATE_TIME)
                .build());
    }

    @Test
    public void businessTermBuilderShouldForbidLongId() {

        assertThrows(IllegalArgumentException.class, () -> SourceBusinessTerm.builder()
                .name(DEFAULT_NAME)
                .id(LONG_ID)
                .description(DEFAULT_DESCRIPTION)
                .addContactRelation(DEFAULT_CONTACT_RELATION)
                .updateTime(DEFAULT_UPDATE_TIME)
                .build());
    }

    @Test
    public void businessTermBuilderShouldForbidLongDescription() {

        assertThrows(IllegalArgumentException.class, () -> SourceBusinessTerm.builder()
                .name(DEFAULT_NAME)
                .id(DEFAULT_ID)
                .description(LONG_DESCRIPTION)
                .addContactRelation(DEFAULT_CONTACT_RELATION)
                .updateTime(DEFAULT_UPDATE_TIME)
                .build());
    }
}
