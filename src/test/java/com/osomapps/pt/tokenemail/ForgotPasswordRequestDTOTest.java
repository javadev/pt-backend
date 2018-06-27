package com.osomapps.pt.tokenemail;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class ForgotPasswordRequestDTOTest {

    @Test
    public void allArgsConstructor() {
        assertThat(new ForgotPasswordRequestDTO(null), notNullValue());
    }

}
