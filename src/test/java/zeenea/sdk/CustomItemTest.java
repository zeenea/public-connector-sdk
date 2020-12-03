package zeenea.sdk;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.net.URI;
import java.time.Instant;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class CustomItemTest {

    static final String DEFAULT_NAME = "custom-item-name";
    static final String LONG_NAME = longString(1024 + 1);
    static final String DEFAULT_ID = "custom-item-id";
    static final String LONG_ID = longString(1024 + 1);
    static final String DEFAULT_CODE = "custom-item-code";
    static final String DEFAULT_DESCRIPTION = "custom-item-description";
    static final String LONG_DESCRIPTION = longString(32 * 1024 + 1);
    static final Metadata DEFAULT_METADATA_KEY = new Metadata("test", "custom-item-property-key", PropertyType.STRING);
    static final PropertyValue DEFAULT_PROPERTY_VALUE = new StringPropertyValue("some-value");
    static final ContactRelation DEFAULT_CONTACT_RELATION = new ContactRelation() {};
    static final Instant DEFAULT_UPDATE_TIME = Instant.now();

    @Test
    public void customItemBuilderShouldReturnAProperCustomItem() {

        CustomItem customItem = new CustomItem.Builder(DEFAULT_NAME, DEFAULT_ID, DEFAULT_CODE)
                .description(DEFAULT_DESCRIPTION)
                .addMetadata(DEFAULT_METADATA_KEY, DEFAULT_PROPERTY_VALUE)
                .addContactRelation(DEFAULT_CONTACT_RELATION)
                .updateTime(DEFAULT_UPDATE_TIME)
                .build();

        assertEquals(DEFAULT_NAME, customItem.getName());
        assertEquals(DEFAULT_ID, customItem.getId());
        assertEquals(DEFAULT_CODE, customItem.getCode());
        assertEquals(DEFAULT_DESCRIPTION, customItem.getDescription().get());
        assertTrue(customItem.getMetadata().containsKey(DEFAULT_METADATA_KEY));
        assertTrue(customItem.getMetadata().containsValue(DEFAULT_PROPERTY_VALUE));
        assertSame(DEFAULT_CONTACT_RELATION, customItem.getContactRelations().iterator().next());
        assertEquals(DEFAULT_UPDATE_TIME, customItem.getUpdateTime().get());
    }

    @Test
    public void customItemBuilderShouldAddProperties() {
        Metadata metadata1 = new Metadata("test", "property1", PropertyType.STRING);
        StringPropertyValue stringValue = new StringPropertyValue("a string value");
        Metadata metadata2 = new Metadata("test", "property2", PropertyType.Number);
        NumberPropertyValue numberValue = new NumberPropertyValue(new BigDecimal("42.01"));
        Metadata metadata3 = new Metadata("test", "property3", PropertyType.Instant);
        InstantPropertyValue instantValue = new InstantPropertyValue(Instant.now());
        Metadata metadata4 = new Metadata("test", "property4", PropertyType.URL);
        UrlPropertyValue urlValueWithoutLabel = new UrlPropertyValue(URI.create("http://localhost:9000"));
        Metadata metadata5 = new Metadata("test", "property5", PropertyType.URL);
        UrlPropertyValue urlValueWithLabel = new UrlPropertyValue(URI.create("http://localhost:9000"), "zeenea");

        CustomItem customItem = new CustomItem.Builder(DEFAULT_NAME, DEFAULT_ID, DEFAULT_CODE)
                .addMetadata(metadata1, stringValue)
                .addMetadata(metadata2, numberValue)
                .addMetadata(metadata3, instantValue)
                .addMetadata(metadata4, urlValueWithoutLabel)
                .addMetadata(metadata5, urlValueWithLabel)
                .build();

        assertEquals(stringValue, customItem.getMetadata().get(metadata1));
        assertEquals(numberValue, customItem.getMetadata().get(metadata2));
        assertEquals(instantValue, customItem.getMetadata().get(metadata3));
        assertEquals(urlValueWithoutLabel, customItem.getMetadata().get(metadata4));
        assertEquals(urlValueWithLabel, customItem.getMetadata().get(metadata5));
    }

    @Test
    public void customItemBuilderShouldForbidLongName() {

        assertThrows(IllegalArgumentException.class, () -> new CustomItem.Builder(LONG_NAME, DEFAULT_ID, DEFAULT_CODE)
                .description(DEFAULT_DESCRIPTION)
                .addContactRelation(DEFAULT_CONTACT_RELATION)
                .updateTime(DEFAULT_UPDATE_TIME)
                .build());
    }

    @Test
    public void customItemBuilderShouldForbidLongId() {

        assertThrows(IllegalArgumentException.class, () -> new CustomItem.Builder(DEFAULT_NAME, LONG_ID, DEFAULT_CODE)
                .description(DEFAULT_DESCRIPTION)
                .addContactRelation(DEFAULT_CONTACT_RELATION)
                .updateTime(DEFAULT_UPDATE_TIME)
                .build());
    }

    @Test
    public void customItemBuilderShouldForbidLongDescription() {

        assertThrows(IllegalArgumentException.class, () -> new CustomItem.Builder(DEFAULT_NAME, DEFAULT_ID, DEFAULT_CODE)
                .description(LONG_DESCRIPTION)
                .addContactRelation(DEFAULT_CONTACT_RELATION)
                .updateTime(DEFAULT_UPDATE_TIME)
                .build());
    }

    private static String longString(int count) {
        return String.join("", Collections.nCopies(count, "a"));
    }
}
