package zeenea.sdk.customitem;

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

public class SourceCustomItemTest {

    static final String DEFAULT_NAME = "custom-item-name";
    static final String LONG_NAME = longString(1024 + 1);
    static final String DEFAULT_ID = "custom-item-id";
    static final String LONG_ID = longString(1024 + 1);
    static final String DEFAULT_CODE = "custom-item-code";
    static final String DEFAULT_DESCRIPTION = "custom-item-description";
    static final String LONG_DESCRIPTION = longString(32 * 1024 + 1);
    static final StringMetadata DEFAULT_METADATA_KEY = new StringMetadata("test", "custom-item-property-key");
    static final StringMetadataValue DEFAULT_PROPERTY_VALUE = new StringMetadataValue("some-value");
    static final SourceContactRelation DEFAULT_CONTACT_RELATION = new SourceContactRelation(SourceContact.builder().email("foobar@example.com").build(), new SourceRole("user"));
    static final Instant DEFAULT_UPDATE_TIME = Instant.now();

    @Test
    public void customItemBuilderShouldReturnAProperCustomItem() {

        SourceCustomItem sourceCustomItem = SourceCustomItem.builder()
                .name(DEFAULT_NAME)
                .id(DEFAULT_ID)
                .code(DEFAULT_CODE)
                .description(DEFAULT_DESCRIPTION)
                .addMetadata(DEFAULT_METADATA_KEY, DEFAULT_PROPERTY_VALUE)
                .addContactRelation(DEFAULT_CONTACT_RELATION)
                .updateTime(DEFAULT_UPDATE_TIME)
                .build();

        assertEquals(DEFAULT_NAME, sourceCustomItem.getName());
        assertEquals(DEFAULT_ID, sourceCustomItem.getId());
        assertEquals(DEFAULT_CODE, sourceCustomItem.getCode());
        assertEquals(DEFAULT_DESCRIPTION, sourceCustomItem.getDescription().get());
        assertTrue(sourceCustomItem.getMetadata().containsKey(DEFAULT_METADATA_KEY.getId()));
        assertTrue(sourceCustomItem.getMetadata().containsValue(DEFAULT_PROPERTY_VALUE));
        assertSame(DEFAULT_CONTACT_RELATION, sourceCustomItem.getContactRelations().iterator().next());
        assertEquals(DEFAULT_UPDATE_TIME, sourceCustomItem.getUpdateTime().get());
    }

    @Test
    public void customItemBuilderShouldAddProperties() {
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

        SourceCustomItem sourceCustomItem = SourceCustomItem.builder()
                .name(DEFAULT_NAME)
                .id(DEFAULT_ID)
                .code(DEFAULT_CODE)
                .addMetadata(stringMetadata, stringValue)
                .addMetadata(numberMetadata, numberValue)
                .addMetadata(instantMetadata, instantValue)
                .addMetadata(urlMetadataWithoutLabel, urlValueWithoutLabel)
                .addMetadata(urlMetadataWithLabel, urlValueWithLabel)
                .build();

        assertEquals(stringValue, sourceCustomItem.getMetadata().get(stringMetadata.getId()));
        assertEquals(numberValue, sourceCustomItem.getMetadata().get(numberMetadata.getId()));
        assertEquals(instantValue, sourceCustomItem.getMetadata().get(instantMetadata.getId()));
        assertEquals(urlValueWithoutLabel, sourceCustomItem.getMetadata().get(urlMetadataWithoutLabel.getId()));
        assertEquals(urlValueWithLabel, sourceCustomItem.getMetadata().get(urlMetadataWithLabel.getId()));
    }

    @Test
    public void customItemBuilderShouldForbidLongName() {

        assertThrows(IllegalArgumentException.class, () -> SourceCustomItem.builder()
                .name(LONG_NAME)
                .id(DEFAULT_ID)
                .code(DEFAULT_CODE)
                .description(DEFAULT_DESCRIPTION)
                .addContactRelation(DEFAULT_CONTACT_RELATION)
                .updateTime(DEFAULT_UPDATE_TIME)
                .build());
    }

    @Test
    public void customItemBuilderShouldForbidLongId() {

        assertThrows(IllegalArgumentException.class, () -> SourceCustomItem.builder()
                .name(DEFAULT_NAME)
                .id(LONG_ID)
                .code(DEFAULT_CODE)
                .description(DEFAULT_DESCRIPTION)
                .addContactRelation(DEFAULT_CONTACT_RELATION)
                .updateTime(DEFAULT_UPDATE_TIME)
                .build());
    }

    @Test
    public void customItemBuilderShouldForbidLongDescription() {

        assertThrows(IllegalArgumentException.class, () -> SourceCustomItem.builder()
                .name(DEFAULT_NAME)
                .id(DEFAULT_ID)
                .code(DEFAULT_CODE)
                .description(LONG_DESCRIPTION)
                .addContactRelation(DEFAULT_CONTACT_RELATION)
                .updateTime(DEFAULT_UPDATE_TIME)
                .build());
    }
}
