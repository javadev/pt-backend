package com.osomapps.pt.tokenemail;

import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserSignupRequestDTOTest {

    @Test
    public void allArgsConstructor() {
        assertThat(new UserSignupRequestDTO(null, null, null), notNullValue());
    }

}
