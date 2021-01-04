package zeenea.sdk.dataprocess;

import org.junit.jupiter.api.Test;
import zeenea.sdk.ConnectionCode;
import zeenea.sdk.ContactRelation;
import zeenea.sdk.property.*;

import java.math.BigDecimal;
import java.net.URI;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
import static zeenea.sdk.TestUtils.longString;

public class SourceDataProcessTest {

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

        SourceDataProcess sourceDataProcess = SourceDataProcess.builder()
                .name(DEFAULT_NAME)
                .id(DEFAULT_ID)
                .externalId(DEFAULT_EXTERNAL_ID)
                .description(DEFAULT_DESCRIPTION)
                .addMetadata(DEFAULT_METADATA_KEY, DEFAULT_PROPERTY_VALUE)
                .addContactRelation(DEFAULT_CONTACT_RELATION)
                .updateTime(DEFAULT_UPDATE_TIME)
                .addInput(DEFAULT_INPUT)
                .addOutput(DEFAULT_OUTPUT)
                .build();

        assertEquals(DEFAULT_NAME, sourceDataProcess.getName());
        assertEquals(DEFAULT_ID, sourceDataProcess.getId());
        assertEquals(DEFAULT_EXTERNAL_ID, sourceDataProcess.getExternalId());
        assertEquals(DEFAULT_DESCRIPTION, sourceDataProcess.getDescription().get());
        assertTrue(sourceDataProcess.getMetadata().containsKey(DEFAULT_METADATA_KEY.getId()));
        assertTrue(sourceDataProcess.getMetadata().containsValue(DEFAULT_PROPERTY_VALUE));
        assertSame(DEFAULT_CONTACT_RELATION, sourceDataProcess.getContactRelations().iterator().next());
        assertEquals(DEFAULT_UPDATE_TIME, sourceDataProcess.getUpdateTime().get());
        assertEquals(DEFAULT_INPUT, sourceDataProcess.getInputs().iterator().next());
        assertEquals(DEFAULT_OUTPUT, sourceDataProcess.getOutputs().iterator().next());
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

        SourceDataProcess sourceDataProcess = SourceDataProcess.builder()
                .name(DEFAULT_NAME)
                .id(DEFAULT_ID)
                .externalId(DEFAULT_EXTERNAL_ID)
                .addMetadata(stringMetadata, stringValue)
                .addMetadata(numberMetadata, numberValue)
                .addMetadata(instantMetadata, instantValue)
                .addMetadata(urlMetadataWithoutLabel, urlValueWithoutLabel)
                .addMetadata(urlMetadataWithLabel, urlValueWithLabel)
                .build();

        assertEquals(stringValue, sourceDataProcess.getMetadata().get(stringMetadata.getId()));
        assertEquals(numberValue, sourceDataProcess.getMetadata().get(numberMetadata.getId()));
        assertEquals(instantValue, sourceDataProcess.getMetadata().get(instantMetadata.getId()));
        assertEquals(urlValueWithoutLabel, sourceDataProcess.getMetadata().get(urlMetadataWithoutLabel.getId()));
        assertEquals(urlValueWithLabel, sourceDataProcess.getMetadata().get(urlMetadataWithLabel.getId()));
    }

    @Test
    public void dataProcessBuilderShouldForbidLongName() {

        assertThrows(IllegalArgumentException.class, () -> SourceDataProcess.builder()
                .name(LONG_NAME)
                .id(DEFAULT_ID)
                .externalId(DEFAULT_EXTERNAL_ID)
                .description(DEFAULT_DESCRIPTION)
                .addContactRelation(DEFAULT_CONTACT_RELATION)
                .updateTime(DEFAULT_UPDATE_TIME)
                .build());
    }

    @Test
    public void dataProcessBuilderShouldForbidLongId() {

        assertThrows(IllegalArgumentException.class, () -> SourceDataProcess.builder()
                .name(DEFAULT_NAME)
                .id(LONG_ID)
                .externalId(DEFAULT_EXTERNAL_ID)
                .description(DEFAULT_DESCRIPTION)
                .addContactRelation(DEFAULT_CONTACT_RELATION)
                .updateTime(DEFAULT_UPDATE_TIME)
                .build());
    }

    @Test
    public void dataProcessBuilderShouldForbidLongDescription() {

        assertThrows(IllegalArgumentException.class, () -> SourceDataProcess.builder()
                .name(DEFAULT_NAME)
                .id(DEFAULT_ID)
                .externalId(DEFAULT_EXTERNAL_ID)
                .description(LONG_DESCRIPTION)
                .addContactRelation(DEFAULT_CONTACT_RELATION)
                .updateTime(DEFAULT_UPDATE_TIME)
                .build());
    }

}
