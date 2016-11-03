package com.github.pt.token;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TokenResourceTest {

    @Mock
    private InUserRepository inUserRepository;
    
    @Mock
    private InUserLoginRepository inUserLoginRepository;

    @Mock
    private InUserLogoutRepository inUserLogoutRepository;
    
    @Mock
    private TokenService tokenService;    
    
    @InjectMocks
    private TokenResource tokenResource;

    @Test
    public void testCreate() throws Exception {
        
    }

}