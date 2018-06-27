package com.osomapps.pt.user;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class UserLevelTest {
    
    @Test
    public void of_3_is_null() {
        assertThat(UserLevel.of(3), nullValue());
    }

}
