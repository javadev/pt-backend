package com.osomapps.pt.token;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.time.LocalDate;
import org.junit.Test;

public class LocalDateSerializerTest {

    @Test
    public void serialize() throws Exception {
        JsonGenerator jsonGenerator = mock(JsonGenerator.class);
        SerializerProvider serializerProvider = mock(SerializerProvider.class);
        new LocalDateSerializer().serialize(LocalDate.MAX, jsonGenerator, serializerProvider);
        verify(jsonGenerator).writeString(eq("+999999999-12-31"));
    }

    @Test
    public void serialize_null() throws Exception {
        JsonGenerator jsonGenerator = mock(JsonGenerator.class);
        SerializerProvider serializerProvider = mock(SerializerProvider.class);
        new LocalDateSerializer().serialize(null, jsonGenerator, serializerProvider);
        verify(jsonGenerator).writeString((String) eq(null));
    }
}
