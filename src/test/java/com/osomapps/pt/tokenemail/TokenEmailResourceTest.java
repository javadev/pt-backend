package com.osomapps.pt.tokenemail;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;

@ExtendWith(MockitoExtension.class)
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
