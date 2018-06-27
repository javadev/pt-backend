package com.osomapps.pt.user;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserLevelDeserializerTest {

    @Test
    public void deserialize() throws IOException {
        JsonParser jsonParser = mock(JsonParser.class);
        when(jsonParser.getNumberValue()).thenReturn(1);
        UserLevel userLevel = new UserLevelDeserializer()
                .deserialize(jsonParser, mock(DeserializationContext.class));
        assertThat(userLevel, equalTo(UserLevel.Unexperienced));
    }

    @Test
    public void deserialize_null() throws IOException {
        JsonParser jsonParser = mock(JsonParser.class);
        when(jsonParser.getNumberValue()).thenReturn(null);
        UserLevel userLevel = new UserLevelDeserializer()
                .deserialize(jsonParser, mock(DeserializationContext.class));
        assertThat(userLevel, nullValue());
    }
}
