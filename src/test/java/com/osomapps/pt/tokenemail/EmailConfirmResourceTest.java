package com.osomapps.pt.tokenemail;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.anyString;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EmailConfirmResourceTest {

    @Mock
    private TokenEmailSignupService tokenEmailSignupService;    

    @InjectMocks
    private EmailConfirmResource emailConfirmResource;

    @Test
    public void list() {
        assertThat(emailConfirmResource.list().size(), equalTo(0));
    }

    @Test
    public void create() {
        emailConfirmResource.create("");
        verify(tokenEmailSignupService).confirmToken(anyString());
    }

    @Test
    public void create_true() {
        when(tokenEmailSignupService.confirmToken(anyString())).thenReturn(true);
        emailConfirmResource.create("");
        verify(tokenEmailSignupService).confirmToken(anyString());
    }
}
