package com.osomapps.pt;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

@RunWith(MockitoJUnitRunner.class)
public class CustomErrorControllerTest {
    
    @Mock
    private ErrorAttributes errorAttributes;
    
    @InjectMocks
    private CustomErrorController customErrorController;
    
    @Test
    public void error() {
        customErrorController.error(mock(WebRequest.class), new MockHttpServletResponse());
        verify(errorAttributes).getErrorAttributes(any(WebRequest.class), anyBoolean());
    }

    @Test
    public void getErrorPath() {
        assertThat(customErrorController.getErrorPath(), equalTo("/error"));
    }
}
