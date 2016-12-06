package com.github.pt.tokenemail;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;

@RunWith(MockitoJUnitRunner.class)
public class TokenEmailResourceTest {

    @Mock
    private TokenEmailService tokenEmailService;    

    @InjectMocks
    private TokenEmailResource tokenEmailResource;

    @Test
    public void create() {
        tokenEmailResource.create(new TokenEmailRequestDTO(), new MockHttpServletRequest());
        verify(tokenEmailService).createOrReadNewToken(any(TokenEmailRequestDTO.class), anyString());
    }

    @Test
    public void delete() {
        tokenEmailResource.delete("", new MockHttpServletRequest());
        verify(tokenEmailService).deleteToken(anyString(), anyString());
    }
}
