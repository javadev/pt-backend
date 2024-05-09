package com.osomapps.pt.reportphoto;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.time.LocalDateTime;
import org.junit.Test;

public class LocalDateTimeSerializerTest {

    @Test
    public void serialize() throws Exception {
        JsonGenerator jsonGenerator = mock(JsonGenerator.class);
        SerializerProvider serializerProvider = mock(SerializerProvider.class);
        new LocalDateTimeSerializer()
                .serialize(LocalDateTime.MAX, jsonGenerator, serializerProvider);
        verify(jsonGenerator).writeString(eq("+999999999-12-31T23:59:59.999999999+00:00"));
    }

    @Test
    public void serialize_null() throws Exception {
        JsonGenerator jsonGenerator = mock(JsonGenerator.class);
        SerializerProvider serializerProvider = mock(SerializerProvider.class);
        new LocalDateTimeSerializer().serialize(null, jsonGenerator, serializerProvider);
        verify(jsonGenerator).writeString((String) eq(null));
    }
}
