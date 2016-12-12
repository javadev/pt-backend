package com.osomapps.pt.tokenemail;

import com.osomapps.pt.ResourceNotFoundException;
import com.osomapps.pt.UnauthorizedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@TestPropertySource("/application-test.properties")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TokenEmailResourceIntTest {
    
    @Autowired
    TokenEmailResource tokenEmailResource;
    @Autowired
    InUserEmailRepository inUserEmailRepository;

    @Before
    public void before() {
        inUserEmailRepository.deleteAll();
    }

    @Test(expected = UnauthorizedException.class)
    public void create() {
        TokenEmailResponseDTO user = tokenEmailResource.create(new TokenEmailRequestDTO("name", "test@mail.com", "test"),
                new MockHttpServletRequest());
        assertThat(user, notNullValue());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void delete() {
        tokenEmailResource.delete("", new MockHttpServletRequest());
    }
}
