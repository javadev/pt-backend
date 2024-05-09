package com.osomapps.pt.token;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;

@RunWith(MockitoJUnitRunner.class)
public class TokenResourceTest {

    @Mock private TokenService tokenService;

    @InjectMocks private TokenResource tokenResource;

    @Test
    public void create() {
        tokenResource.create(new TokenRequestDTO(), new MockHttpServletRequest());
        verify(tokenService).createOrReadNewToken(any(TokenRequestDTO.class), anyString());
    }

    @Test
    public void delete() {
        tokenResource.delete("", new MockHttpServletRequest());
        verify(tokenService).deleteToken(anyString(), anyString());
    }
}
