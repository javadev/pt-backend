package com.osomapps.pt.activecertificate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.time.LocalDate;
import org.junit.Test;

public class LocalDateSerializerTest {

    @Test
    public void serialize() throws Exception {
        JsonGenerator gen = mock(JsonGenerator.class);
        SerializerProvider serializers = mock(SerializerProvider.class);
        new LocalDateSerializer().serialize(LocalDate.MIN, gen, serializers);
        verify(gen).writeString(eq("-999999999-01-01"));
    }

    @Test
    public void serialize_null() throws Exception {
        JsonGenerator gen = mock(JsonGenerator.class);
        SerializerProvider serializers = mock(SerializerProvider.class);
        new LocalDateSerializer().serialize(null, gen, serializers);
        verify(gen).writeString((String) eq(null));
    }
}
