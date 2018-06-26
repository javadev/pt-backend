package com.osomapps.pt.programs;

import java.time.LocalDateTime;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class ParseUserGroupTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new ParseUserGroup(
                1L, null, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        ParseUserGroup parseUserGroup = new ParseUserGroup();
        parseUserGroup.setCreated(LocalDateTime.MAX);
        parseUserGroup.setParseGoal(null);
        assertThat(parseUserGroup, notNullValue());
    }

    @Test
    public void getters() {
        ParseUserGroup parseUserGroup = new ParseUserGroup();
        parseUserGroup.getCreated();
        parseUserGroup.getParseGoal();
        assertThat(parseUserGroup, notNullValue());
    }
}
