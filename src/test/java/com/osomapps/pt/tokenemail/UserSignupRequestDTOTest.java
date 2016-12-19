package com.osomapps.pt.tokenemail;

import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.Test;
import static org.junit.Assert.assertThat;

public class UserSignupRequestDTOTest {

    @Test
    public void allArgsConstructor() {
        assertThat(new UserSignupRequestDTO(null, null, null), notNullValue());
    }

}
