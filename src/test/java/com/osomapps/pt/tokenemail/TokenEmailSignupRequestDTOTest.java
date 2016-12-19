package com.osomapps.pt.tokenemail;

import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.Test;
import static org.junit.Assert.assertThat;

public class TokenEmailSignupRequestDTOTest {

    @Test
    public void allArgsConstructor() {
        assertThat(new TokenEmailSignupRequestDTO(null, null, null), notNullValue());
    }

}
