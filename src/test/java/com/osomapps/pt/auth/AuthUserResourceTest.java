package com.osomapps.pt.auth;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AuthUserResourceTest {
    @Mock private AuthUserService authUserService;

    @InjectMocks private AuthUserResource authUserResource;

    @Test
    public void findOne() {
        authUserResource.findOne();
        verify(authUserService).findOne();
    }
}
