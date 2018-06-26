package com.osomapps.pt.xlsx;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class UserGroupTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new UserGroup(null, null), notNullValue());
    }

    @Test
    public void tostring() {
        assertThat(new UserGroup(null, null).toString(), notNullValue());
    }
}
