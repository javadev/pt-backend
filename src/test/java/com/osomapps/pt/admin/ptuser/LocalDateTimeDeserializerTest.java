package com.osomapps.pt.admin.ptuser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.time.LocalDateTime;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LocalDateTimeDeserializerTest {

    @Test
    public void deserialize() throws IOException {
        JsonParser jsonParser = mock(JsonParser.class);
        when(jsonParser.getText()).thenReturn("2016-01-01T00:00:00");
        LocalDateTime localDateTime = new LocalDateTimeDeserializer()
                .deserialize(jsonParser, mock(DeserializationContext.class));
        assertThat(localDateTime, notNullValue());
    }

    @Test
    public void deserialize_with_z() throws IOException {
        JsonParser jsonParser = mock(JsonParser.class);
        when(jsonParser.getText()).thenReturn("2016-01-01T00:00:00Z");
        LocalDateTime localDateTime = new LocalDateTimeDeserializer()
                .deserialize(jsonParser, mock(DeserializationContext.class));
        assertThat(localDateTime, notNullValue());
    }

}
