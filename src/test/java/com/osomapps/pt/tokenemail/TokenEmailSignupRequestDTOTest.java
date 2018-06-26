package com.osomapps.pt.tokenemail;

import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;

public class TokenEmailSignupRequestDTOTest {

    @Test
    public void allArgsConstructor() {
        assertThat(new TokenEmailSignupRequestDTO(null, null, null), notNullValue());
    }

}
