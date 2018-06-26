package com.osomapps.pt.user;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.jupiter.api.Test;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class UserLevelSerializerTest {

    @Test
    public void serialize() throws Exception {
        JsonGenerator gen = mock(JsonGenerator.class);
        SerializerProvider serializers = mock(SerializerProvider.class);
        new  UserLevelSerializer().serialize(UserLevel.Unexperienced, gen, serializers);
        verify(gen).writeNumber(eq(1));
    }

    @Test
    public void serialize_null() throws Exception {
        JsonGenerator gen = mock(JsonGenerator.class);
        SerializerProvider serializers = mock(SerializerProvider.class);
        new UserLevelSerializer().serialize(null, gen, serializers);
        verify(gen).writeNull();
    }
}
