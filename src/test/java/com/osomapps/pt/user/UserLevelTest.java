package com.osomapps.pt.user;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class UserLevelTest {
    
    @Test
    public void of_3_is_null() {
        assertThat(UserLevel.of(3), nullValue());
    }

}
