package com.osomapps.pt.auth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AuthUserResourceTest {
    @Mock
    private AuthUserService authUserService;    
    
    @InjectMocks
    private AuthUserResource authUserResource;

    @Test
    public void findOne() {
        authUserResource.findOne();
        verify(authUserService).findOne();
    }

}
