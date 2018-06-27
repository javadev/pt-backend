package com.osomapps.pt.user;

import com.osomapps.pt.UnauthorizedException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@TestPropertySource("/application-test.properties")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserResourceIntTest {
    
    @Autowired
    UserResource userResource;

    @Test
    public void findOne() throws Exception {
        UserResponseDTO user = userResource.findOne("");
        assertThat(user, notNullValue());
    }

    @Test
    public void findOneNotFound() throws Exception {
        assertThrows(UnauthorizedException.class, () -> {userResource.findOne("1");});
    }
}
