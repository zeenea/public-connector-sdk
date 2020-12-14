package zeenea.sdk.dataprocess;

import org.junit.jupiter.api.Test;
import zeenea.sdk.ConnectionCode;
import zeenea.sdk.ContactRelation;
import zeenea.sdk.property.*;

import java.math.BigDecimal;
import java.net.URI;
import java.time.Instant;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static zeenea.sdk.TestUtils.longString;

public class DataProcessTest {

    static final String DEFAULT_NAME = "dataprocess-name";
    static final String LONG_NAME = longString(1024 + 1);
    static final String DEFAULT_ID = "dataprocess-id";
    static final String LONG_ID = longString(1024 + 1);
    static final String DEFAULT_EXTERNAL_ID = "dataprocess-external-id";
    static final String DEFAULT_DESCRIPTION = "dataprocess-description";
    static final String LONG_DESCRIPTION = longString(32 * 1024 + 1);
    static final StringMetadata DEFAULT_METADATA_KEY = new StringMetadata("test", "dataprocess-property-key");
    static final StringPropertyValue DEFAULT_PROPERTY_VALUE = new StringPropertyValue("some-value");
    static final DatasetReference DEFAULT_INPUT = new DatasetReference(new ConnectionCode("my-connection"), "external-id");
    static final DatasetReference DEFAULT_OUTPUT = new DatasetReference(new ConnectionCode("my-other-connection"), "other-external-id");
    static final ContactRelation DEFAULT_CONTACT_RELATION = new ContactRelation() {};
    static final Instant DEFAULT_UPDATE_TIME = Instant.now();

    @Test
    public void dataProcessBuilderShouldReturnAProperDataProcess() {

        DataProcess dataProcess = new DataProcess.Builder(DEFAULT_NAME, DEFAULT_ID, DEFAULT_EXTERNAL_ID)
                .description(DEFAULT_DESCRIPTION)
                .addStringMetadata(DEFAULT_METADATA_KEY, DEFAULT_PROPERTY_VALUE)
                .addContactRelation(DEFAULT_CONTACT_RELATION)
                .updateTime(DEFAULT_UPDATE_TIME)
                .addInput(DEFAULT_INPUT)
                .addOutput(DEFAULT_OUTPUT)
                .build();

        assertEquals(DEFAULT_NAME, dataProcess.getName());
        assertEquals(DEFAULT_ID, dataProcess.getId());
        assertEquals(DEFAULT_EXTERNAL_ID, dataProcess.getExternalId());
        assertEquals(DEFAULT_DESCRIPTION, dataProcess.getDescription().get());
        assertTrue(dataProcess.getMetadata().containsKey(DEFAULT_METADATA_KEY.getId()));
        assertTrue(dataProcess.getMetadata().containsValue(DEFAULT_PROPERTY_VALUE));
        assertSame(DEFAULT_CONTACT_RELATION, dataProcess.getContactRelations().iterator().next());
        assertEquals(DEFAULT_UPDATE_TIME, dataProcess.getUpdateTime().get());
        assertEquals(DEFAULT_INPUT, dataProcess.getInputs().iterator().next());
        assertEquals(DEFAULT_OUTPUT, dataProcess.getOutputs().iterator().next());
    }

    @Test
    public void dataProcessBuilderShouldAddProperties() {
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

        DataProcess dataProcess = new DataProcess.Builder(DEFAULT_NAME, DEFAULT_ID, DEFAULT_EXTERNAL_ID)
                .addStringMetadata(stringMetadata, stringValue)
                .addNumberMetadata(numberMetadata, numberValue)
                .addInstantMetadata(instantMetadata, instantValue)
                .addUrlMetadata(urlMetadataWithoutLabel, urlValueWithoutLabel)
                .addUrlMetadata(urlMetadataWithLabel, urlValueWithLabel)
                .build();

        assertEquals(stringValue, dataProcess.getMetadata().get(stringMetadata.getId()));
        assertEquals(numberValue, dataProcess.getMetadata().get(numberMetadata.getId()));
        assertEquals(instantValue, dataProcess.getMetadata().get(instantMetadata.getId()));
        assertEquals(urlValueWithoutLabel, dataProcess.getMetadata().get(urlMetadataWithoutLabel.getId()));
        assertEquals(urlValueWithLabel, dataProcess.getMetadata().get(urlMetadataWithLabel.getId()));
    }

    @Test
    public void dataProcessBuilderShouldForbidLongName() {

        assertThrows(IllegalArgumentException.class, () -> new DataProcess.Builder(LONG_NAME, DEFAULT_ID, DEFAULT_EXTERNAL_ID)
                .description(DEFAULT_DESCRIPTION)
                .addContactRelation(DEFAULT_CONTACT_RELATION)
                .updateTime(DEFAULT_UPDATE_TIME)
                .build());
    }

    @Test
    public void dataProcessBuilderShouldForbidLongId() {

        assertThrows(IllegalArgumentException.class, () -> new DataProcess.Builder(DEFAULT_NAME, LONG_ID, DEFAULT_EXTERNAL_ID)
                .description(DEFAULT_DESCRIPTION)
                .addContactRelation(DEFAULT_CONTACT_RELATION)
                .updateTime(DEFAULT_UPDATE_TIME)
                .build());
    }

    @Test
    public void dataProcessBuilderShouldForbidLongDescription() {

        assertThrows(IllegalArgumentException.class, () -> new DataProcess.Builder(DEFAULT_NAME, DEFAULT_ID, DEFAULT_EXTERNAL_ID)
                .description(LONG_DESCRIPTION)
                .addContactRelation(DEFAULT_CONTACT_RELATION)
                .updateTime(DEFAULT_UPDATE_TIME)
                .build());
    }

}
