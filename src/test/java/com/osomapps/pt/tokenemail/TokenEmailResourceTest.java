package com.osomapps.pt.tokenemail;

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
public class TokenEmailResourceTest {

    @Mock private TokenEmailService tokenEmailService;

    @InjectMocks private TokenEmailResource tokenEmailResource;

    @Test
    public void create() {
        tokenEmailResource.create(new TokenEmailRequestDTO(), new MockHttpServletRequest());
        verify(tokenEmailService)
                .createOrReadNewToken(any(TokenEmailRequestDTO.class), anyString());
    }

    @Test
    public void delete() {
        tokenEmailResource.delete("", new MockHttpServletRequest());
        verify(tokenEmailService).deleteToken(anyString(), anyString());
    }
}
