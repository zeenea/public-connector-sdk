package zeenea.sdk;

import org.junit.jupiter.api.Test;

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
    static final String DEFAULT_METADATA_KEY = "custom-item-metadata-key";
    static final Metadata DEFAULT_METADATA = new Metadata();
    static final ContactRelation DEFAULT_CONTACT_RELATION = new ContactRelation() {};
    static final Instant DEFAULT_UPDATE_TIME = Instant.now();

    @Test
    public void customItemBuilderShouldReturnAProperCustomItem() {

        CustomItem customItem = new CustomItem.Builder(DEFAULT_NAME, DEFAULT_ID, DEFAULT_CODE)
                .description(DEFAULT_DESCRIPTION)
                .addMetadata(DEFAULT_METADATA_KEY, DEFAULT_METADATA)
                .addContactRelation(DEFAULT_CONTACT_RELATION)
                .updateTime(DEFAULT_UPDATE_TIME)
                .build();

        assertEquals(DEFAULT_NAME, customItem.getName());
        assertEquals(DEFAULT_ID, customItem.getId());
        assertEquals(DEFAULT_CODE, customItem.getCode());
        assertEquals(DEFAULT_DESCRIPTION, customItem.getDescription().get());
        assertTrue(customItem.getMetadata().containsValue(DEFAULT_METADATA));
        assertSame(DEFAULT_CONTACT_RELATION, customItem.getContactRelations().iterator().next());
        assertEquals(DEFAULT_UPDATE_TIME, customItem.getUpdateTime().get());
    }

    @Test
    public void customItemBuilderShouldForbidLongName() {

        assertThrows(IllegalArgumentException.class, () -> new CustomItem.Builder(LONG_NAME, DEFAULT_ID, DEFAULT_CODE)
                .description(DEFAULT_DESCRIPTION)
                .addMetadata(DEFAULT_METADATA_KEY, DEFAULT_METADATA)
                .addContactRelation(DEFAULT_CONTACT_RELATION)
                .updateTime(DEFAULT_UPDATE_TIME)
                .build());
    }

    @Test
    public void customItemBuilderShouldForbidLongId() {

        assertThrows(IllegalArgumentException.class, () -> new CustomItem.Builder(DEFAULT_NAME, LONG_ID, DEFAULT_CODE)
                .description(DEFAULT_DESCRIPTION)
                .addMetadata(DEFAULT_METADATA_KEY, DEFAULT_METADATA)
                .addContactRelation(DEFAULT_CONTACT_RELATION)
                .updateTime(DEFAULT_UPDATE_TIME)
                .build());
    }

    @Test
    public void customItemBuilderShouldForbidLongDescription() {

        assertThrows(IllegalArgumentException.class, () -> new CustomItem.Builder(DEFAULT_NAME, DEFAULT_ID, DEFAULT_CODE)
                .description(LONG_DESCRIPTION)
                .addMetadata(DEFAULT_METADATA_KEY, DEFAULT_METADATA)
                .addContactRelation(DEFAULT_CONTACT_RELATION)
                .updateTime(DEFAULT_UPDATE_TIME)
                .build());
    }

    private static String longString(int count) {
        return String.join("", Collections.nCopies(count, "a"));
    }
}
