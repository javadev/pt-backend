package com.osomapps.pt.user;

import com.osomapps.pt.ResourceNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@TestPropertySource("/application-test.properties")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserResourceIntTest {
    
    @Autowired
    UserResource userResource;

    @Test
    public void findOne() throws Exception {
        UserResponseDTO user = userResource.findOne("");
        assertThat(user, notNullValue());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findOneNotFound() throws Exception {
        UserResponseDTO user = userResource.findOne("1");
        assertThat(user, notNullValue());
    }
}
