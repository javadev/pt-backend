package com.osomapps.pt.user;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

class UserLevelSerializer extends JsonSerializer<UserLevel> {

    @Override
    public void serialize(UserLevel value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) {
            gen.writeNull();
        } else {
            gen.writeNumber(value.getLevel());
        }
    }
}
