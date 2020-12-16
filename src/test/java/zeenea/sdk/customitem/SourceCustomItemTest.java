package zeenea.sdk.customitem;

import org.junit.jupiter.api.Test;
import zeenea.sdk.ContactRelation;
import zeenea.sdk.property.*;

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
    static final StringPropertyValue DEFAULT_PROPERTY_VALUE = new StringPropertyValue("some-value");
    static final ContactRelation DEFAULT_CONTACT_RELATION = new ContactRelation() {};
    static final Instant DEFAULT_UPDATE_TIME = Instant.now();

    @Test
    public void customItemBuilderShouldReturnAProperCustomItem() {

        SourceCustomItem sourceCustomItem = new SourceCustomItem.Builder(DEFAULT_NAME, DEFAULT_ID, DEFAULT_CODE)
                .description(DEFAULT_DESCRIPTION)
                .addStringMetadata(DEFAULT_METADATA_KEY, DEFAULT_PROPERTY_VALUE)
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
        StringPropertyValue stringValue = new StringPropertyValue("a string value");
        NumberMetadata numberMetadata = new NumberMetadata("test", "property2");
        NumberPropertyValue numberValue = new NumberPropertyValue(new BigDecimal("42.01"));
        InstantMetadata instantMetadata = new InstantMetadata("test", "property3");
        InstantPropertyValue instantValue = new InstantPropertyValue(Instant.now());
        UrlMetadata urlMetadataWithoutLabel = new UrlMetadata("test", "property4");
        UrlPropertyValue urlValueWithoutLabel = new UrlPropertyValue(URI.create("http://localhost:9000"));
        UrlMetadata urlMetadataWithLabel = new UrlMetadata("test", "property5");
        UrlPropertyValue urlValueWithLabel = new UrlPropertyValue(URI.create("http://localhost:9000"), "zeenea");

        SourceCustomItem sourceCustomItem = new SourceCustomItem.Builder(DEFAULT_NAME, DEFAULT_ID, DEFAULT_CODE)
                .addStringMetadata(stringMetadata, stringValue)
                .addNumberMetadata(numberMetadata, numberValue)
                .addInstantMetadata(instantMetadata, instantValue)
                .addUrlMetadata(urlMetadataWithoutLabel, urlValueWithoutLabel)
                .addUrlMetadata(urlMetadataWithLabel, urlValueWithLabel)
                .build();

        assertEquals(stringValue, sourceCustomItem.getMetadata().get(stringMetadata.getId()));
        assertEquals(numberValue, sourceCustomItem.getMetadata().get(numberMetadata.getId()));
        assertEquals(instantValue, sourceCustomItem.getMetadata().get(instantMetadata.getId()));
        assertEquals(urlValueWithoutLabel, sourceCustomItem.getMetadata().get(urlMetadataWithoutLabel.getId()));
        assertEquals(urlValueWithLabel, sourceCustomItem.getMetadata().get(urlMetadataWithLabel.getId()));
    }

    @Test
    public void customItemBuilderShouldForbidLongName() {

        assertThrows(IllegalArgumentException.class, () -> new SourceCustomItem.Builder(LONG_NAME, DEFAULT_ID, DEFAULT_CODE)
                .description(DEFAULT_DESCRIPTION)
                .addContactRelation(DEFAULT_CONTACT_RELATION)
                .updateTime(DEFAULT_UPDATE_TIME)
                .build());
    }

    @Test
    public void customItemBuilderShouldForbidLongId() {

        assertThrows(IllegalArgumentException.class, () -> new SourceCustomItem.Builder(DEFAULT_NAME, LONG_ID, DEFAULT_CODE)
                .description(DEFAULT_DESCRIPTION)
                .addContactRelation(DEFAULT_CONTACT_RELATION)
                .updateTime(DEFAULT_UPDATE_TIME)
                .build());
    }

    @Test
    public void customItemBuilderShouldForbidLongDescription() {

        assertThrows(IllegalArgumentException.class, () -> new SourceCustomItem.Builder(DEFAULT_NAME, DEFAULT_ID, DEFAULT_CODE)
                .description(LONG_DESCRIPTION)
                .addContactRelation(DEFAULT_CONTACT_RELATION)
                .updateTime(DEFAULT_UPDATE_TIME)
                .build());
    }
}
