package com.osomapps.pt.tokenemail;

import java.util.Optional;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.anyString;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EmailResetResourceTest {

    @Mock
    private TokenEmailSignupService tokenEmailSignupService;    

    @InjectMocks
    private EmailResetResource emailResetResource;

    @Test
    public void list() {
        assertThat(emailResetResource.list().size(), equalTo(0));
    }

    @Test
    public void create() {
        when(tokenEmailSignupService.resetToken(anyString())).thenReturn(Optional.empty());
        emailResetResource.create("");
        verify(tokenEmailSignupService).resetToken(anyString());
    }

    @Test
    public void create_true() {
        when(tokenEmailSignupService.resetToken(anyString())).thenReturn(Optional.of("new"));
        emailResetResource.create("");
        verify(tokenEmailSignupService).resetToken(anyString());
    }
}
