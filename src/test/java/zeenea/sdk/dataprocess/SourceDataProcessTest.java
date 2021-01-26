package zeenea.sdk.dataprocess;

import org.junit.jupiter.api.Test;
import zeenea.sdk.ConnectionCode;
import zeenea.sdk.contact.SourceContactRelation;
import zeenea.sdk.metadata.*;

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
    static final StringMetadata DEFAULT_METADATA_KEY = new StringMetadata("dataprocess-property-key");
    static final StringMetadataValue DEFAULT_PROPERTY_VALUE = new StringMetadataValue("some-value");
    static final DatasetReference DEFAULT_INPUT = new DatasetReference(new ConnectionCode("my-connection"), "external-id");
    static final DatasetReference DEFAULT_OUTPUT = new DatasetReference(new ConnectionCode("my-other-connection"), "other-external-id");
    public static final SourceContactRelation DEFAULT_CONTACT_RELATION = SourceContactRelation.builder()
            .email("foobar@example.com")
            .role("user")
            .build();
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
        assertTrue(sourceDataProcess.getMetadata().containsKey(DEFAULT_METADATA_KEY.getCode()));
        assertTrue(sourceDataProcess.getMetadata().containsValue(DEFAULT_PROPERTY_VALUE));
        assertSame(DEFAULT_CONTACT_RELATION, sourceDataProcess.getContactRelations().iterator().next());
        assertEquals(DEFAULT_UPDATE_TIME, sourceDataProcess.getUpdateTime().get());
        assertEquals(DEFAULT_INPUT, sourceDataProcess.getInputs().iterator().next());
        assertEquals(DEFAULT_OUTPUT, sourceDataProcess.getOutputs().iterator().next());
    }

    @Test
    public void dataProcessBuilderShouldAddProperties() {
        StringMetadata stringMetadata = new StringMetadata("property1");
        StringMetadataValue stringValue = new StringMetadataValue("a string value");
        NumberMetadata numberMetadata = new NumberMetadata("property2");
        NumberMetadataValue numberValue = new NumberMetadataValue(new BigDecimal("42.01"));
        InstantMetadata instantMetadata = new InstantMetadata("property3");
        InstantMetadataValue instantValue = new InstantMetadataValue(Instant.now());
        UrlMetadata urlMetadataWithoutLabel = new UrlMetadata("property4");
        UrlMetadataValue urlValueWithoutLabel = new UrlMetadataValue(URI.create("http://localhost:9000"));
        UrlMetadata urlMetadataWithLabel = new UrlMetadata("property5");
        UrlMetadataValue urlValueWithLabel = new UrlMetadataValue(URI.create("http://localhost:9000"), "zeenea");

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

        assertEquals(stringValue, sourceDataProcess.getMetadata().get(stringMetadata.getCode()));
        assertEquals(numberValue, sourceDataProcess.getMetadata().get(numberMetadata.getCode()));
        assertEquals(instantValue, sourceDataProcess.getMetadata().get(instantMetadata.getCode()));
        assertEquals(urlValueWithoutLabel, sourceDataProcess.getMetadata().get(urlMetadataWithoutLabel.getCode()));
        assertEquals(urlValueWithLabel, sourceDataProcess.getMetadata().get(urlMetadataWithLabel.getCode()));
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
