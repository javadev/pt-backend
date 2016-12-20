package com.osomapps.pt.tokenemail;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.anyString;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;

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
}
