package zeenea.connector.datasampling;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class StructSampleValueSerializer extends JsonSerializer<SampleValue.StructSampleValue> {
    @Override
    public void serialize(SampleValue.StructSampleValue value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        for (SampleValue.StructEntrySampleValue entry : value.getValues()) {
            gen.writeFieldName(entry.getKey());
            gen.writeObject(entry.getValue());
        }
        gen.writeEndObject();
    }
}

