package com.osomapps.pt.user;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

class UserLevelDeserializer extends JsonDeserializer<UserLevel> {

    @Override
    public UserLevel deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        Number number = jsonParser.getNumberValue();
        return number == null ? null : UserLevel.of(number.intValue());
    }
}
