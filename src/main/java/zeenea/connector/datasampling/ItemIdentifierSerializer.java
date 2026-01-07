package zeenea.connector.datasampling;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import zeenea.connector.common.ItemIdentifier;

import java.io.IOException;

public class ItemIdentifierSerializer extends JsonSerializer<ItemIdentifier> {
    @Override
    public void serialize(ItemIdentifier itemIdentifier, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        itemIdentifier.getIdentificationProperties().forEach(prop -> {
            try {
                gen.writeStringField(prop.getKey(), prop.getValue());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        gen.writeEndObject();
    }
}

