package com.osomapps.pt.user;

import java.util.Optional;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class UserGenderTest {

    @Test
    public void of_male2_is_empty() {
        assertThat(UserGender.of("male2"), equalTo(Optional.empty()));
    }

    @Test
    public void of_male() {
        assertThat(UserGender.of("male"), equalTo(Optional.of(UserGender.Male)));
    }

}
