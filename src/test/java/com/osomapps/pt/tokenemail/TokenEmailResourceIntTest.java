package com.osomapps.pt.tokenemail;

import com.osomapps.pt.UnauthorizedException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.MatcherAssert.assertThat;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@TestPropertySource("/application-test.properties")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TokenEmailResourceIntTest {
    
    @Autowired
    TokenEmailResource tokenEmailResource;
    @Autowired
    InUserEmailRepository inUserEmailRepository;

    @BeforeEach
    public void before() {
        inUserEmailRepository.deleteAll();
    }

    @Test
    public void create() {
        assertThrows(UnauthorizedException.class, () -> {tokenEmailResource.create(new TokenEmailRequestDTO("name", "test@mail.com", "test"),
                new MockHttpServletRequest());});
    }

    @Test
    public void delete() {
        assertThrows(UnauthorizedException.class, () -> {tokenEmailResource.delete("", new MockHttpServletRequest());});
    }
}
